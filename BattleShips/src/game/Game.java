package game;

import java.awt.Point;
import java.util.ArrayList;

public class Game {

	private ArrayList<Player> players = new ArrayList<Player>(2);

	private Player activePlayer;

	public Game() {

	}

	public void addNewHumanPlayer(String name) {

		Player player = new Player(name);
		if (players.isEmpty()) {
			players.add(0, player);
		} else {
			players.add(1, player);
		}

	}

	public void addNewAIPlayer(String text) {
		/*
		 * TODO: Add a create AI player constructor or something
		 */

	}

	public String getActivePlayerName() {
		return activePlayer.getName();

	}

	public String getPlayerName(int playerNumber) {

		return players.get(playerNumber - 1).getName();

	}

	public void initialize() {

		activePlayer = players.get(0);
		while (!gameOver()) {

		}

	}

	private boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	public String removeLastPlayer() {
		String returnString;

		if (players.size() == 2) {
			returnString = players.get(1).getName();
			players.remove(1);
			return "Removed " + returnString;
		} else if (players.size() == 1) {
			returnString = players.get(0).getName();
			players.remove(0);
			return "Removed " + returnString;
		} else {
			return "No player to remove!";
		}

	}

	public String deletePlayer(String name) {
		String returnString;
		
		if(players.get(0).getName() == name){

			returnString = "Player " + players.get(0).getName()+ " was deleted";
			players.get(0).deletePlayer();
			return returnString;
		}else if(players.get(1).getName() == name){
			returnString = "Player " + players.get(1).getName()+ " was deleted";
			players.get(1).deletePlayer();
			return returnString;
		}else{
			return "Could not delete player";
		}
		
	}
}
