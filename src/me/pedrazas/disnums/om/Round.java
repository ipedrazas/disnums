package me.pedrazas.disnums.om;

public class Round {
	
	private long startTime;
	private long endTime;
	private int points;
	
	public Round() {
		super();
		this.startTime = System.currentTimeMillis();
	}
	public long getStartTime() {
		return startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void end() {
		this.endTime = System.currentTimeMillis();
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
