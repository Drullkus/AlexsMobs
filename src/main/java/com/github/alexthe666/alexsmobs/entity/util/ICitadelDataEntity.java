package com.github.alexthe666.alexsmobs.entity.util;

import net.minecraft.nbt.CompoundTag;

public interface ICitadelDataEntity {
	CompoundTag getCitadelEntityData();

	void setCitadelEntityData(CompoundTag nbt);
}
