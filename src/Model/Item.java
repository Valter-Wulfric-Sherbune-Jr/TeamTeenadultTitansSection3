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
	
	public String getDescription() {
		return description;
	}
	
	public String getUse() {
		return use;
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public int getValue() {
		return value;
	}
	
}
