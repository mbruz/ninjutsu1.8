package com.bruz.ninjutsu.stats;

public class StaminaStats {
	//speed multiplier, jump multiplier, taijutsu--punch strength and speed
	private int speed;
	private int strength;
	private int experience;
	private int staminaMax;
	
	public StaminaStats() {
		this.staminaMax = 50;
		this.experience = 0;
		this.strength = 1;
		this.speed = 1;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getStaminaMax() {
		return staminaMax;
	}

	public void setStaminaMax(int staminaMax) {
		this.staminaMax = staminaMax;
	}
}
