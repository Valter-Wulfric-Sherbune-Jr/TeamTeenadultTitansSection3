package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button loadGameButton;

    @FXML
    private Button exitGameButton;

    @FXML
    void exitGameOnClick(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void loadGameOnClick(ActionEvent event) throws IOException {
    	Parent secondPane = FXMLLoader.load(getClass().getResource("LoadGame.fxml"));
    	Scene scene3 = new Scene(secondPane);
    	
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
    	window.setScene(scene3);
    	window.show();

    }

    @FXML
    void newGameOnClick(ActionEvent event) throws IOException {
    	Parent secondPane = FXMLLoader.load(getClass().getResource("PlayGame.fxml"));
    	Scene scene2 = new Scene(secondPane);
    	
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
    	window.setScene(scene2);
    	window.show();
    }

}