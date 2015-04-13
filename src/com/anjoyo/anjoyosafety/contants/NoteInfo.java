package com.anjoyo.anjoyosafety.contants;

import android.graphics.drawable.Drawable;

public class NoteInfo {
	public String appName="";
	public Drawable appIcon=null;
	public long time;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Drawable getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
}
