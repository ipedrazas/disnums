package me.pedrazas.disnums.om;

import me.pedrazas.disnums.utils.StopWatch;

public class Round {
	
	private int points;
	private StopWatch stopwatch;
	
	public Round() {
		super();
		this.stopwatch = new StopWatch();
	}
	
	public long getStartTime() {
		return this.stopwatch.getStartTime();
	}
	public long getEndTime() {
		return this.stopwatch.getStopTime();
	}
	public float getDuration(){
		return this.stopwatch.getElapsedTimeSecs();
	}
	public void start() {
		this.stopwatch.start();
	}
	public void end() {
		this.stopwatch.stop();
	}

	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
