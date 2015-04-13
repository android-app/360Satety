package com.anjoyo.anjoyosafety.adapter;

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
//广告拦截主界面的listview显示
public class InterceptAdapter extends BaseAdapter{
	ArrayList<AppInfo> appList ;
	private Context     mContext;  
	AppInfo info;
	   
	   public InterceptAdapter(Context c,ArrayList<AppInfo> appList)  
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
		return position;
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
			convertView=LayoutInflater.from(mContext).inflate(R.layout.ad_main_listview_item, null);
			holder.tv1=(TextView) convertView.findViewById(R.id.ad_main_listview_item_tv1);
			holder.tv2=(TextView) convertView.findViewById(R.id.ad_main_listview_item_tv2);
			holder.icon=(ImageView) convertView.findViewById(R.id.ad_main_listview_item_icon);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		info=appList.get(position);
		
			holder.tv1.setText(info.getAppName());
			System.out.println(info.getAppName());
			holder.tv2.setText("安全软件");
			holder.icon.setImageDrawable(info.getAppIcon());
			
	
			
		
		return convertView;
	}
	class ViewHolder{
		TextView tv1,tv2;
		ImageView icon;
	}
}