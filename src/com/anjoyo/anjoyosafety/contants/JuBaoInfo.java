package com.anjoyo.anjoyosafety.contants;

import android.graphics.drawable.Drawable;

public class JuBaoInfo {
	public Drawable appIcon=null;
	public Drawable Icon=null;
	public String appName="";
	public boolean Checked;
	public boolean isJuBao;
	
	public Drawable getIcon() {
		return Icon;
	}
	public void setIcon(Drawable icon) {
		Icon = icon;
	}
	public boolean isJuBao() {
		return isJuBao;
	}
	public void setJuBao(boolean isJuBao) {
		this.isJuBao = isJuBao;
	}
	public boolean isChecked() {
		return Checked;
	}
	public void setChecked(boolean checked) {
		Checked = checked;
	}
	public Drawable getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
