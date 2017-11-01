package hieu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextModel extends Application
{
	public static void main(String[] args)
	{
		launch(args);
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
}