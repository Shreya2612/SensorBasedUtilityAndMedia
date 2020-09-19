package com.example.utilitymedia;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MSecondActivity extends Activity implements SensorEventListener {

	TextView tv,tv2,tv3;
	AlertDialog.Builder adb;
	SeekBar sb,sb2;
	int count,pos,end,cur,k=1,cp;
	float lastx,lasty,lastz;
	static final int threshold=600;
	long last=0;
	MediaPlayer mp;
	String name,names[],places;
	AudioManager am;
	SensorManager sm,sm1,sm2;
	Sensor accel,ms,ori;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_msecond);
			
			sb=(SeekBar) findViewById(R.id.seekBar1);
			sb2=(SeekBar) findViewById(R.id.seekBar2);
			tv=(TextView) findViewById(R.id.textView1);
			tv2=(TextView) findViewById(R.id.textView2);
			tv3=(TextView) findViewById(R.id.textView3); 
			
			
			count=getIntent().getIntExtra("total", 0);
			pos=getIntent().getIntExtra("value",0);
			name=getIntent().getStringExtra("song");
			places=getIntent().getStringExtra("place");
			names=new String[count];
		names=getIntent().getStringArrayExtra("songs");
		tv.setText(name);
//		tv2.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)end),TimeUnit.MILLISECONDS.toSeconds((long)end)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)end))));
		//pos1=pos;
		
		
			//mp=MediaPlayer.create(this,Uri.parse(places+"/"+names[pos]));
			//k=0;
		
		sm=(SensorManager)getSystemService(SENSOR_SERVICE);
		sm1=(SensorManager)getSystemService(SENSOR_SERVICE);
		sm2=(SensorManager)getSystemService(SENSOR_SERVICE);
		
		accel=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		ms=sm1.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		ori=sm2.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		
	sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
	sm1.registerListener(this, ms, SensorManager.SENSOR_DELAY_NORMAL);
	sm2.registerListener(this, ori, SensorManager.SENSOR_DELAY_NORMAL);

		am=(AudioManager) getSystemService(AUDIO_SERVICE);
	    
		int max=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int current=am.getStreamVolume(AudioManager.STREAM_MUSIC);
		sb2.setProgress(current);
		sb2.setMax(max);
		
		sb2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			//	tv.setText("value of seekbar"+String.valueOf(progress));
				am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
				
			}
		});
		
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if(k==0||k==2)
				mp.seekTo(progress);
				
			}
		});
		
		}
		
		@Override
			protected void onResume() {
				// TODO Auto-generated method stub
				super.onResume();
				
				sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
				sm1.registerListener(this, ms, SensorManager.SENSOR_DELAY_NORMAL);
				sm2.registerListener(this, ori, SensorManager.SENSOR_DELAY_NORMAL);
				
			}
		
		@Override
			protected void onPause() {
				// TODO Auto-generated method stub
				super.onPause();
				
				sm.unregisterListener(this,accel);
				sm1.unregisterListener(this,ms);
				sm2.unregisterListener(this, ori);
			}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			//getMenuInflater().inflate(R.menu.second, menu);
			//return true;
			
			MenuInflater mi=getMenuInflater();
			mi.inflate(R.menu.msecond, menu);
			return true;
		}
		
		@Override
			public boolean onMenuItemSelected(int featureId, MenuItem item) {
				// TODO Auto-generated method stub
			if(item.getTitle().equals("Playlist")){
				Intent intent=new Intent(MSecondActivity.this,MediaActivity.class);
				startActivity(intent);
			}
			if(item.getTitle().equals("Close")){
			//	System.exit(0);
				adb=new AlertDialog.Builder(this);
				adb.setTitle("CONFIRM");
				adb.setMessage("Do you want to Close?");
		    	adb.setIcon(R.drawable.system_help);	
				adb.setPositiveButton("OK", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if(k==0){
							mp.stop();
						}
						System.exit(0);
						
					}
				});
				adb.setNegativeButton("CANCEL", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "cancelling close", Toast.LENGTH_SHORT).show();
						
					}

			
				});
				adb.show();
			}
			if(item.getTitle().equals("About")){
				Intent in=new Intent(MSecondActivity.this,MAboutActivity.class);
				startActivity(in);
			}
				return super.onMenuItemSelected(featureId, item);
			}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle action bar item clicks here. The action bar will
			// automatically handle clicks on the Home/Up button, so long
			// as you specify a parent activity in AndroidManifest.xml.
			int id = item.getItemId();
			if (id == R.id.action_settings) {
				return true;
			}
			return super.onOptionsItemSelected(item);
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			Sensor ms1=event.sensor;
			if(ms1.getType()==accel.TYPE_ACCELEROMETER){
				float x=event.values[0];
				float y=event.values[1];
				float z=event.values[2];
			
			
			long curTime=System.currentTimeMillis();
			
			if(curTime-last>100){
				long diff=curTime-last;
				last=curTime;
			
			
			float speed=Math.abs(x+y+z-lastx-lasty-lastz)/diff*10000;
			
			if(speed>threshold){
				//when the song is playng
				if(k==0&&k!=3){
					mp.pause();
					k=2;
					cp=mp.getCurrentPosition();
					sb.setProgress(cp);
					 tv3.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)cp),TimeUnit.MILLISECONDS.toSeconds((long)cp)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)cp))));
						
									
				}
				
			
				else{
					//if the song is in pause state
					if(k==2){
							mp.start();
							k=0;
							cp=mp.getCurrentPosition();
							sb.setProgress(cp);
							 tv3.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)cp),TimeUnit.MILLISECONDS.toSeconds((long)cp)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)cp))));
								
						}
		//when the song is played for first tym
						if(k!=0&&k!=2){
				mp=MediaPlayer.create(getApplicationContext(), Uri.parse(places+"/"+names[pos]));
				mp.start();
				k=0;
				end=mp.getDuration();
				cp=mp.getCurrentPosition();
				sb.setMax(end);
				sb.setProgress(cp);
				tv.setText(names[pos]);
				tv2.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)end),TimeUnit.MILLISECONDS.toSeconds((long)end)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)end))));
				 tv3.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)cp),TimeUnit.MILLISECONDS.toSeconds((long)cp)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)cp))));
					
						}
				//	if(t.isAlive()==false)
		//		t.start();
				}}
				/*end=mp.getDuration();
				cur=mp.getCurrentPosition();
				if(cur==end)
					pos++;
					}*/
					
			}
		
			lastx=x;
			lasty=y;
			lastz=z;
			}
	 
			
			
		if(ms1.getType()==ms.TYPE_PROXIMITY){
				if(event.values[0]==ms.getMaximumRange()){
					if(k==0&&k!=2){
						cp=mp.getCurrentPosition();
						
						 tv3.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)cp),TimeUnit.MILLISECONDS.toSeconds((long)cp)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)cp))));
							
						sb.setProgress(cp);
				Toast.makeText(getApplicationContext(), "stop",Toast.LENGTH_LONG).show();
				mp.stop();
					k=3;
					mp=null;
					
					
					}		}}
		/*if(ms1.getType()==ori.TYPE_ORIENTATION){
			float x1=event.values[0];
			float y1=event.values[1];
			float z1=event.values[2];
			
		
		 if(x1>0&&x1<=30&&z1>=-5){    //next
				 if(pos<count-1 ){
			pos=pos+1;}
		//	pos1=pos;
				 else{
					 pos=0;
					// pos1=pos;
				 }
				 if(k!=1&&k!=3){
					mp.stop();
					mp=null;    //for stopping the song after next
					}
						//i=5;
						mp=MediaPlayer.create(MSecondActivity.this,Uri.parse(places+"/"+names[pos]));
						tv.setText(names[pos]);
						end=mp.getDuration();
						cp=mp.getCurrentPosition();
						tv2.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)end),TimeUnit.MILLISECONDS.toSeconds((long)end)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)end))));
						 tv3.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)cp),TimeUnit.MILLISECONDS.toSeconds((long)cp)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)cp))));
						sb.setProgress(cp);	
						 Toast.makeText(getApplicationContext(), " next", Toast.LENGTH_SHORT).show();
					if(k==0){
						mp.start();
						k=0;*/
					//	tv.setText(names[pos]);	
				//	}
					
				 
				/* else{
					 Toast.makeText(getApplicationContext(), "no next", Toast.LENGTH_SHORT).show();
				 }*/
			
			 /*}
			 if(x1>=250&&x1<=320&&z1>=3){
				 if(pos>0 ){
					pos=pos-1;
					//pos1=pos;
					}
				 else{
					 pos=count-1;
					// pos1=pos;
				 }
				 if(k!=1&&k!=3){
				 mp.stop();
					mp=null;
				 }
						mp=MediaPlayer.create(this,Uri.parse(places+"/"+names[pos]));
						tv.setText(names[pos]);
						end=mp.getDuration();
						cp=mp.getCurrentPosition();
						tv2.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)end),TimeUnit.MILLISECONDS.toSeconds((long)end)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)end))));
						 tv3.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)cp),TimeUnit.MILLISECONDS.toSeconds((long)cp)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)cp))));
						 sb.setProgress(cp);	
					//	tv2.setText(String.format("%d min,%d sec",TimeUnit.MILLISECONDS.toMinutes((long)end),TimeUnit.MILLISECONDS.toSeconds((long)end)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)end))));
						 Toast.makeText(getApplicationContext(), "previous", Toast.LENGTH_SHORT).show();
					if(k==0){
						mp.start();
						k=0;
						
					}
				 }*/
				/* else{
					 Toast.makeText(getApplicationContext(), "no previous", Toast.LENGTH_SHORT).show();
				 }*/
			 
	//	}
		}
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	}


