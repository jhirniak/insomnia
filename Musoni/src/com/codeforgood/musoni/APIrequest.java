package com.codeforgood.musoni;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyStore;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.codeforgood.musoni.json.ClientList;
import com.codeforgood.musoni.json.PageItems;
import com.google.gson.Gson;

public class APIrequest 
{
	private static final String DOMAIN_NAME = "https://mlite-demo.musoni.eu:8443/mifosng-provider/api/v1/";
	private static final String TENANT_IDENTIFIER = "?tenantIdentifier=code4good";
	
	private static String createRequest(String requestContent)
	{
		return DOMAIN_NAME+requestContent+TENANT_IDENTIFIER;
	}
	
	public static void getClients()
	{
		ReadEvents re = new ReadEvents();
        re.execute(new String[] {createRequest("clients")});
	}
	
	private static class ReadEvents extends AsyncTask<String, Integer, String>
    {
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected String doInBackground(String... params) 
		{
			Log.e("DEBUG", "url - "+params[0]);
			return httpGet(params[0]);
		}
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String read) 
		{	
			PageItems p = jsonToTwitter(read).getPageItems();
			//Log.e("DEBUG", read);
			
			for (int i = 0; i < p.size(); i++)
				Log.e("DEBUG", p.get(i).toString());
		}
		
		private String httpGet(String urlToRead)
		{
			HttpClient client = getNewHttpClient();
	        HttpResponse response;
	        String responseString = "FAIL!";
	        try {
	        	HttpGet request = new HttpGet(urlToRead);
	        	request.setHeader("Authorization", "Basic " + Base64.encodeToString("code4good:UK2013".getBytes(), Base64.NO_WRAP));
	            response = client.execute(request);
	            StatusLine statusLine = response.getStatusLine();
	            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	                ByteArrayOutputStream out = new ByteArrayOutputStream();
	                response.getEntity().writeTo(out);
	                out.close();
	                responseString = out.toString();
	            } else{
	                //Closes the connection.
	                response.getEntity().getContent().close();
	                throw new IOException(statusLine.getReasonPhrase());
	            }
	        } catch (ClientProtocolException e) {
	        	e.printStackTrace();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        return responseString;
		}
		
		public HttpClient getNewHttpClient() {
		    try {
		        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		        trustStore.load(null, null);

		        MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
		        sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		        HttpParams params = new BasicHttpParams();
		        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

		        SchemeRegistry registry = new SchemeRegistry();
		        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		        registry.register(new Scheme("https", sf, 443));

		        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

		        return new DefaultHttpClient(ccm, params);
		    } catch (Exception e) {
		        return new DefaultHttpClient();
		    }
		}
    }
	
	private static ClientList jsonToTwitter(String result) 
	{
		ClientList twits = null;
		if (result != null && result.length() > 0) 
		{
			try {
				twits = new Gson().fromJson(result, ClientList.class);
			} catch (IllegalStateException ex) {ex.printStackTrace();}
		}
		return twits;
	}

}
