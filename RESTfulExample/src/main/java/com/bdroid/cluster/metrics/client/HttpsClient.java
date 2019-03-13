package com.bdroid.cluster.metrics.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.io.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsClient{
	
  public StringBuilder getData()
  {
     return testIt();
  }
	
  private TrustManager[ ] get_trust_mgr() {
     TrustManager[ ] certs = new TrustManager[ ] {
        new X509TrustManager() {
           public X509Certificate[ ] getAcceptedIssuers() { return null; }
           public void checkClientTrusted(X509Certificate[ ] certs, String t) { }
           public void checkServerTrusted(X509Certificate[ ] certs, String t) { }
         }
      };
      return certs;
  }

  private StringBuilder testIt(){
     String https_url = "https://10.178.129.145:8090/ws/v1/cluster/metrics";
     URL url;
     try {
			
	    // Create a context that doesn't check certificates.
            SSLContext ssl_ctx = SSLContext.getInstance("TLS");
            TrustManager[ ] trust_mgr = get_trust_mgr();
            ssl_ctx.init(null,                // key manager
                         trust_mgr,           // trust manager
                         new SecureRandom()); // random number generator
            HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());

	    url = new URL(https_url);
	    System.out.println("Connecting to service.....");
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			
	    
	    String encoded = Base64.getEncoder().encodeToString(("bdadmin"+":"+"P1ym0uth1!").getBytes(StandardCharsets.UTF_8));  //Java 8
	    con.setRequestProperty("Authorization", "Basic "+encoded);
	    
	    
	    // Guard against "bad hostname" errors during handshake.
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String host, SSLSession sess) {
                    if (host.equals("10.178.129.145")) return true;
                    else return false;
                }
            });

	    //dumpl all cert info
	    print_https_cert(con);
			
	   
	    
	    //dump all the content
	  
	    if(con!=null){
			
	        try {
	    		
	    
	 /*   				
	    	BufferedReader br = 
	    		new BufferedReader(
	    			new InputStreamReader(con.getInputStream()));
	    	*/
	    	
	    	StringBuilder sb = new StringBuilder();
	    	System.out.println("Collecting the service response....");
	        String line;
	        BufferedReader    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        while ((line = br.readLine()) != null) {
	            sb.append(line);
	     
	        }
	    	
	    	
	    	
	    	
	    	
	    	//String input;
	    	return sb;
	    	
	    		
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	/*while ((input = br.readLine()) != null){
	    	   System.out.println(input);
	    	}
	    	br.close();
	    				*/
	         } catch (IOException e) {
	    	e.printStackTrace();
	         }		
	       }
	    //  print_content(con);
			
	 } catch (MalformedURLException e) {
		e.printStackTrace();
	 } catch (IOException e) {
		e.printStackTrace();
	 }catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	 }catch (KeyManagementException e) {
		e.printStackTrace();
      }
	return null;	
   }
	
  private void print_https_cert(HttpsURLConnection con){
     if(con!=null){
			
     try {
				
	System.out.println("Response Code : " + con.getResponseCode());
	System.out.println("Cipher Suite : " + con.getCipherSuite());
	System.out.println("\n");
				
	Certificate[] certs = con.getServerCertificates();
	for(Certificate cert : certs){
	  System.out.println("Cert Type : " + cert.getType());
	  System.out.println("Cert Hash Code : " + cert.hashCode());
	  System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
	  System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
	  System.out.println("\n");
	}
				
				
     } catch (SSLPeerUnverifiedException e) {
	  e.printStackTrace();
     } catch (IOException e){
	  e.printStackTrace();
     }	   
   }		
  }
	
  private void print_content(HttpsURLConnection con){
 
  }
}