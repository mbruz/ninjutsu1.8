package com.bruz.ninjutsu.network;

import java.io.IOException;

import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.network.AbstractMessage.AbstractClientMessage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;

public class CastJutsuMessage extends AbstractClientMessage<CastJutsuMessage>  {

	private NBTTagCompound data;

	public CastJutsuMessage() {}
	
	public CastJutsuMessage(EntityPlayer player, Entity target) {
		data = new NBTTagCompound();
		NinjaPropertiesPlayer.get(player).saveLoadedJutsu(data);
	}
	
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		data = buffer.readNBTTagCompoundFromBuffer();
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeNBTTagCompoundToBuffer(data);
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		player.addChatMessage(new ChatComponentText(
				EnumChatFormatting.WHITE + "Server Side processing"));
		NinjaPropertiesPlayer ninja = NinjaPropertiesPlayer.get(player);
		//ninja.getLoadedJutsu().castJutsu(ninja, target);
			
		
	}

}
