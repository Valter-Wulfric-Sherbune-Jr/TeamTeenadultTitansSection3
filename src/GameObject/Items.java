package GameObject;

import java.io.Serializable;

public class Items implements Serializable{
	private String itemName;
	private String itemId;
	private String itemDesc;
	private String itemType;
	private String itemActionValue;
	private int itemAmount;

	//Create Default Object
	public Items() {
		this.itemName = "I00";
		this.itemId = "Invalid Item Name";
		this.itemDesc = "Invalid Item Description";
		this.itemType = "Invalid";
		this.itemActionValue = "Invalid Item Action Value";
		this.itemAmount = 0;
	}

	//Create Object with set parameter
	public Items(String itemName, String itemId, String itemDesc, String itemType, String itemActionValue,
			String itemAmount) {
		this.itemName = itemName;
		this.itemId = itemId;
		this.itemDesc = itemDesc;
		this.itemType = itemType;
		this.itemActionValue = itemActionValue;
		this.itemAmount = 1;
	}

	//Set and get item name
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}	
	public String getItemName() {
		return itemName;
	}


	//Set and get item id
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemId() {
		return itemId;
	}


	//Set and get item description
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getItemDesc() {
		return itemDesc;
	}


	//Set and get item type (5 type: Weapon, Utility, Healing, Throwable, Ammo)
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemType() {
		return itemType;
	}


	/*Set item action value
	 * (WeaponType)
	 * Weapon: Weapon Damage
	 * Utility: Null
	 * Ammo: Weapon ID
	 * Healing: Healing Amount
	 * Throwable: Weapon Damage
	 * 
	 */
	
	/*Get item action value
	 * Weapon: return int
	 * Utility: can't return null so return 0 (You should never have to use this)
	 * Ammo: can't return string, so encode the string into an int
	 * Healing: return int
	 * Throwable: return int
	 * Default: Prompt an error and return 0
	 * */
	public void setItemActionValue(String itemActionValue) {
		if(itemActionValue.equals("null")) {
			this.itemActionValue = "-1";
		}
		else {
			this.itemActionValue = itemActionValue;
		}
		
	}
	public int getItemActionValue() {
		switch(itemType.toLowerCase()) {
		case "weapon":
			return Integer.parseInt(itemActionValue);
		case "utility":
			return 0;
		case "ammo":
			return Integer.parseInt(itemActionValue.substring(itemActionValue.indexOf("I")+1));
		case "healing":
			return Integer.parseInt(itemActionValue);
		case "throwable":
			return Integer.parseInt(itemActionValue);
		default:
			System.out.print("Error: Unknown weapon type (" + itemName + "/" + itemId + ")");
			return 0;
		}
	}

	
	// Set and get how show how many of the same item you have
	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}
	public int getItemAmount() {
		return itemAmount;
	}
	public void increaseItemAmount() {
		this.itemAmount++;
	}
	public void decreaseItemAmount() {
		this.itemAmount--;
	}




	public String toString() {
		String returnString = "";

		//Add Guide Line
		returnString += "--------------------------------------------------\n";

		//Add Item Name
		returnString += getItemName() + " - ";

		//Add Item Type
		returnString += "Type: " + getItemType() + " - ";
		
		//Add Item Function
		if(getItemType().equalsIgnoreCase("weapon") || getItemType().equalsIgnoreCase("throwable")){
			returnString += "Inflicts " + getItemActionValue() + " damage\n\n";
		}
		else if(getItemType().equalsIgnoreCase("healing")){
			returnString += "Recovers " + getItemActionValue() + " health\n\n";
		}
		
		//Add Item Description
		returnString += getItemDesc() + "\n\n";

		//Add Item Amount
		returnString += "Amount: " + getItemAmount();

		return returnString;
	}

}
