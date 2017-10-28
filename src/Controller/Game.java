package Controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import Model.Player;
import Model.RoomManger;

/**
 * @author mansoor
 * This class is the game creator and will be invoked from the Main menu selection.
 * This class creates, saves, loads, view scores, and exit game.
 *
 */

public class Game {
	
	public static Scanner input = new Scanner(System.in);
	final static int[] StartingCoordinate = {0,0,4};
	
	public static void main(String[] args) {
		
		//Create User ID and save user ID
			Scanner selection = new Scanner(System.in);
			System.out.println("Welcome to Hydra Game");
			System.out.println("1. New Game");
			System.out.println("2. Load Game");
			System.out.println("3. View Scores");
			System.out.println("4. Help Menu");
			System.out.println("5. Exit Game");
			
			int gameSelection = selection.nextInt();
		
		switch(gameSelection){
		
		case 1: //if selection is 1 a new Game is created
			System.out.println("New Game is created");
			RoomManger test = new RoomManger();
			test.makeRoom("Hydra Game File");
			test.loadRoomID(1);
			break;
		case 2: //if selection 2 is selected loads previous saved data
			System.out.println("Load Game");
			break;
		case 3: //if selection is 3 all previous scores is displayed
			System.out.println("view score");
			break;
		case 4: // if selection is selected help menu is displayed
			System.out.println("help menu");
			break;
		case 5: // if selected the game is terminated
			System.out.println("Game exit");
			break;
		default: 
			System.out.println("Please Select an Option");
			break;
		
		}

	}

}
