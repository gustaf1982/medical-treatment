package com.medicaltreatment.UI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.medicaltreatment.NursePlanActivity;
import com.medicaltreatment.PatientListActivity;
import com.medicaltreatment.content.NursePlanDataContent;
import com.medicaltreatment.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NursePlanListAdapter extends BaseAdapter {
	
	private NursePlanActivity activity;
	private ArrayList<NursePlanDataContent> data;
	private LayoutInflater inflater;
	public NursePlanListAdapter(NursePlanActivity a, ArrayList<NursePlanDataContent> d) {
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
	public View getView(int position, View convertview, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertview;
		
		if (convertview == null)
			vi = inflater.inflate(R.layout.nurse_plan_list_row, null);
		
		final Spinner spinner = (Spinner)vi.findViewById(R.id.spinner1);
		
		final EditText edtxt = (EditText)vi.findViewById(R.id.highPressureEdtTxt);
		
		
		edtxt.setText(data.get(position).content);
		spinner.setSelection(Integer.valueOf(data.get(position).time));//modify later....
		final int pos = position;
		
		return vi;
	}

}
