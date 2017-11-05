package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Rooms implements Serializable{
	private String roomId;
	private String roomFloor;
	private String roomDescription;
	private boolean roomAccess;
	private ArrayList<String> roomItemId;
	private ArrayList<String> roomMonsterId;
	private HashMap<String, String> roomNavigationList = new HashMap<String, String>();

	public Rooms(){
		this.roomId = "R00";
		this.roomFloor = "Invalid Floor";
		this.roomDescription = "Invalid Description";
		this.roomAccess = false;
		this.roomItemId = new ArrayList<String>();
		this.roomMonsterId = new ArrayList<String>();
	}

	public Rooms(String roomId, String roomFloor, String roomDescription, boolean roomAccess,
			ArrayList<String> roomItemId, ArrayList<String> roomMonsterId, HashMap<String, String> roomNavigationList) {
		this.roomId = roomId;
		this.roomFloor = roomFloor;
		this.roomDescription = roomDescription;
		this.roomAccess = roomAccess;
		this.roomItemId = roomItemId;
		this.roomMonsterId = roomMonsterId;
		this.roomNavigationList = roomNavigationList;
	}

	public void setRoomId(String roomId){
		this.roomId = roomId;
	}
	
	public String getRoomId(){
		return roomId;
	}
	
	public void setRoomFloor(String roomFloor){
		this.roomFloor = roomFloor;
	}
	
	public String getRoomFloor(){
		return roomFloor;
	}
	
	public void setRoomDescription(String roomDescription){
		/*If there are more then 50 character in a line, 
		it'll make a new line*/
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
	
	public String getRoomDescription(){
		return roomDescription;
	}
	
	public void setRoomAccess(boolean access){
		this.roomAccess = access;
	}

	public boolean getRoomAccess(){
		return roomAccess;
	}
	
	public void addRoomItemId(String roomItemId) {
		this.roomItemId.add(roomItemId);
	}
	
	public void removeRoomItemId(String roomItemId) {
		boolean remove = false;
		
		search:
		for(int x = 0; x < this.roomItemId.size(); x++) {
			if(this.roomItemId.get(x).equalsIgnoreCase(roomItemId)) {
				this.roomItemId.remove(x);
				remove = true;
				break search;
			}
		}
		
		if(!remove)
		System.out.println("Error: Item " + roomMonsterId + "  does not exist in this room");
	}
	
	public void setRoomItemId(ArrayList<String> roomItemId) {
		this.roomItemId = roomItemId;
	}

	public ArrayList<String> getRoomItemId() {
		return roomItemId;
	}
	
	public void addRoomMonsterId(String roomMonsterId) {
		this.roomMonsterId.add(roomMonsterId);
	}
	
	public void removeRoomMonsterId(String roomMonsterId) {
		boolean remove = false;
		
		search:
		for(int x = 0; x < this.roomMonsterId.size(); x++) {
			if(this.roomMonsterId.get(x).equalsIgnoreCase(roomMonsterId)) {
				this.roomMonsterId.remove(x);
				remove = true;
				break search;
			}
		}
		
		if(!remove)
		System.out.println("Error: Monster " + roomMonsterId + " does not exist in this room");
	}
	
	public void setRoomMonsterId(ArrayList<String> roomMonsterId) {
		this.roomMonsterId = roomMonsterId;
	}

	public ArrayList<String> getRoomMonsterId() {
		return roomMonsterId;
	}
	
	public void setRoomConnection(String direction, String roomId) {
		this.roomNavigationList.put(direction, roomId);	
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
	
	public HashMap<String, String> getRoomNavigationList() {
		return roomNavigationList;
	}
		
	public String toString() {
		String returnString = "";

		//Add Guide Line
		returnString += "---------------------------------------\n";

		//Add Room Name
		returnString += "Room Floor:\n" + getRoomFloor() + "\n\n";

		//Add Room Description
		returnString += "Room Description:\n" + getRoomDescription() + "\n\n";

		//Add Direction
		returnString += "Direction:\n" + getRoomConnection() + "\n";

		//Add Guide Line
		returnString += "---------------------------------------";

		return returnString;
	}
}