package com.bdroid.cluster.metrics.client;

import java.io.BufferedReader;
import java.util.Iterator;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;

public class ClusterMetricsClient {

	
	  public static void main(String[] args) {
			
		  
		StringBuilder br = new HttpsClient().getData();

		JSONObject resobj = new JSONObject(br.toString());

		JSONObject resobj2 = (JSONObject) resobj.get("clusterMetrics");

		System.out.println(resobj2);

		Iterator<String> keys = resobj2.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			System.out.println(key + ":" + resobj2.get(key));
		}
		  
		  
		  
		  
		  
		  
	  }
	
}
