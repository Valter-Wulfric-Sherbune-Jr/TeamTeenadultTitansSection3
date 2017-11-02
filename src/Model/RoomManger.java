package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RoomManger implements ManagerInterface{
	
	//List of all the room
	private HashMap<String, Rooms> roomList = new HashMap<String, Rooms>();
	private final String gameSubFolder = "/Room";
	private String gameFolder = "";



//	public void move(int roomID) {
//		Rooms room = roomList.get(roomID);
//		Scanner input = new Scanner(System.in);
//		String direction = input.nextLine();
//		HashMap<String, Integer> map = room.getHashMap();
//		loadRoomID(map.get(direction));
//	}

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
	public void setGameFolder(String gameFolder) {
		this.gameFolder = "./res/" + gameFolder;
	}

	@Override
	public String getGameFolder() {
		return gameFolder;
	}

	@Override
	public String getGameSubFolder() {
		return gameSubFolder;
	}

	@Override
	public HashMap<String, Rooms> getList() {
		return roomList;
	}
	
}

class Rooms{
	public String roomId;
	public String roomName;
	public String roomDescription;
	public boolean roomAccess;
	HashMap<String, String> roomNavigationList = new HashMap<String, String>();

	public Rooms(){
		this.roomId = "00";
		this.roomName = "Invalid Room";
		this.roomDescription = "Invalid Description";
		this.roomAccess = false;
	}
	public Rooms(String roomId, String roomName, String roomDescription, boolean roomAccess) {
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.roomAccess = roomAccess;
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
			int coordinate =   (int) mEntry.getValue();
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
		returnString += "Choose Your Direction:\n" + getRoomConnection() + "\n";

		//Add Guide Line
		returnString += "---------------------------------------";

		return returnString;
	}
}