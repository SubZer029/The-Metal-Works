package sub.themetalworks.titanium.furnace;

import sub.themetalworks.TheMetalWorksHub;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class TitaniumGuiHandler implements IGuiHandler
{
    public TitaniumGuiHandler()
    {
        NetworkRegistry.instance().registerGuiHandler(TheMetalWorksHub.instance1, this);
        
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity entity = world.getBlockTileEntity(x, y, z);

        if (entity != null)
        {
            switch (ID)
            {
                case TheMetalWorksHub.guiIdTitaniumFurnace:
                    if (entity instanceof TileEntityTitaniumFurnace);

                    return new ContainerTitaniumFurnace(player.inventory, (TileEntityTitaniumFurnace) entity);
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
                case TheMetalWorksHub.guiIdTitaniumFurnace:
                    if (entity instanceof TileEntityTitaniumFurnace);

                    return new GuiTitaniumFurnace(player.inventory, (TileEntityTitaniumFurnace) entity);
            }
        }

        return null;
    }
    
}
