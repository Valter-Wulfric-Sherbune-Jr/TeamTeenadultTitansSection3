package Controller;
import java.util.Scanner;

public class Menu {
	//ffuygugygy
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create User ID and save user ID
			Scanner selection = new Scanner(System.in);
			System.out.println("Welcome to-------- ");
			System.out.println("1. New Game");
			System.out.println("2. Load Game");
			System.out.println("3. View Scores");
			System.out.println("4. Help Menu");
			System.out.println("5. Exit Game");
			
			int gameSelection = selection.nextInt();
		
		switch(gameSelection){
		
		case 1: //if selection is 1 a new Game is created
			System.out.println("New Game is created");
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
