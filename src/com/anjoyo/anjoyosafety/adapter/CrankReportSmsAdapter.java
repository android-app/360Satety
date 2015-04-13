package com.anjoyo.anjoyosafety.adapter;

import java.util.List;

import com.anjoyo.anjoyosafety.activity.Crank_Tabhost_1;
import com.anjoyo.anjoyosafety.bean.CrankSmsBean;
import com.anjoyo.anjoyosatety.activity.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CrankReportSmsAdapter extends BaseAdapter {

	public Context context;
	public static List<CrankSmsBean> list;

	public CrankReportSmsAdapter(Context context, List<CrankSmsBean> list) {
		this.context = context;
		this.list = list;
		
		

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (list.size() == 0) {
			
			Crank_Tabhost_1.misslayout();
		} else {
			Crank_Tabhost_1.listShowSms();
		}
		convertView = LayoutInflater.from(context).inflate(
				R.layout.cranksms_showreportsms, null);
		TextView photonum = (TextView) convertView
				.findViewById(R.id.crankreportphotonum);
		TextView body = (TextView) convertView
				.findViewById(R.id.crankreportcontext);
		CrankSmsBean bean = list.get(position);
		body.setText(bean.getBody());
		photonum.setText(bean.getPhoto());
		return convertView;
	}

}
