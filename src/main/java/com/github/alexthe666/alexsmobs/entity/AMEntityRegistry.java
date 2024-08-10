package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.google.common.base.Predicates;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.object.builder.FabricEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class AMEntityRegistry {

    //public static final DeferredRegister<EntityType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AlexsMobs.MODID);
    //public static final Holder<EntityType<EntityGrizzlyBear>> GRIZZLY_BEAR = register("grizzly_bear", () -> registerEntity(FabricEntityTypeBuilder.create(EntityGrizzlyBear::new, MobCategory.CREATURE).dimensions(1.6F, 1.8F), "grizzly_bear"));
    //public static final Holder<EntityType<EntityRoadrunner>> ROADRUNNER = register("roadrunner", () -> registerEntity(FabricEntityTypeBuilder.create(EntityRoadrunner::new, MobCategory.CREATURE).dimensions(0.45F, 0.75F), "roadrunner"));
    //public static final Holder<EntityType<EntityBoneSerpent>> BONE_SERPENT = register("bone_serpent", () -> registerEntity(FabricEntityTypeBuilder.create(EntityBoneSerpent::new, MobCategory.MONSTER).dimensions(1.2F, 1.15F).fireImmune(), "bone_serpent"));
    //public static final Holder<EntityType<EntityBoneSerpentPart>> BONE_SERPENT_PART = register("bone_serpent_part", () -> registerEntity(FabricEntityTypeBuilder.create(EntityBoneSerpentPart::new, MobCategory.MONSTER).dimensions(1F, 1F).fireImmune(), "bone_serpent_part"));
    //public static final Holder<EntityType<EntityGazelle>> GAZELLE = register("gazelle", () -> registerEntity(FabricEntityTypeBuilder.create(EntityGazelle::new, MobCategory.CREATURE).dimensions(0.85F, 1.25F), "gazelle"));
    public static final Holder<EntityType<EntityCrocodile>> CROCODILE = register("crocodile", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.WATER_CREATURE, EntityCrocodile::new).dimensions(EntityDimensions.fixed(2.15F, 0.75F))));
    //public static final Holder<EntityType<EntityFly>> FLY = register("fly", () -> registerEntity(FabricEntityTypeBuilder.create(EntityFly::new, MobCategory.AMBIENT).dimensions(0.35F, 0.35F), "fly"));
    //public static final Holder<EntityType<EntityHummingbird>> HUMMINGBIRD = register("hummingbird", () -> registerEntity(FabricEntityTypeBuilder.create(EntityHummingbird::new, MobCategory.CREATURE).dimensions(0.45F, 0.45F), "hummingbird"));
    //public static final Holder<EntityType<EntityOrca>> ORCA = register("orca", () -> registerEntity(FabricEntityTypeBuilder.create(EntityOrca::new, MobCategory.WATER_CREATURE).dimensions(3.75F, 1.75F), "orca"));
    //public static final Holder<EntityType<EntitySunbird>> SUNBIRD = register("sunbird", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySunbird::new, MobCategory.CREATURE).dimensions(2.75F, 1.5F).fireImmune().setTrackingRange(10).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "sunbird"));
    public static final Holder<EntityType<EntityGorilla>> GORILLA = register("gorilla", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityGorilla::new).dimensions(EntityDimensions.fixed(1.15F, 1.35F))));
    //public static final Holder<EntityType<EntityCrimsonMosquito>> CRIMSON_MOSQUITO = register("crimson_mosquito", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCrimsonMosquito::new, MobCategory.MONSTER).dimensions(1.25F, 1.15F).fireImmune(), "crimson_mosquito"));
    //public static final Holder<EntityType<EntityMosquitoSpit>> MOSQUITO_SPIT = register("mosquito_spit", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMosquitoSpit::new, MobCategory.MISC).dimensions(0.5F, 0.5F).setCustomClientFactory(EntityMosquitoSpit::new).fireImmune(), "mosquito_spit"));
    //public static final Holder<EntityType<EntityRattlesnake>> RATTLESNAKE = register("rattlesnake", () -> registerEntity(FabricEntityTypeBuilder.create(EntityRattlesnake::new, MobCategory.CREATURE).dimensions(0.95F, 0.35F), "rattlesnake"));
    //public static final Holder<EntityType<EntityEndergrade>> ENDERGRADE = register("endergrade", () -> registerEntity(FabricEntityTypeBuilder.create(EntityEndergrade::new, MobCategory.CREATURE).dimensions(0.95F, 0.85F), "endergrade"));
    public static final Holder<EntityType<EntityHammerheadShark>> HAMMERHEAD_SHARK = register("hammerhead_shark", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.WATER_CREATURE, EntityHammerheadShark::new).dimensions(EntityDimensions.fixed(2.4F, 1.25F))));
    public static final Holder<EntityType<EntitySharkToothArrow>> SHARK_TOOTH_ARROW = register("shark_tooth_arrow", () -> AMEntityRegistry.registerEntity(FabricEntityTypeBuilder.<EntitySharkToothArrow>create(MobCategory.MISC, EntitySharkToothArrow::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F))));
    //public static final Holder<EntityType<EntityLobster>> LOBSTER = register("lobster", () -> registerEntity(FabricEntityTypeBuilder.create(EntityLobster::new, MobCategory.WATER_AMBIENT).dimensions(0.7F, 0.4F), "lobster"));
    public static final Holder<EntityType<EntityKomodoDragon>> KOMODO_DRAGON = register("komodo_dragon", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityKomodoDragon::new).dimensions(EntityDimensions.fixed(1.9F, 0.9F))));
    public static final Holder<EntityType<EntityCapuchinMonkey>> CAPUCHIN_MONKEY = register("capuchin_monkey", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityCapuchinMonkey::new).dimensions(EntityDimensions.fixed(0.65F, 0.75F))));
    public static final Holder<EntityType<EntityTossedItem>> TOSSED_ITEM = register("tossed_item", () -> registerEntity(FabricEntityTypeBuilder.<EntityTossedItem>create(MobCategory.MISC, EntityTossedItem::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).fireImmune()));
    //public static final Holder<EntityType<EntityCentipedeHead>> CENTIPEDE_HEAD = register("centipede_head", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCentipedeHead::new, MobCategory.MONSTER).dimensions(0.9F, 0.9F), "centipede_head"));
    //public static final Holder<EntityType<EntityCentipedeBody>> CENTIPEDE_BODY = register("centipede_body", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCentipedeBody::new, MobCategory.MISC).dimensions(0.9F, 0.9F).fireImmune().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "centipede_body"));
    //public static final Holder<EntityType<EntityCentipedeTail>> CENTIPEDE_TAIL = register("centipede_tail", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCentipedeTail::new, MobCategory.MISC).dimensions(0.9F, 0.9F).fireImmune().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "centipede_tail"));
    //public static final Holder<EntityType<EntityWarpedToad>> WARPED_TOAD = register("warped_toad", () -> registerEntity(FabricEntityTypeBuilder.create(EntityWarpedToad::new, MobCategory.CREATURE).dimensions(0.9F, 1.4F).fireImmune().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "warped_toad"));
    //public static final Holder<EntityType<EntityMoose>> MOOSE = register("moose", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMoose::new, MobCategory.CREATURE).dimensions(1.7F, 2.4F), "moose"));
    //public static final Holder<EntityType<EntityMimicube>> MIMICUBE = register("mimicube", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMimicube::new, MobCategory.MONSTER).dimensions(0.9F, 0.9F), "mimicube"));
    //public static final Holder<EntityType<EntityRaccoon>> RACCOON = register("raccoon", () -> registerEntity(FabricEntityTypeBuilder.create(EntityRaccoon::new, MobCategory.CREATURE).dimensions(0.8F, 0.9F), "raccoon"));
    //public static final Holder<EntityType<EntityBlobfish>> BLOBFISH = register("blobfish", () -> registerEntity(FabricEntityTypeBuilder.create(EntityBlobfish::new, MobCategory.WATER_AMBIENT).dimensions(0.7F, 0.45F), "blobfish"));
    //public static final Holder<EntityType<EntitySeal>> SEAL = register("seal", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySeal::new, MobCategory.CREATURE).dimensions(1.45F, 0.9F), "seal"));
    //public static final Holder<EntityType<EntityCockroach>> COCKROACH = register("cockroach", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCockroach::new, MobCategory.AMBIENT).dimensions(0.7F, 0.3F), "cockroach"));
    //public static final Holder<EntityType<EntityCockroachEgg>> COCKROACH_EGG = register("cockroach_egg", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCockroachEgg::new, MobCategory.MISC).dimensions(0.5F, 0.5F).setCustomClientFactory(EntityCockroachEgg::new).fireImmune(), "cockroach_egg"));
    //public static final Holder<EntityType<EntityShoebill>> SHOEBILL = register("shoebill", () -> registerEntity(FabricEntityTypeBuilder.create(EntityShoebill::new, MobCategory.CREATURE).dimensions(0.8F, 1.5F).setUpdateInterval(1), "shoebill"));
    //public static final Holder<EntityType<EntityElephant>> ELEPHANT = register("elephant", () -> registerEntity(FabricEntityTypeBuilder.create(EntityElephant::new, MobCategory.CREATURE).dimensions(3.1F, 3.5F).setUpdateInterval(1), "elephant"));
    //public static final Holder<EntityType<EntitySoulVulture>> SOUL_VULTURE = register("soul_vulture", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySoulVulture::new, MobCategory.MONSTER).dimensions(0.9F, 1.3F).setUpdateInterval(1).fireImmune(), "soul_vulture"));
    //public static final Holder<EntityType<EntitySnowLeopard>> SNOW_LEOPARD = register("snow_leopard", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySnowLeopard::new, MobCategory.CREATURE).dimensions(1.2F, 1.3F).immuneTo(Blocks.POWDER_SNOW), "snow_leopard"));
    //public static final Holder<EntityType<EntitySpectre>> SPECTRE = register("spectre", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySpectre::new, MobCategory.CREATURE).dimensions(3.15F, 0.8F).fireImmune().setTrackingRange(10).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "spectre"));
    //public static final Holder<EntityType<EntityCrow>> CROW = register("crow", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCrow::new, MobCategory.CREATURE).dimensions(0.45F, 0.45F), "crow"));
    //public static final Holder<EntityType<EntityAlligatorSnappingTurtle>> ALLIGATOR_SNAPPING_TURTLE = register("alligator_snapping_turtle", () -> registerEntity(FabricEntityTypeBuilder.create(EntityAlligatorSnappingTurtle::new, MobCategory.CREATURE).dimensions(1.25F, 0.65F), "alligator_snapping_turtle"));
    //public static final Holder<EntityType<EntityMungus>> MUNGUS = register("mungus", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMungus::new, MobCategory.CREATURE).dimensions(0.75F, 1.45F), "mungus"));
    //public static final Holder<EntityType<EntityMantisShrimp>> MANTIS_SHRIMP = register("mantis_shrimp", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMantisShrimp::new, MobCategory.WATER_CREATURE).dimensions(1.25F, 1.2F), "mantis_shrimp"));
    //public static final Holder<EntityType<EntityGuster>> GUSTER = register("guster", () -> registerEntity(FabricEntityTypeBuilder.create(EntityGuster::new, MobCategory.MONSTER).dimensions(1.42F, 2.35F).fireImmune(), "guster"));
    //public static final Holder<EntityType<EntitySandShot>> SAND_SHOT = register("sand_shot", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySandShot::new, MobCategory.MISC).dimensions(0.95F, 0.65F).setCustomClientFactory(EntitySandShot::new).fireImmune(), "sand_shot"));
    //public static final Holder<EntityType<EntityGust>> GUST = register("gust", () -> registerEntity(FabricEntityTypeBuilder.create(EntityGust::new, MobCategory.MISC).dimensions(0.8F, 0.8F).setCustomClientFactory(EntityGust::new).fireImmune(), "gust"));
    //public static final Holder<EntityType<EntityWarpedMosco>> WARPED_MOSCO = register("warped_mosco", () -> registerEntity(FabricEntityTypeBuilder.create(EntityWarpedMosco::new, MobCategory.MONSTER).dimensions(1.99F, 3.25F).fireImmune(), "warped_mosco"));
    //public static final Holder<EntityType<EntityHemolymph>> HEMOLYMPH = register("hemolymph", () -> registerEntity(FabricEntityTypeBuilder.create(EntityHemolymph::new, MobCategory.MISC).dimensions(0.5F, 0.5F).setCustomClientFactory(EntityHemolymph::new).fireImmune(), "hemolymph"));
    //public static final Holder<EntityType<EntityStraddler>> STRADDLER = register("straddler", () -> registerEntity(FabricEntityTypeBuilder.create(EntityStraddler::new, MobCategory.MONSTER).dimensions(1.65F, 3F).fireImmune(), "straddler"));
    //public static final Holder<EntityType<EntityStradpole>> STRADPOLE = register("stradpole", () -> registerEntity(FabricEntityTypeBuilder.create(EntityStradpole::new, MobCategory.WATER_AMBIENT).dimensions(0.5F, 0.5F).fireImmune(), "stradpole"));
    //public static final Holder<EntityType<EntityStraddleboard>> STRADDLEBOARD = register("straddleboard", () -> registerEntity(FabricEntityTypeBuilder.create(EntityStraddleboard::new, MobCategory.MISC).dimensions(1.5F, 0.35F).setCustomClientFactory(EntityStraddleboard::new).fireImmune().setUpdateInterval(1).clientTrackingRange(10).setShouldReceiveVelocityUpdates(true), "straddleboard"));
    //public static final Holder<EntityType<EntityEmu>> EMU = register("emu", () -> registerEntity(FabricEntityTypeBuilder.create(EntityEmu::new, MobCategory.CREATURE).dimensions(1.1F, 1.8F), "emu"));
    //public static final Holder<EntityType<EntityEmuEgg>> EMU_EGG = register("emu_egg", () -> registerEntity(FabricEntityTypeBuilder.create(EntityEmuEgg::new, MobCategory.MISC).dimensions(0.5F, 0.5F).setCustomClientFactory(EntityEmuEgg::new).fireImmune(), "emu_egg"));
    //public static final Holder<EntityType<EntityPlatypus>> PLATYPUS = register("platypus", () -> registerEntity(FabricEntityTypeBuilder.create(EntityPlatypus::new, MobCategory.CREATURE).dimensions(0.8F, 0.5F), "platypus"));
    //public static final Holder<EntityType<EntityDropBear>> DROPBEAR = register("dropbear", () -> registerEntity(FabricEntityTypeBuilder.create(EntityDropBear::new, MobCategory.MONSTER).dimensions(1.65F, 1.5F).fireImmune(), "dropbear"));
    //public static final Holder<EntityType<EntityTasmanianDevil>> TASMANIAN_DEVIL = register("tasmanian_devil", () -> registerEntity(FabricEntityTypeBuilder.create(EntityTasmanianDevil::new, MobCategory.CREATURE).dimensions(0.7F, 0.8F), "tasmanian_devil"));
    //public static final Holder<EntityType<EntityKangaroo>> KANGAROO = register("kangaroo", () -> registerEntity(FabricEntityTypeBuilder.create(EntityKangaroo::new, MobCategory.CREATURE).dimensions(1.65F, 1.5F), "kangaroo"));
    //public static final Holder<EntityType<EntityCachalotWhale>> CACHALOT_WHALE = register("cachalot_whale", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCachalotWhale::new, MobCategory.WATER_CREATURE).dimensions(9F, 4.0F), "cachalot_whale"));
    //public static final Holder<EntityType<EntityCachalotEcho>> CACHALOT_ECHO = register("cachalot_echo", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCachalotEcho::new, MobCategory.MISC).dimensions(2F, 2F).setCustomClientFactory(EntityCachalotEcho::new).fireImmune(), "cachalot_echo"));
    public static final Holder<EntityType<EntityLeafcutterAnt>> LEAFCUTTER_ANT = register("leafcutter_ant", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityLeafcutterAnt::new).dimensions(EntityDimensions.fixed(0.8F, 0.5F))));
    //public static final Holder<EntityType<EntityEnderiophage>> ENDERIOPHAGE = register("enderiophage", () -> registerEntity(FabricEntityTypeBuilder.create(EntityEnderiophage::new, MobCategory.CREATURE).dimensions(0.85F, 1.95F).setUpdateInterval(1), "enderiophage"));
    //public static final Holder<EntityType<EntityEnderiophageRocket>> ENDERIOPHAGE_ROCKET = register("enderiophage_rocket", () -> registerEntity(FabricEntityTypeBuilder.create(EntityEnderiophageRocket::new, MobCategory.MISC).dimensions(0.5F, 0.5F).setCustomClientFactory(EntityEnderiophageRocket::new).fireImmune(), "enderiophage_rocket"));
    //public static final Holder<EntityType<EntityBaldEagle>> BALD_EAGLE = register("bald_eagle", () -> registerEntity(FabricEntityTypeBuilder.create(EntityBaldEagle::new, MobCategory.CREATURE).dimensions(0.5F, 0.95F).setUpdateInterval(1).setTrackingRange(14), "bald_eagle"));
    public static final Holder<EntityType<EntityTiger>> TIGER = register("tiger", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityTiger::new).dimensions(EntityDimensions.fixed(1.45F, 1.2F))));
    //public static final Holder<EntityType<EntityTarantulaHawk>> TARANTULA_HAWK = register("tarantula_hawk", () -> registerEntity(FabricEntityTypeBuilder.create(EntityTarantulaHawk::new, MobCategory.CREATURE).dimensions(1.2F, 0.9F), "tarantula_hawk"));
    //public static final Holder<EntityType<EntityVoidWorm>> VOID_WORM = register("void_worm", () -> registerEntity(FabricEntityTypeBuilder.create(EntityVoidWorm::new, MobCategory.MONSTER).dimensions(3.4F, 3F).fireImmune().setTrackingRange(20).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "void_worm"));
    //public static final Holder<EntityType<EntityVoidWormPart>> VOID_WORM_PART = register("void_worm_part", () -> registerEntity(FabricEntityTypeBuilder.create(EntityVoidWormPart::new, MobCategory.MONSTER).dimensions(1.2F, 1.35F).fireImmune().setTrackingRange(20).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "void_worm_part"));
    //public static final Holder<EntityType<EntityVoidWormShot>> VOID_WORM_SHOT = register("void_worm_shot", () -> registerEntity(FabricEntityTypeBuilder.create(EntityVoidWormShot::new, MobCategory.MISC).dimensions(0.5F, 0.5F).setCustomClientFactory(EntityVoidWormShot::new).fireImmune(), "void_worm_shot"));
    //public static final Holder<EntityType<EntityVoidPortal>> VOID_PORTAL = register("void_portal", () -> registerEntity(FabricEntityTypeBuilder.create(EntityVoidPortal::new, MobCategory.MISC).dimensions(0.5F, 0.5F).setCustomClientFactory(EntityVoidPortal::new).fireImmune(), "void_portal"));
    public static final Holder<EntityType<EntityFrilledShark>> FRILLED_SHARK = register("frilled_shark", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.WATER_CREATURE, EntityFrilledShark::new).dimensions(EntityDimensions.fixed(1.3F, 0.4F))));
    //public static final Holder<EntityType<EntityMimicOctopus>> MIMIC_OCTOPUS = register("mimic_octopus", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMimicOctopus::new, MobCategory.WATER_CREATURE).dimensions(0.9F, 0.6F), "mimic_octopus"));
    public static final Holder<EntityType<EntitySeagull>> SEAGULL = register("seagull", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntitySeagull::new).dimensions(EntityDimensions.fixed(0.85F, 0.65F))));
    //public static final Holder<EntityType<EntityFroststalker>> FROSTSTALKER = register("froststalker", () -> registerEntity(FabricEntityTypeBuilder.create(EntityFroststalker::new, MobCategory.CREATURE).dimensions(0.95F, 1.15F).immuneTo(Blocks.POWDER_SNOW), "froststalker"));
    //public static final Holder<EntityType<EntityIceShard>> ICE_SHARD = register("ice_shard", () -> registerEntity(FabricEntityTypeBuilder.create(EntityIceShard::new, MobCategory.MISC).dimensions(0.45F, 0.45F).setCustomClientFactory(EntityIceShard::new).fireImmune(), "ice_shard"));
    //public static final Holder<EntityType<EntityTusklin>> TUSKLIN = register("tusklin", () -> registerEntity(FabricEntityTypeBuilder.create(EntityTusklin::new, MobCategory.CREATURE).dimensions(2.2F, 1.9F).immuneTo(Blocks.POWDER_SNOW), "tusklin"));
    //public static final Holder<EntityType<EntityLaviathan>> LAVIATHAN = register("laviathan", () -> registerEntity(FabricEntityTypeBuilder.create(EntityLaviathan::new, MobCategory.CREATURE).dimensions(3.3F, 2.4F).fireImmune().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "laviathan"));
    //public static final Holder<EntityType<EntityCosmaw>> COSMAW = register("cosmaw", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCosmaw::new, MobCategory.CREATURE).dimensions(1.95F, 1.8F), "cosmaw"));
    public static final Holder<EntityType<EntityToucan>> TOUCAN = register("toucan", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityToucan::new).dimensions(EntityDimensions.fixed(0.45F, 0.45F))));
    //public static final Holder<EntityType<EntityManedWolf>> MANED_WOLF = register("maned_wolf", () -> registerEntity(FabricEntityTypeBuilder.create(EntityManedWolf::new, MobCategory.CREATURE).dimensions(0.9F, 1.26F), "maned_wolf"));
    public static final Holder<EntityType<EntityAnaconda>> ANACONDA = register("anaconda", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityAnaconda::new).dimensions(EntityDimensions.fixed(0.8F, 0.8F))));
    public static final Holder<EntityType<EntityAnacondaPart>> ANACONDA_PART = register("anaconda_part", () -> registerEntity(FabricEntityTypeBuilder.<EntityAnacondaPart>create(MobCategory.MISC, EntityAnacondaPart::new).dimensions(EntityDimensions.fixed(0.8F, 0.8F)).forceTrackedVelocityUpdates(true).trackedUpdateRate(1)));
    //public static final Holder<EntityType<EntityVineLasso>> VINE_LASSO = register("vine_lasso", () -> registerEntity(FabricEntityTypeBuilder.create(EntityVineLasso::new, MobCategory.MISC).dimensions(0.85F, 0.2F).setCustomClientFactory(EntityVineLasso::new).fireImmune(), "vine_lasso"));
    public static final Holder<EntityType<EntityAnteater>> ANTEATER = register("anteater", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityAnteater::new).dimensions(EntityDimensions.fixed(1.3F, 1.1F))));
    public static final Holder<EntityType<EntityRockyRoller>> ROCKY_ROLLER = register("rocky_roller", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.MONSTER, EntityRockyRoller::new).dimensions(EntityDimensions.fixed(1.2F, 1.45F))));
    //public static final Holder<EntityType<EntityFlutter>> FLUTTER = register("flutter", () -> registerEntity(FabricEntityTypeBuilder.create(EntityFlutter::new, MobCategory.AMBIENT).dimensions(0.5F, 0.7F), "flutter"));
    //public static final Holder<EntityType<EntityPollenBall>> POLLEN_BALL = register("pollen_ball", () -> registerEntity(FabricEntityTypeBuilder.create(EntityPollenBall::new, MobCategory.MISC).dimensions(0.35F, 0.35F).setCustomClientFactory(EntityPollenBall::new).fireImmune(), "pollen_ball"));
    //public static final Holder<EntityType<EntityGeladaMonkey>> GELADA_MONKEY = register("gelada_monkey", () -> registerEntity(FabricEntityTypeBuilder.create(EntityGeladaMonkey::new, MobCategory.CREATURE).dimensions(1.2F, 1.2F), "gelada_monkey"));
    //public static final Holder<EntityType<EntityJerboa>> JERBOA = register("jerboa", () -> registerEntity(FabricEntityTypeBuilder.create(EntityJerboa::new, MobCategory.AMBIENT).dimensions(0.5F, 0.5F), "jerboa"));
    //public static final Holder<EntityType<EntityTerrapin>> TERRAPIN = register("terrapin", () -> registerEntity(FabricEntityTypeBuilder.create(EntityTerrapin::new, MobCategory.WATER_AMBIENT).dimensions(0.75F, 0.45F), "terrapin"));
    //public static final Holder<EntityType<EntityCombJelly>> COMB_JELLY = register("comb_jelly", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCombJelly::new, MobCategory.WATER_AMBIENT).dimensions(0.65F, 0.8F), "comb_jelly"));
    //public static final Holder<EntityType<EntityCosmicCod>> COSMIC_COD = register("cosmic_cod", () -> registerEntity(FabricEntityTypeBuilder.create(EntityCosmicCod::new, MobCategory.AMBIENT).dimensions(0.85F, 0.4F), "cosmic_cod"));
    //public static final Holder<EntityType<EntityBunfungus>> BUNFUNGUS = register("bunfungus", () -> registerEntity(FabricEntityTypeBuilder.create(EntityBunfungus::new, MobCategory.CREATURE).dimensions(1.85F, 2.1F), "bunfungus"));
    //public static final Holder<EntityType<EntityBison>> BISON = register("bison", () -> registerEntity(FabricEntityTypeBuilder.create(EntityBison::new, MobCategory.CREATURE).dimensions(2.4F, 2.1F), "bison"));
    //public static final Holder<EntityType<EntityGiantSquid>> GIANT_SQUID = register("giant_squid", () -> registerEntity(FabricEntityTypeBuilder.create(EntityGiantSquid::new, MobCategory.WATER_CREATURE).dimensions(0.9F, 1.2F), "giant_squid"));
    //public static final Holder<EntityType<EntitySquidGrapple>> SQUID_GRAPPLE = register("squid_grapple", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySquidGrapple::new, MobCategory.MISC).dimensions(0.5F, 0.5F).setCustomClientFactory(EntitySquidGrapple::new).fireImmune(), "squid_grapple"));
    //public static final Holder<EntityType<EntitySeaBear>> SEA_BEAR = register("sea_bear", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySeaBear::new, MobCategory.WATER_CREATURE).dimensions(2.4F, 1.99F), "sea_bear"));
    //public static final Holder<EntityType<EntityDevilsHolePupfish>> DEVILS_HOLE_PUPFISH = register("devils_hole_pupfish", () -> registerEntity(FabricEntityTypeBuilder.create(EntityDevilsHolePupfish::new, MobCategory.WATER_AMBIENT).dimensions(0.6F, 0.4F), "devils_hole_pupfish"));
    //public static final Holder<EntityType<EntityCatfish>> CATFISH = register("catfish", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, EntityCatfish::new).dimensions(EntityDimensions.fixed(0.9F, 0.6F))));
    //public static final Holder<EntityType<EntityFlyingFish>> FLYING_FISH = register("flying_fish", () -> registerEntity(FabricEntityTypeBuilder.create(EntityFlyingFish::new, MobCategory.WATER_AMBIENT).dimensions(0.6F, 0.4F), "flying_fish"));
    //public static final Holder<EntityType<EntitySkelewag>> SKELEWAG = register("skelewag", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySkelewag::new, MobCategory.MONSTER).dimensions(2F, 1.2F).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "skelewag"));
    //public static final Holder<EntityType<EntityRainFrog>> RAIN_FROG = register("rain_frog", () -> registerEntity(FabricEntityTypeBuilder.create(EntityRainFrog::new, MobCategory.AMBIENT).dimensions(0.55F, 0.5F), "rain_frog"));
    //public static final Holder<EntityType<EntityPotoo>> POTOO = register("potoo", () -> registerEntity(FabricEntityTypeBuilder.create(EntityPotoo::new, MobCategory.CREATURE).dimensions(0.6F, 0.8F), "potoo"));
    //public static final Holder<EntityType<EntityMudskipper>> MUDSKIPPER = register("mudskipper", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMudskipper::new, MobCategory.CREATURE).dimensions(0.7F, 0.44F), "mudskipper"));
    //public static final Holder<EntityType<EntityMudBall>> MUD_BALL = register("mud_ball", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMudBall::new, MobCategory.MISC).dimensions(0.35F, 0.35F).setCustomClientFactory(EntityMudBall::new).fireImmune(), "mud_ball"));
    //public static final Holder<EntityType<EntityRhinoceros>> RHINOCEROS = register("rhinoceros", () -> registerEntity(FabricEntityTypeBuilder.create(EntityRhinoceros::new, MobCategory.CREATURE).dimensions(2.3F, 2.4F), "rhinoceros"));
    //public static final Holder<EntityType<EntitySugarGlider>> SUGAR_GLIDER = register("sugar_glider", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySugarGlider::new, MobCategory.CREATURE).dimensions(0.8F, 0.45F), "sugar_glider"));
    //public static final Holder<EntityType<EntityFarseer>> FARSEER = register("farseer", () -> registerEntity(FabricEntityTypeBuilder.create(EntityFarseer::new, MobCategory.MONSTER).dimensions(0.99F, 1.5F).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).fireImmune(), "farseer"));
    //public static final Holder<EntityType<EntitySkreecher>> SKREECHER = register("skreecher", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySkreecher::new, MobCategory.MONSTER).dimensions(0.99F, 0.95F).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "skreecher"));
    //public static final Holder<EntityType<EntityUnderminer>> UNDERMINER = register("underminer", () -> registerEntity(FabricEntityTypeBuilder.create(EntityUnderminer::new, MobCategory.AMBIENT).dimensions(0.8F, 1.8F), "underminer"));
    //public static final Holder<EntityType<EntityMurmur>> MURMUR = register("murmur", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMurmur::new, MobCategory.MONSTER).dimensions(0.7F, 1.45F), "murmur"));
    //public static final Holder<EntityType<EntityMurmurHead>> MURMUR_HEAD = register("murmur_head", () -> registerEntity(FabricEntityTypeBuilder.create(EntityMurmurHead::new, MobCategory.MONSTER).dimensions(0.55F, 0.55F), "murmur_head"));
    //public static final Holder<EntityType<EntityTendonSegment>> TENDON_SEGMENT = register("tendon_segment", () -> registerEntity(FabricEntityTypeBuilder.create(EntityTendonSegment::new, MobCategory.MISC).dimensions(0.1F, 0.1F).setCustomClientFactory(EntityTendonSegment::new).fireImmune(), "tendon_segment"));
    //public static final Holder<EntityType<EntitySkunk>> SKUNK = register("skunk", () -> registerEntity(FabricEntityTypeBuilder.create(EntitySkunk::new, MobCategory.CREATURE).dimensions(0.85F, 0.65F), "skunk"));
    //public static final Holder<EntityType<EntityFart>> FART = register("fart", () -> registerEntity(FabricEntityTypeBuilder.create(EntityFart::new, MobCategory.MISC).dimensions(0.7F, 0.3F).setCustomClientFactory(EntityFart::new).fireImmune(), "fart"));
    public static final Holder<EntityType<EntityBananaSlug>> BANANA_SLUG = register("banana_slug", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityBananaSlug::new).dimensions(EntityDimensions.fixed(0.8F, 0.4F))));
    //public static final Holder<EntityType<EntityBlueJay>> BLUE_JAY = register("blue_jay", () -> registerEntity(FabricEntityTypeBuilder.create(EntityBlueJay::new, MobCategory.CREATURE).dimensions(0.5F, 0.6F), "blue_jay"));
    public static final Holder<EntityType<EntityCaiman>> CAIMAN = register("caiman", () -> registerEntity(FabricEntityTypeBuilder.create(MobCategory.CREATURE, EntityCaiman::new).dimensions(EntityDimensions.fixed(1.3F, 0.6F))));
    //public static final Holder<EntityType<EntityTriops>> TRIOPS = register("triops", () -> registerEntity(FabricEntityTypeBuilder.create(EntityTriops::new, MobCategory.WATER_AMBIENT).dimensions(0.7F, 0.25F), "triops"));


    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T extends Entity> Holder<EntityType<T>> register(String name, Supplier<EntityType<T>> builder) {
        return (Holder<EntityType<T>>) (Holder) Registry.registerForHolder(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(AlexsMobs.MODID, name), builder.get());
    }

    private static <T extends Entity> EntityType<T> registerEntity(FabricEntityTypeBuilder<T> builder) {
        return builder.build();
    }

    // public static void initializeAttributes(EntityAttributeCreationEvent event) {
    public static void initializeAttributes() {
        //FIXME
        // SpawnPlacements.Type spawnsOnLeaves = SpawnPlacements.Type.create("am_leaves", AMEntityRegistry::createLeavesSpawnPlacement);
        SpawnPlacements.Type spawnsOnLeaves = SpawnPlacements.Type.ON_GROUND;
//        SpawnPlacements.register(GRIZZLY_BEAR.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
//        SpawnPlacements.register(ROADRUNNER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRoadrunner::canRoadrunnerSpawn);
//        SpawnPlacements.register(BONE_SERPENT.value(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBoneSerpent::canBoneSerpentSpawn);
//        SpawnPlacements.register(GAZELLE.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CROCODILE.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCrocodile::canCrocodileSpawn);
//        SpawnPlacements.register(FLY.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFly::canFlySpawn);
//        SpawnPlacements.register(HUMMINGBIRD.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, EntityHummingbird::canHummingbirdSpawn);
//        SpawnPlacements.register(ORCA.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityOrca::canOrcaSpawn);
//        SpawnPlacements.register(SUNBIRD.value(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySunbird::canSunbirdSpawn);
        SpawnPlacements.register(GORILLA.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, EntityGorilla::canGorillaSpawn);
//        SpawnPlacements.register(CRIMSON_MOSQUITO.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCrimsonMosquito::canMosquitoSpawn);
//        SpawnPlacements.register(RATTLESNAKE.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRattlesnake::canRattlesnakeSpawn);
//        SpawnPlacements.register(ENDERGRADE.value(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityEndergrade::canEndergradeSpawn);
        SpawnPlacements.register(HAMMERHEAD_SHARK.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityHammerheadShark::canHammerheadSharkSpawn);
//        SpawnPlacements.register(LOBSTER.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityLobster::canLobsterSpawn);
        SpawnPlacements.register(KOMODO_DRAGON.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityKomodoDragon::canKomodoDragonSpawn);
        SpawnPlacements.register(CAPUCHIN_MONKEY.value(), spawnsOnLeaves, Heightmap.Types.MOTION_BLOCKING, EntityCapuchinMonkey::canCapuchinSpawn);
//        SpawnPlacements.register(CENTIPEDE_HEAD.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCentipedeHead::canCentipedeSpawn);
//        SpawnPlacements.register(WARPED_TOAD.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, EntityWarpedToad::canWarpedToadSpawn);
//        SpawnPlacements.register(MOOSE.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMoose::canMooseSpawn);
//        SpawnPlacements.register(MIMICUBE.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(RACCOON.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
//        SpawnPlacements.register(BLOBFISH.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBlobfish::canBlobfishSpawn);
//        SpawnPlacements.register(SEAL.value(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySeal::canSealSpawn);
//        SpawnPlacements.register(COCKROACH.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCockroach::canCockroachSpawn);
//        SpawnPlacements.register(SHOEBILL.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
//        SpawnPlacements.register(ELEPHANT.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
//        SpawnPlacements.register(SOUL_VULTURE.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySoulVulture::canVultureSpawn);
//        SpawnPlacements.register(SNOW_LEOPARD.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySnowLeopard::canSnowLeopardSpawn);
//        SpawnPlacements.register(ALLIGATOR_SNAPPING_TURTLE.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAlligatorSnappingTurtle::canTurtleSpawn);
//        SpawnPlacements.register(MUNGUS.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMungus::canMungusSpawn);
//        SpawnPlacements.register(MANTIS_SHRIMP.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMantisShrimp::canMantisShrimpSpawn);
//        SpawnPlacements.register(GUSTER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityGuster::canGusterSpawn);
//        SpawnPlacements.register(WARPED_MOSCO.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
//        SpawnPlacements.register(STRADDLER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityStraddler::canStraddlerSpawn);
//        SpawnPlacements.register(STRADPOLE.value(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityStradpole::canStradpoleSpawn);
//        SpawnPlacements.register(EMU.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityEmu::canEmuSpawn);
//        SpawnPlacements.register(PLATYPUS.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityPlatypus::canPlatypusSpawn);
//        SpawnPlacements.register(DROPBEAR.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
//        SpawnPlacements.register(TASMANIAN_DEVIL.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
//        SpawnPlacements.register(KANGAROO.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityKangaroo::canKangarooSpawn);
//        SpawnPlacements.register(CACHALOT_WHALE.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCachalotWhale::canCachalotWhaleSpawn);
        SpawnPlacements.register(LEAFCUTTER_ANT.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
//        SpawnPlacements.register(ENDERIOPHAGE.value(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityEnderiophage::canEnderiophageSpawn);
//        SpawnPlacements.register(BALD_EAGLE.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, EntityBaldEagle::canEagleSpawn);
        SpawnPlacements.register(TIGER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTiger::canTigerSpawn);
//        SpawnPlacements.register(TARANTULA_HAWK.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTarantulaHawk::canTarantulaHawkSpawn);
//        SpawnPlacements.register(VOID_WORM.value(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityVoidWorm::canVoidWormSpawn);
        SpawnPlacements.register(FRILLED_SHARK.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFrilledShark::canFrilledSharkSpawn);
//        SpawnPlacements.register(MIMIC_OCTOPUS.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMimicOctopus::canMimicOctopusSpawn);
        SpawnPlacements.register(SEAGULL.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySeagull::canSeagullSpawn);
//        SpawnPlacements.register(FROSTSTALKER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFroststalker::canFroststalkerSpawn);
//        SpawnPlacements.register(TUSKLIN.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTusklin::canTusklinSpawn);
//        SpawnPlacements.register(LAVIATHAN.value(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityLaviathan::canLaviathanSpawn);
//        SpawnPlacements.register(COSMAW.value(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCosmaw::canCosmawSpawn);
        SpawnPlacements.register(TOUCAN.value(), spawnsOnLeaves, Heightmap.Types.MOTION_BLOCKING, EntityToucan::canToucanSpawn);
//        SpawnPlacements.register(MANED_WOLF.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityManedWolf::checkAnimalSpawnRules);
        SpawnPlacements.register(ANACONDA.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAnaconda::canAnacondaSpawn);
        SpawnPlacements.register(ANTEATER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAnteater::canAnteaterSpawn);
        SpawnPlacements.register(ROCKY_ROLLER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRockyRoller::checkRockyRollerSpawnRules);
//        SpawnPlacements.register(FLUTTER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFlutter::canFlutterSpawn);
//        SpawnPlacements.register(GELADA_MONKEY.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityGeladaMonkey::checkAnimalSpawnRules);
//        SpawnPlacements.register(JERBOA.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityJerboa::canJerboaSpawn);
//        SpawnPlacements.register(TERRAPIN.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTerrapin::canTerrapinSpawn);
//        SpawnPlacements.register(COMB_JELLY.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCombJelly::canCombJellySpawn);
//        SpawnPlacements.register(BUNFUNGUS.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBunfungus::canBunfungusSpawn);
//        SpawnPlacements.register(BISON.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBison::checkAnimalSpawnRules);
//        SpawnPlacements.register(GIANT_SQUID.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityGiantSquid::canGiantSquidSpawn);
//        SpawnPlacements.register(DEVILS_HOLE_PUPFISH.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityDevilsHolePupfish::canPupfishSpawn);
//        SpawnPlacements.register(CATFISH.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCatfish::canCatfishSpawn);
//        SpawnPlacements.register(FLYING_FISH.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
//        SpawnPlacements.register(SKELEWAG.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySkelewag::canSkelewagSpawn);
//        SpawnPlacements.register(RAIN_FROG.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRainFrog::canRainFrogSpawn);
//        SpawnPlacements.register(POTOO.value(), spawnsOnLeaves, Heightmap.Types.MOTION_BLOCKING, EntityPotoo::canPotooSpawn);
//        SpawnPlacements.register(MUDSKIPPER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMudskipper::canMudskipperSpawn);
//        SpawnPlacements.register(RHINOCEROS.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRhinoceros::checkAnimalSpawnRules);
//        SpawnPlacements.register(SUGAR_GLIDER.value(), spawnsOnLeaves, Heightmap.Types.MOTION_BLOCKING, EntitySugarGlider::canSugarGliderSpawn);
//        SpawnPlacements.register(FARSEER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFarseer::checkFarseerSpawnRules);
//        SpawnPlacements.register(SKREECHER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySkreecher::checkSkreecherSpawnRules);
//        SpawnPlacements.register(UNDERMINER.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityUnderminer::checkUnderminerSpawnRules);
//        SpawnPlacements.register(MURMUR.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMurmur::checkMurmurSpawnRules);
//        SpawnPlacements.register(SKUNK.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySkunk::checkAnimalSpawnRules);
        SpawnPlacements.register(BANANA_SLUG.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBananaSlug::checkBananaSlugSpawnRules);
//        SpawnPlacements.register(BLUE_JAY.value(), spawnsOnLeaves, Heightmap.Types.MOTION_BLOCKING, EntityBlueJay::checkBlueJaySpawnRules);
        SpawnPlacements.register(CAIMAN.value(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCaiman::canCaimanSpawn);
//        SpawnPlacements.register(TRIOPS.value(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
//        FabricDefaultAttributeRegistry.register(GRIZZLY_BEAR.value(), EntityGrizzlyBear.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(ROADRUNNER.value(), EntityRoadrunner.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(BONE_SERPENT.value(), EntityBoneSerpent.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(BONE_SERPENT_PART.value(), EntityBoneSerpentPart.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(GAZELLE.value(), EntityGazelle.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(CROCODILE.value(), EntityCrocodile.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(FLY.value(), EntityFly.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(HUMMINGBIRD.value(), EntityHummingbird.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(ORCA.value(), EntityOrca.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SUNBIRD.value(), EntitySunbird.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(GORILLA.value(), EntityGorilla.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(CRIMSON_MOSQUITO.value(), EntityCrimsonMosquito.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(RATTLESNAKE.value(), EntityRattlesnake.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(ENDERGRADE.value(), EntityEndergrade.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(HAMMERHEAD_SHARK.value(), EntityHammerheadShark.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(LOBSTER.value(), EntityLobster.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(KOMODO_DRAGON.value(), EntityKomodoDragon.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(CAPUCHIN_MONKEY.value(), EntityCapuchinMonkey.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(CENTIPEDE_HEAD.value(), EntityCentipedeHead.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(CENTIPEDE_BODY.value(), EntityCentipedeBody.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(CENTIPEDE_TAIL.value(), EntityCentipedeTail.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(WARPED_TOAD.value(), EntityWarpedToad.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(MOOSE.value(), EntityMoose.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(MIMICUBE.value(), EntityMimicube.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(RACCOON.value(), EntityRaccoon.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(BLOBFISH.value(), EntityBlobfish.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SEAL.value(), EntitySeal.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(COCKROACH.value(), EntityCockroach.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SHOEBILL.value(), EntityShoebill.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(ELEPHANT.value(), EntityElephant.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SOUL_VULTURE.value(), EntitySoulVulture.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SNOW_LEOPARD.value(), EntitySnowLeopard.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SPECTRE.value(), EntitySpectre.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(CROW.value(), EntityCrow.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(ALLIGATOR_SNAPPING_TURTLE.value(), EntityAlligatorSnappingTurtle.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(MUNGUS.value(), EntityMungus.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(MANTIS_SHRIMP.value(), EntityMantisShrimp.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(GUSTER.value(), EntityGuster.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(WARPED_MOSCO.value(), EntityWarpedMosco.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(STRADDLER.value(), EntityStraddler.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(STRADPOLE.value(), EntityStradpole.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(EMU.value(), EntityEmu.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(PLATYPUS.value(), EntityPlatypus.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(DROPBEAR.value(), EntityDropBear.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(TASMANIAN_DEVIL.value(), EntityTasmanianDevil.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(KANGAROO.value(), EntityKangaroo.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(CACHALOT_WHALE.value(), EntityCachalotWhale.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(LEAFCUTTER_ANT.value(), EntityLeafcutterAnt.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(ENDERIOPHAGE.value(), EntityEnderiophage.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(BALD_EAGLE.value(), EntityBaldEagle.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(TIGER.value(), EntityTiger.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(TARANTULA_HAWK.value(), EntityTarantulaHawk.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(VOID_WORM.value(), EntityVoidWorm.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(VOID_WORM_PART.value(), EntityVoidWormPart.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(FRILLED_SHARK.value(), EntityFrilledShark.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(MIMIC_OCTOPUS.value(), EntityMimicOctopus.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(SEAGULL.value(), EntitySeagull.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(FROSTSTALKER.value(), EntityFroststalker.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(TUSKLIN.value(), EntityTusklin.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(LAVIATHAN.value(), EntityLaviathan.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(COSMAW.value(), EntityCosmaw.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(TOUCAN.value(), EntityToucan.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(MANED_WOLF.value(), EntityManedWolf.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(ANACONDA.value(), EntityAnaconda.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(ANACONDA_PART.value(), EntityAnacondaPart.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(ANTEATER.value(), EntityAnteater.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(ROCKY_ROLLER.value(), EntityRockyRoller.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(FLUTTER.value(), EntityFlutter.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(GELADA_MONKEY.value(), EntityGeladaMonkey.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(JERBOA.value(), EntityJerboa.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(TERRAPIN.value(), EntityTerrapin.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(COMB_JELLY.value(), EntityCombJelly.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(COSMIC_COD.value(), EntityCosmicCod.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(BUNFUNGUS.value(), EntityBunfungus.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(BISON.value(), EntityBison.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(GIANT_SQUID.value(), EntityGiantSquid.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SEA_BEAR.value(), EntitySeaBear.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(DEVILS_HOLE_PUPFISH.value(), EntityDevilsHolePupfish.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(CATFISH.value(), EntityCatfish.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(FLYING_FISH.value(), EntityFlyingFish.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SKELEWAG.value(), EntitySkelewag.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(RAIN_FROG.value(), EntityRainFrog.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(POTOO.value(), EntityPotoo.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(MUDSKIPPER.value(), EntityMudskipper.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(RHINOCEROS.value(), EntityRhinoceros.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SUGAR_GLIDER.value(), EntitySugarGlider.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(FARSEER.value(), EntityFarseer.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SKREECHER.value(), EntitySkreecher.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(UNDERMINER.value(), EntityUnderminer.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(MURMUR.value(), EntityMurmur.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(MURMUR_HEAD.value(), EntityMurmurHead.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(SKUNK.value(), EntitySkunk.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(BANANA_SLUG.value(), EntityBananaSlug.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(BLUE_JAY.value(), EntityBlueJay.bakeAttributes().build());
        FabricDefaultAttributeRegistry.register(CAIMAN.value(), EntityCaiman.bakeAttributes().build());
//        FabricDefaultAttributeRegistry.register(TRIOPS.value(), EntityTriops.bakeAttributes().build());
    }

    public static Predicate<LivingEntity> buildPredicateFromTag(TagKey<EntityType<?>> entityTag){
        if(entityTag == null){
            return Predicates.alwaysFalse();
        }else{
            return (com.google.common.base.Predicate<LivingEntity>) e -> e.isAlive() && e.getType().is(entityTag);
        }
    }

    public static Predicate<LivingEntity> buildPredicateFromTagTameable(TagKey<EntityType<?>> entityTag, LivingEntity owner){
        if(entityTag == null){
            return Predicates.alwaysFalse();
        }else{
            return (com.google.common.base.Predicate<LivingEntity>) e -> e.isAlive() && e.getType().is(entityTag) && !owner.isAlliedTo(e);
        }
    }

    public static boolean rollSpawn(int rolls, RandomSource random, MobSpawnType reason){
        if(reason == MobSpawnType.SPAWNER){
            return true;
        }else{
            return rolls <= 0 || random.nextInt(rolls) == 0;
        }
    }

    public static boolean createLeavesSpawnPlacement(LevelReader level, BlockPos pos, EntityType<?> type){
        BlockPos blockpos = pos.above();
        BlockPos blockpos1 = pos.below();
        FluidState fluidstate = level.getFluidState(pos);
        BlockState blockstate = level.getBlockState(pos);
        BlockState blockstate1 = level.getBlockState(blockpos1);
        if (!blockstate1.isValidSpawn(level, blockpos1, type) && !blockstate1.is(BlockTags.LEAVES)) {
            return false;
        } else {
            return NaturalSpawner.isValidEmptySpawnBlock(level, pos, blockstate, fluidstate, type) && NaturalSpawner.isValidEmptySpawnBlock(level, blockpos, level.getBlockState(blockpos), level.getFluidState(blockpos), type);
        }
    }

    public static void init() {
        initializeAttributes();
    }
}
