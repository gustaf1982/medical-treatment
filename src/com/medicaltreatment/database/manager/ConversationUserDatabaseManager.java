package com.medicaltreatment.database.manager;

import com.medicaltreatment.database.tables.ConversationChatTable;
import com.medicaltreatment.database.tables.ConversationUserTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ConversationUserDatabaseManager extends SQLiteOpenHelper {

	public ConversationUserDatabaseManager(Context context) {
		super(context, ConversationUserTable.DATABASE_NAME, null, ConversationUserTable.DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.w("Create", ConversationUserTable.TABLE_CREATE);
		db.execSQL(ConversationUserTable.TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("Upgrade", "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + ConversationUserTable.TABLE_NAME);
			onCreate(db);
	}

	public Cursor selectUser()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(ConversationUserTable.TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}
	
	public long insertUser(String name, String imguri)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(ConversationUserTable.FIELD_PERSONNAME, name);
		cv.put(ConversationUserTable.FIELD_PHONENUMBER, "");
		cv.put(ConversationUserTable.FIELD_IMGNAMEURI, imguri);
		long row = db.insert(ConversationUserTable.TABLE_NAME, null, cv);
		return row;
	}
	
	public void deletePerson(String personName)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String where = ConversationUserTable.FIELD_PERSONNAME + "=?";
		String[] whereValue = {personName};
		db.delete(ConversationUserTable.TABLE_NAME, where, whereValue);
	}

	public void deleteConversationForPerson(String personName) {
		SQLiteDatabase db = this.getReadableDatabase();
		String where = ConversationUserTable.FIELD_PERSONNAME + "=?";
		String[] whereValue = {personName};
		db.delete(ConversationUserTable.TABLE_NAME, where, whereValue);
		db.delete(ConversationChatTable.TABLE_NAME, where, whereValue);
		
	}
}
