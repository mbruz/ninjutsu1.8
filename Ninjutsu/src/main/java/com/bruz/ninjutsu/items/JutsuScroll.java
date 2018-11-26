package com.bruz.ninjutsu.items;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.jutsu.Jutsu;
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
		
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You have learned " + _jutsu.getJutsuName()));
		
		stack.stackSize--;
		
		return stack;
	}
	
	public Jutsu getRandomJutsu(EnumChakraRelease release, EnumRank rank) {
		return new HeadHunter();
	}
	
	public void setJutsu(Jutsu j) {
		_jutsu = j;
	}
}
