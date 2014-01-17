package me.pedrazas.disnums.data;

import java.util.ArrayList;
import java.util.List;

import me.pedrazas.disnums.om.Debug;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{

	 Context helperContext;
	 private static DbHelper singleton=null;

	 DbHelper(Context context) {
         super(context, NumsDB.DATABASE_NAME, null, NumsDB.DATABASE_VERSION);
         helperContext = context;
     }
	 
	 public synchronized static DbHelper getInstance(Context ctxt) {
			if(ctxt != null){
				if (singleton == null) {
					singleton=new DbHelper(ctxt.getApplicationContext());
				}
				return(singleton);
			}
			return null;
		}

     @Override
     public void onCreate(SQLiteDatabase db) {
//     	db.beginTransaction();
		db.execSQL(NumsDB.CREATE_DEBUG_TABLE);			
//		db.setTransactionSuccessful();
     }

     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         Log.w("Circles", "Upgrading database!!!!!");
         //db.execSQL("");
         onCreate(db);
     }

    
     
     
}
