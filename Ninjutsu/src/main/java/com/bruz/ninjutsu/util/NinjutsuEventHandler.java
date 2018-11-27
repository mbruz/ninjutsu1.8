package com.bruz.ninjutsu.util;

import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NinjutsuEventHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if(event.entity instanceof EntityPlayer && NinjaPropertiesPlayer.get((EntityPlayer) event.entity) == null) 
		{
			NinjaPropertiesPlayer.register((EntityPlayer) event.entity);
		}
	}
	
}
