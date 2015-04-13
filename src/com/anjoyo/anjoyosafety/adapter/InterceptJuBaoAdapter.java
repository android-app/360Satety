package com.anjoyo.anjoyosafety.adapter;

import java.util.ArrayList;
import com.anjoyo.anjoyosafety.contants.AppInfo;
import com.anjoyo.anjoyosafety.contants.JuBaoInfo;
import com.anjoyo.anjoyosatety.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InterceptJuBaoAdapter extends BaseAdapter {
	ArrayList<JuBaoInfo> baoInfo ;
	private Context     mContext;  
	JuBaoInfo info;
	
	public InterceptJuBaoAdapter(Context c,ArrayList<JuBaoInfo> baoInfo)  
	{  
		mContext = c;  
		this.baoInfo=baoInfo; 
	} 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return baoInfo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return baoInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			convertView=LayoutInflater.from(mContext).inflate(R.layout.ad_jubao_list_item, null);
		
		TextView tv1=(TextView) convertView.findViewById(R.id.ad_jubao_list_item_tv1);
		ImageView icon=(ImageView) convertView.findViewById(R.id.ad_jubao_list_item_icon);
		ImageView checkicon=(ImageView) convertView.findViewById(R.id.ad_jubao_list_item_checked);
		info=baoInfo.get(position);
		tv1.setText(info.getAppName());
        icon.setImageDrawable(info.getAppIcon());
        checkicon.setImageDrawable(info.getIcon());
		return convertView;
	}
	class ViewHolder{
		TextView tv1;
		ImageView icon;
	}
}
