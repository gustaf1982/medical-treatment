package com.medicaltreatment;

import java.util.ArrayList;

import com.medicaltreatment.UI.DateUtil;
import com.medicaltreatment.UI.NursePlanListAdapter;
import com.medicaltreatment.UI.PatientListAdapter;
import com.medicaltreatment.content.NursePlanDataContent;
import com.medicaltreatment.content.PatientInfoContent;
import com.medicaltreatment.database.manager.NursePlanDatabaseManager;
import com.medicaltreatment.database.manager.PatientInfoDatabaseManager;
import com.medicaltreatment.database.tables.NursePlanInformationTable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class NursePlanActivity extends Activity {

	//ActoinBar Command
	private LinearLayout btnback;
	private Button btnFinsh;
	private TextView title;
	private Bundle receivedata;
	private String name;
	private LayoutInflater inflater;
	private LinearLayout background;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nurse_plan);
		inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		background = (LinearLayout)findViewById(R.id.content_recorded_data);
		getDatafromPrevious();
		setActionBar();
	}
	
	private void getDatafromPrevious() {
		// TODO Auto-generated method stub
		Intent receive = getIntent();
		receivedata = receive.getBundleExtra("Data_patient");
		name = receivedata.getString("name");
		String contentrecord = receive.getStringExtra("sendingrecord");
		View vi = inflater.inflate(R.layout.nurse_plan_list_row, null);
		TextView record_date = (TextView)vi.findViewById(R.id.record_date);
		record_date.setText(DateUtil.getCurrentDate());
		TextView record_day = (TextView)vi.findViewById(R.id.record_day);
		record_day.setText(DateUtil.getCurrentDay());
		TextView content = (TextView)vi.findViewById(R.id.reg_content);
		content.setText(contentrecord);
		background.addView(vi);
	}

	private void setActionBar() {
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
		btnFinsh.setText(getString(R.string.logReg));
	
		title = (TextView)findViewById(R.id.titletxt);
		title.setText(getString(R.string.btnRecord) + ": " + name);
	}
	
}
