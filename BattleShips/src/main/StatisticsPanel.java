package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class StatisticsPanel extends JPanel {

	private JButton backButton;
	private JPanel contentPane;

	public StatisticsPanel(JPanel panel) {
		contentPane = panel;

		setOpaque(true);
		setBackground(Color.GREEN.darker().darker());

		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.show(contentPane, "First Menu Card");
				
			}
		});

		add(backButton);
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}
}

