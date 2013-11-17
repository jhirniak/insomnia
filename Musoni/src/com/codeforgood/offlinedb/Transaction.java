package com.codeforgood.offlinedb;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.R;
import android.content.Context;

import com.codeforgood.musoni.MainActivity;

public class Transaction implements TransactionInterface
{
	public String getClients()
	{
		return null;
	}
	
	private String get(HashMap<String, String> bindings)
	{
//		Context context = MainActivity.getContext();
//		String baseURL = context.getResources().getString(R.id.class);
//		HttpClient httpclient = new DefaultHttpClient();
//		HttpPost httppost = new HttpPost(baseURL);
//
//		// Request parameters and other properties.
//		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//		for (String b : bindings.keySet())
//		{
//			params.add(new BasicNameValuePair(b, bindings.get(b)));
//		}
//		
//		try { httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8")); }
//		catch (UnsupportedEncodingException e) { e.printStackTrace(); }
//
//		//Execute and get the response.
//		HttpResponse response;
//		try
//		{ 
//			response = httpclient.execute(httppost);
//			
//			HttpEntity entity = response.getEntity();
//
//			if (entity != null) {
//			    InputStream instream = entity.getContent();
//			    try {
//			        // do something useful
//			    } finally {
//			        instream.close();
//			    }
//			}
//		}
//		catch (ClientProtocolException e) { e.printStackTrace(); }
//		catch (IOException e) { e.printStackTrace(); }
//		
		return null;
	}
	
}
