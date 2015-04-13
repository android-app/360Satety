package com.anjoyo.anjoyosafety.activity;

import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class CrankSmsAndCallWhiteList extends MyBaseActivity implements OnClickListener{

	TextView wlreturn;
	TextView addwhitelist;
	@Override
	protected void setContentView() {
		setContentView(R.layout.cranksmsandcall_whitelist);
		
	}
	@Override
	protected void findViewById() {
		wlreturn = (TextView) findViewById(R.id.whitelist_return);
		wlreturn.setOnClickListener(this);
		addwhitelist=(TextView) findViewById(R.id.addwhitelist);
		addwhitelist.setOnClickListener(this);
	}
	@Override
	protected void controll() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.whitelist_return:
			finish();
			break;
		case R.id.addwhitelist:
			getwheretowhitelist();
			
			
			
			break;
			
		}
		
	}
	void getwheretowhitelist() {
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.show();
		Window window = dialog.getWindow();
		window.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		window.setContentView(R.layout.crank_fromwheretoblacklist);
		TextView showtowhite =(TextView) window.findViewById(R.id.crank_to_showtv);
		showtowhite.setText("添加到白名单");
		TextView smsadd = (TextView) window
				.findViewById(R.id.crank_addsmstoblacklist);
		TextView calladd = (TextView) window
				.findViewById(R.id.crank_addcalltoblacklist);
		smsadd.setOnClickListener(new OnClickListener() {
			// 从sms中添加白名单
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CrankSmsAndCallWhiteList.this,CrankAddFromToWhiteSms.class);
				startActivity(intent);
				Toast.makeText(CrankSmsAndCallWhiteList.this, "哈哈", 0).show();
			}
		});
		// 从call中添加白名单
		calladd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CrankSmsAndCallWhiteList.this,CrankAddFromToWhiteCall.class);
				startActivity(intent);
				Toast.makeText(CrankSmsAndCallWhiteList.this, "嘿嘿", 0).show();
			}
		});

	}


}
