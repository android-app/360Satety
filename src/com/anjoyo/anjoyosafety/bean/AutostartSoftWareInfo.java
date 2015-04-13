package com.anjoyo.anjoyosafety.bean;

import android.graphics.drawable.Drawable;

public class AutostartSoftWareInfo {

	private String app_name;
	private Drawable app_Icon ;
	private boolean isChecked;
	
    public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public Drawable getApp_Icon() {
		return app_Icon;
	}
	public void setApp_Icon(Drawable app_Icon) {
		this.app_Icon = app_Icon;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
}
