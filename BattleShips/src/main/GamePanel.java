package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class GamePanel extends JPanel {

	private JButton backButton;
	private JButton newHumanButton;
	private JButton newAIButton;
	private JPanel contentPane;
	
	private JTextField inputField;
	
	
	private JTextArea playerDisplay;

	private int numberOfPlayers = 0;

	public GamePanel(JPanel panel) {
		contentPane = panel;

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

		newHumanButton = new JButton("New Human Player");
		newHumanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputField.setEnabled(true);
				/*
				 * TODO: call "addNewHuman-method"
				 */
				numberOfPlayers++;

				if (numberOfPlayers == 2) {

					/*
					 * Start game
					 */

				}
				
				inputField.setEnabled(false);
			}
		});

		add(newHumanButton);

		newAIButton = new JButton("New AI Player");
		newAIButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputField.setEnabled(true);

				/*
				 * TODO: call "addNewAI-method"
				 */
				numberOfPlayers++;

				if (numberOfPlayers == 2) {
					
					/*
					 * Start game
					 */
				}
				
				inputField.setEnabled(false);

			}
		});

		add(newAIButton);
		
		playerDisplay = new JTextArea(2,20);
		add(playerDisplay);
		
		inputField = new JTextField(20);
		inputField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				playerDisplay.append(inputField.getText() + "\n");
				
				
			}
		});
		add(inputField);
		
		
		
		
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}
