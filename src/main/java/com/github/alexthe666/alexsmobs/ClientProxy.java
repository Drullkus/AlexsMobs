package com.github.alexthe666.alexsmobs;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.client.particle.ParticleTeethGlint;
import com.github.alexthe666.alexsmobs.client.render.*;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@OnlyIn(Dist.CLIENT)
//@Mod.EventBusSubscriber(modid = AlexsMobs.MODID, value = Dist.CLIENT)
public class ClientProxy /*extends CommonProxy*/ {

    //public static final Int2ObjectMap<SoundBearMusicBox> BEAR_MUSIC_BOX_SOUND_MAP = new Int2ObjectOpenHashMap<>();
    //public static final Int2ObjectMap<SoundLaCucaracha> COCKROACH_SOUND_MAP = new Int2ObjectOpenHashMap<>();
    //public static final Int2ObjectMap<SoundWormBoss> WORMBOSS_SOUND_MAP = new Int2ObjectOpenHashMap<>();
    public static final List<UUID> currentUnrenderedEntities = new ArrayList<>();
    public static int voidPortalCreationTime = 0;
    public CameraType prevPOV = CameraType.FIRST_PERSON;
    public boolean initializedRainbowBuffers = false;
    private int pupfishChunkX = 0;
    private int pupfishChunkZ = 0;
    private int singingBlueJayId = -1;
    private final ItemStack[] transmuteStacks = new ItemStack[3];

    /*@SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemColors(RegisterColorHandlersEvent.Item event) {

        AlexsMobs.LOGGER.info("loaded in item colorizer");
        if(AMItemRegistry.STRADDLEBOARD.isPresent()){
            event.register((stack, colorIn) -> colorIn < 1 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), AMItemRegistry.STRADDLEBOARD.get());
        }else{
            AlexsMobs.LOGGER.warn("Could not add straddleboard item to colorizer...");
        }
    }*/

    /*@SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onBlockColors(RegisterColorHandlersEvent.Block event) {
        AlexsMobs.LOGGER.info("loaded in block colorizer");
        event.register((state, tintGetter, pos, tint) -> {
            return tintGetter != null && pos != null ? RainbowUtil.calculateGlassColor(pos) : -1;
        }, AMBlockRegistry.RAINBOW_GLASS.get());
    }*/

    public static void init() {
        //IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        //bus.addListener(ClientProxy::onBakingCompleted);
        //bus.addListener(ClientProxy::onItemColors);
        //bus.addListener(ClientProxy::onBlockColors);
        //bus.addListener(ClientLayerRegistry::onAddLayers);
        //bus.addListener(ClientProxy::setupParticles);

        clientInit();
    }

