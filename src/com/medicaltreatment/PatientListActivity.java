package com.medicaltreatment;



import java.util.ArrayList;

import com.medicaltreatment.UI.PatientListAdapter;
import com.medicaltreatment.content.PatientInfoContent;
import com.medicaltreatment.database.manager.PatientInfoDatabaseManager;
import com.medicaltreatment.database.tables.PatientInformationTable;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PatientListActivity extends Activity {
	
	//Selected patient name
	private static String strSelectedPatientName = "";
	//ActionBar Command
	private LinearLayout btnback;
	private Button btnFinsh;
	private TextView title;
	private RelativeLayout btngotoplan;	
	private RelativeLayout btngotorecord;
	private RelativeLayout btngotochat;	
	private ImageView patientphoto;
	private TextView name_tv;
	private TextView gender_old_tv;
	private TextView place_care_tv;
	private TextView contact_tv;
	private TextView phone_tv;
	private TextView statue_tv;
	private Bundle receivedata;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_list);
		name_tv = (TextView)findViewById(R.id.name_text);
		gender_old_tv = (TextView)findViewById(R.id.gender_olderlevel);
		place_care_tv = (TextView)findViewById(R.id.place_care);
		contact_tv = (TextView)findViewById(R.id.contact_tv);
		phone_tv = (TextView)findViewById(R.id.phone_tv);
		statue_tv = (TextView)findViewById(R.id.carestate);		
		ReceiveData();	
		setActionBar();
		btngotoplan = (RelativeLayout)findViewById(R.id.btn_goto_plan);
		btngotoplan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent planintent = new Intent(PatientListActivity.this, LogViewActivity.class);
				planintent.putExtra("Data_patient", receivedata);				
				startActivity(planintent);
			}
		});
		btngotorecord = (RelativeLayout)findViewById(R.id.btn_goto_recode);
		btngotorecord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent recordintent = new Intent(PatientListActivity.this, StatusRecordActivity.class);
				recordintent.putExtra("Data_patient", receivedata);				
				startActivity(recordintent);
			}
		});
		btngotochat = (RelativeLayout)findViewById(R.id.btn_goto_chat);
		btngotochat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent chatIntent = new Intent(PatientListActivity.this, ChatActivity.class);
				chatIntent.putExtra("Data_patient", receivedata);
				startActivity(chatIntent);
			}
		});
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
		btnFinsh.setText(getString(R.string.newLog));
		title = (TextView)findViewById(R.id.titletxt);
		title.setText(name);
	}

	private void ReceiveData() {
		// TODO Auto-generated method stub
		Intent receive = getIntent();
		receivedata = receive.getBundleExtra("Data_patient");
		name = receivedata.getString("name");
		String gender = receivedata.getString("gender");
		String oldlevel = receivedata.getString("oldlevel");
		String place = receivedata.getString("place");
		String contact = receivedata.getString("contact");
		String phonenumber = receivedata.getString("phone");
		String statue = receivedata.getString("statue");
		setData(name, gender, oldlevel, place, contact, phonenumber, statue);
	}

	private void setData(String name, String gender, String oldlevel,
			String place, String contact, String phonenumber, String statue) {
		// TODO Auto-generated method stub
		name_tv.setText(name);
		gender_old_tv.setText(gender + "  " + oldlevel);
		place_care_tv.setText(getText(R.string.place) + ": " + place);
		contact_tv.setText(getText(R.string.contact) + ": " + contact);
		phone_tv.setText(getString(R.string.phoneNum) + ": " + phonenumber);		
		statue_tv.setText(statue);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onContextItemSelected(item);
	}

	public final static String getSelectedPatientName() {
		return strSelectedPatientName;
	}
	
	public final static void setPatientName(String name) {
		strSelectedPatientName = name;
	}
	
}
