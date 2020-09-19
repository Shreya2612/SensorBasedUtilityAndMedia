package com.example.utilitymedia;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MAboutActivity extends Activity {

	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;           
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mabout);
		
		tv1=(TextView) findViewById(R.id.textView1);
		tv2=(TextView) findViewById(R.id.textView2);
		tv3=(TextView) findViewById(R.id.textView3);
		tv4=(TextView) findViewById(R.id.textView4);
		tv5=(TextView) findViewById(R.id.textView5);
		tv6=(TextView) findViewById(R.id.textView6);
		tv7=(TextView) findViewById(R.id.textView7);
		
		tv5.setText("This music player is completely based on sensors which are- PROXIMITY,ACCELEROMETER AND ORIENTATION. " +
		         "Please make sure thatthe above mentioned Sensors are present in your device/mobile. "+
				 "This app plays only AUDIO FILES with .MP3 extension present in INTERNAL STORAGE of your device/mobile. " );
		
		tv7.setText(" TO PLAY : Shake your Device once. " +
				    " TO PAUSE: Again Shake your device once. " +
				    " TO STOP: It works on Proximity Sensor so WAVE your hand over your device to stop the currently playing song. " +
				    " NEXT SONG: Keep your device in horizontal plane and turn towards Left(Range: 0<=x-axis<=20). " +
				    " PREVIOUS SONG: Keep your device in horizontal plane and turn towards yourself(Range:-4=<z-axis<=1 & y-axis>=-70). " + 
				    " FORWARD: Move the Seekbar Forward. " +
				    " BACKWARD: Move the Seekbar Backward. " +
				    " VOLUME: Use the given Volume Seekbar. " +
				    " TO RETURN TO PLAYLIST: Use the Option Menu. " +
				    " TO EXIT: Use the Option Menu ");  
				
						

	
}
}