    public static void clientInit() {
        //initRainbowBuffers();
        //ItemRenderer itemRendererIn = Minecraft.getInstance().getItemRenderer();
        //EntityRendererRegistry.register(AMEntityRegistry.GRIZZLY_BEAR.get(), RenderGrizzlyBear::new);
        //EntityRendererRegistry.register(AMEntityRegistry.ROADRUNNER.get(), RenderRoadrunner::new);
        //EntityRendererRegistry.register(AMEntityRegistry.BONE_SERPENT.get(), RenderBoneSerpent::new);
        //EntityRendererRegistry.register(AMEntityRegistry.BONE_SERPENT_PART.get(), RenderBoneSerpentPart::new);
        //EntityRendererRegistry.register(AMEntityRegistry.GAZELLE.get(), RenderGazelle::new);
        EntityRendererRegistry.register(AMEntityRegistry.CROCODILE.value(), RenderCrocodile::new);
        //egister(AMEntityRegistry.FLY.get(), RenderFly::new);
        //egister(AMEntityRegistry.HUMMINGBIRD.get(), RenderHummingbird::new);
        //egister(AMEntityRegistry.ORCA.get(), RenderOrca::new);
        //egister(AMEntityRegistry.SUNBIRD.get(), RenderSunbird::new);
        EntityRendererRegistry.register(AMEntityRegistry.GORILLA.value(), RenderGorilla::new);
        //EntityRendererRegistry.register(AMEntityRegistry.CRIMSON_MOSQUITO.get(), RenderCrimsonMosquito::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MOSQUITO_SPIT.get(), RenderMosquitoSpit::new);
        //EntityRendererRegistry.register(AMEntityRegistry.RATTLESNAKE.get(), RenderRattlesnake::new);
        //EntityRendererRegistry.register(AMEntityRegistry.ENDERGRADE.get(), RenderEndergrade::new);
        EntityRendererRegistry.register(AMEntityRegistry.HAMMERHEAD_SHARK.value(), RenderHammerheadShark::new);
        EntityRendererRegistry.register(AMEntityRegistry.SHARK_TOOTH_ARROW.value(), RenderSharkToothArrow::new);
        //EntityRendererRegistry.register(AMEntityRegistry.LOBSTER.get(), RenderLobster::new);
        EntityRendererRegistry.register(AMEntityRegistry.KOMODO_DRAGON.value(), RenderKomodoDragon::new);
        EntityRendererRegistry.register(AMEntityRegistry.CAPUCHIN_MONKEY.value(), RenderCapuchinMonkey::new);
        EntityRendererRegistry.register(AMEntityRegistry.TOSSED_ITEM.value(), RenderTossedItem::new);
        //EntityRendererRegistry.register(AMEntityRegistry.CENTIPEDE_HEAD.get(), RenderCentipedeHead::new);
        //EntityRendererRegistry.register(AMEntityRegistry.CENTIPEDE_BODY.get(), RenderCentipedeBody::new);
        //EntityRendererRegistry.register(AMEntityRegistry.CENTIPEDE_TAIL.get(), RenderCentipedeTail::new);
        //EntityRendererRegistry.register(AMEntityRegistry.WARPED_TOAD.get(), RenderWarpedToad::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MOOSE.get(), RenderMoose::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MIMICUBE.get(), RenderMimicube::new);
        //EntityRendererRegistry.register(AMEntityRegistry.RACCOON.get(), RenderRaccoon::new);
        //EntityRendererRegistry.register(AMEntityRegistry.BLOBFISH.get(), RenderBlobfish::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SEAL.get(), RenderSeal::new);
        //EntityRendererRegistry.register(AMEntityRegistry.COCKROACH.get(), RenderCockroach::new);
        //EntityRendererRegistry.register(AMEntityRegistry.COCKROACH_EGG.get(), (render) -> {
        //    return new ThrownItemRenderer<>(render, 0.75F, true);
        //});
        //EntityRendererRegistry.register(AMEntityRegistry.SHOEBILL.get(), RenderShoebill::new);
        //EntityRendererRegistry.register(AMEntityRegistry.ELEPHANT.get(), RenderElephant::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SOUL_VULTURE.get(), RenderSoulVulture::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SNOW_LEOPARD.get(), RenderSnowLeopard::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SPECTRE.get(), RenderSpectre::new);
        //EntityRendererRegistry.register(AMEntityRegistry.CROW.get(), RenderCrow::new);
        //EntityRendererRegistry.register(AMEntityRegistry.ALLIGATOR_SNAPPING_TURTLE.get(), RenderAlligatorSnappingTurtle::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MUNGUS.get(), RenderMungus::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MANTIS_SHRIMP.get(), RenderMantisShrimp::new);
        //EntityRendererRegistry.register(AMEntityRegistry.GUSTER.get(), RenderGuster::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SAND_SHOT.get(), RenderSandShot::new);
        //EntityRendererRegistry.register(AMEntityRegistry.GUST.get(), RenderGust::new);
        //EntityRendererRegistry.register(AMEntityRegistry.WARPED_MOSCO.get(), RenderWarpedMosco::new);
        //EntityRendererRegistry.register(AMEntityRegistry.HEMOLYMPH.get(), RenderHemolymph::new);
        //EntityRendererRegistry.register(AMEntityRegistry.STRADDLER.get(), RenderStraddler::new);
        //EntityRendererRegistry.register(AMEntityRegistry.STRADPOLE.get(), RenderStradpole::new);
        //EntityRendererRegistry.register(AMEntityRegistry.STRADDLEBOARD.get(), RenderStraddleboard::new);
        //EntityRendererRegistry.register(AMEntityRegistry.EMU.get(), RenderEmu::new);
        //EntityRendererRegistry.register(AMEntityRegistry.EMU_EGG.get(), (render) -> {
        //    return new ThrownItemRenderer<>(render, 0.75F, true);
        //});
        //EntityRendererRegistry.register(AMEntityRegistry.PLATYPUS.get(), RenderPlatypus::new);
        //EntityRendererRegistry.register(AMEntityRegistry.DROPBEAR.get(), RenderDropBear::new);
        //EntityRendererRegistry.register(AMEntityRegistry.TASMANIAN_DEVIL.get(), RenderTasmanianDevil::new);
        //EntityRendererRegistry.register(AMEntityRegistry.KANGAROO.get(), RenderKangaroo::new);
        //EntityRendererRegistry.register(AMEntityRegistry.CACHALOT_WHALE.get(), RenderCachalotWhale::new);
        //EntityRendererRegistry.register(AMEntityRegistry.CACHALOT_ECHO.get(), RenderCachalotEcho::new);
        EntityRendererRegistry.register(AMEntityRegistry.LEAFCUTTER_ANT.value(), RenderLeafcutterAnt::new);
        //EntityRendererRegistry.register(AMEntityRegistry.ENDERIOPHAGE.get(), RenderEnderiophage::new);
        //EntityRendererRegistry.register(AMEntityRegistry.ENDERIOPHAGE_ROCKET.get(), (render) -> {
        //    return new ThrownItemRenderer<>(render, 0.75F, true);
        //});
        //EntityRendererRegistry.register(AMEntityRegistry.BALD_EAGLE.get(), RenderBaldEagle::new);
        EntityRendererRegistry.register(AMEntityRegistry.TIGER.value(), RenderTiger::new);
        //EntityRendererRegistry.register(AMEntityRegistry.TARANTULA_HAWK.get(), RenderTarantulaHawk::new);
        //EntityRendererRegistry.register(AMEntityRegistry.VOID_WORM.get(), RenderVoidWormHead::new);
        //EntityRendererRegistry.register(AMEntityRegistry.VOID_WORM_PART.get(), RenderVoidWormBody::new);
        //EntityRendererRegistry.register(AMEntityRegistry.VOID_WORM_SHOT.get(), RenderVoidWormShot::new);
        //EntityRendererRegistry.register(AMEntityRegistry.VOID_PORTAL.get(), RenderVoidPortal::new);
        EntityRendererRegistry.register(AMEntityRegistry.FRILLED_SHARK.value(), RenderFrilledShark::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MIMIC_OCTOPUS.get(), RenderMimicOctopus::new);
        EntityRendererRegistry.register(AMEntityRegistry.SEAGULL.value(), RenderSeagull::new);
        //EntityRendererRegistry.register(AMEntityRegistry.FROSTSTALKER.get(), RenderFroststalker::new);
        //EntityRendererRegistry.register(AMEntityRegistry.ICE_SHARD.get(), RenderIceShard::new);
        //EntityRendererRegistry.register(AMEntityRegistry.TUSKLIN.get(), RenderTusklin::new);
        //EntityRendererRegistry.register(AMEntityRegistry.LAVIATHAN.get(), RenderLaviathan::new);
        //EntityRendererRegistry.register(AMEntityRegistry.COSMAW.get(), RenderCosmaw::new);
        EntityRendererRegistry.register(AMEntityRegistry.TOUCAN.value(), RenderToucan::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MANED_WOLF.get(), RenderManedWolf::new);
        EntityRendererRegistry.register(AMEntityRegistry.ANACONDA.value(), RenderAnaconda::new);
        EntityRendererRegistry.register(AMEntityRegistry.ANACONDA_PART.value(), RenderAnacondaPart::new);
        //EntityRendererRegistry.register(AMEntityRegistry.VINE_LASSO.get(), RenderVineLasso::new);
        EntityRendererRegistry.register(AMEntityRegistry.ANTEATER.value(), RenderAnteater::new);
        EntityRendererRegistry.register(AMEntityRegistry.ROCKY_ROLLER.value(), RenderRockyRoller::new);
        //EntityRendererRegistry.register(AMEntityRegistry.FLUTTER.get(), RenderFlutter::new);
        //EntityRendererRegistry.register(AMEntityRegistry.POLLEN_BALL.get(), RenderPollenBall::new);
        //EntityRendererRegistry.register(AMEntityRegistry.GELADA_MONKEY.get(), RenderGeladaMonkey::new);
        //EntityRendererRegistry.register(AMEntityRegistry.JERBOA.get(), RenderJerboa::new);
        //EntityRendererRegistry.register(AMEntityRegistry.TERRAPIN.get(), RenderTerrapin::new);
        //EntityRendererRegistry.register(AMEntityRegistry.COMB_JELLY.get(), RenderCombJelly::new);
        //EntityRendererRegistry.register(AMEntityRegistry.COSMIC_COD.get(), RenderCosmicCod::new);
        //EntityRendererRegistry.register(AMEntityRegistry.BUNFUNGUS.get(), RenderBunfungus::new);
        //EntityRendererRegistry.register(AMEntityRegistry.BISON.get(), RenderBison::new);
        //EntityRendererRegistry.register(AMEntityRegistry.GIANT_SQUID.get(), RenderGiantSquid::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SQUID_GRAPPLE.get(), RenderSquidGrapple::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SEA_BEAR.get(), RenderSeaBear::new);
        //EntityRendererRegistry.register(AMEntityRegistry.DEVILS_HOLE_PUPFISH.get(), RenderDevilsHolePupfish::new);
        //EntityRendererRegistry.register(AMEntityRegistry.CATFISH.value(), RenderCatfish::new);
        //EntityRendererRegistry.register(AMEntityRegistry.FLYING_FISH.get(), RenderFlyingFish::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SKELEWAG.get(), RenderSkelewag::new);
        //EntityRendererRegistry.register(AMEntityRegistry.RAIN_FROG.get(), RenderRainFrog::new);
        //EntityRendererRegistry.register(AMEntityRegistry.POTOO.get(), RenderPotoo::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MUDSKIPPER.get(), RenderMudskipper::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MUD_BALL.get(), RenderMudBall::new);
        //EntityRendererRegistry.register(AMEntityRegistry.RHINOCEROS.get(), RenderRhinoceros::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SUGAR_GLIDER.get(), RenderSugarGlider::new);
        //EntityRendererRegistry.register(AMEntityRegistry.FARSEER.get(), RenderFarseer::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SKREECHER.get(), RenderSkreecher::new);
        //EntityRendererRegistry.register(AMEntityRegistry.UNDERMINER.get(), RenderUnderminer::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MURMUR.get(), RenderMurmurBody::new);
        //EntityRendererRegistry.register(AMEntityRegistry.MURMUR_HEAD.get(), RenderMurmurHead::new);
        //EntityRendererRegistry.register(AMEntityRegistry.TENDON_SEGMENT.get(), RenderTendonSegment::new);
        //EntityRendererRegistry.register(AMEntityRegistry.SKUNK.get(), RenderSkunk::new);
        //EntityRendererRegistry.register(AMEntityRegistry.FART.get(), RenderFart::new);
        EntityRendererRegistry.register(AMEntityRegistry.BANANA_SLUG.value(), RenderBananaSlug::new);
        //EntityRendererRegistry.register(AMEntityRegistry.BLUE_JAY.get(), RenderBlueJay::new);
        EntityRendererRegistry.register(AMEntityRegistry.CAIMAN.value(), RenderCaiman::new);
        //EntityRendererRegistry.register(AMEntityRegistry.TRIOPS.get(), RenderTriops::new);
        /*MinecraftForge.EVENT_BUS.register(new ClientEvents());
        try {
            ItemProperties.register(AMItemRegistry.BLOOD_SPRAYER.get(), new ResourceLocation("empty"), (stack, p_239428_1_, p_239428_2_, j) -> {
                return !ItemBloodSprayer.isUsable(stack) || p_239428_2_ instanceof Player && ((Player) p_239428_2_).getCooldowns().isOnCooldown(AMItemRegistry.BLOOD_SPRAYER.get()) ? 1.0F : 0.0F;
            });
            ItemProperties.register(AMItemRegistry.HEMOLYMPH_BLASTER.get(), new ResourceLocation("empty"), (stack, p_239428_1_, p_239428_2_, j) -> {
                return !ItemHemolymphBlaster.isUsable(stack) || p_239428_2_ instanceof Player && ((Player) p_239428_2_).getCooldowns().isOnCooldown(AMItemRegistry.HEMOLYMPH_BLASTER.get()) ? 1.0F : 0.0F;
            });
            ItemProperties.register(AMItemRegistry.TARANTULA_HAWK_ELYTRA.get(), new ResourceLocation("broken"), (stack, p_239428_1_, p_239428_2_, j) -> {
                return ItemTarantulaHawkElytra.isUsable(stack) ? 0.0F : 1.0F;
            });
            ItemProperties.register(AMItemRegistry.SHIELD_OF_THE_DEEP.get(), new ResourceLocation("blocking"), (stack, p_239421_1_, p_239421_2_, j) -> {
                return p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F;
            });
            ItemProperties.register(AMItemRegistry.SOMBRERO.get(), new ResourceLocation("silly"), (stack, p_239421_1_, p_239421_2_, j) -> {
                return AlexsMobs.isAprilFools() ? 1.0F : 0.0F;
            });
            ItemProperties.register(AMItemRegistry.TENDON_WHIP.get(), new ResourceLocation("active"), (stack, p_239421_1_, holder, j) -> {
                return ItemTendonWhip.isActive(stack, holder) ? 1.0F : 0.0F;
            });
            ItemProperties.register(AMItemRegistry.PUPFISH_LOCATOR.get(), new ResourceLocation("in_chunk"), (stack, world, entity, j) -> {
                int x = pupfishChunkX * 16;
                int z = pupfishChunkZ * 16;
                if (entity != null && entity.getX() >= x && entity.getX() <= x + 16 && entity.getZ() >= z && entity.getZ() <= z + 16) {
                    return 1.0F;
                }
                return 0.0F;
            });
            ItemProperties.register(AMItemRegistry.SKELEWAG_SWORD.get(), new ResourceLocation("blocking"), (stack, p_239421_1_, p_239421_2_, j) -> {
                return p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F;
            });
        } catch (Exception e) {
            AlexsMobs.LOGGER.warn("Could not load item models for weapons");
        }
        BlockEntityRendererRegistry.register(AMTileEntityRegistry.CAPSID.get(), RenderCapsid::new);
        BlockEntityRendererRegistry.register(AMTileEntityRegistry.VOID_WORM_BEAK.get(), RenderVoidWormBeak::new);
        BlockEntityRendererRegistry.register(AMTileEntityRegistry.TRANSMUTATION_TABLE.get(), RenderTransmutationTable::new);
        MenuScreens.register(AMMenuRegistry.TRANSMUTATION_TABLE.get(), GUITransmutationTable::new);*/
    }

