package com.bruz.ninjutsu.network;

import java.io.IOException;

import com.bruz.ninjutsu.Main;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.network.AbstractMessage.AbstractClientMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class SyncNinjaPropsMessage extends AbstractClientMessage<SyncNinjaPropsMessage> {

	private NBTTagCompound data;

	public SyncNinjaPropsMessage() {}
	
	public SyncNinjaPropsMessage(EntityPlayer player) {
		data = new NBTTagCompound();
		NinjaPropertiesPlayer.get(player).saveNBTData(data);
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
		// now we can just load the NBTTagCompound data directly; one and done, folks
		Main.logger.info("Synchronizing extended properties data on CLIENT");
		NinjaPropertiesPlayer.get(player).loadNBTData(data);		
	}

}
