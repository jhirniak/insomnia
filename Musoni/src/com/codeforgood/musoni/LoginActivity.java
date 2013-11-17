package com.codeforgood.musoni;

import java.io.IOException;
import java.security.KeyStore;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codeforgood.musoni.api.MySSLSocketFactory;

public class LoginActivity extends Activity {
	
	Context context = this;
	TextView error;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		error = (TextView) findViewById(R.id.error);
	}
	
	public void login(View v)
	{
		error.setVisibility(View.GONE);
		EditText username = (EditText) findViewById(R.id.username);
		EditText password = (EditText) findViewById(R.id.password);
		String url = "https://mlite-demo.musoni.eu:8443/mifosng-provider/api/v1/authentication?username="
						+username.getText().toString()+"&password="
						+password.getText().toString()+"&tenantIdentifier=code4good";
		LoginAuthentication log = new LoginAuthentication();
		log.execute(new String[]{url});
	}

	private class LoginAuthentication extends AsyncTask<String, Integer, Integer>
	{				
		
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Integer doInBackground(String... params) 
		{
			return httpPost(params[0]);
		}
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Integer result) 
		{	
			if(result == 200)
			{
				Intent start = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(start);	
				finish(); 
			}
			else
				error.setVisibility(View.VISIBLE);
		}
		
		private int httpPost(String urlToRead)
		{
			HttpClient client = getNewHttpClient();
	        HttpResponse response;
	        try {
	        	HttpPost request = new HttpPost(urlToRead);
	        	request.setHeader("Authorization", "Basic " + Base64.encodeToString("code4good:UK2013".getBytes(), Base64.NO_WRAP));
	            response = client.execute(request);
	            StatusLine statusLine = response.getStatusLine();
	            return statusLine.getStatusCode();
	        } catch (ClientProtocolException e) {
	        	e.printStackTrace();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        return -1;
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
}
