package com.sheodox.skungeons.item;

import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by sheodox on 2017/03/19.
 */
public class ItemOre extends ItemBase implements ItemOreDict {
    private String oreName;

    public ItemOre(String name, String oreName) {
        super (name);

        this.oreName = oreName;
    }

    @Override
    public void initOreDict() {
        OreDictionary.registerOre(oreName, this);
    }
}
