package com.github.cmput301f14t11.teamlort;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImagePopup {

	public static AlertDialog.Builder showPopup(Drawable drawable, Context context) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		
		alert.setTitle("Picture");
		ImageView imageView = new ImageView(context);
		imageView.setImageDrawable(drawable);
		alert.setView(imageView);	
		alert.setPositiveButton("Close", new DialogInterface.OnClickListener() 
		{
		
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				
			}
		});
		return alert;
	}

}
