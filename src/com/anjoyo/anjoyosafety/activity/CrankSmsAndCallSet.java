package com.anjoyo.anjoyosafety.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

/**
 * 设置界面
 * 
 **/
public class CrankSmsAndCallSet extends MyBaseActivity implements OnClickListener {

	TextView setreturn;
	RelativeLayout black;
	RelativeLayout write;
	RelativeLayout model;
	RelativeLayout settime;
	protected void setContentView() {
		setContentView(R.layout.cranksmsandcall_set);

	}

	@Override
	protected void findViewById() {
		setreturn = (TextView) findViewById(R.id.crankset_return);
		setreturn.setOnClickListener(this);
		black = (RelativeLayout) findViewById(R.id.crankset_black);
		black.setOnClickListener(this);
		write = (RelativeLayout) findViewById(R.id.crankset_write);
		write.setOnClickListener(this);
		model =(RelativeLayout) findViewById(R.id.crankset_crankyesorn3);
		model.setOnClickListener(this);
		settime = (RelativeLayout) findViewById(R.id.crankset_crankyesorno4);
		settime.setOnClickListener(this);
	}

	@Override
	protected void controll() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 返回
		case R.id.crankset_return:
			finish();
			break;
		// 跳转到黑名单
		case R.id.crankset_black:
			Intent intent = new Intent(this,CrankSmsAndCallBlackList.class);
			startActivity(intent);
			break;
		// 跳转到白名单
		case R.id.crankset_write:
			Intent intent1 = new Intent(this,CrankSmsAndCallWhiteList.class);
			startActivity(intent1);
			break;
			//模式
		case R.id.crankset_crankyesorn3:
			Intent intent2 = new Intent(this,Crank_DiffModel.class);
			startActivity(intent2);
			break;
		case R.id.crankset_crankyesorno4:
			Intent intent3 = new Intent(this,CrankSmsAndCallWhiteList.class);
			startActivity(intent3);
			break;
			
			
		}
	}

}
