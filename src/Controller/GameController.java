package Controller;

import java.util.Scanner;

import Model.GameModel;
import View.GameView;

public class GameController {

	GameModel model = new GameModel();
	GameView view = new GameView();
	private Scanner input = new Scanner(System.in);

	/*Initialize the Game first with the
	 *game state "Main Menu"
	 */
	public void initializeGame() {
		model.setState("Main Menu");
	};

	/*Prints out the choice that the user can
	 *take to the view class depending on the 
	 *state of the game
	 */
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
			break;
		case "Save Conflict":
			output += "A save file in slot "+ model.getSaveData().getSaveId()+ " already exists. Overwrite save data?\n";
			output += "1. Yes\n";
			output += "2. No\n";
			break;
		case "Inventory Menu":
			output += model.getPlayer().getInventoryListString();
			output +=  "\nWhat will you do?\n";
			output +=  "1. Examine Item\n";
			output +=  "2. Drop Item\n";
			output +=  "3. Use Item\n";
			output +=  "4. Equip Item\n";
			output +=  "5. Exit\n";
			break;
		case "Select Item":
			output += "Inventory:\n";
			output += model.getPlayer().getInventoryListString();
			output += "\nSelect an item to ";
			switch(model.getStoredState()) {
			case"Examine Item":
				output += "examine";
				break;
			case"Drop Item":
				output += "drop";
				break;
			case"Use Item":
				output += "use";
				break;
			case"Equip Item":
				output += "equip";
				break;
			}

