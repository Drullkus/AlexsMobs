package com.github.alexthe666.alexsmobs.client.particle;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class AMParticleRegistry {
    //public static final DeferredRegister<ParticleType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AlexsMobs.MODID);

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T extends ParticleType<T> & ParticleOptions> Holder<T> register(String name, Supplier<T> builder) {
        return (Holder<T>) (Holder) Registry.registerForHolder(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(AlexsMobs.MODID, name), builder.get());
    }
    
    public static final Holder<SimpleParticleType> GUSTER_SAND_SPIN = register("guster_sand_spin", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> GUSTER_SAND_SHOT = register("guster_sand_shot", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> GUSTER_SAND_SPIN_RED = register("guster_sand_spin_red", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> GUSTER_SAND_SHOT_RED = register("guster_sand_shot_red", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> GUSTER_SAND_SPIN_SOUL = register("guster_sand_spin_soul", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> GUSTER_SAND_SHOT_SOUL = register("guster_sand_shot_soul", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> HEMOLYMPH = register("hemolymph", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> PLATYPUS_SENSE = register("platypus_sense", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> WHALE_SPLASH = register("whale_splash", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> DNA = register("dna", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> SHOCKED = register("shocked", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> WORM_PORTAL = register("worm_portal", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> INVERT_DIG = register("invert_dig", ()-> FabricParticleTypes.simple(true));
    public static final Holder<SimpleParticleType> TEETH_GLINT = register("teeth_glint", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> SMELLY = register("smelly", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> BUNFUNGUS_TRANSFORMATION = register("bunfungus_transformation", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> FUNGUS_BUBBLE = register("fungus_bubble", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> BEAR_FREDDY = register("bear_freddy", ()-> FabricParticleTypes.simple(true));
    public static final Holder<SimpleParticleType> SUNBIRD_FEATHER = register("sunbird_feather", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> STATIC_SPARK = register("static_spark", ()-> FabricParticleTypes.simple(false));
    public static final Holder<SimpleParticleType> SKULK_BOOM = register("skulk_boom", ()-> FabricParticleTypes.simple(false));

    public static final Holder<SimpleParticleType> BIRD_SONG = register("bird_song", ()-> FabricParticleTypes.simple(false));

    public static void init() {
    }
}
