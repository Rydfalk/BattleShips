package game;

import java.awt.Point;
import java.util.Scanner;

public class Game {
	
	private Scanner scanner = new Scanner(System.in);
	
	private Point startCoordinate;
	
	private int direction;
	
	private Player[] players = new Player[2];
	

	
	
	public static void main(String[] args) {

		Ship ship1 = new Ship(1);
		Ship ship2 = new Ship(2);
		Ship ship3 = new Ship(3);
		Ship ship4 = new Ship(4);

		
		
		
		
		

	}
	
	
	public void newPlayer(String name){
		Player player = new Player(name);
		
		
	}
	
	public void newAI(){
		
	}
}
