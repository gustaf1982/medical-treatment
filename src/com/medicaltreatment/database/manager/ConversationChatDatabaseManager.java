package com.medicaltreatment.database.manager;

import com.medicaltreatment.database.tables.ConversationChatTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ConversationChatDatabaseManager extends SQLiteOpenHelper {

	public ConversationChatDatabaseManager(Context context) {
		super(context, ConversationChatTable.DATABASE_NAME, null, ConversationChatTable.DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.w("Create", ConversationChatTable.TABLE_CREATE);
		db.execSQL(ConversationChatTable.TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("Upgrade", "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + ConversationChatTable.TABLE_NAME);
			onCreate(db);
	}

	public Cursor selectContentForUser(String username)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String where = ConversationChatTable.FIELD_PERSONNAME + "=?";
		Cursor cursor = db.query(ConversationChatTable.TABLE_NAME, null, where, new String[]{username}, null, null, null);
		return cursor;
	}
	
	public long insertChatContent(String name, String time, int direction, String content)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(ConversationChatTable.FIELD_PERSONNAME, name);
		cv.put(ConversationChatTable.FIELD_TIME, time);
		cv.put(ConversationChatTable.FIELD_DIRECTION, direction);
		cv.put(ConversationChatTable.FIELD_CONTENT, content);
		long row = db.insert(ConversationChatTable.TABLE_NAME, null, cv);
		return row;
	}
	
	public void deletePerson(String personName)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String where = ConversationChatTable.FIELD_PERSONNAME + "=?";
		String[] whereValue = {personName};
		db.delete(ConversationChatTable.TABLE_NAME, where, whereValue);
	}


}
