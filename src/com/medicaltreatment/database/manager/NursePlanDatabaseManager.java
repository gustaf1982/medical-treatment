package com.medicaltreatment.database.manager;

import com.medicaltreatment.database.tables.NursePlanInformationTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NursePlanDatabaseManager extends SQLiteOpenHelper {

	public NursePlanDatabaseManager(Context context) {
		super(context, NursePlanInformationTable.DATABASE_NAME, null, NursePlanInformationTable.DATABASE_VERSION);
	}
	
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.w("Create", NursePlanInformationTable.TABLE_CREATE);
		db.execSQL(NursePlanInformationTable.TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("Upgrade", "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + NursePlanInformationTable.TABLE_NAME);
			onCreate(db);
	}

	public Cursor selectPlanForPatient(String patientName)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String where = NursePlanInformationTable.FIELD_PERSONNAME + "=?";
		Cursor cursor = db.query(NursePlanInformationTable.TABLE_NAME, null, where, new String[]{patientName}, null, null, null);
		return cursor;
	}
	
	public long insertPlan(String name, String time, String content, String status)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(NursePlanInformationTable.FIELD_PERSONNAME, name);
		cv.put(NursePlanInformationTable.FIELD_TIME, time);
		cv.put(NursePlanInformationTable.FIELD_CONTENT, content);
		cv.put(NursePlanInformationTable.FIELD_STATUS, status);
		long row = db.insert(NursePlanInformationTable.TABLE_NAME, null, cv);
		return row;
	}
	
	public void deletePerson(String personName)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String where = NursePlanInformationTable.FIELD_PERSONNAME + "=?";
		String[] whereValue = {personName};
		db.delete(NursePlanInformationTable.TABLE_NAME, where, whereValue);
	}


}
