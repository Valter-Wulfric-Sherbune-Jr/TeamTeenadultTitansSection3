package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import GameObject.Items;
import GameObject.Monsters;
import GameObject.Rooms;
import GameObject.SaveData;
import GameObject.Puzzles;
import GameObject.Players;

public class GameModel{
	private String gameFolder = "";
	private String[] validState = {"Main Menu","New game","Player Creation",
			"Load Menu","Action Menu", "Move Player","Save Menu","Save Conflict","Select Item",
			"Examine Item","Drop Item","Use Item", "Equip Item", "Inventory Menu"};
	private String[] itemCode = {"Item Name:","Item ID:","Item Description:",
			"Item Type:","Item Action Value:","Item Amount:"};
	private String[] monsterCode = {"Monster Name:","Monster ID:","Monster Description:",
			"Monster Health:","Monster Damage:","Monster Hit Percentage:"};
	private String[] roomCode = {"Room Floor:","Room ID:","Room Description:",
			"Room Connection:","Room Access:","Room Item:","Room Monster:"};
	private String[] puzzleCode = {"Puzzle ID:","Puzzle Description:","Puzzle Type:",
			"Puzzle Solution:","Puzzle Hint:","Puzzle Damage:"};
	private ArrayList<String> gameFolderList = new ArrayList<String>();
	private HashMap<String, Items> itemList = new HashMap<String, Items>();
	private HashMap<String, Monsters> monsterList = new HashMap<String, Monsters>();
	private HashMap<String, Rooms> roomList = new HashMap<String, Rooms>();
	private HashMap<String, Puzzles> puzzleList = new HashMap<String, Puzzles>();
	private HashMap<Integer, SaveData> saveList = new HashMap<Integer, SaveData>();
	private SaveData save;
	private Players player;
	private String storedState = "";
	private String state = "";

	
	
	
	/*Set the state of the game, if error then exit game
	 *Used in method: readUserInput()
	 */
	public void setState(String state) {
		if(checkIfValidState(state)) {
			this.state = state;
		}
		else {
			System.out.println("Error: Not Valid State (Method setState())");
			System.out.println(state);
			System.exit(0);
		}
	}
	
	/*Save a state you want to switch to late
	 */
	public void setStoredState(String storedState) {
		if(checkIfValidState(storedState)) {
			this.storedState = storedState;
		}
		else {
			System.out.println("Error: Not Valid State (Method setStoredState())");
			System.out.println(state);
			System.exit(0);
		}
	}
	

	/*Get the state of the game
	 */
	public String getState() {
		return state;
	}
	
	/*Get the previous state of the game
	 */
	public String getStoredState(){
		return storedState;
	}

	/*Return true of false on whether the state is valid or not
	 *Used in method: setState()
	 */
	public boolean checkIfValidState(String state) {
		for(String stateArray: validState) {
			if(stateArray.equalsIgnoreCase(state)) {
				return true;
			}
		}
		return false;
	}

	/*Clear the game folder list, then search the folder path
	 *and add all the game folder to an arrayList
	 *Used in method: printChoice()
	 */

