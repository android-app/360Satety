package com.anjoyo.anjoyosafety.activity;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.anjoyo.anjoyosafety.adapter.CrankSmsAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.bean.CrankSmsBean;
import com.anjoyo.anjoyosafety.dbhelper.SmsDbHelper;
import com.anjoyo.anjoyosatety.activity.R;

/**
 * 
 * ������������ʾϵͳ�����Լ��ٱ�����
 * 
 * **/
public class Crank_Sms_Report extends MyBaseActivity implements
		OnClickListener, OnItemClickListener {

	Button smsreturn;
	TextView smsreport;
	TextView allItem;
	ListView listshowsms;
	SmsDbHelper helper;
	
	// ��ʾȫ����Ϣ��listview
	List<CrankSmsBean> list;

	// �����ռ����URI
	public Uri SMS_INBOX = Uri.parse("content://sms/inbox");
	public CrankSmsAdapter adapter;

	@Override
	protected void setContentView() {
		setContentView(R.layout.crank_showsms);

	}

	@Override
	protected void findViewById() {
		smsreturn = (Button) findViewById(R.id.crank_sms_return);
		smsreturn.setOnClickListener(this);
		smsreport = (TextView) findViewById(R.id.crank_sms_report);
		smsreport.setOnClickListener(this);
		allItem = (TextView) findViewById(R.id.crank_sms_allsms);
		allItem.setOnClickListener(this);
		listshowsms = (ListView) findViewById(R.id.crank_sms_listview2);
		listshowsms.setOnItemClickListener(this);
	}

	@Override
	protected void controll() {
		helper = new SmsDbHelper(this);
		list = readAllSMS();
		adapter = new CrankSmsAdapter(this, list);
		listshowsms.setAdapter(adapter);

	}

	// ���ϵͳ���Ų�������ʾ���ж�Ϣlist��
	@SuppressWarnings("deprecation")
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

	// ��ϵͳ��Ϣ����뵽sms����
	void getsmslist() {
		
		SQLiteDatabase db = helper.getWritableDatabase();

		for (int i = 0; i < list.size(); i++) {
			boolean b = list.get(i).getFlag();
			if (!b) {
				String content = list.get(i).getBody();
				String photonum = list.get(i).getPhoto();
				ContentValues values = new ContentValues();
				values.put("photonum", photonum);
				values.put("content", content);
				db.insert("sms", null, values);
				Crank_Tabhost_1.showlist.add(list.get(i));
			}
		}
		Crank_Tabhost_1.adapter.notifyDataSetChanged();
		db.close();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// ����
		case R.id.crank_sms_return:
			finish();
			break;
		// �ٱ�
		case R.id.crank_sms_report:
			// ����ٱ������
			getsmslist();
			// ɾ��ϵͳ��Ϣ
			finish();
			break;
		// ȫѡ
		case R.id.crank_sms_allsms:
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setFlag(false);
			}
			adapter.notifyDataSetChanged();
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		CrankSmsBean b = (CrankSmsBean) arg0.getAdapter().getItem(arg2);

		if (b.getFlag()) {
			list.get(arg2).setFlag(false);
		} else {
			list.get(arg2).setFlag(true);
		}
		adapter.notifyDataSetChanged();

	}
}
