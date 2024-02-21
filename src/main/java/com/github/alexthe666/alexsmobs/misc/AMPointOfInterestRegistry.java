package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class AMPointOfInterestRegistry {
	//public static final DeferredRegister<PoiType> DEF_REG = DeferredRegister.create(ForgeRegistries.POI_TYPES, AlexsMobs.MODID);

	//private static Holder.Reference<PoiType> register(String name, Supplier<PoiType> builder) {
	//    return Registry.registerForHolder(BuiltInRegistries.POINT_OF_INTEREST_TYPE, new ResourceLocation(AlexsMobs.MODID, name), builder.get());
	//}

	//ic static final Holder.Reference<PoiType> END_PORTAL_FRAME = register("end_portal_frame", () ->new PoiType(getBlockStates(Blocks.END_PORTAL_FRAME), 32, 6));
	//ic static final Holder.Reference<PoiType> LEAFCUTTER_ANT_HILL = register("leafcutter_anthill", () ->new PoiType(getBlockStates(AMBlockRegistry.LEAFCUTTER_ANTHILL.value()), 32, 6));
	//ic static final Holder.Reference<PoiType> BEACON = register("am_beacon", () -> new PoiType(getBlockStates(Blocks.BEACON), 32, 6));
	//public static final Holder<PoiType> HUMMINGBIRD_FEEDER = register("hummingbird_feeder", () -> new PoiType(getBlockStates(AMBlockRegistry.HUMMINGBIRD_FEEDER.value()), 32, 6));

	public static final ResourceLocation LEAFCUTTER_ANTHILL_KEY = new ResourceLocation(AlexsMobs.MODID, "leafcutter_anthill");

	//public static final PoiType END_PORTAL_FRAME = PointOfInterestHelper.register(new ResourceLocation(AlexsMobs.MODID, "end_portal_frame"), 32, 6, Blocks.END_PORTAL_FRAME));
	public static final PoiType LEAFCUTTER_ANT_HILL = PointOfInterestHelper.register(LEAFCUTTER_ANTHILL_KEY, 32, 6, AMBlockRegistry.LEAFCUTTER_ANTHILL.value());
	//public static final PoiType BEACON = PointOfInterestHelper.register(new ResourceLocation(AlexsMobs.MODID, "am_beacon"), 32, 6, Blocks.BEACON);

	private static Set<BlockState> getBlockStates(Block block) {
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}

	public static void init() {
	}
}
