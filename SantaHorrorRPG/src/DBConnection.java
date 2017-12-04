import java.sql.Connection;
import java.sql.DriverManager;
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
	
	private Connection connection;
	
	public DBConnection(String database, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		createDBIfNotExists(database, user, password);
		createTables(database, user, password);
	}
	
	private Connection connect(String database, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
			System.out.println("Connection established.");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1049) {
				System.out.println("Database does not exist. Creating database...");
				createDatabase(user, password);
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
					System.out.println("Connection established.");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
				System.out.println("Unknown SQL Error. Error code:");
				System.out.println(e.getErrorCode());
			}
		}
		return conn;
	}
	
	private void createDatabase(String user, String password) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, password);
			Statement statement = null;
			statement = conn.createStatement();
			String sql = "CREATE DATABASE santa_horror";
			statement.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
	}
	
	private void execute(String sql) {
		try {
			Statement statement = null;
			statement = this.connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private void close() {
		try {
			this.connection.close();
			System.out.println("Database connection closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Boolean createDBIfNotExists(String database, String user, String password) {
		Boolean exists = false;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
			System.out.println("Connection established.");
			exists = true;
		} catch (SQLException e) {
			if (e.getErrorCode() == 1049) {
				System.out.println("Database does not exist. Creating database...");
				createDatabase(user, password);
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
					exists = true;
					System.out.println("Connection established.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
				System.out.println("Unknown SQL Error. Error code:");
				System.out.println(e.getErrorCode());
			}
		}
		return exists;
	}
	
	public void createTables(String database, String user, String password) {
		
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public static void main(String[] argv) {
		DBConnection conn = new DBConnection("santa_horror", "santa", "password");
		conn.close();
	}
}
