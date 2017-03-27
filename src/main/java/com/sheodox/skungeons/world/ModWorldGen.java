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
    private final SkungeonTier ironTier = new SkungeonTier(EnumDyeColor.GRAY, "iron");
    private final SkungeonTier goldTier = new SkungeonTier(EnumDyeColor.YELLOW, "gold");
    private final SkungeonTier diamondTier = new SkungeonTier(EnumDyeColor.LIME, "diamond");

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        boolean shouldSpawn = random.nextInt(willSpawnMax) == 0;
        if (world.provider.getDimension() == 0 && shouldSpawn) {
            Skungeon skungeon = new Skungeon(getTier(random));
            skungeon.generateStructure(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private SkungeonTier getTier(Random random) {
        int tier = random.nextInt(1000);
        if (tier < 150) {
            return diamondTier;
        }
        else if (tier < 450) {
            return goldTier;
        }
        else if (tier < 700) {
            return ironTier;
        }
        return woodTier;
    }
}
