import game.Direction;
import game.Player;
import game.Ship;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {

		JFrame mainFrame = new JFrame("Battleships");

		mainFrame.setSize(800, 600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

		JPanel firstMenu = new JPanel();
		mainFrame.getContentPane().add(firstMenu);
		
		firstMenu.setLayout(null);

		JButton gameButton = new JButton("Play Battleships");
		gameButton.setBounds(300, 250, 200, 30);
		gameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				
				Player player = new Player("zenkan");
				player.setShip(new Ship(2), new Point(0,0), Direction.RIGHT);
				System.out.println("BOAT 2");
				if(player.setShip(new Ship(3), new Point(3,0), Direction.DOWN )){
					System.out.println(true);
				}else{
					System.out.println(false);
				}
				
				
				
//				player.printBoard();
				
				/*
				 * TODO:
				 * 
				 * Ask for players
				 * 
				 * Start game 
				 */
			}
		});

		JButton statisticsButton = new JButton("View Player Statistics");
		statisticsButton.setBounds(300, 300, 200, 30);
		statisticsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				/*
	             * TODO:
	             * 
	             * Show statistics panel
	             *  
	             */
			}
		});
		
		
		firstMenu.add(gameButton);
		
		firstMenu.add(statisticsButton);
		
		
		
	}

}
