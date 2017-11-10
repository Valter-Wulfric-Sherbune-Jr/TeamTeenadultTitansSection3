package Model;

import java.io.Serializable;

class Monsters implements Serializable{
	private String monsterName;
	private String monsterId;
	private String monsterDesc;
	private int monsterHealth;
	private int monsterDamage;
	private double monsterHitPercentage;

	public Monsters() {
		this.monsterId = "E00";
		this.monsterName = "Invalid Monster Name";
		this.monsterDesc = "Invalid Monster Description";
		this.monsterHealth = 00;
		this.monsterDamage = 00;
		this.monsterHitPercentage = 0;
	}

	public Monsters(String monsterID, String monsterName, String monsterDesc, int monsterHealth, int monsterDamage,
			double monsterHitPercentage) {
		this.monsterId = monsterID;
		this.monsterName = monsterName;
		this.monsterDesc = monsterDesc;
		this.monsterHealth = monsterHealth;
		this.monsterDamage = monsterDamage;
		this.monsterHitPercentage = monsterHitPercentage;
	}

	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}

	public void setMonsterId(String monsterID) {
		this.monsterId = monsterID;
	}

	public void setMonsterDesc(String monsterDesc) {
		if(monsterDesc.length() > 50) {
			int totalCharacterLength = 0;
			int descriptionLength = monsterDesc.length();
			String outputString ="";

			for (String word : monsterDesc.split(" ")) {
				totalCharacterLength += word.length();
				descriptionLength -= word.length()+1;
				outputString += word + " ";

				if(totalCharacterLength > 40 && descriptionLength > 0) {
					totalCharacterLength = 0;
					outputString += "\n";
				}
			}
			monsterDesc = outputString;
		}

		this.monsterDesc = monsterDesc;
	}

	public void setMonsterHealth(int monsterHealth) {
		this.monsterHealth = monsterHealth;
	}

	public void setMonsterDamage(int monsterDamage) {
		this.monsterDamage = monsterDamage;
	}

	public void setMonsterHitPercentage(double monsterHitPercentage) {
		this.monsterHitPercentage = monsterHitPercentage;
	}

	public String getMonsterName() {
		return monsterName;
	}

	public String getMonsterId() {
		return monsterId;
	}

	public String getMonsterDesc() {
		return monsterDesc;
	}

	public int getMonsterHealth() {
		return monsterHealth;
	}

	public int getMonsterDamage() {
		return monsterDamage;
	}

	public double getMonsterHitPercentage() {
		return monsterHitPercentage;
	}

	public void takeDmg(int dmg) {
		monsterHealth = (monsterHealth - dmg);
	}
	
	public boolean attackHit() {
		double chance = Math.random() * 100;
		System.out.println("Monster has " + monsterHitPercentage + " percentage, chance -" + chance);
		
		if ((chance -= monsterHitPercentage*100) < 0) 
			return true;
		else 
			return false;	
	}
	public int attackPlayer() {
		if(attackHit() == true) {
			System.out.println("The monster successfully hit you");
			return monsterDamage;
		}
		else{
			System.out.println("The monster attacked and missed you");
			return 0;
		}
	}

	public String toString() {
		String returnString = "";

		//Add Guide Line
		returnString += "---------------------------------------\n";

		//Add Monster Name
		returnString += "Monster Name:\n" + getMonsterName() + "\n\n";

		//Add Monster Description
		returnString += "Monster Description:\n" + getMonsterDesc() + "\n\n";

		//Add Monster Health
		returnString += "Monster Health:\n" + getMonsterHealth() + "\n\n";

		//Add Monster Damage
		returnString += "Monster Damage:\n" + getMonsterDamage() + "\n\n";

		//Add Monster Hit Percentage
		returnString += "Monster Hit Percentage:\n" + getMonsterHitPercentage() + "\n";

		//Add Guide Line
		returnString += "---------------------------------------";

		return returnString;
	}
}
