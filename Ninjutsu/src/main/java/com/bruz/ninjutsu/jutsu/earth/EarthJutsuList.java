package com.bruz.ninjutsu.jutsu.earth;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.bruz.ninjutsu.enums.EnumJutsu;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.jutsu.Jutsu;

public class EarthJutsuList {

	public static Map<Integer, Jutsu> DList = new HashMap<Integer, Jutsu>();
	
	public static void init() {
		DList.put(EnumJutsu.HeadHunter.ordinal(), new HeadHunter());
	}
	
/*	public static Jutsu getRandomEarthJutsu(EnumRank rank) {
		Jutsu j = null;
		switch(rank) {
		case D: j = getRandomDRank();
			break;
		default: 
			break;
		}
		return j;
	}*/
/*	public static Jutsu getRandomDRank() {
		Random random = new Random();
		return DList.get(random.nextInt(DList.size()));
	}*/
}
