package Controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import GameObject.Items;
import GameObject.Monsters;
import Model.GameFXModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class GameFXFullGameController {

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
	private TextArea consoleTextArea;

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
		actionInventoryPane.setVisible(false);
		combatTab.setDisable(true);
		puzzleTab.setDisable(true);
		actionInventoryBox.setVisible(false);
		actionLootBox.setVisible(false);

		consoleTextArea.appendText(model.getPlayer().getCurrentRoom().toString());
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
				actionTab.setDisable(true);
				puzzleTab.setDisable(false);
				puzzleInventoryPane.setVisible(false);
				puzzleKeyPadPane.setVisible(false);
				combatList.setVisible(false);
				actionList.setVisible(false);
				tabPaneMenu.getSelectionModel().select(puzzleTab);
				actionNeutralBox.setVisible(false);
				actionInventoryPane.setVisible(false);
				actionList.getSelectionModel().clearSelection();
			}
			else if(model.getNextRoom().getRoomMonster().isEmpty()) {
				model.getPlayer().setCurrentRoom(model.getNextRoom());
				consoleTextArea.appendText("\nYou moved to the next room");
				consoleTextArea.appendText("\n--------------------------------------------------");
				actionInventoryPane.setVisible(false);
				actionList.getSelectionModel().clearSelection();

			}
			else{
				model.getPlayer().setCurrentRoom(model.getNextRoom());
				consoleTextArea.appendText("\n- - - - -           ENCOUNTER!           - - - - -\n");
				consoleTextArea.appendText("--------------------------------------------------\n");
				for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
					consoleTextArea.appendText(model.getPlayer().getCurrentRoom().getRoomMonster().get(x).toString() + "\n");
				}
				actionTab.setDisable(true);
				combatTab.setDisable(false);
				combatInventoryPane.setVisible(false);
				combatList.setVisible(false);
				actionList.setVisible(false);
				tabPaneMenu.getSelectionModel().select(combatTab);
				actionNeutralBox.setVisible(true);
				combatBox.setVisible(true);
				actionInventoryPane.setVisible(false);
				combatLootBox.setVisible(false);
				actionList.getSelectionModel().clearSelection();
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
		consoleTextArea.appendText("\nPlayer Name: " + model.getPlayer().getPlayerName());
		consoleTextArea.appendText("\nPlayer Health: (" + model.getPlayer().getPlayerCurrentHealth() + "/" + model.getPlayer().getPlayerMaxHealth() + ")");
		consoleTextArea.appendText("\nPlayer Weapon: " +model.getPlayer().getWeapon().getItemName());
		consoleTextArea.appendText("\n--------------------------------------------------");
	}

	@FXML
	void actionExamineItemEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items> itemList = model.getPlayer().getInventoryList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(actionList.getSelectionModel().getSelectedItem())) {
				consoleTextArea.appendText("\n" + itemList.get(x).toString());
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
				consoleTextArea.appendText("\nYou drop the " + itemList.get(x).getItemName());
				consoleTextArea.appendText("\n--------------------------------------------------");
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
					consoleTextArea.appendText("\nYou recover " + itemList.get(x).getItemActionValue() + " health.");
					consoleTextArea.appendText("\n--------------------------------------------------");
					model.getPlayer().removeItemFromInventory(itemList.get(x));
					setListInventory(itemList);
					actionList.setItems(observList);
				}else {
					consoleTextArea.appendText("\nThis item cannot be use right now");
					consoleTextArea.appendText("\n--------------------------------------------------");
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
				consoleTextArea.appendText("\nYou equipped the " + itemList.get(x).getItemName());
				consoleTextArea.appendText("\n--------------------------------------------------");
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
		consoleTextArea.appendText("\n" + model.getPlayer().getCurrentRoom().toString());
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
				consoleTextArea.appendText("\nYou Picked Up the " + itemList.get(x).getItemName());
				consoleTextArea.appendText("\n--------------------------------------------------");
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
				consoleTextArea.appendText("\n" + itemList.get(x).toString());
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
	void actionQuitGameEvent(ActionEvent event) {

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
				consoleTextArea.appendText("\nYou don't have any ammo for your weapon!\n");
				consoleTextArea.appendText("\n--------------------------------------------------");
			}

			consoleTextArea.appendText("\nYou attack the " + model.getCurrentMonster().getMonsterName() + "!");
			model.getCurrentMonster().takeDmg(model.getPlayer().getWeapon().getItemActionValue());
			model.getPlayer().useWeaponAmmo(model.getPlayer().getWeapon());
			consoleTextArea.appendText("\nThe " + model.getCurrentMonster().getMonsterName() + " took " + 
					model.getPlayer().getWeapon().getItemActionValue() + " damage!\n");

			if(model.getCurrentMonster().getMonsterCurrentHealth() <= 0) {
				consoleTextArea.appendText("\nThe " + model.getCurrentMonster().getMonsterName() + " slumps over, defeated.");
				consoleTextArea.appendText("\n--------------------------------------------------");
				model.getPlayer().getCurrentRoom().removeRoomMonster(model.getCurrentMonster());
				model.setLootList(model.getMonsterLootList());
				if(!model.getLootList().isEmpty()) {
					for(int y = 0; y < model.getLootList().size();y++) {
						consoleTextArea.appendText("\nThe monster drop " + model.getSpecificLoot(y).getItemName() +" on the floor of the room");
					}
					consoleTextArea.appendText("\n--------------------------------------------------");
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
				consoleTextArea.appendText("\n" + itemList.get(x).toString());
			}

		}
	}

	@FXML
	void combatLootExitEvent(ActionEvent event) {
		if(!model.getLootList().isEmpty()) {
			consoleTextArea.appendText("\nThe rest of the time dropped to the floor");
			consoleTextArea.appendText("\n--------------------------------------------------");
			for(Items item : model.getLootList()) {
				model.getPlayer().getCurrentRoom().addRoomItem(item);
			}
		}
		tabPaneMenu.getSelectionModel().select(actionTab);
		combatList.getSelectionModel().clearSelection();
		combatInventoryPane.setVisible(false);
		
		actionTab.setDisable(false);
		combatTab.setDisable(true);
		
	}

	@FXML
	void combatLootPickUpEvent(ActionEvent event) {
		String itemName = "";
		ArrayList<Items>itemList = model.getLootList();
		for(int x = 0; x < itemList.size(); x++) {
			itemName = itemList.get(x).getItemName()+ " x" + 
					itemList.get(x).getItemAmount();
			if(itemName.equalsIgnoreCase(combatList.getSelectionModel().getSelectedItem())) {
				consoleTextArea.appendText("\nYou Picked Up the " + itemList.get(x).getItemName());
				consoleTextArea.appendText("\n--------------------------------------------------");
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
		consoleTextArea.appendText("\nYou examine the monster");
		for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
			consoleTextArea.appendText("\n" + model.getPlayer().getCurrentRoom().getRoomMonster().get(x).toString() + "\n");
		}
		consoleTextArea.appendText("--------------------------------------------------\n");
	}

	@FXML
	void combatInventoryEvent(ActionEvent event) {

	}

	@FXML
	void combatRunAwayEvent(ActionEvent event) {
		consoleTextArea.appendText("\nYou ran away from the monster");
		consoleTextArea.appendText("\n--------------------------------------------------");
		for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomMonster().size(); x++) {
			model.setCurrentMonster(model.getPlayer().getCurrentRoom().getRoomMonster().get(x));
			model.getCurrentMonster().setMonsterCurrentHealth(model.getCurrentMonster().getMonsterMaxHealth());
		}
		model.getPlayer().setCurrentRoom(model.getPlayer().getPreviousRoom());
		tabPaneMenu.getSelectionModel().select(actionTab);
		combatList.getSelectionModel().clearSelection();
		combatInventoryPane.setVisible(false);
		actionTab.setDisable(false);
		combatTab.setDisable(true);
	}

	@FXML
	void combatQuitGameEvent(ActionEvent event) {
		
	}

	//----------------------------------------------------------------------------

	@FXML
	void puzzleInputNumberEvent(ActionEvent event) {
		puzzleKeyPadPane.setVisible(true);
		puzzleInventoryPane.setVisible(false);
	}

	@FXML
	void puzzleUseItemEvent(ActionEvent event) {
		puzzleKeyPadPane.setVisible(false);
		puzzleInventoryPane.setVisible(true);
	}

	@FXML
	void puzzleHintEvent(ActionEvent event) {

	}

	@FXML
	void puzzleExitEvent(ActionEvent event) {
		model.getPlayer().setCurrentRoom(model.getPlayer().getPreviousRoom());
		tabPaneMenu.getSelectionModel().select(actionTab);
		combatList.getSelectionModel().clearSelection();
		combatInventoryPane.setVisible(false);
		actionTab.setDisable(false);
		combatTab.setDisable(true);
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
			consoleTextArea.setText("hi");
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
		consoleTextArea.appendText("\nThe " + model.getCurrentMonster().getMonsterName() + " attacks!");
		int mosnterDamage = model.getCurrentMonster().attackPlayer();
		if(mosnterDamage == 0) {
			consoleTextArea.appendText("\nThe attack missed!");
		}else {
			consoleTextArea.appendText("\nYou took " + mosnterDamage + " damage!");
			model.getPlayer().takeDmg(mosnterDamage);
			consoleTextArea.appendText("\nHealth: (" + model.getPlayer().getPlayerCurrentHealth() + "/" + model.getPlayer().getPlayerMaxHealth() + ")");
			checkPlayerDeath();
		}
		consoleTextArea.appendText("\n--------------------------------------------------");

	}

	private void checkPlayerDeath() {
		if(model.getPlayer().getPlayerCurrentHealth() <= 0) {
			consoleTextArea.appendText("\n\n--------------------------------------------------");
			consoleTextArea.appendText("\n||||||||||||||||||||||||||||||||||||||||||||||||||");
			consoleTextArea.appendText("\n=====        YOU DIED   -   GAME OVER        =====");
			consoleTextArea.appendText("\n||||||||||||||||||||||||||||||||||||||||||||||||||");
			consoleTextArea.appendText("\n--------------------------------------------------\n");
		}
	}

	private void checkWinCondition() {
		if(model.getMonsterAlive() == 0) {	
			consoleTextArea.appendText("\n\n--------------------------------------------------");
			consoleTextArea.appendText("\n||||||||||||||||||||||||||||||||||||||||||||||||||");
			consoleTextArea.appendText("\n=====        You Win   -    Vicotry        =====");
			consoleTextArea.appendText("\n||||||||||||||||||||||||||||||||||||||||||||||||||");
			consoleTextArea.appendText("\n--------------------------------------------------\n");
		}
	}









}
