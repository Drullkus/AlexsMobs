package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.entity.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Supplier;

public class AMItemRegistry {
    public static final AMArmorMaterial ROADRUNNER_ARMOR_MATERIAL = new AMArmorMaterial("roadrunner", 18, new int[]{3, 3, 3, 3}, 20, SoundEvents.ARMOR_EQUIP_TURTLE, 0);
    public static final AMArmorMaterial CROCODILE_ARMOR_MATERIAL = new AMArmorMaterial("crocodile", 22, new int[]{2, 5, 7, 3}, 25, SoundEvents.ARMOR_EQUIP_TURTLE, 1);
    public static final AMArmorMaterial CENTIPEDE_ARMOR_MATERIAL = new AMArmorMaterial("centipede", 20, new int[]{6, 6, 6, 6}, 22, SoundEvents.ARMOR_EQUIP_TURTLE, 0.5F);
    public static final AMArmorMaterial MOOSE_ARMOR_MATERIAL = new AMArmorMaterial("moose", 19, new int[]{3, 3, 3, 3}, 21, SoundEvents.ARMOR_EQUIP_TURTLE, 0.5F);
    public static final AMArmorMaterial RACCOON_ARMOR_MATERIAL = new AMArmorMaterial("raccoon", 17, new int[]{3, 3, 3, 3}, 21, SoundEvents.ARMOR_EQUIP_LEATHER, 2.5F);
    public static final AMArmorMaterial SOMBRERO_ARMOR_MATERIAL = new AMArmorMaterial("sombrero", 14, new int[]{2, 2, 2, 2}, 30, SoundEvents.ARMOR_EQUIP_LEATHER, 0.5F);
    public static final AMArmorMaterial SPIKED_TURTLE_SHELL_ARMOR_MATERIAL = new AMArmorMaterial("spiked_turtle_shell", 35, new int[]{3, 3, 3, 3}, 30, SoundEvents.ARMOR_EQUIP_TURTLE, 1F, 0.2F);
    public static final AMArmorMaterial FEDORA_ARMOR_MATERIAL = new AMArmorMaterial("fedora", 10, new int[]{2, 2, 2, 2}, 30, SoundEvents.ARMOR_EQUIP_LEATHER, 0.5F);
    public static final AMArmorMaterial EMU_ARMOR_MATERIAL = new AMArmorMaterial("emu", 9, new int[]{4, 4, 4, 4}, 20, SoundEvents.ARMOR_EQUIP_LEATHER, 0.5F);
    public static final AMArmorMaterial TARANTULA_HAWK_ELYTRA_MATERIAL = new AMArmorMaterial("tarantula_hawk_elytra", 9, new int[]{3, 3, 3, 3}, 5, SoundEvents.ARMOR_EQUIP_LEATHER, 0);
    public static final AMArmorMaterial FROSTSTALKER_ARMOR_MATERIAL = new AMArmorMaterial("froststalker", 9, new int[]{3, 3, 3, 3}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.5F);
    public static final AMArmorMaterial ROCKY_ARMOR_MATERIAL = new AMArmorMaterial("rocky_roller", 20, new int[]{2, 5, 7, 3}, 10, SoundEvents.ARMOR_EQUIP_TURTLE, 0.5F);
    public static final AMArmorMaterial FLYING_FISH_MATERIAL = new AMArmorMaterial("flying_fish", 9, new int[]{1, 1, 1, 1}, 8, SoundEvents.ARMOR_EQUIP_LEATHER, 0F);
    public static final AMArmorMaterial NOVELTY_HAT_MATERIAL = new AMArmorMaterial("novelty_hat", 10, new int[]{2, 2, 2, 2}, 30, SoundEvents.ARMOR_EQUIP_LEATHER, 0F);
    public static final AMArmorMaterial KIMONO_MATERIAL = new AMArmorMaterial("kimono", 8, new int[]{3, 3, 3, 3}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0F);

    //public static final DeferredRegister<Item> DEF_REG = DeferredRegister.create(ForgeRegistries.ITEMS, AlexsMobs.MODID);

    static {
        initSpawnEggs();
    }

    private static Holder<Item> register(String name, Supplier<Item> builder) {
        return Registry.registerForHolder(BuiltInRegistries.ITEM, new ResourceLocation(AlexsMobs.MODID, name), builder.get());
    }

    public static final Holder<Item> TAB_ICON = register("tab_icon", () -> new ItemTabIcon(new Item.Properties()));

