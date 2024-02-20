package com.github.alexthe666.alexsmobs.data;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class AMLootTables extends FabricBlockLootTableProvider {
	private static final float[] JUNGLE_LEAVES_SAPLING_CHANGES = new float[]{0.025f, 0.027777778f, 0.03125f, 0.041666668f, 0.1f};

	protected AMLootTables(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		this.add(Blocks.JUNGLE_LEAVES, this::makeJungleLeavesDrops);
	}

	private LootTable.Builder makeJungleLeavesDrops(Block block) {
		return this.createLeavesDrops(block, Blocks.JUNGLE_SAPLING, JUNGLE_LEAVES_SAPLING_CHANGES)
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(AMItemRegistry.BANANA.value())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 1.0f))));
	}
}
