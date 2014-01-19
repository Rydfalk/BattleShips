package game;

public enum Square {

	EMPTY("~"), OCCUPIED("[]"), HIT("X"), MISS(" ");

	private String status;

	Square(String s) {
		status = s;
	}

	public String toString() {
		return status;
	}

}

