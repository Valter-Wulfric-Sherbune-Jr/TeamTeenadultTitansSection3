package Model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Players implements Serializable{

	private String playerName;
	private int playerHealth;
	private Items weapon;
	private int playerScore;
	private ArrayList<Items> inventoryList;
	private Rooms currentRoom;
	private Rooms previousRoom;
	private long time;
	private long startTime;


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
		this.time = 0;
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
		this.previousRoom = getCurrentRoom();
		this.currentRoom = RoomList.get(roomId);
	}
	
	public Rooms getPreviousRoom() {
		return previousRoom;
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
			if(inventoryList.get(x).getItemId().equalsIgnoreCase(itemId)) {
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
			removeItemFromInventory(itemId);
		}
		else{
			unequipWeapon(ItemList);
			equipWeapon(itemId,ItemList);		
		}
	}
	
	public void unequipWeapon(HashMap<String, Items> ItemList) {		
		addItemToInventory(getWeapon());
		this.weapon = null;
	}
	
	public void startGameTime() {
		this.startTime = System.currentTimeMillis();
	}
	public void endGameTime() {
		this.time += (System.currentTimeMillis() - startTime) / 1000;
	}
	public String getGameTime() {
		DecimalFormat form = new DecimalFormat("##.##");
		return form.format(time);
	}
}
