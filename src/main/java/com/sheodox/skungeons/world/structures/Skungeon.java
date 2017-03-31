package com.sheodox.skungeons.world.structures;

import com.sheodox.skungeons.block.ModBlocks;
import com.sheodox.skungeons.block.altar.TileEntityAltar;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by sheodox on 2017/03/25.
 */
public class Skungeon {
    //y coordinate for the bottom block of a dungeon
    private final int skungeonYBase = 200;
    private final int skungeonSize = 30;
    private final int skungeonHeight = 7;
    private final int skungeonHeightVariation = 40;
    //block the majority of the structure is, to be mostly unbreakable and less easily cheesed
    private final IBlockState baseBlock = Blocks.OBSIDIAN.getDefaultState();
    private final SkungeonTier tier;
    private Random random;

    public Skungeon(SkungeonTier tier) {
        this.tier = tier;
    }

    private int nextLight() {
        return this.random.nextInt(5) + 4;
    }

    private void spawnBlockPlane(World world, IBlockState blockState, boolean hasLights, BlockPos startPos, BlockPos stopPos) {
        int lightCounter = nextLight();

        for (int x = startPos.getX(); x != stopPos.getX(); x++) {
            for (int y = startPos.getY(); y != stopPos.getY(); y++) {
                for (int z = startPos.getZ(); z != stopPos.getZ(); z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (hasLights && lightCounter-- == 0) {
                        world.setBlockState(pos, Blocks.SEA_LANTERN.getDefaultState());
                        lightCounter = nextLight();
                    }
                    else {
                        world.setBlockState(pos, blockState);
                    }
                }
            }
        }
    }

    public void generateStructure(World world, Random random, int chunkXStart, int chunkZStart) {
        System.out.println("generating");
        System.out.println("x: " + chunkXStart + " z: " + chunkZStart);

        //give some variation to the height
        int yBase = skungeonYBase - random.nextInt(skungeonHeightVariation);

        this.random = random;
        //altar
        BlockPos altarPos = new BlockPos(chunkXStart + (skungeonSize / 2), yBase + 1, chunkZStart + (skungeonSize / 2));
        world.setBlockState(altarPos, ModBlocks.pedestal.getDefaultState());
        TileEntityAltar tile = (TileEntityAltar) world.getTileEntity(altarPos);
        tile.setTier(tier.tierName);


        //walls
        spawnBlockPlane(world, tier.decorationBlock, false,
                new BlockPos(chunkXStart, yBase, chunkZStart),
                new BlockPos(chunkXStart + skungeonSize, yBase + skungeonHeight, chunkZStart + 1)
        );
        spawnBlockPlane(world, tier.decorationBlock, false,
                new BlockPos(chunkXStart, yBase, chunkZStart + skungeonSize - 1),
                new BlockPos(chunkXStart + skungeonSize, yBase + skungeonHeight, chunkZStart + skungeonSize)
        );
        spawnBlockPlane(world, tier.decorationBlock, false,
                new BlockPos(chunkXStart, yBase, chunkZStart),
                new BlockPos(chunkXStart + 1, yBase + skungeonHeight, chunkZStart + skungeonSize)
        );
        spawnBlockPlane(world, tier.decorationBlock, false,
                new BlockPos(chunkXStart + skungeonSize - 1, yBase, chunkZStart),
                new BlockPos(chunkXStart + skungeonSize, yBase + skungeonHeight, chunkZStart + skungeonSize)
        );

        //floor
        spawnBlockPlane(world, baseBlock, false,
                new BlockPos(chunkXStart, yBase, chunkZStart),
                new BlockPos(chunkXStart + skungeonSize, yBase + 1, chunkZStart + skungeonSize)
        );
        //ceiling
        spawnBlockPlane(world, baseBlock, true,
                new BlockPos(chunkXStart, yBase + skungeonHeight, chunkZStart),
                new BlockPos(chunkXStart + skungeonSize, yBase + skungeonHeight + 1, chunkZStart + skungeonSize)
        );
    }
}
