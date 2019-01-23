package com.bruz.ninjutsu.network;

import java.io.IOException;
import java.util.UUID;

import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.network.AbstractMessage.AbstractClientMessage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;

public class CastJutsuMessage extends AbstractClientMessage<CastJutsuMessage>  {

	private int _target;
	private BlockPos _block;

	public CastJutsuMessage() {}
	
	public CastJutsuMessage(EntityPlayer player, int target) {
		_target = target;
	}
	public CastJutsuMessage(EntityPlayer player, BlockPos block) {
		_block = block;
	}
	
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		if(buffer.readInt() > 0) {
			_target = buffer.readInt();
		}else {
			_block = buffer.readBlockPos();
		}
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		if(_target > 0) {
			buffer.writeInt(_target);
		}
		else {
			buffer.writeBlockPos(_block);
		}
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		player.addChatMessage(new ChatComponentText(
				EnumChatFormatting.WHITE + "Server Side processing"));
		NinjaPropertiesPlayer ninja = NinjaPropertiesPlayer.get(player);
		ninja.getLoadedJutsu().castJutsu(ninja, _target, _block);
			
		
	}

}
