package game;

public enum Square {

	EMPTY("~"), OCCUPIED("[]"), HIT("X"), MISSED(" ");

	private String status;

	Square(String s) {
		status = s;
	}

	public String toString() {
		return status;
	}

}

