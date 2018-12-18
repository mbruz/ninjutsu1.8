package com.bruz.ninjutsu.jutsu;

import java.util.ArrayList;

import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;

import net.minecraft.entity.Entity;

public interface IJutsu {
	public int getJutsuId();
	
	public void castJutsu(NinjaPropertiesPlayer player, Entity target);
}
