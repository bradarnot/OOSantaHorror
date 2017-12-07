import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileManager {

	private JSONParser parser;
	
	public void loadZone(String name) {

	}
	
	public void loadGameObj(String name) {
		
	}
	
	public void loadMonster(String name) {
		
	}
	
	public void loadPlayer(String name) {
		
	}

	public void loadNPC(String name) {
	
	}
	
	public static JSONObject getSaveFile(String player) {
		DBConnection db = DBConnection.getInstance();
		Number saveID = db.getSaveID(player);
		String filename = saveID + ".json";
		return SavedData.getSavedData(filename);
	}
	
	public static void saveToFile(String player, JSONObject json) {
		DBConnection db = DBConnection.getInstance();
		Number fileID = db.saveGame(player);
		SavedData.save(fileID, json);
	}
	
	public static void main(String[] argv) {
		JSONObject obj = new JSONObject();
        obj.put("foo", "bar");
        obj.put("name", "jeff");
        
		saveToFile("jeff", obj);
		System.out.println(getSaveFile("foo"));
	}

}
