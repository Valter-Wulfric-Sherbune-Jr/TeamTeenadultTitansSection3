package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Players implements Serializable{

	private String playerName;
	private int playerHealth;
	private Items weapon;
	private int playerScore;
	private ArrayList<Items> inventoryList;
	private Rooms currentRoom;


	public Players() {
		this.playerName = "Default Player Name";
		this.playerHealth = 00;
		this.weapon = null;
		this.playerScore = 00;
		this.inventoryList = new ArrayList<Items>();
	}

	public Players(String playerName, int playerHealth, Items weapon, int playerScore,Rooms currentRoom) {
		this.playerName = playerName;
		this.playerHealth = playerHealth;
		this.weapon = weapon;
		this.playerScore = playerScore;
		this.currentRoom = currentRoom;
		this.inventoryList = new ArrayList<Items>();
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}
	
	public int getPlayerHealth() {
		return playerHealth;
	}
	
	public void setWeapon(String weaponId, HashMap<String, Items> ItemList) {
		this.weapon = ItemList.get(weaponId);
	}
	
	public Items getWeapon() {
		return weapon;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public void increaseScore(int score) {
		this.playerScore -= score;
	}
	
	public void decreaseScore(int score) {
		this.playerScore += score;
	}
	
	public Rooms getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(String roomId, HashMap<String, Rooms> RoomList) {
		this.currentRoom = RoomList.get(roomId);
	}

	public ArrayList<Items> getInventoryList() {
		return inventoryList;
	}

	public void addItemToInventory(Items item) {
		this.inventoryList.add(item);
	}
	
	public void removeItemFromInventory(String itemId) {
		boolean remove = false;
		search:
		for(int x = 0; x < inventoryList.size(); x++) {
			if(inventoryList.get(x).getItemId() == itemId) {
				inventoryList.remove(x);
				remove = true;
				break search;
			}
		}
		if(!remove) {
			System.out.println("Error item " + itemId + " does not exist in player inventory");
		}
	}
	
	public void equipWeapon(String itemId, HashMap<String, Items> ItemList) {
		if(weapon == null) {
			setWeapon(itemId, ItemList);
		}
		else if(weapon != null) {
			unequipWeapon(ItemList);
		}
		else {
			removeItemFromInventory(itemId);
			equipWeapon(itemId,ItemList);
		}
	}
	
	public void unequipWeapon(HashMap<String, Items> ItemList) {		
		addItemToInventory(getWeapon());
		this.weapon = null;
	}
}
