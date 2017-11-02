package Controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Model.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * @author mansoor
 * This class is the game creator and will be invoked from the Main menu selection.
 * This class creates, saves, loads, view scores, and exit game.
 *
 */

public class Game extends Application{
	static int menuState;
	public static Player player;
	public static String begin;
	public static Scanner input = new Scanner(System.in);
	final static int[] StartingCoordinate = {0,0,4};

	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage primaryStage)
	{
		TextArea consoleTA  = new TextArea("Welcome to the Mock-up Console!\n"
				+ "\n -- MENU --"
				+ "\n1. New Game"
				+ "\n2. Load Game"
				+ "\n3. Level Editor"
				+ "\n4. Exit Game");
		TextField inputTF   = new TextField();
		
		VBox rootVBox     = new VBox();
		
		rootVBox.getChildren().addAll(consoleTA, inputTF);
		rootVBox.setPadding(new Insets(10));
		
		primaryStage.setScene(new Scene(rootVBox));
		primaryStage.setTitle("Console Mock-up");
		
		consoleTA.setEditable(false);
		consoleTA.setWrapText(true);
		consoleTA.setPrefWidth(1000);
		consoleTA.setPrefHeight(500);
		consoleTA.setFont(Font.font("Monospaced"));
		inputTF.setPrefWidth(1000);
		inputTF.setPrefHeight(20);
		inputTF.setFont(Font.font("Monospaced"));
		inputTF.requestFocus();
		
		
		inputTF.setOnAction(e -> {
			consoleTA.appendText("\n\n>" + inputTF.getText().toUpperCase());
			
			/**
			 * @author Jason
			 * The menuState variable keeps track of where the user is in the menus. The states are as follows:
			 *  menuState = 0: Default state, this is the Main Menu
			 *  menuState = 1: File creation state, assigned when selecting "New Game"
			 *  menuState = 2: Load file state, assigned when selecting "Load Game"
			 *  menuState = 3: Level editor state, assigned when selecting "Level Editor
			 *  menuState = 4: Level editor v2 state, assigned when selecting "Create Game Folder" in the Level editor state.
			 *  
			 *  !!!!! There's probably a better way to implement this, but I dunno how. pls help. !!!!!
			 */
			
			switch(menuState)
			{
			case 0:
				switch(inputTF.getText().toUpperCase().trim())
				{
				case "1": case "NEW GAME": case "NEW":
					consoleTA.appendText("\nNew game? I'll try.");
					menuState = 1;
					consoleTA.appendText("\n\nYou're in the New Game menu now.");
					break;
				case "2": case "LOAD GAME": case "LOAD":
					consoleTA.appendText("\nLoad game? I dunno if I can.");
					menuState = 2;
					consoleTA.appendText("\n\nYou're in the Load Game menu now.");
					break;
				case "3": case "LEVEL EDITOR": case "EDITOR":
					consoleTA.appendText("\nLevel editor? Why wasn't I told of this?");
					menuState = 3;
					consoleTA.appendText("\n\nYou're in the Level Editor menu now.");
					break;
				case "4": case "EXIT GAME": case "EXIT":
					primaryStage.close();
					break;
				case "HELP":
					consoleTA.appendText("\n -- MENU --"
							+ "\n1. New Game"
							+ "\n2. Load Game"
							+ "\n3. Level Editor"
							+ "\n4. Exit Game");
					break;
				default:
					consoleTA.appendText("\nI don't understand what that means, right now.");
					break;
				}
				break;
			case 1:
				switch(inputTF.getText().toUpperCase().trim())
				{
				case "1":
					consoleTA.appendText("\nI don't know what's going on.");
					break;
				case "2": case "BACK": case "BACK TO MAIN MENU":
					consoleTA.appendText("\nBack? Going back to the previous menu...");
					menuState = 0;
					consoleTA.appendText("\n\nYou're in the Main Menu now.");
					break;
				case "HELP":
					consoleTA.appendText("\n -- NEW GAME MENU --"
							+ "\n1. "
							+ "\n2. Back to Main Menu");
					break;
				default:
					consoleTA.appendText("\nI don't understand what that means, right now.");
					break;
				}
				break;
			case 2:
				switch(inputTF.getText().toUpperCase().trim())
				{
				case "1":
					consoleTA.appendText("\nI don't know what's going on.");
					break;
				case "2": case "BACK": case "BACK TO MAIN MENU":
					consoleTA.appendText("\nBack? Going back to the previous menu...");
					menuState = 0;
					consoleTA.appendText("\n\nYou're in the Main Menu now.");
					break;
				case "HELP":
					consoleTA.appendText("\n -- LOAD GAME MENU --"
							+ "\n1. "
							+ "\n2. Back to Main Menu");
					break;
				default:
					consoleTA.appendText("\nI don't understand what that means, right now.");
					break;
				}
				break;
			case 3:
				switch(inputTF.getText().toUpperCase().trim())
				{
				case "1": case "CREATE GAME FOLDER": case "CREATE":
					consoleTA.appendText("\nCreate Game Folder? I dunno what that means but ok");
					menuState = 4;
					consoleTA.appendText("\n\nYou're in the Level Editor V2 menu now.");
					break;
				case "2":
					consoleTA.appendText("\nI don't know what's going on.");
					break;
				case "3": case "BACK": case "BACK TO MAIN MENU":
					consoleTA.appendText("\nBack? Going back to the previous menu...");
					menuState = 0;
					consoleTA.appendText("\n\nYou're in the Main Menu now.");
					break;
				case "HELP":
					consoleTA.appendText("\n -- LEVEL EDITOR MENU --"
							+ "\n1. Create Game Folder"
							+ "\n2. Edit Game Folder"
							+ "\n3. Back to Main Menu");
					break;
				default:
					consoleTA.appendText("\nI don't understand what that means, right now.");
					break;
				}
				break;
			case 4:
				switch(inputTF.getText().toUpperCase().trim())
				{
				case "1":
					consoleTA.appendText("\nLoad Subfolder? ?????");
					break;
				case "2":
					consoleTA.appendText("\nI don't know what's going on.");
					break;
				case "3": case "BACK": case "BACK TO LEVEL EDITOR":
					consoleTA.appendText("\nBack? Going back to the previous menu...");
					menuState = 3;
					consoleTA.appendText("\n\nYou're in the Level Editor menu now.");
					break;
				case "HELP":
					consoleTA.appendText("\n -- LEVEL EDITOR V2 MENU --"
							+ "\n1. Load Subfolder"
							+ "\n2. ???"
							+ "\n3. Back to Level Editor");
					break;
				default:
					consoleTA.appendText("\nI don't understand what that means, right now.");
					break;
				}
				break;
			}
			
			inputTF.clear();
		});
		
		
		
		primaryStage.show();
	}
	
	public void create(Player p) throws IOException{
		{
			//Scanner input = new Scanner(System.in);
			System.out.println("Please enter username: ");
			String user = input.next();
			p = new Player(0, 'x',"",null);
			Game.player = p;
			Player.setUsername(user);
			String username = p.getUsername();		
			File file = new File(username + ".dat");
			if(file.exists())
			{
				System.out.println("This name already exists, please use a different name!");
				create(p);
			} else {
				System.out.println("Profile being created, please wait....");
				PrintWriter writer = new PrintWriter(new FileWriter(file));
				writer.println(username);
				writer.close();
				//Sleep.Delay(3000);
				System.out.println();
				System.out.println("Alright " + p.getUsername() + ", I need you to get on this\n" +
						"train and get as much loot as possible! Good Luck!\n");

			}
		}

	}
	public static void play()
	{
		boolean done = false;

		while(!done)
		{

		}
	}

}