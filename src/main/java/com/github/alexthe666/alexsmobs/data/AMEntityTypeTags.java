package com.github.alexthe666.alexsmobs.data;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class AMEntityTypeTags extends FabricTagProvider.EntityTypeTagProvider {
	public AMEntityTypeTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		this.getOrCreateTagBuilder(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(
				AMEntityRegistry.ANACONDA.value(),
				AMEntityRegistry.ANACONDA_PART.value(),
				AMEntityRegistry.BANANA_SLUG.value(),
				AMEntityRegistry.CAIMAN.value(),
				AMEntityRegistry.CROCODILE.value()
		);
	}
}
