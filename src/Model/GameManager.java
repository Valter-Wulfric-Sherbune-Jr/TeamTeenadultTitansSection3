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

public class GameManager{
	private Scanner input = new Scanner(System.in);
	private String gameFolder = "";
	private String[] itemCode = {"Item Name:","Item ID:","Item Description:",
			"Item Type:","Item Action Value:","Item Usuage Times:"};
	private String[] monsterCode = {"Monster Name:","Monster ID:","Monster Description:",
			"Monster Health:","Monster Damage:","Monster Hit Percentage:"};
	private String[] roomCode = {"Room Floor:","Room ID:","Room Description:",
			"Room Connection:","Room Access:","Room Item:","Room Monster:"};
	private HashMap<String, Items> itemList = new HashMap<String, Items>();
	private HashMap<String, Monsters> monsterList = new HashMap<String, Monsters>();
	private HashMap<String, Rooms> roomList = new HashMap<String, Rooms>();
	private HashMap<Integer, SaveData> saveList = new HashMap<Integer, SaveData>();
	private Players player;

	//Get User Input
	public String getUserInput() {
		System.out.print("\nInput:\n> ");
		return input.nextLine();
	}

	//Get an ArrayList of all the game in the game folder
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

	//Set the game folder to point where to get all the asset from
	public void setGameFolder() {
		ArrayList<String> gameList = getGameFolderList();
		System.out.println("Choose the game folder that you want to use:");	

		for(String gamefolder : gameList) {
			System.out.println(" - " + gamefolder);
		}

		String userFolderChoice = getUserInput();
		boolean vaildChoice = false;

		for(String folder : gameList) {
			search:
				if(userFolderChoice.equalsIgnoreCase(folder)) {
					gameFolder = "./res/Game Folder/" + folder + "/";
					vaildChoice = true;
					break search;
				}
		}

		if(vaildChoice == true) {
			System.out.println("\nFolder \"" + userFolderChoice + "\" selected.\n" );
		}
		else {
			System.out.println("\nInvalid game folder, please try again.\n");
			setGameFolder();
		}
	}

	//Make object list of all subfolder
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

	//Make Object
	public void makeGameObject(String filePath, String object) {
		try {
			//File Reader
			BufferedReader bufferedReader = 
					new BufferedReader(new FileReader(filePath));

			String fileLine = null;
			String setCode = null;

			Items itemObject = new Items();
			Monsters monsterObject = new Monsters();
			Rooms roomObject = new Rooms();

			while((fileLine = bufferedReader.readLine()) != null) {
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
						itemObject.setItemActionValue(fileLine);
						break;	
					case "Item Usage Times:":
						itemObject.setItemUsageTime(Integer.parseInt(fileLine));
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
						monsterObject.setMonsterHealth(Integer.parseInt(fileLine));
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
						boolean roomAccess = false;
						if(fileLine.equalsIgnoreCase("True"))
							roomAccess = true;
						else if(fileLine.equalsIgnoreCase("False"))
							roomAccess = false;
						else 
							System.out.println("Error setting room access at " + filePath);

						roomObject.setRoomAccess(roomAccess);
						break;
					case "Room Item:":
						if(!fileLine.equals("null")) {
							roomObject.addRoomItemId(fileLine);
							break;
						}
					case "Room Monster:":
						if(!fileLine.equals("null")) {
							roomObject.addRoomMonsterId(fileLine);
							break;
						}
					}	
				}
			}

