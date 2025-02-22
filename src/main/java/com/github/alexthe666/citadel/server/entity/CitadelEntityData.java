package com.github.alexthe666.citadel.server.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class CitadelEntityData {
	public static CompoundTag getOrCreateCitadelTag(LivingEntity entity) {
		CompoundTag tag = getCitadelTag(entity);
		return tag == null ? new CompoundTag() : tag;
	}

	public static CompoundTag getCitadelTag(LivingEntity entity) {
		return entity instanceof ICitadelDataEntity citadelDataEntity ? citadelDataEntity.getCitadelEntityData() : new CompoundTag();
	}

	public static void setCitadelTag(LivingEntity entity, CompoundTag tag) {
		if (entity instanceof ICitadelDataEntity citadelDataEntity)
			citadelDataEntity.setCitadelEntityData(tag);
	}
}
