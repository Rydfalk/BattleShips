package game;

public enum Direction {
	UP(1), DOWN(2), RIGHT(3), LEFT(4);
	
	private int status;
	
	Direction(int i){
		status = i;
	}
	
	public String toString(){
		return Integer.toString(status);
	}
}
