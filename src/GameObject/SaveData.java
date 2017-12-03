package GameObject;

import java.io.Serializable;
import java.util.HashMap;

public class SaveData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1539034718245633359L;
	private int saveId;
	private Party party;
	private HashMap<String, Items> itemList;
	private HashMap<String, Monsters> monsterList;
	private HashMap<String, Rooms> roomList;
	private int monsterAlive;
	private String gameFolder;


	public SaveData(int saveId, Party party, HashMap<String, Items> itemList,
			HashMap<String, Monsters> monsterList, HashMap<String, Rooms> roomList, int monsterAlive, String gameFolder) {
		this.saveId = saveId;
		this.party = party;
		this.itemList = itemList;
		this.monsterList = monsterList;
		this.roomList = roomList;
		this.monsterAlive = monsterAlive;
		this.gameFolder = gameFolder;
	}

	
	public Party getParty() {
		return party;
	}


	public void setParty(Party party) {
		this.party = party;
	}


	public int getSaveId() {
		return saveId;
	}

	public void setSaveId(int saveId) {
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

	public HashMap<String, Rooms> getRoomList() {
		return roomList;
	}

	public void setRoomList(HashMap<String, Rooms> roomList) {
		this.roomList = roomList;
	}

	public int getMonsterAlive() {
		return monsterAlive;
	}

	public void setMonsterAlive(int monsterAlive) {
		this.monsterAlive = monsterAlive;
	}

	public String getGameFolder() {
		return gameFolder;
	}

	public void setGameFolder(String gameFolder) {
		this.gameFolder = gameFolder;
	}
	
	

}
