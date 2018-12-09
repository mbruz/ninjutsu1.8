package com.bruz.ninjutsu.jutsu.earth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.bruz.ninjutsu.enums.EnumJutsu;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.jutsu.Jutsu;

public class EarthJutsuList {

	public static ArrayList<Jutsu> DList;
	public static Map<Integer, Jutsu> CList = new HashMap<Integer, Jutsu>();
	public static Map<Integer, Jutsu> BList = new HashMap<Integer, Jutsu>();
	public static Map<Integer, Jutsu> AList = new HashMap<Integer, Jutsu>();
	
	public static void init() {
		DList = new ArrayList<Jutsu>();
	}
	
	public static void Register(Jutsu j) {
		switch(j._rank) {
		case D: DList.add(j);
			break;
		default: 
			break;
		}		
	}
	
	public static Jutsu getRandomEarthJutsu(EnumRank rank) {
		Jutsu j = null;
		switch(rank) {
		case D: j = getRandomJutsu(DList);
			break;
		default: 
			break;
		}
		return j;
	}
	public static Jutsu getRandomJutsu(ArrayList<Jutsu> j) {
		Random random = new Random();
		if(j != null && j.size() > 0) {
			Jutsu jutsu= j.get(random.nextInt(j.size()));
			return jutsu;
		}
		return null;
	}
}
