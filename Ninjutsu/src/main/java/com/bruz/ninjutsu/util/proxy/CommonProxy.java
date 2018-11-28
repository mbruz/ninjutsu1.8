package com.bruz.ninjutsu.util.proxy;

import com.bruz.ninjutsu.Main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {

	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		Main.logger.info("Retrieving player from CommonProxy for message on side " + ctx.side);
		return ctx.getServerHandler().playerEntity;
	}
	
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity.getServerForPlayer();
	}
}
