package com.medicaltreatment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.medicaltreatment.R;
import com.medicaltreatment.UI.ConversationGalleyAdapter;
import com.medicaltreatment.content.ConversationUserInfoContent;
import com.medicaltreatment.content.PatientInfoContent;
import com.medicaltreatment.database.manager.ConversationUserDatabaseManager;
import com.medicaltreatment.database.manager.PatientInfoDatabaseManager;
import com.medicaltreatment.database.manager.StatusRecordDatabaseManager;
import com.medicaltreatment.database.tables.PatientInformationTable;
import com.medicaltreatment.view.SwitchButton;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ConverUserAddActivity extends Activity {
	//ActoinBar Command	
	private LinearLayout btnback;
	private Button btnFinsh;
	private TextView title;
	private ArrayList<ConversationUserInfoContent> dataofmember;
	private ConversationGalleyAdapter adapter;
	private GridView members;
	private SwitchButton swtich_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);	
		setActionBar();
		members = (GridView)findViewById(R.id.grid_members);
		dataofmember = new ArrayList<ConversationUserInfoContent>();
		for (int i = 0; i < 5; i ++) {
			ConversationUserInfoContent data = new ConversationUserInfoContent("RiCholNam", null);
			dataofmember.add(data);
		}
		adapter = new ConversationGalleyAdapter(ConverUserAddActivity.this, dataofmember);
		members.setAdapter(adapter);
		swtich_btn = (SwitchButton)findViewById(R.id.btn_switch);
		swtich_btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					Log.e("Hi! Mr. Tong.", "are you dead");
				} else {
					Log.e("Hi! Mr. Chol.", "are you dead");
				}
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
		btnFinsh.setVisibility(View.GONE);		
		title = (TextView)findViewById(R.id.titletxt);	
		title.setText(getString(R.string.information_staff));
	}	
}
