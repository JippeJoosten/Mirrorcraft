package com.laidmonkey.common.Block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.laidmonkey.common.Mirrorcraft;
import com.laidmonkey.common.Block.TileEntity.TileEntitySolar;
import com.laidmonkey.common.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSolar extends BlockContainer {
    private Icon[] iconArray = new Icon[2];
    
    public String locationTexture;
    
    public boolean runBreak;

    public BlockSolar(int par1, Material par2, String par3) {
        super(par1, par2);
        this.locationTexture = par3;
        this.setUnlocalizedName(Strings.SOLAR_NAME);
        this.setCreativeTab(Mirrorcraft.tabMirrorcraft);
        this.setHardness(5F);
    }
    

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if(runBreak)
        {
            return 0;
        }
        return par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int xAs, int yAs, int zAs, Random par5Random) {}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int xAs, int yAs, int zAs, int blockChangeId) {
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {}

   
    
    @Override
    public boolean onBlockActivated(World world, int par2, int par3, int par4,
            EntityPlayer entityplayer, int par6, float par7, float par8,
            float par9) {
        
        
        
        // Drop through if the player is sneaking
        if (entityplayer.isSneaking())
            return false;

        world.markBlockForUpdate(par2, par3, par4);
        return true;
        
    }
    
    public void func_94444_j_(World par1World, int par2, int par3, int par4)
    {
        
        BreakControl(par1World, par2, par3, par4);
        
        /*
         * Return true when something is in the way of the solar to get light from the sun.
         */
       
       
        if (!par1World.provider.hasNoSky)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            int i1 = par1World.getSavedLightValue(EnumSkyBlock.Sky, par2, par3, par4) - par1World.skylightSubtracted;
            float f = par1World.getCelestialAngleRadians(1.0F);
            

            if (f < (float)Math.PI)
            {
                f += (0.0F - f) * 0.2F;
                
            }
            else
            {
                f += (((float)Math.PI * 2F) - f) * 0.2F;
            }

            i1 = Math.round((float)i1 * MathHelper.cos(f));

            if (i1 < 0)
            {
                i1 = 0;
            }

            if (i1 > 15)
            {
                i1 = 15;
            }

            if (l != i1)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, i1, 3);
            }
        }
        
            
        
        
    }
    
    public void BreakControl(World world, int xAs,int yAs,int zAs)
    {
        for(int i = 0; i < (world.provider.getHeight() - yAs); i++)
        {
            if (!world.isBlockSolidOnSide(xAs, yAs + i, zAs, ForgeDirection.DOWN))
            {
                System.out.println("No solid block above");
                runBreak = false;
            } else {
                System.out.println("a solid block above");
                runBreak = true;
                break;
            }
        }
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
    public boolean canProvidePower()
    {
        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntitySolar();
    }

    @Override
    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return par1 == 1 ? this.iconArray[0] : this.iconArray[1];
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray[0] = par1IconRegister.registerIcon("MirrorCraft/Mirrorcraft_resources:SteamProducerTop");
        this.iconArray[1] = par1IconRegister.registerIcon("daylightDetector_side");
    }

}
