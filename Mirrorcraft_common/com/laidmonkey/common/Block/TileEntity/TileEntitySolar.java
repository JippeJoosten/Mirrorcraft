package com.laidmonkey.common.Block.TileEntity;

import com.laidmonkey.common.Block.BlockSolar;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySolar extends TileEntity {
    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    
    /* SAVING & LOADING */
    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
       // data.setTag("SolarBlock", BlockSolar.writeToNBT(new NBTTagCompound()));
       
    }
    
    
    public void updateEntity()
    {
            if (this.worldObj != null && !this.worldObj.isRemote && this.worldObj.getTotalWorldTime() % 20L == 0L)
            {
                this.blockType = this.getBlockType();
    
                if (this.blockType != null && this.blockType instanceof BlockSolar)
                {
                    ((BlockSolar)this.blockType).func_94444_j_(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                }
            }
        
    }
}
