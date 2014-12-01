package com.github.cmput301f14t11.teamlort.Model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

/**
 * ImageBuilder: A class used to send intents to other apps for the purposes
 * of getting an image.
 * @author Yuey
 *
 */
public class ImageBuilder
{
	public static final int IMAGE_REQUEST_CODE = 1;
	
	private Uri tempFileURI = null;
	
	public ImageBuilder()
	{
		
	}
	
	/**
	 * When called, will start a new Activity via an intent for an image from
	 * a camera. This must be called before RetrieveImageFromStorage to get an
	 * image.
	 * 
	 * @param callingActivity - The activity that wants an image.
	 */
	public void SendImageIntent(Activity callingActivity)
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		if (intent.resolveActivity(callingActivity.getPackageManager()) != null)
		{
			File photoFile = null;
			
			try
			{
				photoFile = createImgTempFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			if (photoFile != null)
			{
				intent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileURI);
				callingActivity.startActivityForResult(intent, IMAGE_REQUEST_CODE);
			}
		}
		else
		{
			Toast.makeText(callingActivity, 
					"You don't have any apps that can take a photo =(", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Gets an image from external storage produced by a camera app.
	 * Typically, this should be called inside of onActivityResult under the
	 * case ImageBuilder.IMAGE_REQUEST_CODE. This method will produce a toast
	 * to the UI and return null in the case there is an error. Note that 
	 * SendImageIntent must be called before this method to work.
	 * 
	 * @param callingActivity - the activity that wants the image.
	 * @return A Drawable (less than 64kB) that the external app produced, or null if there was an error.
	 */
	public Drawable RetrieveImageFromStorage(Activity callingActivity)
	{
		if (tempFileURI == null)
		{
			Toast.makeText(callingActivity.getApplicationContext(),
					"Oops! Something went wrong with the camera.", Toast.LENGTH_LONG).show();
			Log.e("com.github.cmput301f14t11.teamlort.Model.ImageBuilder.RetrieveImageFromStorage()",
					"RetrieveImageFromStorage called but no URI was set (SendImageIntent never called?)");
			tempFileURI = null;
			return null;
		}
		
		Bitmap rawImg = BitmapFactory.decodeFile(tempFileURI.getPath());
		if (rawImg != null)
		{
			Drawable compressMe = (Drawable) new BitmapDrawable(rawImg);
			Bitmap bitmap = ((BitmapDrawable) compressMe).getBitmap();
			
			while (bitmap.getByteCount() > 64000)
			{
				long resizeHeight = Math.round(bitmap.getHeight() * 0.9);
				long resizeWidth  = Math.round(bitmap.getWidth()  * 0.9);
				
				bitmap = Bitmap.createScaledBitmap(bitmap, (int) resizeWidth, (int) resizeHeight, false);
			}
			
			tempFileURI = null;
			return (Drawable) new BitmapDrawable(bitmap);
		}
		else 
		{
			Toast.makeText(callingActivity.getApplicationContext(),
					"Oops! Something went wrong with the camera.", Toast.LENGTH_LONG).show();
			tempFileURI = null;
			return null;
		}
	}
	
	private File createImgTempFile()
	throws IOException
	{
		String fileName =
				"IMG_" + 
				new SimpleDateFormat("yyyy_MM_dd_-_HHmmss", Locale.getDefault()).format(new Date());
		
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		File imageFile = File.createTempFile(fileName, ".jpg", dir);
		if (imageFile != null)
		{
			tempFileURI = Uri.fromFile(imageFile);
		}
		
		return imageFile;
	}
}
