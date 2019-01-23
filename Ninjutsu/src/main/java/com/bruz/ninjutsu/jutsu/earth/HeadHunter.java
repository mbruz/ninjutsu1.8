package com.bruz.ninjutsu.jutsu.earth;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumJutsu;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.jutsu.IJutsu;
import com.bruz.ninjutsu.jutsu.Jutsu;
import com.bruz.ninjutsu.util.PlayerMessageUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HeadHunter extends Jutsu implements IJutsu {

	private EntityPlayer target;
	
	public HeadHunter() {		
		super(EnumChakraRelease.EARTH, "Earth Style: Head Hunter Jutsu", EnumRank.D, 10, new EnumHandSign[] {EnumHandSign.SNAKE});
		JUTSUID = EnumJutsu.HEADHUNTER;
	}

	@SubscribeEvent
	public void onActivate(MouseEvent event) {
		if (event.button != 1) return;
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		NinjaPropertiesPlayer ninja = NinjaPropertiesPlayer.get(player);
		player.addChatMessage(new ChatComponentText(
				EnumChatFormatting.WHITE + "Right Click Event"));
		if(ninja.getChakraMode() == false)
			return;
		
		IJutsu j = ninja.getLoadedJutsu();
		
		if(j == null)
			return;
			
		World world = ninja.entity.worldObj;
		MovingObjectPosition objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
		if (objectMouseOver != null)
		{
			if (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
			{
				Entity e = objectMouseOver.entityHit;
				
				ninja.activateJutsuById(e.getEntityId());
			}
		}
	}
	
	
	//make cast jutsu for server side but activate it on event
	
	@Override
	public void castJutsu(NinjaPropertiesPlayer ninja, int target, BlockPos block) {
		Entity targetEntity = ninja.entity.worldObj.getEntityByID(target);
		World world = ninja.entity.worldObj;
		
		if(targetEntity.isAirBorne)
			return;
		if(targetEntity.isInWater())
			return;
		if(targetEntity.onGround) {
			double x = targetEntity.posX;
			double y = targetEntity.posY;
			double z = targetEntity.posZ;
			
			PlayerMessageUtil.Debug(ninja.entity, this._name);

			world.destroyBlock(new BlockPos(x, y-1, z), true);
			world.destroyBlock(new BlockPos(x, y-2, z), true);

			//target.setPosition(x, y-2, z);

			
			ninja.consumeChakra(this._chakraCost);

		}

	}

	@Override
	public int getJutsuId() {
		return this.JUTSUID.ordinal();
	}
}
