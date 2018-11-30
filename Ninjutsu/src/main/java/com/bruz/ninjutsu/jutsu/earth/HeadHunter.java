package com.bruz.ninjutsu.jutsu.earth;

import java.util.ArrayList;
import java.util.List;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.jutsu.Jutsu;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class HeadHunter extends Jutsu implements ICommand {

	private List aliases = new ArrayList();
	private EntityPlayer target;
	
	public HeadHunter() {
		super(EnumChakraRelease.EARTH, "Earth Style: Head Hunter Jutsu", EnumRank.D, 10);
		aliases.add("earth headhunter");
		aliases.add("e headhunter");
		_handSignRequirement = new EnumHandSign[]{EnumHandSign.SNAKE};
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return null;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/earth headhunter <name of player>";
	}

	@Override
	public List<String> getCommandAliases() {
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
		
		if(args.length != 1) {
			sendErrorMessage(sender, "Invalid number of arguments");
		}
		
	}

	private void sendErrorMessage(ICommandSender sender, String message) {
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + message));
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
