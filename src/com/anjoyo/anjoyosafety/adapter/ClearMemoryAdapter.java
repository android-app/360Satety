package com.anjoyo.anjoyosafety.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjoyo.anjoyosafety.activity.ClearMemory;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.bean.ProcessInfo;
import com.anjoyo.anjoyosafety.util.TrafficDataUtil;
import com.anjoyo.anjoyosatety.activity.R;

public class ClearMemoryAdapter extends BaseAdapter{

    public List<ProcessInfo> mlistAppInfo;	
	LayoutInflater infater = null;
	private ClearMemory act;
	public static List<Integer> clearIds;
	public ClearMemoryAdapter(Context context,  List<ProcessInfo> apps){
		infater=LayoutInflater.from(context);
		act=(ClearMemory) context;
		clearIds= new ArrayList<Integer>();
		this.mlistAppInfo = apps ;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlistAppInfo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlistAppInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null ) {
			convertView = infater.inflate(R.layout.clear_memory_litview_item, null);
			holder = new ViewHolder();
			holder.appIcon=(ImageView) convertView.findViewById(R.id.clear_memory_icon);
			holder.tvAppLabel=(TextView) convertView.findViewById(R.id.clear_memory_app_name);
			holder.tvProcessMemSize=(TextView) convertView.findViewById(R.id.clear_memoryitem_memory);
			holder.cb=(CheckBox) convertView.findViewById(R.id.clear_memory_cb);
			convertView.setTag(holder);
		} 
		else{
			holder = (ViewHolder) convertView.getTag() ;
		}
		final ProcessInfo appInfo = (ProcessInfo) getItem(position);
		holder.appIcon.setImageDrawable(appInfo.getAppIcon());
		holder.tvAppLabel.setText(appInfo.getAppLabel());
		holder.tvProcessMemSize.setText(TrafficDataUtil.getMemoryData(appInfo.getMemSize()));
//		if (appInfo.isChecked()) {
//			appInfo.setChecked(false);
//			holder.cb.setChecked(false);
//		} else {
//			appInfo.setChecked(true);
//			holder.cb.setChecked(true);
//		}
	
		
		return convertView;
	}
	

	class ViewHolder {
		ImageView appIcon;
		TextView tvAppLabel;
        TextView tvProcessMemSize;
        RelativeLayout cb_rl;
        CheckBox cb;
        public CheckBox getCb() {
			return cb;
		}
		public void setCb(CheckBox cb) {
			this.cb = cb;
		}
	}
	
}
