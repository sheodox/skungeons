package com.sheodox.skungeons.world;

import com.sheodox.skungeons.world.structures.Skungeon;
import com.sheodox.skungeons.world.structures.SkungeonTier;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by sheodox on 2017/03/19.
 */
public class ModWorldGen implements IWorldGenerator {
    private final int willSpawnMax = 100;

    private final SkungeonTier woodTier = new SkungeonTier(EnumDyeColor.BROWN, "wood");
    private final SkungeonTier stoneTier = new SkungeonTier(EnumDyeColor.GRAY, "stone");
    private final SkungeonTier coalTier = new SkungeonTier(EnumDyeColor.BLACK, "coal");
    private final SkungeonTier ironTier = new SkungeonTier(EnumDyeColor.SILVER, "iron");
    private final SkungeonTier goldTier = new SkungeonTier(EnumDyeColor.YELLOW, "gold");
    private final SkungeonTier diamondTier = new SkungeonTier(EnumDyeColor.LIME, "diamond");
    private final int woodWeight = 200;
    private final int stoneWeight = 200;
    private final int coalWeight = 200;
    private final int ironWeight = 100;
    private final int goldWeight = 100;
    private final int diamondWeight = 50;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        boolean shouldSpawn = random.nextInt(willSpawnMax) == 0;
        if (world.provider.getDimension() == 0 && shouldSpawn) {
            Skungeon skungeon = new Skungeon(getTier(random));
            skungeon.generateStructure(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private SkungeonTier getTier(Random random) {
        int[] weights = {woodWeight, stoneWeight, coalWeight, ironWeight, goldWeight, diamondWeight};
        SkungeonTier[] tiers = {woodTier, stoneTier, coalTier, ironTier, goldTier, diamondTier};
        int weightTotal = 0;
        for (int weight : weights) {
            weightTotal += weight;
        }

        int pick = random.nextInt(weightTotal - 1);
        for (int i = 0; i < weights.length; i++) {
            if (pick < weights[i]) {
                return tiers[i];
            }
            pick -= weights[i];
        }
        //should never get here, default to wood if it does
        return woodTier;
    }
}
