package menu;

import game.Board;
import game.Game;
import game.Player;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private Game game;
	private Board board = new Board();
	private JPanel gridPanel;
	private JPanel contentPane;
	private SetupGridButton[][] grid;
	private int yCoordinate;
	private int xCoordinate;
	private int width = 7;

	private Player player1;
	private Player player2;
	private Game gameObj;

	// Labels
	JLabel topMessage;
	JLabel playersTurnMessage;

	// To handle ActionListener
	private boolean activeActionListener;

	public GamePanel(JPanel panel, Game gameObject) {

		contentPane = panel;
		gameObj = gameObject;

		setOpaque(true);
		setBackground(Color.BLUE.darker().darker());
		setLayout(null);

		topMessage = new JLabel("Time to make a move");
		add(topMessage);

		JButton devButton = new JButton("DevButton");
		devButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);
				;

			}
		});

		devButton.setBounds(400, 400, 20, 20);
		add(devButton);

		player1 = gameObj.getActivePlayer();
		gameObj.nextActivePlayer();
		player2 = gameObj.getActivePlayer();

		playersTurnMessage = new JLabel("It's " + player1.getName() + "'s turn");
		playersTurnMessage.setForeground(Color.WHITE);
		playersTurnMessage.setBounds(130, 50, 100, 50);
		add(playersTurnMessage);

		gridPanel = new JPanel(new GridLayout(width, width));
		grid = new SetupGridButton[width][width];
		for (yCoordinate = 0; yCoordinate < width; yCoordinate++) {
			for (xCoordinate = 0; xCoordinate < width; xCoordinate++) {
				grid[xCoordinate][yCoordinate] = new SetupGridButton(
						xCoordinate, yCoordinate);

				// If the square is hit it should shine red
				if (player2.getBoard().isHit(
						grid[xCoordinate][yCoordinate].getCoordinates())) {

					grid[xCoordinate][yCoordinate].setBackground(Color.RED);
					grid[xCoordinate][yCoordinate].setEnabled(false);
				}
				// If the square is missed it should be blue
				else if (player2.getBoard().isMissed(
						grid[xCoordinate][yCoordinate].getCoordinates())) {

					grid[xCoordinate][yCoordinate].setBackground(Color.BLUE);
					grid[xCoordinate][yCoordinate].setEnabled(false);

				}
				// if the square is nether hit nor missed we add a
				// ActionListener to it so it can be hit or missed
				else {
					grid[xCoordinate][yCoordinate]
							.addActionListener(new ActionListener() {

								Point coordinate = new Point(xCoordinate,
										yCoordinate);

								public void actionPerformed(ActionEvent e) {
									// Register the move and change the status
									// of the klicked square
									player1.makeMove(player2, coordinate);

									allButtonsEnabled(false);
									updateBoard(player2, player1);

								}
							});
				}

				gridPanel.add(grid[xCoordinate][yCoordinate]);

			}
		}

		gridPanel.setBounds(130, 150, 250, 250);
		add(gridPanel);

		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Switch the players around
				player1 = player2;
				gameObj.nextActivePlayer();
				player2 = gameObj.getActivePlayer();

				updateBoard(player2, player1);
				allButtonsEnabled(true);
			}
		});
		doneButton.setBounds(100, 400, 200, 20);

		add(doneButton);

	}

	private void updateBoard(Player player1, Player player2) {

		Board activeBoard = player1.getBoard();

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				if (activeBoard.isHit(new Point(x, y))) {
					grid[x][y].setBackground(Color.RED);
				} else if (activeBoard.isMissed(new Point(x, y))) {
					grid[x][y].setBackground(Color.BLUE);
				} else {
					grid[x][y].setBackground(null);
				}
			}
		}
	}

	private void allButtonsEnabled(boolean b) {
		Board activeBoard = player2.getBoard();
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				if (!activeBoard.isHit(new Point(x, y))
						&& !activeBoard.isMissed(new Point(x, y))) {
					grid[x][y].setEnabled(b);
				} else {
					grid[x][y].setEnabled(false);
				}

			}
		}
	}

}