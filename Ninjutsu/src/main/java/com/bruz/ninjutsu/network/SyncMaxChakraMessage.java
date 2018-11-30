package com.bruz.ninjutsu.network;

import java.io.IOException;

import com.bruz.ninjutsu.network.AbstractMessage.AbstractClientMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class SyncMaxChakraMessage  extends AbstractClientMessage<SyncMaxChakraMessage>{

	private NBTTagCompound data;
	
	public SyncMaxChakraMessage() {}
	
	public SyncMaxChakraMessage(EntityPlayer player) {
		
	}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		// TODO Auto-generated method stub
		
	}

}
