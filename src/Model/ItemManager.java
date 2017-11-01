package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ItemManager {
	//List of all the Item
	HashMap<String, Items> itemList = new HashMap();

	public void makeItem(String gameFolder) {
		//Check a specify folder for all room file, then make an object for each one
		String folderPath = "./res/" + gameFolder + "/Item";
		try {	
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
			for (File file : listOfFiles) {
				if (file.isFile()) {
					fileName = file.getName();
					folderPath = "./res/" + gameFolder + "/Item/" + fileName;
					makeItemObject(folderPath);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);                
		}	
	}

	public void makeItemObject(String filePath) {
		//Get the name of the file one at a time, and make an object with them
		try {

			//File Reader
			BufferedReader bufferedReader = 
					new BufferedReader(new FileReader(filePath));

			String fileLine = null;
			//Set Code is the title of the string ex: (Item Name, Item ID)
			String setCode = null;
			Items itemObject = new Items();

			while((fileLine = bufferedReader.readLine()) != null) {
				//If fileLine reads a setCode, it get's replace it's current fileline with the next fileline and sets the setcode;
				switch(fileLine) {
				case "Item Name:":
					setCode = "Item Name";
					fileLine = bufferedReader.readLine();
					break;
				case "Item ID:":
					setCode = "Item ID";
					fileLine = bufferedReader.readLine();
					break;
				case "Item Description:":
					setCode = "Item Description";
					fileLine = bufferedReader.readLine();
					break;
				case "Item Type:":
					setCode = "Item Type";
					fileLine = bufferedReader.readLine();
					break;	
				case "Item Action Value:":
					setCode = "Item Action Value";
					fileLine = bufferedReader.readLine();
					break;	
				case "Item Usage Times:":
					setCode = "Item Hit Percentage";
					fileLine = bufferedReader.readLine();
					break;	
				}	
				if(setCode != null) {
					//Depending on the setcode, it'll set the infromation it got from flieLine
					switch(setCode) {
					case "Item Name":
						itemObject.setItemName(fileLine);
						break;
					case "Item ID":
						itemObject.setItemId(fileLine);
						break;
					case "Item Description":
						itemObject.setItemDesc(fileLine);
						break;
					case "Item Type":
						itemObject.setItemType(Integer.parseInt(fileLine));
						break;	
					case "Item Action Value":
						itemObject.setItemActionValue(fileLine);
						break;	
					case "Item Usage Times":
						itemObject.setItemUsageTime(Integer.parseInt(fileLine));
						break;	
					}
				}
			}
			//After it finish setting up object, object is then sent to a hashmap with Item id as a key
			itemList.put(itemObject.getItemId(), itemObject);

			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + filePath + "'");                
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + filePath+ "'");                  
		}
	}

	//Get Item object from hashmap and prints the toString
	public void loadItemId(String itemId) {
		Items item = itemList.get(itemId);
		System.out.println(item.toString());
	}

}


class Items {
	private String itemName;
	private String itemId;
	private String itemDesc;
	private int itemType;
	private String itemActionValue;
	private int itemUsageTime;

	public Items() {
		this.itemName = "I00";
		this.itemId = "Invalid Item Name";
		this.itemDesc = "Invalid Item Description";
		this.itemType = 00;
		this.itemActionValue = "Invalid Item Action Value";
		this.itemUsageTime = 0;
	}
	public Items(String itemName, String itemId, String itemDesc, int itemType, String itemActionValue,
			int itemUsageTime) {
		super();
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

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public String getItemActionValue() {
		return itemActionValue;
	}

	public void setItemActionValue(String itemActionValue) {
		this.itemActionValue = itemActionValue;
	}

	public int getItemUsageTime() {
		return itemUsageTime;
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
