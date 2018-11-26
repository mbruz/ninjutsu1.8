package com.bruz.ninjutsu.items;

import com.bruz.ninjutsu.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClosedEarthScroll extends Item {

	public ClosedEarthScroll() {
		super();
		this.setUnlocalizedName("closedEarthScroll");
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		//check criteria for unlocking
		
		//random value out of 1000 for determining rank
		
		Item randomEarthScroll = GameRegistry.findItem(Main.MODID, "earthScrollRankD");
		stack.setItem(randomEarthScroll);
		
		return stack;
	}
}
