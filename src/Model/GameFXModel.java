package Model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import GameObject.Items;
import GameObject.Monsters;
import GameObject.Party;
import GameObject.Rooms;
import GameObject.SaveData;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import GameObject.Puzzles;
import GameObject.Players;

public class GameFXModel{
	private String gameFolder = "";
	private String[] itemCode = {"Item Name:","Item ID:","Item Description:",
			"Item Type:","Item Action Value:","Item Amount:","Item Drop Rate:"};
	private String[] monsterCode = {"Monster Name:","Monster ID:","Monster Description:",
			"Monster Health:","Monster Damage:","Monster Hit Percentage:", "Monster Type:",
	"Monster Picture:"};
	private String[] roomCode = {"Room Floor:","Room ID:","Room Description:",
			"Room Connection:","Room Access:","Room Item:","Room Monster:", "Room Picture:"};
	private String[] puzzleCode = {"Puzzle ID:","Puzzle Description:","Puzzle Type:",
			"Puzzle Solution:","Puzzle Hint:","Puzzle Damage:"};
	private String[] playerCode = {"Player Name:","Player ID:","Player Health:",
	"Player Weapon:", "Player Icon:", "Player Select:", "Player Death:"};
	private String[] gameSettingCode = {"Create Player:","Party Member:","Party Inventory:",
	"Set Starting Room:"};
	private ArrayList<String> gameFolderList = new ArrayList<String>();
	private HashMap<String, Items> itemList = new HashMap<String, Items>();
	private HashMap<String, Monsters> monsterList = new HashMap<String, Monsters>();
	private HashMap<String, Rooms> roomList = new HashMap<String, Rooms>();
	private HashMap<String, Puzzles> puzzleList = new HashMap<String, Puzzles>();
	private HashMap<Integer, SaveData> saveList = new HashMap<Integer, SaveData>();
	private HashMap<String, Image> pictureList = new HashMap<String, Image>();
	private HashMap<String, Players> playerList = new HashMap<String, Players>();
	private SaveData save;
	private Party playerParty = new Party();;
	private Rooms nextRoom;
	private Monsters currentMonster;
	private ArrayList<Items> lootList = new ArrayList<Items>();
	private int monsterAlive = 0;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerSoundEffect;
	private static Media mediaMenu;
	private static Media mediaTransition;
	private static Media mediaSoundEffect;


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

