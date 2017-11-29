package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import GameObject.Monsters;
import GameObject.SaveData;
import Model.GameFXModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class GameFXLoadGameController {

	@FXML
	private Button loadGameBackButton;

	@FXML
	private Button LoadGameDeleteSaveButton;

	@FXML
	private Button loadGameLoadButton;

	@FXML
	private ListView<String> loadGameList;

	private ObservableList<String> observList = FXCollections.observableArrayList();

	GameFXModel model = new GameFXModel();

	public void initialize(){
		model.setGameFolder("Hydra Game File");
		model.setSaveList();
		for(int x = 1; x <= 10; x++) {
			if(model.getSaveList().get(x) != null) {
				System.out.println("Test");
				observList.add(x + ". Name: " + model.getSaveList().get(x).getPlayerData().getPlayerName()
						+ " Time: " + model.getSaveList().get(x).getPlayerData().getGameTime()
						+ " Room: " + model.getSaveList().get(x).getPlayerData().getCurrentRoom().getRoomId());

			}
			else {
				observList.add(x + ". Empty");
			}
		}
		loadGameList.setItems(observList);
	}

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

	void setLoadList(ArrayList<SaveData> data) {
		observList.clear();

	}


}
