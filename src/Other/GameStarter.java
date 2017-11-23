package Other;

import Controller.GameController;
import Model.GameModel;

public class GameStarter {
	public static void main(String args[]) {
		GameController run = new GameController();
		run.initializeGame();
		while (true) {
			run.readUserInput();
		}
	}
}
