package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RoomSubManager implements SubManagerInterface{

	//List of all the room
	private HashMap<String, Rooms> roomList = new HashMap<String, Rooms>();
	private final String gameSubFolder = "/Room";
	private String gameFolder = "";



	@Override
	public void makeList(String gameFolder) {
		String folderPath = gameFolder + gameSubFolder;
		try {	
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
			for (File file : listOfFiles) {
				if (file.isFile()) {
					fileName = file.getName();
					folderPath = gameFolder + gameSubFolder + "/" + fileName;
					makeListObject(folderPath);
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error in "+ gameSubFolder + ":/n" + e.toString());
		}	
	}

	@Override
	public void makeListObject(String filePath) {
		//Get the name of the file one at a time, and make an object with them
		try {

			//File Reader
			BufferedReader bufferedReader = 
					new BufferedReader(new FileReader(filePath));

			String fileLine = null;
			//Set Code is the title of the string ex: (Room Name, Room ID)
			String setCode = null;
			Rooms roomObject = new Rooms();

			while((fileLine = bufferedReader.readLine()) != null) {
				//If fileLine reads a setCode, it get's replace it's current fileline with the next fileline and sets the setcode;
				switch(fileLine) {
				case "Room Name:":
					setCode = "Room Name";
					fileLine = bufferedReader.readLine();
					break;
				case "Room ID:":
					setCode = "Room ID";
					fileLine = bufferedReader.readLine();
					break;
				case "Room Description:":
					setCode = "Room Description";
					fileLine = bufferedReader.readLine();
					break;
				case "Room Connection:":
					setCode = "Room Connection";
					fileLine = bufferedReader.readLine();
					break;	
				case "Room Access:":
					setCode = "Room Access";
					fileLine = bufferedReader.readLine();
					break;
				case "Room Item:":
					setCode = "Room Item";
					fileLine = bufferedReader.readLine();
					break;	
				case "Room Enemy:":
					setCode = "Room Enemy";
					fileLine = bufferedReader.readLine();
					break;	
				}	
				if(setCode != null) {
					//Depending on the setcode, it'll set the infromation it got from flieLine
					switch(setCode) {
					case "Room Name":
						roomObject.setRoomName(fileLine);
						break;
					case "Room ID":
						roomObject.setRoomId(fileLine);
						break;
					case "Room Description":
						roomObject.setRoomDescription(fileLine);
						break;
					case "Room Connection":
						String[] RoomConnection = fileLine.split(":");
						String roomID = RoomConnection[0];
						String Direction = RoomConnection[1];
						roomObject.setRoomConnection(Direction, roomID);
						break;
					case "Room Access:":
						roomObject.setRoomAccess(fileLine);
						break;
					case "Room Item":
						if(fileLine.equals("null")) {
							roomObject.setHasItem(false);
							break;
						}else {
							ItemSubManager itemlist = new ItemSubManager();
							itemlist.makeList("./res/Hydra Game File");
							roomObject.setRoomItem(itemlist.getList().get(fileLine));
							//System.out.println(itemlist.getList().get(fileLine).getItemDesc());
							roomObject.setHasItem(true);
							break;
						}
					case "Room Enemy":
						if(fileLine.equals("null")) {
							roomObject.setHasMonster(false);
							break;
						}
						else {
							ItemSubManager monsterlist = new ItemSubManager();
							roomObject.setRoomItem(monsterlist.getList().get(fileLine));
							roomObject.setHasMonster(true);
							break;
						}
					}	
				}
			}
			//After it finish setting up object, object is then sent to a hashmap with room id as a key
			roomList.put(roomObject.getRoomId(), roomObject);
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + filePath + "'");                
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + filePath+ "'");                  
		}

	}

	@Override
	public void loadListId(String Id) {
		Rooms room = roomList.get(Id);
		System.out.println(room.toString());
	}

	@Override
	public HashMap<String, Rooms> getList() {
		return roomList;
	}

}

