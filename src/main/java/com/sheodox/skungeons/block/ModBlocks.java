package com.sheodox.skungeons.block;

import com.sheodox.skungeons.block.altar.BlockPedestal;
import com.sheodox.skungeons.item.ItemModelProvider;
import com.sheodox.skungeons.item.ItemOreDict;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by sheodox on 2017/03/18.
 */
public class ModBlocks {
    public static BlockPedestal pedestal;

    public static void init() {
        pedestal = register(new BlockPedestal());
    }

    private static <T extends Block> T register(T block, ItemBlock itemBlock) {
        GameRegistry.register(block);
        if (itemBlock != null) {
            GameRegistry.register(itemBlock);
        }

        if (block instanceof ItemModelProvider) {
            ((ItemModelProvider) block).registerItemModel(itemBlock);
        }

        if (block instanceof ItemOreDict) {
            ((ItemOreDict) block).initOreDict();
        }

        if (itemBlock instanceof ItemOreDict) {
            ((ItemOreDict) itemBlock).initOreDict();
        }

        if (block instanceof BlockTileEntity) {
            GameRegistry.registerTileEntity(
                    ((BlockTileEntity<?>) block).getTileEntityClass(),
                    block.getRegistryName().toString()
            );
        }
        return block;
    }

    private static <T extends Block> T register(T block) {
        ItemBlock itemBlock = new ItemBlock(block);
        if (block.getRegistryName() != null) {
            itemBlock.setRegistryName(block.getRegistryName());
        }
        return register(block, itemBlock);
    }
}
