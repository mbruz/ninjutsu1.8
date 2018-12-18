package com.bruz.ninjutsu.util;

import com.bruz.ninjutsu.Main;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
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
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			NinjaPropertiesPlayer ninja = NinjaPropertiesPlayer.get(player);
			ninja.onUpdate();
/*			if (player.isPlayerFullyAsleep()) {
				player.addChatMessage(new ChatComponentText("After a full night's rest, you feel refreshed!"));
				ExtendedPlayer.get(player).replenishMana();
			}*/
			if(!event.entity.worldObj.isRemote) {
				if(ninja == null)
					return;
			}
		}
	}
}
