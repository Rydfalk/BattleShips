package menu;

import game.Game;
import game.Ship;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class SetUpPanel extends JPanel {
	
	private Game game;

	private JButton[][] grid;

	private JPanel contentPane;

	private JPanel gridPanel;

	private JPanel buttonPanel;
	private JButton verticalButton;
	private JButton horizontalButton;

	private JLabel activePlayerLabel;
	
	private Ship[] shipArray;
	
	private JButton ship1;
	private JButton ship2;
	private JButton ship3;
	private JButton ship4;
	private JButton ship5;
	
	
	private int yCoordinate;
	private int xCoordinate;
	private int width = 7;
	private int length = 7;

	public SetUpPanel(JPanel panel, Game gameObject) {
		
		contentPane = panel;

		game = gameObject;
		
		shipArray = new Ship[5];

		setOpaque(true);
		setBackground(Color.BLACK);
		setLayout(null);

		gridPanel = new JPanel(new GridLayout(width, length));
		grid = new JButton[width][length];
		for (yCoordinate = 0; yCoordinate < length; yCoordinate++) {
			for (xCoordinate = 0; xCoordinate < width; xCoordinate++) {
				grid[xCoordinate][yCoordinate] = new GridButton(xCoordinate,yCoordinate);

				grid[xCoordinate][yCoordinate]
						.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								  
//								 game.getActivePlayer().setShip(ship, startPoint, dir);
								 
							}
						});

				
				gridPanel.add(grid[xCoordinate][yCoordinate]);

			}
		}

		gridPanel.setBounds(130, 150, 250, 250);

		add(gridPanel);

		activePlayerLabel = new JLabel("");
		activePlayerLabel.setForeground(Color.WHITE);
		activePlayerLabel.setBounds(250, 100, 50, 50);
		add(activePlayerLabel);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setBounds(0, 400, 500, 100);
		
		verticalButton = new JButton("Vertical");
		verticalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				

				

			}
		});

		buttonPanel.add(verticalButton);
		
		horizontalButton = new JButton("Horizontal");
		horizontalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				

				

			}
		});

		buttonPanel.add(horizontalButton);
		
		add(buttonPanel);
		
		
		
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

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}
