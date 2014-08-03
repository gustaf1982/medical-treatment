package com.medicaltreatment.UI;

import java.util.ArrayList;

import com.medicaltreatment.content.ConversationUserInfoContent;
import com.medicaltreatment.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConversationGalleyAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<ConversationUserInfoContent> datainfos;
	private LayoutInflater inflater;
	public ConversationGalleyAdapter(Context c, ArrayList<ConversationUserInfoContent> d) {
		// TODO Auto-generated constructor stub
		context = c;
		datainfos = d;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datainfos.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return datainfos.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int postion, View convertview, ViewGroup parent) {
		View vi = inflater.inflate(R.layout.galley_view, null);
		ImageView photo = (ImageView)vi.findViewById(R.id.imageView1);
		TextView tv = (TextView)vi.findViewById(R.id.textView1);		
		tv.setText(datainfos.get(postion).name);
		// TODO Auto-generated method stub
		return vi;
	}

}
