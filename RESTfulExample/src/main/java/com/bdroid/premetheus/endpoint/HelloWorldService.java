package com.bdroid.premetheus.endpoint;
 
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.json.JSONObject;

import com.bdroid.cluster.metrics.client.HttpsClient;
import com.bdroid.json.loader.JsonLoader;
import com.bdroid.json.loader.Pojo;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Gauge.Builder;
import io.prometheus.client.Gauge.Child;
import io.prometheus.client.Histogram;
import io.prometheus.client.exporter.common.TextFormat;


@Path("metrics")

public class HelloWorldService {
	  /*static final Histogram h = Histogram.build().name("histogram").help("meh").register();
	  static final Counter c = Counter.build().name("req").help("request").register();
	  static final  Coffees coffee=  new Coffees();
	  static final Gauge g1 = Gauge.build().name("Json1").help("Data1").register();
	  static final Gauge g2 = Gauge.build().name("Json2").help("Data2").register();
		*/ 
	  
	  // ArrayList<Gauge> ag = new ArrayList<Gauge>();
	   
	//  static final Counter c = Counter.build().name("counter").help("meh").register();
	 // static final Summary s = Summary.build().name("summary").help("meh").register();
	 // static final Histogram h = Histogram.build().name("histogram").help("meh").register();
	 // static final Gauge l = Gauge.build().name("labels").help("blah").labelNames("l").register();
	   
	   
		  static final Gauge clusterMetrics = Gauge.build()
		          .name("clusterMetrics").help("Cluster matrics")
		          .labelNames("fieldName").register();
	   
	   
	@GET
	@Produces(TextFormat.CONTENT_TYPE_004)
	public Response getUserByName(@PathParam("name") String name) throws IOException {
			//JsonLoader jl = new JsonLoader();
			//Pojo pojo=jl.jsonParser();
		
		  
				StringBuilder br = new HttpsClient().getData();
				System.out.println(br.toString());
				System.out.println("Parsing response in json...");
				JSONObject resobj = new JSONObject(br.toString());
				JSONObject resobj2 = (JSONObject) resobj.get("clusterMetrics");
				Iterator<String> keys = resobj2.keys();

				
				System.out.println("Creating the Premetheus Matrics....");
				while (keys.hasNext()) {
					String key = keys.next();
				
					clusterMetrics.labels(key).set(Double.parseDouble((resobj2.get(key).toString())));
					  System.out.println(key + ":" + resobj2.get(key)+"---------->   Added");
				}
				  
		
		
			Writer writer = new StringWriter();
		    writer.append("Bdroid : Exporter");
		    TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
		
		    
		    
		    return Response.status(200)
					.entity(writer.toString()).build();
		    
		    
		    
		    
		    
		  }
		  
	
	  
	
	
	
	
	
	
	
	  
} 	