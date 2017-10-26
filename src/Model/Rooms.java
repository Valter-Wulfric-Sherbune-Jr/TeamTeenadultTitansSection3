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
			
			//
			room[2][2][2].setRoomId(12);
			room[2][2][2].setName("Second Floor");
			room[2][2][2].setDescription("This division holds the offices and work centers of employees "
					+ "who are part of this sectors engineering branch. Large assembly machines can be seen "
					+ "overhead along with the rail system used for movement. "
					+ "Black and yellow hazard lines mark which walkways are safe for movement,"
				+ " and what areas should be avoided.");
			
			room[2][1][2].setRoomId(13);
			room[2][1][2].setName("Second Floor");
			room[2][1][2].setDescription("The massive concrete and steel-plated room has a large portion of its floorspace "
					+ "dedicated to the industrial maglev docking station. Freight loading and unloading is "
					+ "performed in this location by wall and ceiling mounted cranes. "
					+ "Automated rail carts can be seen stationed with their cargo ready for delivery.");
			
			room[2][0][2].setRoomId(14);
			room[2][0][2].setName("Second Floor");
			room[2][0][2].setDescription("This location manages and controls the various communications traffic "
					+ "within the facility. A large screen flanked by multiple smaller ones co-vers the forward-facing "
					+ "wall. Throughout the room there are stations with multiple monitors used for analyzing"
					+ " network operations.");
			
			room[0][0][0].setRoomId(15);
			room[0][0][0].setName("Second Floor");
			room[0][0][0].setDescription("Several elongated hallways are present with numbered residential rooms "
					+ "positioned on either side. The passages are interconnected by lounging areas that have their "
					+ "furnishings knocked over. Many of the lighting fixtures that lined the hallways are now "
					+ "inactive, and have had their functions replaced by the emergency lighting stripes situated at "
					+ "the hallway corners that now illuminate the narrow passages with amber light.");
			
			
			
			
		
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
