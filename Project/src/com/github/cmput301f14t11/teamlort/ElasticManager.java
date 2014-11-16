package com.github.cmput301f14t11.teamlort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;



import android.util.Log;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * responsible for communicating with online server, 
 * retriving questions from the server 
 * and uploading questions from the app 
 * @author sbao
 *
 */
public class ElasticManager {
	private static ElasticManager instance = null;
	
	private static final String serverAddress = "http://cmput301.softwareprocess.es:8080/cmput301f14t11/";
	private static Gson gson;

	protected ElasticManager() {
		
	}
	public static ElasticManager getInstance() {
	      if(instance == null) {
	         instance = new ElasticManager();
	         gson = new Gson();
	      }
	      return instance;
	   }

	/**
	 * for getting a single question, based on ID
	 * @param id
	 * @return
	 */
	public Question getItem(int id) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(serverAddress + id);

		HttpResponse response;

		try {
			response = httpClient.execute(httpGet);
			Elasticitem<Question> sr = parseItem(response);
			return sr.getSource();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return null;
	}
	
	private Elasticitem<Question> parseItem(HttpResponse response) {
		
		try {
			String json = getEntityContent(response);
			Type searchHitType = new TypeToken<Elasticitem<Question>>() {}.getType();
			
			Elasticitem<Question> sr = gson.fromJson(json, searchHitType);
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
	/**
	 * adds a single question to the server
	 * @param deliveritem
	 */
	public void addItem(Question deliveritem) {
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
	/**
	 * deletes question from server based on id
	 * @param Id
	 */
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
	/**
	 * grabs content from server, provided it receives an ID as a starting point
	 * and how far down the list it needs to go
	 * @param id
	 * @param amount
	 * @return
	 */
	public ArrayList<Question>get(int id,int amount)
	{
		HttpClient httpClient = new DefaultHttpClient();
		ArrayList<Question> result = new ArrayList<Question>();
		HttpResponse response;

		try {
			for(int i = 1;i<=amount+1; i++)
			{
				HttpGet httpGet = new HttpGet(serverAddress + id+i);
				response = httpClient.execute(httpGet);
				Elasticitem<Question> sr = parseItem(response);
				result.add(sr.getSource());
			}
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} 
		// if all else fails, return a brand new Array list to avoid null pointer errors
		return new ArrayList<Question>();
	}
}
	
	/**
	 * Search string and return a question list that match the condition
	 * @param string
	 * @return
	 */
//	public ArrayList<Question> search(String string) {
//		// TODO Auto-generated method stub
//		ArrayList<Question> result = new ArrayList<Question>();
//
//		// TODO: Implement search movies using ElasticSearch
//		if(string == null || "".equals(string)){
//			string = "*"; 
//			HttpClient httpClient = new DefaultHttpClient();
//			HttpPost searchRequest = new HttpPost(serverAddress);
//		}
//		HttpClient httpClient = new DefaultHttpClient();
//		
//		try{
//			HttpPost searchRequest = createSearchRequest(string,field);
//			HttpResponse response = httpClient.execute(searchRequest);
//			String status = response.getStatusLine().toString();
//			
//			SearchResponse<Question> esResponse = parseSearchResponse(response);
//			Hits <Movie> hits = esResponse.getHits();
//			
//			if( hits != null)
//				if(hits.getHits()!=null)
//					for (SearchHit<Movie>sesr: hits.getHits())
//					{
//						result.add(sesr.getSource());
//					}
//			
//		}
//		catch(UnsupportedEncodingException e)
//		{
//			e.printStackTrace();
//		}
//		catch(ClientProtocolException e)
//		{
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return result;
//	}
	
