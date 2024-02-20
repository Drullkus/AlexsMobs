package com.github.alexthe666.alexsmobs.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemTabIcon extends ItemInventoryOnly {
    public ItemTabIcon(Item.Properties properties) {
        super(properties);
    }

    public static boolean hasCustomEntityDisplay(ItemStack stack){
        return stack.getTag() != null && stack.getTag().contains("DisplayEntityType");
    }

    public static String getCustomDisplayEntityString(ItemStack stack){
        return stack.getTag().getString("DisplayEntityType");
    }

// FIXME Client Rendering
//    @Override
//    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
//        consumer.accept((IClientItemExtensions)AlexsMobs.PROXY.getISTERProperties());
//    }
//
//    @Nullable
//    public static EntityType getEntityType(@Nullable CompoundTag tag) {
//        if (tag != null && tag.contains("DisplayEntityType")) {
//            String entityType = tag.getString("DisplayEntityType");
//           return ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.tryParse(entityType));
//        }
//        return null;
//    }
}
