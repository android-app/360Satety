package com.anjoyo.anjoyosafety.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class Clear_Timing extends MyBaseActivity implements OnClickListener{

	Button btn_return;
	@Override
	protected void setContentView() {
		setContentView(R.layout.clear_timing);
		
	}

	@Override
	protected void findViewById() {
		btn_return=(Button) findViewById(R.id.clear_timing_back);
		
	}

	@Override
	protected void controll() {
		btn_return.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clear_timing_back:
			finish();
			break;

		}
		
	}

}
