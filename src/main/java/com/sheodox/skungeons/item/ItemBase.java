package com.sheodox.skungeons.item;

import com.sheodox.skungeons.SkungeonsMod;
import net.minecraft.item.Item;

/**
 * Created by sheodox on 2017/03/18.
 */
public class ItemBase extends Item implements ItemModelProvider {
    protected String name;

    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(SkungeonsMod.creativeTab);
    }

    @Override
    public void registerItemModel(Item item) {
        SkungeonsMod.proxy.registerItemRenderer(this, 0, name);
    }
}
