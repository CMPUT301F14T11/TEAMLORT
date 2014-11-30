package com.github.cmput301f14t11.teamlort.Model;

import java.io.Serializable;

public class GpsLocation
implements Serializable
{
	private static final long serialVersionUID = 1L;
	private double latitude;
	private double longitude;

	public GpsLocation (double latitude, double longitude) {
		this.latitude = 0;
		this.longitude = 0; 
	}
	public double getLongtitude()
	{
		return longitude;
	}
	public double getLatitude()
	{
		return latitude;
	}
	public void setLongtitude(int provided)
	{
		longitude = provided;
	}
	public void setLatitude(int provided)
	{
		latitude = provided;
	}
}
