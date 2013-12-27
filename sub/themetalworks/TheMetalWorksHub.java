package sub.themetalworks;

import sub.themetalworks.blocks.MetalBlocks;
import sub.themetalworks.blocks.MetalOres;
import sub.themetalworks.blocks.MetalWorksWorldGenerator;
import sub.themetalworks.combat.NickelArmor;
import sub.themetalworks.combat.MetalSwords;
import sub.themetalworks.items.MetalIngots;
import sub.themetalworks.nickel.furnace.NickelGuiHandler;
import sub.themetalworks.nickel.furnace.NickelFurnace;
import sub.themetalworks.nickel.furnace.TileEntityNickelFurnace;
import sub.themetalworks.titanium.furnace.TileEntityTitaniumFurnace;
//import sub.themetalworks.titanium.furnace.TileEntityTitaniumFurnace;
import sub.themetalworks.titanium.furnace.TitaniumFurnace;
import sub.themetalworks.titanium.furnace.TitaniumGuiHandler;
import sub.themetalworks.tools.MetalAxes;
import sub.themetalworks.tools.MetalHoes;
import sub.themetalworks.tools.MetalPickaxes;
import sub.themetalworks.tools.MetalSpades;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import  net.minecraftforge.common.EnumHelper;

@Mod(modid = "metals", name = "The Metal Works", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false) /*, clientPacketHandlerSpec = @SidedPacketHandler(channels = {"metals"}, packetHandler = net.minecraft.src.SSPPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"metals" }, packetHandler = net.minecraft.src.SMPPacketHandler.class))
*/
public class TheMetalWorksHub
{
    public static final String modid = "metals";
    @Instance("metals")
    public static TheMetalWorksHub instance;
    public static TheMetalWorksHub instance1 = new TheMetalWorksHub();;

    
    
    /** Creative Tabs */
    public static CreativeTabs tabMetals = new CreativeTabs("tabMetals")
    {
        public ItemStack getIconItemStack()
        {
            return new ItemStack(NickelIngot, 1, 0);
        }
    };
   
    
    
    /**Items */
    public static Item NickelIngot;
    public static Item TitaniumIngot;
    
    
    /**Tools/Weapons */
    public static Item NickelSword;
    public static Item NickelPickaxe;
    public static Item NickelAxe;
    public static Item NickelSpade;
    public static Item NickelHoe;
    public static Item TitaniumSword;
    public static Item TitaniumPickaxe;
    public static Item TitaniumAxe;
    public static Item TitaniumSpade;
    public static Item TitaniumHoe;
    
    
    /**Armor */
    public static Item NickelHelmet;
    public static Item NickelBody;
    public static Item NickelPants;
    public static Item NickelBoots;
   
    /**Blocks */
    public static Block NickelOre;
    public static Block NickelBlock;
    public static Block TitaniumBlock;
    public static Block TitaniumOre;
    
    /**Furnaces */
    public static Block NickelFurnaceIdle;
    public static Block NickelFurnaceActive;
    public static Block TitaniumFurnaceIdle;
    public static Block TitaniumFurnaceActive;
    
    /** Tool Materials */
    static EnumToolMaterial EnumToolMaterialNickel= EnumHelper.addToolMaterial("NICKEL", 1, 200, 5.0F, 1.5F, 15);
    static EnumToolMaterial EnumToolMaterialTitanium= EnumHelper.addToolMaterial("TITANIUM", 1, 200, 5.0F, 1.5F, 15);
   
    
    /** Armor Materials */
    static EnumArmorMaterial EnumArmorMaterialNickel= EnumHelper.addArmorMaterial("NICKEL", 66, new int[] {1, 5, 3, 1}, 20);
    
    
    
    /** GUI */
    public static final int guiIdNickelFurnace = 0;
    public static final int guiIdTitaniumFurnace = 1;

    

    
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	/**Items */
    	NickelIngot = new MetalIngots(2013).setUnlocalizedName("NickelIngot")
        .setCreativeTab(this.tabMetals).setTextureName("metals:Nickel");
    	TitaniumIngot = new MetalIngots(2023).setUnlocalizedName("TitaniumIngot")
    	.setCreativeTab(this.tabMetals).setTextureName("metals:titanium");
       
