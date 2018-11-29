package com.bruz.ninjutsu.util.keybinding;

import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
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
    }
}
