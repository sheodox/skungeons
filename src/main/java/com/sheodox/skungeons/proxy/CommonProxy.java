package com.sheodox.skungeons.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;

/**
 * Created by sheodox on 2017/03/18.
 */
public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {}

    public String localize(String unlocalized, Object... args) {
        return I18n.translateToLocalFormatted(unlocalized, args);
    }
}
