package com.github.alexthe666.alexsmobs.client;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;

public class AlexMobsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(Citadel.PACKET_ANIMATION, AlexMobsClient::handleAnimation);
	}

	private static void handleAnimation(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
		int index = buf.readInt();
		int entityId = buf.readInt();

		if (Minecraft.getInstance().level != null) {
			IAnimatedEntity entity = (IAnimatedEntity) Minecraft.getInstance().level.getEntity(entityId);
			if (entity != null) {
				if (index == -1) {
					entity.setAnimation(IAnimatedEntity.NO_ANIMATION);
				} else {
					entity.setAnimation(entity.getAnimations()[index]);
				}
				entity.setAnimationTick(0);
			}
		}
	}
}
