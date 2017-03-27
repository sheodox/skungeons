package com.sheodox.skungeons.proxy;

import com.sheodox.skungeons.SkungeonsMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by sheodox on 2017/03/18.
 */
public class ClientProxy extends CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(
                item,
                meta,
                new ModelResourceLocation(SkungeonsMod.modId + ":" + id, "inventory")
        );
    }

    public String localize(String unlocalized, Object... args) {
        return I18n.format(unlocalized, args);
    }
}
