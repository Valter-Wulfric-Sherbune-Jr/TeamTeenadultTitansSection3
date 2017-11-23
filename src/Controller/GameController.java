package Controller;

import java.util.Scanner;

import Model.GameModel;
import View.GameView;

public class GameController {

	GameModel model = new GameModel();
	GameView view = new GameView();
	private Scanner input = new Scanner(System.in);

	public void initializeGame() {
		model.setState("Main Menu");
	};

	public void readUserInput() {
		printChoice();

		view.print("Input:\n> ");
		String userInput = input.nextLine();

		switch(model.getState()) {
		case "Main Menu":
			mainMenu(userInput);
			break;
		case "New Game":
			selectGameFolder(userInput);
			break;
		case "Player Creation":
			playerCreation(userInput);
			break;
		case "Move Player":
			movePlayer(userInput);
			break;
		case "Action Menu":
			actionMenu(userInput);
			break;
		default:
			System.out.println("Error: Not Valid State (Method readuserInput())");
			System.out.println(model.getState());
			System.exit(0);
		}	
	}

	public void printChoice() {
		String output = "";

		switch(model.getState()) {
		case "New Game":
			model.setGameFolderList();
			for(String gameFolder : model.getGameFolderList()) {
				output += " -" +gameFolder + "\n";
			}
			output += "Choose the game that you want to play.\n";
			break;
		case "Main Menu":
			output += "==================================================\n";
			output += "|| || ||       EMPYREAN - Main Menu       || || ||\n";
			output += "==================================================\n";
			output += "                  1. New Game\n";
			output += "                  2. Load Game\n";
			output += "                  3. View Score\n";
			output += "                  4. Level Editor\n";
			output += "                  5. Exit Game\n";
			break;
		case "Player Creation":
			output += "Please Choose your name:";
			break;
		case "Action Menu":
			output += ">>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<\n";
			output += ">>>>>       Action - What will you do?       <<<<<\n";
			output += ">>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<\n";
			output += "                1. Move\n";
			output += "                2. Examine Room\n";
			output += "                3. Check Inventory\n";
			output += "                4. Save Game\n";
			output += "                5. Quit Game\n";
			break;
		case "Move Player":
			output += "\nWhich direction do you want to move? Type \"Exit\" to cancel\n";
			output += "Exits: " + model.getPlayer().getCurrentRoom().getRoomConnection()+"\n";
			break;
		case "Save Menu":
			output += "\nSave Data:\n";
	
			for(int x = 1; x <= 10;x++) {
				if(model.getSaveList().get(x) != null) {
					output += (x + ". Name: " + model.getSaveList().get(x).getPlayerData().getPlayerName()
							+ "; Time: " + model.getSaveList().get(x).getPlayerData().getGameTime()
							+ "; Room: " + model.getSaveList().get(x).getPlayerData().getCurrentRoom().getRoomId()+"\n");
				}
				else {
					output +=(x + ". Empty" +"\n");
				}
			}
			
			output +="\nSelect a slot to save, or type \"Exit\" to cancel";
			
			break;
		}

		view.println(output);
		output = "";
	}

	public void selectGameFolder(String userInput) {
		if(model.checkValidGameFolder(userInput)) {
			model.setGameFolder(userInput);
			model.loadGameFolder("Item");
			model.loadGameFolder("Monster");
			model.loadGameFolder("Puzzle");
			model.loadGameFolder("Room");
			model.setState("Player Creation");


		}else {
			view.println("Invalid Input");
		}
	}

	public void playerCreation(String userInput) {
		model.makeNewPlayer(userInput);
		view.println(model.getPlayer().getCurrentRoom().toString());
		model.setState("Action Menu");
	}
	public void mainMenu(String userInput) {
		switch(userInput.toLowerCase()) {
		case "new game" : case "1":
			model.setState("New Game");
			break;
		case "load game" : case "2":
			//model.setState("Load Menu");
			view.println("Not Implemented Yet");
			break;
		case "view score" : case "3":
			view.println("Not Implemented Yet");
			break;
		case "level editor" : case "4":
			view.println("Not Implemented Yet");
			break;
		case "exit game" : case "5":
			view.println("Exiting Game");
			System.exit(0);
			break;
		default:
			view.println("Invalid Input");
			break;
		}
	}
	public void actionMenu(String userInput) {
		switch(userInput.toLowerCase()) {
		case "move" : case "1":
			model.setState("Move Player");
			break;
		case "examine room" : case "2":
			view.println("Not Implemented Yet");
			break;
		case "check inventory" : case "3":
			view.println("Not Implemented Yet");
			break;
		case "save game" : case "4":
			model.setState("Save Menu");
			break;
		case "quit game" : case "5":
			model.setState("Main Menu");
			break;
		default:
			view.println("Invalid Input");
			break;
		}
	}
	public void movePlayer(String userInput) {
		if(userInput.equals("Exit")) {
			model.setState("Action Menu");
		}else if(model.checkValidDirection(userInput)) {
			if(model.getNextRoom(userInput).getRoomAccessList().get(model.getPlayer().getCurrentRoom().getRoomId()) != null) {
//				Puzzles puzzle = puzzleList.get(nextRoom.getRoomAccessList().get(player.getCurrentRoom().getRoomId()));
//				if(puzzleMenu(puzzle)) {
//					if(player.getCurrentRoom().getRoomMonster().isEmpty()) {
//						player.setCurrentRoom(nextRoom);
//						System.out.println(player.getCurrentRoom().toString());			
//					}
//				}
			}
			else if(model.getPlayer().getCurrentRoom().getRoomMonster().isEmpty()) {
				model.getPlayer().setCurrentRoom(model.getNextRoom(userInput));
				view.println(model.getPlayer().getCurrentRoom().toString());	
				model.setState("Action Menu");		
			}
			else{
				model.getPlayer().setCurrentRoom(model.getNextRoom(userInput));
				model.setState("Combat Menu");
			}
		}
		else {
			view.println("You can't go that way!");
		}
	}

}
