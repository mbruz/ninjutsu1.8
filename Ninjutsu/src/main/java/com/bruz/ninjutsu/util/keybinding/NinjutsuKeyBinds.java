package com.bruz.ninjutsu.util.keybinding;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class NinjutsuKeyBinds {

	public static KeyBinding chakraMode;
	public static KeyBinding signMonkey;
	public static KeyBinding signDragon;
	public static KeyBinding signRat;
	public static KeyBinding signBird;
	public static KeyBinding signSnake;
	public static KeyBinding signOx;
	public static KeyBinding signDog;
	public static KeyBinding signHorse;
	public static KeyBinding signTiger;
	public static KeyBinding signBoar;
	public static KeyBinding signRam;
	public static KeyBinding signHare;
	
	
	public static final String ninjutsuCategory = "key.categories.ninjutsu";
	
    public static void register()
    {
    	chakraMode = new KeyBinding("key.chakraMode", Keyboard.KEY_LMENU, ninjutsuCategory);
    	
    	signOx = new KeyBinding("key.signOx", Keyboard.KEY_U, ninjutsuCategory);
    	signBird = new KeyBinding("key.signBird", Keyboard.KEY_I, ninjutsuCategory);
    	signSnake = new KeyBinding("key.signSnake", Keyboard.KEY_O, ninjutsuCategory);    	
    	signDog = new KeyBinding("key.signDog", Keyboard.KEY_P, ninjutsuCategory);
    	
    	signHorse = new KeyBinding("key.signHorse", Keyboard.KEY_H, ninjutsuCategory);
    	signDragon = new KeyBinding("key.signDragon", Keyboard.KEY_J, ninjutsuCategory);
    	signMonkey = new KeyBinding("key.signMonkey", Keyboard.KEY_K, ninjutsuCategory);
    	signRat = new KeyBinding("key.signRat", Keyboard.KEY_L, ninjutsuCategory);
    	
    	signTiger = new KeyBinding("key.signTiger", Keyboard.KEY_N, ninjutsuCategory);
    	signBoar = new KeyBinding("key.signBoar", Keyboard.KEY_M, ninjutsuCategory);   
    	signRam = new KeyBinding("key.signRam", Keyboard.KEY_COMMA, ninjutsuCategory);
    	signHare = new KeyBinding("key.signHare", Keyboard.KEY_PERIOD, ninjutsuCategory);
    	
        ClientRegistry.registerKeyBinding(chakraMode);
        ClientRegistry.registerKeyBinding(signMonkey);
        ClientRegistry.registerKeyBinding(signDragon);
        ClientRegistry.registerKeyBinding(signRat);
        ClientRegistry.registerKeyBinding(signBird);
        ClientRegistry.registerKeyBinding(signSnake);
        ClientRegistry.registerKeyBinding(signOx);
        ClientRegistry.registerKeyBinding(signDog);
        ClientRegistry.registerKeyBinding(signHorse);
        ClientRegistry.registerKeyBinding(signTiger);
        ClientRegistry.registerKeyBinding(signBoar);
        ClientRegistry.registerKeyBinding(signRam);
        ClientRegistry.registerKeyBinding(signHare);
        
        
        
    }
	
}
