package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Rooms{
	public int roomId;
	public String roomName;
	public String roomDescription;
	HashMap<String, Integer> map = new HashMap();
	
	public Rooms(){
		
	}
	
	public void setRoomId(int roomId){
		this.roomId = roomId;
	}
	
	public void setRoomName(String roomName){
		this.roomName = roomName;
	}
	
	public void setDescription(String roomDescription){
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
	
	class manageRoom{
		ArrayList<Rooms> roomList = new ArrayList<Rooms>();
		
		public void makeRoom(String gameFolder) {
			//Create a list of all the file in the Game Folder
			String folderPath = "./res/" + gameFolder + "/Monster";
			try {	
				File folder = new File(folderPath);
				File[] listOfFiles = folder.listFiles();
				String fileName = null;
					for (File file : listOfFiles) {
						if (file.isFile()) {
							fileName = file.getName();
							makeRoomObject(fileName);
						}
					}
			}
			catch(Exception e) {
	            System.out.println(e);                
	        }	
		}
		
		public void makeRoomObject(String fileName) {
			//Get the name of the file one at a time, and make an object with them
			try {
				BufferedReader bufferedReader = 
		        	new BufferedReader(new FileReader("./res/" + fileName ));
		           
		        String fileLine = null;
		        String setCode = null;
		        Rooms roomObject = new Rooms();
		           
		        while((fileLine = bufferedReader.readLine()) != null) {
		        	switch(fileLine) {
		            	case "Room Name:":
		                	setCode = "Room Name";
		                	fileLine = bufferedReader.readLine();
		                	break;
		                case "Room ID:":
		                	setCode = "Room ID";
		                	fileLine = bufferedReader.readLine();
		                	break;
		                case "Room Coordinate:":
		                	setCode = "Room Coordinate";
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
		            	switch(setCode) {
		                	case "Room Name":
		                		roomObject.setRoomName(fileLine);
		                		break;
		                	case "Room ID":
		                		roomObject.setRoomId(Integer.parseInt(fileLine));
		                		break;
		                	case "Room Coordinate":
		                		int[]coordinate = new int[3];
		                		int num = 0;
		                		for(String x: fileLine.split(",")) {
		                			coordinate[num] = 1;
		                			num++;
		                		}
		                		break;
		                	case "Room Description":
		                		roomObject.setDescription(fileLine);
		                		break;
		                	case "Room Connection":
		                		String[] parts = fileLine.split("-");
		                		String part1 = parts[0]; // 004
		                		String part2 = parts[1];
		                		roomObject.setRoomConnection(part1, Integer.parseInt(part2));
		                		break;	
		                	}
		            	}
		            	
		            	
		  
		            }   
		        	roomList.add(roomObject); 
		            bufferedReader.close();         
		        }
		        catch(FileNotFoundException ex) {
		            System.out.println("Unable to open file '" + fileName + "'");                
		        }
		        catch(IOException ex) {
		            System.out.println("Error reading file '" + fileName + "'");                  
		        }
		}
	}
}
