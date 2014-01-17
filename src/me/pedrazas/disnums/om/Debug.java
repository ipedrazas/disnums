package me.pedrazas.disnums.om;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import me.pedrazas.disnums.data.NumsDB;
import android.annotation.SuppressLint;
import android.content.ContentValues;

public class Debug {
	
	private String color;
	private int id;
	private String selectedColor;
	private String start;
	private String end;
	private String duration;
	private int success;
	private String date;
	
	@SuppressLint("SimpleDateFormat")
	public void setDate(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();        
		this.date = df.format(today);
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSelectedColor() {
		return selectedColor;
	}
	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public void setStart(long start) {
		this.start = Long.toString(start);
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public void setEnd(long end) {
		this.end = Long.toString(end);
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = Float.toString(duration);
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public void setSuccess(boolean success) {
		if(success){
			this.success = 1;
		}else{
			this.success = 0;
		}
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public ContentValues getContentValues(){
		ContentValues values = new ContentValues();
		
		values.put(NumsDB.KEY_COLOR, this.getColor());
		values.put(NumsDB.KEY_DATE, this.getDate());
		values.put(NumsDB.KEY_DURATION, this.getDuration());
		values.put(NumsDB.KEY_END, this.getEnd());
		values.put(NumsDB.KEY_SELECTED_COLOR, this.getSelectedColor());
		values.put(NumsDB.KEY_START, this.getStart());
		values.put(NumsDB.KEY_SUCCESS, this.getSuccess());
		
		return values;
	}
	
	public String toString(){
		return this.id + ", " + this.date + ", " + this.start + ", " + this.end + ", " + this.duration + ", " + this.color + ", " + this.selectedColor + ", " + this.success;
	}
	
	public String toJson(){
		return "{id:" + this.id + ", date:" + this.date + ", start:" + this.start + ", end:" + this.end + ", duration:" + this.duration + ", color:" + this.color + ", selectedColor:" + this.selectedColor + ", success: " + this.success;
	}

}
