package main;

import game.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

	
	private Game game;
	
	private JButton[][] grid;
	
	private JPanel contentPane;
	
	private JPanel gamePanel;
	
	private JPanel gridPanel;
	
	private JLabel activePlayerLabel;
	
	
	
	
	private int width = 7;
	private int length = 7;
	
	public GamePanel(JPanel panel) {
		contentPane = panel;

		setOpaque(true);
		setBackground(Color.BLACK);
		setLayout(null);

		gridPanel = new JPanel(new GridLayout(width,length));
		gridPanel.setMinimumSize(getPreferredSize());
		grid = new JButton[width][length];
		for (int y = 0; y < length ; y++) {
			for (int x = 0; x < width; x++) {
				grid[x][y] = new JButton();
				gridPanel.add(grid[x][y]);
			}
		}
	
		gridPanel.setBounds(130,150,250,250);
		
		add(gridPanel, BorderLayout.CENTER);
		
		activePlayerLabel = new JLabel(game.getActivePlayer());
		activePlayerLabel.setForeground(Color.WHITE);
		activePlayerLabel.setBounds(250, 100, 50, 50);
		add(activePlayerLabel);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}

}
	