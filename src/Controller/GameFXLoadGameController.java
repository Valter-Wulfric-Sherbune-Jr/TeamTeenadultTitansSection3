package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameFXLoadGameController {

    @FXML
    private Button loadGameBackButton;

    @FXML
    private Button LoadGameDeleteSaveButton;

    @FXML
    private Button loadGameLoadButton;

    @FXML
    private Button loadButton1;

    @FXML
    private Label loadGameLabel1;

    @FXML
    private Label loadGameEmptyLabel1;

    @FXML
    private Label playerNameLabel1;

    @FXML
    private Label roomNameLabel1;

    @FXML
    private Label timeDurationLabel1;

    @FXML
    private Button loadButton2;

    @FXML
    private Label loadGameLabel2;

    @FXML
    private Label loadGameEmptyLabel2;

    @FXML
    private Label playerNameLabel2;

    @FXML
    private Label roomNameLabel2;

    @FXML
    private Label timeDurationLabel2;

    @FXML
    private Button loadButton3;

    @FXML
    private Label loadGameLabel3;

    @FXML
    private Label loadGameEmptyLabel3;

    @FXML
    private Label playerNameLabel3;

    @FXML
    private Label roomNameLabel3;

    @FXML
    private Label timeDurationLabel3;

    @FXML
    private Button loadButton4;

    @FXML
    private Label loadGameLabel4;

    @FXML
    private Label loadGameEmptyLabel4;

    @FXML
    private Label playerNameLabel4;

    @FXML
    private Label roomNameLabel4;

    @FXML
    private Label timeDurationLabel4;

    @FXML
    private Button loadButton5;

    @FXML
    private Label loadGameLabel5;

    @FXML
    private Label loadGameEmptyLabel5;

    @FXML
    private Label playerNameLabel5;

    @FXML
    private Label roomNameLabel5;

    @FXML
    private Label timeDurationLabel5;

    @FXML
    private Button loadButton6;

    @FXML
    private Label loadGameLabel6;

    @FXML
    private Label loadGameEmptyLabel6;

    @FXML
    private Label playerNameLabel6;

    @FXML
    private Label roomNameLabel6;

    @FXML
    private Label timeDurationLabel6;

    @FXML
    private Button loadButton7;

    @FXML
    private Label loadGameLabel7;

    @FXML
    private Label loadGameEmptyLabel7;

    @FXML
    private Label playerNameLabel7;

    @FXML
    private Label roomNameLabel7;

    @FXML
    private Label timeDurationLabel7;

    @FXML
    private Button loadButton8;

    @FXML
    private Label loadGameLabel8;

    @FXML
    private Label loadGameEmptyLabel8;

    @FXML
    private Label playerNameLabel8;

    @FXML
    private Label roomNameLabel8;

    @FXML
    private Label timeDurationLabel8;

    @FXML
    private Button loadButton9;

    @FXML
    private Label loadGameLabel9;

    @FXML
    private Label loadGameEmptyLabel9;

    @FXML
    private Label playerNameLabel9;

    @FXML
    private Label roomNameLabel9;

    @FXML
    private Label timeDurationLabel9;

    @FXML
    private Button loadButton10;

    @FXML
    private Label loadGameLabel10;

    @FXML
    private Label loadGameEmptyLabel10;

    @FXML
    private Label playerNameLabel10;

    @FXML
    private Label roomNameLabel10;

    @FXML
    private Label timeDurationLabel10;

    @FXML
    void loadGameBackButtonOnClick(ActionEvent event) throws IOException {
    	Parent secondPane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
    	Scene scene = new Scene(secondPane);
    	
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
    	window.setScene(scene);
    	window.show();
    }

    @FXML
    void loadGameDeleteSaveButtonOnClick(ActionEvent event) {

    }

    @FXML
    void loadGameLoadButtonOnClick(ActionEvent event) {

    }

    @FXML
    void saveSlotOnClick(ActionEvent event) {

    }

}
