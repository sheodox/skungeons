package com.sheodox.skungeons.item.tool;

import com.sheodox.skungeons.SkungeonsMod;
import com.sheodox.skungeons.item.ItemModelProvider;
import net.minecraft.item.Item;

/**
 * Created by sheodox on 2017/03/19.
 */
public class ItemAxe extends net.minecraft.item.ItemAxe implements ItemModelProvider {

    private String name;

    public ItemAxe(ToolMaterial material, String name) {
        super(material, 8f, -3.1f);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
    }

    @Override
    public void registerItemModel(Item item) {
        SkungeonsMod.proxy.registerItemRenderer(this, 0, name);
    }
}