			if(object.equals("Item/"))
				itemList.put(itemObject.getItemId(), itemObject);
			else if(object.equals("Monster/"))
				monsterList.put(monsterObject.getMonsterId(), monsterObject);
			else if(object.equals("Room/"))
				roomList.put(roomObject.getRoomId(), roomObject);

		} catch (IOException e) {
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
			String userInput = getUserInput();
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
						action();
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

	public void save() {
		boolean loop = true;
		while(loop) {
			saveList.clear();
			setSaveList();
			displaySaveList();
			System.out.println("\nSelect a slot to save, or type \"Exit\" to cancel");
			String userInput = getUserInput();
			if(userInput.equalsIgnoreCase("e") || userInput.equalsIgnoreCase("exit")) {
				loop = false;
			}else {
				try {
					int userInputInt = Integer.parseInt(userInput);
					SaveData data = new SaveData(userInputInt,player,itemList,monsterList,roomList);
					if(userInputInt >= 1 && userInputInt <= 10) {
						ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(gameFolder + "Save/Save "+ userInputInt +".dat"));

						if(saveList.get(userInputInt) == null) {
							player.endGameTime();
							saveList.put(userInputInt,data);
							out.writeObject(data);
							player.startGameTime();
							System.out.println("\nSuccessfully saved in Slot " + userInputInt);
							loop = false;
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
								loop = false;
								break;
							case "no": case "n": case "2":
								System.out.println("\nOverwrite cancelled");
								break;
							default:
								System.out.println("\nInvalid command, overwrite cancelled");
								break;
							}
						}
					}
					else {
						System.out.println("\nInvalid command");
					}
				}catch(Exception e) {
					System.out.println("\nInvalid command");
				}
			}
		}
	}

	public void newGame() {
		setGameFolder();
		makeListObject(gameFolder,"Item/");
		makeListObject(gameFolder,"Monster/");
		makeListObject(gameFolder,"Room/");
		setSaveList();
		System.out.println("Please choose your name:");
		String playerName = getUserInput();
		player = new Players(playerName,100,itemList.get("I01"),0,roomList.get("R01"));
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
		action();
	}

	public void action() {
		System.out.println("--------------------------------------------------");
		if(player.getCurrentRoom().getRoomMonsterId().isEmpty()) {
			System.out.println("MENU - What will you do?\n");
			System.out.println("1. Move");
			System.out.println("2. Examine Room");
			System.out.println("3. Check Inventory");
			System.out.println("4. Save Game");
			System.out.println("5. Quit Game");

			String userInput = getUserInput();

			switch(userInput.toLowerCase().trim()) {
			case "move": case  "1":
				System.out.print("\nWhich direction do you want to move?\n");
				System.out.println("Exits: " + player.getCurrentRoom().getRoomConnection());
				String roomDirection = getUserInput();
				if(player.getCurrentRoom().getRoomNavigationList().get(roomDirection) == null) {
					System.out.println("\nYou can't go that way!");
					action();
					break;
				}
				else {
					player.setCurrentRoom(player.getCurrentRoom().getRoomNavigationList().get(roomDirection),roomList);
					if(player.getCurrentRoom().getRoomMonsterId().isEmpty())
						System.out.println(player.getCurrentRoom().toString());
					action();
				}
				break;
			case "examine room": case "examine": case "search": case "look": case "2":
				System.out.println(player.getCurrentRoom().toString());
				if(!player.getCurrentRoom().getRoomItemId().isEmpty()) {
					for(Iterator<String> iterator = player.getCurrentRoom().getRoomItemId().iterator(); iterator.hasNext();) {
						String itemId = iterator.next();
						Items itemObject = itemList.get(itemId);
						itemObject = itemList.get(itemId);
						System.out.println("\nYou found a " + itemObject.getItemName() + ".");
						System.out.println("Do you want to pick it up?");
						System.out.println("1. Yes");
						System.out.println("2. No");
						String choice = getUserInput();
						switch(choice.toLowerCase().trim()) {
						case "yes": case "y": case "1":
							iterator.remove();
							System.out.println("\nYou picked up the " + itemObject.getItemName() + ".");
							player.addItemToInventory(itemObject);
							break;
						case "no": case "n": case "2":
							System.out.println("\nYou decided to leave the " + itemObject.getItemName() + ".");
							break;
						default:
							System.out.println("\nYou decided to leave the " + itemObject.getItemName() + ".");
							break;
						}
					}
				}
				else if(player.getCurrentRoom().getRoomItemId().isEmpty()){
					System.out.println("\nYou search the room, but find nothing of interest.");
				}
				else {
					System.out.println("\nError searching room.");
				}
				action();
				break;
			case "check inventory": case "check": case "inventory": case "3":
				inventoryMenu();
				action();
				break;
			case "save game": case "save": case "4":		
				save();
				action();
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
					action();
					break;
				}
				break;
			default:
				System.out.println("\nInvalid command: " + userInput);
				action();
				break;
			}


		}
		else if(!player.getCurrentRoom().getRoomMonsterId().isEmpty()){
			Monsters monster = monsterList.get(player.getCurrentRoom().getRoomMonsterId().get(0));
			System.out.println("You encounter a " + monster.getMonsterName() + "!");
			combatMenu();
		}
		else {
			System.out.println("Error with action");
		}
	}

	public void inventoryMenu() {
		System.out.println("--------------------------------------------------");
		System.out.println("Inventory:");

		System.out.println(player.getWeapon().getItemName() + " (Equipped)");
		for(Items item: player.getInventoryList()) {
				System.out.println(item.getItemName());
		}
		
		System.out.println("\nWhat will you do?\n");
		System.out.println("1. Examine Item");
		System.out.println("2. Drop Item");
		System.out.println("3. Use Item");
		System.out.println("4. Equip Item");
		System.out.println("5. Exit");

		String userInput = getUserInput();
		String itemName = "";
		boolean foundItem = false;

		switch(userInput.toLowerCase().trim()) {
		case "examine item": case "examine": case "1":
			System.out.println("\nWhat item do you want to examine?");
			itemName = getUserInput();
			foundItem = false;
			search:
				for(Items item:player.getInventoryList()) {
					if(itemName.equalsIgnoreCase(item.getItemName())) {
						foundItem = true;
						System.out.println(item.toString());
						inventoryMenu();
						break search;
					}
				}
			if(itemName.equalsIgnoreCase(player.getWeapon().getItemName())) {
				foundItem = true;
				System.out.println(player.getWeapon().toString());
				inventoryMenu();
				break;
			}
			if(foundItem == false) {
				System.out.println("\nYou don't have that item.");
				inventoryMenu();
			}
			break;
		case "drop item": case "drop": case "2":
			System.out.println("\nWhat item do you want to drop?");
			itemName = getUserInput();
			foundItem = false;
			search:
				for(Iterator<Items> iterator = player.getInventoryList().iterator(); iterator.hasNext();) {
					Items itemIterator = iterator.next();
					if(itemName.equalsIgnoreCase(itemIterator.getItemName())) {
						foundItem = true;
						iterator.remove();
						player.getCurrentRoom().addRoomItemId(itemIterator.getItemId());
						System.out.println("\nYou drop the " + itemIterator.getItemName());
						inventoryMenu();
						break search;
					}
				}
			if(itemName.equalsIgnoreCase(player.getWeapon().getItemName()))
			{
				foundItem = true;
				System.out.println("\nYou can't drop your equipped weapon!");
				inventoryMenu();
			}
			if(foundItem == false) {
				System.out.println("\nYou don't have that item.");
				inventoryMenu();
			}
			break;
		case "use item": case "use": case "3":
			break;
		case "equip item": case "equip": case "4":
			ArrayList<Items> weaponList = new ArrayList<Items>();
			for(Items item: player.getInventoryList()) {
				if(item.getItemType().equals("Weapon")) {
					weaponList.add(item);	
				}
			}
			if(!weaponList.isEmpty()) {
					System.out.println("\nSelect the weapon you want to equip:");
					for(Items item: player.getInventoryList()) {
						if(item.getItemType().equals("Weapon")) {
							System.out.println(item.getItemName());		
						}
					}
					itemName = getUserInput();
					foundItem = false;
					search:
						for(Iterator<Items> iterator = weaponList.iterator(); iterator.hasNext();) {
							Items itemIterator = iterator.next();
							String userWeapon = itemIterator.getItemName();
							if(itemName.equalsIgnoreCase(userWeapon)) {
								foundItem = true;
								player.equipWeapon(itemIterator.getItemId(), itemList);
								System.out.println("\nYou equipped the " + userWeapon);
								inventoryMenu();
								break search;
							}
						}
					if(foundItem == false) {
						System.out.println("\nYou don't have that item.");
						inventoryMenu();
					}
			}
			else {
				System.out.println("\nYou don't have any weapons");
				inventoryMenu();
			}
			break;
		case "exit": case "5":
			break;
		default:
			System.out.println("\nInvalid command");
			inventoryMenu();
			break;
		}

	}

	public void combatMenu() {
		Monsters monster = monsterList.get(player.getCurrentRoom().getRoomMonsterId().get(0));
		
		System.out.println("--------------------------------------------------");
		System.out.println("COMBAT - What will you do?\n");
		System.out.println("1. Attack");
		System.out.println("2. Defend");
		System.out.println("3. Examine Monster");
		System.out.println("4. Check Inventory");
		System.out.println("5. Run Away");

		String userInput = getUserInput();

		switch(userInput.toLowerCase().trim()) {
		case "attack": case "1":
			System.out.println("\nYou attack the " + monster.getMonsterName() + "!");
			System.out.println("The " + monster.getMonsterName() + " took " + player.getWeapon().getItemActionValue() + " damage!");
			monster.takeDmg(player.getWeapon().getItemActionValue());

			if(monster.getMonsterHealth() <= 0) {
				System.out.println("The " + monster.getMonsterName() + " slumps over, defeated.");
				player.getCurrentRoom().removeRoomMonsterId(monster.getMonsterId());
				action();
				break;
			}

			System.out.println("The " + monster.getMonsterName() + " attacks you!");
			if(monster.attackPlayer() > 0) {
				System.out.println("You took " + monster.attackPlayer() + " damage!");
			}
			else
			{
				System.out.println("The attack missed!");
			}

			combatMenu();
			break;
		case "defend": case "2":
			System.out.println("\nYou block the attack and take no damage!");
			combatMenu();
			break;
		case "examine monster": case "examine": case "3":
			System.out.println(monster.toString());
			combatMenu();
			break;
		case "check inventory": case "check": case "inventory": case "4":
			inventoryMenu();
			combatMenu();
			break;
		case "run away": case "run": case "5":
			System.out.println("\nYou flee to the previous room.");
			player.setCurrentRoom(player.getPreviousRoom().getRoomId(), roomList);
			action();
			break;
		default:
			System.out.println("\nInvalid command");
			combatMenu();
			break;	
		}
	}

}
