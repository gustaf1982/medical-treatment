package com.medicaltreatment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.medicaltreatment.UI.ScheduleAdapter;
import com.medicaltreatment.UI.TimeLineAdapter;
import com.medicaltreatment.content.PatientInfoContent;
import com.medicaltreatment.content.SchedulInfoContent;
import com.medicaltreatment.database.manager.StatusRecordDatabaseManager;
import com.medicaltreatment.database.tables.PatientInformationTable;
import com.medicaltreatment.database.tables.StatusInformationTable;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.HitTestResult;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class LogViewActivity extends Activity {
	
	private String name;
	//Bottom Command	
	private ListView logListVw;	
	private String strMonth;
	private LinearLayout btnback;
	private Button btnFinsh;
	private TextView title;
	private ArrayList<SchedulInfoContent> scheduledata;
	private ScheduleAdapter adapter;
	private Bundle receivedata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_view);
		logListVw = (ListView)findViewById(R.id.schedulelist);
		scheduledata = new ArrayList<SchedulInfoContent>();
		getDatafromPrevious();
		setActiveBar();
		adapter = new ScheduleAdapter(LogViewActivity.this, scheduledata);
		logListVw.setAdapter(adapter);
		logListVw.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (scheduledata.get(arg2).existcare) {
					Log.e("Hey", "How are you");
				}
			}
		});
		setAlarmstate();
	}

	private void setAlarmstate() {
		// TODO Auto-generated method stub
		
	}

	private void setscheduledata() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 48; i ++) {
			SchedulInfoContent data = new SchedulInfoContent(i);
			scheduledata.add(data);
		}
		scheduledata.get(5).setScheduleContent("Pls wait for a while.");
	}

	private void setActiveBar() {
		// TODO Auto-generated method stub
		btnback = (LinearLayout)findViewById(R.id.btnleftaction);
		btnback.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		btnFinsh = (Button)findViewById(R.id.btnrightaction);
		
		title = (TextView)findViewById(R.id.titletxt);
		title.setText(getString(R.string.btnPlan) + ": " + name);
	}

	private void getDatafromPrevious() {
		// TODO Auto-generated method stub
		Intent receive = getIntent();
		receivedata = receive.getBundleExtra("Data_patient");
		name = receivedata.getString("name");
		setscheduledata();
	}
}
