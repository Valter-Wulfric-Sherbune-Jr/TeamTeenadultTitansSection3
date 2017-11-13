package application;
	
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application 
{
	
	@Override
	public void start(Stage primaryStage) 
	{	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		try 
		{
			
			Parent mainPane = FXMLLoader.load(getClass().getResource("LoadGameGUI.fxml"));
			
			Scene scene = new Scene(mainPane, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Empyrean");
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}