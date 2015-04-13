package com.anjoyo.anjoyosafety.activity;

import java.util.List;

import com.anjoyo.anjoyosafety.bean.BlacklistcallBean;
import com.anjoyo.anjoyosatety.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CrankToBlackCallAdapter extends BaseAdapter {
	Context context;
	List<BlacklistcallBean> data;
	
	public CrankToBlackCallAdapter(Context context,List<BlacklistcallBean> data) {
		this.context=context;
		this.data= data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = View.inflate(context,R.layout.cranktoblacklistcalladapter, null);
		TextView showNum = (TextView) convertView.findViewById(R.id.crank_call_showNum);
		TextView showtime = (TextView) convertView.findViewById(R.id.crank_call_time);
		TextView showFag = (TextView) convertView.findViewById(R.id.crank_call_fag);
		showNum.setText(data.get(position).getNumber());
		showtime.setText(data.get(position).getTime());
		showFag.setText(data.get(position).getType());
		return convertView;
	}

}
