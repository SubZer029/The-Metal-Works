package sub.themetalworks.combat;

import sub.themetalworks.TheMetalWorksHub;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class NickelArmor extends ItemArmor {

	public NickelArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		
	}
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
	{
	if(itemID == TheMetalWorksHub.NickelHelmet.itemID || itemID == TheMetalWorksHub.NickelBody.itemID || itemID == TheMetalWorksHub.NickelBoots.itemID)
	{
	return "metals:textures/armor/nickel_layer_1.png";
	}
	if(itemID == TheMetalWorksHub.NickelPants.itemID)
	{
	return "metals:textures/armor/nickel_layer_2.png";
	}
	else return null;
	
	}
	}




