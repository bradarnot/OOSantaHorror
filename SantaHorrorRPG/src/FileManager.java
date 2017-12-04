import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.simple.parser.JSONParser;

public class FileManager {

	private JSONParser parser;
	
	public void loadZone(String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/santa_horror","santa", "password");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadGameObj(String name) {
		
	}
	
	public void loadMonster(String name) {
		
	}
	
	public void loadPlayer(String name) {
		
	}

	public void loadNPC(String name) {
	
	}
	
	public String[] getSaveFiles() {
		return null;
		
	}
	
	public void saveToFile() {
		
	}
	
}
