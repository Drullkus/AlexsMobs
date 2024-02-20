package com.github.alexthe666.alexsmobs.effect;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class AMEffectRegistry {
    //public static final DeferredRegister<MobEffect> EFFECT_DEF_REG = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AlexsMobs.MODID);
    //public static final DeferredRegister<Potion> POTION_DEF_REG = DeferredRegister.create(ForgeRegistries.POTIONS, AlexsMobs.MODID);

    private static Holder<MobEffect> registerEffect(String name, Supplier<MobEffect> builder) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(AlexsMobs.MODID, name), builder.get());
    }

    private static Holder<Potion> registerPotion(String name, Supplier<Potion> builder) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, new ResourceLocation(AlexsMobs.MODID, name), builder.get());
    }

    public static final Holder<MobEffect> KNOCKBACK_RESISTANCE = registerEffect("knockback_resistance", EffectKnockbackResistance::new);
    public static final Holder<MobEffect> LAVA_VISION = registerEffect("lava_vision", EffectLavaVision::new);
    public static final Holder<MobEffect> SUNBIRD_BLESSING = registerEffect("sunbird_blessing", ()-> new EffectSunbird(false));
    public static final Holder<MobEffect> SUNBIRD_CURSE = registerEffect("sunbird_curse", ()-> new EffectSunbird(true));
    public static final Holder<MobEffect> POISON_RESISTANCE = registerEffect("poison_resistance", EffectPoisonResistance::new);
    public static final Holder<MobEffect> OILED = registerEffect("oiled", EffectOiled::new);
    public static final Holder<MobEffect> ORCAS_MIGHT = registerEffect("orcas_might", EffectOrcaMight::new);
    public static final Holder<MobEffect> BUG_PHEROMONES = registerEffect("bug_pheromones", EffectBugPheromones::new);
    public static final Holder<MobEffect> SOULSTEAL = registerEffect("soulsteal", EffectSoulsteal::new);
    public static final Holder<MobEffect> CLINGING = registerEffect("clinging", EffectClinging::new);
    //public static final Holder<MobEffect> ENDER_FLU = registerEffect("ender_flu", EffectEnderFlu::new);
    public static final Holder<MobEffect> FEAR = registerEffect("fear", EffectFear::new);
    public static final Holder<MobEffect> TIGERS_BLESSING = registerEffect("tigers_blessing", EffectTigersBlessing::new);
    public static final Holder<MobEffect> DEBILITATING_STING = registerEffect("debilitating_sting", EffectDebilitatingSting::new);
    public static final Holder<MobEffect> EXSANGUINATION = registerEffect("exsanguination", EffectExsanguination::new);
    public static final Holder<MobEffect> EARTHQUAKE = registerEffect("earthquake", EffectEarthquake::new);
    public static final Holder<MobEffect> FLEET_FOOTED = registerEffect("fleet_footed", EffectFleetFooted::new);
    public static final Holder<MobEffect> POWER_DOWN = registerEffect("power_down", EffectPowerDown::new);

    public static final Holder<MobEffect> MOSQUITO_REPELLENT = registerEffect("mosquito_repellent", EffectMosquitoRepellent::new);
    public static final Holder<Potion> KNOCKBACK_RESISTANCE_POTION = registerPotion("knockback_resistance", ()-> new Potion(new MobEffectInstance(KNOCKBACK_RESISTANCE.value(), 3600)));
    public static final Holder<Potion> LONG_KNOCKBACK_RESISTANCE_POTION = registerPotion("long_knockback_resistance", ()-> new Potion(new MobEffectInstance(KNOCKBACK_RESISTANCE.value(), 9600)));
    public static final Holder<Potion> STRONG_KNOCKBACK_RESISTANCE_POTION = registerPotion("strong_knockback_resistance", ()-> new Potion(new MobEffectInstance(KNOCKBACK_RESISTANCE.value(), 1800, 1)));
    public static final Holder<Potion> LAVA_VISION_POTION = registerPotion("lava_vision", ()-> new Potion(new MobEffectInstance(LAVA_VISION.value(), 3600)));
    public static final Holder<Potion> LONG_LAVA_VISION_POTION = registerPotion("long_lava_vision", ()-> new Potion(new MobEffectInstance(LAVA_VISION.value(), 9600)));
    public static final Holder<Potion> SPEED_III_POTION = registerPotion("speed_iii", ()-> new Potion(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2200, 2)));
    public static final Holder<Potion> POISON_RESISTANCE_POTION = registerPotion("poison_resistance", ()-> new Potion(new MobEffectInstance(POISON_RESISTANCE.value(), 3600)));
    public static final Holder<Potion> LONG_POISON_RESISTANCE_POTION = registerPotion("long_poison_resistance", ()-> new Potion(new MobEffectInstance(POISON_RESISTANCE.value(), 9600)));
    public static final Holder<Potion> BUG_PHEROMONES_POTION = registerPotion("bug_pheromones", ()-> new Potion(new MobEffectInstance(BUG_PHEROMONES.value(), 3600)));
    public static final Holder<Potion> LONG_BUG_PHEROMONES_POTION = registerPotion("long_bug_pheromones", ()-> new Potion(new MobEffectInstance(BUG_PHEROMONES.value(), 9600)));
    public static final Holder<Potion> SOULSTEAL_POTION = registerPotion("soulsteal", ()-> new Potion(new MobEffectInstance(SOULSTEAL.value(), 3600)));
    public static final Holder<Potion> LONG_SOULSTEAL_POTION = registerPotion("long_soulsteal", ()-> new Potion(new MobEffectInstance(SOULSTEAL.value(), 9600)));
    public static final Holder<Potion> STRONG_SOULSTEAL_POTION = registerPotion("strong_soulsteal", ()-> new Potion(new MobEffectInstance(SOULSTEAL.value(), 1800, 1)));
    public static final Holder<Potion> CLINGING_POTION = registerPotion("clinging", ()-> new Potion(new MobEffectInstance(CLINGING.value(), 3600)));
    public static final Holder<Potion> LONG_CLINGING_POTION = registerPotion("long_clinging", ()-> new Potion(new MobEffectInstance(CLINGING.value(), 9600)));

    public static ItemStack createPotion(Holder<Potion> potion){
        return  PotionUtils.setPotion(new ItemStack(Items.POTION), potion.value());
    }

    public static ItemStack createPotion(Potion potion){
        return  PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
    }

    public static void init(){
        //FabricBrewingRecipeRegistry.registerPotionRecipe(Ingredient.of(createPotion(Potions.STRENGTH)), Ingredient.of(AMItemRegistry.BEAR_FUR.value()), createPotion(KNOCKBACK_RESISTANCE_POTION));
        FabricBrewingRecipeRegistry.registerPotionRecipe(KNOCKBACK_RESISTANCE_POTION.value(), Ingredient.of(Items.REDSTONE), LONG_KNOCKBACK_RESISTANCE_POTION.value());
        FabricBrewingRecipeRegistry.registerPotionRecipe(KNOCKBACK_RESISTANCE_POTION.value(), Ingredient.of(Items.GLOWSTONE_DUST), STRONG_KNOCKBACK_RESISTANCE_POTION.value());
        //FabricBrewingRecipeRegistry.registerPotionRecipe(Ingredient.of(AMItemRegistry.LAVA_BOTTLE.value()), Ingredient.of(AMItemRegistry.BONE_SERPENT_TOOTH.value()), createPotion(LAVA_VISION_POTION));
        FabricBrewingRecipeRegistry.registerPotionRecipe(LAVA_VISION_POTION.value(), Ingredient.of(Items.REDSTONE), LONG_LAVA_VISION_POTION.value());
        //FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.POISON, Ingredient.of(AMItemRegistry.RATTLESNAKE_RATTLE.value()), AMItemRegistry.POISON_BOTTLE.value());
        //FabricBrewingRecipeRegistry.registerPotionRecipe(Ingredient.of(AMItemRegistry.POISON_BOTTLE.value()), Ingredient.of(AMItemRegistry.CENTIPEDE_LEG.value()), createPotion(POISON_RESISTANCE_POTION));
        //FabricBrewingRecipeRegistry.registerPotionRecipe(Ingredient.of(AMItemRegistry.KOMODO_SPIT_BOTTLE.value()), Ingredient.of(AMItemRegistry.CENTIPEDE_LEG.value()), createPotion(POISON_RESISTANCE_POTION));
        FabricBrewingRecipeRegistry.registerPotionRecipe(POISON_RESISTANCE_POTION.value(), Ingredient.of(AMItemRegistry.KOMODO_SPIT.value()), LONG_POISON_RESISTANCE_POTION.value());
        //FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.STRONG_SWIFTNESS, Ingredient.of(AMItemRegistry.GAZELLE_HORN.value()), SPEED_III_POTION.value());
        //FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(AMItemRegistry.COCKROACH_WING.value()), BUG_PHEROMONES_POTION.value());
        FabricBrewingRecipeRegistry.registerPotionRecipe(BUG_PHEROMONES_POTION.value(), Ingredient.of(Items.REDSTONE), LONG_BUG_PHEROMONES_POTION.value());
        //FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(AMItemRegistry.SOUL_HEART.value()), SOULSTEAL_POTION.value());
        FabricBrewingRecipeRegistry.registerPotionRecipe(SOULSTEAL_POTION.value(), Ingredient.of(Items.REDSTONE), LONG_SOULSTEAL_POTION.value());
        FabricBrewingRecipeRegistry.registerPotionRecipe(SOULSTEAL_POTION.value(), Ingredient.of(Items.GLOWSTONE_DUST), STRONG_SOULSTEAL_POTION.value());
        //FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(AMItemRegistry.DROPBEAR_CLAW.value()), CLINGING_POTION.value());
        FabricBrewingRecipeRegistry.registerPotionRecipe(CLINGING_POTION.value(), Ingredient.of(Items.REDSTONE), LONG_CLINGING_POTION.value());
    }
}
