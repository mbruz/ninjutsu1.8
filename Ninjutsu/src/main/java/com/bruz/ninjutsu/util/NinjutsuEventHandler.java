package com.bruz.ninjutsu.util;

import com.bruz.ninjutsu.Main;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
				NinjaPropertiesPlayer.get(player).replenishChakra();
				NinjaPropertiesPlayer.get(player).replenishStamina();
			}*/
		}
	}
	
	@SubscribeEvent
	public void onNinjaJump(LivingJumpEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			NinjaPropertiesPlayer ninja = NinjaPropertiesPlayer.get(player);
			if(ninja.getChakraMode()) {
				//figure out better multipliers
				if(player.capabilities.isCreativeMode) {
					if(player.isSprinting()) {
						player.addVelocity(player.motionX * 4, player.motionY* 2, player.motionZ * 4);	
					}else {
						player.addVelocity(player.motionX * 1.5, player.motionY* 3, player.motionZ * 1.5);	
					}
				}else if(ninja.consumeStamina(5)){
					if(player.isSprinting()) {
						player.addVelocity(player.motionX * 4, player.motionY* 2, player.motionZ * 4);	
					}else {
						player.addVelocity(player.motionX * 1.5, player.motionY* 3, player.motionZ * 1.5);	
					}
				}

							
			}
		}
	}
	
	@SubscribeEvent
	public void onNinjaJump(LivingFallEvent event) {
		
		if(event.entity instanceof EntityPlayer) {
			NinjaPropertiesPlayer ninja = NinjaPropertiesPlayer.get((EntityPlayer)event.entity);
			if(ninja.getCurrentStamina() > 0) {
				event.setCanceled(true);
			}
		}
	}
}
