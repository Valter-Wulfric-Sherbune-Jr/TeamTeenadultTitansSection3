package Model;

import java.io.Serializable;

public abstract class Character implements Serializable{
	
	protected int characterIDValue;
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
