package com.bruz.ninjutsu;

import com.bruz.ninjutsu.items.ClosedEarthScroll;
import com.bruz.ninjutsu.items.EarthScrollRankD;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {

	public static final String MODID = "ninjutsu";
	public static final String VERSION = "1.0";
	
	//items
	public static Item closedEarthScroll;
	public static Item earthScrollRankD;
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
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
	
	private void registerItemHelper(String itemName, String itemResourceLocation) {
		Item item = GameRegistry.findItem(MODID, itemName);
		ModelResourceLocation model = new ModelResourceLocation(itemResourceLocation, "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, model);
	}
}
