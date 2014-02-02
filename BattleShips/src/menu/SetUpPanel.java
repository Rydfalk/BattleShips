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
import javax.swing.UIManager;

class SetUpPanel extends JPanel {

	private Game game;

	private Player activePlayer;

	private GamePanel gamePanel;

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
	 * Variables for the button panels
	 */
	private JPanel buttonPanel;
	private JPanel shipButtonPane;
	private JPanel doneButtonPane;
	private JPanel directionButtonPane;
	private JButton verticalButton;
	private JButton horizontalButton;
	private JButton[] shipButtons;
	private JButton doneButton;

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

	private JButton activeDirectionButton;
	private JButton activeShipButton;
	private JButton nullPointerButton;

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

		nullPointerButton = new JButton();
		activeDirectionButton = nullPointerButton;
		activeShipButton = nullPointerButton;

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

		gridPanel.setBounds(130, 100, 250, 250);

		add(gridPanel);

		/*
		 * Labels to know who is the active player and what they should do next
		 */
		activePlayerLabel = new JLabel(activePlayer.getName());
		activePlayerLabel.setForeground(Color.WHITE);
		activePlayerLabel.setBounds(130, 50, 100, 50);
		add(activePlayerLabel);

		statusMessages = new JLabel("Place your ships!");
		statusMessages.setForeground(Color.WHITE);
		statusMessages.setBounds(230, 50, 200, 50);
		add(statusMessages);

		/*
		 * Setting up the button panel
		 */

		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setBounds(0, 350, 500, 100);
		buttonPanel.setLayout(new BorderLayout());

		directionButtonPane = new JPanel();
		directionButtonPane.setBackground(Color.BLACK);

		/*
		 * Setting up direction buttons
		 */
		verticalButton = new JButton("Vertical");
		verticalButton.setOpaque(true);
		verticalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeDirectionButton.setBackground(UIManager.getColor("Button.background"));

				activeDirection = Direction.UP;
				directionButtonPressed = true;
				setShipIfPossible();

