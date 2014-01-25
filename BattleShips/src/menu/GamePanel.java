package menu;

import game.Board;
import game.Game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private Game game;
	private Board board = new Board();
	private JPanel contentPane;
	private JPanel gridPanel;
	
	public GamePanel(JPanel panel, Game gameObject){
		
		contentPane = panel;

		game = gameObject;

		gridPanel = board.getGridPanel(this);
		
		setOpaque(true);
		setBackground(Color.BLUE.darker().darker());
		setLayout(null);
		
		
		add(gridPanel);
		
		
		JButton devButton = new JButton("DevButton");
		devButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);;
				
			}
		});
		
		devButton.setBounds(400,400,20,20);
		add(devButton);
		
		
	}

}
