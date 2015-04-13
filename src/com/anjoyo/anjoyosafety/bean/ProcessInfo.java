package com.anjoyo.anjoyosafety.bean;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Debug.MemoryInfo;


public class ProcessInfo {

	private int id;
	private String appLabel;    //应用程序标签
	private Drawable appIcon ;  //应用程序图像
	private long memSize; // 进程占用的内存大小
	public boolean Checked;
	public String pkgname ; 
	private ActivityManager.RunningAppProcessInfo runinfo = null;
	public ProcessInfo(Context context,ActivityManager.RunningAppProcessInfo runinfo){
		this.runinfo=runinfo;
	}
	

	public String getAppLabel() {
		return appLabel;
	}

	public void setAppLabel(String appLabel) {
		this.appLabel = appLabel;
	}

	public Drawable getAppIcon() {
		return appIcon;
	}

	
	public boolean isChecked() {
		return Checked;
	}


	public void setChecked(boolean checked) {
		Checked = checked;
	}


	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}

	public long getMemSize() {
		return memSize;
	}

	public void setMemSize(long size) {
		this.memSize = size;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}

	

}
