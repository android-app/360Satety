package com.anjoyo.anjoyosafety.activity;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.anjoyo.anjoyosafety.adapter.CrankSmsAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.bean.CrankSmsBean;
import com.anjoyo.anjoyosatety.activity.R;

public class CrankAddFromToBlackSms extends MyBaseActivity implements OnClickListener{

	Button bt;
	ListView listshow;
	TextView tv_true;
	TextView tv_flast;
	
	
	public Uri SMS_INBOX = Uri.parse("content://sms/inbox");
	public CrankSmsAdapter adapter;
	List<CrankSmsBean> list;
	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.cranksms_addfromsms);
		
	}

	@Override
	protected void findViewById() {
		bt=(Button) findViewById(R.id.cranksms_addfromsms_return);
		bt.setOnClickListener(this);
		tv_true = (TextView) findViewById(R.id.blacklist_addfromsms_ture);
		tv_true.setOnClickListener(this);
		tv_flast = (TextView) findViewById(R.id.blacklist_addfromsms_return2);
		tv_flast.setOnClickListener(this);
		listshow = (ListView) findViewById(R.id.crank_sms_listview2);
		
	}

	@Override
	protected void controll() {
		list=readAllSMS();
		adapter = new CrankSmsAdapter(this,list);
		listshow.setAdapter(adapter);
	}
	
	//显示系统的短息信息
	@SuppressWarnings({ "deprecation" })
	private List<CrankSmsBean> readAllSMS() {
		Cursor cursor = managedQuery(SMS_INBOX, new String[] { "address",
				"body" }, null, null, null);

		List<CrankSmsBean> list2 = new ArrayList<CrankSmsBean>();

		if (cursor.moveToFirst()) {

			int addrIdx = cursor.getColumnIndex("address");
			int bodyIdx = cursor.getColumnIndex("body");
			do {
				String addr = cursor.getString(addrIdx);
				String body = cursor.getString(bodyIdx);
				CrankSmsBean bean = new CrankSmsBean();
				bean.setPhoto(addr);
				bean.setBody(body);
				list2.add(bean);
			} while (cursor.moveToNext());
		}
		return list2;
	}
	
	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.cranksms_addfromsms_return:
			finish();
			break;
		}
		
	}

}
