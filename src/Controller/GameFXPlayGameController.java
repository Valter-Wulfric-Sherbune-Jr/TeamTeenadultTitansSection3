package Controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import GameObject.Items;
import GameObject.Monsters;
import Model.GameFXModel;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameFXPlayGameController {

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
		model.setMainState("Action Menu");
		//model.playMusic();
		model.setGameFolder("Hydra Game File");
		model.loadGameFolder("Item");
		model.loadGameFolder("Monster");
		model.loadGameFolder("Puzzle");
		model.loadGameFolder("Room");
		model.makeNewPlayer("Vector");


		printTextConsole(model.getPlayer().getCurrentRoom().toString());
	}

	//----------------------------------------------------------------------------

	@FXML
	void actionMoveEvent(ActionEvent event) {
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

				String movedRoom = "";
				output +=  "\nYou moved to the next room";
				output +=  "\n--------------------------------------------------";
				printTextConsole(output);
				changeTab("Action");

			}
			else{
				model.getPlayer().setCurrentRoom(model.getNextRoom());
				output += "\n- - - - -          ENCOUNTER!          - - - - -\n";
				output += "-----------------------------------------------\n";
				for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
					output += model.getPlayer().getCurrentRoom().getRoomMonster().get(x).toString() + "\n";
				}
				printTextConsole(output);
				changeTab("Combat");
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
		printTextConsole("\nPlayer Name: " + model.getPlayer().getPlayerName());
		printTextConsole("\nPlayer Health: (" + model.getPlayer().getPlayerCurrentHealth() + "/" + model.getPlayer().getPlayerMaxHealth() + ")");
		printTextConsole("\nPlayer Weapon: " +model.getPlayer().getWeapon().getItemName());
		printTextConsole("\n--------------------------------------------------");
	}

	@FXML
	void actionExamineItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getInventoryList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) {
				printTextConsole("\n" + itemList.get(x).toString());
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
				printTextConsole("\nYou drop the " + itemList.get(x).getItemName());
				printTextConsole("\n--------------------------------------------------");
				model.getPlayer().getCurrentRoom().addRoomItem(itemList.get(x));
				model.getPlayer().removeItemFromInventory(itemList.get(x));
				setListInventory(itemList);
				actionList.setItems(observList);
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
					printTextConsole("\nYou recover " + itemList.get(x).getItemActionValue() + " health.");
					printTextConsole("\n--------------------------------------------------");
					model.getPlayer().removeItemFromInventory(itemList.get(x));
					setListInventory(itemList);
					actionList.setItems(observList);
				}else {
					printTextConsole("\nThis item cannot be use right now");
					printTextConsole("\n--------------------------------------------------");
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
				printTextConsole("\nYou equipped the " + itemList.get(x).getItemName());
				model.getPlayer().equipWeapon(itemList.get(x));
				setListInventory(itemList);
				actionList.setItems(observList);
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
		printTextConsole("\n" + model.getPlayer().getCurrentRoom().toString());
		actionLabel.setText("Item on Ground");
		setListInventory(model.getPlayer().getCurrentRoom().getRoomItem());
		actionList.setItems(observList);

	}

	@FXML
	void actionPickUpItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getCurrentRoom().getRoomItem();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) {
				printTextConsole("\nYou Picked Up the " + itemList.get(x).getItemName());
				printTextConsole("\n--------------------------------------------------");
				model.getPlayer().addItemToInventory(itemList.get(x));
				model.getPlayer().getCurrentRoom().removeRoomItemId(itemList.get(x));
				setListInventory(model.getPlayer().getCurrentRoom().getRoomItem());
				actionList.setItems(observList);
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
				printTextConsole("\n" + itemList.get(x).toString());
			}

		}
	}

	@FXML
	void actionLootExitEvent(ActionEvent event) {
		actionLootBox.setVisible(false);
		actionNeutralBox.setVisible(true);
		actionInventoryPane.setVisible(false);



	}

	@FXML
	void actionSaveGameEvent(ActionEvent event) {

	}

	@FXML
	void actionQuitGameEvent(ActionEvent event) throws IOException {
		mainPane.setDisable(true);

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("You are exiting to the Main Menu");
		alert.setContentText("Are you sure you wanna exit?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
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
	void combatAttackEvent(ActionEvent event) {
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
				printTextConsole("\nYou don't have any ammo for your weapon!\n");
				printTextConsole("\n--------------------------------------------------");
			}

			printTextConsole("\nYou attack the " + model.getCurrentMonster().getMonsterName() + "!");
			model.getCurrentMonster().takeDmg(model.getPlayer().getWeapon().getItemActionValue());
			model.getPlayer().useWeaponAmmo(model.getPlayer().getWeapon());
			printTextConsole("\nThe " + model.getCurrentMonster().getMonsterName() + " took " + 
					model.getPlayer().getWeapon().getItemActionValue() + " damage!\n");

			if(model.getCurrentMonster().getMonsterCurrentHealth() <= 0) {
				printTextConsole("\nThe " + model.getCurrentMonster().getMonsterName() + " slumps over, defeated.");
				printTextConsole("\n--------------------------------------------------");
				model.getPlayer().getCurrentRoom().removeRoomMonster(model.getCurrentMonster());
				model.setLootList(model.getMonsterLootList());
				if(!model.getLootList().isEmpty()) {
					for(int y = 0; y < model.getLootList().size();y++) {
						printTextConsole("\nThe monster drop " + model.getSpecificLoot(y).getItemName() +" on the floor of the room");
					}
					printTextConsole("\n--------------------------------------------------");
				}
				model.decreaseMonsterAlive();


			}

			if(!model.getPlayer().getCurrentRoom().getRoomMonster().isEmpty()) {

				for(int z = 0; z < model.getPlayer().getCurrentRoom().getRoomMonster().size(); z++) {
					model.setCurrentMonster(model.getPlayer().getCurrentRoom().getRoomMonster().get(z));
					attackPlayer();
				}
				combatList.getSelectionModel().clearSelection();
				combatInventoryPane.setVisible(false);	
			}else {

				combatBox.setVisible(false);
				combatLootBox.setVisible(true);
				setListItemInRoom(model.getLootList());
				combatList.setItems(observList);
				combatLabel.setText("Monster Drop");
				checkWinCondition();
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
				printTextConsole("\n" + itemList.get(x).toString());
			}

		}
	}

	@FXML
	void combatLootExitEvent(ActionEvent event) {
		if(!model.getLootList().isEmpty()) {
			printTextConsole("\nThe rest of the item dropped to the floor");
			printTextConsole("\n--------------------------------------------------");
			for(Items item : model.getLootList()) {
				model.getPlayer().getCurrentRoom().addRoomItem(item);
			}
		}
		tabPaneMenu.getSelectionModel().select(actionTab);
		combatList.getSelectionModel().clearSelection();
		changeTab("Action");

	}

	@FXML
	void combatLootPickUpEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items>itemList = model.getLootList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) {
				printTextConsole("\nYou Picked Up the " + itemList.get(x).getItemName());
				printTextConsole("\n--------------------------------------------------");
				model.getPlayer().addItemToInventory(itemList.get(x));
				model.removeSpecificLoot(itemList.get(x));
				setListInventory(model.getLootList());
				combatList.setItems(observList);
			}

		}




	}

	@FXML
	void combatDefendEvent(ActionEvent event) {

	}

	@FXML
	void combatExamineEvent(ActionEvent event) {
		printTextConsole("\nYou examine the monster");
		for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
			printTextConsole("\n" + model.getPlayer().getCurrentRoom().getRoomMonster().get(x).toString() + "\n");
		}
		printTextConsole("--------------------------------------------------\n");
	}

	@FXML
	void combatInventoryEvent(ActionEvent event) {

	}

	@FXML
	void combatRunAwayEvent(ActionEvent event) {
		printTextConsole("\nYou ran away from the monster");
		printTextConsole("\n--------------------------------------------------");
		for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
			model.setCurrentMonster(model.getPlayer().getCurrentRoom().getRoomMonster().get(x));
			model.getCurrentMonster().setMonsterCurrentHealth(model.getCurrentMonster().getMonsterMaxHealth());
		}
		model.getPlayer().setCurrentRoom(model.getPlayer().getPreviousRoom());
		changeTab("Action");
	}

	@FXML
	void combatQuitGameEvent(ActionEvent event) {

	}

	//----------------------------------------------------------------------------

	@FXML
	void puzzleInputNumberEvent(ActionEvent event) {
		if (model.getNextRoomPuzzle().getPuzzleType().equalsIgnoreCase("item")) {
			printTextConsole("\nThere's nothing to input.");
			printTextConsole("\n--------------------------------------------------");
		}else {
			puzzleKeyPadPane.setVisible(true);
			puzzleInventoryPane.setVisible(false);
			//			if() {
			//				
			//			}
		}
	}

	@FXML
	void puzzleUseItemEvent(ActionEvent event) {
		if (model.getNextRoomPuzzle().getPuzzleType().equalsIgnoreCase("input")) {
			printTextConsole("\nThere's a keypad right next to the door");
			printTextConsole("\nmaybe I should use that");
			printTextConsole("\n--------------------------------------------------");
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
						printTextConsole("\n You inserted the " + itemList.get(x).getItemName() + " and the door open");
						printTextConsole("\n--------------------------------------------------");
						model.getPlayer().setCurrentRoom(model.getNextRoom());
						changeTab("Action");
						break;
					}

				}
				if(!itemFound) {
					printTextConsole("\n Nothing Interesting Happen");
					printTextConsole("\n--------------------------------------------------");
				}
			}
		}
	}

	@FXML
	void puzzleHintEvent(ActionEvent event) {
		printTextConsole("\n" + model.getNextRoomPuzzle().getPuzzleHint());
		printTextConsole("\n--------------------------------------------------");
	}

	@FXML
	void puzzleExitEvent(ActionEvent event) {
		printTextConsole("\nYou couldn't figure out how to solve the puzzle");
		printTextConsole("\nYou stumble back into the middle of the room");
		printTextConsole("\n--------------------------------------------------");
		changeTab("Action");
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


	void setTabActive() {
		if(model.getState().equalsIgnoreCase("Action Menu")) {
			actionTab.setDisable(false);
			combatTab.setDisable(false);
			puzzleTab.setDisable(false);
		}
		else if(model.getState().equalsIgnoreCase("Combat Menu")) {

		}
		else if(model.getState().equalsIgnoreCase("Puzzle Menu")) {

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

	public void attackPlayer() {
		printTextConsole("\nThe " + model.getCurrentMonster().getMonsterName() + " attacks!");
		int mosnterDamage = model.getCurrentMonster().attackPlayer();
		if(mosnterDamage == 0) {
			printTextConsole("\nThe attack missed!");
		}else {
			printTextConsole("\nYou took " + mosnterDamage + " damage!");
			model.getPlayer().takeDmg(mosnterDamage);
			printTextConsole("\nHealth: (" + model.getPlayer().getPlayerCurrentHealth() + "/" + model.getPlayer().getPlayerMaxHealth() + ")");
			checkPlayerDeath();
		}
		printTextConsole("\n--------------------------------------------------");

	}

	private void checkPlayerDeath() {
		if(model.getPlayer().getPlayerCurrentHealth() <= 0) {
			printTextConsole("\n\n--------------------------------------------------");
			printTextConsole("\n||||||||||||||||||||||||||||||||||||||||||||||||||");
			printTextConsole("\n=====        YOU DIED   -   GAME OVER        =====");
			printTextConsole("\n||||||||||||||||||||||||||||||||||||||||||||||||||");
			printTextConsole("\n--------------------------------------------------\n");
		}
	}

	private void checkWinCondition() {
		if(model.getMonsterAlive() == 0) {	
			printTextConsole("\n\n--------------------------------------------------");
			printTextConsole("\n||||||||||||||||||||||||||||||||||||||||||||||||||");
			printTextConsole("\n=====        You Win   -    Vicotry        =====");
			printTextConsole("\n||||||||||||||||||||||||||||||||||||||||||||||||||");
			printTextConsole("\n--------------------------------------------------\n");
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
			actionList.getSelectionModel().clearSelection();
			break;
		case "combat":
			actionTab.setDisable(true);
			combatTab.setDisable(false);
			tabPaneMenu.getSelectionModel().select(combatTab);
			combatInventoryPane.setVisible(false);
			combatLootBox.setVisible(false);
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

	private void printTextConsole(String text) {
		ArrayList<String> textWord = new ArrayList<String>();
		for(String word: text.split(" ")) {
			if(word.contains("\n")) {
				String[] array = word.split("\n");

				if(array[1].length() == 0) {
					textWord.add(array[0]);
					textWord.add("\n\n");
					textWord.add(array[2]);
				}else {

					textWord.add(array[0]);
					textWord.add("\n");
					textWord.add(array[1]);
					System.out.println(array[0] + "0");
					System.out.println(array[1] + "1");
				}
			}else {
				textWord.add(word);
			}
		}


		final Animation animation = new Transition()
		{
			int lengthOfString = 0;
			{
				setCycleDuration(Duration.seconds(1));
			}



			protected void interpolate(double frac) 
			{
				try {

					lengthOfString += textWord.get(0).length();

					if(lengthOfString >= 50) {
						lengthOfString =0;
						consoleTextArea.appendText(textWord.get(0));
						consoleTextArea.appendText("\n");
					}else {
						consoleTextArea.appendText(textWord.get(0));
						consoleTextArea.appendText(" ");
					}
					if(textWord.get(0).contains("\n")) {
						lengthOfString = 0;
					}
					textWord.remove(0);
				}catch(Exception e) {

				}

			}



		};

		animation.statusProperty().addListener((obs, oldStatus, newStatus) -> 
		actionTab.setDisable(newStatus==Animation.Status.RUNNING));
		animation.play();
		animation.setOnFinished(e ->
		output = " ");
	}









}
