package Model;

public class Monsters {
  private String monsterID;
	private String monsterName;
	private String monsterDesc;
	private int monsterHealth;
	private int monsterDamage;
	private double monsterHitPercentage;
	
	
	public Monsters(String monsterID, String monsterName, String monsterDesc, int monsterHealth, int monsterDamage,
			double monsterHitPercentage) {
		super();
		this.monsterID = monsterID;
		this.monsterName = monsterName;
		this.monsterDesc = monsterDesc;
		this.monsterHealth = monsterHealth;
		this.monsterDamage = monsterDamage;
		this.monsterHitPercentage = monsterHitPercentage;
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
