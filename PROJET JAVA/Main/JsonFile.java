package Main;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class JSONFile 
{
	  public void saveFile(JSONArray array, String file)
	    {   
	      try {
	            FileWriter fi = new FileWriter(file);
	            fi.write(array.toString());
	            fi.flush();
	            fi.close();
	        }catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    public JSONArray readFile( String file , JSONArray json)
	    {
	    	JSONParser jsonParser = new JSONParser();       
	    	try{
	        FileReader reader = new FileReader(file);
	         Object obj = jsonParser.parse(reader);
	         JSONArray lList = (JSONArray) obj;
		         json = lList;
		
		     } catch (FileNotFoundException e) {
		         e.printStackTrace();
		     } catch (IOException e) {
		         e.printStackTrace();
		     } catch (ParseException e) {
		         e.printStackTrace();
		     }
	    	return json;
	 }	
}
