package com.bdroid.json.loader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


public class JsonLoader {

	
	public Pojo jsonParser() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		Gson gson = new Gson();

    	// 1. JSON to Java object, read it from a file.
    	Pojo pojo = gson.fromJson(new FileReader("C:\\Users\\y18\\Downloads\\JAX-RS-Hello-World-Jersey-Example\\RESTfulExample\\file.json"), Pojo.class);
    	
    	return pojo;
    	
    	
    	/*// JSON to JsonElement, convert to String later.
    	JsonElement json = gson.fromJson(new FileReader("file.json"), JsonElement.class);
        String result = gson.toJson(json);
        System.out.println(result);*/
	}
	
	
	
}
