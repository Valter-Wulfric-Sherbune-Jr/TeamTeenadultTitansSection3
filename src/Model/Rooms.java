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
			room[0][0][0].setName();
			room[0][0][0].setDescription();
			
			
			
		
		} 
	 }
	
class Room{
	
}	 
	

}
