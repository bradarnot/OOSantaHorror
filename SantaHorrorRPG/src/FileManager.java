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
		return JsonParser.getJson("saves", filename);
	}
	
	public static void createSaveFile(String player, JSONObject json) {
		DBConnection db = DBConnection.getInstance();
		Number fileID = db.saveGame(player);
		String filename = String.format ("%d", fileID) + ".json";
		JsonParser.saveJson("saves", filename, json);
	}
	
	public static void main(String[] argv) {
		JSONObject obj = new JSONObject();
        obj.put("foo", "bar");
        obj.put("name", "jeff");
        
		createSaveFile("jeff", obj);
		System.out.println(getSaveFile("jeff"));
	}

}
