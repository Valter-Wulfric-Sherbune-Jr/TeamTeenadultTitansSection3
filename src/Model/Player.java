package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Controller.Game;
import Controller.Menu;

public class Player extends Character implements Serializable{

	private static String username;
	private static int score;
	private static int userID;
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	public Player(int characterIDValue, char characterID, String name, Rooms currentRoom) {
		super(characterIDValue, characterID, name, currentRoom);
		// TODO Auto-generated constructor stub
	}
	
	public static int getScore() {
		return score;
	}
	public static void setScore(int score) {
		Player.score = score;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		Player.username = username;
	}
	public static int getUserID() {
		return userID;
	}
	public static void setUserID(int userID) {
		Player.userID = userID;
	}
	public static ArrayList<Item> getInventory() {
		return inventory;
	}
	public static void setInventory(ArrayList<Item> inventory) {
		Player.inventory = inventory;
	}
	public static void increaseScore(int x)
	{
		score += x;
	}

	public static void decreaseScore(int x)
	{
		score -= x;
	}
	
	
	public static void attack(Player p, Monsters m){
		
	}
	
	public void escape(Monsters m){
		System.out.println(m.getExitStatement());
		// needs logic for selecting a valid room
	}
	
	public String readInventory(){
		if (!inventory.isEmpty())
		{
			return "Alright, this is what you've got" + inventory.toString();
		} else
		{
			return "It doesn't look like you've got anything there.";
		}
	}
	public String toString(){
		String in = "";
		for (Item i : inventory)
		{
			in += ": " + i.getName();
		}
		return in;
	}
	

}