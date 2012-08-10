package com.android.apps.emenu.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	private static final int DATABASE_VERSION = 1;

	protected static final String DATABASE_NAME = "emenu.db";

	@Override
	public void onCreate(SQLiteDatabase db) {
		createItemTable(db);
		createCategoryTable(db);
		createCustomerTable(db);
		createOrderTable(db);
		createOrderHistoryTable(db);
		
	}

	private void createOrderHistoryTable(SQLiteDatabase db) {
		db.execSQL(
                "CREATE TABLE " 
                    + OrderHistory.TABLE_NAME
                    + " ("
                    + OrderHistory._ID + " INTEGER PRIMARY KEY,"
                    + OrderHistory.COL_CUSTOMER_ID + " INTEGER NOT NULL,"
                     + OrderHistory.COL_FEEDBACK + " TEXT PRIMARY KEY,"
                    + OrderHistory.COL_CREATED_DATE + " TEXT NOT NULL"
                + ");");
	}

	private void createOrderTable(SQLiteDatabase db) {
		db.execSQL(
                "CREATE TABLE " 
                    + Order.TABLE_NAME
                    + " ("
                    + Order._ID + " INTEGER PRIMARY KEY,"
                    + Order.COL_NAME + " TEXT NOT NULL,"
                    + Order.COL_ITEM_ID + " INTEGER PRIMARY KEY,"
                    + Order.COL_QUANTITY + " INTEGER NOT NULL"
                + ");");
	}

	private void createCustomerTable(SQLiteDatabase db) {
		db.execSQL(
                "CREATE TABLE " 
                    + Customer.TABLE_NAME
                    + " ("
                    + Customer._ID + " INTEGER PRIMARY KEY,"
                    + Customer.COL_NAME + " TEXT NOT NULL"
                + ");");
	}

	private void createCategoryTable(SQLiteDatabase db) {
		
		db.execSQL(
                "CREATE TABLE " 
                    + Category.TABLE_NAME
                    + " ("
                    + Category._ID + " INTEGER PRIMARY KEY,"
                    + Category.COL_NAME + " TEXT NOT NULL,"
                    + Category.COL_CATEGORY_DESCRIPTION + " TEXT NOT NULL,"
                    + Category.COL_CATEGORY_IMAGE + " BLOB NOT NULL,"
                    + Category.COL_AVAILABILTY + " INTEGER NOT NULL,"
                    + Category.COL_CREATED_DATE + " TEXT NOT NULL,"
                    + Category.COL_MODIFIED_DATE + " TEXT NOT NULL"
                + ");");
		
	}

	private void createItemTable(SQLiteDatabase db) {
		db.execSQL(
                "CREATE TABLE " 
                    + Item.TABLE_NAME
                    + " ("
                    + Item._ID + " INTEGER PRIMARY KEY,"
                    + Item.COL_NAME + " TEXT NOT NULL,"
                    + Item.COL_CATEGORY_ID + " INTEGER NOT NULL,"
                    + Item.COL_ITEM_IMAGE + " BLOB NOT NULL,"
                    + Item.COL_ITEM_DESCRIPTION + " INTEGER NOT NULL "
                    + Item.COL_AVAILABILTY + " INTEGER NOT NULL,"
                    + Item.COL_PRICE + " TEXT NOT NULL,"
                    + Item.COL_CREATED_DATE + " TEXT NOT NULL,"
                    + Item.COL_MODIFIED_DATE + " TEXT NOT NULL"
                + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < DATABASE_VERSION) {
			db.execSQL("DROP TABLE IF EXISTS " + Item.TABLE_NAME);
			onCreate(db);
		}
	}

}
