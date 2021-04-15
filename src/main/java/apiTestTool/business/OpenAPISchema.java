package apiTestTool.business;
/**
 * This class is used for creating the APISchema
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OpenAPISchema {

// **************************************************
// Fields
// **************************************************
	
	private String apiFile;
	private List<String> paths;

	
// **************************************************
// Public methods
// **************************************************
	/**
	 * This is the constructor of the class. 
	 * @param apiFile is initialize with the path of the .json file from  the disk  
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public OpenAPISchema(String apiFile) throws FileNotFoundException, IOException, ParseException {
		this.apiFile = apiFile;
		//initialize
		paths = new ArrayList<>();

		createPathsArray();
	}

/**
 * 
 * @return the number of the paths in the .json file
 */
	public int returnPathsSize() {
		return paths.size();
	}

	/**
	 * 
	 * @param index represents the positions of the path in the array
	 * @return the path from the list
	 */
	public String getPath(int index) {
		return paths.get(index);
	}
	

// **************************************************
// Private methods
// **************************************************
	/**
	 * this method retrieves the paths from the .json file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	private void createPathsArray() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		// create the file from the path
		try (FileReader reader = new FileReader(apiFile)) {
			
			//read the JSON file
			Object object = parser.parse(reader);
			JSONObject objJSON = (JSONObject) object;
			
			//creates a map from the sub JSONObject "Path" with the JSONObject names and constructions
			Map pathAddress = ((Map) objJSON.get("paths"));
			
			// iterator for iteration through the map 
			Iterator<Map.Entry> mapIterator = pathAddress.entrySet().iterator();
			
			//iterate trought the map and retrieve the paths and placing in the array
			while (mapIterator.hasNext()) {
				Map.Entry paths = mapIterator.next();
				this.paths.add((String) paths.getKey());
			}
			
		}
	}
}
