package com.anjoyo.anjoyosafety.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class CrankCall_Tag extends MyBaseActivity implements OnClickListener{

	@Override
	protected void setContentView() {
		setContentView(R.layout.crankcall_tag);
		
	}

	@Override
	protected void findViewById() {
		Button mreturn =(Button) findViewById(R.id.crankcall_tag_return);
		mreturn.setOnClickListener(this);
	}

	@Override
	protected void controll() {
		
		
	}

	@Override
	public void onClick(View v) {
		finish();
		
	}

}
