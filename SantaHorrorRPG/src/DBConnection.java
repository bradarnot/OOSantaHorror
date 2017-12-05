import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* Steps to make the DatabaseConnection work
 * 
 * 1. Need MySQL installed
 * 
 * 2. Create a new user:
 *    CREATE USER 'santa'@'localhost' IDENTIFIED BY 'password';
 * 
 */

public class DBConnection {
	
	private String databaseName;
	private String user;
	private String password;
	
	public DBConnection(String database, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		setDatabaseName(database);
		setUser(user);
		setPassword(password);
		
		createDBIfNotExists();
		createTablesIfNotExists();
	}
	
	private void createDatabase() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", this.user, this.password);
			Statement statement = null;
			statement = conn.createStatement();
			String sql = "CREATE DATABASE " + this.databaseName;
			statement.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	private void createDBIfNotExists() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.databaseName, this.user, this.password);
			conn.close();
			System.out.println("Database already exists.");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1049) {
				System.out.println("Database does not exist. Creating database...");
				createDatabase();
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.databaseName, this.user, this.password);
					conn.close();
					System.out.println("Database created.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
				System.out.println("Unknown SQL Error. Error code:");
				System.out.println(e.getErrorCode());
			}
		}
	}
	
	private void createTablesIfNotExists() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.databaseName, this.user, this.password);
			Statement statement = null;
			statement = conn.createStatement();
			String sql = "CREATE TABLE saves ("
					+ "save_id int NOT NULL AUTO_INCREMENT, "
					+ "player varchar(255), "
					+ "created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, "
					+ "PRIMARY KEY (save_id)"
					+ ");";
			statement.executeUpdate(sql);
			conn.close();
			System.out.println("Save table created.");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1050) {
				System.out.println("Save table already exists.");
			} else {
				e.printStackTrace();
				System.out.println("A SQL error occurred. Error code: ");
				System.out.println(e.getErrorCode());
			}
		}	
	}
	
	public ResultSet getSaves() {
		ResultSet results = null;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.databaseName, this.user, this.password);
			Statement statement = null;
			statement = conn.createStatement();
			String sql = "SELECT * FROM saves;";
			results = statement.executeQuery(sql);
			
			try {
				while (results.next()) {
				    // Read values using column name
				    int id = results.getInt("save_id");
				    String player = results.getString("player");
				    Date date = results.getDate("created");
				    System.out.printf("%s %s %s \n", id, player, date);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main(String[] argv) {
		DBConnection db = new DBConnection("santa_horror", "santa", "password");
		db.getSaves();
	}

}