        /**Swords  */
        NickelSword = new MetalSwords(2014, EnumToolMaterialNickel)
        .setUnlocalizedName("NickelSword").setCreativeTab(tabMetals).setTextureName("metals:nickel_sword");
        
        TitaniumSword = new MetalSwords(2024, EnumToolMaterialTitanium)
        .setUnlocalizedName("TitaniumSword").setCreativeTab(tabMetals).setTextureName("metals:titanium_sword");
        
        /** Pickaxes */
        NickelPickaxe = new MetalPickaxes(2015, EnumToolMaterialNickel)
        .setUnlocalizedName("NickelPickaxe").setCreativeTab(tabMetals).setTextureName("metals:nickel_pickaxe");
      
        TitaniumPickaxe = new MetalPickaxes(2025, EnumToolMaterialTitanium)
        .setUnlocalizedName("TitaniumPickaxe").setCreativeTab(tabMetals).setTextureName("metals:titanium_pickaxe");
        
        /** Axes  */ 
        NickelAxe = new MetalAxes(2016, EnumToolMaterialNickel)
        .setUnlocalizedName("NickelAxe").setCreativeTab(tabMetals).setTextureName("metals:nickel_axe");
       
        TitaniumAxe = new MetalAxes(2026, EnumToolMaterialTitanium)
        .setUnlocalizedName("TitaniumAxe").setCreativeTab(tabMetals).setTextureName("metals:titanium_axe");
        
        /** Spades */
        NickelSpade = new MetalSpades(2017, EnumToolMaterialNickel)
        .setUnlocalizedName("NickelSpade").setCreativeTab(tabMetals).setTextureName("metals:nickel_spade");
       
        TitaniumSpade = new MetalSpades(2027, EnumToolMaterialTitanium)
        .setUnlocalizedName("TitaniumSpade").setCreativeTab(tabMetals).setTextureName("metals:titanium_spade");
        
        /** Hoes */ 
        NickelHoe = new MetalHoes(2018, EnumToolMaterialNickel)
        .setUnlocalizedName("NickelHoe").setCreativeTab(tabMetals).setTextureName("metals:nickel_hoe");
        
        TitaniumHoe = new MetalHoes(2028, EnumToolMaterialTitanium)
        .setUnlocalizedName("TitaniumHoe").setCreativeTab(tabMetals).setTextureName("metals:titanium_hoe");
       
        /** Helmets */
       NickelHelmet = new NickelArmor(2019, EnumArmorMaterialNickel, ModLoader.addArmor("nickel"), 0).setUnlocalizedName("NickelHelmet")
        		.setCreativeTab(tabMetals).setTextureName("metals:nickel_helmet");
       
       /** ChestPlates  */
       NickelBody = new NickelArmor(2020, EnumArmorMaterialNickel, ModLoader.addArmor("nickel"), 1).setUnlocalizedName("NickelBody")
        	.setCreativeTab(tabMetals).setTextureName("metals:nickel_body");
       
       /** Pants */
        NickelPants = new NickelArmor(2021, EnumArmorMaterialNickel ,ModLoader.addArmor("nickel"), 2).setUnlocalizedName("NickelPants")
        		.setCreativeTab(tabMetals).setTextureName("metals:nickel_pants");
       
        /** Boots */
        NickelBoots = new NickelArmor(2022, EnumArmorMaterialNickel, ModLoader.addArmor("nickel"), 3).setUnlocalizedName("NickelBoots")
        		.setCreativeTab(tabMetals).setTextureName("metals:nickel_boots");
        
        /**Blocks */
        NickelBlock = new MetalBlocks(500, Material.iron)
        .setUnlocalizedName("NickelBlock").setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
        .setCreativeTab(tabMetals).setTextureName("metals:nickel_block");
       
        TitaniumBlock = new MetalBlocks(504, Material.iron)
        .setUnlocalizedName("TitaniumBlock").setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
        .setCreativeTab(tabMetals).setTextureName("metals:titanium_block");
        
