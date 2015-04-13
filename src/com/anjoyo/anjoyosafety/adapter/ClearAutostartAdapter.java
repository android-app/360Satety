package com.anjoyo.anjoyosafety.adapter;

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

import com.anjoyo.anjoyosafety.bean.AutostartSoftWareInfo;
import com.anjoyo.anjoyosatety.activity.R;

public class ClearAutostartAdapter extends BaseAdapter{

    private List<AutostartSoftWareInfo> mlistAppInfo;	
	LayoutInflater infater = null;
	public ClearAutostartAdapter(Context context,  List<AutostartSoftWareInfo> apps){
		infater=LayoutInflater.from(context);
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null ) {
			convertView = infater.inflate(R.layout.clear_autostart_litview_item, null);
			holder = new ViewHolder();
			holder.appIcon=(ImageView) convertView.findViewById(R.id.clear_autostart_icon);
			holder.tvAppLabel=(TextView) convertView.findViewById(R.id.clear_autostart_app_name);
			holder.tvAutostart=(TextView) convertView.findViewById(R.id.clear_autostart_autostart);
			holder.rl_cb=(RelativeLayout) convertView.findViewById(R.id.clear_autostart_rl_cb);
			holder.cb=(CheckBox) convertView.findViewById(R.id.clear_autostart_cb);
			convertView.setTag(holder);
		} 
		else{
			holder = (ViewHolder) convertView.getTag() ;
		}
		final AutostartSoftWareInfo appInfo = (AutostartSoftWareInfo) getItem(position);
		holder.appIcon.setImageDrawable(appInfo.getApp_Icon());
		holder.tvAppLabel.setText(appInfo.getApp_name());
		final CheckBox checkBox = holder.getCb();
		holder.rl_cb.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(checkBox.isChecked()){
					checkBox.setChecked(false);
					appInfo.setChecked(false);				
				}else{
					checkBox.setChecked(true);
					appInfo.setChecked(true);
				}
				
			}
		});
		return convertView;
	}
	

	class ViewHolder {
		ImageView appIcon;
		TextView tvAppLabel;
        TextView tvAutostart;
        public CheckBox getCb() {
			return cb;
		}
		public void setCb(CheckBox cb) {
			this.cb = cb;
		}
		CheckBox cb;
        RelativeLayout rl_cb;
	}
	
}
