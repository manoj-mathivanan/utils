package com.manoj.marutiservice;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db extends SQLiteOpenHelper {

private static final int DATABASE_VERSION = 1;

private static final String DATABASE_NAME = "maruti";

private static final String TABLE_NAME = "details";

private static final String KEY = "key";
private static final String VALUE = "value";
private static final String ID = "id";

public db(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) {
String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("+ID+" INTEGER PRIMARY KEY,"+ KEY + " TEXT,"+ VALUE + " TEXT"+")";
db.execSQL(CREATE_TABLE);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
onCreate(db);
}

public void addDetail(String key,String value) {
SQLiteDatabase db = this.getWritableDatabase();
ContentValues values = new ContentValues();
values.put(KEY, key);
values.put(VALUE, value);
db.insert(TABLE_NAME, null, values);
db.close();
}

public String getvalue(String key) {
String selectQuery = "SELECT * FROM details where key = \""+key+"\"";
SQLiteDatabase db = this.getWritableDatabase();
Cursor cursor = db.rawQuery(selectQuery, null);
if (cursor.moveToFirst()) {
return cursor.getString(2);
}
else
return "";
}

public String[] getvalues(String key) {
String selectQuery = "SELECT * FROM details where key = \""+key+"\"";
SQLiteDatabase db = this.getWritableDatabase();
Cursor cursor = db.rawQuery(selectQuery, null);
String[] values = new String[cursor.getCount()];
int i=0;
if (cursor.moveToFirst()) {
do {
values[i]=cursor.getString(2);
i++;
} while (cursor.moveToNext());
}
db.close();
return values;
}

public void cleardetails(String key) {
	String selectQuery = "delete from details where key = \"" + key + "\"";
	SQLiteDatabase db = this.getWritableDatabase();
	db.execSQL(selectQuery);
	db.close();
}

}

