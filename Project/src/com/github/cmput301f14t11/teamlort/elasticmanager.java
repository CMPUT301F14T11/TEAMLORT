package com.github.cmput301f14t11.teamlort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;



import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class elasticmanager {
	private static elasticmanager instance = null;
	
	private static final String serverAddress = "http://cmput301.softwareprocess.es:8080/cmput301f14t11/";
	private static Gson gson;

	protected elasticmanager() {
		
	}
	public static elasticmanager getInstance() {
	      if(instance == null) {
	         instance = new elasticmanager();
	         gson = new Gson();
	      }
	      return instance;
	   }

	
	public RepliableText getItem(int id) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(serverAddress + id);

		HttpResponse response;

		try {
			response = httpClient.execute(httpGet);
			Elasticitem<RepliableText> sr = parseItem(response);
			return sr.getSource();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return null;
	}
	private Elasticitem<RepliableText> parseItem(HttpResponse response) {
		
		try {
			String json = getEntityContent(response);
			Type searchHitType = new TypeToken<Elasticitem<RepliableText>>() {}.getType();
			
			Elasticitem<RepliableText> sr = gson.fromJson(json, searchHitType);
			return sr;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * Gets content from an HTTP response
	 */
	public String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}

	
	public static Object serverQuery(String query)
	{
		//TODO
		return null;
	}
	
	public void addItem(RepliableText deliveritem) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPost addRequest = new HttpPost(serverAddress + deliveritem.getID());

			StringEntity stringEntity = new StringEntity(gson.toJson(deliveritem));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			//Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteItem(int Id) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpDelete deleteRequest = new HttpDelete(serverAddress + Id);
			deleteRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			//Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}