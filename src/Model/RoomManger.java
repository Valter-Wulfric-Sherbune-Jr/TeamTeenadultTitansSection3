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

public class RoomManger{
	//List of all the room
	HashMap<Integer, Rooms> roomList = new HashMap();
	
	public void makeRoom(String gameFolder) {
		//Check a specify folder for all room file, then make an object for each one
		String folderPath = "./res/" + gameFolder + "/Room";
		try {	
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
				for (File file : listOfFiles) {
					if (file.isFile()) {
						fileName = file.getName();
						folderPath = "./res/" + gameFolder + "/Room/" + fileName;
						makeRoomObject(folderPath);
					}
				}
		}
		catch(Exception e) {
            System.out.println(e);                
        }	
	}
	
	public void makeRoomObject(String filePath) {
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
	                }	
	        	if(setCode != null) {
	        		//Depending on the setcode, it'll set the infromation it got from flieLine
	            	switch(setCode) {
	                	case "Room Name":
	                		roomObject.setRoomName(fileLine);
	                		break;
	                	case "Room ID":
	                		roomObject.setRoomId(Integer.valueOf(fileLine));
	                		break;
	                	case "Room Description":
	                		roomObject.setRoomDescription(fileLine);
	                		break;
	                	case "Room Connection":
	                		String[] RoomConnection = fileLine.split(":");
	                		String roomID = RoomConnection[0];
	                		String Direction = RoomConnection[1];
	                		roomObject.setRoomConnection(Direction, Integer.parseInt(roomID));
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
	
	//Get Room object from hashmap and prints the toString
	public void loadRoomID(int roomID) {
		Rooms room = roomList.get(roomID);
		System.out.println(room.toString());
		move(roomID);
	}
	
	public void move(int roomID) {
		Rooms room = roomList.get(roomID);
		Scanner input = new Scanner(System.in);
		String direction = input.nextLine();
		HashMap<String, Integer> map = room.getHashMap();
		
		loadRoomID(map.get(direction));
		
	}
}

class Rooms{
	public int roomId;
	public String roomName;
	public String roomDescription;
	HashMap<String, Integer> map = new HashMap();
	
	public Rooms(){
		this.roomId = 99;
		this.roomName = "Invalid Room";
		this.roomDescription = "Invalid Description";
	}
	
	public void setRoomId(int roomId){
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
	
	
	public void setRoomConnection(String string1, int parseInt) {
		map.put(string1, parseInt);	
	}
	
	public HashMap getHashMap() {
		return map;
	}
	public int getRoomId(){
		return roomId;
	}
	public String getRoomName(){
		return roomName;
	}
	public String getRoomDescription(){
		return roomDescription;
	}
	
	public String getRoomConnection() {
		String RoomConnection = "";
		Set set = map.entrySet();
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