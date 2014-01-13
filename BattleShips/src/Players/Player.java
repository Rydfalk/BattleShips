package Players;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseObject;

public class Player extends DatabaseObject{
	
	
	
	
	
	/******************* CLASS VARIABLES *******************/
	private String name;
	private int wins, losses;
	
	/**
	 * Is the name of this class database table
	 */
	private String databaseTableName = "statistics";
	
	/**
	 * The columns and types for the database table
	 */
	private String[][] databaseColumns = {
			{"id", "int"},
			{"name", "varchar(16)"},
			{"wins", "int"},
			{"losses", "int"}
	};
	
	
	
	
	
	/******************* CONSTRUCTORS *******************/	
	public Player(String name){
		super("Battleships");
		
		if(!tableExists(databaseTableName)){
			
			createTable(databaseTableName, databaseColumns);
		}
		
		if(!playerExists(name)){
			
			//If the user does not exist yet
			createPlayer(name);
			
		}
		
		getPlayer(name);
		
	}
	
	
	
	
	
	/******************* PRIVATE METHODS *******************/	
	private void createPlayer(String name){
		String sql = "INSERT INTO "
				+ databaseTableName 
				+ " (name, wins, losses) VALUES ("
				+ name + ", 0, 0)";
		write(sql);

	}
	
	private void getPlayer(String name){
		String sql = "SELECT name, wins, losses  FROM statistics WHERE name = '"
				+ name + "'";
		ResultSet rs = read(sql);
		try {
			this.name = rs.getString("name");
			this.wins = rs.getInt("wins");
			this.losses = rs.getInt("losses");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private boolean playerExists(String name){
		if(tableExists("statistics")){
			String sql = "SELECT count(*) FROM statistics WHERE name='"
					+ name +"'";
			ResultSet rs = read(sql);
			try{
				//Returns true if the count of matched tables are one or more
				return rs.getInt("count(*)") > 0 ? true : false;
			}catch(SQLException e){
				handleError(e);
			}
		}
		return false;
		
	}
	
	
	/******************* PROTECTED METHODS *******************/
	
	
	
	/******************* PUBLIC METHODS *******************/
	public void saveStats(){
		String sql = 
				"UPDATE "
				+ databaseTableName 
				+ "SET "
				+ "wins = " + wins + ", "
				+ "losses = " + losses
				+ "WHERE name = '"
				+ name + "'";
		write(sql);	
		
	}
	
	
	/******************* Getters and Setters *******************/
	
	
	
	
}