				activeDirectionButton = verticalButton;
				verticalButton.setBackground(Color.RED);

			}
		});

		directionButtonPane.add(verticalButton);

		horizontalButton = new JButton("Horizontal");
		horizontalButton.setOpaque(true);
		horizontalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeDirectionButton.setBackground(UIManager.getColor("Button.background"));
				activeDirection = Direction.RIGHT;
				directionButtonPressed = true;
				activeDirectionButton = horizontalButton;
				horizontalButton.setBackground(Color.RED);
				setShipIfPossible();
			}
		});

		directionButtonPane.add(horizontalButton);

		/*
		 * Setting up Ship buttons and their panel
		 */

		shipButtonPane = new JPanel();
		shipButtonPane.setBackground(Color.BLACK);

		shipList = new ArrayList<Ship>();
		shipButtons = new JButton[5];
		for (shipIndex = 0; shipIndex < 5; shipIndex++) {
			shipSize = shipIndex + 1;

			shipList.add(new Ship(shipSize));

			shipButtons[shipIndex] = new JButton(shipSize + " Ship");
			shipButtons[shipIndex].setOpaque(true);

			shipButtons[shipIndex].addActionListener(new ActionListener() {

				private Ship buttonSpecificShip = shipList.get(shipIndex);
				private int index = shipIndex;

				public void actionPerformed(ActionEvent e) {
					activeShipButton.setBackground(UIManager.getColor("Button.background"));
					
					activeShip = buttonSpecificShip;
					shipButtonPressed = true;

					activeShipButton = shipButtons[index];
					shipButtons[index].setBackground(Color.GREEN);
					setShipIfPossible();
				}
			});

		}


		
		for (JButton button : shipButtons) {
			shipButtonPane.add(button);
		}

		doneButtonPane = new JPanel();
		doneButtonPane.setBackground(Color.BLACK);

		doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (activePlayer.getPlacedShips().size() == 5) {

					if (player1Done == false) {
						player1Done = true;

						game.nextActivePlayer();
						activePlayer = game.getActivePlayer();
						activeBoard = activePlayer.getBoard();
						updateLabels();
						updateGridColors();
						doneButton.setEnabled(false);
						for (JButton button : shipButtons) {
							button.setEnabled(true);
						}
						enableGridButtons();

					} else {
						CardLayout cardLayout = (CardLayout) contentPane
								.getLayout();
						cardLayout.show(contentPane, "Game Card");
						gamePanel = new GamePanel(contentPane, game);
						contentPane.add(gamePanel, "Game Card");

					}

				}
			}
		});

		doneButton.setEnabled(false);
		doneButtonPane.add(doneButton);

		buttonPanel.add(doneButtonPane, BorderLayout.CENTER);
		buttonPanel.add(directionButtonPane, BorderLayout.NORTH);
		buttonPanel.add(shipButtonPane, BorderLayout.SOUTH);
		add(buttonPanel);

		/*
		 * Development button
		 * 
		 * Removed when completed project
		 */
		JButton devButton = new JButton("DevButton");
		devButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Player player1 = game.getActivePlayer();
				game.nextActivePlayer();
				Player player2 = game.getActivePlayer();
				game.nextActivePlayer();
				
				if(!(player1.setShip(new Ship(1), new Point(0,1), Direction.RIGHT) ||
				player1.setShip(new Ship(1), new Point(0,3), Direction.RIGHT)||
				player1.setShip(new Ship(1), new Point(2,1), Direction.RIGHT)||
				player1.setShip(new Ship(1), new Point(2,3), Direction.RIGHT)||
				player1.setShip(new Ship(1), new Point(4,3), Direction.RIGHT))){
					System.out.println("Nepp1");
				}
				
				if(!(player2.setShip(new Ship(1), new Point(0,1), Direction.RIGHT) ||
				player2.setShip(new Ship(1), new Point(0,3), Direction.RIGHT)||
				player2.setShip(new Ship(1), new Point(2,1), Direction.RIGHT)||
				player2.setShip(new Ship(1), new Point(2,3), Direction.RIGHT)||
				player2.setShip(new Ship(1), new Point(4,3), Direction.RIGHT))){
					System.out.println("Nepp2");
				}
				
				//TABORT
				CardLayout cardLayout = (CardLayout) contentPane
						.getLayout();
				cardLayout.show(contentPane, "Game Card");
				gamePanel = new GamePanel(contentPane,game);
				contentPane.add(gamePanel, "Game Card");
				
				
				cardLayout = (CardLayout) contentPane.getLayout();
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
				&& shipButtonPressed == true
				&& !(game.getActivePlayer().getPlacedShips().size() == 5)) {

			if (game.getActivePlayer().setShip(activeShip,
					activeButtonCoordinate, activeDirection)) {
				coordinateButtonPressed = false;
				directionButtonPressed = false;
				shipButtonPressed = false;

				/*
				 * UNFINISHED!
				 * 
				 * TODO: Check who is active player, check if both players are
				 * done.
				 */

				updateGridColors();
				resetButtonColors();
				activeShipButton.setEnabled(false);

				disableGridButtons();
				if (game.getActivePlayer().getPlacedShips().size() == 5) {
					doneButton.setEnabled(true);
					resetButtonColors();

				}

				statusMessages.setText("Place another ship!");

			}

		} else {

			if (directionButtonPressed == false) {
				statusMessages.setText("Choose a direction!");
			}
			if (shipButtonPressed == false) {
				statusMessages.setText("Choose a ship!");
			}
			if (coordinateButtonPressed = false) {
				statusMessages.setText("Choose a coordinate!");
			}

			if (coordinateButtonPressed == true && shipButtonPressed == true
					&& directionButtonPressed == true) {
				statusMessages.setText("Can't put ship!");
			}
		}
	}

	private void disableGridButtons() {

		activeBoard = activePlayer.getBoard();

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				if (!activeBoard.isValidToOccupy(x, y)) {
					grid[x][y].setEnabled(false);
				}
			}
		}

	}

	private void enableGridButtons() {

		activeBoard = activePlayer.getBoard();

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {

				grid[x][y].setEnabled(true);

			}
		}

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
				} else {
					grid[x][y].setBackground(null);
				}
			}
		}

	}

	public void updateLabels() {
		activePlayerLabel.setText(activePlayer.getName());
	}

	public void resetButtonColors() {

		activeDirectionButton.setBackground(UIManager
				.getColor("Button.background"));

		activeShipButton.setBackground(UIManager.getColor("Button.background"));

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}
