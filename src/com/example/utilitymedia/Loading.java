package com.example.utilitymedia;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class Loading extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		
VideoView videoView =(VideoView)findViewById(R.id.videoView1);  
        
        //Creating MediaController  
MediaController mediaController= new MediaController(this);  
    mediaController.setAnchorView(videoView);          
 
      //specify the location of media file  
   //Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/media/1.mp4");          
        
      //Setting MediaController and URI, then starting the videoView  
   videoView.setMediaController(mediaController);  
   videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kitkat));          
   videoView.requestFocus();  
   videoView.start(); 
   
		// Create a Timer
		
		Timer RunSplash = new Timer();
 
		// Task to do when the timer ends
		TimerTask ShowSplash = new TimerTask() {
			@Override
			public void run() {
				// Close SplashScreenActivity.class
				finish();
 
				// Start MainActivity.class
				Intent myIntent = new Intent(getBaseContext(),FirstActivity.class);
				startActivity(myIntent);
			}
		};
 
		// Start the timer
		RunSplash.schedule(ShowSplash, 6000);
	}
}