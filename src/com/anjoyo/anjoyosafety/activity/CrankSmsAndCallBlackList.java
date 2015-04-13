package com.anjoyo.anjoyosafety.activity;

import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

/**
 * 
 * 黑名单
 * 
 * **/
public class CrankSmsAndCallBlackList extends MyBaseActivity implements
		OnClickListener {

	TextView blreturn;
	TextView addblacklist;

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.cranksmsandcall_blacklist);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		blreturn = (TextView) findViewById(R.id.blacklist_return);
		blreturn.setOnClickListener(this);
		addblacklist = (TextView) findViewById(R.id.addblacklist);
		addblacklist.setOnClickListener(this);
	}

	@Override
	protected void controll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.blacklist_return:
			finish();
			break;
		// 添加黑名单按钮
		case R.id.addblacklist:
			getwhereto();
			break;
		}

	}

	void getwhereto() {
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.show();
		Window window = dialog.getWindow();
		window.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		window.setContentView(R.layout.crank_fromwheretoblacklist);
		TextView smsadd = (TextView) window
				.findViewById(R.id.crank_addsmstoblacklist);
		TextView calladd = (TextView) window
				.findViewById(R.id.crank_addcalltoblacklist);
		smsadd.setOnClickListener(new OnClickListener() {
			// 从sms中添加黑名单
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CrankSmsAndCallBlackList.this,CrankAddFromToBlackSms.class);
				startActivity(intent);
				dialog.dismiss();
				
			}
		});
		// 从call中添加黑名单
		calladd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CrankSmsAndCallBlackList.this,CrankAddFromToBlackCall.class);
				startActivity(intent);
				dialog.dismiss();
				
			}
		});

	}

}
