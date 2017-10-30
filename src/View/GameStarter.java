package View;

import Controller.Game;
import Controller.Start;

public class GameStarter
{
	static Start start = new Start();

	public static void main(String[] args)
	{
		Start.startMessage();
		Game.play();
	}

}
