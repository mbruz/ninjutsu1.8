package com.bruz.ninjutsu.jutsu.earth;

import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.jutsu.Jutsu;

public class EarthJutsuList {

	public static final Jutsu[] DList = new Jutsu[] {
			new HeadHunter()
	};
	
	public static Jutsu getRandomEarthJutsu(EnumRank rank) {
		Jutsu j = null;
		switch(rank) {
		case D: j = getRandomDRank();
			break;
		default: 
			break;
		}
		return j;
	}
	public static Jutsu getRandomDRank() {
		int random = (int)Math.random()*(DList.length - 1);
		return DList[random];
	}
}
