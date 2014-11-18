package com.github.cmput301f14t11.teamlort.Controller;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * a helper class to notify the elastic manager the network connection avaliablity 
 * @author Hang_Peng
 *
 */
public class NetworkController {
	
	private static PersistentDataManager pdm = PersistentDataManager.getInstance();
	//code is referenced from http://stackoverflow.com/
	//questions/4238921/detect-whether-there-is-an-internet
	//-connection-available-on-android
	
	public boolean checkConnection(Context context){
		ConnectivityManager connectivityManager = 
				(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		
	}
	
	public void notifyDataManager(){
		pdm.notify();
	}

}
