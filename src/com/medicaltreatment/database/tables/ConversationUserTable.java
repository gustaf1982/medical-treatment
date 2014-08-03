package com.medicaltreatment.database.tables;

public interface ConversationUserTable {
	//Database Name & Table
	public static final String DATABASE_NAME = "MedicalTreatment";
	public static final String TABLE_NAME = "conversationuser";
	public static final int DATABASE_VERSION = 1;
	
	//Field Name
	public static final String FIELD_ID = "_id";
	public static final String FIELD_PERSONNAME = "name";
	public static final String FIELD_PHONENUMBER = "phonenumber";
	public static final String FIELD_IMGNAMEURI = "imguri";
	
	public static final String TABLE_CREATE =
			  "CREATE TABLE if not exists " + TABLE_NAME + " (" +
			  FIELD_ID + " integer PRIMARY KEY autoincrement," +
			  FIELD_PERSONNAME + " text, " +
			  FIELD_PHONENUMBER + " text, " +
			  FIELD_IMGNAMEURI + " text)";
}
