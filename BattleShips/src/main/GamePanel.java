package main;

import java.awt.Color;

import game.Game;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

	
	private Game game;
	
	private JPanel contentPane;

	public GamePanel(JPanel contentPane) {
		this.contentPane = contentPane;
		
		setOpaque(true);
		setBackground(Color.BLACK);
		
	}
	

}
	