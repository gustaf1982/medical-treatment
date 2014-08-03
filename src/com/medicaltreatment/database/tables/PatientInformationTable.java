package com.medicaltreatment.database.tables;

public interface PatientInformationTable {
	//Database Name & Table
	public static final String DATABASE_NAME = "MedicalTreatment";
	public static final String TABLE_NAME = "patient";
	public static final int DATABASE_VERSION = 1;
	
	//Field Name
	public static final String FIELD_ID = "_id";
	public static final String FIELD_PERSONNAME = "name";
	public static final String FIELD_SEX = "sex";
	public static final String FIELD_OLD = "old";
	public static final String FIELD_PLACE = "place";
	public static final String FIELD_CONTACT = "contact";
	public static final String FIELD_PHONENUM = "phonenum";
	public static final String FIELD_STATUS = "status";
	
	
	public static final String TABLE_CREATE =
			  "CREATE TABLE if not exists " + TABLE_NAME + " (" +
			  FIELD_ID + " integer PRIMARY KEY autoincrement," +
			  FIELD_PERSONNAME + " text, " +
			  FIELD_SEX + " text, " +
			  FIELD_OLD + " text, " +
			  FIELD_PLACE + " text, " +
			  FIELD_CONTACT + " text, " +
			  FIELD_PHONENUM + " text, " +
			  FIELD_STATUS + " text)";
}
