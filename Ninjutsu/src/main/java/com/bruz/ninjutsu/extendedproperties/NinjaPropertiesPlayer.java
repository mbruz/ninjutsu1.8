package com.bruz.ninjutsu.extendedproperties;

import com.bruz.ninjutsu.enums.EnumChakraRelease;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class NinjaPropertiesPlayer implements IExtendedEntityProperties {

	public final static String extendedPropertiesName = "ninjaPropertiesPlayer";
	protected EntityPlayer entity;
	protected World theWorld;
	
	private int chakraCurrent, chakraMax;
	private int chakraControl;
	private int chakraNature;

	
	private int fireAffinity, windAffinity, lightningAffinity, earthAffinity, waterAffinity;
	
	//private int staminaCurrent, staminaMax;
	//speed multiplier, jump multiplier, taijutsu--punch strength and speed
	
	//Constructor
	public NinjaPropertiesPlayer(EntityPlayer player) {
		this.entity = player;
		chakraCurrent = chakraMax = 50;/*staminaCurrent = staminaMax =*/
		fireAffinity = windAffinity = lightningAffinity = earthAffinity = waterAffinity = 0;
		chakraControl = 0;
		chakraNature = (int)Math.random()*5;
		
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setInteger("CurrentChakra", this.chakraCurrent);
		properties.setInteger("MaxChakra", this.chakraMax);
/*		properties.setInteger("CurrentStamina", this.staminaCurrent);
		properties.setInteger("MaxStamina", this.staminaMax);*/
		
		properties.setInteger("FireAffinity", this.fireAffinity);
		properties.setInteger("WindAffinity", this.windAffinity);
		properties.setInteger("LightningAffinity", this.lightningAffinity);
		properties.setInteger("EarthAffinity", this.earthAffinity);
		properties.setInteger("WaterAffinity", this.waterAffinity);
		
		properties.setInteger("ChakraControl", this.chakraControl);
		properties.setInteger("ChakraNature", this.chakraNature);
		
		compound.setTag(extendedPropertiesName, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = (NBTTagCompound) compound.getTag(extendedPropertiesName);
		
		this.chakraCurrent = properties.getInteger("CurrentChakra");
		this.chakraMax = properties.getInteger("MaxChakra");
/*		this.staminaCurrent = properties.getInteger("CurrentStamina");
		this.staminaMax = properties.getInteger("MaxStamina");*/
		
		this.fireAffinity = properties.getInteger("FireAffinity");
		this.windAffinity = properties.getInteger("WindAffinity");
		this.lightningAffinity = properties.getInteger("LightningAffinity");
		this.earthAffinity = properties.getInteger("EarthAffinity");
		this.waterAffinity = properties.getInteger("WaterAffinity");
		
		this.chakraControl = properties.getInteger("ChakraControl");
		this.chakraNature = properties.getInteger("ChakraNature");
	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
		
	}
	
	//other methods for accessing and manipulating extended variables
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(NinjaPropertiesPlayer.extendedPropertiesName, new NinjaPropertiesPlayer(player));
	}
	
	public static final NinjaPropertiesPlayer get(EntityPlayer player)
	{
		return (NinjaPropertiesPlayer) player.getExtendedProperties(extendedPropertiesName);
	}
	
	public int getCurrentChakra() {
		return this.chakraCurrent;
	}
	
	public int getMaxChakra() {
		return this.chakraMax;
	}
	//consume chakra method
	
}
