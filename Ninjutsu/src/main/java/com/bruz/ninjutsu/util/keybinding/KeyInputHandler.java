package com.bruz.ninjutsu.util.keybinding;

import org.lwjgl.input.Keyboard;

import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.jutsu.IJutsu;
import com.bruz.ninjutsu.jutsu.Jutsu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.ICommand;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyInputHandler {

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event)
    {    	
        if (NinjutsuKeyBinds.chakraMode.isPressed())
        {
        	boolean chakraMode = NinjaPropertiesPlayer.get(Minecraft.getMinecraft().thePlayer).ToggleChakraMode();
        	if(chakraMode) 
        	{
        		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Chakra Mode Activated!"));
                
        	}else 
        	{
        		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Chakra Mode Deactivated!"));                
        	}        	
        }
        
        boolean isChakraModeActive = NinjaPropertiesPlayer.get(Minecraft.getMinecraft().thePlayer).getChakraMode();
        
        // use this to check for handsigns
        if(isChakraModeActive && Keyboard.isKeyDown(Keyboard.KEY_K)) {
        	NinjaPropertiesPlayer.get(Minecraft.getMinecraft().thePlayer).addHandSign(EnumHandSign.MONKEY);
        	Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Monkey")); 
        }
        if(isChakraModeActive && Keyboard.isKeyDown(Keyboard.KEY_J)) {
        	NinjaPropertiesPlayer.get(Minecraft.getMinecraft().thePlayer).addHandSign(EnumHandSign.DRAGON);
        	Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Dragon")); 
        }
        if(isChakraModeActive && Keyboard.isKeyDown(Keyboard.KEY_L)) {
        	NinjaPropertiesPlayer.get(Minecraft.getMinecraft().thePlayer).addHandSign(EnumHandSign.RAT);
        	Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Rat")); 
        }
        if(isChakraModeActive && Keyboard.isKeyDown(Keyboard.KEY_I)) {
        	NinjaPropertiesPlayer.get(Minecraft.getMinecraft().thePlayer).addHandSign(EnumHandSign.BIRD);
        	Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Bird")); 
        }
        if(isChakraModeActive && Keyboard.isKeyDown(Keyboard.KEY_O)) {
        	NinjaPropertiesPlayer.get(Minecraft.getMinecraft().thePlayer).addHandSign(EnumHandSign.SNAKE);
        	Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Snake")); 
        }
    }

	@SubscribeEvent
	public void onMouseEvent(MouseEvent event) {
		if (event.button != 1) return;
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		NinjaPropertiesPlayer ninja = NinjaPropertiesPlayer.get(player);
		
		if(ninja.getChakraMode() == false)
			return;
		
		IJutsu j = ninja.getLoadedJutsu();
		
		if(j == null)
			return;
			//figure out how to activate jutsu
		j.castJutsu(ninja);
		
	}
}
