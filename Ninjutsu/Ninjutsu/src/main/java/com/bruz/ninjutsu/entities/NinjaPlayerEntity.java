package com.bruz.ninjutsu.entities;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class NinjaPlayerEntity extends EntityPlayer {

	public NinjaPlayerEntity(World worldIn, GameProfile gameProfileIn) {
		super(worldIn, gameProfileIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSpectator() {
		// TODO Auto-generated method stub
		return false;
	}

}
