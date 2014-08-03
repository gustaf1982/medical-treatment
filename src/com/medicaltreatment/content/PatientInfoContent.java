package com.medicaltreatment.content;

public class PatientInfoContent {
	public int id;
	public String name;
	public String gender;
	public String oldlevel;
	public String places;
	public String contacts;
	public String phonenumber;
	public String finish_state;
	
	public PatientInfoContent(int i, String n, String g, String o, String p, String c, String ph, String f) {
		// TODO Auto-generated constructor stub
		id = i;
		name = n;
		gender = g;
		oldlevel = o;
		places = p;
		contacts = c;
		phonenumber = ph;
		finish_state = f;
	}
}
