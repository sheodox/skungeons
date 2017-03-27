package com.sheodox.skungeons.item;

import com.sheodox.skungeons.SkungeonsMod;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

/**
 * Created by sheodox on 2017/03/19.
 */
public class ItemArmor extends net.minecraft.item.ItemArmor implements ItemModelProvider {
    private String name;

    public ItemArmor(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
        super(material, 0, slot);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
    }

    @Override
    public void registerItemModel(Item item) {
        SkungeonsMod.proxy.registerItemRenderer(this, 0, name);
    }
}
