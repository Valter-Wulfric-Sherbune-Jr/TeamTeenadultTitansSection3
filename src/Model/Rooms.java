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
			room[0][0][0].setRoomId(0);
			room[0][0][0].setName("Roof");
			room[0][0][0].setDescription("");
			
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
			
			room[1][1][2].setRoomId(16);
			room[1][1][2].setName("Floor 2");
			room[1][1][2].setDescription("This largely open space with high roof clearance is used for testing new technologies for ground based vehicles. "
					+ "Refitment platforms line one side of the area while vehicle frames and parts line the other. "
					+ "A few next generation APCs based on the militaries extended Stryker fami-ly of armored fighting vehicles are also present. "); 
			
			room[1][0][2].setRoomId(17);
			room[1][0][2].setName("Floor 2");
			room[1][0][2].setDescription("Metallic booths and scanners are present in the middle of the hall and cut the room into two sections. "
					+ "White concrete lines the walls, and the floors are composed of satin white marble broken up by grey tiles in square patterns. "
					+ "Office supplies and papers are scattered all over, and security cameras can be found in every corner. "); 
			
			room[0][0][2].setRoomId(18);
			room[0][0][2].setName("Floor 2");
			room[0][0][2].setDescription("TNumerous tables and chairs are strewn about horizontally, and scattered among them are dispensing stations for food and drinks. "
					+ "Plates, utensils, and other tableware have been left as they were right be-fore the evacuation. "); 
			
			room[0][1][2].setRoomId(19);
			room[0][1][2].setName("Floor 2");
			room[0][1][2].setDescription("A contrasting rectangular room with white colored sides, black furniture, grey carpet, and ambient lights at the top corners. "
					+ "Shattered displays and damaged doors are located on either side of the hall. "); 
			
			
			room[1][0][0].setRoomId(30);
			room[1][0][0].setName("Underground");
			room[1][0][0].setDescription("There are wire coming in and out of the room to the West and South. "
					+ "The wires are connected to many servers. " 
					+ "The servers appear to be offline for security reasons. " 
					+ "There is also pipe connected to the servers coming from the ceiling.");
			
			room[0][1][0].setRoomId(31);
			room[0][1][0].setName("Underground");
			room[0][1][0].setDescription("There are wires coming in and out of the walls to the North and South. "
					+ "The room edges are lined with old legacy technology. "
					+ "Modems and Routers and HUBs and switches are everywhere. "
					+ "There is a large machine that looks like it tests projectiles and has a bin of old computers to test. " 
					+ "The wall facing away from the machine has bits of technology debris everywhere. ");
			
			room[1][2][0].setRoomId(32);
			room[1][2][0].setName("Underground");
			room[1][2][0].setDescription("The room isn’t as wired as the others. "
					+ "It has new tech that hasn’t been advertised yet lying around. "
					+ "There’s lots of notes and binders labeled “Prototype 257” through “Prototype 302”. " 
					+ "There is a door that says do not enter. ");
			
			room[0][2][0].setRoomId(33);
			room[0][2][0].setName("Underground");
			room[0][2][0].setDescription("There is a single computer in this room on a desk. "
					+ "There is very few wires and all of them go to the computer from the walls. ");
			
			
			
			
			
			
			
		
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
