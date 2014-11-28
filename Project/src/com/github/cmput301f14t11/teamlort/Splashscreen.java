package com.github.cmput301f14t11.teamlort;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class Splashscreen extends Activity{

	VideoView vidHolder;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       
        try
        {
            vidHolder = new VideoView(this);
            setContentView(vidHolder);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.lortanswers);
            vidHolder.setVideoURI(video);
            vidHolder.setOnCompletionListener(new OnCompletionListener()
            {
                public void onCompletion(MediaPlayer mp) {
                    jump();
            }});
            vidHolder.start();

        }
        catch(Exception ex) 
        {
            jump();
        }
        }
        private void jump()
        {
            if(isFinishing())
                return;
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
        
	

}
