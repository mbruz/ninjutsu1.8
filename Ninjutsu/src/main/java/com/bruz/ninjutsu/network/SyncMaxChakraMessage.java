package com.bruz.ninjutsu.network;

import java.io.IOException;

import com.bruz.ninjutsu.Main;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.network.AbstractMessage.AbstractClientMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class SyncMaxChakraMessage  extends AbstractClientMessage<SyncMaxChakraMessage>{

	private NBTTagCompound data;
	
	public SyncMaxChakraMessage() {}
	
	public SyncMaxChakraMessage(EntityPlayer player) {
		data = new NBTTagCompound();
		NinjaPropertiesPlayer.get(player).saveMaxChakra(data);
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
		Main.logger.info("Synchronizing max chakra data on CLIENT");
		NinjaPropertiesPlayer.get(player).loadMaxChakra(data);
		
	}

}
