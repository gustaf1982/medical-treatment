package com.medicaltreatment.content;

public class ChatDataContent {
	public int direction;
	public String dateAndtime;
	public String name;
	public String chattext;
	
	public static final int OUTGOING_MSG = 1;
	public static final int INCOMING_MSG = 2;
	
	public ChatDataContent(int direct, String username, String time, String chat) {
		// TODO Auto-generated constructor stub
		direction = direct;
		dateAndtime = time;
		name = username;
		chattext = chat;
	}
}
