package com.bruz.ninjutsu.jutsu;

import java.util.ArrayList;
import java.util.UUID;

import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public interface IJutsu {
	public int getJutsuId();
	
	public void castJutsu(NinjaPropertiesPlayer player, int target, BlockPos block);
}
