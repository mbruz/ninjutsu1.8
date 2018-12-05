package com.bruz.ninjutsu.jutsu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.jutsu.earth.HeadHunter;

public class JutsuList {
	public static Map<EnumHandSign[], Jutsu> HandSignJutsuList = new HashMap<EnumHandSign[], Jutsu>();
	public static Map<Integer, EnumHandSign[]> IDList = new HashMap<Integer, EnumHandSign[]>();
	public static ArrayList<Integer> RankDList = new ArrayList<Integer>();
	public static ArrayList<Integer> RankCList = new ArrayList<Integer>();
	public static ArrayList<Integer> RankBList = new ArrayList<Integer>();
	public static ArrayList<Integer> RankAList = new ArrayList<Integer>();
	
	public static void init() {
		registerJutsu(new HeadHunter());

	}
	
	//matching
	
	public static Jutsu matchHandSigns(EnumHandSign[] hs) {
		Jutsu j = null;
		boolean jutsuExists = HandSignJutsuList.containsKey(hs);
		if(jutsuExists) {
			j = HandSignJutsuList.get(hs);
		}
		return j;
		
	}
	
	public static Integer matchHandSignsToId(EnumHandSign[] hs) {
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
			EnumHandSign[] arr = IDList.get(id);
			for(EnumHandSign h : arr) {
				hs.add(h);
			}
		}
		return hs;		
	}
	
	//helpers
	
	private static void registerJutsu(Jutsu j) {
		HandSignJutsuList.put(j._handSignRequirement, j);
		IDList.put(j.JUTSUID.ordinal(), j._handSignRequirement);
		switch(j._rank) {
		case D: RankDList.add(j.JUTSUID.ordinal());
			break;
		case C: RankCList.add(j.JUTSUID.ordinal());
			break;
		case B: RankBList.add(j.JUTSUID.ordinal());
			break;
		case A: RankAList.add(j.JUTSUID.ordinal());
			break;			
		default:
			break;
		}
	}
}
