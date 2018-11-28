package com.bruz.ninjutsu.util;

import com.bruz.ninjutsu.Main;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NinjutsuEventHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if(event.entity instanceof EntityPlayer && NinjaPropertiesPlayer.get((EntityPlayer) event.entity) == null) 
		{
			NinjaPropertiesPlayer.register((EntityPlayer) event.entity);
		}
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
			NinjaPropertiesPlayer.get((EntityPlayer) event.entity).sync();
	}
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		Main.logger.info("Cloning player extended properties");
		NinjaPropertiesPlayer.get(event.entityPlayer).copy(NinjaPropertiesPlayer.get(event.original));
	}
	
}
