package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import GameObject.*;

public class GameModel{
	private Scanner input = new Scanner(System.in);
	private String gameFolder = "";
	private String[] itemCode = {"Item Name:","Item ID:","Item Description:",
			"Item Type:","Item Action Value:","Item Amount:"};
	private String[] monsterCode = {"Monster Name:","Monster ID:","Monster Description:",
			"Monster Health:","Monster Damage:","Monster Hit Percentage:"};
	private String[] roomCode = {"Room Floor:","Room ID:","Room Description:",
			"Room Connection:","Room Access:","Room Item:","Room Monster:"};
	private String[] puzzleCode = {"Puzzle ID:","Puzzle Description:","Puzzle Type:",
			"Puzzle Solution:","Puzzle Hint:","Puzzle Damage:"};
	private HashMap<String, Items> itemList = new HashMap<String, Items>();
	private HashMap<String, Monsters> monsterList = new HashMap<String, Monsters>();
	private HashMap<String, Rooms> roomList = new HashMap<String, Rooms>();
	private HashMap<String, Puzzles> puzzleList = new HashMap<String, Puzzles>();
	private HashMap<Integer, SaveData> saveList = new HashMap<Integer, SaveData>();
	private Players player;


	//Get User Input
	public String getUserInput() {
		System.out.print("\nInput:\n> ");
		return input.nextLine();
	}


	//Search and return an ArrayList with all the game in the game folder
	public ArrayList<String> getGameFolderList(){
		try {
			ArrayList<String> gameList = new ArrayList<String>();
			File folderPath = new File("./res/Game Folder/");
			File[] files = folderPath.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					gameList.add(file.getName());
				}
			}
			return gameList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/*Set the game folder directory, so game knows where to get all directory from
	 *For the parameter, you would want to use getGameFolderList() method
	 */
	public void setGameFolder(ArrayList<String> gameList) {
		System.out.println("Choose the game folder that you want to use:");	

		//Print out all the game in the game folder
		for(String gamefolder : gameList) {
			System.out.println(" - " + gamefolder);
		}

		//Prompt for user input
		String userFolderChoice = getUserInput().trim();
		boolean vaildChoice = false;

		/*Check to see if user input is valid and set the game folder
		 *if it finds the game folder then it sets the choice to valid true
		 *else it stay false
		 */
		for(String folder : gameList) {
			search:
				if(userFolderChoice.equalsIgnoreCase(folder)) {
					gameFolder = "./res/Game Folder/" + folder + "/";
					vaildChoice = true;
					break search;
				}
		}

		//If the userinput matches with game folder, then output what folder was selected
		if(vaildChoice == true) {
			System.out.println("\nFolder \"" + userFolderChoice + "\" selected.\n" );
		}
		//Call itself if no valid game was found for the user input
		else {
			System.out.println("\nInvalid game folder, please try again.\n");
			setGameFolder(gameList);
		}
	}


