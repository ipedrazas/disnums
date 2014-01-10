package me.pedrazas.disnums.utils;

public class StopWatch {


	  private long startTime = 0;
	  private long stopTime = 0;
	  private boolean running = false;


	  public void start() {
	    this.startTime = System.nanoTime();
	    this.running = true;
	  }


	  public void stop() {
	    this.stopTime = System.nanoTime();
	    this.running = false;
	  }


	  //elaspsed time in milliseconds
	  public float getElapsedTime() {
	    float elapsed;
	    if (running) {
	      elapsed = (System.nanoTime() - startTime);
	    }
	    else {
	      elapsed = (stopTime - startTime);
	    }
	    return elapsed;
	  }


	  //elaspsed time in seconds
	  public float getElapsedTimeSecs() {
	    float elapsed;
	    if (running) {
	      elapsed = ((float)(System.nanoTime() - startTime) / 1000000000);
	    }
	    else {
	      elapsed = ((float)(stopTime - startTime) / 1000000000);
	    }
	    return elapsed;
	  }
}
