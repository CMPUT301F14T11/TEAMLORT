package com.github.cmput301f14t11.teamlort.Model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

public class ImageBuilder
{
	public static File CreateTempFile() 
	throws IOException
	{
		String fileName =
				"IMG_" + 
				new SimpleDateFormat("yyyy_MM_dd_-_HHmmss", Locale.getDefault()).format(new Date());
		
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		File imageFile = File.createTempFile(fileName, ".jpg", dir);
		
		return imageFile;
	}
	
	public static void BuildImage(Uri imageURI, ImageView target)
	{
		Bitmap rawImg = BitmapFactory.decodeFile(imageURI.getPath());
		if (rawImg != null)
		{
			Drawable compressMe = (Drawable) new BitmapDrawable(rawImg);
			new CompressImageTask(target).execute(compressMe);
		}
	}
	
	private static class CompressImageTask
	extends AsyncTask<Drawable, Void, Drawable>
	{
		ImageView imageView;
		
		public CompressImageTask(ImageView destination)
		{
			this.imageView = destination;
		}
		
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
		}
		
		@Override
		protected Drawable doInBackground(Drawable... params)
		{
			Drawable result = params[0];
			if (result == null) return null;
			
			Bitmap bitmap = ((BitmapDrawable) result).getBitmap();
			
			while (bitmap.getByteCount() > 64000)
			{
				long resizeHeight = Math.round(bitmap.getHeight() * 0.9);
				long resizeWidth  = Math.round(bitmap.getWidth()  * 0.9);
				
				bitmap = Bitmap.createScaledBitmap(bitmap, (int) resizeWidth, (int) resizeHeight, false);
			}
			
			return (Drawable) new BitmapDrawable(bitmap);
		}
		
		@Override
		protected void onCancelled(Drawable result)
		{
			super.onCancelled(result);
		}
		
		@Override
		protected void onPostExecute(Drawable result)
		{			
			imageView.setImageDrawable(result);
			
			super.onPostExecute(result);
		}
	}
}
