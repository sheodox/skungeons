package com.sheodox.skungeons.block;

import com.sheodox.skungeons.SkungeonsMod;
import com.sheodox.skungeons.item.ItemModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

/**
 * Created by sheodox on 2017/03/18.
 */
public class BlockBase extends Block implements ItemModelProvider {
    protected String name;

    public BlockBase(Material material, String name) {
        super(material);

        this.name = name;

        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(SkungeonsMod.creativeTab);
    }

    public void registerItemModel(Item item) {
        SkungeonsMod.proxy.registerItemRenderer(item, 0, name);
    }
}
