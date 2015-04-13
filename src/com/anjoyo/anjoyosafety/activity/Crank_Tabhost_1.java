package com.anjoyo.anjoyosafety.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.anjoyo.anjoyosafety.adapter.CrankReportSmsAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.bean.CrankSmsBean;
import com.anjoyo.anjoyosafety.dbhelper.SmsDbHelper;
import com.anjoyo.anjoyosafety.util.WeiBoUtil;
import com.anjoyo.anjoyosatety.activity.R;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;


public class Crank_Tabhost_1 extends MyBaseActivity implements OnClickListener,
		OnItemClickListener, OnItemLongClickListener {

	ImageView btn;
	public static ListView listShowSms;
	SmsDbHelper helper;
	public static CrankReportSmsAdapter adapter;
	public static List<CrankSmsBean> showlist;
	public static FrameLayout misslayout;
	String body;
	CrankSmsBean bean;
	private static String appKey = "1201533495";
	@Override
	protected void setContentView() {
		setContentView(R.layout.cranksmsandcall_frament_tab1);
	}

	@Override
	protected void findViewById() {
		btn = (ImageView) findViewById(R.id.crank_sms_imageview);
		btn.setOnClickListener(this);
		listShowSms = (ListView) findViewById(R.id.crank_sms_listview);
		listShowSms.setOnItemClickListener(this);
		listShowSms.setOnItemLongClickListener(this);
		misslayout = (FrameLayout) findViewById(R.id.crank_sms_missframlayout);

	}

	@Override
	protected void controll() {
		showlist = showlist();
        adapter = new CrankReportSmsAdapter(this, showlist);
        listShowSms.setAdapter(adapter);
      

	}

	// 显示举报短息
	public List<CrankSmsBean> showlist() {
		helper = new SmsDbHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		// 删除操作方便修改
		// db.delete("sms", null, null);
		Cursor cursor = db.query("sms", null, null, null, null, null, null);
		List<CrankSmsBean> list = new ArrayList<CrankSmsBean>();
		if (cursor.moveToFirst()) {
			int addrIdx = cursor.getColumnIndex("photonum");
			int bodyIdx = cursor.getColumnIndex("content");
			do {
				String addr = cursor.getString(addrIdx);
				body = cursor.getString(bodyIdx);
				bean = new CrankSmsBean();
				bean.setPhoto(addr);
				bean.setBody(body);
				list.add(bean);
			} while (cursor.moveToNext());
		}

		return list;
	}

	// 点击图片的单击事件
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, Crank_Sms_Report.class);
		startActivity(intent);
	}

	// 隐藏图片

	// 显示pop
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
		View contentView = View.inflate(Crank_Tabhost_1.this,R.layout.crank_sms_popwindow, null);
		RelativeLayout dele = (RelativeLayout) contentView.findViewById(R.id.crank_sms_dele);
		final PopupWindow pWindow = new PopupWindow(contentView,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		pWindow.setFocusable(true);
		Bitmap b = null;
		pWindow.setBackgroundDrawable(new BitmapDrawable(b));
		pWindow.setOutsideTouchable(true);
		pWindow.showAsDropDown(arg1);
		//删除
		dele.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				helper = new SmsDbHelper(Crank_Tabhost_1.this);
				SQLiteDatabase db = helper.getReadableDatabase();

				db.delete("sms", "content=?", new String[] { showlist.get(arg2)
						.getBody() });
				showlist.remove(arg2);
				adapter.notifyDataSetChanged();
				pWindow.dismiss();
			}
		});
		//分享
		RelativeLayout weibo = (RelativeLayout)contentView.findViewById(R.id.crank_sms_weibo);
		weibo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				WeiBoUtil.shareText(Crank_Tabhost_1.this,appKey);

				}

			

		});
		
		
	}

	// 长点击
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
			final int arg2, long arg3) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("删除数据");
		dialog.setMessage("确定删除本条数据?");
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			// 删除数据
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				helper = new SmsDbHelper(Crank_Tabhost_1.this);
				SQLiteDatabase db = helper.getReadableDatabase();

				db.delete("sms", "content=?", new String[] { showlist.get(arg2)
						.getBody() });
				showlist.remove(arg2);
				adapter.notifyDataSetChanged();
				Toast.makeText(Crank_Tabhost_1.this, "删除成功", 1).show();
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		dialog.create();
		dialog.show();

		return false;
	}

	public static void misslayout() {
		
		misslayout.setVisibility(View.VISIBLE);
		listShowSms.setVisibility(View.GONE);
		
		
	}

	public static void listShowSms() {
		
		listShowSms.setVisibility(View.VISIBLE);
		misslayout.setVisibility(View.GONE);
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		 
	}
}