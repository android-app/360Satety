package com.anjoyo.anjoyosafety.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.anjoyo.anjoyosafety.contants.AppInfo;
import com.anjoyo.anjoyosafety.contants.FindAppInfo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
//查找应用图标界面的Gallery显示
public class FindAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<FindAppInfo> mList;

	public FindAdapter(Context context,
			 ArrayList<FindAppInfo> mList) {

		this.mContext = context;
		this.mList = mList;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FindAppInfo info=mList.get(position);
		ImageView imageView = null;
			imageView = new ImageView(mContext);
			imageView.setScaleType(ScaleType.FIT_XY);
			Gallery.LayoutParams galleryParams = new Gallery.LayoutParams(
					100, 100);
			imageView.setLayoutParams(galleryParams);
			imageView.setImageDrawable(info.getAppIcon());
		return imageView;
	}
}

