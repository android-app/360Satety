package com.anjoyo.anjoyosafety.adapter;

import java.util.ArrayList;
import java.util.List;

import com.anjoyo.anjoyosafety.adapter.InterceptAdapter.ViewHolder;
import com.anjoyo.anjoyosafety.contants.AppInfo;
import com.anjoyo.anjoyosatety.activity.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InterceptJianCeAdapter1 extends BaseAdapter{
	private ArrayList<AppInfo> appList;
    private LayoutInflater inflater; 
    Activity activity;
    AppInfo  info;
    public InterceptJianCeAdapter1(Activity a,ArrayList<AppInfo> appList) {
		super();
		activity = a;
		this.appList = appList;
		this.inflater=LayoutInflater.from(a);
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
			convertView=inflater.inflate(R.layout.ad_jiance_list_item1, null);
			holder.tv1=(TextView) convertView.findViewById(R.id.ad_jiance_list_item1_tv1);
			holder.tv2=(TextView) convertView.findViewById(R.id.ad_jiance_list_item1_tv2);
			holder.icon=(ImageView) convertView.findViewById(R.id.ad_jiance_list_item1_icon);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		info=appList.get(position);
			holder.tv1.setText(info.getAppName());
			holder.tv2.setText("暂未发现广告插件");
			holder.icon.setImageDrawable(info.getAppIcon());
		return convertView;
	}
	class ViewHolder{
		TextView tv1,tv2;
		ImageView icon;
	}
	
}
