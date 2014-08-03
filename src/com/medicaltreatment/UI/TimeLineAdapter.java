package com.medicaltreatment.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.medicaltreatment.LogViewActivity;
import com.medicaltreatment.NursePlanActivity;
import com.medicaltreatment.R;
import com.medicaltreatment.content.NursePlanDataContent;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeLineAdapter extends BaseAdapter {
	private LogViewActivity activity;
	private List<String> date;
	private List<Map<String, String>> items;
	private String strMonth;
	
	
	public TimeLineAdapter(LogViewActivity a, String month, List<String> d, List<Map<String, String>> i) {
		// TODO Auto-generated constructor stub
		activity = a;
		strMonth = month;
		date = d;
		items = i;
	}
	
	
	public int getCount() {

		return date.size() + 2;
	}

	public Object getItem(int position) {
		return date.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = activity.getLayoutInflater().inflate(R.layout.time_info, null);
		
		LinearLayout llAllInfo = (LinearLayout) view.findViewById(R.id.llAllInfo);
		if (position == 0) {
			LinearLayout layout = new LinearLayout(activity);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			layout.setGravity(Gravity.CENTER_VERTICAL);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.gravity = Gravity.LEFT;
			lp.setMargins(120, 0, 0, 0);
			layout.setLayoutParams(lp);

			ImageView img = new ImageView(activity);
			img.setImageResource(R.drawable.begin);
			img.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			
			layout.addView(img);

			TextView tvInfo = new TextView(activity);
			//tvInfo.setBackground(activity.getResources().getDrawable(R.drawable.search_bar_edit_normal));
			tvInfo.setGravity(Gravity.CENTER);
			tvInfo.setText(strMonth);
			layout.addView(tvInfo);
			llAllInfo.addView(layout);
		} else if (position == date.size() + 1) {
			LinearLayout layout = new LinearLayout(activity);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			layout.setGravity(Gravity.CENTER_VERTICAL);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.gravity = Gravity.LEFT;
			lp.setMargins(120, 0, 0, 0);
			layout.setLayoutParams(lp);

			ImageView img = new ImageView(activity);
			img.setImageResource(R.drawable.end);
			img.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			layout.addView(img);

			
			llAllInfo.addView(layout);
		} else {

			//tvDate.setText(date.get(position - 1));
			for (int j = 0; j < items.get(position - 1).size(); j++) {
				LinearLayout layout = new LinearLayout(activity);
				layout.setOrientation(LinearLayout.HORIZONTAL);
				layout.setGravity(Gravity.CENTER_VERTICAL);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				lp.gravity = Gravity.LEFT;
				lp.setMargins(130, 0, 0, 0);
				layout.setLayoutParams(lp);

				ImageView img = new ImageView(activity);
				img.setImageResource(R.drawable.send);
				img.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				layout.addView(img);
				TextView tvLine = new TextView(activity);
				tvLine.setLayoutParams(new ViewGroup.LayoutParams(20, 1));
				tvLine.setBackgroundColor(Color.BLACK);
				layout.addView(tvLine);
				TextView tvInfo = new TextView(activity);
				//tvInfo.setBackground(activity.getResources().getDrawable(R.drawable.search_bar_edit_normal));
				tvInfo.setText(items.get(position - 1).get("item" + j));
				tvInfo.setGravity(Gravity.LEFT);
				layout.addView(tvInfo);

				llAllInfo.addView(layout);
			}

		}

		return view;
	}
}