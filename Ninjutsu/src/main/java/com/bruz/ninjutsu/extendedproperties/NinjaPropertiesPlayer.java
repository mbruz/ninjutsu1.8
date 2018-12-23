package com.bruz.ninjutsu.extendedproperties;

import java.util.ArrayList;
import java.util.List;

import com.bruz.ninjutsu.enums.EnumChakraRelease;
import com.bruz.ninjutsu.enums.EnumHandSign;
import com.bruz.ninjutsu.jutsu.IJutsu;
import com.bruz.ninjutsu.jutsu.Jutsu;
import com.bruz.ninjutsu.jutsu.JutsuList;
import com.bruz.ninjutsu.network.SyncJutsuListMessage;
import com.bruz.ninjutsu.network.SyncMaxChakraMessage;
import com.bruz.ninjutsu.network.SyncNinjaPropsMessage;
import com.bruz.ninjutsu.util.PacketDispatcher;
import com.bruz.ninjutsu.network.CastJutsuMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class NinjaPropertiesPlayer implements IExtendedEntityProperties {

	public final static String extendedPropertiesName = "ninjaPropertiesPlayer";
	public EntityPlayer entity;
	protected World theWorld;
	
	private boolean chakraMode;
	
	public static final int CHAKRA_WATCHER = 20;	
	private int chakraMax, chakraRegenTimer;
	
	public static final int STAMINA_WATCHER = 21;
	private int staminaMax, staminaRegenTimer;
	
	//private int chakraControl;
	//private int chakraNature;	
	//private int fireAffinity, windAffinity, lightningAffinity, earthAffinity, waterAffinity;
	
	//speed multiplier, jump multiplier, taijutsu--punch strength and speed
	
	private ArrayList<EnumHandSign> handSigns;
	private int handSignTimer;
	private ArrayList<ArrayList<EnumHandSign>> jutsus;
	private IJutsu defaultJutsu;
	private IJutsu loadedJutsu;
	
	//Constructor
	public NinjaPropertiesPlayer(EntityPlayer player) {
		this.entity = player;
		
		chakraMode = false;
		chakraMax = staminaMax = 50;
		chakraRegenTimer = staminaRegenTimer = 0;
		
		this.entity.getDataWatcher().addObject(CHAKRA_WATCHER, this.chakraMax);
		this.entity.getDataWatcher().addObject(STAMINA_WATCHER, this.staminaMax);
		
		handSigns = new ArrayList<EnumHandSign>();
		handSignTimer = 0;
		
		jutsus = new ArrayList<ArrayList<EnumHandSign>>();
		
		//other stamina attributes
		//affinities
		/*chakraControl = 0;
		chakraNature = (int)Math.random()*5;*/
		
	}
	
	//Getters and Setters
	
	public static final NinjaPropertiesPlayer get(EntityPlayer player)
	{
		return (NinjaPropertiesPlayer) player.getExtendedProperties(extendedPropertiesName);
	}
	
	public IJutsu getLoadedJutsu() {
		if(this.loadedJutsu != null) {
			return this.loadedJutsu;
		}else {
			return this.defaultJutsu;
		}
	}
	
	public int getCurrentChakra() {
		return this.entity.getDataWatcher().getWatchableObjectInt(CHAKRA_WATCHER);
	}
	
	public int getCurrentStamina() {
		return this.entity.getDataWatcher().getWatchableObjectInt(STAMINA_WATCHER);
	}
	
	public final void setCurrentChakra(int amount) {
		entity.getDataWatcher().updateObject(CHAKRA_WATCHER, amount > 0 ? (amount < chakraMax ? amount : chakraMax) : 0);
	}
	
	public final void setCurrentStamina(int amount) {
		entity.getDataWatcher().updateObject(STAMINA_WATCHER, amount > 0 ? (amount < staminaMax ? amount : staminaMax) : 0);
	}
	
	public int getMaxChakra() {
		return this.chakraMax;
	}
	
	public int getMaxStamina() {
		return this.staminaMax;
	}
	
	public final void setMaxChakra(int amount) {
		chakraMax = (amount > 0 ? amount : 0);
		// if your extended properties contains a lot of data, it would be better
		// to make an individual packet for maxMana, rather than sending all of
		// the data each time max mana changes...
		
		PacketDispatcher.sendTo(new SyncNinjaPropsMessage(entity), (EntityPlayerMP) entity);
	}
	
	public void learnJutsu(ArrayList<EnumHandSign> hs) {
		jutsus.add(hs);		
		syncJutsuList();
	}
	
	//Chakra && Stamina
	
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
	
	private boolean updateStaminaTimer() {
		if (staminaRegenTimer > 0) {
			--staminaRegenTimer;
		}
		if (staminaRegenTimer == 0) {
			staminaRegenTimer = getCurrentStamina() < getMaxStamina() ? 100 : 0;
			return true;
		}
		
		return false;
	}
	
	public final void regenChakra(int amount) {
		setCurrentChakra(getCurrentChakra() + amount);
	}
	public final void regenStamina(int amount) {
		setCurrentStamina(getCurrentStamina() + amount);
	}
	
	public final boolean consumeChakra(int amount) {
		boolean sufficient = amount <= getCurrentChakra();
		setCurrentChakra(getCurrentChakra() - amount);
		return sufficient;
	}
	
	public final boolean consumeStamina(int amount) {
		boolean sufficient = amount <= getCurrentStamina();
		setCurrentStamina(getCurrentStamina() - amount);
		return sufficient;
	}
	
	public void replenishChakra() {
		setCurrentChakra(this.chakraMax);
	}
	public void replenishStamina() {
		setCurrentChakra(this.staminaMax);
	}
	
	public void onUpdate() {
		// only want to update the timer and regen mana on the server:
		if (!entity.worldObj.isRemote) {
			if (updateChakraTimer()) {
				regenChakra(5);
			}
			if (updateStaminaTimer()) {
				regenStamina(5);
			}
		}
		updateHandSignTimer();
	}
	
	//handsigns
	
	public ArrayList<EnumHandSign> getHandSigns(){
		return handSigns;
	}
	
	public void addHandSign(EnumHandSign hs) {
		handSigns.add(hs);
		handSignTimer = 10;
	}
	
	private boolean updateHandSignTimer() {
		if (handSignTimer > 0) {
			--handSignTimer; 
		}else if(handSigns.size() > 0 && handSignTimer == 0){
			//check if user has jutsu in personal list
			boolean hasJutsu = checkForJutsu(handSigns);		
			
			if(hasJutsu) {
				//check here to find and load jutsu
				IJutsu j = (IJutsu)JutsuList.matchHandSigns(handSigns);
				
				if(j != null) {
					entity.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Jutsu Matched"));
					loadedJutsu = j;
				}
			}	
			handSigns = new ArrayList<EnumHandSign>();
		}
		
		return false;
	}
	
	public boolean checkForJutsu(ArrayList<EnumHandSign> hs) {
		return jutsus.contains(hs);
	}
	
	//NBT
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("CurrentChakra", this.entity.getDataWatcher().getWatchableObjectInt(CHAKRA_WATCHER));
		properties.setInteger("MaxChakra", this.chakraMax);
		properties.setInteger("ChakraRegenTimer", chakraRegenTimer);		
		
		properties.setInteger("CurrentStamina", this.entity.getDataWatcher().getWatchableObjectInt(STAMINA_WATCHER));
		properties.setInteger("MaxStamina", this.staminaMax);
		properties.setInteger("StaminaRegenTimer", staminaRegenTimer);
		
		
		
/*		properties.setInteger("FireAffinity", this.fireAffinity);
		properties.setInteger("WindAffinity", this.windAffinity);
		properties.setInteger("LightningAffinity", this.lightningAffinity);
		properties.setInteger("EarthAffinity", this.earthAffinity);
		properties.setInteger("WaterAffinity", this.waterAffinity);
		
		properties.setInteger("ChakraControl", this.chakraControl);
		properties.setInteger("ChakraNature", this.chakraNature);*/
		
		//taglist of tags for storing jutsus
		if(jutsus.size() > 0) {
			NBTTagList tagList = new NBTTagList();
			for(int i = 0; i < jutsus.size(); i++) {
				ArrayList<EnumHandSign> al = jutsus.get(i);
				int id = JutsuList.matchHandSignsToId(al);
				if(id >= 0) {
					NBTTagCompound tag = new NBTTagCompound();
					tag.setInteger("jutsu" + i, id);
					tagList.appendTag(tag);
				}
			}
			properties.setTag("JutsuList", tagList);
		}		
		
		compound.setTag(extendedPropertiesName, properties);
	}
	@Override
	public void loadNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = (NBTTagCompound) compound.getTag(extendedPropertiesName);
		
		this.entity.getDataWatcher().updateObject(CHAKRA_WATCHER, properties.getInteger("CurrentChakra"));
		this.chakraMax = properties.getInteger("MaxChakra");
		chakraRegenTimer = properties.getInteger("ChakraRegenTimer");
		
		this.entity.getDataWatcher().updateObject(STAMINA_WATCHER, properties.getInteger("CurrentStamina"));
		this.staminaMax = properties.getInteger("MaxStamina");
		staminaRegenTimer = properties.getInteger("StaminaRegenTimer");
		
