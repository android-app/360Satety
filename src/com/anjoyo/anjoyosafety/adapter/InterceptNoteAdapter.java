package com.anjoyo.anjoyosafety.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.anjoyo.anjoyosafety.contants.AppInfo;
import com.anjoyo.anjoyosatety.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InterceptNoteAdapter extends BaseAdapter {
	ArrayList<AppInfo> appList ;
	private Context     mContext;  
	private final long time=System.currentTimeMillis();
	AppInfo info;
	public InterceptNoteAdapter(Context c,ArrayList<AppInfo> appList)  
	{  
		mContext = c;  
		this.appList=appList; 
	} 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return appList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return appList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=LayoutInflater.from(mContext).inflate(R.layout.ad_note_list_item, null);
			holder.tv1=(TextView) convertView.findViewById(R.id.ad_note_listview_item_tv1);
			holder.icon=(ImageView) convertView.findViewById(R.id.ad_note_listview_item_icon);
			holder.time=(TextView) convertView.findViewById(R.id.ad_note_listview_item_time);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		info=appList.get(position);
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd hh:mm:ss");
		sdf.format(time);
		holder.tv1.setText(info.getAppName());
		holder.icon.setImageDrawable(info.getAppIcon());
		holder.time.setText(sdf.format(time));
		return convertView;
	}
	class ViewHolder{
		TextView tv1,time;
		ImageView icon;
	}

}
