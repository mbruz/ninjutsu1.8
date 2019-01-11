package com.bruz.ninjutsu.jutsu.wood;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumJutsu;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.jutsu.IJutsu;
import com.bruz.ninjutsu.jutsu.Jutsu;

import net.minecraft.entity.Entity;

public class FourPillarHouse  extends Jutsu implements IJutsu {

	public FourPillarHouse(EnumChakraRelease release, String name, EnumRank rank, int chakraCost, EnumHandSign[] hs) {
		super(EnumChakraRelease.WOOD, "Wood Style: Four Pillar House", EnumRank.C, 50, 
				new EnumHandSign[] {EnumHandSign.SNAKE, EnumHandSign.RAT, EnumHandSign.OX, EnumHandSign.RAM, EnumHandSign.SNAKE});
		this.JUTSUID = EnumJutsu.FOURPILLARHOUSE;
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
