package com.bruz.ninjutsu.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class PlayerMessageUtil {

	public static void Debug(EntityPlayer player, String message) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + message));
		 
	}
	
	public static void Blue(EntityPlayer player, String message) {
		player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + message));
		 
	}
}