	public void setGameFolderList(){
		try {
			gameFolderList.clear();
			File folderPath = new File("./res/Game Folder/");
			File[] files = folderPath.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					gameFolderList.add(file.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*Return an arrayList of all the game folder
	 *Used in method: printChoice()
	 */
	public ArrayList<String> getGameFolderList(){
		return gameFolderList;
	}

	/*Check if the user input match a game folder
	 *Used in method: selectGameFolder()
	 */
	public boolean checkValidGameFolder(String userGameFolder) {
		for(String gameFolder: gameFolderList) {
			if(gameFolder.equalsIgnoreCase(userGameFolder)) {
				return true;
			}
		}
		return false;
	}

	/*Set the game folder using userinput
	 *Used in method: selectGameFolder()
	 */
	public void setGameFolder(String gameFolder) {
		this.gameFolder = "./res/Game Folder/" + gameFolder + "/";
	}

	/*Return the gamefolder
	 *Used in method:
	 */
	public String getGameFolder() {
		return gameFolder;
	}

	/*Search through a subfolder of a gamefolder
	 *and make an object for all file in the subfolder
	 *Used in method: selectGameFolder()
	 */
	public void loadGameFolder(String subFolder) {
		try {
			/*Add all the file, in that folder into an arrayList
			 */
			ArrayList<String> filePath = new ArrayList<String>();
			File folder = new File(gameFolder + subFolder + "/");
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
			for (File file : listOfFiles) {
				if (file.isFile()) {
					fileName = file.getName();
					filePath.add(gameFolder + subFolder + "/" + fileName);
				}
			}		

			/*For loop to loop through the file path arrayList
			 *and make an object for every file
			 */
			for(int x = 0; x < filePath.size(); x++) {
				@SuppressWarnings("resource")
				BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.get(x)));

				String fileLine = null;
				String setCode = null;

				/*Create the Default object
				 */

				Items itemObject = new Items();
				Monsters monsterObject = new Monsters();
				Puzzles puzzleObject = new Puzzles();	
				Rooms roomObject = new Rooms();

				/*While loop to check if the text file line matches one of the set code
				 *if it matches, the set code then it set it as such, otherwise set code
				 *stay the same
				 */
				while((fileLine = bufferedReader.readLine()) != null) {
					switch(subFolder.toLowerCase()) {
					case"item":
						for(String itemSetCode: itemCode) {
							if(itemSetCode.equalsIgnoreCase(fileLine)) {
								setCode = itemSetCode;
								fileLine = bufferedReader.readLine();
								break;
							}
						}
						break;
					case"monster":
						for(String monsterSetCode: monsterCode) {
							if(monsterSetCode.equalsIgnoreCase(fileLine)) {
								setCode = monsterSetCode;
								fileLine = bufferedReader.readLine();
								break;
							}
						}
						break;
					case"puzzle":
						for(String puzzleSetCode: puzzleCode) {
							if(puzzleSetCode.equalsIgnoreCase(fileLine)) {
								setCode = puzzleSetCode;
								fileLine = bufferedReader.readLine();
								break;
							}
						}
						break;
					case"room":
						for(String roomSetCode: roomCode) {
							if(roomSetCode.equalsIgnoreCase(fileLine)) {
								setCode = roomSetCode;
								fileLine = bufferedReader.readLine();
								break;
							}
						}
						break;
					}

					//Depending on the set code, it'll set the information it got from flieLine
					if(setCode != null) {
						switch(setCode) {
						case "Item Name:":
							itemObject.setItemName(fileLine);
							break;
						case "Item ID:":
							itemObject.setItemId(fileLine);
							break;
						case "Item Description:":
							itemObject.setItemDesc(fileLine);
							break;
						case "Item Type:":
							itemObject.setItemType(fileLine);
							break;	
						case "Item Action Value:":
							if(!fileLine.equalsIgnoreCase("null")) {
								itemObject.setItemActionValue(fileLine);
							}
							else {
								itemObject.setItemActionValue("null");
							}
							break;	
						case "Item Amount:":
							itemObject.setItemAmount(Integer.parseInt(fileLine));
							break;	
						case "Monster Name:":
							monsterObject.setMonsterName(fileLine);
							break;
						case "Monster ID:":
							monsterObject.setMonsterId(fileLine);
							break;
						case "Monster Description:":
							monsterObject.setMonsterDesc(fileLine);
							break;
						case "Monster Health:":
							monsterObject.setMonsterMaxHealth(Integer.parseInt(fileLine));
							monsterObject.setMonsterCurrentHealth(Integer.parseInt(fileLine));
							break;	
						case "Monster Damage:":
							monsterObject.setMonsterDamage(Integer.parseInt(fileLine));
							break;	
						case "Monster Hit Percentage:":
							monsterObject.setMonsterHitPercentage(Double.parseDouble(fileLine));
							break;	
						case "Room Floor:":
							roomObject.setRoomFloor(fileLine);
							break;
						case "Room ID:":
							roomObject.setRoomId(fileLine);
							break;
						case "Room Description:":
							roomObject.setRoomDescription(fileLine);
							break;
						case "Room Connection:":
							String[] RoomConnection = fileLine.split(":");
							String Direction = RoomConnection[1];
							String roomID = RoomConnection[0];	
							roomObject.setRoomConnection(Direction, roomID);
							break;
						case "Room Access:":
							if(fileLine.equals("null")) 
								break;
							else {
								String[] RoomAccess = fileLine.split(":");
								String puzzleID = RoomAccess[0];
								String currentRoomID = RoomAccess[1];
								roomObject.setRoomAccess(currentRoomID, puzzleID);
							}
							break;
						case "Room Item:":
							if(!fileLine.equals("null")) {
								roomObject.addRoomItem(itemList.get(fileLine));
								break;
							}
						case "Room Monster:":
							if(!fileLine.equals("null")) {
								roomObject.addRoomMonster(monsterList.get(fileLine));
								break;
							}
						case "Puzzle ID:":
							puzzleObject.setPuzzleId(fileLine);
							break;
						case "Puzzle Description:":
							puzzleObject.setPuzzleDesc(fileLine);
							break;
						case "Puzzle Type:":
							puzzleObject.setPuzzleType(fileLine);
							break;
						case "Puzzle Solution:":
							puzzleObject.setPuzzleSolution(fileLine);
							break;
						case "Puzzle Hint:":
							puzzleObject.setPuzzleHint(fileLine);
							break;
						case "Puzzle Damage:":
							if(fileLine.equals("null"))
								puzzleObject.setPuzzleDamage(0);
							else
								puzzleObject.setPuzzleDamage(Integer.parseInt(fileLine));
							break;
						}	
					}
				}


				/*Depending on what subfolder, it makes that object
				 */
				switch(subFolder.toLowerCase()) {
				case"item":
					itemList.put(itemObject.getItemId(), itemObject);
					break;
				case"monster":
					monsterList.put(monsterObject.getMonsterId(), monsterObject);
					break;
				case"puzzle":
					puzzleList.put(puzzleObject.getPuzzleId(), puzzleObject);
					break;
				case"room":
					roomList.put(roomObject.getRoomId(), roomObject);
					break;
				}				
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found (Method LoadGameFolder())");
			System.out.println(gameFolder + subFolder);
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error: Reading File Error (Method LoadGameFolder())");
			System.out.println(gameFolder + subFolder);
			System.exit(0);
		}

	}

	/*Make a new player object and set him up
	 *Need userInput to set the player name
	 *Used in method: playerCreation()
	 */
	public void makeNewPlayer(String playerName) {
		try {
			player = new Players();
			player.setPlayerName(playerName);
			player.setPlayerMaxHealth(100);
			player.setPlayerCurrentHealth(100);
			player.setWeapon(itemList.get("I01"));
			player.addItemToInventory(itemList.get("I18"));
			player.addItemToInventory(itemList.get("I13"));
			player.setCurrentRoom(roomList.get("R01"));
			player.startGameTime();
		}catch(Exception e) {
			System.out.println("Error: Setting Player");
		}
	}

	/*Return the player object
	 *Use in method: playerCreation(), printChoice()
	 */
	public Players getPlayer() {
		return player;
	}
	/*Check if the direction the player enter is valid 
	 *for the room
	 *Used in method: movePlayer()
	 */
	public boolean checkValidDirection(String userInput) {
		if(player.getCurrentRoom().getRoomNavigationList().get(userInput) != null) {
			return true;
		}else {
			return false;
		}
	}

	/*Return the player object
	 *Use in method: playerCreation(), printChoice()
	 */
	public Rooms getNextRoom(String userInput) {
		return roomList.get(player.getCurrentRoom().getRoomNavigationList().get(userInput));
	}
	
	/*Search the save folder of the game folder and add all
	 *files to the saveList hashmap, with the save id as the key
	 *Use in Method: 
	 */
	public void setSaveList() {
		saveList.clear();

		String folderPath = gameFolder + "Save/";
		try {	
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
			for (File file : listOfFiles) {
				if (file.isFile()) {
					fileName = file.getName();
					folderPath = gameFolder + "Save/" + fileName;
					try(ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(folderPath)))
					{
						while(true) {
							SaveData saveObj = (SaveData) (objInput.readObject());
							if(saveList.get(saveObj.getSaveId()) == null) {
								saveList.put(saveObj.getSaveId(), saveObj);
							}else {
								System.out.println("Error: Duplicate Save Data ID " + fileName + " with save id " + saveObj.getSaveId());
							}
						} 
					}
					catch(Exception e)
					{
						//Nothing here
					}

				}
			}
		}
		catch(Exception e) {
			File dir = new File(folderPath);
			dir.mkdir();
			System.out.println("Error, Save folder do not exist" + e.toString());
			System.out.println("Creating Save Folder");
		}
	}

	/*Get the saveList hashmap
	 */
	public HashMap<Integer, SaveData> getSaveList() {
		return saveList;
	}

	/*Initalize the saveData, but does not save the object yet
	 */
	public void setSaveData(int userInputInt){
		player.endGameTime();
		this.save = new SaveData(userInputInt,player,itemList,monsterList,roomList);
		player.startGameTime();
	}
	
	/*Get the saveData that was initalize
	 */
	public SaveData getSaveData(){
		return save;
	}
	
	/*Save the gameData and create a binary test file of it in
	 *the save folder
	 */
	public void saveGameData(SaveData data) {
		try {
			ObjectOutputStream out = 
					new ObjectOutputStream(new FileOutputStream(gameFolder + "Save/Save "+ data.getSaveId() +".dat"));
			out.writeObject(data);
		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found (Method saveGameData())");
		} catch (IOException e) {
			System.out.println("Error: Reading File Error (Method saveGameData())");
		}
	}
	
	public boolean checkValidItem(String itemName) {
		for(Items item: player.getInventoryList()) {
			if(item.getItemName().equalsIgnoreCase(itemName)) {
				return true;
			}
		}
		return false;
	}








	public void updateLoadList() {

	}

}