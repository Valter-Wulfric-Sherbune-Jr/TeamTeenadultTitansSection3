package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoadGameController {

	@FXML
	private Button loadGameBackButton;

	@FXML
	private Button LoadGameDeleteSaveButton;

	@FXML
	private Button loadGameLoadButton;

	@FXML
	private Button loadButton1;

	@FXML
	private Label loadGameNameLabel1;

	@FXML
	private Label loadGameRoomLabel1;

	@FXML
	private Label loadGameTimeLabel1;

	@FXML
	private Label loadGameEmptyLabel1;

	@FXML
	private Button loadButton2;

	@FXML
	private Label loadGameNameLabel2;

	@FXML
	private Label loadGameRoomLabel2;

	@FXML
	private Label loadGameTimeLabel2;

	@FXML
	private Button loadButton3;

	@FXML
	private Label loadGameNameLabel3;

	@FXML
	private Label loadGameRoomLabel3;

	@FXML
	private Label loadGameTimeLabel3;

	@FXML
	private Button loadButton4;

	@FXML
	private Label loadGameNameLabel4;

	@FXML
	private Label loadGameRoomLabel4;

	@FXML
	private Label loadGameTimeLabel4;

	@FXML
	private Button loadButton5;

	@FXML
	private Label loadGameNameLabel5;

	@FXML
	private Label loadGameRoomLabel5;

	@FXML
	private Label loadGameTimeLabel5;

	@FXML
	private Button loadButton6;

	@FXML
	private Label loadGameNameLabel6;

	@FXML
	private Label loadGameRoomLabel6;

	@FXML
	private Label loadGameTimeLabel6;

	@FXML
	private Button loadButton7;

	@FXML
	private Label loadGameNameLabel7;

	@FXML
	private Label loadGameRoomLabel7;

	@FXML
	private Label loadGameTimeLabel7;

	@FXML
	private Button loadButton8;

	@FXML
	private Label loadGameNameLabel8;

	@FXML
	private Label loadGameRoomLabel8;

	@FXML
	private Label loadGameTimeLabel8;

	@FXML
	private Button loadButton9;

	@FXML
	private Label loadGameNameLabel9;

	@FXML
	private Label loadGameRoomLabel9;

	@FXML
	private Label loadGameTimeLabel9;

	@FXML
	private Button loadButton10;

	@FXML
	private Label loadGameNameLabel10;

	@FXML
	private Label loadGameRoomLabel10;

	@FXML
	private Label loadGameTimeLabel10;
	
	public loadButton[] arrayButtons;
	public ArrayList<loadButton> buttonList;

	@FXML
	public void initialize(){ 

		initializeButtonArray();

	}
	public void initializeButtonArray() { 

		arrayButtons = new loadButton[10];
		arrayButtons[1] = new loadButton(loadButton1,loadGameNameLabel1,loadGameRoomLabel1,loadGameTimeLabel1);
		arrayButtons[2] = new loadButton(loadButton2,loadGameNameLabel2,loadGameRoomLabel2,loadGameTimeLabel2);
		arrayButtons[3] = new loadButton(loadButton3,loadGameNameLabel3,loadGameRoomLabel3,loadGameTimeLabel3);
		arrayButtons[4] = new loadButton(loadButton4,loadGameNameLabel4,loadGameRoomLabel4,loadGameTimeLabel4);
		arrayButtons[5] = new loadButton(loadButton5,loadGameNameLabel5,loadGameRoomLabel5,loadGameTimeLabel5);
		arrayButtons[6] = new loadButton(loadButton6,loadGameNameLabel6,loadGameRoomLabel6,loadGameTimeLabel6);
		arrayButtons[7] = new loadButton(loadButton7,loadGameNameLabel7,loadGameRoomLabel7,loadGameTimeLabel7);
		arrayButtons[8] = new loadButton(loadButton8,loadGameNameLabel8,loadGameRoomLabel8,loadGameTimeLabel8);
		arrayButtons[9] = new loadButton(loadButton9,loadGameNameLabel9,loadGameRoomLabel9,loadGameTimeLabel9);
		arrayButtons[10] = new loadButton(loadButton10,loadGameNameLabel10,loadGameRoomLabel10,loadGameTimeLabel10);
	}
	
	@FXML
	void loadGameBackButtonOnClick(ActionEvent event) {

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

class loadButton{

	Button button;
	Label name;
	Label room;
	Label time;
	
	public loadButton(Button button, Label name, Label room, Label time) {
		this.button = button;
		this.name = name;
		this.room = room;
		this.time = time;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public Label getName() {
		return name;
	}

	public void setName(Label name) {
		this.name = name;
	}

	public Label getRoom() {
		return room;
	}

	public void setRoom(Label room) {
		this.room = room;
	}

	public Label getTime() {
		return time;
	}

	public void setTime(Label time) {
		this.time = time;
	}
	
	
	
	
}