        /** Ores */
        NickelOre = new MetalOres(501, Material.rock)
        .setUnlocalizedName("NickelOre").setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
        .setCreativeTab(tabMetals).setTextureName("metals:nickel_ore");
        
       TitaniumOre = new MetalOres(505, Material.rock)
        .setUnlocalizedName("TitaniumOre").setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
        .setCreativeTab(tabMetals).setTextureName("metals:titanium_ore");
        
        /** Idle Furnaces */
        NickelFurnaceIdle = new NickelFurnace(502, false).setUnlocalizedName("NickelFurnaceIdle")
        .setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
        .setCreativeTab(tabMetals);
        
        TitaniumFurnaceIdle = new TitaniumFurnace(506, false).setUnlocalizedName("NickelFurnaceIdle")
                .setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
                .setCreativeTab(tabMetals);
       
        /** Active Furnaces */
        NickelFurnaceActive = new NickelFurnace(503, true).setUnlocalizedName("NickelFurnaceActive")
        .setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
        .setLightValue(0.9F);
       
       TitaniumFurnaceActive = new TitaniumFurnace(507, true).setUnlocalizedName("NickelFurnaceActive")
                .setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
                .setLightValue(0.9F);
    }
   
    
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
       
    	
    	
    	
    	
    	/**Items */
    	LanguageRegistry.addName(NickelIngot, "Nickel Ingot");
    	LanguageRegistry.addName(TitaniumIngot, "Titanium Ingot");
        
        
    	/**Weapons/Tools */
        LanguageRegistry.addName(NickelSword, "Nickel Sword");
        LanguageRegistry.addName(NickelPickaxe, "Nickel Pickaxe");
        LanguageRegistry.addName(NickelAxe, "Nickel Axe");
        LanguageRegistry.addName(NickelSpade, "Nickel Shovel");
        LanguageRegistry.addName(NickelHoe, "Nickel Hoe");
        LanguageRegistry.addName(TitaniumSword, "Titanium Sword");
        LanguageRegistry.addName(TitaniumPickaxe, "Titanium Pickaxe");
        LanguageRegistry.addName(TitaniumAxe, "Titanium Axe");
        LanguageRegistry.addName(TitaniumSpade, "Titanium Shovel");
        LanguageRegistry.addName(TitaniumHoe, "Titanium Hoe");
        
        
        
        /**Armor */
        LanguageRegistry.addName(NickelHelmet, "Nickel Helmet");
        LanguageRegistry.addName(NickelBody, "Nickel Chestplate");
        LanguageRegistry.addName(NickelPants, "Nickel Leggings");
        LanguageRegistry.addName(NickelBoots, "Nickel Boots");
       
        
        /**Blocks */
        LanguageRegistry.addName(NickelBlock, "Block Of Nickel");
        LanguageRegistry.addName(TitaniumBlock, "Block Of Titanium");
        
        /**Ore */
        LanguageRegistry.addName(NickelOre, "Nickel Ore");
        LanguageRegistry.addName(TitaniumOre, "Titanium Ore");
        
        /**Furnace */
        LanguageRegistry.addName(NickelFurnaceIdle, "Nickel Furnace");
        LanguageRegistry.addName(TitaniumFurnaceIdle, "Titanium Furnace");
       
        
        /** Gui */
        LanguageRegistry.instance().addStringLocalization("container.nickelFurnace", "Nickel Furnace");
        new NickelGuiHandler();
        
        LanguageRegistry.instance().addStringLocalization("container.titaniumFurnace", "Titanium Furnace");
        //new TitaniumGuiHandler();
        
       
        
        /** Block Registration Stuff */
        MinecraftForge.setBlockHarvestLevel(NickelOre, "pickaxe", 2);
        GameRegistry.registerTileEntity(TileEntityNickelFurnace.class, "tileEntityNickleFurnace");
        GameRegistry.registerTileEntity(TileEntityTitaniumFurnace.class, "tileEntityTitaniumFurnace");
        GameRegistry.registerBlock(NickelBlock, "NickelBlock");
        GameRegistry.registerBlock(NickelOre, "NickelOre");
        GameRegistry.registerBlock(NickelFurnaceIdle, "NickelFurnaceIdle");
        GameRegistry.registerBlock(NickelFurnaceActive, "NickelFurnaceActive");
        GameRegistry.registerBlock(TitaniumFurnaceIdle, "TitaniumFurnaceIdle");
        GameRegistry.registerBlock(TitaniumFurnaceActive, "TitaniumFurnaceActive");
        GameRegistry.registerBlock(TitaniumBlock, "TitaniumBlock");
        GameRegistry.registerBlock(TitaniumOre, "TitaniumOre");
        GameRegistry.registerWorldGenerator(new MetalWorksWorldGenerator());
       
        /** Crafting */
        ItemStack NickelStack = new ItemStack(NickelIngot);
        ItemStack TitaniumStack = new ItemStack(TitaniumIngot);
        ItemStack stickStack = new ItemStack(Item.stick);
        GameRegistry.addSmelting(NickelOre.blockID, new ItemStack(NickelIngot), 0.1f);
        GameRegistry.addSmelting(TitaniumOre.blockID, new ItemStack(TitaniumIngot), 0.1f);
        
        
        GameRegistry.addRecipe(new ItemStack(NickelSword, 1),
                               " N ", " N ", " I ", 'N', NickelStack, 'I', stickStack);
        
        
        GameRegistry.addRecipe(new ItemStack(NickelPickaxe, 1),
                               "NNN", " I ", " I ", 'N', NickelStack, 'I', stickStack);
        
        
        GameRegistry.addRecipe(new ItemStack(NickelAxe, 1),
                               "NN ", "NI ", " I ", 'N', NickelStack, 'I', stickStack);
        
        
        GameRegistry.addRecipe(new ItemStack(NickelSpade, 1),
                               " N ", " I ", " I ", 'N', NickelStack, 'I', stickStack);
        
        
        GameRegistry.addRecipe(new ItemStack(NickelHoe, 1),
                               "NN ", " I ", " I ", 'N', NickelStack, 'I', stickStack);
        
        
        GameRegistry.addRecipe(new ItemStack(NickelFurnaceIdle, 1),
                               "NNN", "N N", "NNN", 'N', NickelStack);
        
        
        GameRegistry.addShapelessRecipe(new ItemStack(NickelIngot, 9), new ItemStack(NickelBlock));
        
        
        GameRegistry.addShapelessRecipe(new ItemStack(NickelBlock, 1),
                                        NickelIngot, NickelIngot, NickelIngot,
                                        NickelIngot, NickelIngot, NickelIngot,
                                        NickelIngot, NickelIngot, NickelIngot);
        
        
