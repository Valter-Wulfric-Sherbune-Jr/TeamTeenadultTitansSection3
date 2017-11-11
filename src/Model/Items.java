package Model;

import java.io.Serializable;

class Items implements Serializable{
	private String itemName;
	private String itemId;
	private String itemDesc;
	private String itemType;
	private String itemActionValue;
	private int itemUsageTime;

	public Items() {
		this.itemName = "I00";
		this.itemId = "Invalid Item Name";
		this.itemDesc = "Invalid Item Description";
		this.itemType = "Invalid";
		this.itemActionValue = "Invalid Item Action Value";
		this.itemUsageTime = 0;
	}

	public Items(String itemName, String itemId, String itemDesc, String itemType, String itemActionValue,
			int itemUsageTime) {
		this.itemName = itemName;
		this.itemId = itemId;
		this.itemDesc = itemDesc;
		this.itemType = itemType;
		this.itemActionValue = itemActionValue;
		this.itemUsageTime = itemUsageTime;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemId() {
		return itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public String getItemType() {
		return itemType;
	}

	public int getItemActionValue() {
		return Integer.parseInt(itemActionValue);
	}

	public int getItemUsageTime() {
		return itemUsageTime;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}	

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setItemActionValue(String itemActionValue) {
		this.itemActionValue = itemActionValue;
	}

	public void setItemUsageTime(int itemUsageTime) {
		this.itemUsageTime = itemUsageTime;
	}

	public String toString() {
		String returnString = "";

		//Add Guide Line
		returnString += "---------------------------------------\n";

		//Add Item Name
		returnString += "Item Name:\n" + getItemName() + "\n\n";

		//Add Item ID
		returnString += "Item ID:\n" + getItemId() + "\n\n";

		//Add Item Description
		returnString += "Item Description:\n" + getItemDesc() + "\n\n";

		//Add Item Health
		returnString += "Item Type:\n" + getItemType() + "\n\n";

		//Add Item Damage
		returnString += "Item Action Value:\n" + getItemActionValue() + "\n\n";

		//Add Item Hit Percentage
		returnString += "Item Usage Time:\n" + getItemUsageTime() + "\n";

		//Add Guide Line
		returnString += "---------------------------------------";

		return returnString;
	}

}
