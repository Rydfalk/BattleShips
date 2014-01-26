package menu;

import game.Game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class PlayerPanel extends JPanel {
	
	private SetUpPanel setupPanel;
	private GamePanel gamePanel;

	private JButton backButton;
	private JButton newHumanButton;
	private JButton newAIButton;
	private JButton startGameButton;
	private JButton removeLastPlayer;
	private JButton deletePlayer;

	private JPanel contentPane;


	private JTextField inputField;

	private JTextArea playerDisplay;

	private JLabel statusMessagesText;

	private int numberOfPlayers = 0;

	private Game game;

	public PlayerPanel(JPanel panel, Game gameObject) {

		contentPane = panel;

		game = gameObject;

		setOpaque(true);
		setBackground(Color.BLUE.darker().darker());

		

		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.show(contentPane, "First Menu Card");

			}
		});

		add(backButton);

		newHumanButton = new JButton("New Player");
		newHumanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (inputField.getText().length() > 0) {

					game.addNewHumanPlayer(inputField.getText().trim());
					playerDisplay.append(inputField.getText() + "\n");
					numberOfPlayers++;

					if (numberOfPlayers == 2) {

						newHumanButton.setEnabled(false);
						newAIButton.setEnabled(false);
						statusMessagesText.setText("Start Game!");

					}
				} else {
					statusMessagesText.setText("You must write a name");
				}

			}
		});

		add(newHumanButton);

		newAIButton = new JButton("New AI");
		newAIButton.setEnabled(false); // Not usable until we have AI
		newAIButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * TODO: call "addNewAI-method"
				 */

				game.addNewAIPlayer(inputField.getText().trim());
				playerDisplay.append(inputField.getText() + "\n");
				numberOfPlayers++;

				if (numberOfPlayers == 2) {

					newHumanButton.setEnabled(false);
					newAIButton.setEnabled(false);

				}

			}
		});

		add(newAIButton);

		removeLastPlayer = new JButton("Remove Player");
		removeLastPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				statusMessagesText.setText(game.removeLastPlayer());

			}
		});
		add(removeLastPlayer);

		deletePlayer = new JButton("Delete Player");
		deletePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				statusMessagesText.setText(game.deletePlayer(inputField
						.getText()));

			}
		});
		add(deletePlayer);

		playerDisplay = new JTextArea(3, 20);
		playerDisplay.setEditable(false);

		add(playerDisplay);

		inputField = new JTextField(20);

		add(inputField);

		startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (numberOfPlayers == 2) {

					
					game.startGame();
					setupPanel = new SetUpPanel(contentPane, game);
					gamePanel = new GamePanel(contentPane,game);
					
					contentPane.add(setupPanel, "Setup Card");
					contentPane.add(gamePanel, "Game Card");
					CardLayout cardLayout = (CardLayout) contentPane
							.getLayout();
					cardLayout.show(contentPane, "Setup Card");
					
				}

			}
		});
		add(startGameButton);

		statusMessagesText = new JLabel("Add new players!");
		statusMessagesText.setForeground(Color.WHITE);
		add(statusMessagesText);
		
		JButton devButton = new JButton("DevButton");
		devButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);;
				
				
			}
		});
		add(devButton);

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}
