package sub.themetalworks.titanium.furnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerTitaniumFurnace extends Container
{
    private TileEntityTitaniumFurnace TitaniumFurnace;
    /** Time left for this furnace to burn*/
    public int lastBurnTime;
    /** start time for this fuel */
    public int lastItemBurnTime;
    /** Time left before cooked */
    public int lastCookTime;

    public ContainerTitaniumFurnace(InventoryPlayer inventory, TileEntityTitaniumFurnace tileentity)
    {
        this.TitaniumFurnace = tileentity;
        this.addSlotToContainer(new Slot(tileentity, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileentity, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 2, 116, 36));

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9 , 8 + j * 18,  84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.TitaniumFurnace.cookTime);
        icrafting.sendProgressBarUpdate(this, 1, this.TitaniumFurnace.burnTime);
        icrafting.sendProgressBarUpdate(this, 2, this.TitaniumFurnace.currentItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.TitaniumFurnace.cookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.TitaniumFurnace.cookTime);
            }

            if (this.lastBurnTime != this.TitaniumFurnace.burnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.TitaniumFurnace.burnTime);
            }

            if (this.lastItemBurnTime != this.TitaniumFurnace.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.TitaniumFurnace.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.TitaniumFurnace.cookTime;
        this.lastBurnTime = this.TitaniumFurnace.burnTime;
        this.lastItemBurnTime = this.TitaniumFurnace.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue)
    {
        if (slot == 0)
        {
            this.TitaniumFurnace.cookTime = newValue;
        }

        if (slot == 1)
        {
            this.TitaniumFurnace.burnTime = newValue;
        }

        if (slot == 2)
        {
            this.TitaniumFurnace.currentItemBurnTime = newValue;
        }
    }
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        return null;
    }
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.TitaniumFurnace.isUseableByPlayer(entityplayer);
    }
}
