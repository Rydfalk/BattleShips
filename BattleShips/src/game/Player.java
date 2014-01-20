package game;

import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseObject;

public class Player {

	/******************* CLASS VARIABLES *******************/
	private String name;
	private int wins, losses, hitPercentage;
	private DatabaseObject dbo;
	private String databaseTableName = "statistics";

	/**
	 * The columns and types for the database table
	 */
	private String[][] databaseColumns = { { "id", "int" },
			{ "name", "varchar(16)" }, { "wins", "int" }, { "losses", "int" },
			{ "hit_percentage", "DECIMAL(2,2)" } };

	private Board board = new Board();

	/******************* CONSTRUCTORS *******************/
	public Player(String name) {
		this.name = name;

		dbo = new DatabaseObject();

		if (!dbo.tableExists(databaseTableName)) {
			dbo.createTable(databaseTableName, databaseColumns);

			createPlayer(this.name);
		}
		if (playerExists(name)) {

			getPlayer(name);
		} else {
			createPlayer(name);
		}

	}

	/******************* PRIVATE METHODS *******************/
	private void createPlayer(String name) {
		dbo = new DatabaseObject();

		String sql = "INSERT INTO " + databaseTableName
				+ " (name, wins, losses, hit_percentage) VALUES ('" + name
				+ "', 0, 0, 0)";

		dbo.write(sql);
		dbo.closeConnection();

	}

	private void getPlayer(String name) {
		dbo = new DatabaseObject();
		String sql = "SELECT name, wins, losses  FROM statistics WHERE name = '"
				+ name + "'";

		try {
			ResultSet rs = dbo.read(sql);
			this.wins = rs.getInt("wins");
			this.losses = rs.getInt("losses");
		} catch (SQLException e) {
			dbo.handleError(e);
		}
		dbo.closeConnection();
	}

	private boolean playerExists(String name) {
		boolean returnValue = false;
		String sql = "SELECT count(*) FROM statistics WHERE name='" + name
				+ "'";
		try {
			ResultSet rs = dbo.read(sql);
			// Returns true if the count of matched tables are one or more
			if (rs.getInt("count(*)") > 0) {
				returnValue = true;
			}
			dbo.closeConnection();
		} catch (SQLException e) {
			dbo.handleError(e);
		}

		return returnValue;

	}

	/******************* PROTECTED METHODS *******************/

	/******************* PUBLIC METHODS *******************/

	/**
	 * Deletes player from database
	 */
	private void deletePlayer() {
		dbo = new DatabaseObject();
		String sql = "DELETE FROM " + databaseTableName + " WHERE name = '"
				+ name + "'";
		dbo.write(sql);
		dbo.closeConnection();
	}

	/**
	 * Asks the player for a move, checks if its valid. If it's not valid we'll
	 * ask again. If it's valid we perform the move.
	 * 
	 * @param opponent
	 *            : Player object
	 */
	public void makeMove(Player opponent) {
		Board opponentsBoard = new Board();
		Point hitPoint = new Point(0, 0);
		boolean exit = false;
		while (!exit) {
			/*
			 * TODO:
			 * 
			 * Ask player to make move
			 */
			exit = (!opponentsBoard.isHit(hitPoint));

		}
		opponentsBoard.hitSquare(hitPoint);

	}

	/**
	 * Calls the main board print-method
	 */
	public void printBoard() {
		board.printBoard();
	}

	/**
	 * Registrates a win
	 */
	public void registerWin() {
		wins++;
	}

	/**
	 * Registrates a loss
	 */
	public void registerLoss() {
		losses++;
	}

	/**
	 * Uses the ships size, startPoint and direction to calculate where on the
	 * board the ship is suposed to be placed and checks so that it is a valid
	 * placement.
	 * 
	 * If it's a valid placement we continue with the placement and return true.
	 * If it's not a valid placement we return false
	 * 
	 * @param ship
	 *            : Ship object
	 * @param startPoint
	 *            : Point object
	 * @param dir
	 *            : Direction object
	 */
	public boolean setShip(Ship ship, Point startPoint, Direction dir) {

		Board tempBoard = new Board();

		/*
		 * Checks if the given coordinates are valid to be occupied if they are
		 * not we return false
		 */
		switch (dir) {
		case UP:
			for (int i = 0; i < ship.getSize(); i++) {

				if (!board.isValidToOccupy(startPoint.x, startPoint.y - i)) {
					return false;
				}
				tempBoard.occupySquare(startPoint.x, startPoint.y - i);
			}
			break;
		case DOWN:
			for (int i = 0; i < ship.getSize(); i++) {
				if (!board.isValidToOccupy(startPoint.x, startPoint.y + i)) {

					return false;
				}
				tempBoard.occupySquare(startPoint.x, startPoint.y + i);

			}
			break;
		case RIGHT:
			for (int i = 0; i < ship.getSize(); i++) {

				if (!board.isValidToOccupy(startPoint.x + i, startPoint.y)) {

					return false;
				}
				tempBoard.occupySquare(startPoint.x + i, startPoint.y);

			}
			break;
		case LEFT:
			for (int i = 0; i < ship.getSize(); i++) {
				if (!board.isValidToOccupy(startPoint.x - i, startPoint.y)) {

					return false;
				}
				tempBoard.occupySquare(startPoint.x - i, startPoint.y);
			}
			break;

		default:
			break;
		}

		/*
		 * If all is fine and all cells are okey to be occupied we transfer the
		 * cells from tempBoard to our main board
		 */
		for (int x = 0; x < board.boardWidth; x++) {
			for (int y = 0; y < board.boardWidth; y++) {
				if (!tempBoard.isEmpty(x, y)) {
					board.occupySquare(x, y);
				}
			}
		}
		return true;
	}

	/**
	 * Updates the database with the current statistics
	 */
	public void saveStats() {
		dbo = new DatabaseObject();
		String sql = "UPDATE " + databaseTableName + " SET " + "wins = " + wins
				+ ", " + "losses = " + losses + " WHERE name = '" + name + "'";
		dbo.write(sql);

		dbo.closeConnection();
	}
	
	
	/******************* GETTERS AND SETTERS *******************/

	public Board getBoard() {
		return board;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

}
>>>>>>> refs/remotes/origin/Lars-Branch
