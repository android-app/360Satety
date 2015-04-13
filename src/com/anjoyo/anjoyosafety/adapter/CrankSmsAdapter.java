package com.anjoyo.anjoyosafety.adapter;

import java.util.List;

import com.anjoyo.anjoyosafety.bean.CrankSmsBean;
import com.anjoyo.anjoyosatety.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CrankSmsAdapter extends BaseAdapter {
	public Context context;
	public  List<CrankSmsBean> list;
	public CrankSmsAdapter(Context context, List<CrankSmsBean> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public CrankSmsBean getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.cranksms_adapter, null);
		
		ImageView iv = (ImageView) arg1.findViewById(R.id.cranksmscheckbox);
		if(list.get(arg0).getFlag()){
			iv.setBackgroundResource(R.drawable.shield_checkbox_alert_unchecked);
			
		}else{
			iv.setBackgroundResource(R.drawable.shield_checkbox_alert_checked);
			
		}
		
		TextView photonum = (TextView) arg1
				.findViewById(R.id.cranksmsphotonumm);
		TextView body = (TextView) arg1.findViewById(R.id.cranksmsbody);
		CrankSmsBean bean = list.get(arg0);
		body.setText(bean.getBody());
		photonum.setText(bean.getPhoto());

		return arg1;
	}

}
