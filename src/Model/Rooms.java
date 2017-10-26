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
			
			
			//Floor 3
			room[0][0][0].setRoomId(04);
			room[0][0][0].setName("Third Floor");
			room[0][0][0].setDescription("An elongated room that has sectioned off areas filled with "
					+ "various obsta-cles used for training autonomous"
					+ " or semi-autonomous machines. The courses show extensive damage from the numerous "
					+ "live fire exercises done in these environments.");
			
			

			
			
			//Floor 2
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
			
			
			
			

			//Room 20: Connected to Room 21 (South) and Room 25 (West)
			room[2][0][1].setRoomId(20);
			room[2][0][1].setName("Floor 1");
			room[2][0][1].setDescription("A room storing various canisters of foodstuffs and medical supplies."
					+ "Many of the food cansiters are untouched, but all of the medical supplies have been looted.");

			//Room 21: Connected to Room 20 (North) and Room 24 (West)
			room[2][1][1].setRoomId(21);
			room[2][1][1].setName("Floor 1");
			room[2][1][1].setDescription("A steel corridor that has fluorescent lights integrated into the ceiling."
					+ "A damaged security droid is laying facedown, and a nearby wall is marked with bullet holes.");

			//Room 22: Connected to Room 23 (West)
			room[2][2][1].setRoomId(22);
			room[2][2][1].setName("Floor 1");
			room[2][2][1].setDescription("Localized computing clusters contained in large metal cases with tempered glass fronts that are organized in lines of five units per row."
					+ "These systems work independently from the sectors main grid of computers, and they dynamically allocate their resources to whatever department requires more services.");

			//Room 23: Connected to Room 22 (East), Room 24 (North), and Room 28 (West)
			room[1][2][1].setRoomId(23);
			room[1][2][1].setName("Floor 1");
			room[1][2][1].setDescription("This room is the main entrance."
					+ "There is a metal shield blocking the front door."
					+ "There are many bodies of people who tried to escape.");

			//Room 24: Connected to Room 21 (East), Room 23 (South), and Room 25 (North)
			room[1][1][1].setRoomId(24);
			room[1][1][1].setName("Floor 1");
			room[1][1][1].setDescription("The center of the room has the EMPYREAN’s famous High-Energy Particle machine."
					+ "There are many labs and detectors around it to test how particles react under certain conditions."
					+ "There is a walkway going along the outer part of the room for employees and tour groups to get around the machine and stations.");

			//Room 25: Connected to Room 20 (East), Room 24 (South), and Room 26 (West)
			room[1][0][1].setRoomId(25);
			room[1][0][1].setName("Floor 1");
			room[1][0][1].setDescription("The ceiling of this room is much higher than the ceilings of other rooms."
					+ "There is glass below the ceiling."
					+ "There are temperature regulation pipes and bright lights above the glass."
					+ "The Floor is a sidewalk surrounded by dirt."
					+ "The dirt has a wide variety of of small trees and other plants."
					+ "Birds can be heard.");

			//Room 26: Connected to Room 25 (East) and Room 27 (South)
			room[0][0][1].setRoomId(26);
			room[0][0][1].setName("Floor 1");
			room[0][0][1].setDescription("The room has many glass enclosures."
					+ "A few of the enclosures have dead or diseased animals."
					+ "Each enclosure has a well recorded binder full of notes stating how the animals reacted to viruses and toxins.");

			//Room 27: Connected to Room 18 (Staircase/Up), Room 26 (North), and Room 28 (South)
			room[0][1][1].setRoomId(27);
			room[0][1][1].setName("Floor 1");
			room[0][1][1].setDescription("This room is the bottom of a stairwell."
					+ "There is an exit sign near the door to the South and bloody handprint on the door to the North.");

			//Room 28: Connected to Room 23 (East) and Room 27 (North)
			room[0][2][1].setRoomId(28);
			room[0][2][1].setName("Floor 1");
			room[0][2][1].setDescription("There are many desks."
					+ "There are a few meeting rooms."
					+ "This is where the bulk of the business is done."
					+ "Several desks have been knocked over from the panic.");

			
			  
			
			
		
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
