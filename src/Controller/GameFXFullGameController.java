package Controller;

import Model.GameFXModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class GameFXFullGameController {

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
    private ListView<?> actionList;

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
    private Label combatLabel;

    @FXML
    private ListView<?> combatList;

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
    private ListView<?> puzzleList;
    
    @FXML
    private TextArea consoleTextArea;
    
    GameFXModel model = new GameFXModel();
    
    //----------------------------------------------------------------------------
    
    public void initialize() {
    	model.setGameFolder("Hydra Game File");
    	model.loadGameFolder("Item");
		model.loadGameFolder("Monster");
		model.loadGameFolder("Puzzle");
		model.loadGameFolder("Room");
		model.makeNewPlayer("Vector");
		model.setState("Action Menu");
		consoleTextArea.setVisible(true);
		for(int x = 0; x < model.getPlayer().getCurrentRoom().getRoomAccessList().size(); x++) {
			
			
		}
		consoleTextArea.appendText(model.getPlayer().getCurrentRoom().toString());
		//System.out.println(consoleTextArea.getText() + "12");
		//consoleTextArea.append("hi");
//		model.setState("ActionMenu");
    }

    //----------------------------------------------------------------------------
    
    @FXML
    void actionMoveEvent(ActionEvent event) {
    	if(model.getState().equals("Action Menu")) {
    		
    	}
    }
    
    @FXML
    void actionInventoryEvent(ActionEvent event) {

    }
    
    @FXML
    void actionExamineRoomEvent(ActionEvent event) {

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

    }

    @FXML
    void combatDefendEvent(ActionEvent event) {

    }

    @FXML
    void combatExamineEvent(ActionEvent event) {

    }

    @FXML
    void combatInventoryEvent(ActionEvent event) {

    }
    
    @FXML
    void combatRunAwayEvent(ActionEvent event) {

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

    

    

    

    

}
