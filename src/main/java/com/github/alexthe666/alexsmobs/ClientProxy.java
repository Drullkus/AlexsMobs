package com.github.alexthe666.alexsmobs;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.client.event.ClientEvents;
import com.github.alexthe666.alexsmobs.client.gui.GUIAnimalDictionary;
import com.github.alexthe666.alexsmobs.client.model.*;
import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.client.particle.ParticleGusterSandShot;
import com.github.alexthe666.alexsmobs.client.particle.ParticleGusterSandSpin;
import com.github.alexthe666.alexsmobs.client.particle.ParticleHemolymph;
import com.github.alexthe666.alexsmobs.client.render.*;
import com.github.alexthe666.alexsmobs.client.sound.SoundLaCucaracha;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityCockroach;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.item.ItemBloodSprayer;
import com.github.alexthe666.alexsmobs.item.ItemHemolymphBlaster;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.GuardianSound;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.GrassColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = AlexsMobs.MODID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    private static final ModelRoadrunnerBoots ROADRUNNER_BOOTS_MODEL = new ModelRoadrunnerBoots(0.7F);
    private static final ModelMooseHeadgear MOOSE_HEADGEAR_MODEL = new ModelMooseHeadgear(0.3F);
    private static final ModelFrontierCap FRONTIER_CAP_MODEL = new ModelFrontierCap(0.3F);
    private static final ModelSombrero SOMBRERO_MODEL = new ModelSombrero(0.3F);
    private static final ModelSpikedTurtleShell SPIKED_TURTLE_SHELL_MODEL = new ModelSpikedTurtleShell(1.0F);
    public static final Map<Integer, SoundLaCucaracha> COCKROACH_SOUND_MAP = new HashMap<>();

    public void init(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::onItemColors);
    }

    public void clientInit() {
        ItemRenderer itemRendererIn = Minecraft.getInstance().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.GRIZZLY_BEAR, manager -> new RenderGrizzlyBear(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.ROADRUNNER, manager -> new RenderRoadrunner(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.BONE_SERPENT, manager -> new RenderBoneSerpent(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.BONE_SERPENT_PART, manager -> new RenderBoneSerpentPart(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.GAZELLE, manager -> new RenderGazelle(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.CROCODILE, manager -> new RenderCrocodile(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.FLY, manager -> new RenderFly(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.HUMMINGBIRD, manager -> new RenderHummingbird(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.ORCA, manager -> new RenderOrca(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.SUNBIRD, manager -> new RenderSunbird(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.GORILLA, manager -> new RenderGorilla(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.CRIMSON_MOSQUITO, manager -> new RenderCrimsonMosquito(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.MOSQUITO_SPIT, manager -> new RenderMosquitoSpit(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.RATTLESNAKE, manager -> new RenderRattlesnake(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.ENDERGRADE, manager -> new RenderEndergrade(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.HAMMERHEAD_SHARK, manager -> new RenderHammerheadShark(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.SHARK_TOOTH_ARROW, manager -> new RenderSharkToothArrow(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.LOBSTER, manager -> new RenderLobster(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.KOMODO_DRAGON, manager -> new RenderKomodoDragon(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.CAPUCHIN_MONKEY, manager -> new RenderCapuchinMonkey(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.TOSSED_ITEM, manager -> new RenderTossedItem(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.CENTIPEDE_HEAD, manager -> new RenderCentipedeHead(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.CENTIPEDE_BODY, manager -> new RenderCentipedeBody(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.CENTIPEDE_TAIL, manager -> new RenderCentipedeTail(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.WARPED_TOAD, manager -> new RenderWarpedToad(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.MOOSE, manager -> new RenderMoose(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.MIMICUBE, manager -> new RenderMimicube(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.RACCOON, manager -> new RenderRaccoon(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.BLOBFISH, manager -> new RenderBlobfish(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.SEAL, manager -> new RenderSeal(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.COCKROACH, manager -> new RenderCockroach(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.COCKROACH_EGG, manager -> new SpriteRenderer(manager, itemRendererIn));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.SHOEBILL, manager -> new RenderShoebill(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.ELEPHANT, manager -> new RenderElephant(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.SOUL_VULTURE, manager -> new RenderSoulVulture(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.SNOW_LEOPARD, manager -> new RenderSnowLeopard(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.SPECTRE, manager -> new RenderSpectre(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.CROW, manager -> new RenderCrow(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.ALLIGATOR_SNAPPING_TURTLE, manager -> new RenderAlligatorSnappingTurtle(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.MUNGUS, manager -> new RenderMungus(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.MANTIS_SHRIMP, manager -> new RenderMantisShrimp(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.GUSTER, manager -> new RenderGuster(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.SAND_SHOT, manager -> new RenderSandShot(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.GUST, manager -> new RenderGust(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.WARPED_MOSCO, manager -> new RenderWarpedMosco(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.HEMOLYMPH, manager -> new RenderHemolymph(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.STRADDLER, manager -> new RenderStraddler(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.STRADPOLE, manager -> new RenderStradpole(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.STRADDLEBOARD, manager -> new RenderStraddleboard(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.EMU, manager -> new RenderEmu(manager));
        RenderingRegistry.registerEntityRenderingHandler(AMEntityRegistry.EMU_EGG, manager -> new SpriteRenderer(manager, itemRendererIn));
        MinecraftForge.EVENT_BUS.register(new ClientEvents());

        ItemModelsProperties.registerProperty(AMItemRegistry.BLOOD_SPRAYER, new ResourceLocation("empty"), (stack, p_239428_1_, p_239428_2_) -> {
            return !ItemBloodSprayer.isUsable(stack) || p_239428_2_ instanceof PlayerEntity && ((PlayerEntity) p_239428_2_).getCooldownTracker().hasCooldown(AMItemRegistry.BLOOD_SPRAYER) ? 1.0F : 0.0F;
        });
        ItemModelsProperties.registerProperty(AMItemRegistry.HEMOLYMPH_BLASTER, new ResourceLocation("empty"), (stack, p_239428_1_, p_239428_2_) -> {
            return !ItemHemolymphBlaster.isUsable(stack) || p_239428_2_ instanceof PlayerEntity && ((PlayerEntity) p_239428_2_).getCooldownTracker().hasCooldown(AMItemRegistry.HEMOLYMPH_BLASTER) ? 1.0F : 0.0F;
        });
        RenderTypeLookup.setRenderLayer(AMBlockRegistry.BANANA_PEEL, RenderType.getCutout());

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemColors(ColorHandlerEvent.Item event) {
        AlexsMobs.LOGGER.info("loaded in item colorizer");
        event.getItemColors().register((stack, colorIn) -> colorIn < 1 ? -1 : ((IDyeableArmorItem)stack.getItem()).getColor(stack), AMItemRegistry.STRADDLEBOARD);
    }

    public void openBookGUI(ItemStack itemStackIn) {
        Minecraft.getInstance().displayGuiScreen(new GUIAnimalDictionary(itemStackIn));
    }

    public Item.Properties setupISTER(Item.Properties group) {
        return group.setISTER(ClientProxy::getTEISR);
    }

    @OnlyIn(Dist.CLIENT)
    public static Callable<ItemStackTileEntityRenderer> getTEISR() {
        return AMItemstackRenderer::new;
    }

    public PlayerEntity getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }

    @OnlyIn(Dist.CLIENT)
    public Object getArmorModel(int armorId, LivingEntity entity) {
        switch (armorId) {
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
            default:
                return null;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void onEntityStatus(Entity entity, byte updateKind) {
        if(entity instanceof EntityCockroach && entity.isAlive() && updateKind == 67){
            SoundLaCucaracha sound;
            if(COCKROACH_SOUND_MAP.get(entity.getEntityId()) == null){
                sound = new SoundLaCucaracha((EntityCockroach)entity);
                COCKROACH_SOUND_MAP.put(entity.getEntityId(), sound);
            }else{
                sound = COCKROACH_SOUND_MAP.get(entity.getEntityId());
            }
            if(!Minecraft.getInstance().getSoundHandler().isPlaying(sound) && sound.shouldPlaySound() && sound.isOnlyCockroach()){
                Minecraft.getInstance().getSoundHandler().play(sound);
            }
        }
    }

    public void updateBiomeVisuals(int x, int z) {
        Minecraft.getInstance().worldRenderer.markBlockRangeForRenderUpdate(x- 32, 0, x - 32, z + 32, 255, z + 32);
    }

    public void setupParticles() {
        AlexsMobs.LOGGER.debug("Registered particle factories");
        Minecraft.getInstance().particles.registerFactory(AMParticleRegistry.GUSTER_SAND_SPIN, ParticleGusterSandSpin.Factory::new);
        Minecraft.getInstance().particles.registerFactory(AMParticleRegistry.GUSTER_SAND_SHOT, ParticleGusterSandShot.Factory::new);
        Minecraft.getInstance().particles.registerFactory(AMParticleRegistry.HEMOLYMPH, ParticleHemolymph.Factory::new);

    }

}
