package JsonReader.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * Hello world!
 *
 *
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonSyntaxException, JsonIOException, FileNotFoundException
    {
    	Gson gson = new Gson();

    	// 1. JSON to Java object, read it from a file.
    	Pojo pojo = gson.fromJson(new FileReader("file.json"), Pojo.class);
    	System.out.println(pojo.toString());
    	
    	
    	// JSON to JsonElement, convert to String later.
    	JsonElement json = gson.fromJson(new FileReader("file.json"), JsonElement.class);
        String result = gson.toJson(json);
   System.out.println(result);
    }
}
