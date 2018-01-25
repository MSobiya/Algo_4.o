package com.example.registeralgo;

import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

public class DBAdapter {
	public static final String KEY_GNO="gno";
	public static final String KEY_NAME="name";
	public static final String KEY_RNO="rno";
	public static final String KEY_CLG="clg";
	public static final String KEY_YEAR="year";
	public static final String KEY_CON="con";
	public static final String KEY_EMAIL="email";
	public static final String KEY_AMNTP="amnt_p";
	public static final String KEY_AMNTR="amnt_r";
	public static final String KEY_MEM="mem";

	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME="AlgoRegister";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "algorithm";
    private static final String TABLE_CREATE = "create table algorithm(gno text not null,name text not null,rno text not null,clg text not null,year text not null,con text not null,email text not null,amnt_p integer not null,amnt_r integer not null,mem integer not null );";
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    private final Context context;


    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
	
    private static class DatabaseHelper extends SQLiteOpenHelper
    {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			 try {
	                db.execSQL(TABLE_CREATE);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			 Log.w(TAG, "Upgrading database from version " + oldVersion + " to "+ newVersion + ", which will destroy all old data");
	            db.execSQL("DROP TABLE IF EXISTS algorithm");
	            onCreate(db);
		}
    }
		
		//---opens the database---
	    public DBAdapter open() throws SQLException
	    {
	    db=DBHelper.getWritableDatabase();
	       return this;
	    }
	    
	  //---closes the database---
	    public void close()
	    {
	        DBHelper.close();
	    }

	  //---insert a contact into the database---
	    public long insertEntry(String Gno, String name,String Rno,String Clg,String Year,String Con,String Email,int Amnt_p,int Amnt_r,int Mem)
	    {
	        ContentValues value = new ContentValues();
	        value.put(KEY_GNO, Gno);
	        value.put(KEY_NAME, name);
	        value.put(KEY_RNO, Rno);
	        value.put(KEY_CLG, Clg);
	        value.put(KEY_YEAR, Year);
	        value.put(KEY_CON, Con);
	        value.put(KEY_EMAIL, Email);
	        value.put(KEY_AMNTP, Amnt_p);
	        value.put(KEY_AMNTR, Amnt_r);
	        value.put(KEY_MEM, Mem);
	        return db.insert(DATABASE_TABLE, null, value);
	    }
	    
	  //---retrieves all the contacts---
	    public Cursor getAllContacts()
	    {
	        return db.query(DATABASE_TABLE, new String[] {KEY_GNO, KEY_NAME,KEY_RNO,KEY_CLG,KEY_YEAR,KEY_CON,KEY_EMAIL,KEY_AMNTP,KEY_AMNTR,KEY_MEM},null, null, null, null, null);
	    }
	    
}
