package main.java.com.mydao.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpsClientUtil {
	public HttpsClientUtil() throws Exception
	{

	}
	
	public static boolean bHttpsEnabled=false;
	
	public static void httpsClientInit(String IP, int Port, String user, String Password)
	{
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider(); 
		Credentials credentials = new UsernamePasswordCredentials(user,  Password);
		credentialsProvider.setCredentials(new AuthScope(IP, Port), credentials);
        HttpsClientUtil.httpsClient=HttpClients.custom().setSSLSocketFactory(HttpsClientUtil.createSSLConnSocketFactory()).setDefaultCredentialsProvider(credentialsProvider).build();
	}

    public static SSLConnectionSocketFactory createSSLConnSocketFactory() { 
	 
          SSLConnectionSocketFactory sslsf = null;  
	      try {  
	    	 
	    	TrustStrategy trustStrategy=new TrustStrategy(){  
	    		 
	              public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
		               return true;  
		          }  
		    };
		    
	        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, trustStrategy).build();  
	        
	        X509HostnameVerifier x509HostnameVerifier=new X509HostnameVerifier(){  
	       	 		        
                public boolean verify(String arg0, SSLSession arg1) {  
                  return true;  
                }  

                public void verify(String host, SSLSocket ssl) throws IOException {  
                }  
 

                public void verify(String host, X509Certificate cert) throws SSLException {  
                }  

	            public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {  
	            }  
	        };
	        
	        sslsf = new SSLConnectionSocketFactory(sslContext, x509HostnameVerifier);  
	      } catch (GeneralSecurityException e) {  
	            e.printStackTrace();  
	        }  
	       return sslsf;  
	  }  

	
	public static CloseableHttpClient httpsClient=null;
	
	public static String httpsGet(String url)
	{
		String Ret="";
		try {
				CloseableHttpResponse response = null;
				HttpGet httpGet = new HttpGet(url);  
		
				response = httpsClient.execute(httpGet);		 				
				
				int statusCode = response.getStatusLine().getStatusCode();  		
				if (statusCode != HttpStatus.SC_OK)
				{
					Ret = "error "+statusCode;
				}
				
				HttpEntity entity = response.getEntity();  
				if (entity == null)
				{
					Ret = "error response is null";
				}
				
				Ret = EntityUtils.toString(entity, "utf-8"); 
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Ret;
	}
	
	public static String httpsPut(String url, String inboundInfo)
	{
		String Ret="";
		try {
				CloseableHttpResponse response = null;
				HttpPut httpPut = new HttpPut(url);  
				
				HttpEntity inboundInfoEntity = new StringEntity(inboundInfo, "UTF-8");
				httpPut.setEntity(inboundInfoEntity);
 
				response = httpsClient.execute(httpPut);		 				
				
				int statusCode = response.getStatusLine().getStatusCode();  		
				if (statusCode != HttpStatus.SC_OK)
				{
					Ret = "error "+statusCode;
				}
				
				HttpEntity entity = response.getEntity();  
				if (entity == null)
				{
					Ret = "error response is null";
				}
				
				Ret = EntityUtils.toString(entity, "utf-8"); 
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Ret;
	}
	
	public static String httpsPost(String url, String inboundInfo)
	{
		String Ret="";
		try {
				CloseableHttpResponse response = null;
				HttpPost httpPost = new HttpPost(url);  
				
				HttpEntity inboundInfoEntity = new StringEntity(inboundInfo, "UTF-8");
				httpPost.setEntity(inboundInfoEntity);
 
				response = httpsClient.execute(httpPost);		 				
				
				int statusCode = response.getStatusLine().getStatusCode();  		
				if (statusCode != HttpStatus.SC_OK)
				{
					Ret = "error "+statusCode;
				}
				
				HttpEntity entity = response.getEntity();  
				if (entity == null)
				{
					Ret = "error response is null";
				}
				
				Ret = EntityUtils.toString(entity, "utf-8"); 
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Ret;
	}
	
	public static String httpsDelete(String url)
	{
		String Ret="";
		try {
				CloseableHttpResponse response = null;
				HttpDelete httpDelete = new HttpDelete(url);  				
 
				response = httpsClient.execute(httpDelete);		 				
				
				int statusCode = response.getStatusLine().getStatusCode();  		
				if (statusCode != HttpStatus.SC_OK)
				{
					Ret = "error "+statusCode;
				}
				
				HttpEntity entity = response.getEntity();  
				if (entity == null)
				{
					Ret = "error response is null";
				}
				
				Ret = EntityUtils.toString(entity, "utf-8"); 
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Ret;
	}
	
public static String doPostStorageCloud(String url, String json,String faceimage,String boundary) throws Exception {   
    	
	String Ret="";
	try{
		 CloseableHttpResponse response = null;
		   HttpPost method = new HttpPost(url);  
	        method.addHeader("Accept", "text/html, application/xhtml+xml");
	        method.addHeader("Accept-Language", "zh-CN");
	        method.addHeader("Content-Type","multipart/form-data; boundary=" + boundary);
	        method.addHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
	        method.addHeader("Accept-Encoding","gzip, deflate");
	        method.addHeader("Connection","Keep-Alive");
	        method.addHeader("Cache-Control","no-cache");
	      // byte szTemp
	        String bodyParam = 
	        		"--" + boundary + "\r\n" 
	              + "Content-Disposition: form-data; name=\"uploadStorageCloud\";\r\n"
	              + "Content-Type: text/json\r\n" 
	              + "Content-Length: " + Integer.toString(json.length()) + "\r\n\r\n" 
	              +  json + "\r\n" 
	              + "--" + boundary + "\r\n" 
	              + "Content-Disposition: form-data; name=\"imageData\";\r\n"
	              + "Content-Type: image/jpeg\r\n" 
	              + "Content-Length: " + Integer.toString(faceimage.length()) + "\r\n\r\n" 
	              + faceimage 
	              + "\r\n--" + boundary + "--\r\n";

	        
	 	   //HttpEntity inboundInfoEntity = new StringEntity(bodyParam, "UTF-8");
	 	  HttpEntity inboundInfoEntity = new StringEntity(bodyParam);
	 	  String strTemp = inboundInfoEntity.toString();
	 	  
	 	  String strTemppp = strTemp;
	 	  
	 	  method.setEntity(inboundInfoEntity);
	     	
	 	 response = httpsClient.execute(method);
	 	 
	 	int statusCode = response.getStatusLine().getStatusCode();  		
		if (statusCode != HttpStatus.SC_OK)
		{
			Ret = "error "+statusCode;
		}
		
		HttpEntity entity = response.getEntity();  
		if (entity == null)
		{
			Ret = "error response is null";
		}
		
		Ret = EntityUtils.toString(entity, "utf-8"); 
	        // �ͷ�����  
	        method.releaseConnection();  
		
	}catch (ClientProtocolException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Ret;
	  
    } 
public static String doModFacePicRecord(String url, String json,String faceimage,String boundary) throws Exception  {   
	
	String Ret="";
	try{
		 CloseableHttpResponse response = null;
		   HttpPost method = new HttpPost(url);  
		   method.addHeader("Accept", "text/html, application/xhtml+xml");
	        method.addHeader("Accept-Language", "zh-CN");
	        method.addHeader("Content-Type","multipart/form-data; boundary=" + boundary);
	        method.addHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
	        method.addHeader("Accept-Encoding","gzip, deflate");
	        method.addHeader("Connection","Keep-Alive");
	        method.addHeader("Cache-Control","no-cache");
	        
	        String bodyParam = 
	        		"--" + boundary + "\r\n" 
	              + "Content-Disposition: form-data; name=\"FaceDataRecord\";\r\n"
	              + "Content-Type: text/json\r\n" 
	              + "Content-Length: " + Integer.toString(json.length()) + "\r\n\r\n" 
	              +  json + "\r\n" 
	              + "--" + boundary + "\r\n" 
	              + "Content-Disposition: form-data; name=\"FaceImage\";\r\n"
	              + "Content-Type: image/jpeg\r\n" 
	              + "Content-Length: " + Integer.toString(faceimage.length()) + "\r\n\r\n" 
	              + faceimage 
	              + "\r\n--" + boundary + "--\r\n";

	        
	 	   HttpEntity inboundInfoEntity = new StringEntity(bodyParam, "UTF-8");
	 	   String strTemp = inboundInfoEntity.toString();
	 	  method.setEntity(inboundInfoEntity);
	     	
	 	 response = httpsClient.execute(method);
	 	 
	 	int statusCode = response.getStatusLine().getStatusCode();  		
		if (statusCode != HttpStatus.SC_OK)
		{
			Ret = "error "+statusCode;
		}
		
		HttpEntity entity = response.getEntity();  
		if (entity == null)
		{
			Ret = "error response is null";
		}
		
		Ret = EntityUtils.toString(entity, "utf-8"); 
	        // �ͷ�����  
	        method.releaseConnection();  
		
	}catch (ClientProtocolException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Ret;
	  
    } 
public static String doPutWithType(String url, String inbound,String charset,String Type) throws Exception {   
	
 

    String Ret="";
	try {
			CloseableHttpResponse response = null;
			HttpPut httpPut = new HttpPut(url);  
			
			httpPut.addHeader("Content-Type", Type);
			HttpEntity inboundInfoEntity = new StringEntity(inbound, "UTF-8");
			httpPut.setEntity(inboundInfoEntity);

			response = httpsClient.execute(httpPut);		 				
			
			int statusCode = response.getStatusLine().getStatusCode();  		
			if (statusCode != HttpStatus.SC_OK)
			{
				Ret = "error "+statusCode;
			}
			
			HttpEntity entity = response.getEntity();  
			if (entity == null)
			{
				Ret = "error response is null";
			}
			
			Ret = EntityUtils.toString(entity, "utf-8"); 
	
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Ret;
}


}
