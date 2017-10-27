package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character implements Serializable{

	private static String username;
	private static int userID;
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Player(int characterID, String name)
	{
		super();
		// TODO Auto-generated constructor stub
	}
	

}
