package game;

public class Ship {

	private int size;

	public Ship(int size) {

		this.size = size;

	}

	public int getSize() {
		return size;
	}

	public String toString() {
		return Integer.toString(size);
	}

}
