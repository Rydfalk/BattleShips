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
	
	private JPanel playerButtonsPanel;
	private JPanel textFieldPanel;
	private JPanel gameButtonsPanel;
	
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
		
		playerButtonsPanel = new JPanel();
		playerButtonsPanel.setBackground(Color.BLUE.darker().darker());
		
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

		playerButtonsPanel.add(newHumanButton);

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

		playerButtonsPanel.add(newAIButton);

		removeLastPlayer = new JButton("Remove Player");
		removeLastPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				statusMessagesText.setText(game.removeLastPlayer());

			}
		});
		playerButtonsPanel.add(removeLastPlayer);

		deletePlayer = new JButton("Delete Player");
		deletePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				statusMessagesText.setText(game.deletePlayer(inputField
						.getText()));

			}
		});
		playerButtonsPanel.add(deletePlayer);
		
		
		
		
		
		textFieldPanel = new JPanel();
		textFieldPanel.setSize(500,300);
		textFieldPanel.setBackground(Color.BLUE.darker().darker());
		
		statusMessagesText = new JLabel("Add new players!");
		statusMessagesText.setForeground(Color.WHITE);
		textFieldPanel.add(statusMessagesText);
		
		playerDisplay = new JTextArea(3, 10);
		playerDisplay.setEditable(false);

		textFieldPanel.add(playerDisplay);

		inputField = new JTextField(15);

		textFieldPanel.add(inputField);
		
		
		gameButtonsPanel = new JPanel();
		gameButtonsPanel.setBackground(Color.BLUE.darker().darker());
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.show(contentPane, "First Menu Card");

			}
		});

		gameButtonsPanel.add(backButton);

		startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (numberOfPlayers == 2) {

					setupPanel = new SetUpPanel(contentPane, game);
					
					
					contentPane.add(setupPanel, "Setup Card");
					
					
					CardLayout cardLayout = (CardLayout) contentPane
							.getLayout();
					cardLayout.show(contentPane, "Setup Card");
					
				}

			}
		});
		gameButtonsPanel.add(startGameButton);

		
		
		JButton devButton = new JButton("DevButton");
		devButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);;
				
				
			}
		});
		gameButtonsPanel.add(devButton);
		
		add(playerButtonsPanel);
		add(textFieldPanel);
		add(gameButtonsPanel);
		

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}
