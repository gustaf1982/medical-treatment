package com.medicaltreatment.database.tables;

public interface ConversationChatTable {
	//Database Name & Table
	public static final String DATABASE_NAME = "MedicalTreatment";
	public static final String TABLE_NAME = "conversationchat";
	public static final int DATABASE_VERSION = 1;
	
	//Field Name
	public static final String FIELD_ID = "_id";
	public static final String FIELD_PERSONNAME = "name";
	public static final String FIELD_TIME = "time";
	public static final String FIELD_DIRECTION = "direction";
	public static final String FIELD_CONTENT = "content";
	
	public static final String TABLE_CREATE =
			  "CREATE TABLE if not exists " + TABLE_NAME + " (" +
			  FIELD_ID + " integer PRIMARY KEY autoincrement," +
			  FIELD_PERSONNAME + " text, " +
			  FIELD_TIME + " text, " +
			  FIELD_DIRECTION + " integer, " +
			  FIELD_CONTENT + " text)";

}
