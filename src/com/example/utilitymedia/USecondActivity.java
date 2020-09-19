package com.example.utilitymedia;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class USecondActivity extends Activity implements SensorEventListener {
	

	private SensorManager senSensorManager,senSensorManager1,senSensorManager2 ;
	private Sensor senAccelerometer;
	private long lastupdate=0;
	private float lastx,lasty,lastz;
	private static final int shakethreshold=200;    // To be reduced Shreyaaaaaaa
	MediaPlayer mp;
    AudioManager am;
	Thread t;
	TextView tv;
    int flag=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usecond);
		
      tv=(TextView) findViewById(R.id.textView1);
      
      senSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
	  senAccelerometer=senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	  senSensorManager.registerListener(USecondActivity.this, senAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
	
		am=(AudioManager) getSystemService(AUDIO_SERVICE);
		int max=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int current=am.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		
		
			//if(t.isAlive()==false)   //for playing after stop
		//	t.start();
				
				
		
		/*t=new Thread()
		{
			public void run()
			{
				int currentposition=0;
			int	 totalduration=mp.getDuration();
			
				sb.setMax(totalduration);
				
				while(flag==0){          
					try
					{
		             t.sleep(500);
		             currentposition=mp.getCurrentPosition();
		            }
					catch(Exception e){
						Toast.makeText(getApplicationContext(), "nt working", Toast.LENGTH_SHORT).show();
					}
			
				sb.setProgress(currentposition);	
			

			}
		}
	};  */
		
	
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		// TODO Auto-generated method stub
		Sensor mySensor=sensorEvent.sensor;
		if(mySensor.getType()==senAccelerometer.TYPE_ACCELEROMETER){
			float x=sensorEvent.values[0];
			float y=sensorEvent.values[1];
			float z=sensorEvent.values[2];
			
			long curtime=System.currentTimeMillis();
			if((curtime-lastupdate)>100){
				long difftime=(curtime-lastupdate);
				lastupdate=curtime;
				
				float speed=Math.abs(x+y+z-lastx-lasty-lastz)/difftime*10000;
				if(speed>shakethreshold){
				 Toast.makeText(this, "Shake Detected-speed: " + speed, Toast.LENGTH_SHORT).show();
				 tv.setText("MOTION DETECTED");
				 
				
					if(mp==null){
						mp=MediaPlayer.create(this,R.raw.warn);
						mp.start();
					    //flag=1;
					    Toast.makeText(this, "Alarm started!", Toast.LENGTH_SHORT).show();
					  mp=null;
						}
					
					
					
						
			}
			lastx=x;
			lasty=y;
		    lastz=z;
		}	
	}
}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		senSensorManager.unregisterListener(this, senAccelerometer);
	}

}
