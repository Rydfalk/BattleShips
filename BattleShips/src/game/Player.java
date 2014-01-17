package game;

public class Player {

	private String playerName;
	
	private Board board = new Board();

	public Player(String name) {
		playerName = name;

	}

	public String getPlayerName() {
		return playerName;
	}

	
	public Board getBoard() {
		return board;
	}
	
}
