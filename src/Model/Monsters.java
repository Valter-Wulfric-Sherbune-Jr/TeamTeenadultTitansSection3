package Model;

public class Monsters {
	private Item dropItems;
	private String monsterID;
	private String monsterName;
	private String monsterDesc;
	private String enterStatement;
	private String winStatement;
	private String exitStatement;
	private int monsterHealth;
	private int monsterDamage;
	private double monsterHitPercentage;
	
	
	
	public Monsters(Item dropItems, String monsterID, String monsterName, String monsterDesc, String enterStatement
			, String winStatement, String exitStatement, int monsterHealth, int monsterDamage,
			double monsterHitPercentage) {
		super();
		this.dropItems = dropItems;
		this.monsterID = monsterID;
		this.monsterName = monsterName;
		this.monsterDesc = monsterDesc;
		this.enterStatement = enterStatement;
		this.winStatement = winStatement;
		this.exitStatement = exitStatement;
		this.monsterHealth = monsterHealth;
		this.monsterDamage = monsterDamage;
		this.monsterHitPercentage = monsterHitPercentage;
	}
	

	public Item getDropItems() {
		return dropItems;
	}


	public void setDropItems(Item dropItems) {
		this.dropItems = dropItems;
	}


	public String getMonsterID() {
		return monsterID;
	}


	public void setMonsterID(String monsterID) {
		this.monsterID = monsterID;
	}


	public String getMonsterName() {
		return monsterName;
	}


	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}


	public String getMonsterDesc() {
		return monsterDesc;
	}


	public void setMonsterDesc(String monsterDesc) {
		this.monsterDesc = monsterDesc;
	}
	
	
	public String getEnterStatement() {
		return enterStatement;
	}


	public void setEnterStatement(String enterStatement) {
		this.enterStatement = enterStatement;
	}


	public String getWinStatement() {
		return winStatement;
	}


	public void setWinStatement(String winStatement) {
		this.winStatement = winStatement;
	}


	public String getExitStatement() {
		return exitStatement;
	}


	public void setExitStatement(String exitStatement) {
		this.exitStatement = exitStatement;
	}




	public int getMonsterHealth() {
		return monsterHealth;
	}


	public void setMonsterHealth(int monsterHealth) {
		this.monsterHealth = monsterHealth;
	}


	public int getMonsterDamage() {
		return monsterDamage;
	}


	public void setMonsterDamage(int monsterDamage) {
		this.monsterDamage = monsterDamage;
	}


	public double getMonsterHitPercentage() {
		return monsterHitPercentage;
	}


	public void setMonsterHitPercentage(double monsterHitPercentage) {
		this.monsterHitPercentage = monsterHitPercentage;
	}
	
	public void takeDmg(int dmg) {
		monsterHealth = (monsterHealth - dmg);
	}
}
