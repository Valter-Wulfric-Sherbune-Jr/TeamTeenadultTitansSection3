package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class CharacterManager {
	//List of all the Item
		HashMap<String, Items> itemList = new HashMap();

		public void makeCharacter(String gameFolder) {
			//Check a specify folder for all room file, then make an object for each one
			String folderPath = "./res/" + gameFolder + "/Character";
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
		
		public HashMap getItemList() {
			return itemList;
		}
}

class Character implements Serializable{
	
	protected static int characterIDValue;
	protected char characterID;
	protected String name;
	protected static Rooms currentRoom;
	protected static Rooms room;
	
	protected Character(){
		this.characterIDValue = 00;
		this.characterID = 'a';
		this.name = null;
		Character.currentRoom = null;
	}
	protected Character(int characterIDValue, char characterID, String name, Rooms currentRoom){
		this.characterIDValue = characterIDValue;
		this.characterID = characterID;
		this.name = name;
		Character.currentRoom = currentRoom;
	}
	public int getCharacterIDValue() {
		return characterIDValue;
	}
	public void setCharacterIDValue(int characterIDValue) {
		this.characterIDValue = characterIDValue;
	}
	public char getCharacterID() {
		return characterID;
	}
	public void setCharacterID(char characterID) {
		this.characterID = characterID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static Rooms getCurrentRoom() {
		return currentRoom;
	}
	public static void setCurrentRoom(Rooms currentRoom) {
		Character.currentRoom = currentRoom;
	}
	public static Rooms getRoom() {
		return room;
	}
	public static void setRoom(Rooms room) {
		Character.room = room;
	}
}
