package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager implements ManagerInterface{


	@Override
	public void makeList(String gameFolder) {
		// TODO Auto-generated method stub

	}
	@Override
	public void makeListObject(String filePath) {
		// TODO Auto-generated method stub

	}
	@Override
	public void loadListId(String Id) {
		// TODO Auto-generated method stub

	}
	@Override
	public void setGameFolder(String gameFolder) {
		// TODO Auto-generated method stub

	}
	@Override
	public String getGameFolder() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getGameSubFolder() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, ?> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadMonsterList() {

	}
	public void newGame() {

	}
	public void saveGame() {

	}

	public void loadGame() {

	}

}

class SaveDate{

	private Characters charData;
	private HashMap<String, Items> itemList;
	private HashMap<String, Monsters> monsterList;
	private ArrayList<Items> inventoryList;


}

class Players implements Serializable{

	private String playerId;
	private String playerName;
	private int playerHealth;
	private String weapon;
	private int playerScore;
	private ArrayList<Items> inventoryList;


	public Players() {
		this.playerId = "00";
		this.playerName = "Default Player Name";
		this.playerHealth = 00;
		this.weapon = null;
		this.playerScore = 00;
		this.inventoryList = new ArrayList<Items>();
	}

	public Players(String playerId, String playerName, int playerHealth, String weapon, int playerScore,
			ArrayList<Items> inventoryList) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerHealth = playerHealth;
		this.weapon = weapon;
		this.playerScore = playerScore;
		this.inventoryList = inventoryList;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	public void setWeapon(String weaponId) {
		
		this.weapon = weapon;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public String getPlayerId() {
		return playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getPlayerHealth() {
		return playerHealth;
	}

	public String getWeapon() {
		return weapon;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public ArrayList<Items> getInventoryList() {
		return inventoryList;
	}

	public void increaseScore(int score) {
		playerScore -= score;
	}
	public void decreaseScore(int score) {
		playerScore += score;
	}
	public void addItem(String itemId) {
		try{
			ItemManager itemList = new ItemManager();
			itemList.getList().get(itemId);
			inventoryList.add(itemList.getList().get(itemId));
		}catch(Exception e) {
			System.out.println("Error Adding Item to Inventory");
		}
	}



	protected Players(int characterIDValue, char characterID, String name, Rooms currentRoom){
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
