package com.bruz.ninjutsu.jutsu.earth;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumJutsu;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.jutsu.IJutsu;
import com.bruz.ninjutsu.jutsu.Jutsu;
import com.bruz.ninjutsu.util.PlayerMessageUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExplodeBlock extends Jutsu implements IJutsu {

	public ExplodeBlock() {
		super(EnumChakraRelease.EARTH, "Explosive Style: Block Explode Jutsu", EnumRank.D, 25,
				new EnumHandSign[] {EnumHandSign.MONKEY, EnumHandSign.BIRD, EnumHandSign.SNAKE});
		this.JUTSUID = EnumJutsu.EXPLODEBLOCK;
	}

	@SubscribeEvent
	public void activate(PlayerInteractEvent event) {		
		NinjaPropertiesPlayer ninja = NinjaPropertiesPlayer.get((EntityPlayer)event.entityPlayer);
		PlayerMessageUtil.Debug(ninja.entity, "Explode block inside event");
		if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
				if(ninja.getChakraMode() && ninja.getLoadedJutsu().getJutsuId() == this.JUTSUID.ordinal()) {
				PlayerMessageUtil.Debug(ninja.entity, "Explode block inside right click");
				BlockPos block = event.pos;
				/*if(!event.world.isRemote) {*/
					PlayerMessageUtil.Debug(ninja.entity, "world is not remote");
					event.entity.motionY += 2.0;
					//make explosion bigger
					event.entity.worldObj.createExplosion(
							event.entity, block.getX(), block.getY(), block.getZ(), 32.0F, false);
					ninja.consumeChakra(this._chakraCost);
				//}
			}
		}

	}

	@Override
	public int getJutsuId() {
		return this.JUTSUID.ordinal();
	}

	@Override
	public void castJutsu(NinjaPropertiesPlayer player, Entity target) {
		// TODO Auto-generated method stub
		
	}
	
}