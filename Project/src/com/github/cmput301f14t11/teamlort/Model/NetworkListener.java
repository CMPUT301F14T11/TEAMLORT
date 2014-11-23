package com.github.cmput301f14t11.teamlort.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * a helper class to notify the elastic manager the network connection availability 
 * @author Hang_Peng
 *
 */
public class NetworkListener {
	

	/**
	 * This method check if there is a network available  
	 * @param context the service class's context
	 * @return a boolean whether there is a network connection or not
	 * @
	 */
	
	//code is referenced from http://stackoverflow.com/
	//questions/4238921/detect-whether-there-is-an-internet
	//-connection-available-on-android
	public static boolean checkConnection(Context context){
		ConnectivityManager connectivityManager = 
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
		
	}
	/**
	 * This method will notify the elastic manager that there is a network connection
	 * and pushing operation may proceed.
	 * @issue not implemented
	 */
	public void onInternetConnected(){
		return;}

}
