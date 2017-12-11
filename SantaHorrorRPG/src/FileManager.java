import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileManager {

	private JSONParser parser;
	
	public static JSONObject loadZone(String zoneFile) {
		return JsonParser.getJson("zones", zoneFile);
	}
	
	public static JSONObject loadSaveFile(String player) {
		DBConnection db = DBConnection.getInstance();
		Number zoneID = db.getZoneID(player);
		String filename = zoneID + ".json";
		return loadZone(filename);
	}
	
	public static void saveGame(String player, int zoneID) {
		DBConnection db = DBConnection.getInstance();
		db.saveGame(player, zoneID);
	}

}
