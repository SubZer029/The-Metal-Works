package sub.themetalworks.nickel.furnace;

import java.util.Random;

import sub.themetalworks.TheMetalWorksHub;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class NickelFurnace extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon iconFront;

    private static boolean keepInventory;

    private final boolean isActive;

    public NickelFurnace(int id, boolean isActive)
    {
        super(id, Material.rock);
        this.isActive = isActive;
    }
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(TheMetalWorksHub.modid + ":" + "nickel_furnace_side");
        this.iconFront = iconRegister.registerIcon(TheMetalWorksHub.modid + ":" + (this.isActive ? "nickel_furnace_front_on" : "nickel_furnace_front_off"));
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
        return side == metadata ? this.iconFront : this.blockIcon;
    }

    public int idDropped(int par1, Random random, int par3)
    {
        return TheMetalWorksHub.NickelFurnaceIdle.blockID;
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            int l = world.getBlockId(x, y, z - 1);
            int il = world.getBlockId(x, y, z + 1);
            int jl = world.getBlockId(x - 1, y, z);
            int kl = world.getBlockId(x + 1, y, z);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[il])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[il] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[kl] && !Block.opaqueCubeLookup[jl])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[jl] && !Block.opaqueCubeLookup[kl])
            {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            FMLNetworkHandler.openGui(player, TheMetalWorksHub.instance, TheMetalWorksHub.guiIdNickelFurnace, world, x, y, z);
        }

        return true;
    }
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityNickelFurnace();
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemstack)
    {
        int l = MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemstack.hasDisplayName())
        {
            ((TileEntityNickelFurnace)world.getBlockTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
        }
    }
    public static void updateNickelFurnaceBlockState(boolean active, World worldObj, int xCoord, int yCoord, int yCoord2, int zCoord)
    {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileentity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;

        if (active)
        {
            worldObj.setBlock(xCoord, yCoord, zCoord, TheMetalWorksHub.NickelFurnaceActive.blockID);
        }
        else
        {
            worldObj.setBlock(xCoord, yCoord, zCoord, TheMetalWorksHub.NickelFurnaceIdle.blockID);
        }

        keepInventory = false;
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            worldObj.setBlockTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int i)
    {
        return Container.calcRedstoneFromInventory((IInventory)world.getBlockTileEntity(x, y, z));
    }

    public int idPicked(World world, int x, int y, int z)
    {
        return TheMetalWorksHub.NickelFurnaceIdle.blockID;
    }
}
