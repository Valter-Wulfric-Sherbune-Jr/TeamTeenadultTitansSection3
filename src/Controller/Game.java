package Controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Model.Player;


/**
 * @author mansoor
 * This class is the game creator and will be invoked from the Main menu selection.
 * This class creates, saves, loads, view scores, and exit game.
 *
 */

public class Game {
	public static Player player;
	public static String begin;
	public static Scanner input = new Scanner(System.in);
	final static int[] StartingCoordinate = {0,0,4};

	public static void main(String[] args) {

		//Create User ID and save user ID
	}
	public void create(Player p) throws IOException{
		{
			//Scanner input = new Scanner(System.in);
			System.out.println("Please enter username: ");
			String user = input.next();
			p = new Player(0, 'x',"",null);
			Game.player = p;
			Player.setUsername(user);
			String username = p.getUsername();		
			File file = new File(username + ".dat");
			if(file.exists())
			{
				System.out.println("This name already exists, please use a different name!");
				create(p);
			} else {
				System.out.println("Profile being created, please wait....");
				PrintWriter writer = new PrintWriter(new FileWriter(file));
				writer.println(username);
				writer.close();
				//Sleep.Delay(3000);
				System.out.println();
				System.out.println("Alright " + p.getUsername() + ", I need you to get on this\n" +
						"train and get as much loot as possible! Good Luck!\n");

			}
		}

	}
	public static void play()
	{
		boolean done = false;

		while(!done)
		{

		}
	}

}