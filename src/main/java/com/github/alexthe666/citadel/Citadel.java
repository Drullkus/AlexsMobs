package com.github.alexthe666.citadel;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.apache.commons.lang3.ArrayUtils;

public class Citadel {
	public static final ResourceLocation PACKET_PROPERTIES = new ResourceLocation(AlexsMobs.MODID, "properties");
	public static final ResourceLocation PACKET_MULTIPART_HURT = new ResourceLocation(AlexsMobs.MODID, "multipart_hurt");
	public static final ResourceLocation PACKET_ANIMATION = new ResourceLocation(AlexsMobs.MODID, "animation");

	public static void sendPropertiesMessageToAll(ServerLevel serverLevel, String propertyID, CompoundTag compound, int entityID) {
		FriendlyByteBuf buf = PacketByteBufs.create();
		buf.writeUtf(propertyID);
		buf.writeNbt(compound);
		buf.writeInt(entityID);
		Citadel.sendMSGToAll(serverLevel, PACKET_PROPERTIES, buf);
	}

	public static void sendPropertiesMessageToServer(String propertyID, CompoundTag compound, int entityID) {
		FriendlyByteBuf buf = PacketByteBufs.create();
		buf.writeUtf(propertyID);
		buf.writeNbt(compound);
		buf.writeInt(entityID);
		net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.send(PACKET_PROPERTIES, buf);
	}

	public static void sendHurtMultipartToAll(ServerLevel serverLevel, int part, int parent, float damage) {
		sendHurtMultipartToAll(serverLevel, part, parent, damage, "");
	}

	public static void sendHurtMultipartToAll(ServerLevel serverLevel, int part, int parent, float damage, String damageType) {
		FriendlyByteBuf buf = PacketByteBufs.create();
		buf.writeInt(part);
		buf.writeInt(parent);
		buf.writeFloat(damage);
		buf.writeUtf(damageType);
		Citadel.sendMSGToAll(serverLevel, PACKET_MULTIPART_HURT, buf);
	}

	public static <T extends Entity & IAnimatedEntity> void sendAnimationToAll(T entity, Animation animation, ServerLevel serverLevel) {
		FriendlyByteBuf buf = PacketByteBufs.create();
		buf.writeInt(entity.getId());
		buf.writeInt(ArrayUtils.indexOf(entity.getAnimations(), animation));
		Citadel.sendMSGToAll(serverLevel, PACKET_ANIMATION, buf);
	}

	private static void sendMSGToAll(ServerLevel serverLevel, ResourceLocation id, FriendlyByteBuf message) {
		for (ServerPlayer player : serverLevel.getPlayers(p -> true)) {
			ServerPlayNetworking.send(player, id, message);
		}
	}

	//     private void setup(final FMLCommonSetupEvent event) {
	//        PROXY.onPreInit();
	//        LecternBooks.init();
	//        int packetsRegistered = 0;
	//        NETWORK_WRAPPER.registerMessage(packetsRegistered++, PropertiesMessage.class, PropertiesMessage::write, PropertiesMessage::read, PropertiesMessage.Handler::handle);
	//        NETWORK_WRAPPER.registerMessage(packetsRegistered++, AnimationMessage.class, AnimationMessage::write, AnimationMessage::read, AnimationMessage.Handler::handle);
	//        NETWORK_WRAPPER.registerMessage(packetsRegistered++, SyncClientTickRateMessage.class, SyncClientTickRateMessage::write, SyncClientTickRateMessage::read, SyncClientTickRateMessage.Handler::handle);
	//        NETWORK_WRAPPER.registerMessage(packetsRegistered++, DanceJukeboxMessage.class, DanceJukeboxMessage::write, DanceJukeboxMessage::read, DanceJukeboxMessage.Handler::handle);
	//        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageSyncPath.class, MessageSyncPath::write, MessageSyncPath::read, MessageSyncPath.Handler::handle);
	//        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageSyncPathReached.class, MessageSyncPathReached::write, MessageSyncPathReached::read, MessageSyncPathReached.Handler::handle);
	//        BufferedReader urlContents = WebHelper.getURLContents("https://raw.githubusercontent.com/Alex-the-666/Citadel/master/src/main/resources/assets/citadel/patreon.txt", "assets/citadel/patreon.txt");
	//        if (urlContents != null) {
	//            try {
	//                String line;
	//                while ((line = urlContents.readLine()) != null) {
	//                    PATREONS.add(line);
	//                }
	//            } catch (IOException e) {
	//                LOGGER.warn("Failed to load patreon contributor perks");
	//            }
	//        } else LOGGER.warn("Failed to load patreon contributor perks");
	//    }
}
