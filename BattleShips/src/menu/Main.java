package menu;

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
	private EndGamePanel endGamePanel;

	private Game game;

	private void displayGUI() {
		JFrame frame = new JFrame("BattleShips");
		
		game = new Game();
		JPanel contentPane = new JPanel();
		
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout());
		
		
		firstMenuPanel = new FirstMenu(contentPane);
		statisticsPanel = new StatisticsPanel(contentPane);
		playerPanel = new PlayerPanel(contentPane, game);
		endGamePanel = new EndGamePanel(contentPane,game);
		

		contentPane.add(firstMenuPanel, "First Menu Card");
		contentPane.add(statisticsPanel, "Statistics Card");
		contentPane.add(playerPanel, "Player Card");
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
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