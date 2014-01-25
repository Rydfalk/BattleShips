package game;

import java.util.ArrayList;

public class Game {


	private ArrayList<Player> players = new ArrayList<Player>(2);

	private ActivePlayer activePlayer;
	
	private enum ActivePlayer{
		PLAYER1(), PLAYER2();
	}

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

	public Player getActivePlayer() {
		
		if(activePlayer==ActivePlayer.PLAYER1){
			return players.get(0);
		}else{
			return players.get(1);
		}

	}

	public String getPlayerName(int playerNumber) {

		return players.get(playerNumber - 1).getName();

	}

	public void initialize() {

		activePlayer = ActivePlayer.PLAYER1;
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

	public void setCoordinates(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
