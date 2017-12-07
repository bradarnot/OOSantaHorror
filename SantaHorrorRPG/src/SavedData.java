import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

public class SavedData {
	
	public static void save(String filename, JSONObject json) {
		String saveDir = System.getProperty("user.dir") + "/saves/";
		File file = new File(saveDir + filename + ".json");
		if(!file.exists()) {
			System.out.println("File doesn't exist.");
		    try {
				file.createNewFile();
				System.out.println("File created.");
				try {
					FileWriter fileWriter = new FileWriter(saveDir + filename + ".json");
					fileWriter.write(json.toJSONString());
					fileWriter.flush();
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(file.exists() && !file.isDirectory()) {
			try {
				FileWriter fileWriter = new FileWriter(saveDir + filename + ".json");
				fileWriter.write(json.toJSONString());
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static JSONObject getSavedData(String filename) {
		String saveDir = System.getProperty("user.dir") + "/saves/";
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(new FileReader(saveDir + filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public static void main(String[] argv) {
		JSONObject obj = new JSONObject();
        obj.put("foo", "bar");
        obj.put("name", "Geraldson");
        
        save("test", obj);
        JSONObject saveData = getSavedData("test.json");
        System.out.println(saveData);
	   
	}

}
