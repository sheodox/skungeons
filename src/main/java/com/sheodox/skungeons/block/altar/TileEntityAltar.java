package com.sheodox.skungeons.block.altar;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by sheodox on 2017/03/25.
 */
public class TileEntityAltar extends TileEntity {
    private long nextAvailableTime = 0;
    private String tier;
    private String timeNBTKey = "nextAvailableTime";
    private String tierNBTKey = "tier";

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setLong(timeNBTKey, nextAvailableTime);
        compound.setString(tierNBTKey, tier);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        nextAvailableTime = compound.getLong(timeNBTKey);
        tier = compound.getString("tier");
        super.readFromNBT(compound);
    }

    public String getTier() {
        return tier;
    }
    public void setTier(String tier) {
        this.tier = tier;
        markDirty();
    }

    public boolean canStartGauntlet() {
        System.out.println("cur: " + System.currentTimeMillis());
        System.out.println("next: " + nextAvailableTime);
        return System.currentTimeMillis() > nextAvailableTime;
    }

    public String getNextTime() {
        if (canStartGauntlet()) {
            return "Available!";
        }
        long curTime = System.currentTimeMillis();
        int deltaSeconds = (int)(nextAvailableTime - curTime) / 1000;
        Integer minutes = deltaSeconds / 60;
        Integer seconds = deltaSeconds % 60;

        return minutes.toString() + "m " + seconds.toString() + "s";
    }

    public void scheduleNextGauntlet() {
        nextAvailableTime = (System.currentTimeMillis() + (1000 * 60 * 10));
        markDirty();
    }
}
