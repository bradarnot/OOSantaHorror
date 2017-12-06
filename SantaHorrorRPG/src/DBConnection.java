import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void saveGame(String player) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.databaseName, this.user, this.password);
			String sql = "INSERT INTO saves (player) VALUES (?)";
			PreparedStatement s = null;
			s = conn.prepareStatement(sql);
			s.setString(1, player);
			s.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<Map<String, Object>> getSaves() {
		ResultSet results = null;
		List<Map<String, Object>> resultsList = null;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.databaseName, this.user, this.password);
			Statement statement = null;
			statement = conn.createStatement();
			String sql = "SELECT * FROM saves;";
			results = statement.executeQuery(sql);
			resultsList = resultSetToList(results);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultsList;
	}
	
	/* Source for resultSetToList() function: https://gist.github.com/cworks/4175942 */
	private List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
	    while (rs.next()){
	        Map<String, Object> row = new HashMap<String, Object>(columns);
	        for(int i = 1; i <= columns; ++i){
	            row.put(md.getColumnName(i), rs.getObject(i));
	        }
	        rows.add(row);
	    }
	    return rows;
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
		List<Map<String, Object>> results = db.getSaves();
		System.out.println(results);
	}

}

