package Model;

public class Rooms
{
	Room room[][][] = new Room[4][4][6];
	
	public Rooms()
	{
		for(int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				for(int a = 0; a < 6; a++)
				{
					 room[i][j][a]= new Room(); 
				}
			}
		}
		
		//4th Floor 
				room[0][0][4].setRoomId(01);
				room[0][0][4].setName("\nRoof");
				room[0][0][4].setDescription("A breezy Helicopter landing pad with two metal doors. "
						+ "\n     	The landing pad sits on top of the building "
						+ "\n     	and the lights of govern-ment vehicles can be seen below.");
				
				room[0][1][4].setRoomId(02);
				room[0][1][4].setName("\nRoof");
				room[0][1][4].setDescription("A room with maroon carpet and potted plastic plants in the corner. "
						+ "\n     here is an elevator with red stains on it. "
						+ "\n     It doesn’t appear to be functional as the key scanner is broken.");
				
				room[1][0][4].setRoomId(03);
				room[1][0][4].setName("\nRoof");
				room[1][0][4].setDescription("There is a staircase going down one floor and two doors. "
						+ "\n     The metal safety shields have removed outside light as lights below flicker.");
				
				
				//Floor 3
				room[0][1][3].setRoomId(04);
				room[0][1][3].setName("\nThird Floor");
				room[0][1][3].setDescription("An elongated room that has sectioned off areas filled with "
						+ "\n     various obsta-cles used for training autonomous"
						+ "\n      or semi-autonomous machines. The courses show extensive damage from the numerous "
						+ "\n     live fire exercises done in these environments.");
				
				room[0][1][3].setRoomId(05);
				room[0][1][3].setName("\nThird Floor");
				room[0][1][3].setDescription("Large ionization machines are lined up against the walls,"
						+ "\n      and there are numerous metal pipes and tubing formations throughout the room. "
						+ "\n     These systems manage the facilities environmental atmosphere, "
						+ "\n     and the micro-climates that are present within different sectors.");
				
				room[1][0][3].setRoomId(06);
				room[1][0][3].setName("\nThird Floor");
				room[1][0][3].setDescription("The expansive room reminiscent of an airport terminal overlooks the con-necting substation in which the trams dock "
						+ "\n     and drop off personnel who are commuting. Screens that would normally show arrival and departure times "
						+ "\n     now flash lockdown messages across their displays."
						+ "\n      (A contingent of non-hostile military androids can be seen guarding the main exit to the substation)");
				
				room[1][1][3].setRoomId(07);
				room[1][1][3].setName("\nThird Floor");
				room[1][1][3].setDescription("Additive manufacturing machines are located on one side of the room,"
						+ "\n      and electronic testing/measurement instruments are present alongside them. "
						+ "\n     There are numerous stations that possess computer-aided design software used for rapid prototyping.");
				
				room[2][0][3].setRoomId(8);
				room[2][0][3].setName("\nThird Floor");
				room[2][0][3].setDescription("Dark grey marble lines the floor, walls, and columns of this expansive corridor."
						+ "\n      Large cutouts on the side filled with electrotransparent glass des-ignate the office spaces of the company’s administrative personnel.");
				
				room[2][1][3].setRoomId(9);
				room[2][1][3].setName("\nThird Floor");
				room[2][1][3].setDescription("This area is reserved for the study of organic materials."
						+ "\n      The sector in-cludes dedicated ecosystem observatories, specimen containment cells, and a gene-bank repository. "
						+ "\n     Orange painted hazmat robots are stored in wall mounted cells in an offline state.");
				
				room[3][0][3].setRoomId(10);
				room[3][0][3].setName("\nThird Floor");
				room[3][0][3].setDescription("A circular security desk stands in the middle of a large room, "
						+ "\n     and multiple hallway entrances can be seen nearby with colored lines on their sides designating which sub-sector they lead to. "
						+ "\n     The main exits leading to other locations are pulsating their emergency lights to indicate that there has been test chamber breaches.");
				
				room[3][1][3].setRoomId(11);
				room[3][1][3].setName("\nThird Floor");
				room[3][1][3].setDescription("The O.S.F. is home to various reinforced containers spread along the wall in a hexagonal pattern. "
						+ "\n     Larger vessels are stored within the floor, and are raised up once access is required. While the majority of the"
						+ "\n      containers are still in lockdown some of the storage units seem to be unlocked.");


				
				
				//Floor 2
				room[2][2][2].setRoomId(12);
				room[2][2][2].setName("\nFloor 2");
				room[2][2][2].setDescription("This division holds the offices and work centers of employees "
						+ "\n     who are part of this sectors engineering branch. Large assembly machines can be seen "
						+ "\n     overhead along with the rail system used for movement. "
						+ "\n     Black and yellow hazard lines mark which walkways are safe for movement,"
						+ "\n     and what areas should be avoided.");
				
