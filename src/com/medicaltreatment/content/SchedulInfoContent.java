package com.medicaltreatment.content;

public class SchedulInfoContent {	
	
	public int id;
	public String carecontent;
	public boolean existcare = false;	
	public SchedulInfoContent(int i) {
		// TODO Auto-generated constructor stub
		id = i;
		carecontent = "";
		existcare = false;
	}
	public void setScheduleContent(String content) {
		carecontent = content;
		existcare = true;
	}
}
