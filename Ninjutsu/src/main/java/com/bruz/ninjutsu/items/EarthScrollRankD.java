package com.bruz.ninjutsu.items;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumRank;

import net.minecraft.creativetab.CreativeTabs;

public class EarthScrollRankD extends JutsuScroll {

	
	public EarthScrollRankD() {
		super();
		
		this._rank = EnumRank.D;
		this._release = EnumChakraRelease.EARTH;
		
		this.setUnlocalizedName("earthScrollRankD");
		this.setCreativeTab(CreativeTabs.tabMisc);	
		
		
	}
	

}
