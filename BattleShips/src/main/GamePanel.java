package main;

import game.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class GamePanel extends JPanel {

	private Game game;

	private JButton[][] grid;

	private JPanel contentPane;

	private int numberOfPlayers = 0;

	private JPanel gridPanel;

	private JLabel activePlayerLabel;

	private int width = 7;
	private int length = 7;

	public GamePanel(JPanel panel, Game gameObject) {
		contentPane = panel;
		
		game = gameObject;

		setOpaque(true);
		setBackground(Color.BLUE.darker().darker());

		gridPanel = new JPanel(new GridLayout(width, length));
		gridPanel.setMinimumSize(getPreferredSize());
		grid = new JButton[width][length];
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				grid[x][y] = new JButton();
				gridPanel.add(grid[x][y]);

			}
		}

		gridPanel.setBounds(130, 150, 250, 250);

		add(gridPanel, BorderLayout.CENTER);

		try {

			activePlayerLabel.setText((game.getPlayer(1)));

		} catch (Exception e) {
			System.out.println("Could not find the player");
		}

		activePlayerLabel = new JLabel("");
		activePlayerLabel.setForeground(Color.WHITE);
		activePlayerLabel.setBounds(250, 100, 50, 50);
		add(activePlayerLabel);

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}
