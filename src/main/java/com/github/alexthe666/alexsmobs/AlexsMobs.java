package com.github.alexthe666.alexsmobs;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.message.MessageHurtMultipart;
import com.github.alexthe666.alexsmobs.misc.AMCreativeTabRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import com.github.alexthe666.citadel.Citadel;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Date;

public class AlexsMobs implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "alexsmobs";
    //public static final SimpleChannel NETWORK_WRAPPER;
    //private static final String PROTOCOL_VERSION = Integer.toString(1);
    //public static final CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    //private static int packetsRegistered;
    private static boolean isAprilFools = false;
    private static boolean isHalloween = false;

    /*static {
        NetworkRegistry.ChannelBuilder channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation("alexsmobs", "main_channel"));
        String version = PROTOCOL_VERSION;
        version.getClass();
        channel = channel.clientAcceptedVersions(version::equals);
        version = PROTOCOL_VERSION;
        version.getClass();
        NETWORK_WRAPPER = channel.serverAcceptedVersions(version::equals).networkProtocolVersion(() -> {
            return PROTOCOL_VERSION;
        }).simpleChannel();
    }*/

    @Override
    public void onInitialize() {
        //IEventBus modBusEvent = FMLJavaModLoadingContext.get().getModEventBus();
        //modBusEvent.addListener(this::setup);
        //modBusEvent.addListener(this::setupClient);
        //modBusEvent.addListener(this::onModConfigEvent);
        //modBusEvent.addListener(this::setupEntityModelLayers);
        //final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        AMBlockRegistry.init(); //AMBlockRegistry.DEF_REG.register(modBusEvent);
        AMEntityRegistry.init(); //AMEntityRegistry.DEF_REG.register(modBusEvent);
        AMItemRegistry.init(); //AMItemRegistry.DEF_REG.register(modBusEvent);
        AMTileEntityRegistry.init(); //AMTileEntityRegistry.DEF_REG.register(modBusEvent);
        //AMPointOfInterestRegistry.DEF_REG.register(modBusEvent);
        //AMFeatureRegistry.DEF_REG.register(modBusEvent);
        AMSoundRegistry.init(); //AMSoundRegistry.DEF_REG.register(modBusEvent);
        AMParticleRegistry.init(); //AMParticleRegistry.DEF_REG.register(modBusEvent);
        //AMPaintingRegistry.DEF_REG.register(modBusEvent);
        //AMEffectRegistry.EFFECT_DEF_REG.register(modBusEvent);
        //AMEffectRegistry.POTION_DEF_REG.register(modBusEvent);
        //AMEnchantmentRegistry.DEF_REG.register(modBusEvent);
        //AMMenuRegistry.DEF_REG.register(modBusEvent);
        //AMRecipeRegistry.DEF_REG.register(modBusEvent);
        //AMLootRegistry.DEF_REG.register(modBusEvent);
        //AMBannerRegistry.DEF_REG.register(modBusEvent);
        AMCreativeTabRegistry.init(); //AMCreativeTabRegistry.DEF_REG.register(modBusEvent);
        //final DeferredRegister<Codec<? extends BiomeModifier>> biomeModifiers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, AlexsMobs.MODID);
        //biomeModifiers.register(modBusEvent);
        //biomeModifiers.register("am_mob_spawns", AMMobSpawnBiomeModifier::makeCodec);
        //biomeModifiers.register("am_leafcutter_ant_spawns", AMLeafcutterAntBiomeModifier::makeCodec);
        //final DeferredRegister<Codec<? extends StructureModifier>> structureModifiers = DeferredRegister.create(ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, AlexsMobs.MODID);
        //structureModifiers.register(modBusEvent);
        //structureModifiers.register("am_structure_spawns", AMMobSpawnStructureModifier::makeCodec);
        //modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC, "alexsmobs.toml");
        //PROXY.init();
        //MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.EVENT_BUS.register(new ServerEvents());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        isAprilFools = calendar.get(Calendar.MONTH) + 1 == 4 && calendar.get(Calendar.DATE) == 1;
        isHalloween = calendar.get(Calendar.MONTH) + 1 == 10 && calendar.get(Calendar.DATE) >= 29 && calendar.get(Calendar.DATE) <= 31;

        ServerPlayNetworking.registerGlobalReceiver(Citadel.PACKET_PROPERTIES, MessageHurtMultipart::handle);
    }

    public static boolean isAprilFools() {
        return isAprilFools;// || AMConfig.superSecretSettings;
    }

    public static boolean isHalloween() {
        return isHalloween;// || AMConfig.superSecretSettings;
    }

    /*private void setupEntityModelLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        AMModelLayers.register(event);
    }

    @SubscribeEvent
    public void onModConfigEvent(final ModConfigEvent event) {
        final ModConfig config = event.getConfig();
        // Rebake the configs when they change
        if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
            AMConfig.bake(config);
        }
        BiomeConfig.init();
    }

    public static <MSG> void sendMSGToServer(MSG message) {
        NETWORK_WRAPPER.sendToServer(message);
    }

    public static <MSG> void sendMSGToAll(MSG message) {
        for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
            sendNonLocal(message, player);
        }
    }

    public static <MSG> void sendNonLocal(MSG msg, ServerPlayer player) {
        NETWORK_WRAPPER.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    private void setup(final FMLCommonSetupEvent event) {
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageMosquitoMountPlayer.class, MessageMosquitoMountPlayer::write, MessageMosquitoMountPlayer::read, MessageMosquitoMountPlayer.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageMosquitoDismount.class, MessageMosquitoDismount::write, MessageMosquitoDismount::read, MessageMosquitoDismount.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageHurtMultipart.class, MessageHurtMultipart::write, MessageHurtMultipart::read, MessageHurtMultipart.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageCrowMountPlayer.class, MessageCrowMountPlayer::write, MessageCrowMountPlayer::read, MessageCrowMountPlayer.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageCrowDismount.class, MessageCrowDismount::write, MessageCrowDismount::read, MessageCrowDismount.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageMungusBiomeChange.class, MessageMungusBiomeChange::write, MessageMungusBiomeChange::read, MessageMungusBiomeChange.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageKangarooInventorySync.class, MessageKangarooInventorySync::write, MessageKangarooInventorySync::read, MessageKangarooInventorySync.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageKangarooEat.class, MessageKangarooEat::write, MessageKangarooEat::read, MessageKangarooEat.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageUpdateCapsid.class, MessageUpdateCapsid::write, MessageUpdateCapsid::read, MessageUpdateCapsid.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageSwingArm.class, MessageSwingArm::write, MessageSwingArm::read, MessageSwingArm.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageUpdateEagleControls.class, MessageUpdateEagleControls::write, MessageUpdateEagleControls::read, MessageUpdateEagleControls.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageSyncEntityPos.class, MessageSyncEntityPos::write, MessageSyncEntityPos::read, MessageSyncEntityPos.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageTarantulaHawkSting.class, MessageTarantulaHawkSting::write, MessageTarantulaHawkSting::read, MessageTarantulaHawkSting.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageStartDancing.class, MessageStartDancing::write, MessageStartDancing::read, MessageStartDancing.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageInteractMultipart.class, MessageInteractMultipart::write, MessageInteractMultipart::read, MessageInteractMultipart.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageSendVisualFlagFromServer.class, MessageSendVisualFlagFromServer::write, MessageSendVisualFlagFromServer::read, MessageSendVisualFlagFromServer.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageSetPupfishChunkOnClient.class, MessageSetPupfishChunkOnClient::write, MessageSetPupfishChunkOnClient::read, MessageSetPupfishChunkOnClient.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageUpdateTransmutablesToDisplay.class, MessageUpdateTransmutablesToDisplay::write, MessageUpdateTransmutablesToDisplay::read, MessageUpdateTransmutablesToDisplay.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageTransmuteFromMenu.class, MessageTransmuteFromMenu::write, MessageTransmuteFromMenu::read, MessageTransmuteFromMenu.Handler::handle);
        event.enqueueWork(AMItemRegistry::init);
        event.enqueueWork(AMItemRegistry::initDispenser);
        AMAdvancementTriggerRegistry.init();
        AMEffectRegistry.init();
        AMRecipeRegistry.init();
    }

    private void setupClient(FMLClientSetupEvent event) {
        PROXY.clientInit();
    }*/
}
