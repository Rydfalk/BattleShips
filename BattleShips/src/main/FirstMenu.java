package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



class FirstMenu extends JPanel {
	private JButton statisticsButton;
	private JButton gameButton;
	private JPanel contentPane;
/*************************** Constructors ******************************/
	
/*************************** Public Methods ******************************/
	public FirstMenu(JPanel panel) {
		contentPane = panel;
		setOpaque(true);
		setBackground(Color.RED.darker().darker());
		
		statisticsButton = new JButton("Statistics");
		statisticsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.show(contentPane, "Statistics Card" );
				
			}
		});
		add(statisticsButton);
		
		
		gameButton = new JButton("Battleships");
		gameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.show(contentPane, "Player Card" );
				
			}
		});
		add(gameButton);
	}
	
	

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}
