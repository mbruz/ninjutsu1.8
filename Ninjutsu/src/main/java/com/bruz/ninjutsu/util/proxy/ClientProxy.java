package com.bruz.ninjutsu.util.proxy;

import com.bruz.ninjutsu.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {

	private final Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		// Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
		// your packets will not work as expected in single player because the
		// integrated server also uses this proxy

		// Solution is to double-check side before returning the player:
		Main.logger.info("Retrieving player from ClientProxy for message on side " + ctx.side);
		return (ctx.side.isClient() ? mc.thePlayer : super.getPlayerEntity(ctx));
	}

	@Override
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return (ctx.side.isClient() ? mc : super.getThreadFromContext(ctx));
	}
}
