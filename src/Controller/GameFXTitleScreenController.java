package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.GameFXModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameFXTitleScreenController {
	
    @FXML
    private ImageView TitleBackground;

    @FXML
    private ImageView TitleText;

    @FXML
    private Button newGameButton;
    
    @FXML
    private Button levelEditorButton;

    @FXML
    private Button loadGameButton;

    @FXML
    private Button exitGameButton;

    GameFXModel model = new GameFXModel();
    
    public void initialize() throws URISyntaxException {
    	model.playMusic("Main Menu.mp3");
    	
    	newGameButton.setStyle("-fx-focus-color: grey;");
    	levelEditorButton.setStyle("-fx-focus-color: grey;");
    	loadGameButton.setStyle("-fx-focus-color: grey;");
    	exitGameButton.setStyle("-fx-focus-color: grey;");
    }
    
    @FXML
    void exitGameOnClick(ActionEvent event) {
    	System.exit(0);
    }
    
    @FXML
    void levelEditorOnClick(ActionEvent event) throws IOException, URISyntaxException {
    	model.stopMusic();
    	Parent secondPane = FXMLLoader.load(getClass().getResource("LevelEditor.fxml"));
    	Scene scene2 = new Scene(secondPane);
    	
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
    	window.setScene(scene2);
    	window.show();
    }

    @FXML
    void loadGameOnClick(ActionEvent event) throws IOException {
    	

    	Parent secondPane = FXMLLoader.load(getClass().getResource("LoadGame.fxml"));
    	Scene scene3 = new Scene(secondPane);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
    	// The Java 8 way to get the response value (with lambda expression).
    	window.setScene(scene3);
    	window.show();

    }

    @FXML
    void newGameOnClick(ActionEvent event) throws IOException, URISyntaxException {
    	model.setGameFolderList();
    	model.getGameFolderList();
    	List<String> choices = new ArrayList<>();

    	for(String gameFolder: model.getGameFolderList()) {
    		choices.add(gameFolder);
    	}

    	ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
    	dialog.setTitle("Game Folder Selection");
    	dialog.setHeaderText("What game folder do you want to play?");
    	dialog.setContentText("Select a game folder:");

    	
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		
    		File file = new File("./res/Loading Folder Choice.txt");
    		PrintWriter writer = new PrintWriter(file, "UTF-8");
    		writer.println("New Game");
    		writer.println(result.get());
    		writer.close();
    		
    	    model.stopMusic();
    	    Parent secondPane = FXMLLoader.load(getClass().getResource("PlayGame.fxml"));
        	Scene scene2 = new Scene(secondPane);
        	
        	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow()); 
        	window.setTitle(result.get());
        	window.setScene(scene2);
        	window.show();
    	}
    	
    	
    	
    }

}