GameRegistry.addRecipe(new ItemStack(TitaniumSword, 1),
              	" N ", " N ", " I ", 'N', TitaniumStack, 'I', stickStack);


GameRegistry.addRecipe(new ItemStack(TitaniumPickaxe, 1),
                "NNN", " I ", " I ", 'N', TitaniumStack, 'I', stickStack);


GameRegistry.addRecipe(new ItemStack(TitaniumAxe, 1),
                "NN ", "NI ", " I ", 'N', TitaniumStack, 'I', stickStack);


GameRegistry.addRecipe(new ItemStack(TitaniumSpade, 1),
                " N ", " I ", " I ", 'N', TitaniumStack, 'I', stickStack);


GameRegistry.addRecipe(new ItemStack(TitaniumHoe, 1),
                "NN ", " I ", " I ", 'N', TitaniumStack, 'I', stickStack);


GameRegistry.addShapelessRecipe(new ItemStack(TitaniumIngot, 9), new ItemStack(TitaniumBlock));


GameRegistry.addShapelessRecipe(new ItemStack(TitaniumBlock, 1),
                         TitaniumIngot, TitaniumIngot, TitaniumIngot,
                         TitaniumIngot, TitaniumIngot, TitaniumIngot,
                         TitaniumIngot, TitaniumIngot, TitaniumIngot);
    }
}
