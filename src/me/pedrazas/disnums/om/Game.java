package me.pedrazas.disnums.om;

import java.util.Date;
import java.util.List;

public class Game {
	
	public Game() {
		super();
		this.date = new Date();
		this.startTime = System.currentTimeMillis();
		this.numRounds = 5;
	}
	
	private List<Round> rounds;
	private long startTime;
	private long endTime;
	private int numRounds;
	private Date date;
	private int result;
	private String player;
	
	public void end(){
		this.endTime = System.currentTimeMillis();
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

}
