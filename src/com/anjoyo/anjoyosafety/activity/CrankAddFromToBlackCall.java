package com.anjoyo.anjoyosafety.activity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjoyo.anjoyosafety.adapter.CrankToBlackCallAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.bean.BlacklistcallBean;
import com.anjoyo.anjoyosafety.dbhelper.BlackListHelper;
import com.anjoyo.anjoyosatety.activity.R;

public class CrankAddFromToBlackCall extends MyBaseActivity implements OnClickListener{
	TextView tv ;
	TextView tbt;
	Button mbtn;
	ListView list;
	List<BlacklistcallBean> data;
	CrankToBlackCallAdapter adapter;
	BlackListHelper helper;
	@Override
	protected void setContentView() {
		setContentView(R.layout.cranksms_addfromsms);
	}
	
	@Override
	protected void findViewById() {
		tv=(TextView) findViewById(R.id.cranksms_addfromsms_tv1);
		tv.setText("从通话记录添加");
		mbtn=(Button) findViewById(R.id.cranksms_addfromsms_return);
		mbtn.setOnClickListener(this);
		tbt = (TextView) findViewById(R.id.blacklist_addfromsms_ture);
		tbt.setOnClickListener(this);
		list=  (ListView) findViewById(R.id.crank_sms_listview2);
	}
	
	@Override
	protected void controll() {
		data = readAllPhonenumber();
		adapter = new CrankToBlackCallAdapter(CrankAddFromToBlackCall.this, data);
		list.setAdapter(adapter);
		helper = new BlackListHelper(CrankAddFromToBlackCall.this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cranksms_addfromsms_return:
			finish();
			break;
		case R.id.blacklist_addfromsms_ture:
			Toast.makeText(this, "a", 0).show();
			break;
		}
	
		
	}
	
	
	public List<BlacklistcallBean> readAllPhonenumber() {
		ContentResolver cr = getContentResolver();
		final Cursor cursor = cr.query(CallLog.Calls.CONTENT_URI, new String[] {
				CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,
				CallLog.Calls.TYPE, CallLog.Calls.DATE }, null, null,
				CallLog.Calls.DEFAULT_SORT_ORDER);
		List<BlacklistcallBean> list = new ArrayList<BlacklistcallBean>();
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			String strNumber = cursor.getString(0); // 呼叫号码
			String strName = cursor.getString(1); // 联系人姓名
			int type = cursor.getInt(2); // 来电:1,拨出:2,未接:3
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(Long.parseLong(cursor.getString(3)));
			String time = sfd.format(date);
			BlacklistcallBean bean = new BlacklistcallBean();
			bean.setName(strName);
			bean.setNumber(strNumber);
			bean.setTime(time);
			bean.setType(type);
			list.add(bean);
		}
		return list;
	}

}
