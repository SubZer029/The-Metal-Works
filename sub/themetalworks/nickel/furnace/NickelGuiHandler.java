package sub.themetalworks.nickel.furnace;

import sub.themetalworks.TheMetalWorksHub;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class NickelGuiHandler implements IGuiHandler
{
    public NickelGuiHandler()
    {
        NetworkRegistry.instance().registerGuiHandler(TheMetalWorksHub.instance, this);
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity entity = world.getBlockTileEntity(x, y, z);

        if (entity != null)
        {
            switch (ID)
            {
                case TheMetalWorksHub.guiIdNickelFurnace:
                    if (entity instanceof TileEntityNickelFurnace);

                    return new ContainerNickelFurnace(player.inventory, (TileEntityNickelFurnace) entity);
            }
        }

        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity entity = world.getBlockTileEntity(x, y, z);

        if (entity != null)
        {
            switch (ID)
            {
                case TheMetalWorksHub.guiIdNickelFurnace:
                    if (entity instanceof TileEntityNickelFurnace);

                    return new GuiNickelFurnace(player.inventory, (TileEntityNickelFurnace) entity);
            }
        }

        return null;
    }
    
}
