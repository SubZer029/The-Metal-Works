package sub.themetalworks.nickel.furnace;

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

public class ContainerNickelFurnace extends Container
{
    private TileEntityNickelFurnace nickelFurnace;
    /** Time left for this furnace to burn*/
    public int lastBurnTime;
    /** start time for this fuel */
    public int lastItemBurnTime;
    /** Time left before cooked */
    public int lastCookTime;

    public ContainerNickelFurnace(InventoryPlayer inventory, TileEntityNickelFurnace tileentity)
    {
        this.nickelFurnace = tileentity;
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
        icrafting.sendProgressBarUpdate(this, 0, this.nickelFurnace.cookTime);
        icrafting.sendProgressBarUpdate(this, 1, this.nickelFurnace.burnTime);
        icrafting.sendProgressBarUpdate(this, 2, this.nickelFurnace.currentItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.nickelFurnace.cookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.nickelFurnace.cookTime);
            }

            if (this.lastBurnTime != this.nickelFurnace.burnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.nickelFurnace.burnTime);
            }

            if (this.lastItemBurnTime != this.nickelFurnace.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.nickelFurnace.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.nickelFurnace.cookTime;
        this.lastBurnTime = this.nickelFurnace.burnTime;
        this.lastItemBurnTime = this.nickelFurnace.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue)
    {
        if (slot == 0)
        {
            this.nickelFurnace.cookTime = newValue;
        }

        if (slot == 1)
        {
            this.nickelFurnace.burnTime = newValue;
        }

        if (slot == 2)
        {
            this.nickelFurnace.currentItemBurnTime = newValue;
        }
    }
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        return null;
    }
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.nickelFurnace.isUseableByPlayer(entityplayer);
    }
}
