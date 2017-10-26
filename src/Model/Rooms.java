package Model;
import java.util.Arrays;


class Rooms {
	 public static void roomCreate(Room[][][] room,final int x, final int y, final int z){
		for(int i = 0; i < x; i++){
			for (int j =0; j < y; j++){
				for(int a = 0; a< z; a++){
					 room[i][j][a] = new Room(i,j,a,null);
				}
			}
			
			//Room ArrayList
			room[0][0][0].setRoomId();
			room[0][0][0].setName("Roof");
			room[0][0][0].setDescription();
			
			//4th Floor 
			room[0][0][4].setRoomId(01);
			room[0][0][4].setName("Roof");
			room[0][0][4].setDescription("A breezy Helicopter landing pad with two metal doors. "
					+ "	The landing pad sits on top of the building "
					+ "	and the lights of govern-ment vehicles can be seen below.");
			
			room[0][1][4].setRoomId(02);
			room[0][1][4].setName("Roof");
			room[0][1][4].setDescription("A room with maroon carpet and potted plastic plants in the corner. "
					+ "here is an elevator with red stains on it. "
					+ "It doesn’t appear to be functional as the key scanner is broken.");
			
			room[1][0][4].setRoomId(03);
			room[1][0][4].setName("Roof");
			room[1][0][4].setDescription("There is a staircase going down one floor and two doors. "
					+ "The metal safety shields have removed outside light as lights below flicker.");
			
			
			
			
			
		
		} 
	 }
	
class Room{
	public int roomId;
	public String name;
	public String description;
	
	public Room(int roomId, String name, String description){
		
	}
	public Room(int i, int j, int a, Object object) {
		// TODO Auto-generated constructor stub
	}
	public void setRoomId(int roomId){
		this.roomId = roomId;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setDescription(String description){
		this.setDescription(description);
	}
	
	public int getRoomId(){
		return	this.roomId;
	}
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.getDescription();
	}
	
}	 
	

}
