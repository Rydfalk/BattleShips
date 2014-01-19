package game;

import java.awt.Point;

public class Board {

	protected int boardWidth;
	private Square[][] board;

	/**
	 * Constructors
	 */
	public Board() {
		boardWidth = 7;
		createBoard();
	}

	public Board(int boardWidth) {
		this.boardWidth = boardWidth;
		createBoard();
	}

	/******************* PRIVATE METHODS *******************/
	/**
	 * Creates the board with a boardWidth x boardWidth Squares
	 * 
	 */
	private void createBoard() {
		board = new Square[boardWidth][boardWidth];

		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardWidth; j++) {
				board[i][j] = Square.EMPTY;
			}
		}
	}

	/******************* PUBLIC METHODS *******************/

	/**
	 * Prints board
	 */
	public void printBoard() {
		for (int row = 0; row < boardWidth; row++) {
			for (int col = 0; col < boardWidth; col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
	}

	/**
	 * Sets the given coordinates to occupied
	 * 
	 *@param point
	 *            : Point object
	 */
	public void occupySquare(Point point) {
		board[point.y][point.x] = Square.OCCUPIED;
	}
	
	/**
	 * Sets the given coordinates to occupied
	 * 
	 *@param point
	 *            : Point object
	 */
	public void occupySquare(int x, int y) {
		board[y][x] = Square.OCCUPIED;
	}

	/**
	 * Checks if the Square with the given x- and y- coordinates is occupied. If
	 * so then return true else return false
	 * 
	 * @param point
	 *            : Point object
	 * @return
	 */
	public boolean isOccupied(Point point) {
		return (board[point.y][point.x] == Square.OCCUPIED);
	}

	/**
	 * Checks if the Square with the given x- and y- coordinates is empty. If so
	 * then return true else return false
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isEmpty(int x, int y) {
		return (board[y][x] == Square.EMPTY);
	}

	/**
	 * Checks if the Square with the given x- and y- coordinates is empty. If so
	 * then return true else return false
	 * 
	 * @param point
	 *            : Point object
	 * @return
	 */
	public boolean isEmpty(Point point) {
		return (board[point.y][point.x] == Square.EMPTY);
	}

	/**
	 * Checks if the Square with the given x- and y- coordinates is hit. If so
	 * then return true else return false
	 * 
	 * @param point
	 *            : Point object
	 * @return
	 */
	public boolean isHit(Point point) {
		return (board[point.y][point.x] == Square.HIT);
	}

	/**
	 * Sets the given coordinates to hit
	 * 
	 * @param point
	 *            : Point object
	 */
	public void hitSquare(Point point) {
		board[point.y][point.x] = Square.HIT;
	}

	/******************* GETTERS AND SETTERS *******************/
	public int getBoardWidth() {
		return boardWidth;
	}

}
