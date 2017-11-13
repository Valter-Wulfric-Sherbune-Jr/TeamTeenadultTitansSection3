package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class TitleScreenController {

	TitleScreenModel model = new TitleScreenModel();
	Main view = new Main();
	
    @FXML
    private ImageView TitleBackground;

    @FXML
    private ImageView TitleText;

    @FXML
    private Button newGameButton;

    @FXML
    private Button loadGameButton;

    @FXML
    private Button viewScoreButton;

    @FXML
    private Button levelEditorButton;

    @FXML
    private Button exitGameButton;

    @FXML
    void exitGameOnClick(ActionEvent event) {
    	model.exitGame();
    }

    @FXML
    void levelEditorOnClick(ActionEvent event) {
    	model.levelEditor();
    }

    @FXML
    void loadGameOnClick(ActionEvent event) throws IOException {
    	model.loadGame(event);
    }

    @FXML
    void newGameOnClick(ActionEvent event) {
    	model.newGame();
    }

    @FXML
    void viewScoreOnClick(ActionEvent event) {
    	model.viewScore();
    }

}