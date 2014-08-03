package com.medicaltreatment.database.manager;

import com.medicaltreatment.content.StatusDataContent;
import com.medicaltreatment.database.tables.StatusInformationTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StatusRecordDatabaseManager extends SQLiteOpenHelper {

	public StatusRecordDatabaseManager(Context context) {
		// TODO Auto-generated constructor stub
		super(context, StatusInformationTable.DATABASE_NAME, null, StatusInformationTable.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.w("Create", StatusInformationTable.TABLE_CREATE);
		db.execSQL(StatusInformationTable.TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w("Upgrade", "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + StatusInformationTable.TABLE_NAME);
		onCreate(db);
	}
	

	public Cursor selectStatusForPatient(String patientName, String currentMonth)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String where = StatusInformationTable.FIELD_PERSONNAME + "=? and " + StatusInformationTable.FIELD_RECORDMONTH + "=?";
		Cursor cursor = db.query(StatusInformationTable.TABLE_NAME, null, where, new String[]{patientName, currentMonth}, null, null, null);
		return cursor;
	}
	
	public long insertStatus(StatusDataContent statusRecord)
	{
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		
		cv.put(StatusInformationTable.FIELD_PERSONNAME, statusRecord.strName);
		cv.put(StatusInformationTable.FIELD_RECORDMONTH, statusRecord.strMonth);
		cv.put(StatusInformationTable.FIELD_RECORDDAY, statusRecord.strDay);
		cv.put(StatusInformationTable.FIELD_RECORDTIME, statusRecord.strTime);
		cv.put(StatusInformationTable.FIELD_HIGHPRESSURE, statusRecord.strHighPressure);
		cv.put(StatusInformationTable.FIELD_LOWPRESSURE, statusRecord.strLowPressure);
		cv.put(StatusInformationTable.FIELD_PLUSATION, statusRecord.strPlusation);
		cv.put(StatusInformationTable.FIELD_TEMPERATURE, statusRecord.strTemperature);
		cv.put(StatusInformationTable.FIELD_INTHINGNAME, statusRecord.strInThingName);
		cv.put(StatusInformationTable.FIELD_INTHINGAMOUNT, statusRecord.strInThingAmount);
		cv.put(StatusInformationTable.FIELD_OUTTHINGNAME, statusRecord.strOutThingName);
		cv.put(StatusInformationTable.FIELD_OUTTHINGAMOUNT, statusRecord.strOutThingAmount);
		cv.put(StatusInformationTable.FIELD_OPINION, statusRecord.strOpinion);
		
		long row = db.insert(StatusInformationTable.TABLE_NAME, null, cv);
		
		return row;
	}
	
	public void deletePerson(String personName)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String where = StatusInformationTable.FIELD_PERSONNAME + "=?";
		String[] whereValue = {personName };
		db.delete(StatusInformationTable.TABLE_NAME, where, whereValue);
	}

}
