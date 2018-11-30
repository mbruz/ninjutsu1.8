package com.bruz.ninjutsu.items;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.jutsu.Jutsu;
import com.bruz.ninjutsu.jutsu.earth.EarthJutsuList;
import com.bruz.ninjutsu.jutsu.earth.HeadHunter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public abstract class JutsuScroll extends Item {
	protected Jutsu _jutsu;
	
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		if(!world.isRemote) {
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You have learned " + _jutsu.getJutsuName()));
			
		}
		
		//add jutsu to player list
		
		stack.stackSize--;
		
		//send packet for jutsu list
		
		return stack;
	}
	
	public Jutsu getScrollJutsu(EnumChakraRelease release, EnumRank rank) {
		Jutsu j = null;
		switch(release) 
		{
			case FIRE: //get random fire
				break;
			case WIND: //get random wind
				break;
			case LIGHTNING: //get random Lightning
				break;
			case EARTH: j = EarthJutsuList.getRandomEarthJutsu(rank);
				break;
			case WATER: //get random water
				break;
			default:
				break;
		}
		return j;
	}
	
	public void setJutsu(Jutsu j) {
		_jutsu = j;
	}
	
}
