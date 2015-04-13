package com.anjoyo.anjoyosafety.adapter;

import java.util.List;

import com.anjoyo.anjoyosafety.bean.ViewPagerBean1;
import com.anjoyo.anjoyosafety.bean.ViewPagerBean2;
import com.anjoyo.anjoyosafety.contants.MainFinal;
import com.anjoyo.anjoyosafetymain.MainActivity;
import com.anjoyo.anjoyosatety.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainGridViewAdapter2 extends BaseAdapter{
	private List<ViewPagerBean2> applist;
	private int holdPosition; //
	private boolean isChanged = false; //是否变化
	private boolean ShowItem = false; //显示item
	private LayoutInflater inflater;
	public MainGridViewAdapter2(MainActivity c)  
	{  
		this.inflater=LayoutInflater.from(c);
		this.applist=MainFinal.getbeans2();
	}  


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return applist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return applist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {



		convertView=inflater.inflate(R.layout.main_gridview_item, null);

		TextView	tv=(TextView) convertView.findViewById(R.id.main_gridview_item_name);
		ImageView	iv=(ImageView) convertView.findViewById(R.id.main_gridview_src);
		tv.setText(applist.get(position).getName());
		iv.setBackgroundResource(applist.get(position).getId());
		ViewPagerBean2 bean=applist.get(position);
		if(isChanged){
			if(position==holdPosition){
				if(!ShowItem){
					convertView.setVisibility(View.VISIBLE);
				}
			}
		}
		return convertView;
	}

	public void exchange(int startPosition, int endPosition){
		Object start_obj=getItem(startPosition);
		holdPosition=endPosition;
		if(startPosition<endPosition){
			applist.add(endPosition+1,(ViewPagerBean2)start_obj);
			applist.remove(startPosition);
		}else{
			applist.add(endPosition, (ViewPagerBean2)start_obj);
			applist.remove(startPosition+1);
		}
		isChanged=true;
		notifyDataSetChanged();
	}


	public void showDropItem(boolean b) {
		// TODO Auto-generated method stub
		this.ShowItem=b;
	}
}
