package com.bruz.ninjutsu.extendedproperties;

import java.util.ArrayList;
import java.util.List;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.network.SyncNinjaPropsMessage;
import com.bruz.ninjutsu.util.PacketDispatcher;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class NinjaPropertiesPlayer implements IExtendedEntityProperties {

	public final static String extendedPropertiesName = "ninjaPropertiesPlayer";
	protected EntityPlayer entity;
	protected World theWorld;
	
	private boolean chakraMode;
	
	public static final int CHAKRA_WATCHER = 20;	
	private int chakraMax, chakraRegenTimer;
	//private int chakraControl;
	//private int chakraNature;

	
	//private int fireAffinity, windAffinity, lightningAffinity, earthAffinity, waterAffinity;
	
	//private int staminaCurrent, staminaMax;
	//speed multiplier, jump multiplier, taijutsu--punch strength and speed
	
	private List<Integer> earthJutsu;
	
	//Constructor
	public NinjaPropertiesPlayer(EntityPlayer player) {
		this.entity = player;
		chakraMode = false;
		chakraMax = 50;
		chakraRegenTimer = 0;
		/*staminaCurrent = staminaMax =*/
		this.entity.getDataWatcher().addObject(CHAKRA_WATCHER, this.chakraMax);
		//fireAffinity = windAffinity = lightningAffinity = earthAffinity = waterAffinity = 0;
		/*chakraControl = 0;
		chakraNature = (int)Math.random()*5;*/
		
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("CurrentChakra", this.entity.getDataWatcher().getWatchableObjectInt(CHAKRA_WATCHER));
		properties.setInteger("MaxChakra", this.chakraMax);
		properties.setInteger("ChakraRegenTimer", chakraRegenTimer);
/*		properties.setInteger("CurrentStamina", this.staminaCurrent);
		properties.setInteger("MaxStamina", this.staminaMax);*/
		
/*		properties.setInteger("FireAffinity", this.fireAffinity);
		properties.setInteger("WindAffinity", this.windAffinity);
		properties.setInteger("LightningAffinity", this.lightningAffinity);
		properties.setInteger("EarthAffinity", this.earthAffinity);
		properties.setInteger("WaterAffinity", this.waterAffinity);
		
		properties.setInteger("ChakraControl", this.chakraControl);
		properties.setInteger("ChakraNature", this.chakraNature);*/
		
		compound.setTag(extendedPropertiesName, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = (NBTTagCompound) compound.getTag(extendedPropertiesName);
		
		this.entity.getDataWatcher().updateObject(CHAKRA_WATCHER, properties.getInteger("CurrentChakra"));
		this.chakraMax = properties.getInteger("MaxChakra");
		chakraRegenTimer = properties.getInteger("ChakraRegenTimer");
/*		this.staminaCurrent = properties.getInteger("CurrentStamina");
		this.staminaMax = properties.getInteger("MaxStamina");*/
		
/*		this.fireAffinity = properties.getInteger("FireAffinity");
		this.windAffinity = properties.getInteger("WindAffinity");
		this.lightningAffinity = properties.getInteger("LightningAffinity");
		this.earthAffinity = properties.getInteger("EarthAffinity");
		this.waterAffinity = properties.getInteger("WaterAffinity");
		
		this.chakraControl = properties.getInteger("ChakraControl");
		this.chakraNature = properties.getInteger("ChakraNature");*/
	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
		
	}
	
	public void copy(NinjaPropertiesPlayer props) {
		entity.getDataWatcher().updateObject(CHAKRA_WATCHER, props.getCurrentChakra());
		chakraMax = props.chakraMax;
		chakraRegenTimer = props.chakraRegenTimer;
	}
	
	//Getters and Setters
	
	public static final NinjaPropertiesPlayer get(EntityPlayer player)
	{
		return (NinjaPropertiesPlayer) player.getExtendedProperties(extendedPropertiesName);
	}
	
	public int getCurrentChakra() {
		return this.entity.getDataWatcher().getWatchableObjectInt(CHAKRA_WATCHER);
	}
	
	public final void setCurrentChakra(int amount) {
		entity.getDataWatcher().updateObject(CHAKRA_WATCHER, amount > 0 ? (amount < chakraMax ? amount : chakraMax) : 0);
	}
	
	public int getMaxChakra() {
		return this.chakraMax;
	}
	
	public final void setMaxChakra(int amount) {
		chakraMax = (amount > 0 ? amount : 0);
		// if your extended properties contains a lot of data, it would be better
		// to make an individual packet for maxMana, rather than sending all of
		// the data each time max mana changes...
		
		PacketDispatcher.sendTo(new SyncNinjaPropsMessage(entity), (EntityPlayerMP) entity);
	}
	
	//Chakra Updating
	
	public boolean getChakraMode() {
		return chakraMode;
	}
	
	public boolean ToggleChakraMode() {
		chakraMode = !chakraMode;
		return chakraMode;
	}
	
	private boolean updateChakraTimer() {
		if (chakraRegenTimer > 0) {
			--chakraRegenTimer;
		}
		if (chakraRegenTimer == 0) {
			chakraRegenTimer = getCurrentChakra() < getMaxChakra() ? 100 : 0;
			return true;
		}
		
		return false;
	}
	
	public final void regenChakra(int amount) {
		setCurrentChakra(getCurrentChakra() + amount);
	}
	
	public final boolean consumeChakra(int amount) {
		boolean sufficient = amount <= getCurrentChakra();
		setCurrentChakra(getCurrentChakra() - amount);
		return sufficient;
	}
	
	public void onUpdate() {
		// only want to update the timer and regen mana on the server:
		if (!entity.worldObj.isRemote) {
			if (updateChakraTimer()) {
				regenChakra(1);
			}
		}
	}
	
	//Sync methods
	
	public final void sync() 
	{
		PacketDispatcher.sendTo(new SyncNinjaPropsMessage(this.entity), (EntityPlayerMP) this.entity);
	}
	
	public final void syncMaxChakra() 
	{
		//make individual message for max chakra
	}
	
	//helpers
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(NinjaPropertiesPlayer.extendedPropertiesName, new NinjaPropertiesPlayer(player));
	}
}
