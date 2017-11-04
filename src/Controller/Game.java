package Controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Model.GameManager;
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

public class Game{
	static int menuState;
	public static String begin;
	public static Scanner input = new Scanner(System.in);
	final static int[] StartingCoordinate = {0,0,4};

	public static void main(String[] args)
	{
		
		GameManager game = new GameManager();
		game.newGame();
		
		//launch(args);
	}
	
	
	public void start(Stage primaryStage)
	{
		TextArea consoleTA  = new TextArea("Welcome to the Mock-up Console!");
		TextArea entryTA    = new TextArea();
		TextField inputTF   = new TextField();
		
		VBox rootVBox     = new VBox();
		
		rootVBox.getChildren().addAll(consoleTA, entryTA, inputTF);
		rootVBox.setPadding(new Insets(10));
		
		primaryStage.setScene(new Scene(rootVBox));
		primaryStage.setTitle("Console Mock-up");
		
		consoleTA.setEditable(false);
		consoleTA.setPrefWidth(1000);
		consoleTA.setPrefHeight(500);
		consoleTA.setFont(Font.font("Monospaced"));
		entryTA.setEditable(false);
		entryTA.setPrefWidth(1000);
		entryTA.setPrefHeight(50);
		entryTA.setFont(Font.font("Monospaced"));
		inputTF.setPrefWidth(1000);
		inputTF.setPrefHeight(20);
		inputTF.setFont(Font.font("Monospaced"));
		inputTF.requestFocus();
		
		inputTF.setOnAction(e -> {
			if(inputTF.getText().length() >= 90)
			{
				entryTA.appendText("\nThat command is too long!");
				inputTF.clear();
			}
			else
			{
				entryTA.appendText("\n>" + inputTF.getText().toUpperCase());
				consoleTA.appendText("\n\nYou shout \"" + inputTF.getText().toUpperCase() + "\" out into the void.");
				consoleTA.appendText("\nNothing interesting happens, and the effort is wasted.");
				inputTF.clear();
			}
		});
		
		primaryStage.show();
	}
	
	
	public static void play()
	{
		boolean done = false;

		while(!done)
		{

		}
	}



}