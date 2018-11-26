package com.bruz.ninjutsu.items;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumRank;

import net.minecraft.creativetab.CreativeTabs;

public class EarthScrollRankD extends JutsuScroll {

	
	public EarthScrollRankD() {
		super();
		this.setUnlocalizedName("earthScrollRankD");
		this.setCreativeTab(CreativeTabs.tabMisc);
		
		this.setJutsu(this.getRandomJutsu(EnumChakraRelease.EARTH, EnumRank.D));
		
	}
	

}
