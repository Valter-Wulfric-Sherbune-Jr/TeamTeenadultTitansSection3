package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class CharacterManager implements ManagerInterface{

	//List of all the Item
	private HashMap<String, Characters> characterList = new HashMap<String, Characters>();
	private String gameFolder = "/Character";
	private String gameSubFolder = null;

	@Override
	public void makeList(String gameFolder) {
		String folderPath = gameFolder + gameSubFolder;
		try {	
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
			for (File file : listOfFiles) {
				if (file.isFile()) {
					fileName = file.getName();
					folderPath = gameFolder + gameSubFolder + "/" + fileName;
					makeListObject(folderPath);
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error in "+ gameSubFolder + ":/n" + e.toString());
		}	
	}

	@Override
	public void makeListObject(String folderPath) {
		
	}

	@Override
	public void loadListId(String Id) {
		Characters character = characterList.get(Id);
		System.out.println(character.toString());
	}
	
	@Override
	public void setGameFolder(String gameFolder) {
		this.gameFolder = "./res/" + gameFolder;
	}

	@Override
	public String getGameFolder() {
		return gameFolder;
	}

	@Override
	public String getGameSubFolder() {
		return gameSubFolder;
	}

	@Override
	public HashMap<String, Characters> getList() {
		return characterList;
	}

}

class Characters implements Serializable{

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
