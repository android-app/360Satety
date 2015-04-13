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

import com.anjoyo.anjoyosafety.activity.FlowSortAct;
import com.anjoyo.anjoyosafety.bean.TrafficInfo;
import com.anjoyo.anjoyosafety.contants.AppInfo;
import com.anjoyo.anjoyosafety.util.TrafficDataUtil;
import com.anjoyo.anjoyosatety.activity.R;

public class FlowSortListAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	private FlowSortAct context;
	private List<TrafficInfo> trafficInfo_list;
	public FlowSortListAdapter(FlowSortAct context,List<TrafficInfo> trafficInfo_list){
		inflater=LayoutInflater.from(context);
		this.trafficInfo_list=trafficInfo_list;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return trafficInfo_list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return trafficInfo_list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		TrafficInfo info = trafficInfo_list.get(position);
		if(convertView==null){
			convertView = inflater.inflate(R.layout.flow_sort_listview_item, null);
			holder = new ViewHolder();
			holder.icon = (ImageView) convertView.findViewById(R.id.flow_sort_app_icon);
			holder.appName = (TextView) convertView.findViewById(R.id.flow_sort_app_name);
			holder.upData = (TextView) convertView.findViewById(R.id.flow_sort_updata);
			holder.downData = (TextView) convertView.findViewById(R.id.flow_sort_downdata);
			holder.allData = (TextView) convertView.findViewById(R.id.flow_sort_alldata);
			convertView.setTag(holder);
		}else {
			
			holder = (ViewHolder) convertView.getTag();
		}
		holder.icon.setImageDrawable(info.getIcon());
		holder.appName.setText(info.getName());
		//上传的数据
		final long totalTx = TrafficStats.getUidTxBytes(info.getUid());
		//接收的数据
		final long totalRx = TrafficStats.getUidRxBytes(info.getUid());
		
		holder.upData.setText(TrafficDataUtil.getTrafficData(totalTx));
		holder.downData.setText(TrafficDataUtil.getTrafficData(totalRx));
		long all=totalTx+totalRx;
		holder.allData.setText(TrafficDataUtil.getTrafficData(all));
		return convertView;
	}
	
	
   class ViewHolder{
	ImageView icon;
	
	TextView appName;
	TextView upData;
	TextView downData;
	TextView allData;
} 

}
