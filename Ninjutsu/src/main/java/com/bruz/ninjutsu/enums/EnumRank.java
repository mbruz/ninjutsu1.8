package com.bruz.ninjutsu.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum EnumRank {

	D, C, B, A, S;
	
	private static final List<EnumRank> VALUES =
			Collections.unmodifiableList(Arrays.asList(values()));

	public static EnumRank GetRank(int id) {
		return VALUES.get(id);
	}
}
