package com.anjoyo.anjoyosafety.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyViewPage extends PagerAdapter{
	public List<View> listvp;
	public MyViewPage(List<View> listvp){
		this.listvp=listvp;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listvp.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		// TODO Auto-generated method stub
		return view==obj;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
		container.removeView(listvp.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(listvp.get(position));
		return listvp.get(position);
	}
}
