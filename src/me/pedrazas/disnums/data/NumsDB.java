package me.pedrazas.disnums.data;

public class NumsDB {

	
	public static final String DATABASE_NAME = "me.pedrazas.disnums.data.debug";
	public static final int DATABASE_VERSION = 1;
	public static final String DEBUG_TABLE_NAME = "DEBUG";
	
	public static final String KEY_ID = "id";
	public static final String KEY_SELECTED_COLOR = "color_selected";
	public static final String KEY_COLOR = "color";
	public static final String KEY_START = "start";
	public static final String KEY_END = "end";
	public static final String KEY_DURATION = "duration";
	public static final String KEY_SUCCESS = "success";
	public static final String KEY_DATE = "date";
	
	
	public static String CREATE_DEBUG_TABLE = "CREATE TABLE " + DEBUG_TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_SELECTED_COLOR + " TEXT,"
            + KEY_COLOR + " TEXT,"
            + KEY_START + " TEXT,"
            + KEY_END + " TEXT,"
            + KEY_DURATION + " TEXT,"
            + KEY_SUCCESS + " INTEGER,"
            + KEY_DATE + " TEXT"

            + ")";
}

