package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Optional;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Path;

public class GameFXLoadGameController {

	@FXML
	private ScrollPane loadGameScrollPane;

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

	public void initialize() throws URISyntaxException{
		model.playMusic("Main Menu.mp3");
		model.setSaveList();
		setListSave();
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
		if(loadGameList.getSelectionModel().getSelectedItem() != null) {
			int saveId = loadGameList.getSelectionModel().getSelectedIndex()+1;

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Deletation Confirmation");
			alert.setContentText("Are you sure you wanna delete this save file?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				File file = new File("./res/Save/Save " +  saveId + ".dat");
				file.delete();

				model.setSaveList();
				setListSave();
				loadGameList.setItems(observList);
			}
		}
	}

	@FXML
	void loadGameLoadButtonOnClick(ActionEvent event) throws IOException, URISyntaxException {
		if(loadGameList.getSelectionModel().getSelectedItem() != null) {
			int saveId = loadGameList.getSelectionModel().getSelectedIndex()+1;
			File file = new File("./res/Loading Folder Choice.txt");
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.println("Load Game");
			writer.println(saveId);
			writer.close();
			System.out.println("Test");
			model.stopMusic();
			System.out.println("Test2");
			
			Parent secondPane = FXMLLoader.load(getClass().getResource("PlayGame.fxml"));
			Scene scene2 = new Scene(secondPane);

			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow()); 
			window.setTitle(model.getSaveList().get(saveId).getGameFolder());
			window.setScene(scene2);
			window.show();
		}

	}

	void setLoadList(ArrayList<SaveData> data) {
		observList.clear();

	}

	void setListSave() {
		observList.clear();

		for(int x = 1; x <= 23; x++) 
		{
			if(model.getSaveList().get(x) != null) 
			{
				SaveData data = model.getSaveList().get(x);
				observList.add(x + "." + data.getGameFolder()
				+ " Time: " + data.getPlayerData().getGameTime()
				+ " Room: " + data.getPlayerData().getCurrentRoom().getRoomFloor());
			}
			else 
			{
				observList.add(x + ". Empty");
			}
		}
	}


}