			output += "? Type \"Exit\" to cancel.";
			break;
		default:
			System.out.println("Error: Not Valid State (Method readuserInput())");
			System.out.println(model.getState());
			System.exit(0);
			break;
		}

		view.println(output);
		output = "";
	}

	/*After the choice for the player is print out
	 *depending on the player's input, it calls the method
	 *that corresponding to that command, otherwise
	 *nothing happen and the game state reminds, which
	 *will prompt the user choice again and input
	 */
	public void readUserInput() {
		printChoice();

		view.print("Input:\n> ");
		String userInput = input.nextLine();

		switch(model.getState()) {
		case "Main Menu":
			mainMenu(userInput);
			break;
		case "Action Menu":
			actionMenu(userInput);
			break;
		case "Save Menu":
			saveGame(userInput);
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
		case "Save Conflict":
			saveConflict(userInput);
			break;
		case "Inventory Menu":
			inventoryMenu(userInput);
			break;
		case "Select Item":
			checkUserItem(userInput);
			break;
		default:
			System.out.println("Error: Not Valid State (Method readuserInput())");
			System.out.println(model.getState());
			System.exit(0);
		}
	}	


	private void checkUserItem(String userInput) {
		if(userInput.equalsIgnoreCase("exit")) {
			if(model.getStoredState().equalsIgnoreCase("Puzzle")) {
				model.setState("Puzzle Menu");
			}else{
				model.setState("Action Menu");
			}
		}
		else if(model.checkValidItem(userInput)) {
			for(int x = 0; x < model.getPlayer().getInventoryList().size(); x++) {
				if(model.getPlayer().getInventoryList().get(x).getItemName().equals(userInput)) {
					model.getPlayer().setSelectedItem(model.getPlayer().getInventoryList().get(x));
				}
			}
			
			switch(model.getStoredState()) {
			case"Examine Item":
				examineItem(userInput);
				break;
			case"Drop Item":
				dropItem(userInput);
				break;
			case"Use Item":
				useItem(userInput);
				break;
			case"Equip Item":
				equipItem(userInput);
				break;	
			}
		}else {
			System.out.println("Invalid Input");
		}		
	}

	private void equipItem(String userInput) {
		if(model.getPlayer().getSelectedItem().equals(model.getPlayer().getWeapon())) {
			view.println("You're already holding that weapon!");
			model.setState("Action Menu");
		}else {
			model.getPlayer().equipWeapon(model.getPlayer().getSelectedItem());
			view.println("You equipped the " + model.getPlayer().getWeapon().getItemName());
			model.setState("Action Menu");
		}

	}

	private void useItem(String userInput) {
		if(model.getPlayer().getSelectedItem().getItemType().equalsIgnoreCase("healing")) {
			if(!(model.getPlayer().getPlayerCurrentHealth() == model.getPlayer().getPlayerMaxHealth())) {
				model.getPlayer().healHealth(model.getPlayer().getSelectedItem().getItemActionValue());
				view.println("You recover " + model.getPlayer().getSelectedItem().getItemActionValue() + " health.");
				model.getPlayer().removeItemFromInventory(model.getPlayer().getSelectedItem());
				model.setState("Action Menu");
			}else {
				System.out.println("You are already at full health.");
				model.setState("Action Menu");
			}
		}else {
			view.println("Nothing Interesting Happened");
			model.setState("Action Menu");
		}	
	}

	private void dropItem(String userInput) {
		model.getPlayer().getCurrentRoom().addRoomItem(model.getPlayer().getSelectedItem());
		model.getPlayer().removeItemFromInventory(model.getPlayer().getSelectedItem());
		view.println("You drop the " + model.getPlayer().getSelectedItem().getItemName());
		model.setState("Action Menu");

	}

	private void examineItem(String userInput) {
		view.println(model.getPlayer().getSelectedItem().toString());
		if(model.getPlayer().getSelectedItem().getItemType().equalsIgnoreCase("weapon")) {
			view.println("\nAmmo x " + model.getPlayer().getWeaponAmmo(model.getPlayer().getSelectedItem()));
		}
		model.setState("Action Menu");
	}

	/*Main Menu that set the state depending on the
	 *player input
	 */
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

	/*Action Menu that set the state depending on the
	 *player input
	 */
	public void actionMenu(String userInput) {
		switch(userInput.toLowerCase()) {
		case "move" : case "1":
			model.setState("Move Player");
			break;
		case "examine room" : case "2":
			model.getPlayer().getCurrentRoom().toString();
			model.setState("Action Menu");
			break;
		case "check inventory" : case "3":
			model.setState("Inventory Menu");
			break;
		case "save game" : case "4":
			model.setState("Save Menu");
			model.setSaveList();
			break;
		case "quit game" : case "5":
			model.setState("Main Menu");
			break;
		default:
			view.println("Invalid Input");
			break;
		}
	}

	/*Action Menu that set the state depending on the
	 *player input
	 */
	public void inventoryMenu(String userInput) {
		switch(userInput.toLowerCase()) {
		case "examine item": case "1":
			model.setState("Select Item");
			model.setStoredState("Examine Item");
			break;
		case "drop item": case "2":
			model.setState("Select Item");
			model.setStoredState("Drop Item");
			break;
		case "use item": case "3":
			model.setState("Select Item");
			model.setStoredState("Use Item");
			break;
		case "equip item": case "4":
			model.setState("Select Item");
			model.setStoredState("Equip Item");
			break;
		case "exit": case "5":
			model.setState("Action Menu");
			break;
		default:
			System.out.println("\nInvalid Input.");
			break;
		}
	}

	/*Check the userinput, and load the gamefolder and all
	 *asset in the game folder to the game
	 *Need Input: Game Folder
	 *Related State Affliction: New Game(Originated from), PlayerCreation
	 */
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

	/*Tell the model to create a player class with the player name
	 *then set the state to action menu to start the game
	 *Need Input: Player Name
	 *Related State Affliction: New Game(Method: selectGameFolder), ActionMenu
	 */
	public void playerCreation(String userInput) {
		model.makeNewPlayer(userInput);
		view.println(model.getPlayer().getCurrentRoom().toString());
		model.setState("Action Menu");
	}

	/*Move the player from one room to another, depending
	 *on the next room, it checks wether it has a monster
	 *or a puzzle. Then set the state accordingly
	 *Need Input: Direction
	 *Related State Affliction: Action Menu(Originated from), Combat Menu, Puzzle Menu
	 */
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

	/*Save the game of the player
	 *Need Input: Exit or Number of the Save Slot
	 *Related State Affliction: Action Menu(Originated from), Save Conflict
	 */
	private void saveGame(String userInput) {
		if(userInput.trim().equalsIgnoreCase("Exit")) {
			model.setState("Action Menu");
		}
		else if(Integer.parseInt(userInput) >= 1 && Integer.parseInt(userInput) <= 10) {
			int userInputInt = Integer.parseInt(userInput);
			model.setSaveData(userInputInt);
			if(model.getSaveList().get(userInputInt) != null) {
				model.setState("Save Conflict");
			}
			else {
				view.println("Save Successful");
				model.saveGameData(model.getSaveData());
				model.setState("Action Menu");
			}
		}else {
			view.print("Invalid command");
		}

	}

	/*If there is an already existing save file in the save slot
	 *this method is called asking for the user if they want to override
	 *Need Input: Yes or No
	 *Related State Affliction: Action Menu(Method: saveGame), Action Menu
	 */
	private void saveConflict(String userInput) {
		switch(userInput.toLowerCase()) {
		case "yes" : case "1":
			view.println("Save Successful");
			model.saveGameData(model.getSaveData());
			model.setState("Action Menu");
			break;
		case "no" : case "2":
			view.println("Overwrite cancelled");
			model.setState("Action Menu");
			break;
		default:
			System.out.println("\nInvalid command");
			break;
		}
	}


}
