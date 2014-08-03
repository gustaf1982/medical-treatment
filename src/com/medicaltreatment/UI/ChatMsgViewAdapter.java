package com.medicaltreatment.UI;

import java.util.ArrayList;
import java.util.List;

import com.medicaltreatment.R;
import com.medicaltreatment.content.ChatMsgEntity;
import com.medicaltreatment.content.ExpressionUtil;
import com.medicaltreatment.view.CircleImageView;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatMsgViewAdapter extends BaseAdapter {
	private static final String TAG = ChatMsgViewAdapter.class.getSimpleName();

	private List<ChatMsgEntity> coll;

	private Context ctx;

	private LayoutInflater mInflater;

	public static interface IMsgViewType {
		int IMVT_COM_MSG = 0;
		int IMVT_TO_MSG = 1;
	}

	public ChatMsgViewAdapter() {
		// TODO Auto-generated constructor stub
	}

	public ChatMsgViewAdapter(Context context, List<ChatMsgEntity> list) {
		this.ctx = context;
		this.coll = list;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return coll.size();
	}

	@Override
	public Object getItem(int position) {
		return coll.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		ChatMsgEntity entity = coll.get(position);

		if (entity.getMsgType()) {
			return IMsgViewType.IMVT_COM_MSG;
		} else {
			return IMsgViewType.IMVT_TO_MSG;
		}
	}
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi;
		ChatMsgEntity entity = coll.get(position);
		boolean isComMsg = entity.getMsgType();
		ViewHolder viewHolder = null;	
	    
		if (isComMsg)
		{
			vi = mInflater.inflate(R.layout.chatting_item_msg_text_left, null);
		}else{
			vi = mInflater.inflate(R.layout.chatting_item_msg_text_right, null);
		}

	    	  viewHolder = new ViewHolder();
			  viewHolder.tvSendTime = (TextView) vi.findViewById(R.id.tv_sendtime);
			  viewHolder.tvUserName = (TextView) vi.findViewById(R.id.tv_username);
			  viewHolder.tvContent = (TextView) vi.findViewById(R.id.tv_chatcontent);
			  viewHolder.ivPhoto = (CircleImageView) vi.findViewById(R.id.iv_userhead);
			  viewHolder.isComMsg = isComMsg;			  
		
	    
	    
	    viewHolder.tvSendTime.setText(entity.getDate());
	    viewHolder.tvUserName.setText(entity.getName());
	    String str = entity.getText(); // 消�?�具体内容
		String zhengze = "f0[0-9]{2}|f10[0-7]"; // 正则表达�?，用�?�判断消�?�内是�?�有表情
		try {
			SpannableString spannableString = ExpressionUtil
					.getExpressionString(ctx, str, zhengze);
			viewHolder.tvContent.setText(spannableString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return vi;
	}
	
	static class ViewHolder { 
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public CircleImageView ivPhoto;
        public boolean isComMsg = true;
    }

}
