package com.example.utilitymedia;

import java.io.File;
import java.io.FilenameFilter;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

public class MediaActivity extends Activity implements OnQueryTextListener {

	ListView lv;
	int c;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_media);
			
			   lv=(ListView)findViewById(R.id.listView1);
				File f=Environment.getExternalStorageDirectory();
				FilenameFilter f1=new Filef("mp3");
		        final String s[]=f.list(f1);
				 final String path=f.getPath();
				
				final ArrayAdapter<String> a=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,s);
				lv.setAdapter(a);
			    lv.setTextFilterEnabled(true);
				
				SearchManager sm=(SearchManager)getSystemService(SEARCH_SERVICE);
				SearchView sv=(SearchView) findViewById(R.id.searchView1);
				sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
				sv.setSubmitButtonEnabled(true);
				sv.setOnQueryTextListener(this);
				
				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Toast.makeText(MediaActivity.this, path+"/"+s[position], Toast.LENGTH_SHORT).show();
					
						c=a.getCount();
						Intent i =new Intent(MediaActivity.this,MSecondActivity.class);
						i.putExtra("total", c);
						i.putExtra("value", position);
						i.putExtra("place", path);
					i.putExtra("song",(String)parent.getItemAtPosition(position));
				i.putExtra("songs", s);
						startActivity(i);
						
					}
				});
					}
				
		

	

		@Override
		public boolean onQueryTextSubmit(String query) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onQueryTextChange(String newText) {
			// TODO Auto-generated method stub	
			if(TextUtils.isEmpty(newText)){
			lv.clearTextFilter();
		}
		else{
			lv.setFilterText((newText).toString());
			
		}
			return true;
		}
	}
