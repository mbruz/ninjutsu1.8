package com.bruz.ninjutsu;

import com.bruz.ninjutsu.gui.GuiChakraBar;
import com.bruz.ninjutsu.gui.GuiStaminaBar;
import com.bruz.ninjutsu.items.ClosedEarthScroll;
import com.bruz.ninjutsu.items.EarthScrollRankD;
import com.bruz.ninjutsu.jutsu.JutsuList;
import com.bruz.ninjutsu.jutsu.earth.EarthJutsuList;
import com.bruz.ninjutsu.jutsu.earth.ExplodeBlock;
import com.bruz.ninjutsu.jutsu.earth.HeadHunter;
import com.bruz.ninjutsu.util.NinjutsuEventHandler;
import com.bruz.ninjutsu.util.PacketDispatcher;
import com.bruz.ninjutsu.util.keybinding.NinjutsuKeyBinds;
import com.bruz.ninjutsu.util.keybinding.KeyInputHandler;
import com.bruz.ninjutsu.util.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {

	public static final String MODID = "ninjutsu";
	public static final String VERSION = "1.0";
	
	@Mod.Instance(MODID)
	public static Main instance;

	@SidedProxy(clientSide = "com.bruz.ninjutsu.util.proxy.ClientProxy", serverSide = "com.bruz.ninjutsu.util.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final Logger logger = LogManager.getLogger(MODID);
	public static final Random RANDOM = new Random();
	
	//items
	public static Item closedEarthScroll;
	public static Item earthScrollRankD;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//Register Network Packets
		PacketDispatcher.registerPackets();
		
		//Register Key Bindings
		NinjutsuKeyBinds.register();
		MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
		
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		JutsuList.init();
		
		MinecraftForge.EVENT_BUS.register(new NinjutsuEventHandler());
		MinecraftForge.EVENT_BUS.register(new HeadHunter());
		MinecraftForge.EVENT_BUS.register(new ExplodeBlock());
		
		//REGISTER ITEMS
		earthScrollRankD = new EarthScrollRankD();
		GameRegistry.registerItem(earthScrollRankD, "earthScrollRankD");
		closedEarthScroll = new ClosedEarthScroll();
		GameRegistry.registerItem(closedEarthScroll, "closedEarthScroll");
		
		//REGISTER ITEM MODEL
		registerItemHelper("earthScrollRankD", "ninjutsu:earthScrollRankD");
		registerItemHelper("closedEarthScroll", "ninjutsu:closedEarthScroll");
		
/*		Item earthScrollRankDItem = GameRegistry.findItem(MODID, "earthScrollRankD");
		ModelResourceLocation earthScrollRankDModel = new ModelResourceLocation("ninjutsu:earthScrollRankD", "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(earthScrollRankDItem, 0, earthScrollRankDModel);*/
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(new GuiChakraBar(Minecraft.getMinecraft()));
			MinecraftForge.EVENT_BUS.register(new GuiStaminaBar(Minecraft.getMinecraft()));
		}		
	}
	
	
	private void registerItemHelper(String itemName, String itemResourceLocation) {
		Item item = GameRegistry.findItem(MODID, itemName);
		ModelResourceLocation model = new ModelResourceLocation(itemResourceLocation, "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, model);
	}
}
