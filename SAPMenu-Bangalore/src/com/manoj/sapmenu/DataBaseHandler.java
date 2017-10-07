package com.manoj.sapmenu;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {

private static final int DATABASE_VERSION = 1;

private static final String DATABASE_NAME = "sapmenu";

private static final String TABLE_MENU = "sapmenu";

private static final String ITEM_NAME = "item_name";
private static final String CALORIES = "calories";
private static final String DAY1 = "day1";
private static final String WHEN1 = "when1";
private static final String ID = "id";
private static final String DATE1 = "date1";

public DataBaseHandler(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) {
String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MENU + "("+ID+" INTEGER PRIMARY KEY,"+ ITEM_NAME + " TEXT,"+ CALORIES + " TEXT," + DAY1 + " TEXT," + WHEN1 + " TEXT," + DATE1 + " TEXT)";
db.execSQL(CREATE_CONTACTS_TABLE);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
onCreate(db);
}

public void addItem(Items item) {
SQLiteDatabase db = this.getWritableDatabase();
ContentValues values = new ContentValues();
values.put(ITEM_NAME, item.getItemName());
values.put(CALORIES, item.getCalories());
values.put(WHEN1, item.getWhen());
values.put(DAY1, item.getDay());
values.put(DATE1, item.getDate());
System.out.println("EXCEL" + " " + item.getItemName() + " " + item.getCalories() + " " + item.getWhen() + " " + item.getDay() + " " + item.getDate());
db.insert(TABLE_MENU, null, values);
db.close();
}

public ArrayList<Items> getAllItems(String day, String when) {
ArrayList<Items> itemlist = new ArrayList<Items>();
String selectQuery = "SELECT * FROM sapmenu where when1 = \""+when+"\" and day1 = \""+day+"\"";
SQLiteDatabase db = this.getWritableDatabase();
Cursor cursor = db.rawQuery(selectQuery, null);
if (cursor.moveToFirst()) {
do {
Items item = new Items(cursor.getString(1),cursor.getString(2),day,when,cursor.getString(5));
itemlist.add(item);
//System.out.println("DB" + " " +cursor.getInt(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5));
} while (cursor.moveToNext());
}
db.close();
return itemlist;
}

public void droptables() {
	// TODO Auto-generated method stub
	//String selectQuery = "update contacts set spoke=spoke+1 and time = '"+System.currentTimeMillis()+"' where number ='"+number+"'";
	SQLiteDatabase db = this.getWritableDatabase();
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
	onCreate(db);
	db.close();
}

}

