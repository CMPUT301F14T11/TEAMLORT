package com.github.cmput301f14t11.teamlort.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * This class will get notified when network status changed.
 * @author Hang_Peng
 *
 */
public class NetWorkChangeReceiver
extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (NetworkListener.checkConnection(context))
		{
			Toast.makeText(context, "Pushing Questions", Toast.LENGTH_LONG).show();
			PushQueue.getInstance().pushAll();
			Toast.makeText(context, "Pushing Complete!", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(context, "Network is off", Toast.LENGTH_LONG).show();
		}		
	}
}
