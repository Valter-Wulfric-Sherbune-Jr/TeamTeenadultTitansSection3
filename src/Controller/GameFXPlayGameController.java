package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import Controller.GameFXLevelEditorController.tabSelection;
import GameObject.Items;
import GameObject.Monsters;
import GameObject.Players;
import GameObject.SaveData;
import Model.GameFXModel;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameFXPlayGameController {

	@FXML
	private ImageView actionBackground;

	@FXML
	private ImageView actionPlayerImage;

	@FXML
	private ImageView combatBackground;

	@FXML
	private ImageView combatEnemyImage3;

	@FXML
	private ImageView combatEnemyImage1;

	@FXML
	private ImageView combatEnemyImage2;

	@FXML
	private ImageView combatEnemyImage4;

	@FXML
	private SplitPane mainPane;

	@FXML
	private TabPane tabPaneMenu;

	@FXML
	private Tab actionTab;

	@FXML
	private Button actionMove;

	@FXML
	private Button actionInventory;

	@FXML
	private Button actionExamineRoom;

	@FXML
	private Button actionSaveGame;

	@FXML
	private Button actionQuitGame;

	@FXML
	private Label actionLabel;

	@FXML
	private SplitPane actionInventoryPane;

	@FXML
	private ListView<String> actionList;

	@FXML
	private VBox actionLootBox;

	@FXML
	private Button actionPickUpItem;

	@FXML
	private Button actionExamineLootEvent;

	@FXML
	private Button actionLootExit;

	@FXML
	private VBox actionNeutralBox;

	@FXML
	private VBox actionInventoryBox;

	@FXML
	private Button actionExamineItem;

	@FXML
	private Button actionDropItem;

	@FXML
	private Button actionUseItem;

	@FXML
	private Button actionEquipItemEvent;

	@FXML
	private Button actionExitInventory;

	@FXML
	private Tab combatTab;

	@FXML
	private Button combatAttack;

	@FXML
	private Button combatDefend;

	@FXML
	private Button combatExamineMonster;

	@FXML
	private Button combatInventory;

	@FXML
	private Button combatRunAway;

	@FXML
	private Button combatQuitGame;

	@FXML
	private SplitPane combatInventoryPane;

	@FXML
	private VBox combatBox;

	@FXML
	private Label combatLabel;

	@FXML
	private ListView<String> combatList;

	@FXML
	private VBox combatLootBox;

	@FXML
	private Button combatLootPickUp;

	@FXML
	private Button combatLootExamineItem;

	@FXML
	private Button combatLootExit;

	@FXML
	private VBox combatInventoryVBox;

	@FXML
	private Button combatUseItem;

	@FXML
	private Button combatExamineItem;

	@FXML
	private Button combatEquipItem;

	@FXML
	private Button combatInventoryExit;

	@FXML
	private Tab puzzleTab;

	@FXML
	private Button puzzleInputNumber;

	@FXML
	private Button puzzleUseItem;

	@FXML
	private Button puzzleHint;

	@FXML
	private Button puzzleExit;

	@FXML
	private SplitPane puzzleKeyPadPane;

	@FXML
	private TextArea puzzleKeyPadTextArea;

	@FXML
	private Button puzzleKeyPad7;

	@FXML
	private Button puzzleKeyPad4;

	@FXML
	private Button puzzleKeyPad1;

	@FXML
	private Button puzzleKeyPadDot;

	@FXML
	private Button puzzleKeyPad2;

	@FXML
	private Button puzzleKeyPad5;

	@FXML
	private Button puzzleKeyPad6;

	@FXML
	private Button puzzleKeyPad3;

	@FXML
	private Button puzzleKeyPadClear;

	@FXML
	private Button puzzleKeyPad8;

	@FXML
	private Button puzzleKeyPad9;

	@FXML
	private Button puzzleKeyPad0;

	@FXML
	private SplitPane puzzleInventoryPane;

	@FXML
	private Label puzzleLabel;

	@FXML
	private ListView<String> puzzleList;

	@FXML
	private VBox puzzleBox;

	@FXML
	private TextArea consoleTextArea;

	@FXML
	private Tab partyMemberTab1;

	@FXML
	private ImageView partyMemberImage1;

	@FXML
	private Label partyMemberHealth1;

	@FXML
	private Label partyMemberWeapon1;

	@FXML
	private ProgressBar partyMemberHealthBar1;

	@FXML
	private Tab partyMemberTab2;

	@FXML
	private ImageView partyMemberImage2;

	@FXML
	private Label partyMemberHealth2;

	@FXML
	private Label partyMemberWeapon2;

	@FXML
	private ProgressBar partyMemberHealthBar2;

	@FXML
	private Tab partyMemberTab3;

	@FXML
	private ImageView partyMemberImage3;

	@FXML
	private Label partyMemberHealth3;

	@FXML
	private Label partyMemberWeapon3;

	@FXML
	private ProgressBar partyMemberHealthBar3;

	@FXML
	private TabPane partyMemberTabPane;

	partyMemberSelection[] partyMemberSlot = new partyMemberSelection[3];

	private String output;

	private ObservableList<String> observList = FXCollections.observableArrayList();



	GameFXModel model = new GameFXModel();

	private String gameFolder = "";

	private HashMap<String, Image> pictureList;

	//----------------------------------------------------------------------------

	public void initialize() throws URISyntaxException 
	{
		try 
		{
			model.playSoundEffect("Scene Transition.wav");
			BufferedReader bufferedReader = new BufferedReader(new FileReader("./res/Loading Folder Choice.txt"));
			String type = bufferedReader.readLine();

			if(type.equalsIgnoreCase("New Game")) {
				gameFolder = bufferedReader.readLine();
				bufferedReader.close();

				model.setGameFolder(gameFolder);
				model.loadGamePictureFolder();
				model.loadGameFolder("Item");
				model.loadGameFolder("Monster");
				model.loadGameFolder("Puzzle");
				model.loadGameFolder("Room");	
				model.loadGameFolder("Player");	
				model.setGameSetting();

				pictureList = model.getPictureList();





			}
			else if(type.equalsIgnoreCase("Load Game")) {
				String saveId = bufferedReader.readLine();
				model.setSaveList();
				gameFolder = model.getSaveList().get(Integer.parseInt(saveId)).getGameFolder();
				model.setGameFolder(gameFolder);
				model.loadGamePictureFolder();
				pictureList = model.getPictureList();
				model.loadGameSaveData(model.getSaveList().get(Integer.parseInt(saveId)));
			}



			partyMemberSlot[0] = new partyMemberSelection(partyMemberTab1,partyMemberImage1,partyMemberHealth1,partyMemberWeapon1,partyMemberHealthBar1);		
			partyMemberSlot[1] = new partyMemberSelection(partyMemberTab2,partyMemberImage2,partyMemberHealth2,partyMemberWeapon2,partyMemberHealthBar2);
			partyMemberSlot[2] = new partyMemberSelection(partyMemberTab3,partyMemberImage3,partyMemberHealth3,partyMemberWeapon3,partyMemberHealthBar3);


			for(int x = 0; x < model.getParty().getPartyMember().size(); x++) {
				Players player = model.getParty().getPartyMember().get(x);
				partyMemberSlot[x].setPlayerMember(player);
			}

			updatePlayerTab();

			model.getParty().startGameTime();
			model.playMusic("Action Menu.mp3");
			puzzleKeyPadTextArea.setEditable(false);
			consoleTextArea.setEditable(false);
			actionBackground.setImage(pictureList.get(model.getParty().getCurrentRoom().getRoomBackground()));
			actionBackground.setVisible(true);
			actionBackground.setDisable(false);
			printHelp(model.getParty().getCurrentRoom().toString());

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------------------

	@FXML
	void actionMoveEvent(ActionEvent event) throws URISyntaxException 
	{
		if(actionList.getSelectionModel().getSelectedItem() == null) 
		{
			actionInventoryPane.setVisible(true);
			actionList.setVisible(true);

			actionLabel.setText("Room Exit");
			setListDirection(model.getParty().getCurrentRoom().getRoomNavigationList());
			actionList.setItems(observList);
		}
		else 
		{
			model.setNextRoom(actionList.getSelectionModel().getSelectedItem());
			moveRoom();
		}
	}

	private void moveRoom() throws URISyntaxException 
	{
		if(model.getNextRoomPuzzle() != null) 
		{
			changeTab("Puzzle");
		}
		else if(model.getNextRoom().getRoomMonster().isEmpty()) 
		{
			model.getParty().setCurrentRoom(model.getNextRoom());
			output +=  "You moved to the next room";
			output +=  "\n--------------------------------------------------";
			changeTab("Action");
			printHelp(output);
		}
		else
		{
			model.stopMusic();
			model.getParty().setCurrentRoom(model.getNextRoom());
			checkDupMonster(model.getParty().getCurrentRoom().getRoomMonster());

			String combatMusic = "Combat 1.mp3";

			output += "- - - - -          ENCOUNTER!          - - - - -\n";
			output += "-----------------------------------------------\n";
			for(int x = 0; x < model.getParty().getCurrentRoom().getRoomMonster().size(); x++) 
			{
				output += model.getParty().getCurrentRoom().getRoomMonster().get(x).toString() + "\n";
				if(model.getParty().getCurrentRoom().getRoomMonster().get(x).getMonsterType().equalsIgnoreCase("Final Boss")) 
				{
					combatMusic = "Combat 3.mp3";
				}else if(model.getParty().getCurrentRoom().getRoomMonster().get(x).getMonsterType().equalsIgnoreCase("Mini Boss")) 
				{
					combatMusic = "Combat 2.mp3";
				}
			}

			model.playCombatMusic("Battle Transition.mp3", combatMusic);
			changeTab("Combat");
			printHelp(output);
		}
	}

	@FXML
	void actionInventoryEvent(ActionEvent event) 
	{
		actionNeutralBox.setVisible(false);
		actionInventoryBox.setVisible(true);
		actionInventoryPane.setVisible(true);
		actionList.setVisible(true);

		actionLabel.setText("Inventory List");
		setListInventory(model.getParty().getPartyInventory());
		actionList.setItems(observList);

		output += "\n";
		for(int x = 0; x < model.getParty().getPartyMember().size(); x++) {
			Players player = model.getParty().getPartyMember().get(x);	
			output += "Player Name: " + player.getPlayerName();
			output += "\nPlayer Health: (" + player.getPlayerCurrentHealth() + "/" + player.getPlayerMaxHealth() + ")";
			output += "\nPlayer Weapon: " +player.getWeapon().getItemName();
			output += "\n\n";
		}
		output += "--------------------------------------------------";
		printHelp(output);
	}

	@FXML
	void actionExamineItemEvent(ActionEvent event) 
	{

		ArrayList<Items> itemList = model.getParty().getPartyInventory();
		Items item = itemList.get(checkInventory("actionList",itemList));

		output += item.toString();
		printHelp(output);

	}

	@FXML
	void actionDropItemEvent(ActionEvent event) 
	{
		ArrayList<Items> itemList = model.getParty().getPartyInventory();
		Items item = itemList.get(checkInventory("actionList",itemList));

		output += "You drop the " + item.getItemName();
		output += "\n--------------------------------------------------";

		model.getParty().getCurrentRoom().addRoomItem(item);
		model.getParty().removeItemFromInventory(item);

		setListInventory(itemList);
		actionList.setItems(observList);

		printHelp(output);


	}

	private int checkInventory(String list, ArrayList<Items> itemList) 
	{
		switch(list.toLowerCase()) {
		case "actionlist":
			String itemName = "";
			for(int x = 0; x < itemList.size(); x++) 
			{
				itemName = itemList.get(x).getItemName()+ " x" + itemList.get(x).getItemAmount();
				if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) 
				{
					return x;
				}

			}
			System.out.println("Error Finding Item");
			return 0;
		case "combatlist":
			String itemName1 = "";
			for(int x = 0; x < itemList.size(); x++) 
			{
				itemName1 = itemList.get(x).getItemName()+ " x" + itemList.get(x).getItemAmount();
				if(itemName1.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) 
				{
					return x;
				}

			}
			System.out.println("Error Finding Item");
			return 0;
		case "puzzlelist":
			return 0;
		default:
			return 0;
		}
	}

	@FXML
	void actionUseItemEvent(ActionEvent event) 
	{
		ArrayList<Items> itemList = model.getParty().getPartyInventory();
		Items item = itemList.get(checkInventory("actionList",itemList));

		if(item.getItemType().equalsIgnoreCase("Healing")) 
		{
			mainPane.setDisable(true);
			List<String> choices = new ArrayList<>();

			for(int x = 0; x < model.getParty().getPartyMember().size(); x++) {
				Players player = model.getParty().getPartyMember().get(x);
				choices.add(player.getPlayerName());
			}

			ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
			dialog.setTitle("Player Selection");
			dialog.setHeaderText("Who will you use this on?");
			dialog.setContentText("Select a player:");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				int bound = 0;
				for(int x = 0; x < choices.size(); x++) {
					if(choices.get(x).equalsIgnoreCase(result.get())) {
						bound = x;
						break;
					}
				}
				Players player = model.getParty().getPartyMember().get(bound);

				if(player.getPlayerCurrentHealth() == player.getPlayerMaxHealth()) {
					output += player.getPlayerName() + " is already at full health";
					output += "\n--------------------------------------------------";
					printHelp(output);
					mainPane.setDisable(false);
				}else {

					player.healHealth(item.getItemActionValue());
					model.getParty().removeItemFromInventory(item);

					output += player.getPlayerName() + " recover " + item.getItemActionValue() + " health.";
					output += "\n--------------------------------------------------";	

					setListInventory(itemList);
					actionList.setItems(observList);
					printHelp(output);
					mainPane.setDisable(false);
				}
			}else {
				mainPane.setDisable(false);
			} 	
		}else 
		{
			output += "This item cannot be use right now";
			output += "\n--------------------------------------------------";
			printHelp(output);
		}	

	}


	@FXML
	void actionEquipItemEvent(ActionEvent event) 
	{
		ArrayList<Items> itemList = model.getParty().getPartyInventory();
		Items item = itemList.get(checkInventory("actionList",itemList));

		if(item.getItemType().equalsIgnoreCase("Weapon")) 
		{
			mainPane.setDisable(true);
			List<String> choices = new ArrayList<>();

			for(int x = 0; x < model.getParty().getPartyMember().size(); x++) {
				Players player = model.getParty().getPartyMember().get(x);
				choices.add(player.getPlayerName());
			}

			ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
			dialog.setTitle("Player Selection");
			dialog.setHeaderText("Who will you use this on?");
			dialog.setContentText("Select a player:");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				int bound = 0;
				for(int x = 0; x < choices.size(); x++) {
					if(choices.get(x).equalsIgnoreCase(result.get())) {
						bound = x;
						break;
					}
				}
				Players player = model.getParty().getPartyMember().get(bound);

				model.getParty().addItemToInventory(player.getWeapon());
				player.equipWeapon(item);
				model.getParty().removeItemFromInventory(item);

				output += player.getPlayerName() + " equipped the " + item.getItemName();
				output += "\n--------------------------------------------------";


				setListInventory(itemList);
				actionList.setItems(observList);
				updatePlayerTab();

				printHelp(output);
				mainPane.setDisable(false);
			}else {
				mainPane.setDisable(false);
			} 	
		}else 
		{
			output += "This item is not a weapon";
			output += "\n--------------------------------------------------";
			printHelp(output);
		}	

	}

	@FXML
	void actionExitInventoryEvent(ActionEvent event) 
	{
		actionList.getSelectionModel().clearSelection();
		actionInventoryBox.setVisible(false);
		actionInventoryPane.setVisible(false);
		actionNeutralBox.setVisible(true);
	}

	@FXML
	void actionExamineRoomEvent(ActionEvent event) 
	{
		actionNeutralBox.setVisible(false);

		actionLootBox.setVisible(true);
		actionInventoryPane.setVisible(true);
		actionList.setVisible(true);

		output += model.getParty().getCurrentRoom().toString();

		actionLabel.setText("Item on Ground");
		setListInventory(model.getParty().getCurrentRoom().getRoomItem());
		actionList.setItems(observList);

		printHelp(output);
	}

	@FXML
	void actionPickUpItemEvent(ActionEvent event) 
	{
		ArrayList<Items> itemList = model.getParty().getCurrentRoom().getRoomItem();
		Items item = itemList.get(checkInventory("actionList",itemList));

		output += "You Picked Up the " + item.getItemName();
		output += "\n--------------------------------------------------\n";

		model.getParty().addItemToInventory(item);
		model.getParty().getCurrentRoom().removeRoomItemId(item);

		setListInventory(model.getParty().getCurrentRoom().getRoomItem());
		actionList.setItems(observList);

		printHelp(output);
	}

	@FXML
	void actionExamineLootEvent(ActionEvent event) 
	{
		ArrayList<Items> itemList = model.getParty().getCurrentRoom().getRoomItem();
		Items item = itemList.get(checkInventory("actionList",itemList));

		output +=  item.toString();
		printHelp(output);
	}

	@FXML
	void actionLootExitEvent(ActionEvent event) throws URISyntaxException  
	{
		actionLootBox.setVisible(false);
		actionNeutralBox.setVisible(true);
		actionInventoryPane.setVisible(false);

		model.stopMusic();
		model.playMusic("Action Menu.mp3");
	}

	@FXML
	void actionSaveGameEvent(ActionEvent event) 
	{
		if(actionList.getSelectionModel().getSelectedItem() == null) 
		{
			actionInventoryPane.setVisible(true);

			model.setSaveList();
			setListSave();

			actionLabel.setText("Save Data");
			actionList.setItems(observList);
		}
		else 
		{
			int saveId = actionList.getSelectionModel().getSelectedIndex()+1;

			model.setSaveData(saveId,gameFolder);
			model.saveGameData(model.getSaveData());



			output += "The game has been saved!\n";
			output += "--------------------------------------------------";



			model.setSaveList();
			setListSave();
			actionList.setItems(observList);

			printHelp(output);

		}
	}

	@FXML
	void actionQuitGameEvent(ActionEvent event) throws IOException, URISyntaxException 
	{
		mainPane.setDisable(true);

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("You are exiting to the Main Menu");
		alert.setContentText("Are you sure you wanna exit?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			model.stopMusic();
			Parent secondPane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
			Scene scene = new Scene(secondPane);

			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
			window.setTitle("Game Maker");
			window.setScene(scene);
			window.show();
		}
		else 
		{
			mainPane.setDisable(false);
		}
	}

	//----------------------------------------------------------------------------

	@FXML
	void combatAttackEvent(ActionEvent event) throws URISyntaxException, IOException 
	{
		if(combatList.getSelectionModel().getSelectedItem() == null) 
		{
			combatInventoryPane.setVisible(true);
			combatList.setVisible(true);

			combatLabel.setText("Monsters");
			checkDupMonster(model.getParty().getCurrentRoom().getRoomMonster());
			setListMonster(model.getParty().getCurrentRoom().getRoomMonster());
			combatList.setItems(observList);
		}
		else 
		{
			String monsterName = "";
			ArrayList<Monsters> monsterList = model.getParty().getCurrentRoom().getRoomMonster();
			for(int x = 0; x < monsterList.size(); x++) {
				monsterName = monsterList.get(x).getMonsterName() + " - Health:" + 
						monsterList.get(x).getMonsterCurrentHealth() + "/" + monsterList.get(x).getMonsterMaxHealth();
				if(monsterName.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) {
					model.setCurrentMonster(monsterList.get(x));
				}

			}

			int index = partyMemberTabPane.getSelectionModel().getSelectedIndex();
			Players player = partyMemberSlot[index].getPlayerMember();

			if(model.getParty().getWeaponAmmo(player.getWeapon()) == 0) {
				output += "You don't have any ammo for your weapon!\n";
				output += "\n--------------------------------------------------";
				printHelp(output);
				partyMemberTabPane.getSelectionModel().getSelectedItem().setDisable(true);
				if(checkPartyTab() == false) {
					for(int z = 0; z < model.getParty().getCurrentRoom().getRoomMonster().size(); z++) {
						allMonsterAttack(event);

					}
				}
			}else {

				output += player.getPlayerName() + " attack the " + model.getCurrentMonster().getMonsterName() + "!";
				model.getCurrentMonster().takeDmg(player.getWeapon().getItemActionValue());
				model.getParty().useWeaponAmmo(player.getWeapon());
				output += "\nThe " + model.getCurrentMonster().getMonsterName() + " took " + 
						player.getWeapon().getItemActionValue() + " damage!\n";

				checkMonsterDeath(event);

				partyMemberTabPane.getSelectionModel().getSelectedItem().setDisable(true);
				boolean playerStillHaveTurn = checkPartyTab();

				if(!model.getParty().getCurrentRoom().getRoomMonster().isEmpty() && !playerStillHaveTurn) {
					System.out.println("Test");
					allMonsterAttack(event);

				}else if(playerStillHaveTurn == true && !model.getParty().getCurrentRoom().getRoomMonster().isEmpty()) {

					combatList.getSelectionModel().clearSelection();
					combatInventoryPane.setVisible(false);
					printHelp(output);
				}
				else {
					if(!model.getLootList().isEmpty()) {
						for(int y = 0; y < model.getLootList().size();y++) {
							output += "\nThe monster drop " + model.getSpecificLoot(y).getItemName() +" on the floor of the room";
						}
						output += "\n--------------------------------------------------";
					}
					combatBox.setVisible(false);
					combatLootBox.setVisible(true);
					setListItemInRoom(model.getLootList());
					combatList.setItems(observList);
					combatLabel.setText("Monster Drop");
					checkWinCondition();
					printHelp(output);
					model.stopMusic();
					model.playMusic("Victory.mp3");
					updatePlayerTab();

				}
			}

		}
	}

	private void checkMonsterDeath(Event event) throws IOException, URISyntaxException {
		if(model.getCurrentMonster().getMonsterCurrentHealth() <= 0) {
			output += "\nThe " + model.getCurrentMonster().getMonsterName() + " slumps over, defeated.";
			output += "\n--------------------------------------------------";
			model.getParty().getCurrentRoom().removeRoomMonster(model.getCurrentMonster());
			model.setLootList(model.getMonsterLootList());
			model.decreaseMonsterAlive();
			if(model.getCurrentMonster().getMonsterType().equalsIgnoreCase("Final Boss")) {
				model.stopMusic();
				Parent secondPane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
				Scene scene = new Scene(secondPane);

				Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
				window.setTitle("Game Maker");
				window.setScene(scene);
				window.show();
			}else if(model.getMonsterAlive() == 0) {
				model.stopMusic();
				Parent secondPane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
				Scene scene = new Scene(secondPane);

				Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
				window.setTitle("Game Maker");
				window.setScene(scene);
				window.show();
			}
		}
	}

	private void checkDupMonster(ArrayList<Monsters> roomMonster) {
		ArrayList<String> list = new ArrayList<String>();
		for(Monsters monster: roomMonster) {
			list.add(monster.getMonsterName());
		}

		Set<String> set = new LinkedHashSet<>();

		for (String str : list) {
			String value = str;

			for (int i = 1; !set.add(value); i++) {
				value = str + " (" +i + ")";
			}
		}

		Iterator<String> iter = set.iterator();
		int index = 0;
		int counter = 1;
		while(iter.hasNext()) {
			roomMonster.get(index).setMonsterName(iter.next());
			index++;
			counter++;
		}


	}

	@FXML
	void combatLootExamineItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getLootList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) {
				output += "\n" + itemList.get(x).toString();
				printHelp(output);
				break;
			}

		}
	}

	@FXML
	void combatLootExitEvent(ActionEvent event) throws URISyntaxException  {
		if(!model.getLootList().isEmpty()) {
			output += "The rest of the item dropped to the floor";
			output += "\n--------------------------------------------------";
			for(Items item : model.getLootList()) {
				model.getParty().getCurrentRoom().addRoomItem(item);
			}
		}
		tabPaneMenu.getSelectionModel().select(actionTab);
		combatList.getSelectionModel().clearSelection();
		changeTab("Action");
		printHelp(output);
		model.stopMusic();
		model.playMusic("Action Menu.mp3");

	}

	@FXML
	void combatLootPickUpEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items>itemList = model.getLootList();

		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) {
				output += "You Picked Up the " + itemList.get(x).getItemName();
				output += "\n--------------------------------------------------";
				model.getParty().addItemToInventory(itemList.get(x));
				model.removeSpecificLoot(itemList.get(x));
				setListInventory(model.getLootList());
				combatList.setItems(observList);
				printHelp(output);
			}

		}




	}

	@FXML
	void combatDefendEvent(ActionEvent event) {
		output += "You block the monster attack\n";
		output += "--------------------------------------------------\n";
		printHelp(output);
	}

	@FXML
	void combatExamineEvent(ActionEvent event) {
		output += "You examine the monster";
		for(int x = 0; x < model.getParty().getCurrentRoom().getRoomMonster().size(); x++) {
			output += "\n" + model.getParty().getCurrentRoom().getRoomMonster().get(x).toString() + "\n";
		}
		output += "--------------------------------------------------\n";
		printHelp(output);
	}

	@FXML
	void combatInventoryEvent(ActionEvent event) {
		combatInventoryPane.setVisible(true);
		combatList.setVisible(true);
		combatLabel.setText("Inventory List"); 
		combatBox.setVisible(false);
		setListInventory(model.getParty().getPartyInventory());
		combatList.setItems(observList);
		combatInventoryVBox.setVisible(true);
		output += "\n";
		for(int x = 0; x < model.getParty().getPartyMember().size(); x++) {
			Players player = model.getParty().getPartyMember().get(x);	
			output += "Player Name: " + player.getPlayerName();
			output += "\nPlayer Health: (" + player.getPlayerCurrentHealth() + "/" + player.getPlayerMaxHealth() + ")";
			output += "\nPlayer Weapon: " +player.getWeapon().getItemName();
			output += "\n\n";
		}
		output += "--------------------------------------------------";
		printHelp(output);
	}

	@FXML
	void combatUseItemEvent(ActionEvent event) throws URISyntaxException, IOException {
		ArrayList<Items> itemList = model.getParty().getPartyInventory();
		Items item = itemList.get(checkInventory("combatList",itemList));

		if(item.getItemType().equalsIgnoreCase("Healing")) {
			mainPane.setDisable(true);
			List<String> choices = new ArrayList<>();

			for(int x = 0; x < model.getParty().getPartyMember().size(); x++) {
				Players player = model.getParty().getPartyMember().get(x);
				choices.add(player.getPlayerName());
			}

			ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
			dialog.setTitle("Player Selection");
			dialog.setHeaderText("Who will you use this on?");
			dialog.setContentText("Select a player:");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				int bound = 0;
				for(int x = 0; x < choices.size(); x++) {
					if(choices.get(x).equalsIgnoreCase(result.get())) {
						bound = x;
						break;
					}
				}
				Players player = model.getParty().getPartyMember().get(bound);

				if(player.getPlayerCurrentHealth() == player.getPlayerMaxHealth()) {
					output += player.getPlayerName() + " is already at full health";
					output += "\n--------------------------------------------------";
					printHelp(output);
					mainPane.setDisable(false);
				}else {

					player.healHealth(item.getItemActionValue());
					model.getParty().removeItemFromInventory(item);

					output += player.getPlayerName() + " recover " + item.getItemActionValue() + " health.";
					output += "\n--------------------------------------------------";	


					mainPane.setDisable(false);
					partyMemberTabPane.getSelectionModel().getSelectedItem().setDisable(true);
					if(checkPartyTab() == false) {
						allMonsterAttack(event);
					}
					combatList.getSelectionModel().clearSelection();
					combatInventoryPane.setVisible(false);
					combatInventoryVBox.setVisible(false);
					combatBox.setVisible(true);

					printHelp(output);


				}
			}
		}else if(item.getItemType().equalsIgnoreCase("Throwable")) {
			mainPane.setDisable(true);
			List<String> choices = new ArrayList<>();

			for(int x = 0; x < model.getParty().getCurrentRoom().getRoomMonster().size(); x++) {
				Monsters monster = model.getParty().getCurrentRoom().getRoomMonster().get(x);
				choices.add(monster.getMonsterName());
			}
			int index = partyMemberTabPane.getSelectionModel().getSelectedIndex();
			Players player = model.getParty().getPartyMember().get(index);
			ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
			dialog.setTitle("Monster Selection");
			dialog.setHeaderText("Who will you use this on?");
			dialog.setContentText("Select a monster:");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				int bound = 0;
				for(int x = 0; x < choices.size(); x++) {
					if(choices.get(x).equalsIgnoreCase(result.get())) {
						bound = x;
						break;
					}
				}
				Monsters monster = model.getParty().getCurrentRoom().getRoomMonster().get(bound);
				model.setCurrentMonster(monster);
				monster.takeDmg(item.getItemActionValue());
				output += player.getPlayerName() + " threw the " + item.getItemName() + " at the " + monster.getMonsterName();
				output += "\nThe monster took " + item.getItemActionValue() + " damage!";
				model.getParty().removeItemFromInventory(item);
				output += "\n--------------------------------------------------";

				checkMonsterDeath(event);




				mainPane.setDisable(false);
				partyMemberTabPane.getSelectionModel().getSelectedItem().setDisable(true);
				if(checkPartyTab() == false) {
					allMonsterAttack(event);
				}
				combatList.getSelectionModel().clearSelection();
				combatInventoryPane.setVisible(false);
				combatInventoryVBox.setVisible(false);
				combatBox.setVisible(true);

				printHelp(output);
			}
		}
		else {
			output += "This item cannot be use right now";
			output += "\n--------------------------------------------------";
			printHelp(output);
		}	


	}


	@FXML
	void combatExamineItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getParty().getPartyInventory();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) {
				output += itemList.get(x).toString();
				printHelp(output);
				break;
			}

		}
	}
	@FXML
	void combatEquipItemEvent(ActionEvent event) {
		ArrayList<Items> itemList = model.getParty().getPartyInventory();
		Items item = itemList.get(checkInventory("combatList",itemList));

		if(item.getItemType().equalsIgnoreCase("Weapon")) 
		{
			mainPane.setDisable(true);
			List<String> choices = new ArrayList<>();

			for(int x = 0; x < model.getParty().getPartyMember().size(); x++) {
				Players player = model.getParty().getPartyMember().get(x);
				choices.add(player.getPlayerName());
			}

			ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
			dialog.setTitle("Player Selection");
			dialog.setHeaderText("Who will equip this?");
			dialog.setContentText("Select a player:");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent())
			{
				int bound = 0;
				for(int x = 0; x < choices.size(); x++) {
					if(choices.get(x).equalsIgnoreCase(result.get())) {
						bound = x;
						break;
					}
				}
				Players player = model.getParty().getPartyMember().get(bound);

				model.getParty().addItemToInventory(player.getWeapon());
				player.equipWeapon(item);
				model.getParty().removeItemFromInventory(item);

				output += player.getPlayerName() + " equipped the " + item.getItemName();
				output += "\n--------------------------------------------------";

				mainPane.setDisable(false);
				partyMemberTabPane.getSelectionModel().getSelectedItem().setDisable(true);
				partyMemberSlot[bound].getPartyMemberWeapon().setText(item.getItemName());
				combatList.getSelectionModel().clearSelection();
				combatInventoryPane.setVisible(false);
				combatInventoryVBox.setVisible(false);
				combatBox.setVisible(true);
				printHelp(output);
			}
			else 
			{
				mainPane.setDisable(false);
			} 	
		}
		else 
		{
			output += "This item is not a weapon";
			output += "\n--------------------------------------------------";
			printHelp(output);
		}	
	}

	@FXML
	void combatInventoryExitEvent(ActionEvent event) {
		combatInventoryVBox.setVisible(false);
		combatInventoryPane.setVisible(false);
		combatBox.setVisible(true);
	}

	@FXML
	void combatRunAwayEvent(ActionEvent event) throws URISyntaxException {
		output += "You ran away from the monster";
		output += "\n--------------------------------------------------";
		for(int x = 0; x < model.getParty().getCurrentRoom().getRoomMonster().size(); x++) {
			model.setCurrentMonster(model.getParty().getCurrentRoom().getRoomMonster().get(x));
			model.getCurrentMonster().setMonsterCurrentHealth(model.getCurrentMonster().getMonsterMaxHealth());
		}
		model.getParty().setCurrentRoom(model.getParty().getPreviousRoom());
		changeTab("Action");
		printHelp(output);
		model.stopMusic();
		model.playMusic("Action Menu.mp3");
	}

	@FXML
	void combatQuitGameEvent(ActionEvent event) throws IOException, URISyntaxException {
		mainPane.setDisable(true);

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("You are exiting to the Main Menu");
		alert.setContentText("Are you sure you wanna exit?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			model.stopMusic();
			Parent secondPane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
			Scene scene = new Scene(secondPane);

			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
			window.setScene(scene);
			window.show();
		} else {
			mainPane.setDisable(false);
		}
	}

	//----------------------------------------------------------------------------

	@FXML
	void puzzleInputNumberEvent(ActionEvent event) throws URISyntaxException {
		if (model.getNextRoomPuzzle().getPuzzleType().equalsIgnoreCase("item")) {
			output += "There's nothing to input.";
			output += "\n--------------------------------------------------";
			printHelp(output);
		}else {
			if(puzzleKeyPadTextArea.getText().equalsIgnoreCase("")) {
				puzzleKeyPadPane.setVisible(true);
				puzzleInventoryPane.setVisible(false);
			}else {
				if(model.getNextRoomPuzzle().getPuzzleSolution().equalsIgnoreCase(puzzleKeyPadTextArea.getText())) {
					output += "You enter the number, and the door opens!";
					output += "\n--------------------------------------------------";
					if(model.getNextRoom().getRoomMonster().isEmpty()) {
						model.getParty().setCurrentRoom(model.getNextRoom());

						output +=  "\nYou moved to the next room";
						output +=  "\n--------------------------------------------------";
						changeTab("Action");
						printHelp(output);

					}
					else{
						model.stopMusic();
						String combatMusic = "Combat 1.mp3";


						model.getParty().setCurrentRoom(model.getNextRoom());

						output += "- - - - -          ENCOUNTER!          - - - - -\n";
						output += "-----------------------------------------------\n";
						for(int x = 0; x < model.getParty().getCurrentRoom().getRoomMonster().size(); x++) {
							output += model.getParty().getCurrentRoom().getRoomMonster().get(x).toString() + "\n";
							if(model.getParty().getCurrentRoom().getRoomMonster().get(x).getMonsterType().equalsIgnoreCase("Final Boss")) {
								combatMusic = "Combat 3.mp3";
							}else if(model.getParty().getCurrentRoom().getRoomMonster().get(x).getMonsterType().equalsIgnoreCase("Mini Boss")) {
								combatMusic = "Combat 2.mp3";
							}
						}	
						model.playCombatMusic("Battle Transition.mp3", combatMusic);
						changeTab("Combat");
						printHelp(output);
					}

				}else {
					output += "You input the wrong number and get shocked for " + model.getNextRoomPuzzle().getPuzzleDamage() + " damage!";
					for(int x = 0; x < model.getParty().getPartyMember().size(); x++) {
						Players player = model.getParty().getPartyMember().get(x);
						player.takeDmg(model.getNextRoomPuzzle().getPuzzleDamage());
						output += "\nPlayer Health: (" + player.getPlayerCurrentHealth() + "/" + player.getPlayerMaxHealth() + ")";
					}

					output += "\n--------------------------------------------------";
					printHelp(output);
					updatePlayerTab();
				}


			}

		}
	}

	@FXML
	void puzzleUseItemEvent(ActionEvent event) {
		if (model.getNextRoomPuzzle().getPuzzleType().equalsIgnoreCase("input")) {
			output += "There's a keypad right next to the door";
			output += "\nmaybe I should use that";
			output += "\n--------------------------------------------------";
			printHelp(output);
		}else {
			puzzleKeyPadPane.setVisible(false);

			if(puzzleList.getSelectionModel().getSelectedItem() == null) {
				puzzleLabel.setText("Use Puzzle Item");
				puzzleInventoryPane.setVisible(true);
				setListInventory(model.getParty().getPartyInventory());
				puzzleList.setItems(observList);

			}else {
				boolean itemFound = false;
				String itemName = "";
				ArrayList<Items>itemList = model.getParty().getPartyInventory();
				for(int x = 0; x < itemList.size(); x++) {
					itemName = itemList.get(x).getItemName()+ " x" + 
							itemList.get(x).getItemAmount();
					if(itemName.equalsIgnoreCase(puzzleList.getSelectionModel().getSelectedItem())
							&& model.getNextRoomPuzzle().getPuzzleSolution().equalsIgnoreCase(itemList.get(x).getItemId())) {
						itemFound = true;
						output += "You inserted the " + itemList.get(x).getItemName() + " and the door open";
						output += "\n--------------------------------------------------";
						model.getParty().setCurrentRoom(model.getNextRoom());
						changeTab("Action");
						printHelp(output);
						break;
					}

				}
				if(!itemFound) {
					output += "Nothing Interesting Happen";
					output += "\n--------------------------------------------------";
					printHelp(output);
				}
			}
		}
	}

	@FXML
	void puzzleHintEvent(ActionEvent event) {
		output += model.getNextRoomPuzzle().getPuzzleHint();
		output += "\n--------------------------------------------------";
		printHelp(output);
	}

	@FXML
	void puzzleExitEvent(ActionEvent event) {
		output += "You couldn't figure out how to solve the puzzle";
		output += "\nYou stumble back into the middle of the room";
		output += "\n--------------------------------------------------";	
		changeTab("Action");
		printHelp(output);
	}

	@FXML
	void keyPadPressed(ActionEvent event) {
		String text = ((Button)event.getSource()).getText();
		String textAreaText = puzzleKeyPadTextArea.getText();
		if(text.equals("Clear")) {
			puzzleKeyPadTextArea.clear();
		}
		else if(text.equals("Dot")) {
			textAreaText += ".";
			puzzleKeyPadTextArea.setText(textAreaText);
		}else {
			textAreaText += text;
			puzzleKeyPadTextArea.setText(textAreaText);
		}
	}

	void setListDirection(HashMap<String, ?> list) {
		observList.clear();
		Set set = list.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) { 
			Map.Entry mEntry = (Map.Entry)iterator.next();
			observList.add((String) mEntry.getKey());
		}

	}

	void setListItemInRoom(ArrayList<Items> item) {
		observList.clear();
		for(int x = 0; x < item.size(); x++) {
			observList.add(item.get(x).getItemName() + " x" + item.get(x).getItemAmount());
		}
	}

	void setListInventory(ArrayList<Items> item) {
		observList.clear();
		for(int x = 0; x < item.size(); x++) {
			observList.add(item.get(x).getItemName() + " x" + item.get(x).getItemAmount());
		}
	}

	void setListMonster(ArrayList<Monsters> monster) {
		observList.clear();
		for(int x = 0; x < monster.size(); x++) {
			observList.add(monster.get(x).getMonsterName() + " - Health:" + 
					monster.get(x).getMonsterCurrentHealth() + "/" + monster.get(x).getMonsterMaxHealth());
		}
	}

	void setListSave() {
		observList.clear();

		for(int x = 1; x <= 23; x++) 
		{
			if(model.getSaveList().get(x) != null) 
			{
				SaveData data = model.getSaveList().get(x);
				observList.add(x + "." + data.getGameFolder()
				+ " Time: " + data.getParty().getGameTime()
				+ " Room: " + data.getParty().getCurrentRoom().getRoomFloor());
			}
			else 
			{
				observList.add(x + ". Empty");
			}
		}
	}

	public void allMonsterAttack(Event event) throws URISyntaxException, IOException {
		for(int z = 0; z < model.getParty().getCurrentRoom().getRoomMonster().size(); z++) {

			model.setCurrentMonster(model.getParty().getCurrentRoom().getRoomMonster().get(z));
			attackPlayer(event);
		}
		output += "\n--------------------------------------------------";
		updatePlayerTab();
		combatList.getSelectionModel().clearSelection();
		combatInventoryPane.setVisible(false);
		printHelp(output);
	}

	public void attackPlayer(Event event) throws URISyntaxException, IOException {
		Random r = new Random();
		int Low = 0;
		int High = model.getParty().getPartyMember().size();
		int Result = r.nextInt(High-Low) + Low;

		Players player = model.getParty().getPartyMember().get(Result);
		output += "\nThe " + model.getCurrentMonster().getMonsterName() + " attacked "+ player.getPlayerName() + "!";
		int mosnterDamage = model.getCurrentMonster().attackPlayer();
		if(mosnterDamage == 0) {
			output += "\nThe attack missed!\n";
		}else {
			output += "\n" + player.getPlayerName() + " took " + mosnterDamage + " damage!";
			player.takeDmg(mosnterDamage);
			output += "\nHealth: (" + player.getPlayerCurrentHealth() + "/" + player.getPlayerMaxHealth() + ")\n";
			if(player.getPlayerCurrentHealth() <= 0) {
				output += "\n" + player.getPlayerName() + " died!\n";
				model.playGameSoundEffect(player.getPlayerDeath());
				model.getParty().removePartyMember(player);
				if(model.getParty().getPartyMember().isEmpty()) {
					model.stopMusic();

					Parent secondPane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
					Scene scene = new Scene(secondPane);

					Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
					window.setScene(scene);
					window.show();
				}
			}
		}

	}


	private void checkWinCondition() {
		if(model.getMonsterAlive() == 0) {	
			output += "\n\n--------------------------------------------------";
			output += "\n||||||||||||||||||||||||||||||||||||||||||||||||||";
			output += "\n=====        You Win   -    Vicotry        =====";
			output += "\n||||||||||||||||||||||||||||||||||||||||||||||||||";
			output += "\n--------------------------------------------------\n";
			printHelp(output);
		}
	}



	private void changeTab(String tab) {
		switch(tab.toLowerCase()) {
		case "action":
			if(!combatTab.isDisable()) {
				actionTab.setDisable(false);
				combatTab.setDisable(true);
			}else if(!puzzleTab.isDisable()) {
				actionTab.setDisable(false);
				puzzleTab.setDisable(true);
			}
			tabPaneMenu.getSelectionModel().select(actionTab);
			actionNeutralBox.setVisible(true);
			actionInventoryPane.setVisible(false);
			actionInventoryBox.setVisible(false);
			actionLootBox.setVisible(false);
			actionList.setVisible(false);
			//actionBackground.setImage(pictureList.get(model.getParty().getCurrentRoom().getRoomBackground()));
			actionList.getSelectionModel().clearSelection();
			break;
		case "combat":
			actionTab.setDisable(true);
			combatTab.setDisable(false);
			combatBox.setVisible(true);
			tabPaneMenu.getSelectionModel().select(combatTab);
			combatInventoryPane.setVisible(false);
			combatLootBox.setVisible(false);
			//combatBackground.setImage(pictureList.get(model.getParty().getCurrentRoom().getRoomBackground()));
			combatList.getSelectionModel().clearSelection();
			break;
		case "puzzle":
			actionTab.setDisable(true);
			puzzleTab.setDisable(false);
			tabPaneMenu.getSelectionModel().select(puzzleTab);
			puzzleBox.setVisible(true);
			puzzleKeyPadPane.setVisible(false);
			puzzleInventoryPane.setVisible(false);
			puzzleList.getSelectionModel().clearSelection();
			puzzleKeyPadTextArea.clear();
			break;
		}
	}

	private void printHelp(String textWord) {
		Timeline timelines = new Timeline();
		final IntegerProperty t = new SimpleIntegerProperty(1);
		final IntegerProperty d = new SimpleIntegerProperty(0);
		KeyFrame keyFrame = new KeyFrame(
				Duration.seconds(.009),
				event -> {
					if(t.get() > textWord.length()) {
						timelines.stop();
					} else {
						consoleTextArea.appendText(textWord.substring(d.get(), t.get()));
						t.set(t.get() + 1);
						d.set(d.get() + 1);
					}
				});

		timelines.statusProperty().addListener((obs, oldStatus, newStatus) -> 
		tabPaneMenu.getSelectionModel().getSelectedItem().setDisable(timelines.getStatus() == Status.RUNNING));
		timelines.statusProperty().addListener((obs, oldStatus, newStatus) -> 
		partyMemberTabPane.setDisable(timelines.getStatus() == Status.RUNNING));
		if(timelines.getStatus() == Status.STOPPED) {
			output = "\n";
		}
		timelines.getKeyFrames().add(keyFrame);
		timelines.setCycleCount(Animation.INDEFINITE);
		timelines.play();

	}

	private void updatePlayerTab() {
		int x = 0;
		try {
			for(x = 0; x < partyMemberSlot.length; x++) {
				Players player = model.getParty().getPartyMember().get(x);

				System.out.println(player.getPlayerIcon());
				partyMemberSlot[x].getPartyMemberTab().setDisable(false);
				partyMemberSlot[x].getPartyMemberTab().setText(player.getPlayerName());
				partyMemberSlot[x].getPartyMemberImage().setImage(model.getPictureList().get(player.getPlayerIcon()));
				partyMemberSlot[x].getPartyMemberWeapon().setText(player.getWeapon().getItemName());
				partyMemberSlot[x].getPartyMemberHealth().setText("(" + player.getPlayerCurrentHealth() + "/" + player.getPlayerMaxHealth() + ")");
				partyMemberSlot[x].getPartyMemberHealthBar().setProgress((player.getPlayerCurrentHealth()/100));
			}
			model.playGameSoundEffect(partyMemberSlot[0].getPlayerMember().getPlayerSelect());
			partyMemberTabPane.getSelectionModel().select(partyMemberSlot[0].getPartyMemberTab());
		}
		catch(Exception e) {
			partyMemberSlot[x].getPartyMemberTab().setDisable(true);
			partyMemberSlot[x].getPartyMemberTab().setText("Empty");
			partyMemberSlot[x].getPartyMemberWeapon().setText("Null");
			partyMemberSlot[x].getPartyMemberHealth().setText("Null");
			partyMemberSlot[x].getPartyMemberHealthBar().setProgress(0);
		}

	}


	private Boolean checkPartyTab(){
		for(int x = 0; x < model.getParty().getPartyMember().size(); x++) {
			if(!partyMemberSlot[x].getPartyMemberTab().isDisable()) {
				model.playGameSoundEffect(partyMemberSlot[x].getPlayerMember().getPlayerSelect());
				partyMemberTabPane.getSelectionModel().select(partyMemberSlot[x].getPartyMemberTab());
				return true;
			}	
		}
		return false;
	}

	class partyMemberSelection{

		Players playerMember;
		Tab partyMemberTab;
		ImageView partyMemberImage;
		Label partyMemberHealth;
		Label partyMemberWeapon;
		ProgressBar partyMemberHealthBar;

		public partyMemberSelection(Tab partyMemberTab, ImageView partyMemberImage, Label partyMemberHealth,
				Label partyMemberWeapon, ProgressBar partyMemberHealthBar) {
			this.partyMemberTab = partyMemberTab;
			this.partyMemberImage = partyMemberImage;
			this.partyMemberHealth = partyMemberHealth;
			this.partyMemberWeapon = partyMemberWeapon;
			this.partyMemberHealthBar = partyMemberHealthBar;
		}

		public Players getPlayerMember() {
			return playerMember;
		}
		public void setPlayerMember(Players playerMember) {
			this.playerMember = playerMember;
		}

		public Tab getPartyMemberTab() {
			return partyMemberTab;
		}
		public void setPartyMemberTab(Tab partyMemberTab) {
			this.partyMemberTab = partyMemberTab;
		}
		public ImageView getPartyMemberImage() {
			return partyMemberImage;
		}
		public void setPartyMemberImage(ImageView partyMemberImage) {
			this.partyMemberImage = partyMemberImage;
		}
		public Label getPartyMemberHealth() {
			return partyMemberHealth;
		}
		public void setPartyMemberHealth(Label partyMemberHealth) {
			this.partyMemberHealth = partyMemberHealth;
		}
		public Label getPartyMemberWeapon() {
			return partyMemberWeapon;
		}
		public void setPartyMemberWeapon(Label partyMemberWeapon) {
			this.partyMemberWeapon = partyMemberWeapon;
		}
		public ProgressBar getPartyMemberHealthBar() {
			return partyMemberHealthBar;
		}
		public void setPartyMemberHealthBar(ProgressBar partyMemberHealthBar) {
			this.partyMemberHealthBar = partyMemberHealthBar;
		}

	}










}
