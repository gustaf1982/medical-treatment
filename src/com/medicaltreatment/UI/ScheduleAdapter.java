package com.medicaltreatment.UI;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.medicaltreatment.LogViewActivity;
import com.medicaltreatment.R;
import com.medicaltreatment.content.SchedulInfoContent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScheduleAdapter extends BaseAdapter {

	private LogViewActivity activity;
	private ArrayList<SchedulInfoContent> datalist;
	private LayoutInflater inflater;
	
	public ScheduleAdapter(LogViewActivity a, ArrayList<SchedulInfoContent> d) {
		// TODO Auto-generated constructor stub
		activity = a;
		datalist = d;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datalist.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return datalist.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = inflater.inflate(R.layout.time_info, null);			
		TextView timeset = (TextView)vi.findViewById(R.id.timeset);
		String time = DateUtil.getNormalTime(datalist.get(position).id);
		timeset.setText(time);
		boolean changed = false;
		changed = datalist.get(position).existcare;
		LinearLayout back = (LinearLayout)vi.findViewById(R.id.backforlist);
		TextView content = (TextView)vi.findViewById(R.id.content_alarm_text);
		ImageView alarmstatimg = (ImageView)vi.findViewById(R.id.state_alarm_img);
		content.setText("");
		if (changed) {
			content.setText(datalist.get(position).carecontent);
			alarmstatimg.setImageResource(android.R.drawable.ic_menu_recent_history);
			back.setBackgroundResource(R.color.blue_back);
		}
		
		return vi;
	}

}
