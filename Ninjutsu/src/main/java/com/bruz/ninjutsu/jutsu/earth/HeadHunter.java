package com.bruz.ninjutsu.jutsu.earth;

import java.util.ArrayList;
import java.util.List;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumJutsu;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.jutsu.IJutsu;
import com.bruz.ninjutsu.jutsu.Jutsu;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class HeadHunter extends Jutsu implements IJutsu {

	private EntityPlayer target;
	
	public HeadHunter() {		
		super(EnumChakraRelease.EARTH, "Earth Style: Head Hunter Jutsu", EnumRank.D, 10, new EnumHandSign[] {EnumHandSign.SNAKE});
		JUTSUID = EnumJutsu.HeadHunter;
	}

	@Override
	public void castJutsu(NinjaPropertiesPlayer player) {
		World world = player.entity.worldObj;
			
		if(!world.isRemote) {
			
		}
		
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
				EnumChatFormatting.WHITE + this._name));
	}
}
