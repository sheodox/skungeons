package com.sheodox.skungeons.client;

import com.sheodox.skungeons.SkungeonsMod;
import com.sheodox.skungeons.block.ModBlocks;
import com.sheodox.skungeons.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Created by sheodox on 2017/03/19.
 */
public class PlaygroundTab extends CreativeTabs {
    public PlaygroundTab() {
        super(SkungeonsMod.modId);
        setBackgroundImageName("item_search.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModBlocks.pedestal);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
