package Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GameFXLevelEditorController {

	@FXML
	private Button levelEditorBackButton;

	@FXML
	private Button levelEditorSaveFile;

	@FXML
	private Button levelEditorLoadFile;

	@FXML
	private Button levelEditorNewFile;

	@FXML
	private Button levelEditorGetPreset;

	@FXML
	private TextField levelEditorFileNameTextArea;

	@FXML
	private TextArea levelEditorHelpTextArea;

	@FXML
	private TextArea levelEditorConsoleTextArea;

	@FXML
	private RadioButton levelEditorCharacterRadioButton;

	@FXML
	private RadioButton levelEditorItemRadioButton;

	@FXML
	private RadioButton levelEditorMonsterRadioButton;

	@FXML
	private RadioButton levelEditorPictureRadioButton;

	@FXML
	private RadioButton levelEditorPuzzleRadioButton;

	@FXML
	private RadioButton levelEditorRoomRadioButton;

	@FXML
	private TabPane levelEditorTabPane;

	@FXML
	private Tab levelEditorSlot1;

	@FXML
	private TextArea levelEditorSlot1TextArea;

	@FXML
	private Tab levelEditorSlot2;

	@FXML
	private TextArea levelEditorSlot2TextArea;

	@FXML
	private Tab levelEditorSlot3;

	@FXML
	private TextArea levelEditorSlot3TextArea;

	@FXML
	private Tab levelEditorSlot4;

	@FXML
	private TextArea levelEditorSlot4TextArea;

	@FXML
	private Tab levelEditorSlot5;

	@FXML
	private TextArea levelEditorSlot5TextArea;

	@FXML
	private Tab levelEditorSlot6;

	@FXML
	private TextArea levelEditorSlot6TextArea;

	@FXML
	private Tab levelEditorSlot7;

	@FXML
	private TextArea levelEditorSlot7TextArea;

	@FXML
	private Tab levelEditorSlot8;

	@FXML
	private TextArea levelEditorSlot8TextArea;

	@FXML
	private Tab levelEditorSlot9;

	@FXML
	private TextArea levelEditorSlot9TextArea;

	@FXML
	private Tab levelEditorSlot10;

	@FXML
	private TextArea levelEditorSlot10TextArea;

	String radioSelection = "";

	tabSelection[] tabSlot = new tabSelection[11];

	ToggleGroup radioToggleGroup = new ToggleGroup();

	public void initialize(){

		tabSlot[0] = new tabSelection(levelEditorSlot1,levelEditorSlot1TextArea);		
		tabSlot[1] = new tabSelection(levelEditorSlot2,levelEditorSlot2TextArea);		
		tabSlot[2] = new tabSelection(levelEditorSlot3,levelEditorSlot3TextArea);		
		tabSlot[3] = new tabSelection(levelEditorSlot4,levelEditorSlot4TextArea);		
		tabSlot[4] = new tabSelection(levelEditorSlot5,levelEditorSlot5TextArea);		
		tabSlot[5] = new tabSelection(levelEditorSlot6,levelEditorSlot6TextArea);		
		tabSlot[6] = new tabSelection(levelEditorSlot7,levelEditorSlot7TextArea);		
		tabSlot[7] = new tabSelection(levelEditorSlot8,levelEditorSlot8TextArea);		
		tabSlot[8] = new tabSelection(levelEditorSlot9,levelEditorSlot9TextArea);		
		tabSlot[9] = new tabSelection(levelEditorSlot10,levelEditorSlot10TextArea);

		levelEditorCharacterRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorItemRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorMonsterRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorPictureRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorPuzzleRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorRoomRadioButton.setToggleGroup(radioToggleGroup);

	}

	@FXML
	void levelEditorBackButtonEvent(ActionEvent event) throws IOException {
		Parent secondPane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
		Scene scene = new Scene(secondPane);

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
		window.setScene(scene);
		window.show();
	}

	@FXML
	void levelEditorChooseGameFolderEvent(ActionEvent event) {

	}

	@FXML
	void levelEditorCreateGameFolderEvent(ActionEvent event) {

	}

	@FXML
	void levelEditorGetPresetEvent(ActionEvent event) {
		if(radioSelection.equalsIgnoreCase("")) {
			levelEditorConsoleTextArea.setText("Please Select a "+"\n"+"Category of Files First");
		}else {
			int x = levelEditorTabPane.getSelectionModel().getSelectedIndex();
			System.out.println(x);
			String output = "";
			switch(radioSelection) {
			case "Character":
				output += "Room Floor:";
				output += "\nnull\n";
				output += "Room ID:";
				output += "\nnull\n";
				output += "Room Description:";
				output += "\nnull\n";
				output += "Room Connection:";
				output += "\nnull\n";
				output += "Room Access:";
				output += "\nnull\n";
				output += "Room Item:";
				output += "\nnull\n";
				output += "Room Monster:";
				output += "\nnull\n";
				output += "Room Picture:";
				output += "\nnull\n";
				output += "Room Floor:";
				output += "\nnull\n";
				tabSlot[x].getTextAreaSlot().setText(output);
				break;
			case "Item":
				output += "Item Name:";
				output += "\nnull\n";
				output += "Item ID:";
				output += "\nnull\n";
				output += "Item Description:";
				output += "\nnull\n";
				output += "Item Type:";
				output += "\nnull\n";
				output += "Item Action Value:";
				output += "\nnull\n";
				output += "Item Amount:";
				output += "\nnull\n";
				output += "Item Drop Rate:";
				output += "\nnull\n";
				tabSlot[x].getTextAreaSlot().setText(output);
				break;
			case "Monster":
				output += "Monster Name:";
				output += "\nnull\n";
				output += "Monster ID:";
				output += "\nnull\n";
				output += "Monster Description:";
				output += "\nnull\n";
				output += "Monster Health:";
				output += "\nnull\n";
				output += "Monster Damage:";
				output += "\nnull\n";
				output += "Monster Hit Percentage:";
				output += "\nnull\n";
				tabSlot[x].getTextAreaSlot().setText(output);
				break;
			case "Picture":
				output += "There is no preset for picture";
				tabSlot[x].getTextAreaSlot().setText(output);
				break;
			case "Puzzle":
				output += "Puzzle ID:";
				output += "\nnull\n";
				output += "Puzzle Description:";
				output += "\nnull\n";
				output += "Puzzle Type:";
				output += "\nnull\n";
				output += "Puzzle Solution:";
				output += "\nnull\n";
				output += "Puzzle Hint:";
				output += "\nnull\n";
				output += "Puzzle Damage:";
				output += "\nnull\n";
				tabSlot[x].getTextAreaSlot().setText(output);
				break;
			case "Room":
				output += "Room Floor:";
				output += "\nnull\n";
				output += "Room ID:";
				output += "\nnull\n";
				output += "Room Description:";
				output += "\nnull\n";
				output += "Room Connection:";
				output += "\nnull\n";
				output += "Room Access:";
				output += "\nnull\n";
				output += "Room Item:";
				output += "\nnull\n";
				output += "Room Monster:";
				output += "\nnull\n";
				output += "Room Picture:";
				output += "\nnull\n";
				tabSlot[x].getTextAreaSlot().setText(output);
				break;
			}
		}
	}

	@FXML
	void levelEditorLoadFileEvent(ActionEvent event) throws IOException {
		int x = levelEditorTabPane.getSelectionModel().getSelectedIndex(); 
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("CSV files (*.txt)", "*.txt");
		File userDirectory = new File("./res/Game Folder/");
		chooser.setInitialDirectory(userDirectory);
		chooser.getExtensionFilters().add(extentionFilter);
		chooser.setTitle("Load File");
		File file = chooser.showOpenDialog(new Stage());
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
		String output = "";
		String fileLine = "";
		while((fileLine = bufferedReader.readLine()) != null) {
			if(fileLine.length() > 50) {
				int totalCharacterLength = 0;
				int descriptionLength = fileLine.length();
				String outputString ="";

				for (String word : fileLine.split(" ")) {
					totalCharacterLength += word.length();
					descriptionLength -= word.length()+1;
					outputString += word + " ";

					if(totalCharacterLength > 40 && descriptionLength > 0) {
						totalCharacterLength = 0;
						outputString += "\n";
					}
				}
				output += outputString + "\n";
			}else {
				output += fileLine;
				output += "\n";
			}
		}
		tabSlot[x].getTextAreaSlot().setText(output);
		tabSlot[x].getTabSlot().setText(file.getName());
	}

	@FXML
	void levelEditorNewFileEvent(ActionEvent event) {

	}

	@FXML
	void radioButtonPressed(ActionEvent event) {
		radioSelection = ((RadioButton)event.getSource()).getText();
		String output = "";
		switch(radioSelection) {
		case "Character":
			output += "-[Room Floor:]\n";
			output += "Elevator (Put in the Room Name)\n"
					+ "(Only 1 name allowed)\n";
			output += "-[Room ID:]\n";
			output += "R00 (Letter R Followed by 2 Digit)\n"
					+ "(Only 1 ID allowed)\n";
			output += "-[Room Description:]\n";
			output += "A dark secret room (Describe the room)\n";
			output += "-[Room Connection:]\n";
			output += "R02:Roof (Room ID followed by room direction)\n"
					+ "(Can have mutiple room direction and id)\n";
			output += "-[Room Access:]\n";
			output += "P09:R02 (Puzzle Id followed by previous room id)\n"
					+ "(Only 1 puzzle allowed, but mutiple room fine)\n";
			output += "-[Room Item:]\n";
			output += "I01 (Letter I followed by 2 Digit)\n"
					+ "(Can have multiple item in a room)\n";
			output += "-[Room Monster:]\n";
			output += "R01 (Letter I followed by 2 Digit)\n" + 
					"(Can have multiple monster in a room,\n"
					+ "but they must be unique monster)\n";
			output += "-[Room Picture:]\n";
			output += "Room 1.png (File name of picture)\n"
					+ "(Only 1 picture allowed)\n";
			levelEditorHelpTextArea.setText(output);
			break;
		case "Item":
			output += "-[Item Name:]\n";
			output += "An Arrow (Put in the weapon name)\n"
					+ "(Only 1 name allowed)\n";
			output += "-[Weapon ID:]\n";
			output += "I01 (Letter I Followed by 2 Digit)\n"
					+ "(Only 1 ID allowed)\n";
			output += "-[Weapon Description:]\n";
			output += "Hurts my Knee (Describe the weapon)\n";
			output += "-[Item Type:]\n";
			output += "Weapon (5 Different type of item)\n"
					+ "(Can have 1 type)\n"
					+ "(Weapon,Utility,Ammo,Healing,Throwable)\n";
			output += "-[Item Action Value]\n";
			output += "5 (different action value depending on type)\n"
					+ "(Only 1 puzzle allowed, but mutiple room fine)\n";
			output += "-[Room Item:]\n";
			output += "I01 (Letter I followed by 2 Digit)\n"
					+ "(Can have multiple item in a room)\n";
			output += "-[Room Monster:]\n";
			output += "R01 (Letter I followed by 2 Digit)\n" + 
					"(Can have multiple monster in a room,\n"
					+ "but they must be unique monster)\n";
			output += "-[Room Picture:]\n";
			output += "Room 1.png (File name of picture)\n"
					+ "(Only 1 picture allowed)\n";
			levelEditorHelpTextArea.setText(output);
			break;
		case "Monster":
			break;
		case "Picture":
			break;
		case "Puzzle":
			break;
		case "Room":
			break;
		}
	}

	@FXML
	void levelEditorSaveFileEvent(ActionEvent event) {
		int x = levelEditorTabPane.getSelectionModel().getSelectedIndex();
		if(tabSlot[x].getTextAreaSlot().getText().equalsIgnoreCase("")) {
			levelEditorConsoleTextArea.setText("There is nothing to save \nwith this file");
		}else {

			FileChooser fileChooser = new FileChooser();
			File userDirectory = new File("./res/Game Folder/");
			fileChooser.setInitialDirectory(userDirectory);

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(new Stage());

			if(file != null){
				SaveFile(tabSlot[x].getTextAreaSlot().getText(), file);
				tabSlot[x].getTabSlot().setText(file.getName());
			}
		}
	}

	private void SaveFile(String content, File file){
		try {
			FileWriter fileWriter = null;

			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (Exception e) {

		}

	}

	class tabSelection{
		Tab tabSlot;
		TextArea textAreaSlot;

		public tabSelection(Tab tabSlot, TextArea textAreaSlot) {		
			this.tabSlot = tabSlot;		
			this.textAreaSlot = textAreaSlot;		
		}

		public Tab getTabSlot() {
			return tabSlot;
		}

		public void setTabSlot(Tab tabSlot) {
			this.tabSlot = tabSlot;
		}

		public TextArea getTextAreaSlot() {
			return textAreaSlot;
		}

		public void setTextAreaSlot(TextArea textAreaSlot) {
			this.textAreaSlot = textAreaSlot;
		}


	}

}
