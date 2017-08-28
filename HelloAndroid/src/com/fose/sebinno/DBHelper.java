/**
 * 
 */
package com.fose.sebinno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Ning WANG
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	
	private Context mContext;
	
	//private final static String DATABASE_NAME = "sebproject.db"; 
	//private final static int DATABASE_VERSION = 1; 
	
	public DBHelper(Context context) {
		super(context, AppConfig.DB_NAME, null, AppConfig.DB_VERSION);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//String sql = "CREATE TABLE locations (locationID int(11) NOT NULL, x float NOT NULL, y float NOT NULL, hallway varchar(50) DEFAULT NULL, tags varchar(256) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		//db.execSQL(sql);
		executeAssetsSQL(db, "sebprojectdb.sql");
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//String sql = "DROP TABLE IF EXISTS locations"; 
		//db.execSQL(sql); 
		//onCreate(db);
		if (newVersion <= oldVersion) {  
            return;  
        }  
        AppConfig.oldVersion = oldVersion;  
  
        int changeCnt = newVersion - oldVersion;  
        for (int i = 0; i < changeCnt; i++) {  
            // 依次执行updatei_i+1文件      由1更新到2 [1-2]，2更新到3 [2-3]  
            String schemaName = "update" + (oldVersion + i) + "_"  
                    + (oldVersion + i + 1) + ".sql";  
            executeAssetsSQL(db, schemaName);  
        }
	}
	
	
    private void executeAssetsSQL(SQLiteDatabase db, String schemaName) {  
        BufferedReader in = null;  
        try {  
            in = new BufferedReader(new InputStreamReader(mContext.getAssets()  
                    .open(AppConfig.DB_PATH + "/" + schemaName)));  
              
            //System.out.println("Path: "+AppConfig.DB_PATH + "/" + schemaName);  
            String line;  
            String buffer = "";  
            while ((line = in.readLine()) != null) {  
                buffer += line;  
                if (line.trim().endsWith(";")) { 
                	//System.out.println("buffer: " + buffer);
                    db.execSQL(buffer.replace(";", ""));  
                    buffer = "";  
                }  
            }
            /*
            String[] s = buffer.split(";");  
            for (int i = 0; i < s.length; i++) {  
                if (s[i] != "") {  
                    db.execSQL(s[i]);  
                }
            }*/
        } catch (IOException e) {  
            Log.e("db-error", e.toString());  
        } finally {  
            try {  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                Log.e("db-error", e.toString());  
            }  
        }  
    }
    
    
    public Cursor select(String sql) { 
    	//String[] args={Integer.toString(locationID)};
   
    	SQLiteDatabase db = this.getReadableDatabase(); 
    	Cursor cursor = db.rawQuery(sql, null);
    	//Cursor cursor = db.rawQuery("SELECT * FROM locations WHERE locationID=?", args);
    	return cursor;
    }
	/*
	public long insert(String bookname,String author) 
	{ 
		SQLiteDatabase db = this.getWritableDatabase(); 
		// ContentValues 
		ContentValues cv = new ContentValues(); 
		cv.put(BOOK_NAME, bookname); 
		cv.put(BOOK_AUTHOR, author); 
		long row = db.insert(TABLE_NAME, null, cv); 
		return row; 
	} */

}
