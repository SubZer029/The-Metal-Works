package sub.themetalworks.nickel.furnace;

import org.lwjgl.opengl.GL11;

import sub.themetalworks.TheMetalWorksHub;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiNickelFurnace extends GuiContainer
{
    public static final ResourceLocation texture = new ResourceLocation(TheMetalWorksHub.modid, "textures/gui/furnace.png");

    public TileEntityNickelFurnace nickelFurnace;

    public GuiNickelFurnace(InventoryPlayer inventoryPlayer, TileEntityNickelFurnace entity)
    {
        super(new ContainerNickelFurnace(inventoryPlayer, entity));
        this.nickelFurnace = entity;
        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String name = this.nickelFurnace.isInvNameLocalized() ? this.nickelFurnace.getInvName() : I18n.getString(this.nickelFurnace.getInvName()) ;
        this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    public void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (this.nickelFurnace.isBurning())
        {
            int k = this.nickelFurnace.getBurnTimeRemainingScaled(12);
            drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - k, 176, 12 - k, 14, k + 2);
        }

        int k = this.nickelFurnace.getCookProgressScaled(24);
        drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, k + 1, 16);
    }
}
