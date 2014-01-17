package game;

import java.awt.Point;

public class Board {

	private int boardWidth = 7;
	private Square[][] board;

	public Board() {

		board = new Square[boardWidth][boardWidth];

		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardWidth; j++) {
				board[i][j] = Square.EMPTY;
			}
		}
	}

	/**
	 * 
	 * 
	 * 
	 * @param ship
	 *            : Ship object
	 * @param startPoint
	 *            : Point
	 * @param direction
	 *            : integer (key-code)
	 */
	public void SetShip(Ship ship, Point startPoint, int direction) {

		validPosition(ship, startPoint, direction);
		
		
		switch (direction) {
		case 1:
			for (int i = 0; i < ship.getSize(); i++) {

				board[startPoint.y - i][startPoint.x] = Square.OCCUPIED;
			}
			break;
		case 2:
			for (int i = 0; i < ship.getSize(); i++) {

				board[startPoint.y + i][startPoint.x] = Square.OCCUPIED;

			}
			break;
		case 3:
			for (int i = 0; i < ship.getSize(); i++) {

				board[startPoint.y][startPoint.x + i] = Square.OCCUPIED;

			}
			break;
		case 4:
			for (int i = 0; i < ship.getSize(); i++) {

				board[startPoint.y][startPoint.x - i] = Square.OCCUPIED;

			}
			break;

		default:
			break;
		}
	}

	public void printBoard() {
		for (int row = 0; row < boardWidth; row++) {
			for (int col = 0; col < boardWidth; col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
	}

	/**
	 * Checks so that the new ship does not overlap an existing one. (Only used
	 * in the SetShip method)
	 * 
	 * @param startPoint
	 * @param ship
	 * @return
	 */
	private boolean validPosition(Ship ship, Point startPoint, int direction) { 

		/*	
		 * TODO: Checks if there is a ship in any of the squares where the player is
		 * trying to put a ship. 
		 * 
		 * if or switch maybe...
		 *  
		 *  
		*/
		
		
		return true;
	}
	
}