				room[2][1][2].setRoomId(13);
				room[2][1][2].setName("\nFloor 2");
				room[2][1][2].setDescription("The massive concrete and steel-plated room has a large portion of its floorspace "
						+ "\n     dedicated to the industrial maglev docking station. Freight loading and unloading is "
						+ "\n     performed in this location by wall and ceiling mounted cranes. "
						+ "\n     Automated rail carts can be seen stationed with their cargo ready for delivery.");
				
				room[2][0][2].setRoomId(14);
				room[2][0][2].setName("\nFloor 2");
				room[2][0][2].setDescription("This location manages and controls the various communications traffic "
						+ "\n     within the facility. A large screen flanked by multiple smaller ones co-vers the forward-facing "
						+ "\n     wall. Throughout the room there are stations with multiple monitors used for analyzing"
						+ "\n      network operations.");
				

				room[0][0][0].setRoomId(15);
				room[0][0][0].setName("\nFloor 2");
				room[0][0][0].setDescription("Several elongated hallways are present with numbered residential rooms "
						+ "\n     positioned on either side. The passages are interconnected by lounging areas that have their "
						+ "\n     furnishings knocked over. Many of the lighting fixtures that lined the hallways are now "
						+ "\n     inactive, and have had their functions replaced by the emergency lighting stripes situated at "
						+ "\n     the hallway corners that now illuminate the narrow passages with amber light.");
				
				room[1][1][2].setRoomId(16);
				room[1][1][2].setName("\nFloor 2");
				room[1][1][2].setDescription("This largely open space with high roof clearance is used for testing new technologies for ground based vehicles. "
						+ "\n     Refitment platforms line one side of the area while vehicle frames and parts line the other. "
						+ "\n     A few next generation APCs based on the militaries extended Stryker fami-ly of armored fighting vehicles are also present. "); 
				
				room[1][0][2].setRoomId(17);
				room[1][0][2].setName("\nFloor 2");
				room[1][0][2].setDescription("Metallic booths and scanners are present in the middle of the hall and cut the room into two sections. "
						+ "\n     White concrete lines the walls, and the floors are composed of satin white marble broken up by grey tiles in square patterns. "
						+ "\n     Office supplies and papers are scattered all over, and security cameras can be found in every corner. "); 
				
				room[0][0][2].setRoomId(18);
				room[0][0][2].setName("\nFloor 2");
				room[0][0][2].setDescription("TNumerous tables and chairs are strewn about horizontally, and scattered among them are dispensing stations for food and drinks. "
						+ "\n     Plates, utensils, and other tableware have been left as they were right be-fore the evacuation. "); 
				
				room[0][1][2].setRoomId(19);
				room[0][1][2].setName("\nFloor 2");
				room[0][1][2].setDescription("A contrasting rectangular room with white colored sides, black furniture, grey carpet, and ambient lights at the top corners. "
						+ "\n     Shattered displays and damaged doors are located on either side of the hall. "); 
				
				
				//Floor 1
				//Room 20: Connected to Room 21 (South) and Room 25 (West)
				room[2][0][1].setRoomId(20);
				room[2][0][1].setName("\nFloor 1");
				room[2][0][1].setDescription("A room storing various canisters of foodstuffs and medical supplies."
						+ "\n     Many of the food cansiters are untouched, but all of the medical supplies have been looted.");

				//Room 21: Connected to Room 20 (North) and Room 24 (West)
				room[2][1][1].setRoomId(21);
				room[2][1][1].setName("\nFloor 1");
				room[2][1][1].setDescription("A steel corridor that has fluorescent lights integrated into the ceiling."
						+ "\n     A damaged security droid is laying facedown, and a nearby wall is marked with bullet holes.");

				//Room 22: Connected to Room 23 (West)
				room[2][2][1].setRoomId(22);
				room[2][2][1].setName("\nFloor 1");
				room[2][2][1].setDescription("Localized computing clusters contained in large metal cases with tempered glass fronts that are organized in lines of five units per row."
						+ "\n     These systems work independently from the sectors main grid of computers, and they dynamically allocate their resources to whatever department requires more services.");

				//Room 23: Connected to Room 22 (East), Room 24 (North), and Room 28 (West)
				room[1][2][1].setRoomId(23);
				room[1][2][1].setName("\nFloor 1");
				room[1][2][1].setDescription("This room is the main entrance."
						+ "\n     There is a metal shield blocking the front door."
						+ "\n     There are many bodies of people who tried to escape.");

