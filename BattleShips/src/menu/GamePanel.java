package menu;

import java.awt.Color;

import game.Game;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private Game game;
	private JPanel contentPane;
	
	public GamePanel(JPanel panel, Game gameObject){
		
		contentPane = panel;

		game = gameObject;

		setOpaque(true);
		setBackground(Color.BLUE.darker().darker());
		
		
		
		
	}

}
