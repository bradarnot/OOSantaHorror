import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
	
	private String filepath;
	
	public static JSONObject getJson(String directory, String filename) {
		String file = System.getProperty("user.dir") + "/" + directory + "/" + filename;
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static void saveJson(String directory, String filename, JSONObject json) {
		String filepath = System.getProperty("user.dir") + "/" + directory + "/" + filename;
		File file = new File(filepath);
		if(!file.exists()) {
			System.out.println("File doesn't exist.");
		    try {
				file.createNewFile();
				System.out.println("File created.");
				try {
					FileWriter fileWriter = new FileWriter(filepath);
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
				FileWriter fileWriter = new FileWriter(filepath);
				fileWriter.write(json.toJSONString());
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
