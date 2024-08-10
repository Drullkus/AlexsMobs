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
		this.add(Blocks.OAK_LEAVES, block -> this.makeJungleLeavesDrops(block, Blocks.OAK_SAPLING));
		this.add(Blocks.BIRCH_LEAVES, block -> this.makeJungleLeavesDrops(block, Blocks.BIRCH_SAPLING));
		this.add(Blocks.SPRUCE_LEAVES, block -> this.makeJungleLeavesDrops(block, Blocks.SPRUCE_SAPLING));
		this.add(Blocks.JUNGLE_LEAVES, block -> this.makeJungleLeavesDrops(block, Blocks.JUNGLE_SAPLING));
		this.add(Blocks.DARK_OAK_LEAVES, block -> this.makeJungleLeavesDrops(block, Blocks.DARK_OAK_SAPLING));
		this.add(Blocks.ACACIA_LEAVES, block -> this.makeJungleLeavesDrops(block, Blocks.ACACIA_SAPLING));
		this.add(Blocks.AZALEA_LEAVES, block -> this.makeJungleLeavesDrops(block, Blocks.AZALEA));
		this.add(Blocks.CHERRY_LEAVES, block -> this.makeJungleLeavesDrops(block, Blocks.CHERRY_SAPLING));
		this.add(Blocks.MANGROVE_LEAVES, block -> this.makeJungleLeavesDrops(block, Blocks.MANGROVE_PROPAGULE));
	}

	private LootTable.Builder makeJungleLeavesDrops(Block block, Block sapling) {
		return this.createLeavesDrops(block, sapling, JUNGLE_LEAVES_SAPLING_CHANGES)
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(AMItemRegistry.BANANA.value())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 1.0f))));
	}
}
