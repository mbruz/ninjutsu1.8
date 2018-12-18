package com.bruz.ninjutsu.jutsu.earth;

import java.util.ArrayList;
import java.util.List;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumJutsu;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.jutsu.IJutsu;
import com.bruz.ninjutsu.jutsu.Jutsu;

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
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HeadHunter extends Jutsu implements IJutsu {

	private EntityPlayer target;
	
	public HeadHunter() {		
		super(EnumChakraRelease.EARTH, "Earth Style: Head Hunter Jutsu", EnumRank.D, 10, new EnumHandSign[] {EnumHandSign.SNAKE});
		JUTSUID = EnumJutsu.HeadHunter;
	}

/*	@SubscribeEvent
	public void onActivate(PlayerInteractEvent event) {
		if(!event.world.isRemote) {
			EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
			NinjaPropertiesPlayer.get(player).activateJutsu();
		}
	}*/
	
	
	@Override
	public void castJutsu(NinjaPropertiesPlayer player, Entity target) {
		World world = player.entity.worldObj;
		MovingObjectPosition objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
		if (objectMouseOver != null)
		{
			if (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
			{
				player.entity.addChatMessage(new ChatComponentText(
						EnumChatFormatting.WHITE + "About to process jutsu"));
				//return objectMouseOver.entityHit.getEntityName();
				Entity e = objectMouseOver.entityHit;
				
				if(e.isAirBorne)
					return;
				if(e.isInWater())
					return;
				if(e.onGround) {
					float width = e.width;

					double x = e.posX;
					double y = e.posY;
					double z = e.posZ;

					world.destroyBlock(new BlockPos(x, y-1, z), true);

					world.destroyBlock(new BlockPos(x, y-2, z), true);


					e.setPosition(x, y-2, z);

					player.entity.addChatMessage(new ChatComponentText(
							EnumChatFormatting.WHITE + this._name));
					player.consumeChakra(this._chakraCost);

				}

			}		
		}

	}

	@Override
	public int getJutsuId() {
		return this.JUTSUID.ordinal();
	}
}
