package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.IHurtableMultipart;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class MessageHurtMultipart {
    /*public int part;
    public int parent;
    public float damage;
    public String damageType;

    public MessageHurtMultipart(int part, int parent, float damage) {
        this.part = part;
        this.parent = parent;
        this.damage = damage;
        this.damageType = "";
    }

    public MessageHurtMultipart(int part, int parent, float damage, String damageType) {
        this.part = part;
        this.parent = parent;
        this.damage = damage;
        this.damageType = damageType;
    }

    public MessageHurtMultipart() {
    }

    public static MessageHurtMultipart read(FriendlyByteBuf buf) {
        return new MessageHurtMultipart(buf.readInt(), buf.readInt(), buf.readFloat(), buf.readUtf());
    }

    public static void write(MessageHurtMultipart message, FriendlyByteBuf buf) {
        buf.writeInt(message.part);
        buf.writeInt(message.parent);
        buf.writeFloat(message.damage);
        buf.writeUtf(message.damageType);
    }*/

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        int partBuf = buf.readInt();
        int parentBuf = buf.readInt();
        float damageBuf = buf.readFloat();
        String damageTypeBuf = buf.readUtf();
        Level level = player.level();

		Entity part = level.getEntity(partBuf);
		Entity parent = level.getEntity(parentBuf);
		Registry<DamageType> registry = level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);

        DamageType dmg = registry.get(new ResourceLocation(damageTypeBuf));

        if (dmg == null) return;

		Holder<DamageType> holder = registry.getHolder(registry.getId(dmg)).orElseGet(null);

        if (holder == null) return;

		DamageSource source = new DamageSource(holder);

        if (!(part instanceof IHurtableMultipart hurtableMultipart && parent instanceof LivingEntity)) return;

        hurtableMultipart.onAttackedFromServer((LivingEntity) parent, damageBuf, source);

		// Not needed yet
		//if (part == null && parent != null && parent.isMultipartEntity()) {
		//    parent.hurt(source, message.damage);
		//}
	}

    /*public static class Handler {
        public Handler() {
        }

        public static void handle(MessageHurtMultipart message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            Player player = context.get().getSender();
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                player = AlexsMobs.PROXY.getClientSidePlayer();
            }

            if (player != null) {
                if (player.level() != null) {
                    Entity part = player.level().getEntity(message.part);
                    Entity parent = player.level().getEntity(message.parent);
                    Registry<DamageType> registry = player.level().registryAccess().registry(Registries.DAMAGE_TYPE).get();
                    DamageType dmg = registry.get(new ResourceLocation(message.damageType));
                    if (dmg != null) {
                        Holder<DamageType> holder = registry.getHolder(registry.getId(dmg)).orElseGet(null);
                        if (holder != null) {
                            DamageSource source = new DamageSource(registry.getHolder(registry.getId(dmg)).get());
                            if (part instanceof IHurtableMultipart && parent instanceof LivingEntity) {
                                ((IHurtableMultipart) part).onAttackedFromServer((LivingEntity) parent, message.damage, source);
                            }
                            // Not needed yet
                            //if (part == null && parent != null && parent.isMultipartEntity()) {
                            //    parent.hurt(source, message.damage);
                            //}
                        }
                    }

                }
            }
        }
    }*/
}