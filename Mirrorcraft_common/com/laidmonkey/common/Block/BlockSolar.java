package com.laidmonkey.common.Block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDaylightDetector;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.laidmonkey.common.Mirrorcraft;
import com.laidmonkey.common.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSolar extends BlockContainer {
    
    public String locationTexture;

    public BlockSolar(int par1, Material par2, String par3) {
        super(par1, par2);
        this.locationTexture = par3;
        this.setUnlocalizedName(Strings.SOLAR_NAME);
        this.setCreativeTab(Mirrorcraft.tabMirrorcraft);
        this.setHardness(5F);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {}

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {}
    
    
    
    @Override
    public boolean onBlockActivated(World world, int par2, int par3, int par4,
            EntityPlayer entityplayer, int par6, float par7, float par8,
            float par9) {
        
        
        int l = world.getBlockMetadata(par2, par3, par4);
        int i1 = world.getSavedLightValue(EnumSkyBlock.Sky, par2, par3, par4) - world.skylightSubtracted;
        float f = world.getCelestialAngleRadians(1.0F);
        
        if (f < (float)Math.PI)
        {
            f += (0.0F - f) * 0.2F;
            
        }
        else
        {
            f += (((float)Math.PI * 2F) - f) * 0.2F;
           
        }
        if (l != i1)
        {
            world.setBlockMetadataWithNotify(par2, par3, par4, i1, 3);
        }
        
        System.out.println(world.getSavedLightValue(EnumSkyBlock.Sky, par2, par3, par4) + " " + par4);
        //System.out.println("i1 is hier: " + i1 + " f is hierbij: " + f + " en dan nog par2, par3, par4 " + par2 + ", " + par3 + ", " + par4 + "" + " Light is: " + world.skylightSubtracted);

        // Drop through if the player is sneaking
        if (entityplayer.isSneaking())
            return false;

        world.markBlockForUpdate(par2, par3, par4);
        return true;
        
        
    }
    
    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return true;
    }

    @Override
    public void breakBlock(World world, int i, int j, int k, int par5, int par6) {
        // Utils.preDestroyBlock(world, i, j, k);

        super.breakBlock(world, i, j, k, par5, par6);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addCreativeItems(ArrayList itemList) {
        itemList.add(new ItemStack(this));
    }
    

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityDaylightDetector();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {

        this.blockIcon = par1IconRegister.registerIcon(locationTexture);

    }

}
