package com.bruz.ninjutsu.jutsu;

import java.util.ArrayList;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumRank;

public abstract class Jutsu {

	public EnumChakraRelease _release;
	public String _name;
	public EnumRank _rank;
	public int _chakraCost;
	
	
	public Jutsu(EnumChakraRelease release, String name, EnumRank rank, int chakraCost) {
		_release = release;
		_name = name;
		_rank = rank;
		_chakraCost = chakraCost;
	}

	public String getJutsuName() {
		return _name;
	}
}
