package com.example.utilitymedia;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class FirstActivity extends Activity {
	ImageButton m1,m2;
	MediaPlayer mp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		m1=(ImageButton) findViewById(R.id.imageButton1);
		m2=(ImageButton) findViewById(R.id.imageButton2);
		
		if(mp==null){
			mp=MediaPlayer.create(this,R.raw.voice);
			mp.start();
		    //flag=1;
		  //  Toast.makeText(this, "Alarm started!", Toast.LENGTH_SHORT).show();
		  //  mp=null;
			}
		
		m1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.stop();
				mp=null;
				Intent i=new Intent(FirstActivity.this,MediaActivity.class);
				startActivity(i);
			}

		
	});
		m2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.stop();
				mp=null;
				Intent i=new Intent(FirstActivity.this,UtilityActivity.class);
				startActivity(i);	
			}

	});
	
}}
