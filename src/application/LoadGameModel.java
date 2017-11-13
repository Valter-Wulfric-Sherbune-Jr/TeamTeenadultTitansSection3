package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadGameModel 
{
	
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
	
}
