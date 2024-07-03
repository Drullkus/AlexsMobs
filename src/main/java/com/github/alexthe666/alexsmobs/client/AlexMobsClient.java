package com.github.alexthe666.alexsmobs.client;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.ClientProxy;
import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.entity.Entity;

public class AlexMobsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(Citadel.PACKET_ANIMATION, AlexMobsClient::handleAnimation);
		ClientProxy.init();
	}

	private static void handleAnimation(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
		int entityId = buf.readInt();
		int index = buf.readInt();

		if (Minecraft.getInstance().level != null) {
			Entity entityFound = Minecraft.getInstance().level.getEntity(entityId);
			if (entityFound instanceof IAnimatedEntity entity) {
				if (index == -1) {
					entity.setAnimation(IAnimatedEntity.NO_ANIMATION);
				} else {
					entity.setAnimation(entity.getAnimations()[index]);
				}
				entity.setAnimationTick(0);
			} /*else if (entityFound != null) {
				AlexsMobs.LOGGER.warn("Received Animation packet for non-animation entity " + entityFound.getType() + ", index: " + index + ", entityId: " + entityId);
			} else {
				AlexsMobs.LOGGER.warn("Received Animation packet for an unfound entity, index: " + index + ", entityId: " + entityId);
			}*/
		}
	}
}
