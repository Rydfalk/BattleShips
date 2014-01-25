package jpanelsandbuttons;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Database.DatabaseObject;

class StatisticsPanel extends JPanel {

	private JButton backButton;
	private JPanel contentPane;

	private DatabaseObject dbo = new DatabaseObject();

	public StatisticsPanel(JPanel panel) {
		contentPane = panel;

		setOpaque(true);
		setBackground(Color.GREEN.darker().darker());

		/*
		 * Crates the header lable and adds it to the panel
		 */
		JLabel label = new JLabel("<html>Statistik: TOP 10<br></html>");
		label.setFont(new Font("Serif", Font.BOLD, 24));
		label.setForeground(Color.BLACK);
		add(label);
		
		/*
		 * Creates the back button
		 */
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.show(contentPane, "First Menu Card");

			}
		});
		/**
		 * Selects all data and calculates a value based on wins/losses The
		 * higher the value is the higher rank the player gets
		 * 
		 * Displays only the top 10 results
		 */
		String sql = "SELECT"
				+ "	("
				+ "SELECT "
				+ "count(*) -1 "
				+ "FROM statistics as stats2 "
				+ "WHERE "
				+ "cast(case when stats2.losses is 0 then 99999 else stats2.wins*1.0/stats2.losses end as float) >= "
				+ "(case when stats1.wins is 0 then 0 else "
				+ "cast(case when stats1.losses is 0 then 99999 else stats1.wins*1.0/stats1.losses end as float) "
				+ "end)), "
				+ "name, "
				+ "wins, "
				+ "losses, "
				+ "hit_percentage, "
				+ "case when wins is 0 then 0 else "
				+ "cast(case when losses is 0 then 99999 else wins*1.0/losses end as float) "
				+ "end as test " + "FROM " + "statistics as stats1 "
				+ "ORDER BY " + "test DESC";

		ResultSet rs = dbo.read(sql);

		/*
		 * Creates the JTable out of the sql results
		 * and then we add the table to the panel
		 */
		JTable testTable = new JTable(buildTableModel(rs));
		testTable.setGridColor(Color.black);
		testTable.setBackground(Color.white.darker().darker());
		testTable.setCellSelectionEnabled(true);
		add(testTable);

		/*
		 * Adds the back-button to the panel
		 */
		add(backButton);

		/*
		 * Closes the databaseconnection
		 */
		dbo.closeConnection();
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(500, 500));
	}

	public static DefaultTableModel buildTableModel(ResultSet rs) {
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> columnNames = new Vector<String>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();

			// names of columns
			int columnCount = metaData.getColumnCount();
			for (int column = 1; column <= columnCount; column++) {
				columnNames.add(metaData.getColumnName(column));
			}

			// data of the table
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					vector.add(rs.getObject(columnIndex));
				}
				data.add(vector);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new DefaultTableModel(data, columnNames);

	}
}
