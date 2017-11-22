package GameObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Players implements Serializable{

	private String playerName;
	private int playerHealth;
	private Items weapon;
	private double playerScore;
	private ArrayList<Items> inventoryList;
	private Rooms currentRoom;
	private Rooms previousRoom;
	private long time;
	private long startTime;

	//Create Default Object
	public Players() {
		this.playerName = "Default Player Name";
		this.playerHealth = 00;
		this.weapon = null;
		this.playerScore = 00;
		this.inventoryList = new ArrayList<Items>();
	}

	//Create Object with set parameter
	public Players(String playerName, int playerHealth, Items weapon, int playerScore,Rooms currentRoom) {
		this.playerName = playerName;
		this.playerHealth = playerHealth;
		this.weapon = weapon;
		this.playerScore = playerScore;
		this.currentRoom = currentRoom;
		this.inventoryList = new ArrayList<Items>();
		this.time = 0;
	}


	//Set and get player name
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerName() {
		return playerName;
	}


	//Set and get player health
	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}
	public int getPlayerHealth() {
		return playerHealth;
	}


	/*Calculate, set and get player's score
	 *
	 *Score is calculated by completition score minus
	 *the time it takes to complete the game
	 *
	 *Called at the end of the game
	 */
	public void calculatePlayerScore() {
		double completitionScore = 50000;
		double timeSubtaction = Double.parseDouble(getGameTime())*10;
		System.out.println("Completition Score: +" + completitionScore + " points");
		System.out.println("Time Duration:" + getGameTime());
		System.out.println("Time Subtraction -" + timeSubtaction + " points");
		setPlayerScore(completitionScore - timeSubtaction);
		System.out.println("Final Score is " + getPlayerScore());
	}
	public void setPlayerScore(double playerScore) {
		this.playerScore = playerScore;
	}
	public double getPlayerScore() {
		return playerScore;
	}


	/*Set the current room, and the previous run incase you want to run
	 *away from the monster
	 *
	 *Parameter: needs id of the room, and the list 
	 *of all the room in the game
	 *
	 *Get the current room object or previous room object
	 */
	public void setCurrentRoom(Rooms room) {
		this.previousRoom = getCurrentRoom();
		this.currentRoom = room;
	}
	public Rooms getCurrentRoom() {
		return currentRoom;
	}
	public Rooms getPreviousRoom() {
		return previousRoom;
	}


	/*Add item to inventory
	 *Check to see if there is another item of the same property
	 *and if there is, it increment that item amount by 1
	 *else it add the item to the inventory
	 * 
	 *Remove item from inventory
	 *if the item amount is greater then 1 it decrease the amount
	 *else it remove the item entirely
	 *
	 *Get inventory arrayList
	 *
	 *Get inventory string
	 *return a string of all the item you have
	 *in your inventory including the item amount
	 */
	public void addItemToInventory(Items item) {
		boolean dupItem = false;
		int dupItemBound = 0;
		for(int x = 0; x < inventoryList.size(); x++) {
			if(inventoryList.get(x).equals(item)) {
				dupItem = true;
				dupItemBound = x;
			}
		}

		if(dupItem){
			inventoryList.get(dupItemBound).increaseItemAmount();
		}else {
			this.inventoryList.add(item);
		}		

	}
	public void removeItemFromInventory(Items item) {	
		/*Search through the inventory list to check to see if
		 *the item exist in your inventory and if it does then
		 *it make remove as true, else it mark remove as false
		 *and prompt an error that the item doesn't exist in your
		 *inventory
		 */
		boolean remove = false;
		search:		
			for(int x = 0; x < inventoryList.size(); x++) {
				if(inventoryList.get(x).equals(item)) {
					if(inventoryList.get(x).getItemAmount() > 1) {
						inventoryList.get(x).decreaseItemAmount();
						remove = true;
					}
					else {
						inventoryList.remove(x);
					}
					break search;
				}
			}
		if(!remove) {
			System.out.println("Error: Item " + item.getItemName() + "/" + item.getItemId() + " does not exist in player inventory");
		}
	}
	public ArrayList<Items> getInventoryList() {
		return inventoryList;
	}
	public String getInventoryListString() {
		String inventoryString = "";
		for(Items item: inventoryList) {
			inventoryString += item.getItemName() + " x" + item.getItemAmount() + "\n";
		}
		return inventoryString;
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
			removeItemFromInventory(item);
		}
		else{
			unequipWeapon();
			equipWeapon(item);		
		}
	}
	public void unequipWeapon() {		
		addItemToInventory(getWeapon());
		this.weapon = null;
	}
	public Items getWeapon() {
		return weapon;
	}
	


	/* Check if weapon has ammo
	 * 
	 * Get the amount of ammo a weapon has
	 */
	public boolean weaponHasAmmo() {
		String currentEquipWeaponId = weapon.getItemId();

		boolean foundAmmo = false;
		search:		
			for(int x = 0; x < inventoryList.size(); x++) {
				Items item = inventoryList.get(x);
				if(item.getItemType().equalsIgnoreCase("ammo")) {
					String itemAmmoId = "I" + item.getItemActionValue();
					if(itemAmmoId.equalsIgnoreCase(currentEquipWeaponId)) {
						foundAmmo = true;
						break search;
					}
				}
			}

		if(!foundAmmo) {
			return false;
		}else {
			return true;
		}
	}
	public int getWeaponAmmo() {
		String currentEquipWeaponId = weapon.getItemId();

		for(int x = 0; x < inventoryList.size(); x++) {
			Items item = inventoryList.get(x);
			if(item.getItemType().equalsIgnoreCase("ammo")) {
				String itemAmmoId = "I" + item.getItemActionValue();
				if(itemAmmoId.equalsIgnoreCase(currentEquipWeaponId)) {
					return item.getItemAmount();
				}
			}
		}
		return 0;
	}


	//Increase or decrease health depending on 
	public void takeDmg(int dmg) {
		this.playerHealth = (playerHealth - dmg);
	}
	public void healHealth(int health) {
		this.playerHealth = (playerHealth + health);
	}

	/*Set the start time when the game begin or resume
	 *then after it end or when saving, the end game time is 
	 *record, then add time to total time elapsed
	 *
	 *return time in decimal format
	 * */
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
