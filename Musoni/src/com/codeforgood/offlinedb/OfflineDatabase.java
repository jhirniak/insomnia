package com.codeforgood.offlinedb;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class OfflineDatabase implements OfflineDatabaseInterface
{
	private static final String[] tables = {"CLIENT", "LOAN", "GROUP", "ACCOUNTING",
		"ORG", "USER", "SYSTEM", "NON-CORE", "REPORT", "TEMPLATE"};
		
	private boolean online = false;
	private SQLiteDatabase database = null;
	//private MySQLiteHelper dbHelper;
	
	public OfflineDatabase()
	{ sync(); }
	
	public void sync()
	{
		if (!online)
		{ return; }
		
		
	}
	
	public void wipeout()
	{
//		database.deleteDatabase(file);
		database = null;
	}
}
