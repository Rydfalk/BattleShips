package main;

import game.Game;

import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

	private JPanel contentPane;
	private FirstMenu firstMenuPanel;
	private StatisticsPanel statisticsPanel;
	private PlayerPanel playerPanel;
	private GamePanel gamePanel;

	private Game game;

	private void displayGUI() {
		JFrame frame = new JFrame("BattleShips");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		game = new Game();

		JPanel contentPane = new JPanel();
		
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout());
		
		
		firstMenuPanel = new FirstMenu(contentPane);
		statisticsPanel = new StatisticsPanel(contentPane);
		playerPanel = new PlayerPanel(contentPane, game);
		gamePanel = new GamePanel(contentPane, game);
		
		contentPane.add(firstMenuPanel, "First Menu Card");
		contentPane.add(statisticsPanel, "Statistics Card");
		contentPane.add(playerPanel, "Player Card");
		contentPane.add(gamePanel, "Game Card");
		
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	public static void main(String... args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main().displayGUI();
			}
		});
	}
}