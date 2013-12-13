package main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame mainFrame = new JFrame("Battleships");
		
		mainFrame.setSize(800, 600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
		
	}

}
