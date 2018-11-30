package com.bruz.ninjutsu.jutsu;

import java.util.ArrayList;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumRank;

public abstract class Jutsu {

	public EnumChakraRelease _release;
	public String _name;
	public EnumRank _rank;
	public int _chakraCost;
	public EnumHandSign[] _handSignRequirement;
	
	
	public Jutsu(EnumChakraRelease release, String name, EnumRank rank, int chakraCost) {
		_release = release;
		_name = name;
		_rank = rank;
		_chakraCost = chakraCost;		
	}

	public String getJutsuName() {
		return _name;
	}
	
	public boolean matchHandsigns(EnumHandSign[] input) {
		if(input.length == _handSignRequirement.length) 
		{
			for(int i = 0; i < input.length; i++) 
			{
				if(input[i] != _handSignRequirement[i]) 
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
