package com.sheodox.skungeons.world.structures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;

/**
 * Created by sheodox on 2017/03/25.
 */
public class SkungeonTier {
    public final IBlockState decorationBlock;
    public final String tierName;

    public SkungeonTier(EnumDyeColor color, String tierName){
        this.decorationBlock = Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, color);
        this.tierName = tierName;
    }
}
