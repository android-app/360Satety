package com.anjoyo.anjoyosafety.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;
//二维码设置界面
public class CodeSetActivity extends MyBaseActivity implements OnClickListener{
private Button code_setting_fanhui;
	@Override
	protected void controll() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		code_setting_fanhui=(Button) findViewById(R.id.code_setting_fanhui);
		code_setting_fanhui.setOnClickListener(this);
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.code_setting);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.code_setting_fanhui:
			finish();
			break;

		default:
			break;
		}
	}

}
