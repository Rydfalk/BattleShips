package menu;

import game.Board;
import game.Direction;
import game.Game;
import game.Player;
import game.Ship;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class SetUpPanel extends JPanel {

	private Game game;

	private Player activePlayer;

	private JPanel contentPane;

	/*
	 * Grid related variables
	 */
	private JPanel gridPanel;
	private SetupGridButton[][] grid;
	private int yCoordinate;
	private int xCoordinate;
	private int width = 7;

	/*
	 * Variables for the button panel
	 */
	private JPanel buttonPanel;
	private JPanel southButtonPane;
	private JPanel northButtonPane;
	private JButton verticalButton;
	private JButton horizontalButton;
	private JButton[] shipButtons;
	

	/*
	 * Labels to tell messages
	 */

	private JLabel statusMessages;
	private JLabel activePlayerLabel;

	/*
	 * Variables for the ship placement logic
	 */
	Board activeBoard;
	private List<Ship> shipList;
	private int shipSize;
	private int shipIndex;
	
	private Point activeButtonCoordinate;
	private Direction activeDirection;
	private Ship activeShip;
	
	private boolean player1Done = false;
	private boolean player2Done = false;

	private boolean coordinateButtonPressed = false;
	private boolean directionButtonPressed = false;
	private boolean shipButtonPressed = false;

	public SetUpPanel(JPanel panel, Game gameObject) {

		contentPane = panel;

		game = gameObject;
		
		activePlayer = game.getActivePlayer();
		activeBoard = activePlayer.getBoard();

		setOpaque(true);
		setBackground(Color.BLACK);
		setLayout(null);

		/*
		 * Setting up the panel for grid buttons and the buttons in it
		 */

		gridPanel = new JPanel(new GridLayout(width, width));
		grid = new SetupGridButton[width][width];
		for (yCoordinate = 0; yCoordinate < width; yCoordinate++) {
			for (xCoordinate = 0; xCoordinate < width; xCoordinate++) {
				grid[xCoordinate][yCoordinate] = new SetupGridButton(
						xCoordinate, yCoordinate);

				grid[xCoordinate][yCoordinate]
						.addActionListener(new ActionListener() {

							Point coordinate = grid[xCoordinate][yCoordinate]
									.getCoordinates();

							public void actionPerformed(ActionEvent e) {

								activeButtonCoordinate = coordinate;
								coordinateButtonPressed = true;
								setShipIfPossible();

							}
						});

				gridPanel.add(grid[xCoordinate][yCoordinate]);

			}
		}

		gridPanel.setBounds(130, 150, 250, 250);

		add(gridPanel);

		/*
		 * Labels to know who is the active player and what they should do next
		 */
		activePlayerLabel = new JLabel(activePlayer.getName());
		activePlayerLabel.setForeground(Color.WHITE);
		activePlayerLabel.setBounds(130, 100, 100, 50);
		add(activePlayerLabel);

		statusMessages = new JLabel("Place your ships!");
		statusMessages.setForeground(Color.WHITE);
		statusMessages.setBounds(230, 100, 100, 50);
		add(statusMessages);

		/*
		 * Setting up the button panel
		 */

		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setBounds(0, 400, 500, 100);
		buttonPanel.setLayout(new BorderLayout());

		southButtonPane = new JPanel();
		southButtonPane.setBackground(Color.BLACK);
		northButtonPane = new JPanel();
		northButtonPane.setBackground(Color.BLACK);

		/*
		 * Setting up direction buttons
		 */
		verticalButton = new JButton("Vertical");
		verticalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				activeDirection = Direction.UP;
				directionButtonPressed = true;
				setShipIfPossible();

			}
		});

		northButtonPane.add(verticalButton);

		horizontalButton = new JButton("Horizontal");
		horizontalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				activeDirection = Direction.RIGHT;
				directionButtonPressed = true;
				setShipIfPossible();

			}
		});

		northButtonPane.add(horizontalButton);

		/*
		 * Setting up Ship buttons
		 */

		shipList = new ArrayList<Ship>();
		shipButtons = new JButton[5];
		for (shipIndex = 0; shipIndex < 5; shipIndex++) {
			shipSize = shipIndex + 1;

			shipList.add(new Ship(shipSize));

			shipButtons[shipIndex] = new JButton(shipSize + " Ship");

			shipButtons[shipIndex].addActionListener(new ActionListener() {

				private int buttonSpecificShipSize = shipSize;
				private Ship buttonSpecificShip = shipList.get(shipIndex);

				public void actionPerformed(ActionEvent e) {

					activeShip = buttonSpecificShip;
					shipButtonPressed = true;
					setShipIfPossible();

				}
			});

		}

		for (JButton button : shipButtons) {
			southButtonPane.add(button);
		}

		buttonPanel.add(northButtonPane, BorderLayout.NORTH);
		buttonPanel.add(southButtonPane, BorderLayout.CENTER);
		add(buttonPanel);

		/*
		 * Development button
		 * 
		 * Removed when completed project
		 */
		JButton devButton = new JButton("DevButton");
		devButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);

			}
		});

		devButton.setBounds(400, 400, 20, 20);
		add(devButton);

	}

	/**
	 * Called when a button is pressed that has to do with the ship placement.
	 * If all buttons have been pressed (one direction, one ship and one
	 * position) it will call the active players setShip method, reset all the
	 * button flags and update the grid colors
	 * 
	 */
	private void setShipIfPossible() {
		if (coordinateButtonPressed == true && directionButtonPressed == true
				&& shipButtonPressed == true) {

			if (game.getActivePlayer().setShip(activeShip,
					activeButtonCoordinate, activeDirection)) {
				coordinateButtonPressed = false;
				directionButtonPressed = false;
				shipButtonPressed = false;

				/*
				 * UNFINISHED!
				 * 
				 * TODO: Check who is active player, check if both players are done.
				 */
				updateGridColors();
				if(shipList.size() == 5){
					
					game.nextActivePlayer();
					activePlayer = game.getActivePlayer();
					activeBoard = activePlayer.getBoard();
				}
				
				
			}
			

		}
		
		System.out.println("NOT POSSIBLE!");

	}

	/**
	 * If a specific position is occupied it will be black
	 */
	private void updateGridColors() {
		activeBoard = activePlayer.getBoard();

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				if (activeBoard.isOccupied(x, y)) {
					grid[x][y].setBackground(Color.BLACK);
				}
			}
		}

	}

	public void updateLabels() {
		activePlayerLabel = new JLabel(activePlayer.getName());
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}
