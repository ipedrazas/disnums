package me.pedrazas.disnums;

import java.util.ArrayList;

import me.pedrazas.disnums.utils.StopWatch;
import me.pedrazas.disnums.utils.Utils;
import me.pedrazas.disnums.views.ImageCirclesView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Menu;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;

public class PlayActivity extends Activity {
	
	protected static final int REQUEST_OK = 1;
	int blue = 0;
	int red = 0;
	private final StopWatch stopWatch = new StopWatch();
	
	private boolean isBlue(){
		if( this.blue < this.red){
			return false;
		}else{
			return true;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		blue = Utils.randInt(1, 10);
		red = Utils.randInt(1, 10);
		// don't want same amount of dots
		if(red == blue){
			red = red + 1;
		}
		
		Log.d("Circles", "Red, Green: " + red + ", " + blue);
		
		class MyRecognitionListener implements RecognitionListener {
			
			private final String RED = "red";
			private final String BLUE = "blue";

            @Override
            public void onBeginningOfSpeech() {
                    Log.d("Speech", "onBeginningOfSpeech");
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                    Log.d("Speech", "onBufferReceived");
            }

            @Override
            public void onEndOfSpeech() {
                    Log.d("Speech", "onEndOfSpeech");
            }

            @Override
            public void onError(int error) {
            	stopWatch.stop();
                    Log.d("Circles", "onError " + error);
                    Log.d("Circles", "Total time: " + stopWatch.getElapsedTimeSecs());
                    Log.d("Circles", "Red, Green: " + red + ", " + blue);
                    // timeout or no match
                    if(error == 6 || error == 7){
                    	Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
                    	intent.putExtra("SUCCESS", false);
                	    startActivity(intent);
                    }
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                    Log.d("Speech", "onEvent");
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                    Log.d("Speech", "onPartialResults");
            }

            @Override
            public void onReadyForSpeech(Bundle params) {
                    Log.d("Speech", "onReadyForSpeech");
            }
           

            @Override
            public void onResults(Bundle results) {
            		stopWatch.stop();
                    Log.d("Circles", "onResults");
                    Log.d("Circles", "Total time: " + stopWatch.getElapsedTimeSecs());
                    Log.d("Circles", "Red, Green: " + red + ", " + blue);
                    ArrayList<String> strlist = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    String color = this.getColor(strlist);
                    Log.d("Circles", "Color=" + color);
                    boolean success = false;
                	if (PlayActivity.this.isBlue()){
                		if(color.equalsIgnoreCase("blue")){                            
                    		success = true;
                    	}                            	
                    }else{
                    	if(color.equalsIgnoreCase("red")){                            
                    		success = true;
                    	}  
                    }
                	Log.d("Circles", "Success=" + success);
                    Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
                	intent.putExtra("SUCCESS", success);
            	    startActivity(intent);
                    
            }

            @Override
            public void onRmsChanged(float rmsdB) {
//                    Log.d("Circles", "onRmsChanged");
            }
            
            private String getColor(ArrayList<String> strlist){
            	for (int i = 0; i < strlist.size();i++ ) {
            		Log.d("Circles", "result=" + strlist.get(i));
            		if(strlist.get(i).equalsIgnoreCase(BLUE)){
            			return BLUE;
            		}
            		if(strlist.get(i).equalsIgnoreCase(RED)){
            			return RED;
            		}
            	}
            	return "";            	
            }
		}
		
		
		
		SpeechRecognizer sr = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        MyRecognitionListener listener = new MyRecognitionListener();
        sr.setRecognitionListener(listener);
        sr.startListening(RecognizerIntent.getVoiceDetailsIntent(getApplicationContext()));
		
		final LinearLayout lc1 =  (LinearLayout) findViewById(R.id.lc1);
		final LinearLayout lc2 =  (LinearLayout) findViewById(R.id.lc2);
			
			lc1.getViewTreeObserver().addOnGlobalLayoutListener( 
				    new OnGlobalLayoutListener(){
				        @SuppressWarnings("deprecation")
						@SuppressLint("NewApi") @Override
				        public void onGlobalLayout() {
				        	int width = lc1.getWidth();
				        	int height = lc1.getHeight();
				        	Log.d("Circles", "Details 1: " + width + ", " + height/2);
				        	lc1.addView(new ImageCirclesView(PlayActivity.this, R.drawable.button_round_red, width, height, PlayActivity.this.red));
				        	Log.d("Circles", "Details 2: " + width + ", " + height);
				        	lc2.addView(new ImageCirclesView(PlayActivity.this, R.drawable.button_round_blue, width, height, PlayActivity.this.blue));

				        	ViewTreeObserver obs = lc1.getViewTreeObserver();
				            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				                obs.removeOnGlobalLayoutListener(this);
				            } else {
				                obs.removeGlobalOnLayoutListener(this);
				            }
				            stopWatch.start();
				        }
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}


}
