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

	public GamePanel(JPanel panel, Game gameObj) {

		contentPane = panel;

		setOpaque(true);
		setBackground(Color.BLUE.darker().darker());
		setLayout(null);

		player1 = gameObj.getActivePlayer();
		gameObj.nextActivePlayer();
		player2 = gameObj.getActivePlayer();
		
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

		do {

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

					}
					// If the square is missed it should be blue
					else if (player2.getBoard().isMissed(
							grid[xCoordinate][yCoordinate].getCoordinates())) {

						grid[xCoordinate][yCoordinate]
								.setBackground(Color.BLUE);

					}
					// if the square is nether hit nor missed we add a
					// ActionListener to it so it can be hit or missed
					else {
						grid[xCoordinate][yCoordinate]
								.addActionListener(new ActionListener() {

									Point coordinate = new Point(xCoordinate,
											yCoordinate);

									public void actionPerformed(ActionEvent e) {

										player1.makeMove(player2, coordinate);
										remove(gridPanel);
									}
								});
					}

					gridPanel.add(grid[xCoordinate][yCoordinate]);

				}
			}

			
			
			
			gridPanel.setBounds(130, 150, 250, 250);
			add(gridPanel);
			
			
			
		} while (true);



	}

}
