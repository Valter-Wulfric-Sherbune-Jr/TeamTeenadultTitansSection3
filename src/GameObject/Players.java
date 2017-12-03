package GameObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Players implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4732377618852013083L;
	private String playerName;
	private String playerId;
	private double playerMaxHealth;
	private double playerCurrentHealth;
	private Items weapon;
	private String playerIcon;
	private String playerSelect;
	private String playerDeath;

	//Create Default Object
	public Players() {
		this.playerId = "P00";
		this.playerName = "Default Player Name";
		this.playerMaxHealth = 00;
		this.playerCurrentHealth = playerMaxHealth;
		this.weapon = null;
	}

	//Create Object with set parameter
	public Players(String playerName, String playerId, int playerMaxHealth, Items weapon,Rooms currentRoom) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerMaxHealth = playerMaxHealth;
		this.playerCurrentHealth = playerMaxHealth;
		this.weapon = weapon;
	}

	
	public String getPlayerIcon() {
		return playerIcon;
	}

	public void setPlayerIcon(String playerIcon) {
		this.playerIcon = playerIcon;
	}

	public String getPlayerSelect() {
		return playerSelect;
	}

	public void setPlayerSelect(String playerSelect) {
		this.playerSelect = playerSelect;
	}

	public String getPlayerDeath() {
		return playerDeath;
	}

	public void setPlayerDeath(String playerDeath) {
		this.playerDeath = playerDeath;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	//Set and get player name
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerName() {
		return playerName;
	}


	//Set and get player health
	public void setPlayerMaxHealth(double playerMaxHealth) {
		this.playerMaxHealth = playerMaxHealth;
	}
	public double getPlayerMaxHealth() {
		return playerMaxHealth;
	}
	public void setPlayerCurrentHealth(double playerCurrentHealth) {
		this.playerCurrentHealth = playerCurrentHealth;
	}
	public double getPlayerCurrentHealth() {
		return playerCurrentHealth;
	}
	public void takeDmg(int dmg) {
		if((playerCurrentHealth - dmg) <= 0) {
			this.playerCurrentHealth = 0;
		}else {
			this.playerCurrentHealth -= dmg;
		}
	}
	public void healHealth(int health) {
		if((playerCurrentHealth + health) >= playerMaxHealth) {
			this.playerCurrentHealth = playerMaxHealth;
		}else {
			this.playerCurrentHealth += health;
		}
	}

	/*Set, equip, unequip, and get weapon
	 *
	 */
	public void setWeapon(Items item) {
		this.weapon = item;
	}
	public void equipWeapon(Items item) {
		if(weapon == null) {
			setWeapon(item);
			//removeItemFromInventory(item);
		}
		else{
			unequipWeapon();
			equipWeapon(item);		
		}
	}
	public void unequipWeapon() {		
		//addItemToInventory(getWeapon());
		this.weapon = null;
	}
	public Items getWeapon() {
		return weapon;
	}





}
