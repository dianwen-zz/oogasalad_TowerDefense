package main.java.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import main.java.schema.GameBlueprint;
import main.java.schema.GameSchema;

import com.google.gson.Gson;

public class JSONHandler {

	private static String FILE_PATH = "src/main/resources/";
	private Gson myGson;
	
	public JSONHandler(){
  		myGson = new Gson();
  	}
	/**
	 * Method to write the information of an object into a text file
	 * named after the filename String
	 * @param filename
	 * @param d
	 * @throws FileNotFoundException
	 */
	public void serializeObjectToJSON(Object obj, String filename) throws FileNotFoundException	{
		File outputFile = new File(FILE_PATH + filename + ".json");
		PrintWriter output = new PrintWriter(outputFile);
		String json = myGson.toJson(obj);
		System.out.println(json);
		output.println(json);
		output.close();
	}
	
	/**
	 * 
	 * Takes a JSON file and creates the object
	 * from it, will mostly be used to load gameblueprints
	 * 
	 * @param filepath
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public Object deserializeObjectFromJSON(Object obj, String filepath) throws IOException	{
		BufferedReader reader = new BufferedReader(new FileReader(filepath));
		String json = "";
		String line = null;
		while ((line = reader.readLine()) != null) {
//			System.out.println(line);
		    json += line;
		}
		return new Gson().fromJson(json, obj.getClass());
	}

	public static void main(String[] args) throws IOException	{
//		GameSchema testSchema = new GameSchema();
//		testSchema.addAttribute("Lives",10);
//		GameBlueprint testBlueprint = new GameBlueprint();
//		testBlueprint.setMyGameScenario(testSchema);
		//Game maps no longer exist
		/*List<GameMap> maps = new ArrayList<GameMap>();
		maps.add(new GameMap());
		testBlueprint.setMyGameMaps(maps);*/
		
		// creates a test object with a map and set, mirrors actual gameblueprint design hierarchy to test JSON
		TestObject t = new TestObject();
		t.populateDefaultAttributes("testObjectName");
		
		JSONHandler j = new JSONHandler();
		j.serializeObjectToJSON(t, "testobjectJSON");
		GameBlueprint g = (GameBlueprint) (j.deserializeObjectFromJSON(t, (FILE_PATH + "testobjectJSON.json")));
		j.serializeObjectToJSON(g,"testobjectJSON2");

	}
	
}
