package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class MonsterManager {
	//List of all the Monster
	HashMap<String, Monsters> monsterList = new HashMap();

	public void makeMonster(String gameFolder) {
		//Check a specify folder for all room file, then make an object for each one
		String folderPath = "./res/" + gameFolder + "/Monster";
		try {	
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
			for (File file : listOfFiles) {
				if (file.isFile()) {
					fileName = file.getName();
					folderPath = "./res/" + gameFolder + "/Monster/" + fileName;
					makeMonsterObject(folderPath);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);                
		}	
	}

	public void makeMonsterObject(String filePath) {
		//Get the name of the file one at a time, and make an object with them
		try {

			//File Reader
			BufferedReader bufferedReader = 
					new BufferedReader(new FileReader(filePath));

			String fileLine = null;
			//Set Code is the title of the string ex: (Monster Name, Monster ID)
			String setCode = null;
			Monsters monsterObject = new Monsters();

			while((fileLine = bufferedReader.readLine()) != null) {
				//If fileLine reads a setCode, it get's replace it's current fileline with the next fileline and sets the setcode;
				switch(fileLine) {
				case "Monster Name:":
					setCode = "Monster Name";
					fileLine = bufferedReader.readLine();
					break;
				case "Monster ID:":
					setCode = "Monster ID";
					fileLine = bufferedReader.readLine();
					break;
				case "Monster Description:":
					setCode = "Monster Description";
					fileLine = bufferedReader.readLine();
					break;
				case "Monster Health:":
					setCode = "Monster Health";
					fileLine = bufferedReader.readLine();
					break;	
				case "Monster Damage:":
					setCode = "Monster Damage";
					fileLine = bufferedReader.readLine();
					break;	
				case "Monster Hit Percentage:":
					setCode = "Monster Hit Percentage";
					fileLine = bufferedReader.readLine();
					break;	
				}	
				if(setCode != null) {
					//Depending on the setcode, it'll set the infromation it got from flieLine
					switch(setCode) {
					case "Monster Name":
						monsterObject.setMonsterName(fileLine);
						break;
					case "Monster ID":
						monsterObject.setMonsterId(fileLine);
						break;
					case "Monster Description":
						monsterObject.setMonsterDesc(fileLine);
						break;
					case "Monster Health":
						monsterObject.setMonsterHealth(Integer.parseInt(fileLine));
						break;	
					case "Monster Damage":
						monsterObject.setMonsterDamage(Integer.parseInt(fileLine));
						break;	
					case "Monster Hit Percentage":
						monsterObject.setMonsterHitPercentage(Double.parseDouble(fileLine));
						break;	
					}
				}
			}
			//After it finish setting up object, object is then sent to a hashmap with Monster id as a key
			monsterList.put(monsterObject.getMonsterId(), monsterObject);

			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + filePath + "'");                
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + filePath+ "'");                  
		}
	}

	//Get Monster object from hashmap and prints the toString
	public void loadMonsterId(String monsterId) {
		Monsters monster = monsterList.get(monsterId);
		System.out.println(monster.toString());
	}

}

class Monsters {
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

	public String toString() {
		String returnString = "";

		//Add Guide Line
		returnString += "---------------------------------------\n";

		//Add Monster Name
		returnString += "Monster Name:\n" + getMonsterName() + "\n\n";

		//Add Monster ID
		returnString += "Monster ID:\n" + getMonsterId() + "\n\n";

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