/*		this.fireAffinity = properties.getInteger("FireAffinity");
		this.windAffinity = properties.getInteger("WindAffinity");
		this.lightningAffinity = properties.getInteger("LightningAffinity");
		this.earthAffinity = properties.getInteger("EarthAffinity");
		this.waterAffinity = properties.getInteger("WaterAffinity");
		
		this.chakraControl = properties.getInteger("ChakraControl");
		this.chakraNature = properties.getInteger("ChakraNature");*/
		NBTTagList tagList = properties.getTagList("JutsuList", Constants.NBT.TAG_COMPOUND);
		if(tagList.tagCount() > 0) {
			for(int i = 0; i < tagList.tagCount(); i++) {
				NBTTagCompound tag = tagList.getCompoundTagAt(i);
				int jutsuID = tag.getInteger("jutsu" + i);
				ArrayList<EnumHandSign> al = JutsuList.matchIDtoHandSigns(jutsuID);
				jutsus.add(al);
			}
		}
	}

	public void saveMaxChakra(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setInteger("MaxChakra", this.chakraMax);
		properties.setInteger("ChakraRegenTimer", chakraRegenTimer);
		
		compound.setTag(extendedPropertiesName, properties);
	}
	public void loadMaxChakra(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(extendedPropertiesName);
		
		this.chakraMax = properties.getInteger("MaxChakra");
		chakraRegenTimer = properties.getInteger("ChakraRegenTimer");

	}
	
	public void saveJutsuList(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		
		if(jutsus.size() > 0) {
			NBTTagList tagList = new NBTTagList();
			for(int i = 0; i < jutsus.size(); i++) {
				ArrayList<EnumHandSign> al = jutsus.get(i);
				int id = JutsuList.matchHandSignsToId(al);
				if(id >= 0) {
					NBTTagCompound tag = new NBTTagCompound();
					tag.setInteger("jutsu" + i, id);
					tagList.appendTag(tag);
				}
			}
			properties.setTag("JutsuList", tagList);
		}
		
		compound.setTag(extendedPropertiesName, properties);
	}
	public void loadJutsuList(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(extendedPropertiesName);
		
		NBTTagList tagList = properties.getTagList("JutsuList", Constants.NBT.TAG_COMPOUND);
		if(tagList.tagCount() > 0) {
			for(int i = 0; i < tagList.tagCount(); i++) {
				NBTTagCompound tag = tagList.getCompoundTagAt(i);
				int jutsuID = tag.getInteger("jutsu" + i);
				ArrayList<EnumHandSign> al = JutsuList.matchIDtoHandSigns(jutsuID);
				jutsus.add(al);
			}
		}
	}
	
	public void saveLoadedJutsu(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setInteger("LoadedJutsu", this.loadedJutsu.getJutsuId());
		
		compound.setTag(extendedPropertiesName, properties);
	}
	public void loadLoadedJutsu(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(extendedPropertiesName);
		
		this.loadedJutsu = (IJutsu)JutsuList.matchIdtoJutsu(properties.getInteger("LoadedJutsu"));
	}
	
	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
		
	}
	
	//Sync methods
	
	public final void sync() 
	{
		PacketDispatcher.sendTo(new SyncNinjaPropsMessage(this.entity), (EntityPlayerMP) this.entity);
	}
	
	public final void syncMaxChakra() 
	{
		PacketDispatcher.sendTo(new SyncMaxChakraMessage(this.entity), (EntityPlayerMP) this.entity);
	}
	
	public final void syncJutsuList() 
	{
		PacketDispatcher.sendTo(new SyncJutsuListMessage(this.entity), (EntityPlayerMP) this.entity);
	}
	
	public final void activateJutsu() 
	{
		PacketDispatcher.sendToServer(new CastJutsuMessage(this.entity));
	}
	
	//helpers
	
	public void copy(NinjaPropertiesPlayer props) {
		entity.getDataWatcher().updateObject(CHAKRA_WATCHER, props.getCurrentChakra());
		chakraMax = props.chakraMax;
		chakraRegenTimer = props.chakraRegenTimer;
		
		entity.getDataWatcher().updateObject(STAMINA_WATCHER, props.getCurrentStamina());
		staminaMax = props.staminaMax;
		staminaRegenTimer = props.staminaRegenTimer;
	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(NinjaPropertiesPlayer.extendedPropertiesName, new NinjaPropertiesPlayer(player));
	}
}
