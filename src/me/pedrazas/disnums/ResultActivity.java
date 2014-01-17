package me.pedrazas.disnums;

import java.util.List;

import me.pedrazas.disnums.data.DbHelper;
import me.pedrazas.disnums.data.DebugDataSource;
import me.pedrazas.disnums.om.Debug;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ResultActivity extends Activity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_result);
		Intent intent = getIntent();
		boolean success = false;
		String color = null;
		String validColor = null;
		if(intent != null){
			success = intent.getBooleanExtra("SUCCESS", false);
			validColor = intent.getStringExtra("VALIDCOLOR");
			color = intent.getStringExtra("COLOR");
		}
		
		TextView txt =  (TextView) findViewById(R.id.textResult);
		View view = findViewById(R.id.textResultLayout);
	    String text;
		if(success){
			text = getResources().getString(R.string.great);
			view.setBackgroundColor(Color.parseColor("#008A05"));
			
		}else{
			if(color != null){
				text = getResources().getString(R.string.ohoh) + "\n" + color + "?... \n it was " + validColor;	
			}else{
				text = getResources().getString(R.string.missed);
			}
			
			view.setBackgroundColor(Color.parseColor("#E60017"));
		}
		txt.setText(text);
		DebugDataSource ds = new DebugDataSource(this);
		List<Debug> entries = ds.getAllDebugEntries();
		for(Debug d : entries){
			Log.d("Circles", d.toJson());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}
	
	public void playAgain(View view) {
	    Intent intent = new Intent(ResultActivity.this, PlayActivity.class);
	    startActivity(intent);
	}
	
	public void doExit(View view) {
	    Intent intent = new Intent(ResultActivity.this, SummaryActivity.class);
	    startActivity(intent);
	}
}
