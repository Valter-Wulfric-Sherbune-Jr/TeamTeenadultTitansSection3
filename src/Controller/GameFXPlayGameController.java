package Controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import GameObject.Items;
import GameObject.Monsters;
import Model.GameFXModel;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

	private String output;

	private ObservableList<String> observList = FXCollections.observableArrayList();

	GameFXModel model = new GameFXModel();

	//----------------------------------------------------------------------------

	public void initialize() throws URISyntaxException {
		ArrayList<ImageView> combatImage = new ArrayList<ImageView>();
		
		
		model.playMusic("Action Menu.mp3");
		model.setGameFolder("Hydra Game File");
		model.loadGamePictureFolder();
		model.loadGameFolder("Item");
		model.loadGameFolder("Monster");
		model.loadGameFolder("Puzzle");
		model.loadGameFolder("Room");	
		model.makeNewPlayer("Vector");

		puzzleKeyPadTextArea.setEditable(false);
		consoleTextArea.setEditable(false);
		actionBackground.setImage(model.getPlayer().getCurrentRoom().getRoomBackground());
		actionBackground.setVisible(true);
		actionBackground.setDisable(false);


		printHelp(model.getPlayer().getCurrentRoom().toString());
	}

	//----------------------------------------------------------------------------

	@FXML
	void actionMoveEvent(ActionEvent event) throws URISyntaxException {
		if(actionList.getSelectionModel().getSelectedItem() == null) {
			actionInventoryPane.setVisible(true);
			actionList.setVisible(true);
			actionLabel.setText("Room Exit");
			setListDirection(model.getPlayer().getCurrentRoom().getRoomNavigationList());
			actionList.setItems(observList);
		}else {
			model.setNextRoom(actionList.getSelectionModel().getSelectedItem());
			if(model.getNextRoomPuzzle() != null) {
				changeTab("Puzzle");
			}
			else if(model.getNextRoom().getRoomMonster().isEmpty()) {
				model.getPlayer().setCurrentRoom(model.getNextRoom());

				output +=  "You moved to the next room";
				output +=  "\n--------------------------------------------------";
				changeTab("Action");
				printHelp(output);

			}
			else{
				model.stopMusic();
				String combatMusic = "Combat 1.mp3";
				
				
				model.getPlayer().setCurrentRoom(model.getNextRoom());
				
				output += "- - - - -          ENCOUNTER!          - - - - -\n";
				output += "-----------------------------------------------\n";
				for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
					output += model.getPlayer().getCurrentRoom().getRoomMonster().get(x).toString() + "\n";
					if(model.getPlayer().getCurrentRoom().getRoomMonster().get(x).getMonsterType().equalsIgnoreCase("Final Boss")) {
						combatMusic = "Combat 3.mp3";
					}else if(model.getPlayer().getCurrentRoom().getRoomMonster().get(x).getMonsterType().equalsIgnoreCase("Mini Boss")) {
						combatMusic = "Combat 2.mp3";
					}
				}	
				model.playCombatMusic("Battle Transition.mp3", combatMusic);
				changeTab("Combat");
				printHelp(output);
			}
		}
	}

	@FXML
	void actionInventoryEvent(ActionEvent event) {
		actionInventoryPane.setVisible(true);
		actionList.setVisible(true);
		actionLabel.setText("Inventory List");
		setListInventory(model.getPlayer().getInventoryList());
		actionList.setItems(observList);
		actionInventoryBox.setVisible(true);
		actionNeutralBox.setVisible(false);
		output += "Player Name: " + model.getPlayer().getPlayerName();
		output += "\nPlayer Health: (" + model.getPlayer().getPlayerCurrentHealth() + "/" + model.getPlayer().getPlayerMaxHealth() + ")";
		output += "\nPlayer Weapon: " +model.getPlayer().getWeapon().getItemName();
		output += "\n--------------------------------------------------";
		printHelp(output);
	}

	@FXML
	void actionExamineItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getInventoryList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) {
				output += itemList.get(x).toString();
				printHelp(output);
				break;
			}

		}
	}




	@FXML
	void actionDropItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getInventoryList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) {
				output += "You drop the " + itemList.get(x).getItemName();
				output += "\n--------------------------------------------------";
				model.getPlayer().getCurrentRoom().addRoomItem(itemList.get(x));
				model.getPlayer().removeItemFromInventory(itemList.get(x));
				setListInventory(itemList);
				actionList.setItems(observList);
				printHelp(output);
			}

		}
	}

	@FXML
	void actionUseItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getInventoryList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) {
				if(itemList.get(x).getItemType().equalsIgnoreCase("Healing") && model.getPlayer().getPlayerCurrentHealth() != 100) {
					model.getPlayer().healHealth(itemList.get(x).getItemActionValue());
					output += "You recover " + itemList.get(x).getItemActionValue() + " health.";
					output += "\n--------------------------------------------------";
					model.getPlayer().removeItemFromInventory(itemList.get(x));
					setListInventory(itemList);
					actionList.setItems(observList);
					printHelp(output);
				}else {
					output += "This item cannot be use right now";
					output += "\n--------------------------------------------------";
					printHelp(output);
				}	
			}

		}
	}

	@FXML
	void actionEquipItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getInventoryList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) {
				output += "You equipped the " + itemList.get(x).getItemName();
				model.getPlayer().equipWeapon(itemList.get(x));
				setListInventory(itemList);
				actionList.setItems(observList);
				printHelp(output);
			}

		}
	}

	@FXML
	void actionExitInventoryEvent(ActionEvent event) {
		actionInventoryBox.setVisible(false);
		actionInventoryPane.setVisible(false);
		actionNeutralBox.setVisible(true);
	}

	@FXML
	void actionExamineRoomEvent(ActionEvent event) {
		actionNeutralBox.setVisible(false);
		actionLootBox.setVisible(true);

		actionInventoryPane.setVisible(true);

		actionList.setVisible(true);
		output += model.getPlayer().getCurrentRoom().toString();
		actionLabel.setText("Item on Ground");
		setListInventory(model.getPlayer().getCurrentRoom().getRoomItem());
		actionList.setItems(observList);
		printHelp(output);

	}

	@FXML
	void actionPickUpItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getCurrentRoom().getRoomItem();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) {
				output += "You Picked Up the " + itemList.get(x).getItemName();
				output += "\n--------------------------------------------------";
				model.getPlayer().addItemToInventory(itemList.get(x));
				model.getPlayer().getCurrentRoom().removeRoomItemId(itemList.get(x));
				setListInventory(model.getPlayer().getCurrentRoom().getRoomItem());
				actionList.setItems(observList);
				printHelp(output);
			}

		}
	}

	@FXML
	void actionExamineLootEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getCurrentRoom().getRoomItem();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) {
				output +=  itemList.get(x).toString();
				printHelp(output);
			}

		}
	}

	@FXML
	void actionLootExitEvent(ActionEvent event) throws URISyntaxException  {
		actionLootBox.setVisible(false);
		actionNeutralBox.setVisible(true);
		actionInventoryPane.setVisible(false);
		model.stopMusic();
		model.playMusic("Action Menu.mp3");



	}

	@FXML
	void actionSaveGameEvent(ActionEvent event) {
		if(actionList.getSelectionModel().getSelectedItem() == null) {
		actionInventoryPane.setVisible(true);
		model.setSaveList();
		for(int x = 1; x <= 10; x++) {
			
			if(model.getSaveList().get(x) != null) {
				System.out.println(model.getSaveList().get(x).getPlayerData().getCurrentRoom().getRoomId());
				observList.add(x + ". Name: " + model.getSaveList().get(x).getPlayerData().getPlayerName()
						+ " Time: " + model.getSaveList().get(x).getPlayerData().getGameTime()
						+ " Room: " + model.getSaveList().get(x).getPlayerData().getCurrentRoom().getRoomId());

			}
			else {
				observList.add(x + ". Empty");
			}
		}
		
		actionLabel.setText("Save Data");
		actionList.setItems(observList);
		}else {
			model.setSaveList();
			model.setSaveData(actionList.getSelectionModel().getSelectedIndex()+1);
			model.saveGameData(model.getSaveData());
			output += "The game has been saved!\n";
			output += "--------------------------------------------------";
			printHelp(output);
			
			for(int x = 1; x <= 10; x++) {
				if(model.getSaveList().get(x) != null) {
					observList.add(x + ". Name: " + model.getSaveList().get(x).getPlayerData().getPlayerName()
							+ " Time: " + model.getSaveList().get(x).getPlayerData().getGameTime()
							+ " Room: " + model.getSaveList().get(x).getPlayerData().getCurrentRoom().getRoomId());

				}
				else {
					observList.add(x + ". Empty");
				}
			}
			
			actionLabel.setText("Save Data");
			actionList.setItems(observList);
			
		}
	}

	@FXML
	void actionQuitGameEvent(ActionEvent event) throws IOException, URISyntaxException {
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
	void combatAttackEvent(ActionEvent event) throws URISyntaxException, IOException {
		if(combatList.getSelectionModel().getSelectedItem() == null) {
			combatInventoryPane.setVisible(true);
			combatList.setVisible(true);
			combatLabel.setText("Monsters");
			setListMonster(model.getPlayer().getCurrentRoom().getRoomMonster());
			combatList.setItems(observList);
		}else {
			String monsterName = "";
			ArrayList<Monsters> monsterList = model.getPlayer().getCurrentRoom().getRoomMonster();
			for(int x = 0; x < monsterList.size(); x++) {
				monsterName = monsterList.get(x).getMonsterName() + " - Health:" + 
						monsterList.get(x).getMonsterCurrentHealth() + "/" + monsterList.get(x).getMonsterMaxHealth();
				if(monsterName.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) {
					model.setCurrentMonster(monsterList.get(x));

				}

			}


			if(model.getPlayer().getWeaponAmmo(model.getPlayer().getWeapon()) == 0) {
				output += "\nYou don't have any ammo for your weapon!\n";
				output += "\n--------------------------------------------------";
				printHelp(output);
			}

			output += "\nYou attack the " + model.getCurrentMonster().getMonsterName() + "!";
			model.getCurrentMonster().takeDmg(model.getPlayer().getWeapon().getItemActionValue());
			model.getPlayer().useWeaponAmmo(model.getPlayer().getWeapon());
			output += "\nThe " + model.getCurrentMonster().getMonsterName() + " took " + 
					model.getPlayer().getWeapon().getItemActionValue() + " damage!\n";

			if(model.getCurrentMonster().getMonsterCurrentHealth() <= 0) {
				output += "\nThe " + model.getCurrentMonster().getMonsterName() + " slumps over, defeated.";
				output += "\n--------------------------------------------------";
				model.getPlayer().getCurrentRoom().removeRoomMonster(model.getCurrentMonster());
				model.setLootList(model.getMonsterLootList());

				model.decreaseMonsterAlive();


			}

			if(!model.getPlayer().getCurrentRoom().getRoomMonster().isEmpty()) {

				for(int z = 0; z < model.getPlayer().getCurrentRoom().getRoomMonster().size(); z++) {
					model.setCurrentMonster(model.getPlayer().getCurrentRoom().getRoomMonster().get(z));
					attackPlayer(event);
				}
				combatList.getSelectionModel().clearSelection();
				combatInventoryPane.setVisible(false);
				printHelp(output);

			}else {
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

			}

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
				model.getPlayer().getCurrentRoom().addRoomItem(item);
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
				model.getPlayer().addItemToInventory(itemList.get(x));
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
		for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
			output += "\n" + model.getPlayer().getCurrentRoom().getRoomMonster().get(x).toString() + "\n";
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
		setListInventory(model.getPlayer().getInventoryList());
		combatList.setItems(observList);
		combatInventoryVBox.setVisible(true);
		output += "Player Name: " + model.getPlayer().getPlayerName();
		output += "\nPlayer Health: (" + model.getPlayer().getPlayerCurrentHealth() + "/" + model.getPlayer().getPlayerMaxHealth() + ")";
		output += "\nPlayer Weapon: " +model.getPlayer().getWeapon().getItemName();
		output += "\n--------------------------------------------------";
		printHelp(output);
	}
	
	@FXML
    void combatUseItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getInventoryList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) {
				if(itemList.get(x).getItemType().equalsIgnoreCase("Healing") && model.getPlayer().getPlayerCurrentHealth() != 100) {
					model.getPlayer().healHealth(itemList.get(x).getItemActionValue());
					output += "You recover " + itemList.get(x).getItemActionValue() + " health.";
					output += "\n--------------------------------------------------";
					model.getPlayer().removeItemFromInventory(itemList.get(x));
					setListInventory(itemList);
					combatList.setItems(observList);
					printHelp(output);
				}else if(itemList.get(x).getItemType().equalsIgnoreCase("Throwable")) {
					output += "This item function has not been implemented yet";
					output += "\n--------------------------------------------------";
					printHelp(output);
				}
				else {
					output += "This item cannot be use right now";
					output += "\n--------------------------------------------------";
					printHelp(output);
				}	
			}

		}
    }
	
	@FXML
    void combatExamineItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getInventoryList();
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
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getInventoryList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) {
				output += "You equipped the " + itemList.get(x).getItemName();
				model.getPlayer().equipWeapon(itemList.get(x));
				setListInventory(itemList);
				actionList.setItems(observList);
				printHelp(output);
			}

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
		for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
			model.setCurrentMonster(model.getPlayer().getCurrentRoom().getRoomMonster().get(x));
			model.getCurrentMonster().setMonsterCurrentHealth(model.getCurrentMonster().getMonsterMaxHealth());
		}
		model.getPlayer().setCurrentRoom(model.getPlayer().getPreviousRoom());
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
						model.getPlayer().setCurrentRoom(model.getNextRoom());

						output +=  "\nYou moved to the next room";
						output +=  "\n--------------------------------------------------";
						changeTab("Action");
						printHelp(output);

					}
					else{
						model.stopMusic();
						String combatMusic = "Combat 1.mp3";
						
						
						model.getPlayer().setCurrentRoom(model.getNextRoom());
						
						output += "- - - - -          ENCOUNTER!          - - - - -\n";
						output += "-----------------------------------------------\n";
						for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
							output += model.getPlayer().getCurrentRoom().getRoomMonster().get(x).toString() + "\n";
							if(model.getPlayer().getCurrentRoom().getRoomMonster().get(x).getMonsterType().equalsIgnoreCase("Final Boss")) {
								combatMusic = "Combat 3.mp3";
							}else if(model.getPlayer().getCurrentRoom().getRoomMonster().get(x).getMonsterType().equalsIgnoreCase("Mini Boss")) {
								combatMusic = "Combat 2.mp3";
							}
						}	
						model.playCombatMusic("Battle Transition.mp3", combatMusic);
						changeTab("Combat");
						printHelp(output);
					}

				}else {
					model.getPlayer().takeDmg(model.getNextRoomPuzzle().getPuzzleDamage());
					output += "You input the wrong number and get shocked for " + model.getNextRoomPuzzle().getPuzzleDamage() + " damage!";
					output += "\nPlayer Health: (" + model.getPlayer().getPlayerCurrentHealth() + "/" + model.getPlayer().getPlayerMaxHealth() + ")";
					output += "\n--------------------------------------------------";
					printHelp(output);
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
				setListInventory(model.getPlayer().getInventoryList());
				puzzleList.setItems(observList);

			}else {
				boolean itemFound = false;
				String itemName = "";
				ArrayList<Items>itemList = model.getPlayer().getInventoryList();
				for(int x = 0; x < itemList.size(); x++) {
					itemName = itemList.get(x).getItemName()+ " x" + 
							itemList.get(x).getItemAmount();
					if(itemName.equalsIgnoreCase(puzzleList.getSelectionModel().getSelectedItem())
							&& model.getNextRoomPuzzle().getPuzzleSolution().equalsIgnoreCase(itemList.get(x).getItemId())) {
						itemFound = true;
						output += "You inserted the " + itemList.get(x).getItemName() + " and the door open";
						output += "\n--------------------------------------------------";
						model.getPlayer().setCurrentRoom(model.getNextRoom());
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
			System.out.println(x);
			System.out.println(item.size() + " size");
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

	public void attackPlayer(Event event) throws URISyntaxException, IOException {
		output += "\nThe " + model.getCurrentMonster().getMonsterName() + " attacks!";
		int mosnterDamage = model.getCurrentMonster().attackPlayer();
		if(mosnterDamage == 0) {
			output += "\nThe attack missed!";
		}else {
			output += "\nYou took " + mosnterDamage + " damage!";
			model.getPlayer().takeDmg(mosnterDamage);
			output += "\nHealth: (" + model.getPlayer().getPlayerCurrentHealth() + "/" + model.getPlayer().getPlayerMaxHealth() + ")";
			output += "\n--------------------------------------------------";
			checkPlayerDeath(event);
		}



	}

	private void checkPlayerDeath(Event event) throws URISyntaxException, IOException {
		if(model.getPlayer().getPlayerCurrentHealth() <= 0) {
			model.stopMusic();
			
			Parent secondPane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
			Scene scene = new Scene(secondPane);

			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());  
			window.setScene(scene);
			window.show();
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
			actionBackground.setImage(model.getPlayer().getCurrentRoom().getRoomBackground());
			actionList.getSelectionModel().clearSelection();
			break;
		case "combat":
			actionTab.setDisable(true);
			combatTab.setDisable(false);
			combatBox.setVisible(true);
			tabPaneMenu.getSelectionModel().select(combatTab);
			combatInventoryPane.setVisible(false);
			combatLootBox.setVisible(false);
			combatBackground.setImage(model.getPlayer().getCurrentRoom().getRoomBackground());
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
		final IntegerProperty t = new SimpleIntegerProperty(0);
		KeyFrame keyFrame = new KeyFrame(
				Duration.seconds(.009),
				event -> {
					if(t.get() > textWord.length()) {
						timelines.stop();
					} else {
						consoleTextArea.setText(textWord.substring(0, t.get()));
						t.set(t.get() + 1);
					}
				});
		
		timelines.statusProperty().addListener((obs, oldStatus, newStatus) -> 
		//timelines.Status.RUNNING
		tabPaneMenu.getSelectionModel().getSelectedItem().setDisable(timelines.getStatus() == Status.RUNNING));
		timelines.getKeyFrames().add(keyFrame);
		timelines.setCycleCount(Animation.INDEFINITE);
		timelines.play();
	}










}