class Rooms implements Serializable{
	public String roomId;
	public String roomName;
	public String roomDescription;
	public boolean roomAccess;
	public Items roomItem;
	public Monsters roomMonster;
	public boolean hasItem;
	public boolean hasMonster;
	HashMap<String, String> roomNavigationList = new HashMap<String, String>();

	public Rooms(){
		this.roomId = "00";
		this.roomName = "Invalid Room";
		this.roomDescription = "Invalid Description";
		this.roomAccess = false;
	}

	public Rooms(String roomId, String roomName, String roomDescription, boolean roomAccess, Items roomItem,
			Monsters roomMonster, HashMap<String, String> roomNavigationList) {
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.roomAccess = roomAccess;
		this.roomItem = roomItem;
		this.roomMonster = roomMonster;
		this.roomNavigationList = roomNavigationList;
	}

	public void setHasItem(boolean hasItem) {
		this.hasItem = hasItem;
	}
	public void setHasMonster(boolean hasMonster) {
		this.hasMonster = hasMonster;
	}
	public boolean getHasItem() {
		return hasItem;
	}
	public boolean getHasMonster() {
		return hasMonster;
	}
	public Items getRoomItem() {
		return roomItem;
	}

	public void setRoomItem(Items roomItem) {
		this.roomItem = roomItem;
	}

	public Monsters getRoomMonster() {
		return roomMonster;
	}

	public void setRoomMonster(Monsters roomMonster) {
		this.roomMonster = roomMonster;
	}

	public void setRoomId(String roomId){
		this.roomId = roomId;
	}
	public void setRoomName(String roomName){
		this.roomName = roomName;
	}
	public void setRoomDescription(String roomDescription){
		if(roomDescription.length() > 50) {
			int totalCharacterLength = 0;
			int descriptionLength = roomDescription.length();
			String outputString ="";

			for (String word : roomDescription.split(" ")) {
				totalCharacterLength += word.length();
				descriptionLength -= word.length()+1;
				outputString += word + " ";

				if(totalCharacterLength > 40 && descriptionLength > 0) {
					totalCharacterLength = 0;
					outputString += "\n";
				}
			}
			roomDescription = outputString;
		}

		this.roomDescription = roomDescription;
	}
	public void setRoomAccess(String access){
		if(access.equalsIgnoreCase("True")) {
			this.roomAccess = true;
		}
		else if(access.equalsIgnoreCase("False")) {
			this.roomAccess = false;
		}
		else {
			System.out.println("Error Loading Room Access");
		}
	}
	public void setRoomConnection(String direction, String roomId) {
		this.roomNavigationList.put(direction, roomId);	
	}
	public HashMap<String, String> getRoomNavigation() {
		return roomNavigationList;
	}
	public String getRoomId(){
		return roomId;
	}
	public String getRoomName(){
		return roomName;
	}
	public String getRoomDescription(){
		return roomDescription;
	}	
	public boolean getRoomAccess(){
		return roomAccess;
	}
	public String getRoomConnection() {
		String RoomConnection = "";
		Set set = roomNavigationList.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) { 
			Map.Entry mEntry = (Map.Entry)iterator.next();
			String coordinate =   (String) mEntry.getValue();
			RoomConnection += mEntry.getKey();
			if(iterator.hasNext()) {
				RoomConnection += ",";
			}
		}
		return RoomConnection;
	}
	public String toString() {
		String returnString = "";

		//Add Guide Line
		returnString += "---------------------------------------\n";

		//Add Room Name
		returnString += "Room Name:\n" + getRoomName() + "\n\n";

		//Add Room ID
		returnString += "Room ID:\n" + getRoomId() + "\n\n";

		//Add Room Description
		returnString += "Room Description:\n" + getRoomDescription() + "\n\n";

		//Add Direction
		returnString += "Direction:\n" + getRoomConnection() + "\n";

		//Add Guide Line
		returnString += "---------------------------------------";

		return returnString;
	}
}