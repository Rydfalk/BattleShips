package menu;

import game.Game;
import game.Player;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGamePanel extends JPanel {

	private JPanel contentPane;
	private Game game;

	public EndGamePanel(JPanel panel, Game gameObject) {

		

		this.contentPane = panel;

		this.game = gameObject;
		
		Player player1 = game.getActivePlayer();
		game.nextActivePlayer();
		Player player2 = game.getActivePlayer();
		player1.saveStats();
		player2.saveStats();
		

		setOpaque(true);
		setBackground(Color.MAGENTA.darker().darker());
		JLabel victoryText = new JLabel("CONGRATULATIONS "
				+ game.getActivePlayer().getName() + "! YOU WON!");
		victoryText.setForeground(Color.WHITE);
		victoryText.setFont(new Font("Serif", Font.BOLD, 24));
		add(victoryText);
		

	}

}
