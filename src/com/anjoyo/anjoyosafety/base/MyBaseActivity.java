package com.anjoyo.anjoyosafety.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class MyBaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView();
		findViewById();
		controll();
	}

	protected abstract void setContentView();

	protected abstract void findViewById();

	protected abstract void controll();

}
