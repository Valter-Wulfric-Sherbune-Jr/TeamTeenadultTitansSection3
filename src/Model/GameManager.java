package Model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameManager{

	private MonsterSubManager monsterInfo;
	private RoomSubManager roomInfo;
	private ItemSubManager itemInfo;
	private Players player;
	private Scanner input = new Scanner(System.in);
	private String gameFolder = "";
	private ArrayList<String> gameList = new ArrayList<String>();


	public void newGame() {
		getAllGameFolder();
		chooseGameFolder();
		setSubManagerList(gameFolder);
		Players player = new Players();
		saveGame();
		
		private String playerId;
		private String playerName;
		private int playerHealth;
		private String weapon;
		private int playerScore;
		private ArrayList<Items> inventoryList;
		
	}

	/*Ask the user to choose a game folder then set the game folder*/
	public void chooseGameFolder() {
		boolean loop = true;
		while(loop == true) {
			System.out.println("Choose the game folder that you want to play:");	
			for(String folder : gameList) {
				System.out.println(folder);
			}	
			String userFolderChoice = getUserInput();
			for(String folder : gameList) {
				if(userFolderChoice.equalsIgnoreCase(folder)) {
					gameFolder = folder;
					loop = true;
					break;
				}
			}
			if(gameFolder.equals("")) {
				System.out.println("\nYou did not choose a valid game folder\n");
			}	
		}
	}
	
	/*Add all game folder into an arraylist*/
	public void getAllGameFolder() {	
		try {
			File folder = new File("./res/");
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					gameList.add(file.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*After a user picks a gamefolder, it loads all the
	asset from that game folder*/
	public void setSubManagerList(String gameFolder) {
		monsterInfo = new MonsterSubManager();
		roomInfo = new RoomSubManager();
		itemInfo = new ItemSubManager();

		monsterInfo.makeList(gameFolder);
		itemInfo.makeList(gameFolder);
		roomInfo.makeList(gameFolder);
	}

	
	
	public void loadListId(String Id) {
		// TODO Auto-generated method stub

	}

	public void setGameFolder(String gameFolder) {
		this.gameFolder = gameFolder;
	}
	
	public String getGameSubFolder() {
		return gameFolder;
	}

	public HashMap<String, ?> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadMonsterList() {

	}

	public String getUserInput() {
		System.out.print("\nInput:\n> ");
		return input.nextLine();
	}
	
	public void saveGame() {

	}

	public void loadGame() {

	}

}

class SaveDate{

	private String dataId;
	private String gameFolder;
	private HashMap<String, Items> itemList;
	private HashMap<String, Monsters> monsterList;
	private ArrayList<Items> inventoryList;

	public SaveDate() {
	}

	public SaveDate(String dataId, HashMap<String, Items> itemList, HashMap<String, Monsters> monsterList,
			ArrayList<Items> inventoryList) {
		this.dataId = dataId;
		this.itemList = itemList;
		this.monsterList = monsterList;
		this.inventoryList = inventoryList;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public HashMap<String, Items> getItemList() {
		return itemList;
	}

	public void setItemList(HashMap<String, Items> itemList) {
		this.itemList = itemList;
	}

	public HashMap<String, Monsters> getMonsterList() {
		return monsterList;
	}

	public void setMonsterList(HashMap<String, Monsters> monsterList) {
		this.monsterList = monsterList;
	}

	public ArrayList<Items> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(ArrayList<Items> inventoryList) {
		this.inventoryList = inventoryList;
	}

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

	public Players(String playerId, String playerName, int playerHealth, String weapon, int playerScore) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerHealth = playerHealth;
		this.weapon = weapon;
		this.playerScore = playerScore;
		this.inventoryList = inventoryList;
	}
	
	public Players(String playerId, String playerName, int playerHealth, String weapon, int playerScore,
			ArrayList<Items> inventoryList) {
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
			ItemSubManager itemList = new ItemSubManager();
			itemList.getList().get(itemId);
			inventoryList.add(itemList.getList().get(itemId));
		}catch(Exception e) {
			System.out.println("Error Adding Item to Inventory");
		}
	}
}
