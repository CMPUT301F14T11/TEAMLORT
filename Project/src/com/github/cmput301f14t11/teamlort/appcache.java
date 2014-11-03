package com.github.cmput301f14t11.teamlort;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class appcache {
	
	private static Context context;
	static String file_save_name = "appcache.sav";
	static Question tempwrite = new Question();
	static Question tempread = new Question();
	public static void setContext(Context ctx)
	{
		context = ctx;
		Toast.makeText(context, "before read or write", Toast.LENGTH_SHORT).show();
    }
	static void providewritedata(Question provided)
	{
		tempwrite = provided;
	}
	
	public static Question read()
	{
		try 
		{
			FileInputStream fis =  context.openFileInput(file_save_name);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			Type type = new TypeToken<Question>() {}.getType();
			tempread = gson.fromJson(in,type);
			fis.close();
			Toast.makeText(context, "object successfully read", Toast.LENGTH_SHORT).show();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			File yourFile = new File(file_save_name);//instead of throwing error and crying like a little girl, create the file instead
			try {
				yourFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempread;
	}
	public static void write() 
	{
		try 
		{
			FileOutputStream fos = context.openFileOutput(file_save_name,Context.MODE_PRIVATE);
			OutputStreamWriter os = new OutputStreamWriter(fos);
			Gson gsonout = new Gson();
			gsonout.toJson(tempwrite,os);		
			os.flush();
			fos.close();
			Toast.makeText(context, "object successfully written", Toast.LENGTH_SHORT).show();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			File yourFile = new File(file_save_name);
			try {
				yourFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
