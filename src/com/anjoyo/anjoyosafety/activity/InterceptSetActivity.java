package com.anjoyo.anjoyosafety.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;
//广告设置界面
public class InterceptSetActivity extends MyBaseActivity implements
		OnClickListener {
	private RelativeLayout ad_setting_rl7;
	private Button ad_setting_fanhui;
	@Override
	protected void controll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		ad_setting_rl7 = (RelativeLayout) findViewById(R.id.ad_setting_rl7);
		ad_setting_rl7.setOnClickListener(this);
		ad_setting_fanhui = (Button) findViewById(R.id.ad_setting_fanhui);
		ad_setting_fanhui.setOnClickListener(this);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.ad_setting);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.ad_setting_fanhui:
			finish();
			break;
		case R.id.ad_setting_rl7:
			Intent intent=new Intent(this, InterceptNoteActivity.class);
			startActivity(intent);
			break;

		
		}
	}

}
