package menu;

import game.Board;
import game.Game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class SetUpPanel extends JPanel {
	
	private Game game;
	
	private Board board;

	private JButton[][] grid;

	private JPanel contentPane;

	private JPanel gridPanel;

	private JPanel buttonPanel;

	private JLabel activePlayerLabel;

	private int width = 7;
	private int length = 7;

	public SetUpPanel(JPanel panel, Game gameObject) {
		
		contentPane = panel;

		game = gameObject;
		
		board = new Board();

		setOpaque(true);
		setBackground(Color.BLACK);
		setLayout(null);

		gridPanel = board.getGridPanel(this);
		
		gridPanel.setVisible(true);
		
		add(gridPanel);

		try {

			activePlayerLabel.setText((game.getPlayerName(1)));

		} catch (Exception e) {
			System.out.println("Could not find the player");
		}

		activePlayerLabel = new JLabel("");
		activePlayerLabel.setForeground(Color.WHITE);
		activePlayerLabel.setBounds(250, 100, 50, 50);
		add(activePlayerLabel);
		
		JButton devButton = new JButton("DevButton");
		devButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);;
				
			}
		});
		
		devButton.setBounds(400, 400, 20, 20);
		add(devButton);
		
		

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}
