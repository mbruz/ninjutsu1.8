package com.bruz.ninjutsu.jutsu;

import java.util.ArrayList;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumJutsu;
import com.bruz.ninjutsu.enums.EnumRank;

public abstract class Jutsu {

	public EnumJutsu JUTSUID;
	public EnumChakraRelease _release;
	public String _name;
	public EnumRank _rank;
	public int _chakraCost;
	public EnumHandSign[] _handSignRequirement;
	
	
	public Jutsu(EnumChakraRelease release, String name, EnumRank rank, int chakraCost, EnumHandSign[] hs) {
		_release = release;
		_name = name;
		_rank = rank;
		_chakraCost = chakraCost;
		_handSignRequirement = hs;
	}

	public String getJutsuName() {
		return _name;
	}
}
