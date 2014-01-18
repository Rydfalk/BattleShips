package Players;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseObject;

public class Player{
	
	
	
	
	
	/******************* CLASS VARIABLES *******************/
	private String name;
	private int wins, losses;
	private DatabaseObject dbo;
	
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
		
		dbo = new DatabaseObject();
		
		if(!dbo.tableExists(databaseTableName)){
			System.out.println("HEJ");
			dbo.createTable(databaseTableName, databaseColumns);
			
		}
		
		if(!playerExists(name)){

			//If the user does not exist yet
			createPlayer(name);
			System.out.println("HELLO");
			
		}
		getPlayer(name);
		dbo.closeConnection();
		System.out.println("Wins: " + wins);
		System.out.println("Losses: " + losses);
		System.out.println("Name: " + name);
		
	}
	
	
	
	
	
	/******************* PRIVATE METHODS *******************/	
	private void createPlayer(String name){
		dbo = new DatabaseObject();
		String sql = "INSERT INTO "
				+ databaseTableName 
				+ " (name, wins, losses) VALUES ('"
				+ name + "', 0, 0)";
		
		dbo.write(sql);
		dbo.closeConnection();

	}
	
	
	
	private void getPlayer(String name){
		dbo = new DatabaseObject();
		String sql = "SELECT name, wins, losses  FROM statistics WHERE name = '"
				+ name + "'";
		
		try {
			ResultSet rs = dbo.read(sql);
			this.name = rs.getString("name");
			this.wins = rs.getInt("wins");
			this.losses = rs.getInt("losses");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbo.closeConnection();
	}
	
	
	
	
	private boolean playerExists(String name){
		if(dbo.tableExists("statistics")){
			
			String sql = "SELECT count(*) FROM statistics WHERE name='"
					+ name +"'";	
			try{
				ResultSet rs = dbo.read(sql);
				dbo.closeConnection();
				//Returns true if the count of matched tables are one or more
				if(rs.getInt("count(*)") > 0){
					return true;
				}
				dbo.closeConnection();
			}catch(SQLException e){
				dbo.handleError(e);
			}
		}
		return false;
		
	}
	
	
	/******************* PROTECTED METHODS *******************/
	
	
	
	/******************* PUBLIC METHODS *******************/
	public void saveStats(){
		dbo = new DatabaseObject();
		String sql = 
				"UPDATE "
				+ databaseTableName 
				+ " SET "
				+ "wins = " + wins + ", "
				+ "losses = " + losses
				+ " WHERE name = '"
				+ name + "'";
		dbo.write(sql);
		
			
		dbo.closeConnection();
	}
	
	/**
	 * Registrates a win
	 */
	public void registerWin(){
		wins++;
	}
	
	
	/**
	 * Registrates a loss
	 */
	public void registerLoss(){
		losses++;
	}

	
	
	/******************* Getters and Setters *******************/
	





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public int getWins() {
		return wins;
	}





	public void setWins(int wins) {
		this.wins = wins;
	}





	public int getLosses() {
		return losses;
	}





	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	
}
