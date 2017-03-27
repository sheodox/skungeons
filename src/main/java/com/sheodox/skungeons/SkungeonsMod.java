package com.sheodox.skungeons;

import com.sheodox.skungeons.block.ModBlocks;
import com.sheodox.skungeons.client.PlaygroundTab;
import com.sheodox.skungeons.item.ModItems;
import com.sheodox.skungeons.proxy.CommonProxy;
import com.sheodox.skungeons.recipe.ModRecipes;
import com.sheodox.skungeons.world.ModWorldGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

/**
 * Created by sheodox on 2017/03/18.
 */
@Mod(modid = SkungeonsMod.modId, name = SkungeonsMod.name, version = SkungeonsMod.version, acceptedMinecraftVersions = "[1.11.2]")
public class SkungeonsMod {

    public static final String modId = "skungeons";
    public static final String name = "SkungeonsMod";
    public static final String version = "0.0.2";

    public static final PlaygroundTab creativeTab = new PlaygroundTab();

    public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 500, 6, 2, 14);
    public static final ItemArmor.ArmorMaterial copperArmorMaterial = EnumHelper.addArmorMaterial("COPPER", modId + ":copper", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f);

    @Mod.Instance(modId)
    public static SkungeonsMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
        ModBlocks.init();
        ModItems.init();
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
    }

    public static void broadcast(World world, String msg) {
        List<EntityPlayer> players = world.playerEntities;
        for (int i = 0; i < players.size(); i++) {
            players.get(i).sendMessage(new TextComponentString(msg));
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SidedProxy(serverSide = "com.sheodox.skungeons.proxy.CommonProxy", clientSide = "com.sheodox.skungeons.proxy.ClientProxy")
    public static CommonProxy proxy;
}
