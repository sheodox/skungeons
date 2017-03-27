package com.sheodox.skungeons.block.altar;

import com.sheodox.skungeons.SkungeonsMod;
import com.sheodox.skungeons.block.BlockTileEntity;
import jline.internal.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.Random;

/**
 * Created by sheodox on 2017/03/19.
 */
public class BlockPedestal extends BlockTileEntity<TileEntityAltar> {
    private Random random = new Random();

    public BlockPedestal() {
        super(Material.ROCK, "pedestal");
        this.setBlockUnbreakable();
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public Class<TileEntityAltar> getTileEntityClass() {
        return TileEntityAltar.class;
    }

    @Nullable
    @Override
    public  TileEntityAltar createTileEntity(World world, IBlockState state) {
        return new TileEntityAltar();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityAltar tile = getTileEntity(world, pos);
            System.out.println("can start: " + tile.canStartGauntlet());
            if (tile.canStartGauntlet()) {
                GauntletHandler gauntlet = new GauntletHandler(world, pos, tile);
                gauntlet.startGauntlet();
            }
            else {
                player.sendMessage(new TextComponentString("Time until next gauntlet: " + tile.getNextTime()));
            }
        }
        return true;
    }
}
