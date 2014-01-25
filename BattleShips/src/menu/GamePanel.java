package menu;

import game.Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private int xCoordinate;
	private int yCoordinate;
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
		for (yCoordinate = 0; yCoordinate < length; yCoordinate++) {
			for (xCoordinate = 0; xCoordinate < width; xCoordinate++) {
				grid[xCoordinate][yCoordinate] = new JButton();

				grid[xCoordinate][yCoordinate]
						.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								/*
								 * TODO: Something like this
								 * 
								 * game.getActivePlayer().registerButtonPressed(
								 * xCoordinate,yCoordinate);
								 */
							}
						});

				gridPanel.add(grid[xCoordinate][yCoordinate]);

			}
		}

		gridPanel.setBounds(130, 150, 250, 250);

		add(gridPanel, BorderLayout.CENTER);

		try {

			activePlayerLabel.setText((game.getPlayerName(1)));

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
