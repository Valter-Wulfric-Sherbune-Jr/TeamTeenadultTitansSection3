package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameManager{

	private HashMap<String, Items> itemList;
	private HashMap<String, Monsters> monsterList;
	private HashMap<String, Rooms> roomList;

	private Players player;
	private Scanner input = new Scanner(System.in);
	private String gameFolder = "";
	private ArrayList<String> gameList = new ArrayList<String>();

	private HashMap<String, SaveData> saveList = new HashMap<String, SaveData>();

	public void newGame() {
		getAllGameFolder();
		chooseGameFolder();
		makeSaveList();
		System.out.println("Please choose your name:");
		String playerName = getUserInput();
		player = new Players(playerName,100,itemList.get("I01"),0,roomList.get("01"));
		//Debug Save later
		//saveGame("S1",player);

		System.out.println("New Game Created");
		System.out.println("\nPrivate your mission is to investigate this building and clear out all enemy");
		System.out.println("You Jumped out of the plan and landed on the roof");

		System.out.println(roomList.get("01").toString());
		action("01");
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
					gameFolder = "./res/" + folder;
					loop = false;
					break;
				}
			}
			if(gameFolder.equals("")) {
				System.out.println("\nYou did not choose a valid game folder\n");
			}	
		}

		MonsterSubManager monsterInfo = new MonsterSubManager();
		RoomSubManager roomInfo = new RoomSubManager();
		ItemSubManager itemInfo = new ItemSubManager();

		monsterInfo.makeList(gameFolder);
		itemInfo.makeList(gameFolder);
		roomInfo.makeList(gameFolder);

		monsterList = monsterInfo.getList();
		roomList = roomInfo.getList();
		itemList = itemInfo.getList();

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

	public void action(String roomId) {
		if(player.getCurrentRoom().hasItem == false) {
			System.out.println("What will you do?");
			System.out.println("1. Move");
			System.out.println("2. Search Room");
			System.out.println("3. Check Inventory");

			String userinput = getUserInput();
			switch(userinput) {
			case "Move":
				move(roomId);
				break;
			case "Search Room":
				System.out.println("There is nothing in this room\n");
				action(roomId);
				break;
			case "Check Inventory":
				if(player.getInventoryList() == null || player.getInventoryList().size() == 0) {
					System.out.println("You have nothing\n");
				}
				else {
					for(Items item : player.getInventoryList()) {
						System.out.println(item.getItemName());
					}
				}
				action(roomId);
				break;
			default:
				System.out.println("Wrong Command inputed\n");
				action(roomId);
				break;
			}
		}
		else if(player.getCurrentRoom().hasItem == true) {
			System.out.println("What will you do?");
			System.out.println("1. Move");
			System.out.println("2. Search Room");
			System.out.println("3. Check Inventory");

			String userinput = getUserInput();
			switch(userinput) {
			case "Move":
				move(roomId);
				break;
			case "Search Room":
				System.out.println("You found a " + roomList.get(roomId).getRoomItem().getItemName());
				System.out.println("You added it into your inventory");
				player.addItem(roomList.get(roomId).getRoomItem().getItemName());
				action(roomId);
				break;
			case "Check Inventory":
				if(player.getInventoryList() == null || player.getInventoryList().size() == 0) {
					System.out.println("You have nothing\n");
				}
				else {
					for(Items item : player.getInventoryList()) {
						System.out.println(item.getItemName());
					}
				}
				action(roomId);
				break;
			default:
				System.out.println("Wrong Command inputed\n");
				action(roomId);
				break;
			}
		}
	}

	public String checkRoom(String id) {
		if(roomList.get(id).getRoomMonster() != null) {
			return "Monster Room";
		}
		return null;
	}

	public void makeSaveList() {
		String folderPath = "./res/Save/";
		try {	
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.isFile()) {
					ObjectInputStream input = new ObjectInputStream(new FileInputStream(folderPath + file));
					SaveData obj =  (SaveData)(input.readObject());
					String saveId = obj.getSaveId();
					saveList.put(saveId, obj);	
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error in "+ "Save" + ":/n" + e.toString());
		}
	}



	public String getUserInput() {
		System.out.print("\nInput:\n> ");
		return input.nextLine();
	}

	public void saveGame(String saveId,Players player) {
		try {
			SaveData save = new SaveData(saveId,gameFolder,player,itemList,monsterList,roomList);
			if(saveList.isEmpty()) {
				saveId = "S1";
			}
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./res/Save/" + saveId + ".dat"));
			output.writeObject(save);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadGame() {

	}
	
	public void move(String roomId) {
		Rooms room = roomList.get(roomId);
		System.out.print("What direction you wanna move?\n");
		System.out.println(room.getRoomConnection());
		Scanner input = new Scanner(System.in);
		String direction = getUserInput();
		HashMap<String, String> map = room.getRoomNavigation();
		player.setCurrentRoom(roomList.get(map.get(direction)));
		System.out.println(roomList.get(map.get(direction)).toString());
		action(map.get(direction));
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

	public HashMap<String, Rooms> getRoomList() {
		return roomList;
	}

	public void setRoomList(HashMap<String, Rooms> roomList) {
		this.roomList = roomList;
	}



}

class SaveData implements Serializable{

	private String saveId;
	private String gameFolder;
	private Players playerData;
	private HashMap<String, Items> itemList;
	private HashMap<String, Monsters> monsterList;
	private HashMap<String, Rooms> roomList;

	public SaveData() {
	}

	public SaveData(String saveId, String gameFolder, Players playerData, HashMap<String, Items> itemList,
			HashMap<String, Monsters> monsterList, HashMap<String, Rooms> roomList) {
		super();
		this.saveId = saveId;
		this.gameFolder = gameFolder;
		this.playerData = playerData;
		this.itemList = itemList;
		this.monsterList = monsterList;
		this.roomList = roomList;
	}



	public String getSaveId() {
		return saveId;
	}

	public void setsaveId(String saveId) {
		this.saveId = saveId;
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

}

class Players implements Serializable{

	private String playerName;
	private int playerHealth;
	private Items weapon;
	private int playerScore;
	private ArrayList<Items> inventoryList;
	private Rooms currentRoom;


	public Players() {
		this.playerName = "Default Player Name";
		this.playerHealth = 00;
		this.weapon = null;
		this.playerScore = 00;
		this.inventoryList = new ArrayList<Items>();
	}

	public Players(String playerName, int playerHealth, Items weapon, int playerScore,Rooms currentRoom) {
		this.playerName = playerName;
		this.playerHealth = playerHealth;
		this.weapon = weapon;
		this.playerScore = playerScore;
		this.currentRoom = currentRoom;
	}

	public Players(String playerName, int playerHealth, Items weapon, int playerScore,
			ArrayList<Items> inventoryList,Rooms currentRoom) {
		this.playerName = playerName;
		this.playerHealth = playerHealth;
		this.weapon = weapon;
		this.playerScore = playerScore;
		this.inventoryList = inventoryList;
	}


	public Rooms getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Rooms currentRoom) {
		this.currentRoom = currentRoom;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	public void setWeapon(String weaponId) {
		GameManager itemManager = new GameManager();
		this.weapon = itemManager.getItemList().get(weaponId);
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getPlayerHealth() {
		return playerHealth;
	}

	public Items getWeapon() {
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
