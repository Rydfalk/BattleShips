package game;

public class Ship {

	private int size;

	public Ship(){
		size = 1;
	}
	
	public Ship(int size) {

		this.size = size;

	}

	public int getSize() {
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}

	public String toString() {
		return Integer.toString(size);
	}

}
