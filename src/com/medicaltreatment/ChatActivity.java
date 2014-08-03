package com.medicaltreatment;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.medicaltreatment.UI.ChatMsgViewAdapter;
import com.medicaltreatment.content.ChatMsgEntity;
import com.medicaltreatment.content.Expressions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChatActivity extends Activity implements OnClickListener {
	
	
	private static final int SELECT_PICTURE = 1;
	private String selectedImagePath;
	private String patient_name;
	
	private Context mCon;
	private ViewPager viewPager;
	private ArrayList<GridView> grids;
	private int[] expressionImages;
	private String[] expressionImageNames;
	private int[] expressionImages1;
	private String[] expressionImageNames1;
	private int[] expressionImages2;
	private String[] expressionImageNames2;
	private LinearLayout mBtnSend;
	private boolean showorhide = false;
	
	private ImageView page0;
	private ImageView page1;
	private ImageView page2;
	private EditText mEditTextContent;
	private ListView mListView;
	private GridView gView1;
	private GridView gView2;
	private GridView gView3;
	private ChatMsgViewAdapter mAdapter;
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();
	private String[] msgArray = new String[] { "Hi!", "Hi!", "How are you?", "Fine thanks and you?",
			"I am fine too", "What doing thesedays?", "Working hard", "I am....", };

	private String[] dateArray = new String[] { "2012-12-09 18:00",
			"2012-12-09 18:10", "2012-12-09 18:11", "2012-12-09 18:20",
			"2012-12-09 18:30", "2012-12-09 18:35", "2012-12-09 18:40",
			"2012-12-09 18:50" };
	
	private LinearLayout mBtnBack;
	private Button rightBtn;	
	private TextView title_text;
	
	
	private LinearLayout page_select;
	private final static int COUNT = 8;
	
	private RelativeLayout btn_take_photo;
	private RelativeLayout btn_get_photo;
	private RelativeLayout btn_short_conver;
	private RelativeLayout btn_emoticon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conversation);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		mCon = ChatActivity.this;
		
		getdataFromPrevious();
		
		page_select = (LinearLayout) findViewById(R.id.page_select_under);
		page0 = (ImageView) findViewById(R.id.page0_select);
		page1 = (ImageView) findViewById(R.id.page1_select);
		page2 = (ImageView) findViewById(R.id.page2_select);
		mListView = (ListView) findViewById(R.id.chat_list_saved);
		// 引入表情
		expressionImages = Expressions.expressionImgs;
		expressionImageNames = Expressions.expressionImgNames;
		expressionImages1 = Expressions.expressionImgs1;
		expressionImageNames1 = Expressions.expressionImgNames1;
		expressionImages2 = Expressions.expressionImgs2;
		expressionImageNames2 = Expressions.expressionImgNames2;
		// 创建ViewPager
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		
		// 发送
		mBtnSend = (LinearLayout) findViewById(R.id.btn_send_messages);
		mBtnSend.setOnClickListener(this);
		// 返回
		mBtnBack = (LinearLayout) findViewById(R.id.btnleftaction);
		mBtnBack.setOnClickListener(this);
		// 个人信息
		rightBtn = (Button) findViewById(R.id.btnrightaction);
		rightBtn.setOnClickListener(this);
		rightBtn.setText(getString(R.string.information));
		
		title_text = (TextView)findViewById(R.id.titletxt);
		title_text.setText(getString(R.string.btnExchange) + ": " + patient_name);
		
		// 语音
		Log.e("aaaaaaaaaa", "bbbbbbbbbbbbb");
		btn_emoticon = (RelativeLayout) findViewById(R.id.btn_emoticon);
		btn_emoticon.setOnClickListener(this);	
		
		btn_take_photo = (RelativeLayout)findViewById(R.id.btn_take_photo);
		btn_take_photo.setOnClickListener(this);
		
		btn_get_photo = (RelativeLayout)findViewById(R.id.btn_photos);
		btn_get_photo.setOnClickListener(this);
		
		btn_short_conver = (RelativeLayout)findViewById(R.id.btn_quickrecovery);
		btn_short_conver.setOnClickListener(this);
		
		mEditTextContent = (EditText) findViewById(R.id.send_chat_text);
		
		initViewPager();
		initData();
	}

	private void getdataFromPrevious() {
		// TODO Auto-generated method stub
		Intent previous = getIntent();
		Bundle previousdata = previous.getBundleExtra("Data_patient");
		patient_name = previousdata.getString("name");
	}

	private void initViewPager() {
		LayoutInflater inflater = LayoutInflater.from(this);
		grids = new ArrayList<GridView>();
		gView1 = (GridView) inflater.inflate(R.layout.grid, null);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		// 生成24个表情
		for (int i = 0; i < 24; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", expressionImages[i]);
			listItems.add(listItem);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(mCon, listItems,
				R.layout.singleexpression, new String[] { "image" },
				new int[] { R.id.image });
		gView1.setAdapter(simpleAdapter);
		gView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Bitmap bitmap = null;
				bitmap = BitmapFactory.decodeResource(getResources(),
						expressionImages[arg2 % expressionImages.length]);
				ImageSpan imageSpan = new ImageSpan(mCon, bitmap);
				SpannableString spannableString = new SpannableString(
						expressionImageNames[arg2].substring(1,
								expressionImageNames[arg2].length() - 1));
				spannableString.setSpan(imageSpan, 0,
						expressionImageNames[arg2].length() - 2,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// 编辑框设置数据
				mEditTextContent.append(spannableString);
				System.out.println("edit的内容 = " + spannableString);
			}
		});
		grids.add(gView1);

		gView2 = (GridView) inflater.inflate(R.layout.grid, null);
		grids.add(gView2);

		gView3 = (GridView) inflater.inflate(R.layout.grid, null);
		grids.add(gView3);
		System.out.println("GridView的长度 = " + grids.size());

		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return grids.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(grids.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(grids.get(position));
				return grids.get(position);
			}
		};

		viewPager.setAdapter(mPagerAdapter);
		// viewPager.setAdapter();

		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	}

	private void initData() {
		for (int i = 0; i < COUNT; i++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(dateArray[i]);
			if (i % 2 == 0) {
				entity.setName("KimCholHiek");
				entity.setMsgType(true);
			} else {
				entity.setName("TongMiengIl");
				entity.setMsgType(false);
			}
			entity.setText(msgArray[i]);
			mDataArrays.add(entity);
		}
		mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);
	}

	private String getDate() {
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);
		return sbBuffer.toString();
	}

	@Override
	public void onClick(View v) {
		boolean isFoused = false;
		switch (v.getId()) {
		// 返回
		case R.id.btnleftaction:
			finish();
			break;
			
		case R.id.btnrightaction:
			Intent converuseradd = new Intent(mCon, ConverUserAddActivity.class);
			startActivity(converuseradd);
		// 发送
		case R.id.btn_send_messages:
			String content = mEditTextContent.getText().toString();
			System.out.println("edit.get的内容 = " + content);
			if (content.length() > 0) {
				ChatMsgEntity entity = new ChatMsgEntity();
				entity.setDate(getDate());
				entity.setName("人马");
				entity.setMsgType(false);
				entity.setText(content);

				mDataArrays.add(entity);
				// 更新listview
				mEditTextContent.setText("");
				viewPager.setVisibility(ViewPager.GONE);
				page_select.setVisibility(page_select.GONE);
				mAdapter.notifyDataSetChanged();
				mListView.setSelection(mListView.getCount() - 1);
			} else {
				Toast.makeText(mCon, "不能发送空消息", Toast.LENGTH_LONG).show();
			}
			break;
		
		// 表情
		case R.id.btn_emoticon:
			showorhide = !showorhide;
			ImageView bg_img;
			bg_img = (ImageView)findViewById(R.id.img_emoticon);
			if (showorhide) {
				Bitmap bg_1 = BitmapFactory.decodeResource(getResources(), R.drawable.begin);				
				bg_img.setImageBitmap(bg_1);				
				viewPager.setVisibility(viewPager.VISIBLE);
				page_select.setVisibility(page_select.VISIBLE);
			} else {
				Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_emoticon);				
				bg_img.setImageBitmap(bg);
				viewPager.setVisibility(viewPager.GONE);
				page_select.setVisibility(page_select.GONE);
			}			
			break;
			
		case R.id.btn_take_photo:
			
			Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"workupload.jpg"));  			
			//  
			cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(cameraIntent, 0);
			break;
			
		case R.id.btn_photos:
			Intent getPhotointent = new Intent();
			getPhotointent.setType("image/*");
			getPhotointent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(getPhotointent,"Select Picture"), SELECT_PICTURE);
            break;
		}
		
		

	}

	// 点击小黑图像
	public void head_xiaohei(View v) { // 标题栏 返回按钮
	}

	// ** 指引页面改监听器 */
	class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				page0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_focused));
				page1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));

				break;
			case 1:
				page1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_focused));
				page0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				page2.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
				// 生成24个表情
				for (int i = 0; i < 24; i++) {
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("image", expressionImages1[i]);
					listItems.add(listItem);
				}

				SimpleAdapter simpleAdapter = new SimpleAdapter(mCon,
						listItems, R.layout.singleexpression,
						new String[] { "image" }, new int[] { R.id.image });
				gView2.setAdapter(simpleAdapter);
				gView2.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Bitmap bitmap = null;
						bitmap = BitmapFactory.decodeResource(getResources(),
								expressionImages1[arg2
										% expressionImages1.length]);
						ImageSpan imageSpan = new ImageSpan(mCon, bitmap);
						SpannableString spannableString = new SpannableString(
								expressionImageNames1[arg2]
										.substring(1,
												expressionImageNames1[arg2]
														.length() - 1));
						spannableString.setSpan(imageSpan, 0,
								expressionImageNames1[arg2].length() - 2,
								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						// 编辑框设置数据
						mEditTextContent.append(spannableString);
						System.out.println("edit的内容 = " + spannableString);
					}
				});
				break;
			case 2:
				page2.setImageDrawable(getResources().getDrawable(
						R.drawable.page_focused));
				page1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				page0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				List<Map<String, Object>> listItems1 = new ArrayList<Map<String, Object>>();
				// 生成24个表情
				for (int i = 0; i < 24; i++) {
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("image", expressionImages2[i]);
					listItems1.add(listItem);
				}

				SimpleAdapter simpleAdapter1 = new SimpleAdapter(mCon,
						listItems1, R.layout.singleexpression,
						new String[] { "image" }, new int[] { R.id.image });
				gView3.setAdapter(simpleAdapter1);
				gView3.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Bitmap bitmap = null;
						bitmap = BitmapFactory.decodeResource(getResources(),
								expressionImages2[arg2
										% expressionImages2.length]);
						ImageSpan imageSpan = new ImageSpan(mCon, bitmap);
						SpannableString spannableString = new SpannableString(
								expressionImageNames2[arg2]
										.substring(1,
												expressionImageNames2[arg2]
														.length() - 1));
						spannableString.setSpan(imageSpan, 0,
								expressionImageNames2[arg2].length() - 2,
								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						// 编辑框设置数据
						mEditTextContent.append(spannableString);
						System.out.println("edit的内容 = " + spannableString);
					}
				});
				break;

			}
		}
	}	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);
                System.out.println(bitmap);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
