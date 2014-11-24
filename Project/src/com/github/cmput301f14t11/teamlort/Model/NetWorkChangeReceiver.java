package com.github.cmput301f14t11.teamlort.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

//http://viralpatel.net/blogs/android-internet-connection
//-status-network-change/
public class NetWorkChangeReceiver
extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (NetworkListener.checkConnection(context))
		{
			Toast.makeText(context, "Network is on", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(context, "Network is off", Toast.LENGTH_LONG).show();
		}
		
	}

}