    //private void initRainbowBuffers() {
    //    Minecraft.getInstance().renderBuffers().fixedBuffers.put(AMRenderTypes.COMBJELLY_RAINBOW_GLINT, new BufferBuilder(AMRenderTypes.COMBJELLY_RAINBOW_GLINT.bufferSize()));
    //    Minecraft.getInstance().renderBuffers().fixedBuffers.put(AMRenderTypes.VOID_WORM_PORTAL_OVERLAY, new BufferBuilder(AMRenderTypes.VOID_WORM_PORTAL_OVERLAY.bufferSize()));
    //    Minecraft.getInstance().renderBuffers().fixedBuffers.put(AMRenderTypes.STATIC_PORTAL, new BufferBuilder(AMRenderTypes.STATIC_PORTAL.bufferSize()));
    //    Minecraft.getInstance().renderBuffers().fixedBuffers.put(AMRenderTypes.STATIC_PARTICLE, new BufferBuilder(AMRenderTypes.STATIC_PARTICLE.bufferSize()));
    //    Minecraft.getInstance().renderBuffers().fixedBuffers.put(AMRenderTypes.STATIC_ENTITY, new BufferBuilder(AMRenderTypes.STATIC_ENTITY.bufferSize()));
    //    initializedRainbowBuffers = true;
    //}

