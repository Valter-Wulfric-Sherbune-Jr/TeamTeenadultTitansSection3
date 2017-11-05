package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
			System.out.println(gamefolder);
		}

		String userFolderChoice = getUserInput();
		boolean vaildChoice = false;

		for(String folder : gameList) {
			if(userFolderChoice.equalsIgnoreCase(folder)) {
				gameFolder = "./res/Game Folder/" + folder + "/";
				vaildChoice = true;
				break;
			}
		}

		if(vaildChoice == false) {
			System.out.println("Invalid Game Folder");
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
				for(String itemSetCode: itemCode) {
					if(itemSetCode.equalsIgnoreCase(fileLine)) {
						setCode = itemSetCode;
						fileLine = bufferedReader.readLine();
						break;
					}
				}
				for(String monsterSetCode: monsterCode) {
					if(monsterSetCode.equalsIgnoreCase(fileLine)) {
						setCode = monsterSetCode;
						fileLine = bufferedReader.readLine();
						break;
					}
				}
				for(String roomSetCode: roomCode) {
					if(roomSetCode.equalsIgnoreCase(fileLine)) {
						setCode = roomSetCode;
						fileLine = bufferedReader.readLine();
						break;
					}
				}

				if(setCode != null) {
					//Depending on the set code, it'll set the information it got from flieLine
					switch(setCode) {
					case "Item Name":
						itemObject.setItemName(fileLine);
						break;
					case "Item ID":
						itemObject.setItemId(fileLine);
						break;
					case "Item Description":
						itemObject.setItemDesc(fileLine);
						break;
					case "Item Type":
						itemObject.setItemType(fileLine);
						break;	
					case "Item Action Value":
						itemObject.setItemActionValue(fileLine);
						break;	
					case "Item Usage Times":
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

	public void newGame() {
		setGameFolder();
		makeListObject(gameFolder,"Item/");
		makeListObject(gameFolder,"Monster/");
		makeListObject(gameFolder,"Room/");

		System.out.println("Please choose your name:");
		String playerName = getUserInput();
		player = new Players(playerName,100,itemList.get("I01"),0,roomList.get("R01"));
		//Debug Save later
		//saveGame("S1",player);

		System.out.println("New Game Created");
		System.out.println("\nPrivate your mission is to investigate this building and clear out all enemy");
		System.out.println("You jumped out of the plane and landed on the roof");
		System.out.println(player.getCurrentRoom().toString());
		action(player);
	}

	public void action(Players player) {
		if(player.getCurrentRoom().getRoomMonsterId().isEmpty()) {
			System.out.println("What will you do?");
			System.out.println("1. Move");
			System.out.println("2. Search Room");
			System.out.println("3. Check Inventory");

			String userinput = getUserInput();

			switch(userinput) {
			case "Move":
				System.out.print("\nWhat direction you wanna move?\n");
				System.out.println(player.getCurrentRoom().getRoomConnection());
				String roomDirection = getUserInput();
				player.setCurrentRoom(player.getCurrentRoom().getRoomNavigationList().get(roomDirection),roomList);
				if(player.getCurrentRoom().getRoomMonsterId().isEmpty())
					System.out.println(player.getCurrentRoom().toString());
				action(player);
				break;
			default:
				System.out.println("Wrong Command inputed\n");
				action(player);
				break;
			}


		}
		else if(!player.getCurrentRoom().getRoomMonsterId().isEmpty()){
			System.out.println("You encounter a monster");
		}
	}


}