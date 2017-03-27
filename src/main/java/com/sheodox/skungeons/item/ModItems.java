package com.sheodox.skungeons.item;

import com.sheodox.skungeons.SkungeonsMod;
import com.sheodox.skungeons.item.tool.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by sheodox on 2017/03/18.
 */
public class ModItems {

    public static void init() {

    }

    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);
        if (item instanceof ItemModelProvider) {
            ((ItemModelProvider)item).registerItemModel(item);
        }

        if (item instanceof ItemOreDict) {
            ((ItemOreDict)item).initOreDict();
        }

        return item;
    }
}
