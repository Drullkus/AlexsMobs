package com.github.alexthe666.alexsmobs.client;

import com.github.alexthe666.alexsmobs.ClientProxy;
import com.github.alexthe666.alexsmobs.entity.EntityAnaconda;
import com.github.alexthe666.alexsmobs.entity.IHurtableMultipart;
import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AlexMobsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(Citadel.PACKET_ANIMATION, AlexMobsClient::handleAnimation);
		ClientPlayNetworking.registerGlobalReceiver(Citadel.PACKET_MULTIPART_HURT, AlexMobsClient::handleMultipartHurt);
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

	private static void handleMultipartHurt(Minecraft minecraft, ClientPacketListener clientPacketListener, FriendlyByteBuf buf, PacketSender packetSender) {
		int bufPart = buf.readInt();
		int bufParent = buf.readInt();
		float bufDamage = buf.readFloat();
		String bufDamageType = buf.readUtf();

		Player player = minecraft.player;

		if (player != null) {
			Level level = player.level();
			Entity part = level.getEntity(bufPart);
			Entity parent = level.getEntity(bufParent);
			Registry<DamageType> registry = level.registryAccess().registry(Registries.DAMAGE_TYPE).get();
			DamageType dmg = registry.get(new ResourceLocation(bufDamageType));
			if (dmg != null) {
				Holder<DamageType> holder = registry.getHolder(registry.getId(dmg)).orElseGet(null);
				if (holder != null) {
					DamageSource source = new DamageSource(registry.getHolder(registry.getId(dmg)).get());
					if (part instanceof IHurtableMultipart && parent instanceof LivingEntity) {
						((IHurtableMultipart) part).onAttackedFromServer((LivingEntity) parent, bufDamage, source);
					}
					if (part == null && parent != null && parent instanceof EntityAnaconda) {
						parent.hurt(source, bufDamage);
					}
				}
			}
		}
	}
}
