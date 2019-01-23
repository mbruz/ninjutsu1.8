package com.bruz.ninjutsu.util.keybinding;

import org.lwjgl.input.Keyboard;

import com.bruz.ninjutsu.Main;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;
import com.bruz.ninjutsu.jutsu.IJutsu;
import com.bruz.ninjutsu.jutsu.Jutsu;
import com.bruz.ninjutsu.util.PacketDispatcher;
import com.bruz.ninjutsu.util.PlayerMessageUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

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
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        boolean isChakraModeActive = NinjaPropertiesPlayer.get(player).getChakraMode();
        
        // use this to check for handsigns
        if(!isChakraModeActive) {return;}
        
        if(NinjutsuKeyBinds.signMonkey.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.MONKEY);
        	PlayerMessageUtil.Blue(player, "Monkey"); 
        }
        if(NinjutsuKeyBinds.signDragon.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.DRAGON);
        	PlayerMessageUtil.Blue(player, "Dragon"); 
        }
        if(NinjutsuKeyBinds.signRat.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.RAT);
        	PlayerMessageUtil.Blue(player, "Rat"); 
        }
        if(NinjutsuKeyBinds.signBird.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.BIRD);
        	PlayerMessageUtil.Blue(player, "Bird"); 
        }
        if(NinjutsuKeyBinds.signSnake.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.SNAKE);
        	PlayerMessageUtil.Blue(player, "Snake"); 
        }
        if(NinjutsuKeyBinds.signOx.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.OX);
        	PlayerMessageUtil.Blue(player, "Ox"); 
        }
        if(NinjutsuKeyBinds.signDog.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.DOG);
        	PlayerMessageUtil.Blue(player, "Dog"); 
        }
        if(NinjutsuKeyBinds.signHorse.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.HORSE);
        	PlayerMessageUtil.Blue(player, "Horse"); 
        }
        if(NinjutsuKeyBinds.signTiger.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.TIGER);
        	PlayerMessageUtil.Blue(player, "Tiger"); 
        }
        if(NinjutsuKeyBinds.signBoar.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.BOAR);
        	PlayerMessageUtil.Blue(player, "Boar"); 
        }
        if(NinjutsuKeyBinds.signRam.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.RAM);
        	PlayerMessageUtil.Blue(player, "Ram"); 
        }
        
        if(NinjutsuKeyBinds.signHare.isPressed()) {
        	NinjaPropertiesPlayer.get(player).addHandSign(EnumHandSign.HARE);
        	PlayerMessageUtil.Blue(player, "Hare"); 
        }
    }

}
