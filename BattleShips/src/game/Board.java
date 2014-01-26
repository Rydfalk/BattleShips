package game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board {

	protected int boardWidth;
	private Square[][] board;
	
	private Ship[] placedShips;
	private Ship[] destroyedShips;
	
	private JPanel gridPanel;
	private JButton[][] grid;

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
	 * checks all the nearby squares. returns iff all adjecent squares are empty
	 * 
	 * @param point
	 * @return: boolean
	 * 
	 */
	public boolean isValidToOccupy(int x, int y) {
		Point point = new Point(x, y);
		if (!isOccupied(point)) {
			// Loops through all adjecent tiles
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					// if the square is not a valid square
					// (the startpoint can be in a corner)
					// then we do not need to test it
					if (isValidSquare(new Point(point.x - i, point.y - j))) {
						// Returns false if any of the adjecent tiles are
						// occupied
						if (isOccupied(point.x - i, point.y - j)) {
							return false;
						}
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the given point is a valid square on the board. That is iff
	 * (0,0) <= (x,y) < (boardWidht, boardWidth)
	 * 
	 * @param point
	 * @return : boolean iff (0,0) <= (x,y) < (boardWidht, boardWidth)
	 */
	public boolean isValidSquare(Point point) {
		return (point.x > -1 && point.y > -1 && point.x < boardWidth && point.y < boardWidth);
	}

	/**
	 * Sets the given coordinates to occupied
	 * 
	 * @param point
	 *            : Point object
	 */
	public void occupySquare(Point point) {
		board[point.y][point.x] = Square.OCCUPIED;
	}

	/**
	 * Sets the given coordinates to occupied
	 * 
	 * @param point
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
	 * Checks if the Square with the given x- and y- coordinates is occupied. If
	 * so then return true else return false
	 * 
	 * @param point
	 *            : Point object
	 * @return
	 */
	public boolean isOccupied(int x, int y) {
		return (board[y][x] == Square.OCCUPIED);
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
	
	public boolean isMissed(Point point){
		return (board[point.y][point.x] == Square.MISSED);
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
	
	/**
	 * Sets the given coordinates to missed
	 * 
	 * @param point
	 */
	public void missSquare(Point point){
		board[point.y][point.x] = Square.MISSED;
	}
	

	/******************* GETTERS AND SETTERS *******************/
	public int getBoardWidth() {
		return boardWidth;
	}

}

