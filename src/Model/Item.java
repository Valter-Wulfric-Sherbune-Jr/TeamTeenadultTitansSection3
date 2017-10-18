package Model;

public class Item {
	private String name, description,use;
	private int itemID, value;
	
	public Item(String name, int itemID, int value, String use, String description ) {
	
		this.name = name;
		this.description = description;
		this.use = use;
		this.itemID = itemID;
		this.value = value;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
