package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;
import java.util.function.Supplier;

public class AMPointOfInterestRegistry {
    //public static final DeferredRegister<PoiType> DEF_REG = DeferredRegister.create(ForgeRegistries.POI_TYPES, AlexsMobs.MODID);

    private static Holder.Reference<PoiType> register(String name, Supplier<PoiType> builder) {
        return Registry.registerForHolder(BuiltInRegistries.POINT_OF_INTEREST_TYPE, new ResourceLocation(AlexsMobs.MODID, name), builder.get());
    }

    public static final Holder.Reference<PoiType> END_PORTAL_FRAME = register("end_portal_frame", () ->new PoiType(getBlockStates(Blocks.END_PORTAL_FRAME), 32, 6));
    public static final Holder.Reference<PoiType> LEAFCUTTER_ANT_HILL = register("leafcutter_anthill", () ->new PoiType(getBlockStates(AMBlockRegistry.LEAFCUTTER_ANTHILL.value()), 32, 6));
    public static final Holder.Reference<PoiType> BEACON = register("am_beacon", () -> new PoiType(getBlockStates(Blocks.BEACON), 32, 6));
    //public static final Holder<PoiType> HUMMINGBIRD_FEEDER = register("hummingbird_feeder", () -> new PoiType(getBlockStates(AMBlockRegistry.HUMMINGBIRD_FEEDER.value()), 32, 6));

    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }

    public static void init() {
    }
}
