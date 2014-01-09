package me.pedrazas.disnums;

import me.pedrazas.disnums.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		final LinearLayout lc1 =  (LinearLayout) findViewById(R.id.lc1);
		final LinearLayout lc2 =  (LinearLayout) findViewById(R.id.lc2);
		
		lc1.getViewTreeObserver().addOnGlobalLayoutListener( 
			    new OnGlobalLayoutListener(){
			        @SuppressWarnings("deprecation")
					@SuppressLint("NewApi") @Override
			        public void onGlobalLayout() {
			        	int width = lc1.getWidth();
			        	int height = lc1.getHeight();
			        	
			        	lc1.addView(new CirclesView(MainActivity.this, Color.RED, width, height, 3));
			        	lc2.addView(new CirclesView(MainActivity.this, Color.GREEN, width, height, 4));
			        	
			        	ViewTreeObserver obs = lc1.getViewTreeObserver();
			            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			                obs.removeOnGlobalLayoutListener(this);
			            } else {
			                obs.removeGlobalOnLayoutListener(this);
			            }			            
			        }
			});
		
	    
	    
	    
	}

}