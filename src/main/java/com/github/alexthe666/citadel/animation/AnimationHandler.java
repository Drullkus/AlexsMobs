package com.github.alexthe666.citadel.animation;

import com.github.alexthe666.citadel.Citadel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

/**
 * @author iLexiconn
 * @since 1.0.0
 */
public enum AnimationHandler {
    INSTANCE;

    /**
     * Sends an animation packet to all clients, notifying them of a changed animation
     *
     * @param entity    the entity with an animation to be updated
     * @param animation the animation to be updated
     * @param <T>       the entity type
     */
    public <T extends Entity & IAnimatedEntity> void sendAnimationMessage(T entity, Animation animation) {
        if (entity.level().isClientSide || !(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        entity.setAnimation(animation);
        //Citadel.sendMSGToAll(serverLevel, new AnimationMessage(entity.getId(), ArrayUtils.indexOf(entity.getAnimations(), animation)));
        Citadel.sendAnimationToAll(entity, animation, serverLevel);
    }

    /**
     * Updates all animations for a given entity
     *
     * @param entity the entity with an animation to be updated
     * @param <T>    the entity type
     */
    public <T extends Entity & IAnimatedEntity> void updateAnimations(T entity) {
        if (entity.getAnimation() == null) {
            entity.setAnimation(IAnimatedEntity.NO_ANIMATION);
        } else if (entity.getAnimation() != IAnimatedEntity.NO_ANIMATION) {
            if (entity.getAnimationTick() == 0) {
                //AnimationEvent event = new AnimationEvent.Start(entity, ((IAnimatedEntity)entity).getAnimation());
                //if (!MinecraftForge.EVENT_BUS.post(event)) {
                //    this.sendAnimationMessage(entity, event.getAnimation());
                //}
                this.sendAnimationMessage(entity, entity.getAnimation());
            }

            if (entity.getAnimationTick() < entity.getAnimation().getDuration()) {
                entity.setAnimationTick(entity.getAnimationTick() + 1);
                //MinecraftForge.EVENT_BUS.post(new AnimationEvent.Tick(entity, ((IAnimatedEntity)entity).getAnimation(), ((IAnimatedEntity)entity).getAnimationTick()));
            }

            if (entity.getAnimationTick() == entity.getAnimation().getDuration()) {
                entity.setAnimationTick(0);
                entity.setAnimation(IAnimatedEntity.NO_ANIMATION);
            }
        }
    }
}