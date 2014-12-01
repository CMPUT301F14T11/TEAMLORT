package com.github.cmput301f14t11.teamlort.Model;

import android.util.Log;

public class GeoParser
{
	public static String parseGeoString(String s)
	{
		String[] tokens = s.split(",");
		for (String token : tokens)
		{
			if(token.contains("display_name"))
			{
				String[] kvPair = token.split(":");
				return kvPair[1];
			}
			
			Log.i("parser",token);
			
			
		}
		return "not a legit location";
	}
}
//String key = kvPair[0].replaceAll("[^a-zA-Z ]", "");
//if (key == "city")
//{
//	return kvPair[1].replaceAll("[^a-zA-Z ]", "");
//}