    //public static final Holder<Item> ANIMAL_DICTIONARY = register("animal_dictionary", () -> new ItemAnimalDictionary(new Item.Properties().stacksTo(1)));
    //public static final Holder<Item> BEAR_FUR = register("bear_fur", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> BEAR_DUST = register("bear_dust", () -> new ItemBearDust(new Item.Properties().rarity(Rarity.EPIC)));
    //public static final Holder<Item> ROADRUNNER_FEATHER = register("roadrunner_feather", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> ROADDRUNNER_BOOTS = register("roadrunner_boots", () -> new ItemModArmor(ROADRUNNER_ARMOR_MATERIAL, ArmorItem.Type.BOOTS));
    public static final Holder<Item> LAVA_BOTTLE = register("lava_bottle", () -> new Item(new Item.Properties().stacksTo(1)));
    //public static final Holder<Item> BONE_SERPENT_TOOTH = register("bone_serpent_tooth", () -> new Item(new Item.Properties().fireResistant()));
    //public static final Holder<Item> GAZELLE_HORN = register("gazelle_horn", () -> new Item(new Item.Properties().fireResistant()));
    public static final Holder<Item> CROCODILE_SCUTE = register("crocodile_scute", () -> new Item(new Item.Properties()));
    public static final Holder<Item> CROCODILE_CHESTPLATE = register("crocodile_chestplate", () -> new ItemModArmor(CROCODILE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE));
    public static final Holder<Item> MAGGOT = register("maggot", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).build())));
    public static final Holder<Item> BANANA = register("banana", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final Holder<Item> ANCIENT_DART = register("ancient_dart", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final Holder<Item> HALO = register("halo", () -> new ItemInventoryOnly(new Item.Properties()));
    public static final Holder<Item> BLOOD_SAC = register("blood_sac", () -> new Item(new Item.Properties()));

    //public static final Holder<Item> MOSQUITO_PROBOSCIS = register("mosquito_proboscis", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> BLOOD_SPRAYER = register("blood_sprayer", () -> new ItemBloodSprayer(new Item.Properties().durability(100)));
    //public static final Holder<Item> RATTLESNAKE_RATTLE = register("rattlesnake_rattle", () -> new Item(new Item.Properties()));
    public static final Holder<Item> CHORUS_ON_A_STICK = register("chorus_on_a_stick", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final Holder<Item> SHARK_TOOTH = register("shark_tooth", () -> new Item(new Item.Properties()));
    public static final Holder<Item> SHARK_TOOTH_ARROW = register("shark_tooth_arrow", () -> new ItemModArrow(new Item.Properties()));
    //public static final Holder<Item> LOBSTER_TAIL = register("lobster_tail", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).meat().build())));
    //public static final Holder<Item> COOKED_LOBSTER_TAIL = register("cooked_lobster_tail", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.65F).meat().build())));
    //public static final Holder<Item> LOBSTER_BUCKET = register("lobster_bucket", () -> new ItemModFishBucket(AMEntityRegistry.LOBSTER, Fluids.WATER, new Item.Properties()));
    public static final Holder<Item> KOMODO_SPIT = register("komodo_spit", () -> new Item(new Item.Properties()));
    public static final Holder<Item> KOMODO_SPIT_BOTTLE = register("komodo_spit_bottle", () -> new Item(new Item.Properties()));
    public static final Holder<Item> POISON_BOTTLE = register("poison_bottle", () -> new Item(new Item.Properties()));
    public static final Holder<Item> SOPA_DE_MACACO = register("sopa_de_macaco", () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).meat().build()).stacksTo(1)));
    //public static final Holder<Item> CENTIPEDE_LEG = register("centipede_leg", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> CENTIPEDE_LEGGINGS = register("centipede_leggings", () -> new ItemModArmor(CENTIPEDE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS));
    //public static final Holder<Item> MOSQUITO_LARVA = register("mosquito_larva", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> MOOSE_ANTLER = register("moose_antler", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> MOOSE_HEADGEAR = register("moose_headgear", () -> new ItemModArmor(MOOSE_ARMOR_MATERIAL, ArmorItem.Type.HELMET));
    //public static final Holder<Item> MOOSE_RIBS = register("moose_ribs", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.6F).meat().build())));
    //public static final Holder<Item> COOKED_MOOSE_RIBS = register("cooked_moose_ribs", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.85F).meat().build())));
    //public static final Holder<Item> MIMICREAM = register("mimicream", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> RACCOON_TAIL = register("raccoon_tail", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> FRONTIER_CAP = register("frontier_cap", () -> new ItemModArmor(RACCOON_ARMOR_MATERIAL, ArmorItem.Type.HELMET));
    //public static final Holder<Item> BLOBFISH = register("blobfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).meat().effect(new MobEffectInstance(MobEffects.POISON, 120, 0), 1F).build())));
    //public static final Holder<Item> BLOBFISH_BUCKET = register("blobfish_bucket", () -> new ItemModFishBucket(AMEntityRegistry.BLOBFISH, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> FISH_OIL = register("fish_oil", () -> new ItemFishOil(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(0).saturationMod(0.2F).build())));
    //public static final Holder<Item> MARACA = register("maraca", () -> new ItemMaraca(new Item.Properties()));
    //public static final Holder<Item> SOMBRERO = register("sombrero", () -> new ItemModArmor(SOMBRERO_ARMOR_MATERIAL, ArmorItem.Type.HELMET));
    //public static final Holder<Item> COCKROACH_WING_FRAGMENT = register("cockroach_wing_fragment", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> COCKROACH_WING = register("cockroach_wing", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> COCKROACH_OOTHECA = register("cockroach_ootheca", () -> new ItemAnimalEgg(new Item.Properties()));
    //public static final Holder<Item> ACACIA_BLOSSOM = register("acacia_blossom", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> SOUL_HEART = register("soul_heart", () -> new Item(new Item.Properties()));
    public static final Holder<Item> SPIKED_SCUTE = register("spiked_scute", () -> new Item(new Item.Properties()));
    public static final Holder<Item> SPIKED_TURTLE_SHELL = register("spiked_turtle_shell", () -> new ItemModArmor(SPIKED_TURTLE_SHELL_ARMOR_MATERIAL, ArmorItem.Type.HELMET));
    //public static final Holder<Item> SHRIMP_FRIED_RICE = register("shrimp_fried_rice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(12).saturationMod(1F).build())));
    public static final Holder<Item> GUSTER_EYE = register("guster_eye", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> POCKET_SAND = register("pocket_sand", () -> new ItemPocketSand(new Item.Properties().durability(220)));
    //public static final Holder<Item> WARPED_MUSCLE = register("warped_muscle", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> HEMOLYMPH_SAC = register("hemolymph_sac", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> HEMOLYMPH_BLASTER = register("hemolymph_blaster", () -> new ItemHemolymphBlaster(new Item.Properties().durability(150)));
    //public static final Holder<Item> WARPED_MIXTURE = register("warped_mixture", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).craftRemainder(Items.GLASS_BOTTLE)));
    //public static final Holder<Item> STRADDLITE = register("straddlite", () -> new Item(new Item.Properties().fireResistant()));
    //public static final Holder<Item> STRADPOLE_BUCKET = register("stradpole_bucket", () -> new ItemModFishBucket(AMEntityRegistry.STRADPOLE, Fluids.LAVA, new Item.Properties()));
    //public static final Holder<Item> STRADDLEBOARD = register("straddleboard", () -> new ItemStraddleboard(new Item.Properties().fireResistant().durability(220)));
    //public static final Holder<Item> EMU_EGG = register("emu_egg", () -> new ItemAnimalEgg(new Item.Properties().stacksTo(8)));
    //public static final Holder<Item> BOILED_EMU_EGG = register("boiled_emu_egg", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1F).meat().build())));
    //public static final Holder<Item> EMU_FEATHER = register("emu_feather", () -> new Item(new Item.Properties().fireResistant()));
    //public static final Holder<Item> EMU_LEGGINGS = register("emu_leggings", () -> new ItemModArmor(EMU_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS));
    //public static final Holder<Item> PLATYPUS_BUCKET = register("platypus_bucket", () -> new ItemModFishBucket(AMEntityRegistry.PLATYPUS, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> FEDORA = register("fedora", () -> new ItemModArmor(FEDORA_ARMOR_MATERIAL, ArmorItem.Type.HELMET));
    //public static final Holder<Item> DROPBEAR_CLAW = register("dropbear_claw", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> KANGAROO_MEAT = register("kangaroo_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).meat().build())));
    //public static final Holder<Item> COOKED_KANGAROO_MEAT = register("cooked_kangaroo_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.85F).meat().build())));
    //public static final Holder<Item> KANGAROO_HIDE = register("kangaroo_hide", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> KANGAROO_BURGER = register("kangaroo_burger", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(12).saturationMod(1F).meat().build())));
    //public static final Holder<Item> AMBERGRIS = register("ambergris", () -> new ItemFuel(new Item.Properties(), 12800));
    //public static final Holder<Item> CACHALOT_WHALE_TOOTH = register("cachalot_whale_tooth", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> ECHOLOCATOR = register("echolocator", () -> new ItemEcholocator(new Item.Properties().durability(100), ItemEcholocator.EchoType.ECHOLOCATION));
    //public static final Holder<Item> ENDOLOCATOR = register("endolocator", () -> new ItemEcholocator(new Item.Properties().durability(25), ItemEcholocator.EchoType.ENDER));
    public static final Holder<Item> GONGYLIDIA = register("gongylidia", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1.2F).build())));
    public static final Holder<Item> LEAFCUTTER_ANT_PUPA = register("leafcutter_ant_pupa", () -> new ItemLeafcutterPupa(new Item.Properties()));
    //public static final Holder<Item> ENDERIOPHAGE_ROCKET = register("enderiophage_rocket", () -> new ItemEnderiophageRocket(new Item.Properties()));
    //public static final Holder<Item> FALCONRY_GLOVE_INVENTORY = register("falconry_glove_inventory", () -> new ItemInventoryOnly(new Item.Properties()));
    //public static final Holder<Item> FALCONRY_GLOVE_HAND = register("falconry_glove_hand", () -> new ItemInventoryOnly(new Item.Properties()));
    //public static final Holder<Item> FALCONRY_GLOVE = register("falconry_glove", () -> new ItemFalconryGlove(new Item.Properties().stacksTo(1)));
    //public static final Holder<Item> FALCONRY_HOOD = register("falconry_hood", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> TARANTULA_HAWK_WING_FRAGMENT = register("tarantula_hawk_wing_fragment", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> TARANTULA_HAWK_WING = register("tarantula_hawk_wing", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> TARANTULA_HAWK_ELYTRA = register("tarantula_hawk_elytra", () -> new ItemTarantulaHawkElytra(new Item.Properties().durability(800).rarity(Rarity.UNCOMMON), TARANTULA_HAWK_ELYTRA_MATERIAL));
    //public static final Holder<Item> MYSTERIOUS_WORM = register("mysterious_worm", () -> new ItemMysteriousWorm(new Item.Properties().rarity(Rarity.RARE)));
    //public static final Holder<Item> VOID_WORM_MANDIBLE = register("void_worm_mandible", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> VOID_WORM_EYE = register("void_worm_eye", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    //public static final Holder<Item> DIMENSIONAL_CARVER = register("dimensional_carver", () -> new ItemDimensionalCarver(new Item.Properties().durability(20).rarity(Rarity.EPIC)));
    //public static final Holder<Item> SHATTERED_DIMENSIONAL_CARVER = register("shattered_dimensional_carver", () -> new ItemShatteredDimensionalCarver(new Item.Properties().durability(4).rarity(Rarity.RARE)));
    public static final Holder<Item> SERRATED_SHARK_TOOTH = register("serrated_shark_tooth", () -> new Item(new Item.Properties()));
    public static final Holder<Item> FRILLED_SHARK_BUCKET = register("frilled_shark_bucket", () -> new ItemModFishBucket(AMEntityRegistry.FRILLED_SHARK, Fluids.WATER, new Item.Properties()));
    public static final Holder<Item> SHIELD_OF_THE_DEEP = register("shield_of_the_deep", () -> new ItemShieldOfTheDeep(new Item.Properties().durability(400).rarity(Rarity.UNCOMMON)));
    //public static final Holder<Item> MIMIC_OCTOPUS_BUCKET = register("mimic_octopus_bucket", () -> new ItemModFishBucket(AMEntityRegistry.MIMIC_OCTOPUS, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> FROSTSTALKER_HORN = register("froststalker_horn", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> FROSTSTALKER_HELMET = register("froststalker_helmet", () -> new ItemModArmor(FROSTSTALKER_ARMOR_MATERIAL, ArmorItem.Type.HELMET));
    //public static final Holder<Item> PIGSHOES = register("pigshoes", () -> new ItemPigshoes(new Item.Properties().stacksTo(1)));
    //public static final Holder<Item> STRADDLE_HELMET = register("straddle_helmet", () -> new Item(new Item.Properties().fireResistant()));
    //public static final Holder<Item> STRADDLE_SADDLE = register("straddle_saddle", () -> new Item(new Item.Properties().fireResistant()));
    //public static final Holder<Item> COSMIC_COD = register("cosmic_cod", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).effect(new MobEffectInstance(AMEffectRegistry.ENDER_FLU.value(), 12000), 0.15F).build())));
    public static final Holder<Item> SHED_SNAKE_SKIN = register("shed_snake_skin", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> VINE_LASSO_INVENTORY = register("vine_lasso_inventory", () -> new ItemInventoryOnly(new Item.Properties()));
    //public static final Holder<Item> VINE_LASSO_HAND = register("vine_lasso_hand", () -> new ItemInventoryOnly(new Item.Properties()));
    //public static final Holder<Item> VINE_LASSO = register("vine_lasso", () -> new ItemVineLasso(new Item.Properties().stacksTo(1)));
    public static final Holder<Item> ROCKY_SHELL = register("rocky_shell", () -> new Item(new Item.Properties()));
    public static final Holder<Item> ROCKY_CHESTPLATE = register("rocky_chestplate", () -> new ItemModArmor(ROCKY_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE));
    //public static final Holder<Item> POTTED_FLUTTER = register("potted_flutter", () -> new ItemFlutterPot(new Item.Properties()));
    //public static final Holder<Item> TERRAPIN_BUCKET = register("terrapin_bucket", () -> new ItemModFishBucket(AMEntityRegistry.TERRAPIN, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> COMB_JELLY_BUCKET = register("comb_jelly_bucket", () -> new ItemModFishBucket(AMEntityRegistry.COMB_JELLY, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> RAINBOW_JELLY = register("rainbow_jelly", () -> new ItemRainbowJelly(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).build())));
    //public static final Holder<Item> COSMIC_COD_BUCKET = register("cosmic_cod_bucket", () -> new ItemCosmicCodBucket(new Item.Properties()));
    //public static final Holder<Item> MUNGAL_SPORES = register("mungal_spores", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> BISON_FUR = register("bison_fur", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> LOST_TENTACLE = register("lost_tentacle", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> SQUID_GRAPPLE = register("squid_grapple", () -> new ItemSquidGrapple(new Item.Properties().durability(450)));
    //public static final Holder<Item> DEVILS_HOLE_PUPFISH_BUCKET = register("devils_hole_pupfish_bucket", () -> new ItemModFishBucket(AMEntityRegistry.DEVILS_HOLE_PUPFISH, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> PUPFISH_LOCATOR = register("pupfish_locator", () -> new ItemEcholocator(new Item.Properties().durability(200), ItemEcholocator.EchoType.PUPFISH));
    //public static final Holder<Item> SMALL_CATFISH_BUCKET = register("small_catfish_bucket", () -> new ItemModFishBucket(AMEntityRegistry.CATFISH, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> MEDIUM_CATFISH_BUCKET = register("medium_catfish_bucket", () -> new ItemModFishBucket(AMEntityRegistry.CATFISH, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> LARGE_CATFISH_BUCKET = register("large_catfish_bucket", () -> new ItemModFishBucket(AMEntityRegistry.CATFISH, Fluids.WATER, new Item.Properties()));
    public static final Holder<Item> RAW_CATFISH = register("raw_catfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).meat().build())));
    public static final Holder<Item> COOKED_CATFISH = register("cooked_catfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).meat().build())));
    //public static final Holder<Item> FLYING_FISH = register("flying_fish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).meat().build())));
    //public static final Holder<Item> FLYING_FISH_BOOTS = register("flying_fish_boots", () -> new ItemModArmor(FLYING_FISH_MATERIAL, ArmorItem.Type.BOOTS));
    //public static final Holder<Item> FLYING_FISH_BUCKET = register("flying_fish_bucket", () -> new ItemModFishBucket(AMEntityRegistry.FLYING_FISH, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> FISH_BONES = register("fish_bones", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> SKELEWAG_SWORD_INVENTORY = register("skelewag_sword_inventory", () -> new ItemInventoryOnly(new Item.Properties()));
    //public static final Holder<Item> SKELEWAG_SWORD_HAND = register("skelewag_sword_hand", () -> new ItemInventoryOnly(new Item.Properties()));
    //public static final Holder<Item> SKELEWAG_SWORD = register("skelewag_sword", () -> new ItemSkelewagSword(new Item.Properties().stacksTo(1).durability(430)));
    //public static final Holder<Item> NOVELTY_HAT = register("novelty_hat", () -> new ItemModArmor(NOVELTY_HAT_MATERIAL, ArmorItem.Type.HELMET));
    //public static final Holder<Item> MUDSKIPPER_BUCKET = register("mudskipper_bucket", () -> new ItemModFishBucket(AMEntityRegistry.MUDSKIPPER, Fluids.WATER, new Item.Properties()));
    //public static final Holder<Item> FARSEER_ARM = register("farseer_arm", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    //public static final Holder<Item> SKREECHER_SOUL = register("skreecher_soul", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> GHOSTLY_PICKAXE = register("ghostly_pickaxe", () -> new ItemGhostlyPickaxe(new Item.Properties()));
    //public static final Holder<Item> ELASTIC_TENDON = register("elastic_tendon", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> TENDON_WHIP = register("tendon_whip", () -> new ItemTendonWhip(new Item.Properties()));
    //public static final Holder<Item> UNSETTLING_KIMONO = register("unsettling_kimono", () -> new ItemModArmor(KIMONO_MATERIAL, ArmorItem.Type.CHESTPLATE));
    //public static final Holder<Item> STINK_BOTTLE = register("stink_bottle", () -> new ItemStinkBottle(AMBlockRegistry.SKUNK_SPRAY, new Item.Properties().stacksTo(16)));

    //public static final Holder<Item> STINK_RAY_HAND = register("stink_ray_hand", () -> new ItemInventoryOnly(new Item.Properties()));

    //public static final Holder<Item> STINK_RAY_INVENTORY = register("stink_ray_inventory", () -> new ItemInventoryOnly(new Item.Properties()));

    //public static final Holder<Item> STINK_RAY_EMPTY_HAND = register("stink_ray_empty_hand", () -> new ItemInventoryOnly(new Item.Properties()));

    //public static final Holder<Item> STINK_RAY_EMPTY_INVENTORY = register("stink_ray_empty_inventory", () -> new ItemInventoryOnly(new Item.Properties()));

    //public static final Holder<Item> STINK_RAY = register("stink_ray", () -> new ItemStinkRay(new Item.Properties().durability(5)));
    public static final Holder<Item> BANANA_SLUG_SLIME = register("banana_slug_slime", () -> new Item(new Item.Properties()));
    //public static final Holder<Item> MOSQUITO_REPELLENT_STEW = register("mosquito_repellent_stew", () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).alwaysEat().saturationMod(0.3F).effect(() -> new MobEffectInstance(AMEffectRegistry.MOSQUITO_REPELLENT.value(), 24000), 1.0F).build()).stacksTo(1)));
    //public static final Holder<Item> TRIOPS_BUCKET = register("triops_bucket", () -> new ItemModFishBucket(AMEntityRegistry.TRIOPS, Fluids.WATER, new Item.Properties()));

    //public static final Holder<Item> MUSIC_DISC_THIME = register("music_disc_thime", () -> new RecordItem(14, AMSoundRegistry.MUSIC_DISC_THIME, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 191 * 20));
    //public static final Holder<Item> MUSIC_DISC_DAZE = register("music_disc_daze", () -> new RecordItem(14, AMSoundRegistry.MUSIC_DISC_DAZE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 314 * 20));

    public static void initSpawnEggs() {
        //register("spawn_egg_grizzly_bear", () -> new SpawnEggItem(AMEntityRegistry.GRIZZLY_BEAR, 0X693A2C, 0X976144, new Item.Properties()));
        //register("spawn_egg_roadrunner", () -> new SpawnEggItem(AMEntityRegistry.ROADRUNNER, 0X3A2E26, 0XFBE9CE, new Item.Properties()));
        //register("spawn_egg_bone_serpent", () -> new SpawnEggItem(AMEntityRegistry.BONE_SERPENT, 0XE5D9C4, 0XFF6038, new Item.Properties()));
        //register("spawn_egg_gazelle", () -> new SpawnEggItem(AMEntityRegistry.GAZELLE, 0XDDA675,0X2C2925, new Item.Properties()));
        register("spawn_egg_crocodile", () -> new SpawnEggItem(AMEntityRegistry.CROCODILE.value(), 0X738940,0XA6A15E, new Item.Properties()));
        //register("spawn_egg_fly", () -> new SpawnEggItem(AMEntityRegistry.FLY, 0X464241,0X892E2E, new Item.Properties()));
        //register("spawn_egg_hummingbird", () -> new SpawnEggItem(AMEntityRegistry.HUMMINGBIRD, 0X325E7F,0X44A75F, new Item.Properties()));
        //register("spawn_egg_orca", () -> new SpawnEggItem(AMEntityRegistry.ORCA, 0X2C2C2C,0XD6D8E4, new Item.Properties()));
        //register("spawn_egg_sunbird", () -> new SpawnEggItem(AMEntityRegistry.SUNBIRD, 0XF6694F,0XFFDDA0, new Item.Properties()));
        register("spawn_egg_gorilla", () -> new SpawnEggItem(AMEntityRegistry.GORILLA.value(), 0X595B5D,0X1C1C21, new Item.Properties()));
        //register("spawn_egg_crimson_mosquito", () -> new SpawnEggItem(AMEntityRegistry.CRIMSON_MOSQUITO, 0X53403F,0XC11A1A, new Item.Properties()));
        //register("spawn_egg_rattlesnake", () -> new SpawnEggItem(AMEntityRegistry.RATTLESNAKE, 0XCEB994,0X937A5B, new Item.Properties()));
        //register("spawn_egg_endergrade", () -> new SpawnEggItem(AMEntityRegistry.ENDERGRADE, 0X7862B3,0x81BDEB, new Item.Properties()));
        register("spawn_egg_hammerhead_shark", () -> new SpawnEggItem(AMEntityRegistry.HAMMERHEAD_SHARK.value(), 0X8A92B5,0XB9BED8, new Item.Properties()));
        //register("spawn_egg_lobster", () -> new SpawnEggItem(AMEntityRegistry.LOBSTER, 0XC43123,0XDD5F38, new Item.Properties()));
        register("spawn_egg_komodo_dragon", () -> new SpawnEggItem(AMEntityRegistry.KOMODO_DRAGON.value(), 0X746C4F,0X564231, new Item.Properties()));
        register("spawn_egg_capuchin_monkey", () -> new SpawnEggItem(AMEntityRegistry.CAPUCHIN_MONKEY.value(), 0X25211F,0XF1DAB3, new Item.Properties()));
        //register("spawn_egg_centipede", () -> new SpawnEggItem(AMEntityRegistry.CENTIPEDE_HEAD, 0X342B2E,0X733449, new Item.Properties()));
        //register("spawn_egg_warped_toad", () -> new SpawnEggItem(AMEntityRegistry.WARPED_TOAD, 0X1F968E,0XFEAC6D, new Item.Properties()));
        //register("spawn_egg_moose", () -> new SpawnEggItem(AMEntityRegistry.MOOSE, 0X36302A,0XD4B183, new Item.Properties()));
        //register("spawn_egg_mimicube", () -> new SpawnEggItem(AMEntityRegistry.MIMICUBE, 0X8A80C1,0X5E4F6F, new Item.Properties()));
        //register("spawn_egg_raccoon", () -> new SpawnEggItem(AMEntityRegistry.RACCOON, 0X85827E,0X2A2726, new Item.Properties()));
        //register("spawn_egg_blobfish", () -> new SpawnEggItem(AMEntityRegistry.BLOBFISH, 0XDBC6BD,0X9E7A7F, new Item.Properties()));
        //register("spawn_egg_seal", () -> new SpawnEggItem(AMEntityRegistry.SEAL, 0X483C32,0X66594C, new Item.Properties()));
        //register("spawn_egg_cockroach", () -> new SpawnEggItem(AMEntityRegistry.COCKROACH, 0X0D0909,0X42241E, new Item.Properties()));
        //register("spawn_egg_shoebill", () -> new SpawnEggItem(AMEntityRegistry.SHOEBILL, 0X828282,0XD5B48A, new Item.Properties()));
        //register("spawn_egg_elephant", () -> new SpawnEggItem(AMEntityRegistry.ELEPHANT, 0X8D8987,0XEDE5D1, new Item.Properties()));
        //register("spawn_egg_soul_vulture", () -> new SpawnEggItem(AMEntityRegistry.SOUL_VULTURE, 0X23262D,0X57F4FF, new Item.Properties()));
        //register("spawn_egg_snow_leopard", () -> new SpawnEggItem(AMEntityRegistry.SNOW_LEOPARD, 0XACA293,0X26201D, new Item.Properties()));
        //register("spawn_egg_spectre", () -> new SpawnEggItem(AMEntityRegistry.SPECTRE, 0XC8D0EF,0X8791EF, new Item.Properties()));
        //register("spawn_egg_crow", () -> new SpawnEggItem(AMEntityRegistry.CROW, 0X0D111C,0X1C2030, new Item.Properties()));
        //register("spawn_egg_alligator_snapping_turtle", () -> new SpawnEggItem(AMEntityRegistry.ALLIGATOR_SNAPPING_TURTLE, 0X6C5C52,0X456926, new Item.Properties()));
        //register("spawn_egg_mungus", () -> new SpawnEggItem(AMEntityRegistry.MUNGUS, 0X836A8D,0X45454C, new Item.Properties()));
        //register("spawn_egg_mantis_shrimp", () -> new SpawnEggItem(AMEntityRegistry.MANTIS_SHRIMP, 0XDB4858,0X15991E, new Item.Properties()));
        //register("spawn_egg_guster", () -> new SpawnEggItem(AMEntityRegistry.GUSTER, 0XF8D49A,0XFF720A, new Item.Properties()));
        //register("spawn_egg_warped_mosco", () -> new SpawnEggItem(AMEntityRegistry.WARPED_MOSCO, 0X322F58,0X5B5EF1, new Item.Properties()));
        //register("spawn_egg_straddler", () -> new SpawnEggItem(AMEntityRegistry.STRADDLER, 0X5D5F6E,0XCDA886, new Item.Properties()));
        //register("spawn_egg_stradpole", () -> new SpawnEggItem(AMEntityRegistry.STRADPOLE, 0X5D5F6E,0X576A8B, new Item.Properties()));
        //register("spawn_egg_emu", () -> new SpawnEggItem(AMEntityRegistry.EMU, 0X665346,0X3B3938, new Item.Properties()));
        //register("spawn_egg_platypus", () -> new SpawnEggItem(AMEntityRegistry.PLATYPUS, 0X7D503E,0X363B43, new Item.Properties()));
        //register("spawn_egg_dropbear", () -> new SpawnEggItem(AMEntityRegistry.DROPBEAR, 0X8A2D35,0X60A3A3, new Item.Properties()));
        //register("spawn_egg_tasmanian_devil", () -> new SpawnEggItem(AMEntityRegistry.TASMANIAN_DEVIL, 0X252426,0XA8B4BF, new Item.Properties()));
        //register("spawn_egg_kangaroo", () -> new SpawnEggItem(AMEntityRegistry.KANGAROO, 0XCE9D65,0XDEBDA0, new Item.Properties()));
        //register("spawn_egg_cachalot_whale", () -> new SpawnEggItem(AMEntityRegistry.CACHALOT_WHALE, 0X949899,0X5F666E, new Item.Properties()));
        register("spawn_egg_leafcutter_ant", () -> new SpawnEggItem(AMEntityRegistry.LEAFCUTTER_ANT.value(), 0X964023,0XA65930, new Item.Properties()));
        //register("spawn_egg_enderiophage", () -> new SpawnEggItem(AMEntityRegistry.ENDERIOPHAGE, 0X872D83,0XF6E2CD, new Item.Properties()));
        //register("spawn_egg_bald_eagle", () -> new SpawnEggItem(AMEntityRegistry.BALD_EAGLE, 0X321F18,0XF4F4F4, new Item.Properties()));
        register("spawn_egg_tiger", () -> new SpawnEggItem(AMEntityRegistry.TIGER.value(), 0XC7612E,0X2A3233, new Item.Properties()));
        //register("spawn_egg_tarantula_hawk", () -> new SpawnEggItem(AMEntityRegistry.TARANTULA_HAWK, 0X234763,0XE37B38, new Item.Properties()));
        //register("spawn_egg_void_worm", () -> new SpawnEggItem(AMEntityRegistry.VOID_WORM, 0X0F1026,0X1699AB, new Item.Properties()));
        register("spawn_egg_frilled_shark", () -> new SpawnEggItem(AMEntityRegistry.FRILLED_SHARK.value(), 0X726B6B,0X873D3D, new Item.Properties()));
        //register("spawn_egg_mimic_octopus", () -> new SpawnEggItem(AMEntityRegistry.MIMIC_OCTOPUS, 0XFFEBDC,0X1D1C1F, new Item.Properties()));
        register("spawn_egg_seagull", () -> new SpawnEggItem(AMEntityRegistry.SEAGULL.value(), 0XC9D2DC,0XFFD850, new Item.Properties()));
        //register("spawn_egg_froststalker", () -> new SpawnEggItem(AMEntityRegistry.FROSTSTALKER, 0X788AC1,0XA1C3FF, new Item.Properties()));
        //register("spawn_egg_tusklin", () -> new SpawnEggItem(AMEntityRegistry.TUSKLIN, 0X735841,0XE8E2D5, new Item.Properties()));
        //register("spawn_egg_laviathan", () -> new SpawnEggItem(AMEntityRegistry.LAVIATHAN, 0XD68356,0X3C3947, new Item.Properties()));
        //register("spawn_egg_cosmaw", () -> new SpawnEggItem(AMEntityRegistry.COSMAW, 0X746DBD,0XD6BFE3, new Item.Properties()));
        register("spawn_egg_toucan", () -> new SpawnEggItem(AMEntityRegistry.TOUCAN.value(), 0XF58F33,0X1E2133, new Item.Properties()));
        //register("spawn_egg_maned_wolf", () -> new SpawnEggItem(AMEntityRegistry.MANED_WOLF, 0XBB7A47,0X40271A, new Item.Properties()));
        register("spawn_egg_anaconda", () -> new SpawnEggItem(AMEntityRegistry.ANACONDA.value(), 0X565C22,0XD3763F, new Item.Properties()));
        register("spawn_egg_anteater", () -> new SpawnEggItem(AMEntityRegistry.ANTEATER.value(), 0X4C3F3A, 0XCCBCB4, new Item.Properties()));
        register("spawn_egg_rocky_roller", () -> new SpawnEggItem(AMEntityRegistry.ROCKY_ROLLER.value(), 0XB0856F, 0X999184, new Item.Properties()));
        //register("spawn_egg_flutter", () -> new SpawnEggItem(AMEntityRegistry.FLUTTER, 0X70922D, 0XD07BE3, new Item.Properties()));
        //register("spawn_egg_gelada_monkey", () -> new SpawnEggItem(AMEntityRegistry.GELADA_MONKEY, 0XB08C64, 0XFF4F53, new Item.Properties()));
        //register("spawn_egg_jerboa", () -> new SpawnEggItem(AMEntityRegistry.JERBOA, 0XDEC58A, 0XDE9D90, new Item.Properties()));
        //register("spawn_egg_terrapin", () -> new SpawnEggItem(AMEntityRegistry.TERRAPIN, 0X6E6E30, 0X929647, new Item.Properties()));
        //register("spawn_egg_comb_jelly", () -> new SpawnEggItem(AMEntityRegistry.COMB_JELLY, 0XCFE9FE, 0X6EFF8B, new Item.Properties()));
        //register("spawn_egg_cosmic_cod", () -> new SpawnEggItem(AMEntityRegistry.COSMIC_COD, 0X6985C7, 0XE2D1FF, new Item.Properties()));
        //register("spawn_egg_bunfungus", () -> new SpawnEggItem(AMEntityRegistry.BUNFUNGUS, 0X6F6D91, 0XC92B29, new Item.Properties()));
        //register("spawn_egg_bison", () -> new SpawnEggItem(AMEntityRegistry.BISON, 0X4C3A2E, 0X7A6546, new Item.Properties()));
        //register("spawn_egg_giant_squid", () -> new SpawnEggItem(AMEntityRegistry.GIANT_SQUID, 0XAB4B4D, 0XD67D6B, new Item.Properties()));
        //register("spawn_egg_devils_hole_pupfish", () -> new SpawnEggItem(AMEntityRegistry.DEVILS_HOLE_PUPFISH, 0X567BC4, 0X6C4475, new Item.Properties()));
        //register("spawn_egg_catfish", () -> new SpawnEggItem(AMEntityRegistry.CATFISH, 0X807757, 0X8A7466, new Item.Properties()));
        //register("spawn_egg_flying_fish", () -> new SpawnEggItem(AMEntityRegistry.FLYING_FISH, 0X7BBCED, 0X6881B3, new Item.Properties()));
        //register("spawn_egg_skelewag", () -> new SpawnEggItem(AMEntityRegistry.SKELEWAG, 0XD9FCB1, 0X3A4F30, new Item.Properties()));
        //register("spawn_egg_rain_frog", () -> new SpawnEggItem(AMEntityRegistry.RAIN_FROG, 0XC0B59B, 0X7B654F, new Item.Properties()));
        //register("spawn_egg_potoo", () -> new SpawnEggItem(AMEntityRegistry.POTOO, 0X8C7753, 0XFFC042, new Item.Properties()));
        //register("spawn_egg_mudskipper", () -> new SpawnEggItem(AMEntityRegistry.MUDSKIPPER, 0X60704A, 0X49806C, new Item.Properties()));
        //register("spawn_egg_rhinoceros", () -> new SpawnEggItem(AMEntityRegistry.RHINOCEROS, 0XA19594, 0X827474, new Item.Properties()));
        //register("spawn_egg_sugar_glider", () -> new SpawnEggItem(AMEntityRegistry.SUGAR_GLIDER, 0X868181, 0XEBEBE0, new Item.Properties()));
        //register("spawn_egg_farseer", () -> new SpawnEggItem(AMEntityRegistry.FARSEER, 0X33374F, 0X91FF59, new Item.Properties()));
        //register("spawn_egg_skreecher", () -> new SpawnEggItem(AMEntityRegistry.SKREECHER, 0X074857, 0X7FF8FF, new Item.Properties()));
        //register("spawn_egg_underminer", () -> new SpawnEggItem(AMEntityRegistry.UNDERMINER, 0XD6E2FF, 0X6C84C4, new Item.Properties()));
        //register("spawn_egg_murmur", () -> new SpawnEggItem(AMEntityRegistry.MURMUR, 0X804448, 0XB5AF9C, new Item.Properties()));
        //register("spawn_egg_skunk", () -> new SpawnEggItem(AMEntityRegistry.SKUNK, 0X222D36, 0XE4E5F2, new Item.Properties()));
        register("spawn_egg_banana_slug", () -> new SpawnEggItem(AMEntityRegistry.BANANA_SLUG.value(), 0XFFD045, 0XFFF173, new Item.Properties()));
        //register("spawn_egg_blue_jay", () -> new SpawnEggItem(AMEntityRegistry.BLUE_JAY, 0X5FB7FE, 0X293B42, new Item.Properties()));
        register("spawn_egg_caiman", () -> new SpawnEggItem(AMEntityRegistry.CAIMAN.value(), 0X5C5631, 0XBBC45C, new Item.Properties()));
        //register("spawn_egg_triops", () -> new SpawnEggItem(AMEntityRegistry.TRIOPS, 0X967954, 0XCA7150, new Item.Properties()));
        //registerPatternItem("bear");
        //registerPatternItem("australia_0");
        //registerPatternItem("australia_1");
        //registerPatternItem("new_mexico");
        //registerPatternItem("brazil");
        //for(int i = 0; i <= 10; i++){
        //    register("dimensional_carver_shard_" + i, () -> new ItemInventoryOnly(new Item.Properties()));
        //}
    }

    private static void registerPatternItem(String name) {
        TagKey<BannerPattern> bannerPatternTagKey = TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(AlexsMobs.MODID, "pattern_for_" + name));
        register("banner_pattern_" + name, () -> new BannerPatternItem(bannerPatternTagKey, (new Item.Properties()).stacksTo(1)));
    }

    public static void init() {
        CROCODILE_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(CROCODILE_SCUTE.value()));
        //ROADRUNNER_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(ROADRUNNER_FEATHER.value()));
        //CENTIPEDE_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(CENTIPEDE_LEG.value()));
        //MOOSE_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(MOOSE_ANTLER.value()));
        //RACCOON_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(RACCOON_TAIL.value()));
        SOMBRERO_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(Items.HAY_BLOCK));
        SPIKED_TURTLE_SHELL_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(SPIKED_SCUTE.value()));
        FEDORA_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(Items.LEATHER));
        //EMU_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(EMU_FEATHER.value()));
        ROCKY_ARMOR_MATERIAL.setRepairMaterial(Ingredient.of(ROCKY_SHELL.value()));
        //FLYING_FISH_MATERIAL.setRepairMaterial(Ingredient.of(FLYING_FISH.value()));
        NOVELTY_HAT_MATERIAL.setRepairMaterial(Ingredient.of(Items.BONE));
        KIMONO_MATERIAL.setRepairMaterial(Ingredient.of(ItemTags.WOOL));
        //LecternBooks.BOOKS.put(ANIMAL_DICTIONARY.getId(), new LecternBooks.BookData(0X606B26, 0XFDF8ED));
    }

    public static void initDispenser(){
        DispenserBlock.registerBehavior(SHARK_TOOTH_ARROW.value(), new AbstractProjectileDispenseBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
                EntitySharkToothArrow entityarrow = new EntitySharkToothArrow(AMEntityRegistry.SHARK_TOOTH_ARROW.value(), position.x(), position.y(), position.z(), worldIn);
                entityarrow.pickup = EntitySharkToothArrow.Pickup.ALLOWED;
                return entityarrow;
            }
        });
        DispenserBlock.registerBehavior(ANCIENT_DART.value(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
                EntityTossedItem tossedItem = new EntityTossedItem(worldIn, position.x(), position.y(), position.z());
                tossedItem.setDart(true);
                return tossedItem;
            }
        });
        //DispenserBlock.registerBehavior(COCKROACH_OOTHECA.value(), new AbstractProjectileDispenseBehavior() {
        //    protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
        //        EntityCockroachEgg entityarrow = new EntityCockroachEgg(worldIn, position.x(), position.y(), position.z());
        //        return entityarrow;
        //    }
        //});
        //DispenserBlock.registerBehavior(EMU_EGG.value(), new AbstractProjectileDispenseBehavior() {
        //    protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
        //        EntityEmuEgg entityarrow = new EntityEmuEgg(worldIn, position.x(), position.y(), position.z());
        //        return entityarrow;
        //    }
        //});
        //DispenserBlock.registerBehavior(ENDERIOPHAGE_ROCKET.value(), new AbstractProjectileDispenseBehavior() {
        //    protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
        //        EntityEnderiophageRocket entityarrow = new EntityEnderiophageRocket(worldIn, position.x(), position.y(), position.z(), stackIn);
        //        return entityarrow;
        //    }
        //});
        DispenseItemBehavior bucketDispenseBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource blockSource, ItemStack stack) {
                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)stack.getItem();
                BlockPos blockpos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                Level level = blockSource.level();
                if (dispensiblecontaineritem.emptyContents((Player)null, level, blockpos, (BlockHitResult)null)) {
                    dispensiblecontaineritem.checkExtraContent((Player)null, level, stack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(blockSource, stack);
                }
            }
        };
        //DispenserBlock.registerBehavior(LOBSTER_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(BLOBFISH_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(STRADPOLE_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(PLATYPUS_BUCKET.value(), bucketDispenseBehavior);
        DispenserBlock.registerBehavior(FRILLED_SHARK_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(MIMIC_OCTOPUS_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(TERRAPIN_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(COMB_JELLY_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(COSMIC_COD_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(DEVILS_HOLE_PUPFISH_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(SMALL_CATFISH_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(MEDIUM_CATFISH_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(LARGE_CATFISH_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(FLYING_FISH_BUCKET.value(), bucketDispenseBehavior);
        //DispenserBlock.registerBehavior(MUDSKIPPER_BUCKET.value(), bucketDispenseBehavior);
        ComposterBlock.COMPOSTABLES.put(BANANA.value(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(AMBlockRegistry.BANANA_PEEL.value().asItem(), 1F);
        //ComposterBlock.COMPOSTABLES.put(ACACIA_BLOSSOM.value(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GONGYLIDIA.value(), 0.9F);
    }

}
