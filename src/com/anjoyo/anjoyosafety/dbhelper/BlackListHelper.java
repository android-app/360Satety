package com.anjoyo.anjoyosafety.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BlackListHelper extends SQLiteOpenHelper{

	public static final String DB_NAME = "blacklist.db";
	public static final int VERSION = 1;
	public static final String TABLE_NAME = "blacknum";
	public static final String _ID = "_id";
	public static final String PHOTONUM = "photonum";
	
	
	public BlackListHelper(Context context) {
		super(context,DB_NAME,null,VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE " + TABLE_NAME + "(" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + PHOTONUM
				+ " TEXT NOT NULL)"
				);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}

}
