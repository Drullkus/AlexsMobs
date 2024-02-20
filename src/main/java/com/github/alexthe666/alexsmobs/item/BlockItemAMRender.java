package com.github.alexthe666.alexsmobs.item;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;

public class BlockItemAMRender extends AMBlockItem {

    public BlockItemAMRender(Holder<Block> blockSupplier, Properties props) {
        super(blockSupplier, props);
    }

    // FIXME
    //@Override
    //public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
    //    consumer.accept((IClientItemExtensions) AlexsMobs.PROXY.getISTERProperties());
    //}
}
