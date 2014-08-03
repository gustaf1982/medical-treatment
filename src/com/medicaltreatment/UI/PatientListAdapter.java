package com.medicaltreatment.UI;

import java.util.ArrayList;


import com.medicaltreatment.NursePlanActivity;
import com.medicaltreatment.PatientListActivity;
import com.medicaltreatment.R;
import com.medicaltreatment.StatusRecordActivity;
import com.medicaltreatment.R.id;
import com.medicaltreatment.R.layout;
import com.medicaltreatment.content.PatientInfoContent;
import com.medicaltreatment.database.manager.PatientInfoDatabaseManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PatientListAdapter extends BaseAdapter {
	
	private Activity activity;
	private ArrayList<PatientInfoContent> data;
	private LayoutInflater inflater;
	
	
	
	
	public PatientListAdapter(Activity a, ArrayList<PatientInfoContent> d) {
		// TODO Auto-generated constructor stub
		activity = a;
		data = d;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int postion, View convertview, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View vi;		
		vi = inflater.inflate(R.layout.patient_data, null);
		TextView messages = (TextView)vi.findViewById(R.id.leftmessagestxt);
		TextView name = (TextView)vi.findViewById(R.id.patientname);
		name.setText(data.get(postion).name);
		TextView caremans = (TextView)vi.findViewById(R.id.caremans);
		TextView result = (TextView)vi.findViewById(R.id.result);
		String state_finish = data.get(postion).finish_state;
		result.setText(state_finish);
		if (state_finish == "看护已结束") {
			result.setBackgroundResource(R.color.blue_back);
		}
 		ImageView photoimg = (ImageView)vi.findViewById(R.id.patientphotoimg);
		return vi;		
	}
}
