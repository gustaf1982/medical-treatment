package com.medicaltreatment;


import java.util.ArrayList;

import com.medicaltreatment.database.manager.PatientInfoDatabaseManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegPatientActivity extends Activity {
	//ActoinBar Command
	private LinearLayout btnReturn;
	private Button btnRegOK;
	private LinearLayout container;
	private LayoutInflater inflater;
	//Frame UI
	private Button btnOk;
	
	private EditText editPersonName;
	private EditText editContact;
	private EditText editPhoneNum;
	private RadioGroup radioSex;
	private RadioGroup radioOld;
	private RadioGroup radioPlace;
	private String personName;
	private String sex, old, place;
	private String contact, phoneNum, status;
	
	private final static String MALE = "male";
	private final static String FEMALE = "female";
	private final static String ADALT = "adalt";
	private final static String OLDMAN = "oldman";
	private final static String CHILD = "child";
	private final static String HOSPITAL = "hospital";
	private final static String ALMS = "alms";
	private final static String HOUSE = "house";
	
	private PatientInfoDatabaseManager datahandler;
	private Spinner spinner1;
	private Spinner spinner2;
	private Spinner spinner3;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reg_patient);
		container = (LinearLayout)findViewById(R.id.container);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		LinearLayout listname = (LinearLayout)inflater.inflate(R.layout.reg_input_list_item, null);		
		container.addView(listname);
		TextView fieldname = (TextView)listname.findViewById(R.id.reg_field_text);
		fieldname.setText(R.string.personName);
		editPersonName = (EditText)listname.findViewById(R.id.reg_content_text);
		editPersonName.setVisibility(View.VISIBLE);
		editPersonName.setHint("请输入姓名");
		Spinner sp = (Spinner)listname.findViewById(R.id.spinner1);
		sp.setVisibility(View.GONE);
		
		LinearLayout listgender = (LinearLayout)inflater.inflate(R.layout.reg_input_list_item, null);
		container.addView(listgender);
		TextView fieldgender = (TextView)listgender.findViewById(R.id.reg_field_text);
		fieldgender.setText(R.string.gender);
		String[] genders = { getString(R.string.personSex_M), getString(R.string.personSex_F)};
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, genders);
		spinner1 = (Spinner)listgender.findViewById(R.id.spinner1);
		spinner1.setAdapter(adapter1);
		
		
		LinearLayout listold = (LinearLayout)inflater.inflate(R.layout.reg_input_list_item, null);
		container.addView(listold);
		TextView fieldold = (TextView)listold.findViewById(R.id.reg_field_text);
		fieldold.setText(R.string.oldlevel);
		String[] oldlevel = { getString(R.string.personOld_A), getString(R.string.personOld_C), getString(R.string.personOld_O) };
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, oldlevel);
		spinner2 = (Spinner)listold.findViewById(R.id.spinner1);
		spinner2.setAdapter(adapter2);
		
		LinearLayout listwhere = (LinearLayout)inflater.inflate(R.layout.reg_input_list_item, null);
		container.addView(listwhere);
		TextView fieldpostion = (TextView)listwhere.findViewById(R.id.reg_field_text);
		fieldpostion.setText(R.string.place);
		String[] places = { "    " + getString(R.string.place1), getString(R.string.place2), "    " + getString(R.string.place3) };
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, places);
		spinner3 = (Spinner)listwhere.findViewById(R.id.spinner1);
		spinner3.setAdapter(adapter3);
		
		LinearLayout listhelper = (LinearLayout)inflater.inflate(R.layout.reg_input_list_item, null);
		container.addView(listhelper);
		TextView fieldhelper = (TextView)listhelper.findViewById(R.id.reg_field_text);
		fieldhelper.setText(R.string.contact);
		editContact = (EditText)listhelper.findViewById(R.id.reg_content_text);
		editContact.setVisibility(View.VISIBLE);
		editContact.setHint("请输入联系人姓名");
		Spinner sp2 = (Spinner)listhelper.findViewById(R.id.spinner1);
		sp2.setVisibility(View.GONE);
		
		LinearLayout listphone = (LinearLayout)inflater.inflate(R.layout.reg_input_list_item, null);
		container.addView(listphone);
		editPhoneNum = (EditText)listphone.findViewById(R.id.reg_content_text);
		editPhoneNum.setVisibility(View.VISIBLE);
		editPhoneNum.setInputType(InputType.TYPE_CLASS_PHONE);
		editPhoneNum.setHint("请输入手机号码");
		Spinner sp3 = (Spinner)listphone.findViewById(R.id.spinner1);
		sp3.setVisibility(View.GONE);
		TextView fieldphone = (TextView)listphone.findViewById(R.id.reg_field_text);
		fieldphone.setText(R.string.phoneNum);
		LinearLayout seperater = (LinearLayout)listphone.findViewById(R.id.seperator);
		seperater.setVisibility(View.GONE);
		//database create
		datahandler = new PatientInfoDatabaseManager(this);
		TextView title = (TextView)findViewById(R.id.titletxt);
		title.setText(R.string.add_person);
		/**
	     * Defining all layout items
	     **/
		
		btnReturn = (LinearLayout)findViewById(R.id.btnleftaction);	
		btnReturn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();				
			}
		});
		
		btnRegOK = (Button)findViewById(R.id.btnrightaction);
		btnRegOK.setText(R.string.complete);
		btnRegOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (registerPatient()) {
					Intent intent = new Intent();
					setResult(Activity.RESULT_OK, intent);
					finish();
				}
			}
		});
	}


	protected boolean registerPatient() {
		// TODO Auto-generated method stub
		if((!editPersonName.getText().toString().equals("")) && (!editContact.getText().toString().equals("")) && (!editPhoneNum.getText().toString().equals("")))
		{
			personName = editPersonName.getText().toString();			
			if(datahandler.isExistPerson(personName)) {
				Toast.makeText(getApplicationContext(), "Patient is exist already!", Toast.LENGTH_SHORT).show();
				return false;
			}
			contact = editContact.getText().toString();
			phoneNum = editPhoneNum.getText().toString();
			sex = spinner1.getSelectedItem().toString();
			old = spinner2.getSelectedItem().toString();
			place = spinner3.getSelectedItem().toString();
			/*sex=MALE;
			if (spinner1.getSelectedItem().toString() == getString(R.string.personSex_M)) 
				sex = MALE;
			else 
				sex = FEMALE;
			old = ADALT;
			if(spinner2.getSelectedItemId() == 1)
				old = ADALT; //成人
			else if(spinner2.getSelectedItemId() == 2)
				old = OLDMAN; //老人
			else if(spinner2.getSelectedItemId() == 0)
				old = CHILD; //儿童
			place = HOSPITAL;
			if(spinner3.getSelectedItemId() == 0)
				place = HOSPITAL; //医院
			else if(spinner3.getSelectedItemId() == 1)
				place = ALMS; //养老院
			else if(spinner3.getSelectedItemId() == 2)
				place = HOUSE; //家庭
*/			
			status = getString(R.string.state_continue); 
			/*dbHelper = new DatabaseAdapter(v.getContext());
			dbHelper.open();
			dbHelper.createPerson(personName, sex, old, place, contact, phoneNum);*/
			
			datahandler.insertPerson(personName, sex, old, place, contact, phoneNum, status);							
			return true;
		}
		else
		{
			Toast.makeText(getApplicationContext(), "All field is need!", Toast.LENGTH_SHORT).show();
		}
		
		return false;
	}
	
	
}
