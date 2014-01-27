package me.pedrazas.disnums.om;

import java.util.Date;
import java.util.List;

import me.pedrazas.disnums.utils.StopWatch;
import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable{
	
	public Game() {
		super();
		this.date = new Date();
		this.startTime = System.currentTimeMillis();
		this.numRounds = 5;
		this.stopWatch = new StopWatch();
		
	}
	
	private List<Round> rounds;
	private long startTime;
	private long endTime;
	private int numRounds;
	private Date date;
	private int result;
	private String player;
	private StopWatch stopWatch;
	
	
	
	public StopWatch getStopWatch() {
		return stopWatch;
	}

	public void setStopWatch(StopWatch stopWatch) {
		this.stopWatch = stopWatch;
	}

	public void end(){
		this.endTime = System.currentTimeMillis();
		this.stopWatch.stop();
	}
	
	public List<Round> getRounds() {
		return rounds;
	}
	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}
	public long getStartTime() {
		return startTime;
		
	}
	
	public int getNumRounds() {
		return numRounds;
	}
	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
	}
	public long getEndTime() {
		return endTime;
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

}
