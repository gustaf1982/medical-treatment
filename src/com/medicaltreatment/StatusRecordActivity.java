package com.medicaltreatment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.medicaltreatment.UI.DateUtil;
import com.medicaltreatment.content.StatusDataContent;
import com.medicaltreatment.database.manager.PatientInfoDatabaseManager;
import com.medicaltreatment.database.manager.StatusRecordDatabaseManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StatusRecordActivity extends Activity {

	private LinearLayout btnback;
	private Button btnFinsh;
	private TextView title;
	private Bundle receivedata;	
	private ImageView btn_bloodup;
	private ImageView btn_heartbeat;
	private ImageView btn_bodytemp;
	private ImageView btn_takephoto;
	private ImageView btn_content_rec;
	private EditText text_bloodup;
	private EditText text_heatbeat;
	private EditText text_bodytemp;
	private EditText text_takephoto;
	private EditText text_contentrec;
	private InputMethodManager mgr1;
	private InputMethodManager mgr2;
	private InputMethodManager mgr3;
	private InputMethodManager mgr4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status_record);	
		getDatafromPrevious();
		setActiveBar();
		mgr1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);		
		text_bloodup = (EditText)findViewById(R.id.bloodup_text);
		text_bloodup.setText("");
		text_bloodup.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (!arg1) {					
					text_bloodup.setText(DateUtil.getBloodUpType(text_bloodup.getText().toString()));
					mgr1.hideSoftInputFromWindow(text_bloodup.getWindowToken(), 0);
				} else {
					text_bloodup.setText("");
				}
			}
		});
		text_heatbeat = (EditText)findViewById(R.id.heartbeat_text);
		text_heatbeat.setText("");
		text_heatbeat.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (!arg1) {
					if (!text_heatbeat.getText().toString().equals("")) {
						text_heatbeat.setText(text_heatbeat.getText().toString() + "bmp");
					}
					
				} else {
					text_heatbeat.setText("");
				}
			}
		});
		text_bodytemp = (EditText)findViewById(R.id.bodytemp_text);
		text_bodytemp.setText("");
		text_bodytemp.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (!arg1) {
					if (!text_bodytemp.getText().toString().equals("")) {
						text_bodytemp.setText(text_bodytemp.getText().toString() + "摄氏度");
					}										
				} else {
					text_bodytemp.setText("");
				}
			}
		});
		text_takephoto = (EditText)findViewById(R.id.photo_text);
		text_contentrec = (EditText)findViewById(R.id.content_state_text);
		text_contentrec.setText("");
		text_contentrec.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {					
					text_contentrec.setText("");
				} 
			}
		});
		btn_bloodup = (ImageView)findViewById(R.id.bloodup_editable);
		btn_heartbeat = (ImageView)findViewById(R.id.heartbeat_editable);
		btn_bodytemp = (ImageView)findViewById(R.id.bodytemp_editable);
		btn_takephoto = (ImageView)findViewById(R.id.photo_editable);
		btn_content_rec = (ImageView)findViewById(R.id.content_state_editable);
		btn_bloodup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				text_bloodup.setInputType(InputType.TYPE_CLASS_NUMBER);				
				 // only will trigger it if no physical keyboard is open
				mgr1.showSoftInput(text_bloodup, InputMethodManager.SHOW_IMPLICIT);				
			}
		});
		btn_heartbeat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				text_heatbeat.setInputType(InputType.TYPE_CLASS_NUMBER);				
			}
		});
		btn_bodytemp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				text_bodytemp.setInputType(InputType.TYPE_CLASS_NUMBER);				
			}
		});
		btn_content_rec.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				text_contentrec.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);				
			}
		});
	}
	private void getDatafromPrevious() {
		// TODO Auto-generated method stub
		Intent previous = getIntent();
		receivedata = previous.getBundleExtra("Data_patient");
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
		btnFinsh.setText(getString(R.string.complete));
		btnFinsh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent recorddata = new Intent(StatusRecordActivity.this, NursePlanActivity.class);
				recorddata.putExtra("Data_patient", receivedata);
				String senddata = "";
				if (!text_bloodup.getText().toString().equals("")) {
					senddata += getString(R.string.bloodpressure) + " " + text_bloodup.getText().toString() + "mmhg"+ "\n";
				}
				if (!text_heatbeat.getText().toString().equals("")) {
					senddata += getString(R.string.heartbeat) + " " + text_heatbeat.getText().toString() + "\n";					
				}
				if (!text_bodytemp.getText().toString().equals("")) {
					senddata += getString(R.string.bodyTemp) + " " + text_bodytemp.getText().toString() + "\n";
				}
				if (!text_contentrec.getText().toString().equals("")) {
					senddata += getString(R.string.state_patient) + " " + text_contentrec.getText().toString();
				}
				Log.e(senddata, "why");
				if (senddata.equals("")) {
					Log.e("aaaaaaaaaaaaaaaaaaa", "bbbbbbbbbbbb");
					showToast();
				} else {
					recorddata.putExtra("sendingrecord", senddata);
					startActivity(recorddata);
				}
				
			}
		});
		title = (TextView)findViewById(R.id.titletxt);
		title.setText(getString(R.string.btnRecord));
	}
	protected void showToast() {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "aaaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
	}
	
}
