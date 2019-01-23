package com.bruz.ninjutsu.jutsu.earth;

import com.bruz.ninjutsu.Main;
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
import net.minecraft.world.World;
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
		
		if(event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
			if(ninja.getChakraMode() && ninja.getLoadedJutsu().getJutsuId() == this.JUTSUID.ordinal()) 
			{
				BlockPos block = event.pos;
				PlayerMessageUtil.Debug(ninja.entity, this._name);
				
				ninja.activateJutsuByBlockPos(block);
				
				ninja.chakraStats.incrementAffinity(EnumChakraRelease.EARTH, 5);
				ninja.chakraStats.incrementExperience(5);
				ninja.unloadJutsu();	
			}
		}

	}

	@Override
	public int getJutsuId() {
		return this.JUTSUID.ordinal();
	}

	@Override
	public void castJutsu(NinjaPropertiesPlayer player, int target, BlockPos block) {
		// TODO Auto-generated method stub
		World world = player.entity.worldObj;
		world.createExplosion(player.entity, block.getX(), block.getY() + 1, block.getZ(), 10.0F, true);
		player.consumeChakra(this._chakraCost);
	}
	
}