	/*Make object list of all subfolder
	 *Parameter: require that you already set up the game folder, and a text
	 *of what subFolder you want to make an object of
	 *ex: "/Rooms","/Puzzle","/Monsters",/Items"
	 *You should call it 4 Times to make puzzle, item, monster and room object
	 */
	public void makeListObject(String gameFolder, String gameSubFolder) {
		String folderPath = gameFolder + gameSubFolder;
		try {	
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
			for (File file : listOfFiles) {
				if (file.isFile()) {
					fileName = file.getName();
					folderPath = gameFolder + gameSubFolder + fileName;
					makeGameObject(folderPath, gameSubFolder);
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error in "+ gameSubFolder + ":/n" + e.toString());
		}	
	}



	/*Make Object then add it to the corresponding hashmap, with the key being
	 *Parameter: Needs the path to the game folder object, and needs the name of 
	 *the object you wanna create
	 */
	public void makeGameObject(String filePath, String object) {
		try {
			//File Reader
			BufferedReader bufferedReader = 
					new BufferedReader(new FileReader(filePath));

			String fileLine = null;
			String setCode = null;

			//Make a default object
			Items itemObject = new Items();
			Monsters monsterObject = new Monsters();
			Rooms roomObject = new Rooms();
			Puzzles puzzleObject = new Puzzles();

			while((fileLine = bufferedReader.readLine()) != null) {
				/*For loop to check if the text file line matches one of the setcode
				 *if it matches, the set code then it set it as such, otherwise setcode
				 *stay the same
				 * */
				search:
					for(String itemSetCode: itemCode) {
						if(itemSetCode.equalsIgnoreCase(fileLine)) {
							setCode = itemSetCode;
							fileLine = bufferedReader.readLine();
							break search;
						}
					}
			search2:
				for(String monsterSetCode: monsterCode) {
					if(monsterSetCode.equalsIgnoreCase(fileLine)) {
						setCode = monsterSetCode;
						fileLine = bufferedReader.readLine();
						break search2;
					}
				}
					search3:
						for(String roomSetCode: roomCode) {
							if(roomSetCode.equalsIgnoreCase(fileLine)) {
								setCode = roomSetCode;
								fileLine = bufferedReader.readLine();
								break search3;
							}
						}
				search4:
					for(String puzzleSetCode: puzzleCode) {
						if(puzzleSetCode.equalsIgnoreCase(fileLine)) {
							setCode = puzzleSetCode;
							fileLine = bufferedReader.readLine();
							break search4;
						}
					}

						if(setCode != null) {
							//Depending on the set code, it'll set the information it got from flieLine
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

			if(object.equals("Item/"))
				itemList.put(itemObject.getItemId(), itemObject);
			else if(object.equals("Monster/"))
				monsterList.put(monsterObject.getMonsterId(), monsterObject);
			else if(object.equals("Room/"))
				roomList.put(roomObject.getRoomId(), roomObject);
			else if(object.equals("Puzzle/"))
				puzzleList.put(puzzleObject.getPuzzleId(), puzzleObject);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSaveList() {
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

	public void displaySaveList() {
		System.out.println("\nSave Data:");
		for(int x = 1; x <= 10;x++) {
			if(saveList.get(x) != null) {
				System.out.println(x + ". Name: " + saveList.get(x).getPlayerData().getPlayerName()
						+ "; Time: " + saveList.get(x).getPlayerData().getGameTime() + "; Room: " + saveList.get(x).getPlayerData().getCurrentRoom().getRoomId());
			}
			else {
				System.out.println(x + ". Empty");
			}
		}
	}

	public void load() {
		boolean loop = true;
		while(loop) {
			saveList.clear();
			setSaveList();
			displaySaveList();
			System.out.println("\nSelect a slot to load, or type \"Exit\" to cancel.");
			String userInput = getUserInput().trim();
			if(userInput.equalsIgnoreCase("e") || userInput.equalsIgnoreCase("exit")) {
				loop = false;
			}else {

				int userInputInt = Integer.parseInt(userInput);
				if(userInputInt >= 1 && userInputInt <= 10) {
					if(saveList.get(userInputInt) != null) {
						player = saveList.get(userInputInt).getPlayerData();
						monsterList = saveList.get(userInputInt).getMonsterList();
						roomList = saveList.get(userInputInt).getRoomList();
						itemList = saveList.get(userInputInt).getItemList();
						System.out.println("\nSuccessfully loaded Slot " + userInputInt);
						actionMenu();
					}else {
						System.out.println("\nNo save data exists in Slot " + userInputInt);
					}
				}
				else {
					System.out.println("\nInvalid command; load cancelled.");
				}
			}
		}
	}

	public void newGame() {
		setGameFolder(getGameFolderList());
		makeListObject(gameFolder,"Item/");
		makeListObject(gameFolder,"Monster/");
		makeListObject(gameFolder,"Puzzle/");
		makeListObject(gameFolder,"Room/");	
		setSaveList();
		System.out.println("Please choose your name:");
		String playerName = getUserInput().trim();
		player = new Players(playerName,100,itemList.get("I01"),0,roomList.get("R01"));
		player.setPlayerName(playerName);
		player.setPlayerMaxHealth(100);
		player.setPlayerCurrentHealth(100);
		player.setWeapon(itemList.get("I01"));
		player.addItemToInventory(itemList.get("I18"));
		player.addItemToInventory(itemList.get("I13"));
		player.setCurrentRoom(roomList.get("R01"));
		player.startGameTime();

		System.out.println("\nNew Game Created");
		System.out.println("====================================================\n");
		System.out.println("EMPYREAN, a multinational conglomerate, has recently"
				+ "\nhad a major incident in one of their remote research"
				+ "\nfacilities. A new iteration of their Incumbent-Class"
				+ "\nA.I. systems used for information warfare has gone"
				+ "\nagainst its protocols, and has done major damage"
				+ "\nwithin the sector it resides in."
				+ "\n\nSecurity protocols and countermeasures have been"
				+ "\nenacted in order to quarantine the subject, and to"
				+ "\nprevent its influence on sector systems. However,"
				+ "\nmany machines/networks are still unresponsive, and"
				+ "\nare considered to be highly dangerous given their"
				+ "\nrogue nature."
				+ "\n\nThe company doesn't want to risk any unnecessary"
				+ "\nresources or agents to get this issue resolved so"
				+ "\nthey're sending you, one of their best arbitrators,"
				+ "\nto fix this mess. You'll be dropped off at the"
				+ "\nlocation, and rendezvous with the superintendent"
				+ "\nconstruct inside the facility. It's been keeping"
				+ "\nthe A.I. contained, and it'll provide you with any"
				+ "\nadditional information you may want to know.\n");
		System.out.println("====================================================");
		System.out.println(player.getCurrentRoom().toString());
		actionMenu();
	}


	//------------------------------------------------------------------------------------------------------
	public void actionMenu() {
		System.out.println("--------------------------------------------------");
		if(player.getCurrentRoom().getRoomMonster().isEmpty()) {
			System.out.println("What will you do?\n");
			System.out.println("1. Move");
			System.out.println("2. Examine Room");
			System.out.println("3. Check Inventory");
			System.out.println("4. Save Game");
			System.out.println("5. Quit Game");

			String userInput = getUserInput().trim();

			switch(userInput.toLowerCase()) {
			case "move": case  "1":
				moveRoomCommand();
				actionMenu();
				break;
			case "examine room": case "examine": case "look": case "search": case "2":
				examineRoom();
				actionMenu();
				break;
			case "check inventory": case "check": case "inventory": case "3":
				checkInventoryCommand();
				actionMenu();
				break;
			case "save game": case "save": case "4":		
				saveGameCommand();
				actionMenu();
				break;
			case "quit game": case "quit": case "exit": case "5":		
				System.out.println("\nAre you sure you want to quit?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				switch(getUserInput().toLowerCase()) {
				case "yes": case "y": case "1":	
					//Add code to get back to main menu
					break;
				case "no": case "n": case "2":
					actionMenu();
					break;
				}
				break;
			default:
				System.out.println("\nInvalid command: " + userInput);
				actionMenu();
				break;
			}


		}
		else if(!player.getCurrentRoom().getRoomMonster().isEmpty()){
			System.out.println("-----               ENCOUNTER!               -----");
			combatMenu();
		}
		else {
			System.out.println("Error with action");
		}
	}

	/*Fuction: Move the player from one room to another
	 *If invalid room direction is typed, then it call itself
	 *else it move the player to that room, then call back to action()
	 * */
	public void moveRoomCommand(){
		System.out.print("\nWhich direction do you want to move? Type \"Exit\" to cancel\n");
		System.out.println("Exits: " + player.getCurrentRoom().getRoomConnection());
		String roomDirection = getUserInput().trim();
		if(roomDirection.equalsIgnoreCase("Exit")) {
			actionMenu();
		}
		else if(player.getCurrentRoom().getRoomNavigationList().get(roomDirection) == null) {
			System.out.println("\nYou can't go that way!");
			moveRoomCommand();
		}
		else {
			Rooms nextRoom = roomList.get(player.getCurrentRoom().getRoomNavigationList().get(roomDirection));
			System.out.println(nextRoom.getRoomAccessList().get(player.getCurrentRoom().getRoomId()));
			System.out.println(player.getCurrentRoom().getRoomId());
			if(nextRoom.getRoomAccessList().get(player.getCurrentRoom().getRoomId()) != null) {
				Puzzles puzzle = puzzleList.get(nextRoom.getRoomAccessList().get(player.getCurrentRoom().getRoomId()));
				if(puzzleMenu(puzzle)) {
					if(player.getCurrentRoom().getRoomMonster().isEmpty()) {
						player.setCurrentRoom(nextRoom);
						System.out.println(player.getCurrentRoom().toString());			
					}
				}
			}
			else if(player.getCurrentRoom().getRoomMonster().isEmpty()) {
				player.setCurrentRoom(nextRoom);
				System.out.println(player.getCurrentRoom().toString());
				
			}
			
			actionMenu();
		}
	}


	/*Function: Print out the room description and search the room for any item
	 *
	 *Method Called: foundItemOption (Prompt yes or no to picking up the item)
	 * */
	public void examineRoom() {
		System.out.println(player.getCurrentRoom().toString());
		if(!player.getCurrentRoom().getRoomItem().isEmpty()) {
			foundItemOption();
		}
		else{
			System.out.println("\nYou search the room, but find nothing of interest.");
		}
		actionMenu();
	}	
	public void foundItemOption() {
		search:
			for(Iterator<Items> iterator = player.getCurrentRoom().getRoomItem().iterator(); iterator.hasNext();) {
				String itemId = iterator.next().getItemId();
				Items itemObject = itemList.get(itemId);
				itemObject = itemList.get(itemId);
				System.out.println("\nYou found a " + itemObject.getItemName() + ".");
				System.out.println("Do you want to pick it up?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				String choice = getUserInput().trim();
				switch(choice.toLowerCase()) {
				case "yes": case "y": case "1":
					iterator.remove();
					player.addItemToInventory(itemObject);
					System.out.println("\nYou picked up the " + itemObject.getItemName() + ".");	
					break;
				case "no": case "n": case "2":
					System.out.println("\nYou decided to leave the " + itemObject.getItemName() + ".");
					break;
				default:
					System.out.println("\nInvalid Command");
					foundItemOption();
					break search;
				}
			}
	}

	/*Function: Check if your inventory is empty, if not then it direct you
	 *to the inventory menu
	 * */
	public void checkInventoryCommand() {
		if(player.getInventoryList().isEmpty())
			System.out.println("\nYou have nothing in your inventory.");
		else
			inventoryMenu();
		actionMenu();

	}


	public void saveGameCommand() {
		try {
			saveList.clear();
			setSaveList();
			displaySaveList();

			System.out.println("\nSelect a slot to save, or type \"Exit\" to cancel");
			String userInput = getUserInput().trim();
			if(userInput.equalsIgnoreCase("exit")) {

			}
			else if(Integer.parseInt(userInput) >= 1 && Integer.parseInt(userInput) <= 10) {
				int userInputInt = Integer.parseInt(userInput);
				SaveData data = new SaveData(userInputInt,player,itemList,monsterList,roomList);
				ObjectOutputStream out = 
						new ObjectOutputStream(new FileOutputStream(gameFolder + "Save/Save "+ userInputInt +".dat"));
				if(saveList.get(userInputInt) == null) {
				}else {
					System.out.println("\nA save file in Slot " + userInputInt + " already exists; Overwrite save data?");
					System.out.println("1. Yes");
					System.out.println("2. No");
					switch(getUserInput().toLowerCase()) {
					case "yes": case "y": case "1":
						player.endGameTime();
						saveList.remove(userInputInt);
						saveList.put(userInputInt,data);
						out.writeObject(data);
						player.startGameTime();
						System.out.println("\nSuccessfully saved in Slot " + userInputInt);
						break;
					case "no": case "n": case "2":
						System.out.println("\nOverwrite cancelled");
						saveGameCommand();
						break;
					default:
						System.out.println("\nInvalid command, overwrite cancelled");
						saveGameCommand();
						break;
					}
				}

			}
			else {
				System.out.println("\nInvalid command");
				saveGameCommand();
			}

		}
		catch(NumberFormatException e) {
			System.out.println("\nInvalid command");
			saveGameCommand();
		}catch(Exception e) {
			System.out.print("Error Saving");
			saveGameCommand();
		}

	}


	//------------------------------------------------------------------------------------------------------
	public void inventoryMenu() {
		System.out.println("--------------------------------------------------");
		System.out.println("Inventory:\n");
		showInventoryItem();

		System.out.println("\nWhat will you do?\n");
		System.out.println("1. Examine Item");
		System.out.println("2. Drop Item");
		System.out.println("3. Use Item");
		System.out.println("4. Equip Item");
		System.out.println("5. Exit");

		String userInput = getUserInput().trim();

		switch(userInput.toLowerCase()) {
		case "examine item": case "examine": case "1":
			examineItemCommand();
			inventoryMenu();
			break;
		case "drop item": case "drop": case "2":
			dropItemCommand();
			inventoryMenu();
			break;
		case "use item": case "use": case "3":
			useItemCommand();
			inventoryMenu();
			break;
		case "equip item": case "equip": case "4":
			equipItemCommand();
			inventoryMenu();
			break;
		case "exit": case "5":
			break;
		default:
			System.out.println("\nInvalid command.");
			inventoryMenu();
			break;
		}

	}

	public void showInventoryItem() {
		System.out.println(player.getWeapon().getItemName() + " (Equipped)");
		for(Items item: player.getInventoryList()) {
			System.out.println(item.getItemName());
		}
	}

	/*Function: Prompt you 
	 * */
	public void examineItemCommand() {
		System.out.println("\nWhich item do you want to examine? Type \"Exit\" to cancel.");
		showInventoryItem();
		String itemName = getUserInput().trim();
		boolean foundItem = false;
		if(itemName.equalsIgnoreCase("exit")) {

		}
		else{
			search:
				for(Items item:player.getInventoryList()) {
					if(itemName.equalsIgnoreCase(item.getItemName())) {
						foundItem = true;
						System.out.println(item.toString());
						if(item.getItemType().equals("Weapon")) {
							System.out.println("\nAmmo x "+ player.getWeaponAmmo(item));
						}
						break search;
					}
				}
		if(itemName.equalsIgnoreCase(player.getWeapon().getItemName())&&foundItem == false) {
			foundItem = true;
			System.out.println(player.getWeapon().toString());
			System.out.println("\nAmmo x "+ player.getWeaponAmmo(player.getWeapon()));
		}
		if(foundItem == false) {
			System.out.println("\nYou don't have that item.");
			examineItemCommand();
		}
		}
	}


	//
	public void dropItemCommand() {
		System.out.println("\nWhich item do you want to drop? Type \"Exit\" to cancel.");
		showInventoryItem();

		String itemName = getUserInput().trim();
		if(itemName.equalsIgnoreCase("Exit")) {

		}
		else {
			boolean foundItem = false;
			search:
				for(Iterator<Items> iterator = player.getInventoryList().iterator(); iterator.hasNext();) {
					Items itemIterator = iterator.next();
					if(itemName.equalsIgnoreCase(itemIterator.getItemName())) {
						foundItem = true;
						player.getCurrentRoom().addRoomItem(itemIterator);
						iterator.remove();
						System.out.println("\nYou drop the " + itemIterator.getItemName() + ".");
						if(player.getInventoryList().isEmpty()) {
							System.out.println("Your inventory is empty.");
						}
						break search;
					}
				}
			if(itemName.equals(player.getWeapon().getItemName())&&foundItem == false)
			{
				foundItem = true;
				System.out.println("\nFighting unarmed would be an unwise descision.");
			}
			if(foundItem == false) {
				System.out.println("\nYou don't have that item.");
				dropItemCommand();
			}
		}
	}

	public void useItemCommand(){
		System.out.println("\nWhich item do you want to use? Type \"Exit\" to cancel.");
		showInventoryItem();

		String itemName = getUserInput().trim();
		if(itemName.equalsIgnoreCase("Exit")) {

		}else {
			boolean foundItem = false;
			search:
				for(Iterator<Items> iterator = player.getInventoryList().iterator(); iterator.hasNext();) {
					Items itemIterator = iterator.next();
					if(itemName.equalsIgnoreCase(itemIterator.getItemName())) {
						foundItem = true;
						if(itemIterator.getItemType().equalsIgnoreCase("healing")) {
							if(!(player.getPlayerCurrentHealth() == player.getPlayerMaxHealth())) {
								player.healHealth(itemIterator.getItemActionValue());
								System.out.println("You recover " + itemIterator.getItemActionValue() + " health.");
								iterator.remove();
							}else {
								System.out.println("You are already at full health.");
							}
						}else {
							System.out.println("Nothing interesting happens.");
						}		
						break search;
					}
				}
			if(foundItem == false) {
				System.out.println("\nYou don't have that item.");
				dropItemCommand();
			}
		}
	}


	public void equipItemCommand() {
		ArrayList<Items> weaponList = new ArrayList<Items>();
		for(Items item: player.getInventoryList()) {
			if(item.getItemType().equals("Weapon")) {
				weaponList.add(item);	
			}
		}
		if(!weaponList.isEmpty()) {
			System.out.println("Which weapon do you want to equip? Type \"Exit\" to cancel");
			for(Items item: player.getInventoryList()) {
				if(item.getItemType().equals("Weapon")) {
					System.out.println(item.getItemName());		
				}
			}
			String itemName = getUserInput().trim();
			if(itemName.equalsIgnoreCase("Exit")) {

			}else {
				boolean foundItem = false;
				search:
					for(Iterator<Items> iterator = weaponList.iterator(); iterator.hasNext();) {
						Items itemIterator = iterator.next();
						String userWeapon = itemIterator.getItemName();
						if(itemName.equalsIgnoreCase(userWeapon)) {
							foundItem = true;
							player.equipWeapon(itemList.get(itemIterator.getItemId()));
							System.out.println("\nYou equipped the " + userWeapon);
							break search;
						}
					}
				if(itemName.equals(player.getWeapon().getItemName())&&foundItem == false)
				{
					foundItem = true;
					System.out.println("\nYou're already holding that weapon!");
				}
				if(foundItem == false) {
					System.out.println("\nYou don't have that item.");
					equipItemCommand();
				}
			}
		}
		else {
			System.out.println("You don't have any other weapons.");
		}
	}

	//------------------------------------------------------------------------------------------------------
	public void combatMenu() {
		showMonsterInBattle();

		System.out.println("--------------------------------------------------");
		System.out.println("COMBAT - What will you do?\n");
		System.out.println("1. Attack");
		System.out.println("2. Defend");
		System.out.println("3. View Inventory");
		System.out.println("4. Run Away");

		String userInput = getUserInput().trim();

		switch(userInput.toLowerCase()) {
		case "attack" : case "1":
			attackMonsterCommand();
			if(player.getCurrentRoom().getRoomMonster().isEmpty()) {
				actionMenu();
			}else {
				combatMenu();
			}
			break;
		case "defend" : case "2":
			System.out.println("You block all attacks, and take no damage!");
			combatMenu();
			break;
		case "check Inventory": case "check": case "inventory": case "3":
			inventoryMenu();
			combatMenu();
			break;
		case "run away": case "run": case "4":
			actionMenu();
			break;
		default:
			System.out.println("Invalid command");
			combatMenu();
		}

	}
	public void showPlayerHealth() {
		System.out.println(player.getPlayerCurrentHealth() + "/" + player.getPlayerMaxHealth());
	}
	public void checkPlayerDeath() {
		if(player.getPlayerCurrentHealth() == 0) {
			System.out.println("Game Over");
			System.exit(0); // insert code to exit to main menu here instead
		}
	}

	public void showMonsterInBattle() {
		for(int x = 0; x < player.getCurrentRoom().getRoomMonster().size(); x++) {
			System.out.println(player.getCurrentRoom().getRoomMonster().get(x).toString());
		}
	}

	public void attackMonsterCommand() {
		if(player.getCurrentRoom().getRoomMonster().size() == 1) {
			Monsters monster = player.getCurrentRoom().getRoomMonster().get(0);

			if(player.getWeaponAmmo(player.getWeapon()) == 0) {
				System.out.println("You don't have any ammo for your weapon");
			}else {
				System.out.println("\nYou attack the " + monster.getMonsterName() + "!");
				System.out.println("The " + monster.getMonsterName() + " took " + player.getWeapon().getItemActionValue() + " damage!\n");
				monster.takeDmg(player.getWeapon().getItemActionValue());

				if(monster.getMonsterCurrentHealth() <= 0) {
					System.out.println("The " + monster.getMonsterName() + " slumps over, defeated.");
					player.getCurrentRoom().removeRoomMonster(monsterList.get(monster.getMonsterId()));
				}
			}
			if(!player.getCurrentRoom().getRoomMonster().isEmpty()) {
				monsterTurn(monster);	
			}
		}
		else
		{
			System.out.println("\nWhich monster do you want to attack? Type \"Exit\" to cancel");

			String monsterInput = getUserInput().trim();

			if(monsterInput.equalsIgnoreCase("Exit")) {

			}else {
				boolean foundMonster = false;
				for(Iterator<Monsters> iterator = player.getCurrentRoom().getRoomMonster().iterator(); iterator.hasNext();) {
					Monsters monsterIterator = iterator.next();
					if(monsterIterator.getMonsterName().equalsIgnoreCase(monsterInput)) {
						foundMonster = true;
						if(player.getWeaponAmmo(player.getWeapon()) == 0) {
							System.out.println("You don't have any ammo for your weapon");
						}else {
							System.out.println("You attack the " + monsterIterator.getMonsterName() + "!");
							player.useWeaponAmmo(player.getWeapon());
							System.out.println("The " + monsterIterator.getMonsterName() + " took " + player.getWeapon().getItemActionValue() + " damage!");
							monsterIterator.takeDmg(player.getWeapon().getItemActionValue());

							if(monsterIterator.getMonsterCurrentHealth() == 0) {
								System.out.println("The " + monsterIterator.getMonsterName() + " slumps over, defeated.");
								iterator.remove();
							}
						}
						if(!player.getCurrentRoom().getRoomMonster().isEmpty()) {
							monsterTurn(monsterIterator);	
						}		
					}
				}
				if(foundMonster == false) {
					System.out.println("\nYou can't attack that!");
					attackMonsterCommand();
				}
			}
		}
	}
	public void monsterTurn(Monsters monster) {
		System.out.println("The " + monster.getMonsterName() + " attacks!");
		if(monster.attackPlayer() == 0) {
			System.out.println("The attack missed!");
		}else {
			System.out.println("You took " + monster.attackPlayer() + " damage!");
			player.takeDmg(monster.attackPlayer());
			showPlayerHealth();
			checkPlayerDeath();
		}	
	}
	public void runAwayCommand() {
		for(int x = 0; x < player.getCurrentRoom().getRoomMonster().size(); x++) {
			player.getCurrentRoom().getRoomMonster().get(x).setMonsterCurrentHealth(
					player.getCurrentRoom().getRoomMonster().get(x).getMonsterMaxHealth());
		}
		player.setCurrentRoom(player.getPreviousRoom());
	}

	public boolean puzzleMenu(Puzzles puzzle) {
		System.out.println(puzzle.getPuzzleDesc());
		if(puzzle.getPuzzleType().equalsIgnoreCase("item")) {
			System.out.println("\nWhat will you do?");
			System.out.println("1. Use Item");
			System.out.println("2. Hint");
			System.out.println("3. Leave");
			String userInput = getUserInput().trim();
			switch(userInput.toLowerCase()) {
			case "use item" : case "1":
				return useItemCommand(puzzle);
			case "hint" : case "2":
				System.out.println(puzzle.getPuzzleHint());
				return puzzleMenu(puzzle);
			case "leave" : case "3":
				return false;
			default:
				System.out.println("Invalid Input");
				return puzzleMenu(puzzle);

			}
		}
		else if(puzzle.getPuzzleType().equalsIgnoreCase("input")) {
			System.out.println("\nWhat will you do?");
			System.out.println("1. Input Number");
			System.out.println("2. Hint");
			System.out.println("3. Leave");
			String userInput = getUserInput().trim();
			switch(userInput.toLowerCase()) {
			case "input number" : case "1":
				return inputNumber(puzzle);
			case "hint" : case "2":
				System.out.println(puzzle.getPuzzleHint());
				return puzzleMenu(puzzle);
			case "leave" : case "3":
				return false;
			default:
				System.out.println("Invalid Input");
				return puzzleMenu(puzzle);
			}
		}
		else {
			System.out.println("Error with puzzle type");
			return false;
		}
	}
	public boolean useItemCommand(Puzzles puzzle) {
		System.out.println("\nWhich item do you want to use? Type \"Exit\" to cancel.");
		showInventoryItem();

		String itemName = getUserInput().trim();
		if(itemName.equalsIgnoreCase("Exit")) {
			return puzzleMenu(puzzle);
		}else {
			for(Iterator<Items> iterator = player.getInventoryList().iterator(); iterator.hasNext();) {
				Items itemIterator = iterator.next();
				if(itemName.equalsIgnoreCase(itemIterator.getItemName())) {
					if(itemIterator.getItemId().equalsIgnoreCase(puzzle.getPuzzleSolution())) {
						return true;
					}
				}
			}
			System.out.println("\nYou don't have that item.");
			return useItemCommand(puzzle);
		}
	}
	public boolean inputNumber(Puzzles puzzle) {
		System.out.println("Type in a number. Type \"Exit\" to cancel.");
		String puzzleSolution = getUserInput();
		if(puzzleSolution.equalsIgnoreCase(puzzle.getPuzzleSolution())) {
			System.out.println("You inputed the right number");
			return true;
		}
		else if(puzzleSolution.equalsIgnoreCase("exit")) {
			return puzzleMenu(puzzle);
		}
		else{
			System.out.println("Invalid Answer");
			player.takeDmg(puzzle.getPuzzleDamage());
			System.out.println("You got zap for " + puzzle.getPuzzleDamage() + " damage");
			showPlayerHealth();
			checkPlayerDeath();
			return inputNumber(puzzle);
		}

	}

}
