package com.bruz.ninjutsu.jutsu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.jutsu.earth.EarthJutsuList;
import com.bruz.ninjutsu.jutsu.earth.HeadHunter;

public class JutsuList {
	public static Map<ArrayList<EnumHandSign>, Jutsu> HandSignJutsuList = new HashMap<ArrayList<EnumHandSign>, Jutsu>();
	public static Map<Integer, ArrayList<EnumHandSign>> IDList = new HashMap<Integer, ArrayList<EnumHandSign>>();
	
	public static void init() {
		EarthJutsuList.init();
		
		registerJutsu(new HeadHunter());

	}
	
	//matching
	
	public static Jutsu matchHandSigns(ArrayList<EnumHandSign> hs) {
		Jutsu j = null;
		boolean jutsuExists = HandSignJutsuList.containsKey(hs);
		if(jutsuExists) {
			j = HandSignJutsuList.get(hs);
		}
		return j;
		
	}
	
	public static Integer matchHandSignsToId(ArrayList<EnumHandSign> hs) {
		Integer j = 0;
		boolean jutsuExists = IDList.containsValue(hs);
		if(jutsuExists) {
			j = HandSignJutsuList.get(hs).JUTSUID.ordinal();
		}
		return j;
		
	}
	public static ArrayList<EnumHandSign> matchIDtoHandSigns(int id) {
		ArrayList<EnumHandSign> hs = new ArrayList<EnumHandSign>();
		boolean idExists = IDList.containsKey(id);
		if(idExists) {
			hs = IDList.get(id);
		}
		return hs;		
	}
	
	public static Jutsu matchIdtoJutsu(int id) {
		return HandSignJutsuList.get(IDList.get(id));
	}
	
	//helpers
	
	private static void registerJutsu(Jutsu j) {
		HandSignJutsuList.put(j._handSignRequirement, j);
		IDList.put(j.JUTSUID.ordinal(), j._handSignRequirement);
		switch(j._release) {
		case EARTH: EarthJutsuList.Register(j);
			break;
		default: 
			break;
		}
	}
}