	/*Return the gamefolder
	 *Used in method:
	 */
	public void loadGamePictureFolder() {
		/*Add all the file, in that folder into an arrayList
		 */
		File folder = new File(gameFolder + "Picture/Room/");
		File[] listOfFiles = folder.listFiles();
		String fileName = null;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				fileName = file.getName();	
				Image image = new Image(file.toURI().toString(), 670, 265,false,false);
				pictureList.put(fileName,image);

			}
		}

		File folder2 = new File(gameFolder + "Picture/Monster/");
		File[] listOfFiles2 = folder2.listFiles();
		String fileName2 = null;
		for (File file2 : listOfFiles2) {
			if (file2.isFile()) {
				fileName2 = file2.getName();	
				Image image = new Image(file2.toURI().toString(), 110, 170,false,false);
				pictureList.put(fileName2,image);

			}
		}
		
		File folder3 = new File(gameFolder + "Picture/Player/");
		File[] listOfFiles3 = folder3.listFiles();
		String fileName3 = null;
		for (File file3 : listOfFiles3) {
			if (file3.isFile()) {
				fileName3 = file3.getName();	
				Image image = new Image(file3.toURI().toString(), 260, 225,false,false);
				pictureList.put(fileName3,image);
				System.out.println(file3.toURI().toString());
			}
		}


	}

	public HashMap<String, Image> getPictureList() {
		return pictureList;
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
				Players playerObject = new Players();

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

					case"player":
						for(String playerSetCode: playerCode) {
							if(playerSetCode.equalsIgnoreCase(fileLine)) {
								setCode = playerSetCode;
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
						case "Item Drop Rate:":
							if(!fileLine.equalsIgnoreCase("null")) {
								itemObject.setItemDropRate(Double.parseDouble(fileLine));
							}else {
								itemObject.setItemDropRate(0);
							}
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
						case "Monster Type:":
							monsterObject.setMonsterType(fileLine);
							break;
						case "Monster Icon:":
							monsterObject.setMonsterIcon(fileLine);
							break;	
						case "Monster BattleImage:":
							monsterObject.setMonsterBattleImage(fileLine);
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
								Items newItem = (Items) clone(itemList.get(fileLine));
								roomObject.addRoomItem(newItem);
								break;
							}
						case "Room Monster:":
							if(!fileLine.equals("null")) {
								Monsters newMonster = (Monsters) clone(monsterList.get(fileLine));
								roomObject.addRoomMonster(newMonster);
								monsterAlive++;
								break;
							}	
						case "Room Picture:":
							if(!fileLine.equals("null")) {
								roomObject.setRoomBackground(fileLine);
								break;
							}else {
								roomObject.setRoomBackground("null");
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

						case "Player Name:":
							playerObject.setPlayerName(fileLine);
							break;
						case "Player ID:":
							playerObject.setPlayerId(fileLine);
							break;
						case "Player Health:":
							double maxhealth = Double.parseDouble(fileLine);
							playerObject.setPlayerMaxHealth(maxhealth);
							playerObject.setPlayerCurrentHealth(maxhealth);
							break;
						case "Player Weapon:":
							playerObject.setWeapon(itemList.get(fileLine));
							break;
						case "Player Icon:":
							playerObject.setPlayerIcon(fileLine);
							break;
						case "Player Select:":
							playerObject.setPlayerSelect(fileLine);
							break;
						case "Player Death:":
							playerObject.setPlayerDeath(fileLine);
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
				case"player":
					playerList.put(playerObject.getPlayerId(), playerObject);
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

	public void setGameSetting(){
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(gameFolder + "/Game Setting.txt"));

			String fileLine = "";
			String setCode = "";
			while((fileLine = bufferedReader.readLine()) != null) {

				for(String gameSettingSetCode: gameSettingCode) {
					if(gameSettingSetCode.equalsIgnoreCase(fileLine)) {
						setCode = gameSettingSetCode;
						fileLine = bufferedReader.readLine();
						break;
					}
				}

				if(setCode != null) {
					switch(setCode) {
					case "Party Member:":
						if(!fileLine.equals("null")) {
							playerParty.addPartyMember(playerList.get(fileLine));		
						}
						break;
					case "Party Inventory:":
						if(!fileLine.equals("null")) {
							playerParty.addItemToInventory(itemList.get(fileLine));
						}						
						break;
					case "Set Starting Room:":
						if(!fileLine.equals("null")) {
							playerParty.setCurrentRoom(roomList.get(fileLine));
						}
						break;	
					}	



				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*Copy the value of the object rather then the reference
	 *Useful because you adding a monster from an hashmap to
	 *a room object, but if you attack it, then it hurts all
	 *monster that share that object, but with clone, every 
	 *object in that room is unique rather than by reference
	 */
	public static Object clone(Object copyObject) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(copyObject);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object deepCopy = ois.readObject();
			return deepCopy;
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Party getParty() {
		return playerParty;
	}

	/*Check if the direction the player enter is valid 
	 *for the room
	 *Used in method: movePlayer()
	 */
	public boolean checkValidDirection(String userInput) {
		if(playerParty.getCurrentRoom().getRoomNavigationList().get(userInput) != null) {
			return true;
		}else {
			return false;
		}
	}

	/*Return the player object
	 *Use in method: playerCreation(), printChoice()
	 */
	public void setNextRoom(String userInput) {
		this.nextRoom = roomList.get(playerParty.getCurrentRoom().getRoomNavigationList().get(userInput));
	}

	/*Return the player object
	 *Use in method: playerCreation(), printChoice()
	 */
	public Rooms getNextRoom() {
		return nextRoom;
	}

	/*Return the puzzle of the next room
	 *Use in method: movePlayer(), puzzleMenu(), usePuzzleItem(), inputNumber(), checkMonsterRoom()
	 */
	public Puzzles getNextRoomPuzzle() {
		return puzzleList.get(nextRoom.getRoomAccessList().get(playerParty.getCurrentRoom().getRoomId()));
	}

	/*Search the save folder of the game folder and add all
	 *files to the saveList hashmap, with the save id as the key
	 *Use in Method: actionMenu()
	 */
	public void setSaveList() {
		saveList.clear();
		try {	
			File folder = new File("./res/Save/");
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
			for (File file : listOfFiles) {
				if (file.isFile()) {
					fileName = file.getName();
					String folderPath = "./res/Save/" + fileName;
					System.out.println(fileName);
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
			System.out.println("Error; Save folder does not exist" + e.toString());
			System.out.println("Creating save folder...");
			File dir = new File("./res/Save");
			dir.mkdir();
		}
	}

	/*Get the saveList hashmap
	 *Use in Method: saveGame(), printChoice()
	 */
	public HashMap<Integer, SaveData> getSaveList() {
		return saveList;
	}

		/*Initalize the saveData, but does not save the object yet
		 *Use in Method: saveGame()
		 */
		public void setSaveData(int userInputInt, String gameFolder2){
			playerParty.endGameTime();
			this.save = new SaveData(userInputInt,playerParty,itemList,monsterList,roomList,monsterAlive,gameFolder2);
			playerParty.startGameTime();
		}

	/*Get the saveData that was initalize
	 *Use in Method: saveGame(), saveConflict, printChoice()
	 */
	public SaveData getSaveData(){
		return save;
	}

		public void loadGameSaveData(SaveData save) {
			playerParty = save.getParty();
			monsterList = save.getMonsterList();
			roomList = save.getRoomList();
			itemList = save.getItemList();
			monsterAlive = save.getMonsterAlive();
			playerParty.startGameTime();
			playerParty.setCurrentRoom(save.getParty().getCurrentRoom());
		}

	/*Save the gameData and create a binary test file of it in
	 *Use in Method: saveGame(), saveConflict
	 *the save folder
	 */
	public void saveGameData(SaveData data) {
		try {
			ObjectOutputStream out = 
					new ObjectOutputStream(new FileOutputStream("./res/Save/Save "+ data.getSaveId() +".dat"));
			out.writeObject(data);
		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found (Method saveGameData())");
		} catch (IOException e) {
			System.out.println("Error: Reading File Error (Method saveGameData())");
			System.out.println(e);
		}
	}

	/*Set the current monster as a point of action
	 *for the player to take again
	 *Use in Method: attackMonster(), runAway()
	 */
	public void setCurrentMonster(Monsters monster) {
		this.currentMonster = monster;
	}

	/*Get the current monster
	 *Use in Method: attackPlayer(), runAway(), checkWeaponAmmo(), attackMonster()
	 */
	public Monsters getCurrentMonster() {
		return currentMonster;
	}

	/*Set the item that you are allowed to loot by
	 *require an input of an arraylist which you can
	 *copy the value
	 *Use in Method: actionMenu()
	 */
	public void setLootList(ArrayList<Items> lootListMonster) {
		for(Items item: lootListMonster) {
			lootList.add(item);
		}
	}

	/*return the lootList
	 *Use in Method: lootItem()
	 */
	public ArrayList<Items> getLootList() {
		return lootList;
	}

	/*Remove the first loot from the loot list
	 *Use in Method: lootItem()
	 */
	public void removeLoot() {
		lootList.remove(0);
	}

	/*Remove the first loot from the loot list
	 *Use in Method: lootItem()
	 */
	public void removeSpecificLoot(Items item) {
		for(int x = 0 ; x < lootList.size(); x++) {
			if(lootList.get(x).equals(item)) {
				lootList.remove(x);
				break;
			}
		}
	}

	public Items getSpecificLoot(int x) {
		return lootList.get(x);
	}

	/*Get the first loot from the loot list
	 *Use in Method: printChoice(), lootItem()
	 */
	public Items getLoot() {
		return lootList.get(0);
	}

	/*Get an arraylist of item that has been
	 *add by drop rate.
	 *Use in Method: printChoice(), lootItem()
	 */
	public ArrayList<Items> getMonsterLootList() {
		ArrayList<Items> validDrop = new ArrayList<Items>();
		ArrayList<String> itemIdList = new ArrayList<String>();
		Set set = itemList.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) { 
			Map.Entry mEntry = (Map.Entry)iterator.next();
			itemIdList.add((String) mEntry.getKey());
		}


		for(int x = 0; x < itemList.size(); x++) {
			//Creates a random value between 1 and 100
			double chance = Math.random() * 100;
			double itemDropPercent = 0 ;
			if(itemList.get(itemIdList.get(x)).getItemDropRate() != 0) {
				//copy of percentage and multiply by a hundred to get a non decimal
				itemDropPercent = itemList.get(itemIdList.get(x)).getItemDropRate()*100;

			}


			Items newItem = (Items) clone(itemList.get(itemIdList.get(x)));

			if ((itemDropPercent - chance) >= 0) {
				validDrop.add(newItem);
			}
		}
		return validDrop;
	}

	/*Get the amount of monster alive
	 *Use in Method: checkWinCondition()
	 */
	public int getMonsterAlive() {
		return monsterAlive;
	}

	/*Decrease the amount of monster alive
	 *Use in Method: attackMonster(), attackMultipleMonster()
	 */
	public void decreaseMonsterAlive() {
		monsterAlive--;
	}

	public void updateRoom(Rooms room) {
		roomList.remove(room.getRoomId());
		roomList.put(room.getRoomId(), room);
	}
	
	public void playSoundEffect(String music) {
		String folderPath = "./res/Music/";
		folderPath += music;

		mediaSoundEffect = new Media(new File(folderPath).toURI().toString());

		mediaPlayerSoundEffect = new MediaPlayer(mediaSoundEffect);
		mediaPlayerSoundEffect.setAutoPlay(true);
		mediaPlayerSoundEffect.play();
		mediaPlayerSoundEffect.setVolume(0.5);
	}
	
	public void playGameSoundEffect(String music) {
		String folderPath = gameFolder + "Music/";
		folderPath += music;

		mediaSoundEffect = new Media(new File(folderPath).toURI().toString());

		mediaPlayerSoundEffect = new MediaPlayer(mediaSoundEffect);
		mediaPlayerSoundEffect.setAutoPlay(true);
		mediaPlayerSoundEffect.play();
		mediaPlayerSoundEffect.setVolume(0.5);
	}

	public void playMusic(String music) throws URISyntaxException
	{
		String folderPath = "./res/Music/";
		folderPath += music;

		mediaMenu = new Media(new File(folderPath).toURI().toString());

		mediaPlayer = new MediaPlayer(mediaMenu);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Runnable()
		{
			public void run()
			{
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.setVolume(0.5);



	}

	public void playCombatMusic(String music, String music2) throws URISyntaxException
	{
		String folderPath1 = "./res/Music/";
		folderPath1 += music;

		String folderPath2 = "./res/Music/";
		folderPath2 += music2;


		mediaTransition = new Media(new File(folderPath1).toURI().toString());
		mediaMenu = new Media(new File(folderPath2).toURI().toString());

		mediaPlayer = new MediaPlayer(mediaTransition);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Runnable()
		{
			public void run()
			{
				mediaPlayer = new MediaPlayer(mediaMenu);
				mediaPlayer.setAutoPlay(true);
				mediaPlayer.play();
				mediaPlayer.setOnEndOfMedia(new Runnable()
				{
					public void run()
					{
						mediaPlayer.seek(Duration.ZERO);
					}

				});

			}


		});
		mediaPlayer.setVolume(0.2);



	}
	public void stopMusic() throws URISyntaxException
	{
		mediaPlayer.stop();
	}



}