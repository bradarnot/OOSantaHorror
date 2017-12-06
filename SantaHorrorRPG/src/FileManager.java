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
		String filename = player + ".json";
		return SavedData.getSavedData(filename);
	}
	
	public static void saveToFile(String player, JSONObject json) {
		DBConnection db = new DBConnection("santa_horror", "santa", "password");
		db.saveGame(player);
		SavedData.save(player, json);
	}
	
	public static void main(String[] argv) {
		JSONObject obj = new JSONObject();
        obj.put("foo", "bar");
        obj.put("name", "Geraldson");
        
		saveToFile("jeff", obj);
		System.out.println(getSaveFile("jeff"));
	}

}
