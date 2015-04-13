package com.anjoyo.anjoyosafety.adapter;

import java.util.List;

import android.content.pm.ResolveInfo;
import android.net.TrafficStats;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.anjoyo.anjoyosafety.activity.FlowFirewallAct;
import com.anjoyo.anjoyosafety.contants.AppInfo;
import com.anjoyo.anjoyosafety.util.TrafficDataUtil;
import com.anjoyo.anjoyosatety.activity.R;

public class FlowFirewallListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<ResolveInfo> listInfo;
	private FlowFirewallAct context;
	AppInfo info;
	public FlowFirewallListAdapter(FlowFirewallAct context,List<ResolveInfo> listInfo){
		inflater=LayoutInflater.from(context);
		this.listInfo=listInfo;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listInfo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(null == convertView){
			convertView = inflater.inflate(R.layout.flow_firewall_listview_item, null);
			holder = new ViewHolder();
			holder.icon = (ImageView) convertView.findViewById(R.id.flow_firewall_app_icon);
			holder.appName = (TextView) convertView.findViewById(R.id.flow_firewall_app_name);
			holder.allData = (TextView) convertView.findViewById(R.id.flow_firewall_alldata);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		final ResolveInfo info = listInfo.get(position);
		holder.icon.setImageDrawable(info.loadIcon(context.getPackageManager()));
		holder.appName.setText(info.loadLabel(context.getPackageManager()));
		final long totalTx = TrafficStats.getUidTxBytes(info.activityInfo.applicationInfo.uid);
		final long totalRx = TrafficStats.getUidRxBytes(info.activityInfo.applicationInfo.uid);
		long all=totalTx+totalRx;
		holder.allData.setText(TrafficDataUtil.getTrafficData(all));
		return convertView;
	}	
   class ViewHolder{
	ImageView icon;	
	TextView appName;
	TextView allData;
} 

}
