package me.pedrazas.disnums.data;

import java.util.ArrayList;
import java.util.List;

import me.pedrazas.disnums.om.Debug;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class DebugDataSource {

	
	 // Database fields
	  private SQLiteDatabase database;
	  private DbHelper dbHelper;
	  
	public DebugDataSource(Context context) {
		super();
		dbHelper = new DbHelper(context);
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public void addDebugEntry(Debug debug){	 
		  this.open();
		  database.insert(NumsDB.DEBUG_TABLE_NAME, null, debug.getContentValues());     	
	}
	  
	  public List<Debug> getAllDebugEntries(){		  
			this.open();			  
			String selectQuery = "SELECT  * FROM " + NumsDB.DEBUG_TABLE_NAME;
			List<Debug> debugs = new ArrayList<Debug>();
			Cursor cursor = null;
			try {
				cursor = database.rawQuery(selectQuery, null);
				cursor.moveToFirst();
				for (boolean hasItem = cursor.moveToFirst(); hasItem; hasItem = cursor.moveToNext()) {
					debugs.add(this.getDebugFromCursor(cursor));
				}
			} finally {
				if(cursor != null){
					cursor.close();
				}
				this.close();
			}
			return debugs;
	 	}
	     
	     private Debug getDebugFromCursor(Cursor cursor){
	    	 Debug d = new Debug();
	    	 d.setColor(cursor.getString(cursor.getColumnIndex(NumsDB.KEY_COLOR)));
	    	 d.setDate(cursor.getString(cursor.getColumnIndex(NumsDB.KEY_DATE)));
	    	 d.setDuration(cursor.getString(cursor.getColumnIndex(NumsDB.KEY_DURATION)));
	    	 d.setEnd(cursor.getString(cursor.getColumnIndex(NumsDB.KEY_END)));
	    	 d.setId(cursor.getInt(cursor.getColumnIndex(NumsDB.KEY_ID)));
	    	 d.setSelectedColor(cursor.getString(cursor.getColumnIndex(NumsDB.KEY_SELECTED_COLOR)));
	    	 d.setStart(cursor.getString(cursor.getColumnIndex(NumsDB.KEY_START)));
	    	 d.setSuccess(cursor.getInt(cursor.getColumnIndex(NumsDB.KEY_SUCCESS)));
	    	 return d;
	     }
	  
}
