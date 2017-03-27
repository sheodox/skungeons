package com.sheodox.skungeons.item.tool;

import com.sheodox.skungeons.SkungeonsMod;
import com.sheodox.skungeons.item.ItemModelProvider;
import net.minecraft.item.Item;

/**
 * Created by sheodox on 2017/03/19.
 */
public class ItemPickaxe extends net.minecraft.item.ItemPickaxe implements ItemModelProvider {
    private String name;

    public ItemPickaxe(ToolMaterial material, String name) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
    }

    @Override
    public void registerItemModel(Item item) {
        SkungeonsMod.proxy.registerItemRenderer(this, 0, name);
    }
}
