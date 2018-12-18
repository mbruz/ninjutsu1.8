package com.bruz.ninjutsu.gui;

import org.lwjgl.opengl.GL11;

import com.bruz.ninjutsu.Main;
import com.bruz.ninjutsu.extendedproperties.NinjaPropertiesPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiStaminaBar extends Gui
{
	
	private Minecraft mc;
	private static final ResourceLocation texturepath = new ResourceLocation(Main.MODID, "textures/gui/mana_bar.png");

	public GuiStaminaBar(Minecraft mc)
	{
		super();
		// We need this to invoke the render engine.
		this.mc = mc;
	}

	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) 
	{
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE)
		{
			return;
		}
		NinjaPropertiesPlayer props = NinjaPropertiesPlayer.get(this.mc.thePlayer);
		
		if (props == null || props.getMaxStamina() == 0)
		{
			return;
		}
		
		int xPos = 2;
		int yPos = 8;
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		this.mc.getTextureManager().bindTexture(texturepath);
		
		this.drawTexturedModalRect(xPos, yPos, 0, 0, 50, 4);
		
		int staminabarwidth = (int)(((float) props.getCurrentStamina() / props.getMaxStamina()) * 50);
		
		this.drawTexturedModalRect(xPos, yPos + 1, 0, 4, staminabarwidth, 2);
	}
	
}