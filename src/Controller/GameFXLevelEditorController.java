package Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Optional;

import Model.GameFXModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GameFXLevelEditorController {

	@FXML
	private SplitPane mainPane;

	@FXML
	private Button levelEditorBackButton;

	@FXML
	private Button levelEditorSaveFile;

	@FXML
	private Button levelEditorLoadFile;

	@FXML
	private Button levelEditorGetPreset;

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
	private RadioButton levelEditorGameSettingRadioButton;

	@FXML
	private Button levelEditorCreateGameFolder;

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

	GameFXModel model = new GameFXModel();

	public void initialize() throws URISyntaxException{
		model.playMusic("Level Editor.mp3");

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

		tabSlot[0].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		tabSlot[1].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		tabSlot[2].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		tabSlot[3].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		tabSlot[4].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		tabSlot[5].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		tabSlot[6].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		tabSlot[7].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		tabSlot[8].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		tabSlot[9].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));

		levelEditorCharacterRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorItemRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorMonsterRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorPictureRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorPuzzleRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorRoomRadioButton.setToggleGroup(radioToggleGroup);
		levelEditorGameSettingRadioButton.setToggleGroup(radioToggleGroup);

		levelEditorConsoleTextArea.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		levelEditorConsoleTextArea.setEditable(false);

	}

	@FXML
	void levelEditorBackButtonEvent(ActionEvent event) throws IOException, URISyntaxException {
		model.stopMusic();

		Parent secondPane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
		Scene scene = new Scene(secondPane);

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
		window.setScene(scene);
		window.show();
	}


	@FXML
	void levelEditorGetPresetEvent(ActionEvent event) {
		if(radioSelection.equalsIgnoreCase("")) {
			levelEditorConsoleTextArea.setText("Please Select a "+"\n"+"Category of Files First");
		}else {
			int x = levelEditorTabPane.getSelectionModel().getSelectedIndex();
			System.out.println(x);
			String output = "";
			tabSlot[x].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
			switch(radioSelection) {
			case "Character":
				output += "Character Name:";
				output += "\nnull\n";
				output += "Character ID:";
				output += "\nnull\n";
				output += "Character Description:";
				output += "\nnull\n";
				output += "Character Health:";
				output += "\nnull\n";
				output += "Character Inventory:";
				output += "\nnull\n";
				output += "Character Weapon:";
				output += "\nnull\n";
				output += "Character Location:";
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
			case "Game Setting":
				output += "Create Player:";
				output += "\nnull\n";
				output += "Set Starting Room:";
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
		String output = "";
		String fileLine = "";
		if (file != null) {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
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
			tabSlot[x].getTextAreaSlot().setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
			tabSlot[x].getTextAreaSlot().setText(output);
			tabSlot[x].getTabSlot().setText(file.getName());
			levelEditorConsoleTextArea.setText("File Loaded!");
		}else {
			levelEditorConsoleTextArea.setText("Load File Cancel");
		}

	}


	@FXML
	void radioButtonPressed(ActionEvent event) {
		radioSelection = ((RadioButton)event.getSource()).getText();
		String output = "";
		levelEditorHelpTextArea.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
		switch(radioSelection) {
		case "Character":
			output += "-[Character Name:]\n";
			output += "(Put in the character name)\n"
					+ "(Rule: Only 1 character name allowed)\n\n";

			output += "-[Character ID:]\n";
			output += "(Letter C Followed by 2 Digit)\n"
					+ "(Rule: Only 1 ID allowed)\n\n";

			output += "-[Character Description:]\n";
			output += "(Describe the character)\n\n";

			output += "-[Character Health:]\n";
			output += "(Describe the character)\n\n";

			output += "-[Character Inventory:]\n";
			output += "(Letter I Followed by 2 Digit)\n"
					+ "(Rule: Can have multiple item)\n\n";

			output += "-[Character Weapon:]\n";
			output += "(Letter W Followed by 2 Digit)\n"
					+ "(Rule: Can only have 1 weapon)\n\n";

			output += "-[Character Location:]\n";
			output += "(Letter R Followed by 2 Digit)\n"
					+ "(Rule: Can only have 1 room)\n\n";
			levelEditorHelpTextArea.setText(output);
			break;
		case "Item":
			output += "-[Item Name:]\n";
			output += "(Put in the item name)\n"
					+ "(Rule: Only 1 item name allowed)\n\n";

			output += "-[Item ID:]\n";
			output += "(Letter I Followed by 2 Digit)\n"
					+ "(Rule: Only 1 ID allowed)\n\n";

			output += "-[Item Description:]\n";
			output += "(Describe the weapon)\n\n";

			output += "-[Item Type:]\n";
			output += "(Only 5 type of item allowed below)\n"
					+ "(Rule: Only 1 type allowed)\n"
					+ "(Rule: Weapon,Special Weapon,Utility,Ammo,Healing, or Throwable)\n\n";

			output += "-[Item Action Value]\n";
			output += "(Different action value depending on type, int or null)\n"
					+ "(Hint: Weapon/Special Weapon = Weapon Damage)\n"
					+ "(Hint: Utility = Null)\n"
					+ "(Hint: Ammo = Weapon ID)\n"
					+ "(Hint: Healing = Healing Amount)\n"
					+ "(Hint: Throwable = Weapon Damage)\n"
					+ "(Rule: Only 1 Action Value allowed)\n\n";

			output += "-[Item Amount:]\n";
			output += "(The amount you can pick up and drop)\n"
					+ "(Rule: Only 1 Item Amount allowed)\n\n";

			output += "-[Item Drop Rate:]\n";
			output += "(2 Digit decimal number below 1)\n"
					+ "(Rule: Only 1 Item drop rate allowed)";
			levelEditorHelpTextArea.setText(output);
			break;
		case "Monster":
			output += "-[Monster Name:]\n";
			output += "(Put in the monster name)\n"
					+ "(Rule: Only 1 monster name allowed)\n\n";

			output += "-[Monster ID:]\n";
			output += "(Letter M Followed by 2 Digit)\n"
					+ "(Rule: Only 1 ID allowed)\n";

			output += "-[Monster Description:]\n";
			output += "(Describe the monster)\n\n";

			output += "-[Monster Health:]\n";
			output += "(Set monster max health)\n\n";

			output += "-[Monster Damage:]\n";
			output += "(Set monster damage)\n\n";

			output += "-[Monster Hit Percentage:]\n";
			output += "(2 Digit decimal number below 1)\n"
					+"(Rule: Only 1 Monster Hit Percentage allowed)\n\n";

			output += "-[Monster Type:]\n";
			output += "(Only 3 type of monster allowed below)\n"
					+ "(Rule: Grunt, Mini Boss, or Final Boss)\n\n";

			output += "-[Monster Picture:]\n";
			output += "(File name of picture)\n"
					+ "(Only 1 picture allowed)\n"
					+ "(Place picture in picture folder)";
			levelEditorHelpTextArea.setText(output);
			break;
		case "Picture":
			output += "Place picture in picture folder";
			levelEditorHelpTextArea.setText(output);
			break;
		case "Puzzle":
			output += "-[Puzzle ID:]\n";
			output += "(Letter P followed by 2 Digit)\n"
					+ "(Rule: Only 1 ID Allowed)\n\n";

			output += "-[Puzzle Description:]\n";
			output += "(Describe the puzzle)\n\n";

			output += "-[Puzzle Type:]\n";
			output += "(Only 2 type of puzzle allowed below)\n"
					+ "(Rule: Input or Item)\n\n";

			output += "-[Puzzle Solution:]\n";
			output += "(Number to solve puzzle or ID of item)\n\n";

			output += "-[Puzzle Hints:]\n";
			output += "(Describe the hint for the puzzle)\n\n";

			output += "-[Puzzle Damage:]\n";
			output += "(Integer of damage if you fail to beat puzzle)";

			levelEditorHelpTextArea.setText(output);
			break;
		case "Room":
			output += "-[Room Floor:]\n";
			output += "(Put Room Name Here)\n"
					+ "(Rule: Only 1 Name Allowed)\n\n";

			output += "-[Room ID:]\n";
			output += "(Letter R Followed by 2 Digit)\n"
					+ "(Rule: Only 1 ID Allowed)\n\n";

			output += "-[Room Description:]\n";
			output += "(Describe the room)\n\n";

			output += "-[Room Connection:]\n";
			output += "(Room ID and Room direction seperated by comma)\n"
					+ "(Hint: Can have multiple room connection)\n\n";

			output += "-[Room Access:]\n";
			output += "(Puzzle Id and Previous Room id seperated by comma)\n"
					+ "(Rule: Only 1 puzzle, but multiple room fine)\n\n";

			output += "-[Room Item:]\n";
			output += "(Letter I followed by 2 Digit)\n"
					+ "(Hint: Can have multiple item in a room)\n\n";

			output += "-[Room Monster:]\n";
			output += "(Letter R followed by 2 Digit)\n" + 
					"(Hint: Can have multiple monster in a room)\n"
					+ "(Rule: Must be Unique Monster)\n\n";

			output += "-[Room Picture:]\n";
			output += "(File name of picture)\n"
					+ "(Only 1 picture allowed)\n"
					+ "(Place picture in picture folder)";
			levelEditorHelpTextArea.setText(output);
			break;
		case "Game Setting":
			output += "-[Create Character:]\n";
			output += "(Letter C Followed by 2 Digit)\n"
					+ "(Hint: Can make multiple character)\n"
					+ "(Rule: Max character is 3)\n\n";

			output += "-[Game Introduction:]\n";
			output += "(Describe the introduction)\n\n";

			output += "-[Set Starting Room:]\n";
			output += "(Letter R Followed by 2 Digit)\n"
					+ "(Rule: Can only have 1 starting room)\n\n";

			levelEditorHelpTextArea.setText(output);
			break;
		}
	}

	@FXML
	void levelEditorSaveFileEvent(ActionEvent event) {
		int x = levelEditorTabPane.getSelectionModel().getSelectedIndex();
		if(tabSlot[x].getTextAreaSlot().getText().equalsIgnoreCase("")) {
			levelEditorConsoleTextArea.setText("There is nothing to save");
		}else {


			FileChooser fileChooser = new FileChooser();

			if(!tabSlot[x].getTabSlot().getText().equalsIgnoreCase("Empty")) {
				fileChooser.setInitialFileName(tabSlot[x].getTabSlot().getText());
			}

			File userDirectory = new File("./res/Game Folder/");
			fileChooser.setInitialDirectory(userDirectory);

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(new Stage());

			if(file != null){
				SaveFile(tabSlot[x].getTextAreaSlot().getText(), file);
				tabSlot[x].getTabSlot().setText(file.getName());
				levelEditorConsoleTextArea.setText("File Saved!");
			}else {
				levelEditorConsoleTextArea.setText("Saving file cancel");
			}
		}
	}

	@FXML
	void levelEditorCreateGameFolderEvent(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Game Folder Creater");
		dialog.setHeaderText("Creating Game Folder");
		dialog.setContentText("Please enter a name:");


		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			File filePath = new File("./res/Game Folder/" + result.get());
			if(filePath.exists() && filePath.isDirectory()) {
				levelEditorConsoleTextArea.setText("Folder already exist there");
			}else {
				File gameFolder = new File("./res/Game Folder/" + result.get());
				File characterFolder = new File("./res/Game Folder/" + result.get() + "/Character");
				File itemFolder = new File("./res/Game Folder/" + result.get() + "/Item");
				File monsterFolder = new File("./res/Game Folder/" + result.get() + "/Monster");
				File pictureFolder = new File("./res/Game Folder/" + result.get() + "/Picture");
				File puzzleFolder = new File("./res/Game Folder/" + result.get() + "/Puzzle");
				File roomFolder = new File("./res/Game Folder/" + result.get() + "/Room");

				gameFolder.mkdir();
				characterFolder.mkdir();
				itemFolder.mkdir();
				monsterFolder.mkdir();
				pictureFolder.mkdir();
				puzzleFolder.mkdir();
				roomFolder.mkdir();
				levelEditorConsoleTextArea.setText("Folder " + result.get() + " created.");
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
