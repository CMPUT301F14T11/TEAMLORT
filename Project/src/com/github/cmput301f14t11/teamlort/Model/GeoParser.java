package com.github.cmput301f14t11.teamlort.Model;

public class GeoParser
{
	public static String parseGeoString(String s)
	{
		String[] tokens = s.split(",");
		for (String token : tokens)
		{
			String[] kvPair = token.split(":");
			String key = kvPair[0].replaceAll("[^a-zA-Z ]", "");
			if (key == "city")
			{
				return kvPair[1].replaceAll("[^a-zA-Z ]", "");
			}
		}
		return null;
	}
}
