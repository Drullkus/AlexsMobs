package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.item.CustomTabBehavior;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AMCreativeTabRegistry {


    //public static final DeferredRegister<CreativeModeTab> DEF_REG = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AlexsMobs.MODID);

    //public static final RegistryObject<CreativeModeTab> TAB = DEF_REG.register(AlexsMobs.MODID, () -> CreativeModeTab.builder()
    public static final Holder<CreativeModeTab> TAB = Registry.registerForHolder(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(AlexsMobs.MODID, AlexsMobs.MODID), FabricItemGroup.builder()
            .title(Component.translatable("itemGroup." + AlexsMobs.MODID))
            //.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> new ItemStack(AMItemRegistry.TAB_ICON.value()))
            .displayItems((enabledFeatures, output) -> {
                for(Item item : BuiltInRegistries.ITEM) {
					if (!item.builtInRegistryHolder().key().location().getNamespace().equals(AlexsMobs.MODID)) continue;

                    if (item instanceof CustomTabBehavior customTabBehavior) {
                        customTabBehavior.fillItemCategory(output);
                    } else {
                        output.accept(item);
                    }
                }
            })
            .build());

    public static void init() {
    }
}
