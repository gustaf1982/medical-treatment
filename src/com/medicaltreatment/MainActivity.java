package com.medicaltreatment;



import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import com.medicaltreatment.UI.PatientListAdapter;
import com.medicaltreatment.content.PatientInfoContent;
import com.medicaltreatment.database.manager.PatientInfoDatabaseManager;
import com.medicaltreatment.database.tables.PatientInformationTable;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private LinearLayout btnback;
	private Button btnsetting;
	private TextView title;
	private Button btnSignIn;
	private GridView grid;
	private ArrayList<PatientInfoContent> patientlist;
	private PatientListAdapter adapter;
	private PatientInfoDatabaseManager dbHandler;
	private Cursor myCursor;
	private static int RESULT_REG = 1;
	private Runnable Timer_Thick = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ShowMainMenu();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ImageView logoimg = new ImageView(this);
		logoimg.setScaleType(ImageView.ScaleType.FIT_XY);
		
		patientlist = new ArrayList<PatientInfoContent>();
		dbHandler = new PatientInfoDatabaseManager(this);
		
		Bitmap logobmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
		logoimg.setImageBitmap(logobmp);
		LayoutParams lpms = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setContentView(logoimg, lpms);
		Timer timer = new Timer();		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TimerMethod();
			}
		}, 3000);
	}

	protected void TimerMethod() {
		// TODO Auto-generated method stub
		this.runOnUiThread(Timer_Thick);
	}

	protected void ShowMainMenu() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.login);
		grid = (GridView)findViewById(R.id.gridpatient);
		setdata();	
		adapter = new PatientListAdapter(MainActivity.this, patientlist);
		grid.setAdapter(adapter);
		btnSignIn = (Button)findViewById(R.id.signInBtn);
		btnSignIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MainActivity.this, RegPatientActivity.class);
                startActivityForResult(myIntent, RESULT_REG);
			}
		});
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, PatientListActivity.class);
				Bundle dataInteraction = new Bundle();
				dataInteraction.putString("name", patientlist.get(arg2).name);
				dataInteraction.putString("name", patientlist.get(arg2).name);
				dataInteraction.putString("gender", patientlist.get(arg2).gender);
				dataInteraction.putString("oldlevel", patientlist.get(arg2).oldlevel);
				dataInteraction.putString("place", patientlist.get(arg2).places);
				dataInteraction.putString("contact", patientlist.get(arg2).contacts);
				dataInteraction.putString("phone", patientlist.get(arg2).phonenumber);
				dataInteraction.putString("statue", patientlist.get(arg2).finish_state);
				intent.putExtra("Data_patient", dataInteraction);
				startActivity(intent);
			}
		});
		btnback = (LinearLayout)findViewById(R.id.btnleftaction);
		btnback.setVisibility(View.GONE);
		btnsetting = (Button)findViewById(R.id.btnrightaction);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		setdata();
		adapter.notifyDataSetChanged();	
	}
	private void setdata() {
		// TODO Auto-generated method stub
		
		myCursor = dbHandler.selectPerson();
		if (myCursor.getCount() <= 0) return;
		int count = patientlist.size();
		for (int i = 0; i < count; i ++) {
			patientlist.remove(0);
		}
		int index = 0;
		Log.e("bbbbb", "ddddddd");
		myCursor.moveToLast();
	    while (myCursor.isBeforeFirst() == false) {
	    	index ++;
	    	String name = myCursor.getString(myCursor.getColumnIndex(PatientInformationTable.FIELD_PERSONNAME));
	    	String gender = myCursor.getString(myCursor.getColumnIndex(PatientInformationTable.FIELD_SEX));
	    	String old = myCursor.getString(myCursor.getColumnIndex(PatientInformationTable.FIELD_OLD));
	    	String place = myCursor.getString(myCursor.getColumnIndex(PatientInformationTable.FIELD_PLACE));
	    	String contact = myCursor.getString(myCursor.getColumnIndex(PatientInformationTable.FIELD_CONTACT));
	    	String phone = myCursor.getString(myCursor.getColumnIndex(PatientInformationTable.FIELD_PHONENUM));
	    	String state = myCursor.getString(myCursor.getColumnIndex(PatientInformationTable.FIELD_STATUS));
			PatientInfoContent patientInfo = new PatientInfoContent(index, name, gender, old, place, contact, phone, state);
			patientlist.add(patientInfo);
            myCursor.moveToPrevious();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
