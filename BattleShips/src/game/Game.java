package game;

import java.awt.Point;

public class Game {

	private Point startCoordinate;

	private int direction;

	private Player[] players = new Player[2];

	public static void main(String[] args) {

	}

	public void addNewHumanPlayer(String text) {

		Player player = new Player(text);
		if (players[1].equals(null)) {
			players[1] = player;
		} else {
			players[2] = player;
		}

	}

	public void addNewAIPlayer(String text) {
		/*
		 * TODO: Add a create AI player constructor or something
		 */

	}

	public String getActivePlayer() {
		// TODO Auto-generated method stub
		return null;

	}

	public String getPlayer(int i) {

		try {
			return players[i].getName();
		} catch (Exception e) {
			return new String("No player");
		}

	}

	public void initialize() {

		/*
		 * TODO: Game loop
		 */
		
	}
}
