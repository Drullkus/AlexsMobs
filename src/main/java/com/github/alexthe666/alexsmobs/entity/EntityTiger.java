package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.ai.*;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.server.entity.collision.ICustomCollisions;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.EnumSet;
import java.util.UUID;
import java.util.function.Predicate;

public class EntityTiger extends Animal implements ICustomCollisions, IAnimatedEntity, NeutralMob, ITargetsDroppedItems {

    public static final Animation ANIMATION_PAW_R = Animation.create(15);
    public static final Animation ANIMATION_PAW_L = Animation.create(15);
    public static final Animation ANIMATION_TAIL_FLICK = Animation.create(45);
    public static final Animation ANIMATION_LEAP = Animation.create(20);
    private static final EntityDataAccessor<Boolean> WHITE = SynchedEntityData.defineId(EntityTiger.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RUNNING = SynchedEntityData.defineId(EntityTiger.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(EntityTiger.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityTiger.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> STEALTH_MODE = SynchedEntityData.defineId(EntityTiger.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HOLDING = SynchedEntityData.defineId(EntityTiger.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(EntityTiger.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> LAST_SCARED_MOB_ID = SynchedEntityData.defineId(EntityTiger.class, EntityDataSerializers.INT);
    private static final UniformInt ANGRY_TIMER = TimeUtil.rangeOfSeconds(40, 80);
    private static final Predicate<LivingEntity> NO_BLESSING_EFFECT = (mob) -> {
        return !mob.hasEffect(AMEffectRegistry.TIGERS_BLESSING.value());
    };
    public float prevSitProgress;
    public float sitProgress;
    public float prevSleepProgress;
    public float sleepProgress;
    public float prevHoldProgress;
    public float holdProgress;
    public float prevStealthProgress;
    public float stealthProgress;
    private int animationTick;
    private Animation currentAnimation;
    private boolean hasSpedUp = false;
    private UUID lastHurtBy;
    private int sittingTime;
    private int maxSitTime;
    private int holdTime = 0;
    private int prevScaredMobId = -1;
    private boolean dontSitFlag = false;

    protected EntityTiger(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0);
        this.moveControl = new MovementControllerCustomCollisions(this);
    }

    public static boolean canTigerSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return worldIn.getRawBrightness(pos, 0) > 8;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 50D).add(Attributes.ATTACK_DAMAGE, 12.0D).add(Attributes.MOVEMENT_SPEED, 0.25F).add(Attributes.FOLLOW_RANGE, 86);
    }

    public boolean checkSpawnRules(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.tigerSpawnRolls, this.getRandom(), spawnReasonIn);
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader worldIn) {
        return worldIn.getFluidState(pos.below()).isEmpty() && worldIn.getFluidState(pos).is(FluidTags.WATER) ? 0.0F : super.getWalkTargetValue(pos, worldIn);
    }

    public boolean checkSpawnObstruction(LevelReader worldIn) {
        return !worldIn.containsAnyLiquid(this.getBoundingBox());
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("TigerSitting", this.isSitting());
        compound.putBoolean("TigerSleeping", this.isSleeping());
        compound.putBoolean("White", this.isWhite());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setSitting(compound.getBoolean("TigerSitting"));
        this.setSleeping(compound.getBoolean("TigerSleeping"));
        this.setWhite(compound.getBoolean("White"));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WHITE, false);
        this.entityData.define(RUNNING, false);
        this.entityData.define(SITTING, false);
        this.entityData.define(STEALTH_MODE, false);
        this.entityData.define(HOLDING, false);
        this.entityData.define(SLEEPING, false);
        this.entityData.define(ANGER_TIME, 0);
        this.entityData.define(LAST_SCARED_MOB_ID, -1);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AnimalAIPanicBaby(this, 1.25D));
        this.goalSelector.addGoal(3, new AIMelee());
        this.goalSelector.addGoal(5, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new AnimalAIWanderRanged(this, 60, 1.0D, 14, 7));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 25F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new CreatureAITargetItems(this, false, 10));
        this.targetSelector.addGoal(2, (new AngerGoal(this)));
        this.targetSelector.addGoal(3, new AttackPlayerGoal());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, LivingEntity.class, 220, false, false, AMEntityRegistry.buildPredicateFromTag(AMTagRegistry.TIGER_TARGETS)) {
            public boolean canUse() {
                return !EntityTiger.this.isBaby() && super.canUse();
            }
        });
        this.targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    protected SoundEvent getAmbientSound() {
        return isStealth() ? super.getAmbientSound() : getRemainingPersistentAngerTime() > 0 ? AMSoundRegistry.TIGER_ANGRY.value() : AMSoundRegistry.TIGER_IDLE.value();
    }

    public int getAmbientSoundInterval() {
        return getRemainingPersistentAngerTime() > 0 ? 40 : 80;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return AMSoundRegistry.TIGER_HURT.value();
    }

    protected SoundEvent getDeathSound() {
        return AMSoundRegistry.TIGER_HURT.value();
    }



    protected float getWaterSlowDown() {
        return 0.99F;
    }

    public boolean shouldMove() {
        return !isSitting() && !isSleeping() && !this.isHolding();
    }

    public double getVisibilityPercent(Entity lookingEntity) {
        if (this.isStealth()) {
            return 0.2D;
        }
        return super.getVisibilityPercent(lookingEntity);
    }

    public boolean isFood(ItemStack stack) {
        return stack.is(AMTagRegistry.TIGER_BREEDABLES);
    }

    //killEntity
    public void awardKillScore(LivingEntity entity, int score, DamageSource src) {
        this.heal(5);
        super.awardKillScore(entity, score, src);
    }

    public void travel(Vec3 vec3d) {
        if (!this.shouldMove()) {
            if (this.getNavigation().getPath() != null) {
                this.getNavigation().stop();
            }
            vec3d = Vec3.ZERO;
        }
        super.travel(vec3d);
    }

    protected PathNavigation createNavigation(Level worldIn) {
        return new Navigator(this, worldIn);
    }

    public boolean isWhite() {
        return this.entityData.get(WHITE);
    }

    public void setWhite(boolean white) {
        this.entityData.set(WHITE, white);
    }

    public boolean isRunning() {
        return this.entityData.get(RUNNING);
    }

    public void setRunning(boolean running) {
        this.entityData.set(RUNNING, running);
    }

    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }

    public void setSitting(boolean bar) {
        this.entityData.set(SITTING, bar);
    }

    public boolean isStealth() {
        return this.entityData.get(STEALTH_MODE);
    }

    public void setStealth(boolean bar) {
        this.entityData.set(STEALTH_MODE, bar);
    }

    public boolean isHolding() {
        return this.entityData.get(HOLDING);
    }

    public void setHolding(boolean running) {
        this.entityData.set(HOLDING, running);
    }

    public boolean isSleeping() {
        return this.entityData.get(SLEEPING);
    }

    public void setSleeping(boolean sleeping) {
        this.entityData.set(SLEEPING, sleeping);
    }

    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int time) {
        this.entityData.set(ANGER_TIME, time);
    }

    public UUID getPersistentAngerTarget() {
        return this.lastHurtBy;
    }

    public void setPersistentAngerTarget(UUID target) {
        this.lastHurtBy = target;
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(ANGRY_TIMER.sample(this.random));
    }

    protected void customServerAiStep() {
        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel) this.level(), false);
        }
    }

    public boolean isColliding(BlockPos pos, BlockState blockstate) {
        return !(blockstate.getBlock() == Blocks.BAMBOO || blockstate.is(BlockTags.LEAVES)) && super.isColliding(pos, blockstate);
    }

    public Vec3 collide(Vec3 vec3) {
        return ICustomCollisions.getAllowedMovementForEntity(this, vec3);
    }

    public void tick() {
        super.tick();
        prevSitProgress = sitProgress;
        prevSleepProgress = sleepProgress;
        prevHoldProgress = holdProgress;
        prevStealthProgress = stealthProgress;

        final boolean sitting = isSitting();
        final boolean sleeping = isSleeping();
        final boolean holding = isHolding();
        final boolean stealth = isStealth();

        if (sitting) {
            if (sitProgress < 5F)
                sitProgress++;
        } else {
            if (sitProgress > 0F)
                sitProgress--;
        }

        if (sleeping) {
            if (sleepProgress < 5F)
                sleepProgress++;
        } else {
            if (sleepProgress > 0F)
                sleepProgress--;
        }

        if (holding) {
            if (holdProgress < 5F)
                holdProgress++;
        } else {
            if (holdProgress > 0F)
                holdProgress--;
        }

        if (stealth) {
            if (stealthProgress < 10F)
                stealthProgress += 0.25F;
        } else {
            if (stealthProgress > 0F)
                stealthProgress--;
        }

        if (!this.level().isClientSide) {
            if (isRunning() && !hasSpedUp) {
                hasSpedUp = true;
                this.setMaxUpStep(1F);
                this.setSprinting(true);
                this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F);
            }
            if (!isRunning() && hasSpedUp) {
                hasSpedUp = false;
                this.setMaxUpStep(0.6F);
                this.setSprinting(false);
                this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25F);
            }
            if ((isSitting() || isSleeping()) && (++sittingTime > maxSitTime || this.getTarget() != null || this.isInLove() || dontSitFlag || this.isInWaterOrBubble())) {
                this.setSitting(false);
                this.setSleeping(false);
                sittingTime = 0;
                maxSitTime = 100 + random.nextInt(50);
            }
            if (this.getTarget() == null && !dontSitFlag && this.getDeltaMovement().lengthSqr() < 0.03D && this.getAnimation() == NO_ANIMATION && !this.isSleeping() && !this.isSitting() && !this.isInWaterOrBubble() && random.nextInt(100) == 0) {
                sittingTime = 0;
                if (this.getRandom().nextBoolean()) {
                    maxSitTime = 100 + random.nextInt(550);
                    this.setSitting(true);
                    this.setSleeping(false);
                } else {
                    maxSitTime = 200 + random.nextInt(550);
                    this.setSitting(false);
                    this.setSleeping(true);
                }
            }
            if (this.getDeltaMovement().lengthSqr() < 0.03D && this.getAnimation() == NO_ANIMATION && !this.isSleeping() && !this.isSitting() && random.nextInt(100) == 0) {
                this.setAnimation(ANIMATION_TAIL_FLICK);
            }
        }
        if (this.isHolding()) {
            this.setSprinting(false);
            this.setRunning(false);
            if (!this.level().isClientSide && this.getTarget() != null && this.getTarget().isAlive()) {
                this.setXRot(0);
                final float radius = 1.0F + this.getTarget().getBbWidth() * 0.5F;
                final float angle = (Maths.STARTING_ANGLE * this.yBodyRot);
                final double extraX = radius * Mth.sin(Mth.PI + angle);
                final double extraZ = radius * Mth.cos(angle);
                final double extraY = -0.5F;
                Vec3 minus = new Vec3(this.getX() + extraX - this.getTarget().getX(), this.getY() + extraY - this.getTarget().getY(), this.getZ() + extraZ - this.getTarget().getZ());
                this.getTarget().setDeltaMovement(minus);
                if (holdTime % 20 == 0) {
                    this.getTarget().hurt(this.damageSources().mobAttack(this), 5 + this.getRandom().nextInt(2));
                }
            }
            holdTime++;
            if (holdTime > 100) {
                holdTime = 0;
                this.setHolding(false);
            }
        } else {
            holdTime = 0;
        }
        if (prevScaredMobId != this.entityData.get(LAST_SCARED_MOB_ID) && this.level().isClientSide) {
            Entity e = level().getEntity(this.entityData.get(LAST_SCARED_MOB_ID));
            if (e != null) {
                final double d2 = this.random.nextGaussian() * 0.1D;
                final double d0 = this.random.nextGaussian() * 0.1D;
                final double d1 = this.random.nextGaussian() * 0.1D;
                this.level().addParticle(AMParticleRegistry.SHOCKED.value(), e.getX(), e.getEyeY() + e.getBbHeight() * 0.15F + (double) (this.random.nextFloat() * e.getBbHeight() * 0.15F), e.getZ(), d0, d1, d2);
            }
        }
        if(this.getTarget() != null && this.getTarget().hasEffect(AMEffectRegistry.TIGERS_BLESSING.value())){
            this.setTarget(null);
            this.setLastHurtByMob(null);
        }
        prevScaredMobId = this.entityData.get(LAST_SCARED_MOB_ID);
        AnimationHandler.INSTANCE.updateAnimations(this);
    }

    public boolean hurt(DamageSource source, float amount) {
        final boolean prev = super.hurt(source, amount);
        if (prev) {
            if (source.getEntity() != null) {
                if (source.getEntity() instanceof LivingEntity) {
                    LivingEntity hurter = (LivingEntity) source.getEntity();
                    if (hurter.hasEffect(AMEffectRegistry.TIGERS_BLESSING.value())) {
                        hurter.removeEffect(AMEffectRegistry.TIGERS_BLESSING.value());
                    }
                }
            }
            return prev;
        }
        return prev;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public BlockPos getLightPosition() {
        BlockPos pos = AMBlockPos.fromVec3(this.position());
        if (!level().getBlockState(pos).canOcclude()) {
            return pos.above();
        }
        return pos;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        final boolean whiteOther = p_241840_2_ instanceof EntityTiger && ((EntityTiger) p_241840_2_).isWhite();
        EntityTiger baby = AMEntityRegistry.TIGER.value().create(p_241840_1_);
        double whiteChance = 0.1D;
        if (this.isWhite() && whiteOther) {
            whiteChance = 0.8D;
        }
        if (this.isWhite() != whiteOther) {
            whiteChance = 0.4D;
        }
        baby.setWhite(random.nextDouble() < whiteChance);
        return baby;
    }

    @Override
    public boolean canPassThrough(BlockPos mutablePos, BlockState blockstate, VoxelShape voxelshape) {
        return blockstate.getBlock() == Blocks.BAMBOO || blockstate.is(BlockTags.LEAVES);
    }

    @Override
    public Animation getAnimation() {
        return currentAnimation;
    }

    @Override
    public void setAnimation(Animation animation) {
        currentAnimation = animation;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_PAW_R, ANIMATION_PAW_L, ANIMATION_LEAP, ANIMATION_TAIL_FLICK};
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    public void push(Entity entityIn) {
        if (!this.isHolding() || entityIn != this.getTarget()) {
            super.push(entityIn);
        }
    }

    @Override
    protected void doPush(Entity entityIn) {
        if (!this.isHolding() || entityIn != this.getTarget()) {
            super.doPush(entityIn);
        }
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return stack.getItem().isEdible() && stack.getItem().getFoodProperties() != null && stack.getItem().getFoodProperties().isMeat() && stack.getItem() != Items.ROTTEN_FLESH;
    }

    public double getMaxDistToItem() {
        return 3.0D;
    }

    @Override
    public void onGetItem(ItemEntity e) {
        this.dontSitFlag = false;
        ItemStack stack = e.getItem();
        if (stack.getItem().isEdible() && stack.getItem().getFoodProperties() != null && stack.getItem().getFoodProperties().isMeat() && stack.getItem() != Items.ROTTEN_FLESH) {
            this.gameEvent(GameEvent.EAT);
            this.playSound(SoundEvents.CAT_EAT, this.getVoicePitch(), this.getSoundVolume());
            this.heal(5);
            Entity thrower = e.getOwner();
            if (thrower != null && random.nextFloat() < getChanceForEffect(stack) && level().getPlayerByUUID(thrower.getUUID()) != null) {
                Player player = level().getPlayerByUUID(thrower.getUUID());
                player.addEffect(new MobEffectInstance(AMEffectRegistry.TIGERS_BLESSING.value(), 12000));
                this.setTarget(null);
                this.setLastHurtByMob(null);
            }
        }
    }

    public void onFindTarget(ItemEntity e) {
        this.dontSitFlag = true;
        this.setSitting(false);
        this.setSleeping(false);
    }

    public double getChanceForEffect(ItemStack stack) {
        if (stack.getItem() == Items.PORKCHOP || stack.getItem() == Items.COOKED_PORKCHOP) {
            return 0.4F;
        }
        if (stack.getItem() == Items.CHICKEN || stack.getItem() == Items.COOKED_CHICKEN) {
            return 0.3F;
        }
        return 0.1F;
    }

    protected void jumpFromGround() {
        if (!this.isSleeping() && !this.isSitting()) {
            super.jumpFromGround();
        }
    }

    static class TigerNodeEvaluator extends WalkNodeEvaluator {
        protected BlockPathTypes evaluateBlockPathType(BlockGetter level, BlockPos pos, BlockPathTypes typeIn) {
            return typeIn == BlockPathTypes.LEAVES || level.getBlockState(pos).getBlock() == Blocks.BAMBOO ? BlockPathTypes.OPEN : super.evaluateBlockPathType(level, pos, typeIn);
        }
    }

    static class Navigator extends GroundPathNavigatorWide {

        public Navigator(Mob mob, Level world) {
            super(mob, world, 1.2F);
        }

        protected PathFinder createPathFinder(int i) {
            this.nodeEvaluator = new TigerNodeEvaluator();
            return new PathFinder(this.nodeEvaluator, i);
        }
    }

    private class AIMelee extends Goal {
        private final EntityTiger tiger;
        private int jumpAttemptCooldown = 0;

        public AIMelee() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
            tiger = EntityTiger.this;
        }

        @Override
        public boolean canUse() {
            return tiger.getTarget() != null && tiger.getTarget().isAlive();
        }

        public void tick() {
            if (jumpAttemptCooldown > 0) {
                jumpAttemptCooldown--;
            }
            LivingEntity target = tiger.getTarget();
            if (target != null && target.isAlive()) {
                final double dist = tiger.distanceTo(target);
                if (dist < 10 && tiger.getLastHurtByMob() != null && tiger.getLastHurtByMob().isAlive()) {
                    tiger.setStealth(false);
                } else {
                    if (dist > 20) {
                        tiger.setRunning(false);
                        tiger.setStealth(true);
                    }
                }
                if (dist <= 20) {
                    tiger.setStealth(false);
                    tiger.setRunning(true);
                    if (tiger.entityData.get(LAST_SCARED_MOB_ID) != target.getId()) {
                        tiger.entityData.set(LAST_SCARED_MOB_ID, target.getId());
                        target.addEffect(new MobEffectInstance(AMEffectRegistry.FEAR.value(), 100, 0, true, false));
                    }
                }
                if (dist < 12 && tiger.getAnimation() == NO_ANIMATION && tiger.onGround() && jumpAttemptCooldown == 0 && !tiger.isHolding()) {
                    tiger.setAnimation(ANIMATION_LEAP);
                    jumpAttemptCooldown = 70;
                }
                if ((jumpAttemptCooldown > 0 || tiger.isInWaterOrBubble()) && !tiger.isHolding() && tiger.getAnimation() == NO_ANIMATION && dist < 4 + target.getBbWidth()) {
                    tiger.setAnimation(tiger.getRandom().nextBoolean() ? ANIMATION_PAW_L : ANIMATION_PAW_R);
                }
                if (dist < 4 + target.getBbWidth() && (tiger.getAnimation() == ANIMATION_PAW_L || tiger.getAnimation() == ANIMATION_PAW_R) && tiger.getAnimationTick() == 8) {
                    target.hurt(tiger.damageSources().mobAttack(tiger), 7 + tiger.getRandom().nextInt(5));
                }
                if (tiger.getAnimation() == ANIMATION_LEAP) {
                    tiger.getNavigation().stop();
                    Vec3 vec = target.position().subtract(tiger.position());
                    tiger.setYRot(-((float) Mth.atan2(vec.x, vec.z)) * Mth.RAD_TO_DEG);
                    tiger.yBodyRot = tiger.getYRot();
                    if (tiger.getAnimationTick() >= 5 && tiger.getAnimationTick() < 11 && tiger.onGround()) {
                        Vec3 vector3d1 = new Vec3(target.getX() - this.tiger.getX(), 0.0D, target.getZ() - this.tiger.getZ());
                        if (vector3d1.lengthSqr() > 1.0E-7D) {
                            vector3d1 = vector3d1.normalize().scale(Math.min(dist, 15) * 0.2F);
                        }
                        this.tiger.setDeltaMovement(vector3d1.x, vector3d1.y + 0.3F + 0.1F * Mth.clamp(target.getEyeY() - this.tiger.getY(), 0, 2), vector3d1.z);
                    }
                    if (dist < target.getBbWidth() + 3 && tiger.getAnimationTick() >= 15) {
                        target.hurt(tiger.damageSources().mobAttack(tiger), 2);
                        tiger.setRunning(false);
                        tiger.setStealth(false);
                        tiger.setHolding(true);
                    }
                } else {
                    if(target != null){
                        tiger.getNavigation().moveTo(target, tiger.isStealth() ? 0.75F : 1.0F);
                    }
                }
            }
        }

        public void stop() {
            tiger.setStealth(false);
            tiger.setRunning(false);
            tiger.setHolding(false);
        }
    }

    class AttackPlayerGoal extends NearestAttackableTargetGoal<Player> {

        public AttackPlayerGoal() {
            super(EntityTiger.this, Player.class, 100, false, true, NO_BLESSING_EFFECT);
        }

        public boolean canUse() {
            if (EntityTiger.this.isBaby()) {
                return false;
            } else {
                return super.canUse();
            }
        }

        protected double getFollowDistance() {
            return 4.0D;
        }
    }

    class AngerGoal extends HurtByTargetGoal {
        AngerGoal(EntityTiger beeIn) {
            super(beeIn);
        }

        public boolean canContinueToUse() {
            return EntityTiger.this.isAngry() && super.canContinueToUse();
        }

        public void start() {
            super.start();
            if (EntityTiger.this.isBaby()) {
                this.alertOthers();
                this.stop();
            }

        }

        protected void alertOther(Mob mobIn, LivingEntity targetIn) {
            if (!mobIn.isBaby()) {
                super.alertOther(mobIn, targetIn);
            }
        }
    }
}
