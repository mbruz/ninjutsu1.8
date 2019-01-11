package com.bruz.ninjutsu.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.bruz.ninjutsu.Main;

public enum EnumChakraRelease {

	FIRE, WIND, LIGHTNING, EARTH, WATER, WOOD;
		
	private static final List<EnumChakraRelease> VALUES =
			    Collections.unmodifiableList(Arrays.asList(values()));
	
	private static final int SIZE = VALUES.size();
	
	public static EnumChakraRelease getRandomRelease() {
		return VALUES.get(Main.RANDOM.nextInt(SIZE));
	}
	
	public static EnumChakraRelease getRelease(int id) {
		return VALUES.get(id);
	}
}
