package me.pedrazas.disnums;

import java.util.List;

import me.pedrazas.disnums.data.DebugDataSource;
import me.pedrazas.disnums.om.Debug;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SummaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_summary);
		DebugDataSource ds = new DebugDataSource(this);
		List<Debug> entries = ds.getAllDebugEntries();
		for(Debug d : entries){
			Log.d("Circles", d.toJson());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
		return true;
	}
	
	public void playAgain(View view) {
		Log.d("Circles", "playAgain Clicked");
	    Intent intent = new Intent(SummaryActivity.this, PlayActivity.class);
	    startActivity(intent);
	}

}
