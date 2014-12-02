package com.github.cmput301f14t11.teamlort.Model;

import android.util.Log;

public class GeoParser
{
	public static String parseGeoString(String s)
	{
		String[] tokens = s.split(":");
		for (int i = 0; i < tokens.length; i++)
		{
			if(tokens[i].contains("display_name"))
			{
				return tokens[i+1].replace("address", "");
			}
			
			Log.i("parser",tokens[i]);
			
			
		}
		return "not a legit location";
	}
}
//String key = kvPair[0].replaceAll("[^a-zA-Z ]", "");
//if (key == "city")
//{
//	return kvPair[1].replaceAll("[^a-zA-Z ]", "");
//}