package Model;

import java.io.Serializable;
import java.util.HashMap;

public class SaveData implements Serializable{

	private String saveId;
	private String gameFolder;
	private Players playerData;
	private HashMap<String, Items> itemList;
	private HashMap<String, Monsters> monsterList;
	private HashMap<String, Rooms> roomList;

	public SaveData() {
	}

	public SaveData(String saveId, String gameFolder, Players playerData, HashMap<String, Items> itemList,
			HashMap<String, Monsters> monsterList, HashMap<String, Rooms> roomList) {
		super();
		this.saveId = saveId;
		this.gameFolder = gameFolder;
		this.playerData = playerData;
		this.itemList = itemList;
		this.monsterList = monsterList;
		this.roomList = roomList;
	}



	public String getSaveId() {
		return saveId;
	}

	public void setsaveId(String saveId) {
		this.saveId = saveId;
	}

	public HashMap<String, Items> getItemList() {
		return itemList;
	}

	public void setItemList(HashMap<String, Items> itemList) {
		this.itemList = itemList;
	}

	public HashMap<String, Monsters> getMonsterList() {
		return monsterList;
	}

	public void setMonsterList(HashMap<String, Monsters> monsterList) {
		this.monsterList = monsterList;
	}

}
