package com.medicaltreatment.database.tables;

public interface StatusInformationTable {
	//Database Name & Table
	public static final String DATABASE_NAME = "MedicalTreatment";
	public static final String TABLE_NAME = "statusinfo";
	public static final int DATABASE_VERSION = 1;
	
	//Field Name
	public static final String FIELD_ID = "_id";
	public static final String FIELD_PERSONNAME = "name";
	public static final String FIELD_RECORDMONTH = "month";
	public static final String FIELD_RECORDDAY = "day";
	public static final String FIELD_RECORDTIME = "time";
	public static final String FIELD_HIGHPRESSURE = "highpressure";
	public static final String FIELD_LOWPRESSURE = "lowpressure";
	public static final String FIELD_PLUSATION = "plusation";
	public static final String FIELD_TEMPERATURE = "temperature";
	public static final String FIELD_INTHINGNAME = "inthingname";
	public static final String FIELD_INTHINGAMOUNT = "inthingamount";
	public static final String FIELD_OUTTHINGNAME = "outthingname";
	public static final String FIELD_OUTTHINGAMOUNT = "outthingamount";
	public static final String FIELD_OPINION = "opinion";
	
	
	public static final String TABLE_CREATE =
			  "CREATE TABLE if not exists " + TABLE_NAME + " (" +
			  FIELD_ID + " integer PRIMARY KEY autoincrement," +
			  FIELD_PERSONNAME + " text, " +
			  FIELD_RECORDMONTH + " text, " +
			  FIELD_RECORDDAY + " text, " +
			  FIELD_RECORDTIME + " text, " +
			  FIELD_HIGHPRESSURE + " text, " +
			  FIELD_LOWPRESSURE + " text, " +
			  FIELD_PLUSATION + " text, " +
			  FIELD_TEMPERATURE + " text, " +
			  FIELD_INTHINGNAME + " text, " +
			  FIELD_INTHINGAMOUNT + " text, " +
			  FIELD_OUTTHINGNAME + " text, " +
			  FIELD_OUTTHINGAMOUNT + " text, " +
			  FIELD_OPINION + " text)";
}

