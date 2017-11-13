package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TitleScreenModel 
{

	Stage stage; 
	Parent root;

	public void exitGame()
	{
		System.exit(0);
	}

	public void changeToSaveScreen(ActionEvent event) throws IOException
	{
		Parent secondPane = FXMLLoader.load(getClass().getResource("SaveDataScreen.fxml"));
		Scene scene2 = new Scene(secondPane);

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
		window.setScene(scene2);
		window.show();
	}

	public void levelEditor() {
		// TODO Auto-generated method stub

	}

	public void loadGame(ActionEvent event) throws IOException{     
//		stage=(Stage) event.getSource().getScene().getWindow();
//		root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
//
//		//create a new scene with root and set the stage
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//
//
//		BorderPane secondPane = FXMLLoader.load(getClass().getResource("LoadGameGUI.fxml"));
//		Scene scene2 = new Scene(secondPane);
//		System.out.print("test");
//
//		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
//		window.setScene(scene2);
//		window.show();

	}

	public void newGame() {
		// TODO Auto-generated method stub

	}

	public void viewScore() {
		// TODO Auto-generated method stub

	}

}
