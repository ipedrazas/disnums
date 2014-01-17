package me.pedrazas.disnums;

import java.util.ArrayList;

import me.pedrazas.disnums.data.DebugDataSource;
import me.pedrazas.disnums.om.Debug;
import me.pedrazas.disnums.om.Round;
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
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class PlayActivity extends Activity {
	
	protected static final int REQUEST_OK = 1;
	
	int colorOne = 0;
	int colorTwo = 0;
	
	private final Round round = new Round();
	private final StopWatch stopWatch = new StopWatch();
	String firstColor;
	String secondColor;
	
	private boolean isColorOne(){
		if( this.colorOne < this.colorTwo){
			return false;
		}else{
			return true;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_play);
		this.firstColor = getResources().getString(R.string.blue);
		this.secondColor = getResources().getString(R.string.red);
		this.colorOne = Utils.randInt(1, 10);
		this.colorTwo = Utils.randInt(1, 10);
		// don't want same amount of dots
		if(colorTwo == colorOne){
			colorTwo = colorTwo + 1;
		}
		
		Log.d("Circles", "Red, Green: " + colorTwo + ", " + colorOne);
		
		class MyRecognitionListener implements RecognitionListener {

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
				ArrayList<String> strlist = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				String color = this.getColor(strlist);
				Log.d("Circles", "Color=" + color);
				PlayActivity.this.showResult(color);                   
            }

            @Override
            public void onRmsChanged(float rmsdB) {
//                    Log.d("Circles", "onRmsChanged");
            }
            
            private String getColor(ArrayList<String> strlist){
            	for (int i = 0; i < strlist.size();i++ ) {
            		Log.d("Circles", "result=" + strlist.get(i));
            		if(strlist.get(i).equalsIgnoreCase(PlayActivity.this.firstColor)){
            			return PlayActivity.this.firstColor;
            		}
            		if(strlist.get(i).equalsIgnoreCase(PlayActivity.this.secondColor)){
            			return PlayActivity.this.secondColor;
            		}
            	}
            	return strlist.get(0);            	
            }
		}
		
//		new Runnable(){ 
//			public void run(){ 

				SpeechRecognizer sr = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
		        MyRecognitionListener listener = new MyRecognitionListener();
		        sr.setRecognitionListener(listener);
		        sr.startListening(RecognizerIntent.getVoiceDetailsIntent(getApplicationContext()));
		        
//				}
//			};
		
		
		
		final LinearLayout lc1 =  (LinearLayout) findViewById(R.id.lc1);
		final LinearLayout lc2 =  (LinearLayout) findViewById(R.id.lc2);
			
			lc1.getViewTreeObserver().addOnGlobalLayoutListener( 
				    new OnGlobalLayoutListener(){
				        @SuppressWarnings("deprecation")
						@SuppressLint("NewApi") @Override
				        public void onGlobalLayout() {
				        	int width = lc1.getWidth();
				        	int height = lc1.getHeight();
				        	lc1.addView(new ImageCirclesView(PlayActivity.this, R.drawable.button_round_red, width, height, PlayActivity.this.colorTwo));
				        	lc2.addView(new ImageCirclesView(PlayActivity.this, R.drawable.button_round_blue, width, height, PlayActivity.this.colorOne));
				        	ViewTreeObserver obs = lc1.getViewTreeObserver();
				            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				                obs.removeOnGlobalLayoutListener(this);
				            } else {
				                obs.removeGlobalOnLayoutListener(this);
				            }
				            PlayActivity.this.round.start();
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
	
	public void selectBlue(View view) 
	{
		stopWatch.stop();
		showResult("blue");
	}
	
	public void selectRed(View view) 
	{
		stopWatch.stop();
		showResult("red");
	}
	
	public void showResult(String color){
		boolean success = false;
		String validColor = null;
		boolean isColorOne = this.isColorOne();
		
		Log.d("Circles", "isColorOne? " + isColorOne + ", Color: " + color + " colorOne: " + colorOne + ", colorTwo " + colorTwo);
     	if (isColorOne){
     		validColor = this.firstColor;
     		if(color.equalsIgnoreCase(this.firstColor)){                            
         		success = true;
         	}                            	
         }else{
        	 validColor = this.secondColor;
         	if(color.equalsIgnoreCase(this.secondColor)){                            
         		success = true;         		
         	}  
         }
     	Log.d("Circles", "Success=" + success);
     	Log.d("Circles", "Entry color is " + color + ", First is " + this.firstColor + ", second " + this.secondColor + ". Valid: " + validColor );
        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
     	intent.putExtra("SUCCESS", success);
     	intent.putExtra("COLOR", color);
     	intent.putExtra("VALIDCOLOR", validColor);
     	Debug d = new Debug();
     	d.setColor(validColor);
     	d.setDate();
     	d.setDuration(this.stopWatch.getElapsedTimeSecs());
     	d.setEnd(this.stopWatch.getStopTime());
     	d.setSelectedColor(color);
     	d.setStart(this.stopWatch.getStartTime());
     	d.setSuccess(success);
     	DebugDataSource ds = new DebugDataSource(this);
     	ds.addDebugEntry(d);
 	    startActivity(intent);
	}


}