				//Room 24: Connected to Room 21 (East), Room 23 (South), and Room 25 (North)
				room[1][1][1].setRoomId(24);
				room[1][1][1].setName("\nFloor 1");
				room[1][1][1].setDescription("The center of the room has the EMPYREAN’s famous High-Energy Particle machine."
						+ "\n     There are many labs and detectors around it to test how particles react under certain conditions."
						+ "\n     There is a walkway going along the outer part of the room for employees and tour groups to get around the machine and stations.");

				//Room 25: Connected to Room 20 (East), Room 24 (South), and Room 26 (West)
				room[1][0][1].setRoomId(25);
				room[1][0][1].setName("\nFloor 1");
				room[1][0][1].setDescription("The ceiling of this room is much higher than the ceilings of other rooms."
						+ "\n     There is glass below the ceiling."
						+ "\n     There are temperature regulation pipes and bright lights above the glass."
						+ "\n     The Floor is a sidewalk surrounded by dirt."
						+ "\n     The dirt has a wide variety of of small trees and other plants."
						+ "\n     Birds can be heard.");

				//Room 26: Connected to Room 25 (East) and Room 27 (South)
				room[0][0][1].setRoomId(26);
				room[0][0][1].setName("\nFloor 1");
				room[0][0][1].setDescription("The room has many glass enclosures."
						+ "\n     A few of the enclosures have dead or diseased animals."
						+ "\n     Each enclosure has a well recorded binder full of notes stating how the animals reacted to viruses and toxins.");

				//Room 27: Connected to Room 18 (Staircase/Up), Room 26 (North), and Room 28 (South)
				room[0][1][1].setRoomId(27);
				room[0][1][1].setName("\nFloor 1");
				room[0][1][1].setDescription("This room is the bottom of a stairwell."
						+ "\n     There is an exit sign near the door to the South and bloody handprint on the door to the North.");

				//Room 28: Connected to Room 23 (East) and Room 27 (North)
				room[0][2][1].setRoomId(28);
				room[0][2][1].setName("\nFloor 1");
				room[0][2][1].setDescription("There are many desks."
						+ "\n     There are a few meeting rooms."
						+ "\n     This is where the bulk of the business is done."
						+ "\n     Several desks have been knocked over from the panic.");

				//Ground Floor/Basement
				room[0][0][0].setRoomId(29);
				room[0][0][0].setName("\nUnderground");
				room[0][0][0].setDescription("");
				
				room[1][0][0].setRoomId(30);
				room[1][0][0].setName("\nUnderground");
				room[1][0][0].setDescription("There are wire coming in and out of the room to the West and South. "
						+ "\n     The wires are connected to many servers. " 
						+ "\n     The servers appear to be offline for security reasons. " 
						+ "\n     There is also pipe connected to the servers coming from the ceiling.");
				
				room[0][1][0].setRoomId(31);
				room[0][1][0].setName("\nUnderground");
				room[0][1][0].setDescription("There are wires coming in and out of the walls to the North and South. "
						+ "\n     The room edges are lined with old legacy technology. "
						+ "\n     Modems and Routers and HUBs and switches are everywhere. "
						+ "\n     There is a large machine that looks like it tests projectiles and has a bin of old computers to test. " 
						+ "\n     The wall facing away from the machine has bits of technology debris everywhere. ");
				
				room[1][2][0].setRoomId(32);
				room[1][2][0].setName("\nUnderground");
				room[1][2][0].setDescription("The room isn’t as wired as the others. "
						+ "\n     It has new tech that hasn’t been advertised yet lying around. "
						+ "\n     There’s lots of notes and binders labeled “Prototype 257” through “Prototype 302”. " 
						+ "\n     There is a door that says do not enter. ");
				
				room[0][2][0].setRoomId(33);
				room[0][2][0].setName("\nUnderground");
				room[0][2][0].setDescription("There is a single computer in this room on a desk. "
						+ "\n     There is very few wires and all of them go to the computer from the walls. ");
		
	}
	
	public String getRoomInfo(int x, int y, int z)
	{
		return room[x][y][z].toString();
	}
}
	 
	 
class Room{
	public int roomId;
	public String name;
	public String description;
	
	public Room(){
		
	}
	
	public Room(String string) {
		// TODO Auto-generated constructor stub
	}
	public void setRoomId(int roomId){
		this.roomId = roomId;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
	public int getRoomId(){
		return	this.roomId;
	}
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.description;
	}
	@Override
	public String toString() {
		return getName() + ": Room " + getRoomId() + "\n\n" + getDescription();
	}	
}