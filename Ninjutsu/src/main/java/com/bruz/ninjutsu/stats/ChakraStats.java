package com.bruz.ninjutsu.stats;

import java.util.ArrayList;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.enums.EnumRank;
import com.bruz.ninjutsu.jutsu.JutsuList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class ChakraStats {
	private int chakraMax;
	
	private int experience;
	
	private EnumRank chakraControl;
	private EnumChakraRelease chakraNature;	
	private int fireAffinity, windAffinity, lightningAffinity, earthAffinity, waterAffinity;
	
	public ChakraStats() {
		this.chakraMax = 50;
		this.experience = 0;
		this.chakraControl = EnumRank.D;
		this.chakraNature = EnumChakraRelease.getRandomRelease();
		fireAffinity = windAffinity = lightningAffinity = earthAffinity = waterAffinity = 0;
	}

	public void incrementExperience(int amount) {
		this.experience += amount;
		//packet to sync
	}
	
	public void incrementAffinity(EnumChakraRelease nature, int amount) {
		switch(nature) {
			case FIRE: this.fireAffinity += amount;
			//packet
			break;
			case WIND: this.windAffinity += amount;
			//packet
			break;
			case LIGHTNING: this.lightningAffinity += amount;
			//packet
			break;
			case EARTH: this.earthAffinity += amount;
			//packet
			break;
			case WATER: this.waterAffinity += amount;
			//packet to sync
			break;
			default: break;		
		}
	}
	
	//Getters and Setters
	
	public int getChakraMax() {
		return chakraMax;
	}

	public void setChakraMax(int chakraMax) {
		this.chakraMax = chakraMax;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public EnumRank getChakraControl() {
		return chakraControl;
	}

	public void setChakraControl(EnumRank chakraControl) {
		this.chakraControl = chakraControl;
	}

	public EnumChakraRelease getChakraNature() {
		return chakraNature;
	}

	public void setChakraNature(EnumChakraRelease chakraNature) {
		this.chakraNature = chakraNature;
	}

	public int getFireAffinity() {
		return fireAffinity;
	}

	public void setFireAffinity(int fireAffinity) {
		this.fireAffinity = fireAffinity;
	}

	public int getWindAffinity() {
		return windAffinity;
	}

	public void setWindAffinity(int windAffinity) {
		this.windAffinity = windAffinity;
	}

	public int getLightningAffinity() {
		return lightningAffinity;
	}

	public void setLightningAffinity(int lightningAffinity) {
		this.lightningAffinity = lightningAffinity;
	}

	public int getEarthAffinity() {
		return earthAffinity;
	}

	public void setEarthAffinity(int earthAffinity) {
		this.earthAffinity = earthAffinity;
	}

	public int getWaterAffinity() {
		return waterAffinity;
	}

	public void setWaterAffinity(int waterAffinity) {
		this.waterAffinity = waterAffinity;
	}
	
}
