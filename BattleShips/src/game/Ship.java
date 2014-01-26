package game;

import java.awt.Point;

public class Ship {

	private int size;
	private Point[] position;

	public Ship() {
		size = 1;
		position = new Point[size];
	}

	public Ship(int size) {

		this.size = size;
		position = new Point[size];

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String toString() {
		return Integer.toString(size);
	}

	/**
	 * Adding this would allow us to ask where the ship is and keep track of
	 * which ships have been shot down.
	 * 
	 * ---------- UNFINISHED ----------
	 * 
	 * @param start
	 * @param end
	 * @param dir
	 */
	public void setPosition(Point start, Point end, Direction dir) {

		if (size == 1) {
			position[0] = start;

		} else {

			position[0] = start;
			position[size - 1] = end;

		}

	}

}
