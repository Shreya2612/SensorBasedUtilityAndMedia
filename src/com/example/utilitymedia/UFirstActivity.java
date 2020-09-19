package com.example.utilitymedia;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UFirstActivity extends Activity {

	TextView textLight;
	SensorManager sensorManager;
	Sensor sensor;
	Camera camera;
	boolean isLighOn = false;
	Parameters p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ufirst);
		
	   sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		 sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		textLight = (TextView) findViewById(R.id.textLight);
		Context context = this;
		PackageManager pm = context.getPackageManager();

		// if device support camera?
		if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Log.e("err", "Device has no camera!");
			return;
		}

		camera = Camera.open();
		  p = camera.getParameters();

	}
	
	public void onResume() {
		super.onResume();
		sensorManager.registerListener(lightListener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
 
	public void onStop() {
		super.onStop();
		sensorManager.unregisterListener(lightListener);
		

			if (camera != null) {
				camera.release();
			}
		}
	

	public SensorEventListener lightListener = new SensorEventListener() {
		public void onAccuracyChanged(Sensor sensor, int acc) { }
 
		public void onSensorChanged(SensorEvent event) {
			float x = event.values[0];

			textLight.setText((int)x + " lux");
			if(x>30 && x<1000)
			{
				if (isLighOn) {

					Log.i("info", "torch is turn off!");

					p.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(p);
					camera.stopPreview();
					isLighOn = false;

				} else {

					Log.i("info", "torch is turn on!");

					p.setFlashMode(Parameters.FLASH_MODE_TORCH);

					camera.setParameters(p);
					camera.startPreview();
					isLighOn = true;	
			}
		}
	}

			
		
		};

}

