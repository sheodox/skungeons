package com.sheodox.skungeons.block.altar;

import com.sheodox.skungeons.SkungeonsMod;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Random;

/**
 * Created by sheodox on 2017/03/25.
 */
public class GauntletHandler {
    private boolean inProgress = false;
    private final TileEntityAltar tile;
    private final BlockPos altarPos;
    private final World world;
    private final String tierName;
    private final Random random = new Random();
    private int entitiesRemaining = 0;

    public GauntletHandler(World world, BlockPos altarPos, TileEntityAltar tile) {
        this.world = world;
        this.altarPos = altarPos;
        this.tile = tile;
        this.tierName = tile.getTier();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private int randomOffset() {
        return random.nextInt(24) - 12;
    }

    private Entity getMob() {
        if (tierName.equals("wood")) {
            return new EntityZombie(world);
        }
        else if (tierName.equals("iron")) {
            return new EntitySkeleton(world);
        }
        else if (tierName.equals("gold")) {
            return new EntityPigZombie(world);
        }
        else {
            return new EntityBlaze(world);
        }
    }

    private Item getReward() {
        if (tierName.equals("wood")) {
            return Item.getItemById(17);
        }
        else if (tierName.equals("iron")) {
            return Items.IRON_INGOT;
        }
        else if (tierName.equals("gold")) {
            return Items.GOLD_INGOT;
        }
        else {
            return Items.DIAMOND;
        }
    }


    public void startGauntlet() {
        tile.scheduleNextGauntlet();
        inProgress = true;
        SkungeonsMod.broadcast(world, "Gauntlet started!");

        //reset in case something was left over from last time, don't want this to be unobtainable
        entitiesRemaining = 0;
        for (int i = 0; i < 20; i++) {
            Entity mob = getMob();
            mob.setPosition(
                    altarPos.getX() + randomOffset(),
                    altarPos.getY(),
                    altarPos.getZ() + randomOffset()
            );
            world.spawnEntity(mob);
            //add two because entityDied event is getting fired twice for some reason, hacky
            entitiesRemaining++;
        }
    }

    @SubscribeEvent
    public void entityDied(LivingDeathEvent event) {
        if (inProgress && !world.isRemote) {
            Entity entity = event.getEntity();
            double distance = entity.getPosition().getDistance(altarPos.getX(), altarPos.getY(), altarPos.getZ());
            if (distance < 50) {
                entitiesRemaining--;
                checkRemaining();
            }
        }
    }

    private void checkRemaining() {
        if (entitiesRemaining == 0) {
            inProgress = false;
            //reward
            SkungeonsMod.broadcast(world, "Gauntlet finished!");
            for (int i = 0; i < 5; i++) {
                ItemStack reward = new ItemStack(getReward());
                reward.setCount(64);
                world.spawnEntity(new EntityItem(world, altarPos.getX(), altarPos.getY() + 2, altarPos.getZ(), reward));
            }
        }
    }
}
