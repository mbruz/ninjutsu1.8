package com.bruz.ninjutsu.util.keybinding;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class NinjutsuKeyBinds {

	public static KeyBinding chakraMode;
	
	public static final String ninjutsuCategory = "key.categories.ninjutsu";
	
    public static void register()
    {
    	chakraMode = new KeyBinding("key.chakraMode", Keyboard.KEY_LMENU, ninjutsuCategory);
 
        ClientRegistry.registerKeyBinding(chakraMode);
    }
	
}