    //private static void onBakingCompleted(final ModelEvent.ModifyBakingResult e) {
    //    String ghostlyPickaxe = "alexsmobs:ghostly_pickaxe";
    //    for (ResourceLocation id : e.getModels().keySet()) {
    //        if (id.toString().contains(ghostlyPickaxe)) {
    //            e.getModels().put(id, new GhostlyPickaxeBakedModel(e.getModels().get(id)));
    //        }
    //    }
    //}

    //public void openBookGUI(ItemStack itemStackIn) {
    //    Minecraft.getInstance().setScreen(new GUIAnimalDictionary(itemStackIn));
    //}

    //public void openBookGUI(ItemStack itemStackIn, String page) {
    //    Minecraft.getInstance().setScreen(new GUIAnimalDictionary(itemStackIn, page));
    //}

    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }

    /*@OnlyIn(Dist.CLIENT)
    public Object getArmorModel(int armorId, LivingEntity entity) {
        switch (armorId) {
            *//*
            case 0:
                return ROADRUNNER_BOOTS_MODEL;
            case 1:
                return MOOSE_HEADGEAR_MODEL;
            case 2:
                return FRONTIER_CAP_MODEL.withAnimations(entity);
            case 3:
                return SOMBRERO_MODEL;
            case 4:
                return SPIKED_TURTLE_SHELL_MODEL;
            case 5:
                return FEDORA_MODEL;
            case 6:
                return ELYTRA_MODEL.withAnimations(entity);

             *//*
            default:
                return null;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void onEntityStatus(Entity entity, byte updateKind) {
        if (updateKind == 67) {
            if (entity instanceof EntityCockroach && entity.isAlive()) {
                SoundLaCucaracha sound;
                if (COCKROACH_SOUND_MAP.get(entity.getId()) == null) {
                    sound = new SoundLaCucaracha((EntityCockroach) entity);
                    COCKROACH_SOUND_MAP.put(entity.getId(), sound);
                } else {
                    sound = COCKROACH_SOUND_MAP.get(entity.getId());
                }
                if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound() && sound.isOnlyCockroach()) {
                    Minecraft.getInstance().getSoundManager().play(sound);
                }
            } else if (entity instanceof EntityVoidWorm && entity.isAlive()) {
                final float f2 = Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.MUSIC);
                if (f2 <= 0) {
                    WORMBOSS_SOUND_MAP.clear();
                } else {
                    SoundWormBoss sound;
                    if (WORMBOSS_SOUND_MAP.get(entity.getId()) == null) {
                        sound = new SoundWormBoss((EntityVoidWorm) entity);
                        WORMBOSS_SOUND_MAP.put(entity.getId(), sound);
                    } else {
                        sound = WORMBOSS_SOUND_MAP.get(entity.getId());
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.isNearest()) {
                        Minecraft.getInstance().getSoundManager().play(sound);
                    }
                }
            } else if (entity instanceof EntityGrizzlyBear && entity.isAlive()) {
                SoundBearMusicBox sound;
                if (BEAR_MUSIC_BOX_SOUND_MAP.get(entity.getId()) == null) {
                    sound = new SoundBearMusicBox((EntityGrizzlyBear) entity);
                    BEAR_MUSIC_BOX_SOUND_MAP.put(entity.getId(), sound);
                } else {
                    sound = BEAR_MUSIC_BOX_SOUND_MAP.get(entity.getId());
                }
                if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound() && sound.isOnlyMusicBox()) {
                    Minecraft.getInstance().getSoundManager().play(sound);
                }
            } else if (entity instanceof EntityBlueJay && entity.isAlive()) {
                singingBlueJayId = entity.getId();
            }
        }
        if (entity instanceof EntityBlueJay && entity.isAlive() && updateKind == 68) {
            singingBlueJayId = -1;
        }
    }

    public void updateBiomeVisuals(int x, int z) {
        Minecraft.getInstance().levelRenderer.setBlocksDirty(x - 32, 0, x - 32, z + 32, 255, z + 32);
    }*/

    //public static void setupParticles(RegisterParticleProvidersEvent registry) {
    public static void setupParticles() {
        AlexsMobs.LOGGER.debug("Registered particle factories");
        //registry.registerSpriteSet(AMParticleRegistry.GUSTER_SAND_SPIN.get(), ParticleGusterSandSpin.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.GUSTER_SAND_SHOT.get(), ParticleGusterSandShot.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.GUSTER_SAND_SPIN_RED.get(), ParticleGusterSandSpin.FactoryRed::new);
        //registry.registerSpriteSet(AMParticleRegistry.GUSTER_SAND_SHOT_RED.get(), ParticleGusterSandShot.FactoryRed::new);
        //registry.registerSpriteSet(AMParticleRegistry.GUSTER_SAND_SPIN_SOUL.get(), ParticleGusterSandSpin.FactorySoul::new);
        //registry.registerSpriteSet(AMParticleRegistry.GUSTER_SAND_SHOT_SOUL.get(), ParticleGusterSandShot.FactorySoul::new);
        //registry.registerSpriteSet(AMParticleRegistry.HEMOLYMPH.get(), ParticleHemolymph.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.PLATYPUS_SENSE.get(), ParticlePlatypus.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.WHALE_SPLASH.get(), ParticleWhaleSplash.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.DNA.get(), ParticleDna.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.SHOCKED.get(), ParticleSimpleHeart.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.WORM_PORTAL.get(), ParticleWormPortal.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.INVERT_DIG.get(), ParticleInvertDig.Factory::new);
        ParticleFactoryRegistry.getInstance().register(AMParticleRegistry.TEETH_GLINT.value(), ParticleTeethGlint.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.SMELLY.get(), ParticleSmelly.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.BUNFUNGUS_TRANSFORMATION.get(), ParticleBunfungusTransformation.Factory::new);
        //registry.registerSpriteSet(AMParticleRegistry.FUNGUS_BUBBLE.get(), ParticleFungusBubble.Factory::new);
        //registry.registerSpecial(AMParticleRegistry.BEAR_FREDDY.get(), new ParticleBearFreddy.Factory());
        //registry.registerSpriteSet(AMParticleRegistry.SUNBIRD_FEATHER.get(), ParticleSunbirdFeather.Factory::new);
        //registry.registerSpecial(AMParticleRegistry.STATIC_SPARK.get(), new ParticleStaticSpark.Factory());
        //registry.registerSpecial(AMParticleRegistry.SKULK_BOOM.get(), new ParticleSkulkBoom.Factory());
        //registry.registerSpriteSet(AMParticleRegistry.BIRD_SONG.get(), ParticleBirdSong.Factory::new);
    }


    /*public void setRenderViewEntity(Entity entity) {
        prevPOV = Minecraft.getInstance().options.getCameraType();
        Minecraft.getInstance().setCameraEntity(entity);
        Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_BACK);
    }

    public void resetRenderViewEntity() {
        Minecraft.getInstance().setCameraEntity(Minecraft.getInstance().player);
    }

    public int getPreviousPOV() {
        return prevPOV.ordinal();
    }

    public boolean isFarFromCamera(double x, double y, double z) {
        Minecraft lvt_1_1_ = Minecraft.getInstance();
        return lvt_1_1_.gameRenderer.getMainCamera().getPosition().distanceToSqr(x, y, z) >= 256.0D;
    }

    public void resetVoidPortalCreation(Player player) {

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRegisterEntityRenders(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }

    @Override
    public Object getISTERProperties() {
        return new AMItemRenderProperties();
    }

    @Override
    public Object getArmorRenderProperties() {
        return new CustomArmorRenderProperties();
    }

    public void spawnSpecialParticle(int type) {
        if (type == 0) {
            Minecraft.getInstance().level.addParticle(AMParticleRegistry.BEAR_FREDDY.get(), Minecraft.getInstance().player.getX(), Minecraft.getInstance().player.getY(), Minecraft.getInstance().player.getZ(), 0, 0, 0);
        }
    }

    public void processVisualFlag(Entity entity, int flag) {
        if (entity == Minecraft.getInstance().player && flag == 87) {
            ClientEvents.renderStaticScreenFor = 60;
        }
    }

    public void setPupfishChunkForItem(int chunkX, int chunkZ) {
        this.pupfishChunkX = chunkX;
        this.pupfishChunkZ = chunkZ;
    }

    public void setDisplayTransmuteResult(int slot, ItemStack stack){
        transmuteStacks[Mth.clamp(slot, 0, 2)] = stack;
    }

    public ItemStack getDisplayTransmuteResult(int slot){
        ItemStack stack = transmuteStacks[Mth.clamp(slot, 0, 2)];
        return stack == null ? ItemStack.EMPTY : stack;
    }

    public int getSingingBlueJayId() {
        return singingBlueJayId;
    }*/

}
