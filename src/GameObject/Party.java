package GameObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Party implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3225817561434147444L;
	private ArrayList<Items> partyInventory;
	private ArrayList<Players> partyMember;
	private Rooms currentRoom;
	private Rooms previousRoom;
	private long time;
	private long startTime;

	public Party() {
		this.partyInventory = new ArrayList<Items>();
		this.partyMember = new ArrayList<Players>();
	}


	public Party(ArrayList<Items> partyInventory, ArrayList<Players> partyMember,
			Rooms currentRoom, Rooms previousRoom, long time, long startTime) {
		this.partyInventory = partyInventory;
		this.partyMember = partyMember;
		this.currentRoom = currentRoom;
		this.previousRoom = previousRoom;
		this.time = time;
		this.startTime = startTime;
	}

	public void setPartyPlayers(ArrayList<Players> partyPlayers) {
		this.partyMember = partyPlayers;
	}

	public void addPartyMember(Players player) {
		partyMember.add(player);
	}

	public void removePartyMember(Players removePlayer) {
		for(int x = 0; x < partyMember.size(); x++) {
			if(partyMember.get(x).equals(removePlayer)) {
				partyMember.remove(x);
				break;
			}
		}
	}

	public ArrayList<Players> getPartyMember() {
		return partyMember;
	}


	public void setPartyInventory(ArrayList<Items> partyInventory) {
		this.partyInventory = partyInventory;
	}
	public ArrayList<Items> getPartyInventory() {
		return partyInventory;
	}


	public Rooms getCurrentRoom() {
		return currentRoom;
	}
	public void setCurrentRoom(Rooms currentRoom) {
		this.previousRoom = getCurrentRoom();
		this.currentRoom = currentRoom;
	}
	public Rooms getPreviousRoom() {
		return previousRoom;
	}
	public void setPreviousRoom(Rooms previousRoom) {
		this.previousRoom = previousRoom;
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
		System.out.println(time);
		this.time += (System.currentTimeMillis() - startTime) / 1000;
		System.out.println(time);
	}
	public String getGameTime() {
		DecimalFormat form = new DecimalFormat("##.##");
		return form.format(time);
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
		for(int x = 0; x < partyInventory.size(); x++) {
			if(partyInventory.get(x).equals(item)) {
				dupItem = true;
				dupItemBound = x;
			}
		}

		if(dupItem){
			partyInventory.get(dupItemBound).increaseItemAmount();
		}else {
			this.partyInventory.add(item);
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
			for(int x = 0; x < partyInventory.size(); x++) {
				if(partyInventory.get(x).equals(item) && !item.getItemType().equalsIgnoreCase("Ammo")) {
					if(partyInventory.get(x).getItemAmount() > 1) {
						partyInventory.get(x).decreaseItemAmount();
						remove = true;
					}
					else {
						partyInventory.remove(x);
						remove = true;
					}
					break search;
				}else if(partyInventory.get(x).equals(item) && item.getItemType().equalsIgnoreCase("Ammo")){
					partyInventory.remove(x);
				}
			}
		if(!remove) {
			System.out.println("Error: Item " + item.getItemName() + "/" + item.getItemId() + " does not exist in player inventory");
		}
	}

	public String getInventoryListString() {
		String inventoryString = "";
		for(Items item: partyInventory) {
			inventoryString += item.getItemName() + " x" + item.getItemAmount() + "\n";
		}
		return inventoryString;
	}

	//Return the amount of ammo a weapon has
	public int getWeaponAmmo(Items weapon) {
		for(int x = 0; x < partyInventory.size(); x++) {
			Items item = partyInventory.get(x);
			if(item.getItemType().equalsIgnoreCase("ammo")) {
				String itemAmmoId = "I";
				if(item.getItemActionValue() < 10) {
					itemAmmoId += ("0" + item.getItemActionValue());
				}
				else {
					itemAmmoId += item.getItemActionValue();
				}
				if(itemAmmoId.equalsIgnoreCase(weapon.getItemId())) {
					return item.getItemAmount();
				}
			}
		}
		return 0;
	}
	public void useWeaponAmmo(Items weapon) {
		for(int x = 0; x < partyInventory.size(); x++) {
			Items item = partyInventory.get(x);
			if(item.getItemType().equalsIgnoreCase("ammo")) {
				String itemAmmoId = "I";
				if(item.getItemActionValue() < 10) {
					itemAmmoId += ("0" + item.getItemActionValue());
				}
				else {
					itemAmmoId += item.getItemActionValue();
				}
				if(itemAmmoId.equalsIgnoreCase(weapon.getItemId())) {
					item.decreaseItemAmount();
				}
			}
		}
	}	

}
