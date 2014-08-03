package com.medicaltreatment.database.manager;


import com.medicaltreatment.database.tables.ConversationChatTable;
import com.medicaltreatment.database.tables.ConversationUserTable;
import com.medicaltreatment.database.tables.NursePlanInformationTable;
import com.medicaltreatment.database.tables.PatientInformationTable;
import com.medicaltreatment.database.tables.StatusInformationTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PatientInfoDatabaseManager extends SQLiteOpenHelper{
	
	
	
	public PatientInfoDatabaseManager(Context context)
	{
		super(context, PatientInformationTable.DATABASE_NAME, null, PatientInformationTable.DATABASE_VERSION);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.w("Create", PatientInformationTable.TABLE_CREATE);
		db.execSQL(PatientInformationTable.TABLE_CREATE);
		//modify later
		Log.w("Create", NursePlanInformationTable.TABLE_CREATE);
		db.execSQL(NursePlanInformationTable.TABLE_CREATE);
		Log.w("Create", StatusInformationTable.TABLE_CREATE);
		db.execSQL(StatusInformationTable.TABLE_CREATE);
		Log.w("Create", ConversationUserTable.TABLE_CREATE);
		db.execSQL(ConversationUserTable.TABLE_CREATE);
		Log.w("Create", ConversationChatTable.TABLE_CREATE);
		db.execSQL(ConversationChatTable.TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("Upgrade", "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + PatientInformationTable.TABLE_NAME);
			onCreate(db);
	}
	

	public Cursor selectPerson()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(PatientInformationTable.TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}
	
	public boolean isExistPerson(String patientName) 
	{

		SQLiteDatabase db = this.getReadableDatabase();
		String where = NursePlanInformationTable.FIELD_PERSONNAME + "=?";
		Cursor cursor = db.query(PatientInformationTable.TABLE_NAME, null, where, new String[]{patientName}, null, null, null);
		if(cursor.getCount() > 0)
			return true;
		return false;
	}
	public long insertPerson(String name, String sex, String old, String place, String contact, String phonenum, String status)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(PatientInformationTable.FIELD_PERSONNAME, name);
		cv.put(PatientInformationTable.FIELD_SEX, sex);
		cv.put(PatientInformationTable.FIELD_OLD, old);
		cv.put(PatientInformationTable.FIELD_PLACE, place);
		cv.put(PatientInformationTable.FIELD_CONTACT, contact);
		cv.put(PatientInformationTable.FIELD_PHONENUM, phonenum);
		cv.put(PatientInformationTable.FIELD_STATUS, status);
		long row = db.insert(PatientInformationTable.TABLE_NAME, null, cv);
		return row;
	}
	
	public void deletePerson(String personName)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String where = PatientInformationTable.FIELD_PERSONNAME + "=?";
		String[] whereValue = {personName};
		db.delete(PatientInformationTable.TABLE_NAME, where, whereValue);
	}

	public void deleteAllContentPatient(String personName) {
		SQLiteDatabase db = this.getReadableDatabase();
		String where = PatientInformationTable.FIELD_PERSONNAME + "=?";
		String[] whereValue = {personName};
		db.delete(PatientInformationTable.TABLE_NAME, where, whereValue);
		db.delete(NursePlanInformationTable.TABLE_NAME, where, whereValue);
		db.delete(StatusInformationTable.TABLE_NAME, where, whereValue);
		db.delete(ConversationChatTable.TABLE_NAME, where, whereValue);
		db.delete(ConversationUserTable.TABLE_NAME, where, whereValue);
		
	}
}
