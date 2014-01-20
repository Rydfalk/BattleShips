package game;

import java.awt.Point;

public class Game {
	
	private Point startCoordinate;

	private int direction;

	private Player[] players = new Player[2];

	public static void main(String[] args) {

		Ship ship1 = new Ship(1);
		Ship ship2 = new Ship(2);
		Ship ship3 = new Ship(3);
		Ship ship4 = new Ship(4);

	}

	public void addNewHumanPlayer(String text) {

	/*
	 * TODO: Add new player
	 */

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

		return players[i].getName();

	}

	public void initialize() {
		
		/*
		 * TODO: Game loop
		 */
		
	}
}
