package Controller;
	
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import Model.GameFXModel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class GameFXMainRunner extends Application 
{
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Game Maker");
        stage.show();
        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        
    }

	
	
	
}