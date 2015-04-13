package com.anjoyo.anjoyosafety.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import com.anjoyo.anjoyosafety.adapter.InterceptNoteAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;
public class InterceptNoteActivity extends MyBaseActivity implements OnClickListener{
	private InterceptNoteAdapter adapter;
	private ListView listView;
	private ImageView ad_note_xuanzhuan;
    private Button ad_note_delete,ad_note_dialog_ok,ad_note_dialog_no,ad_note_fanhui;
	@Override
	protected void controll() {
		// TODO Auto-generated method stub
		
			adapter = new InterceptNoteAdapter(this, InterceptActivity.appList);
			listView.setAdapter(adapter);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.ad_note_lv);
		ad_note_xuanzhuan = (ImageView) findViewById(R.id.ad_note_xuanzhuan);
		ad_note_fanhui = (Button) findViewById(R.id.ad_note_fanhui);
		ad_note_fanhui.setOnClickListener(this);
		ad_note_delete = (Button) findViewById(R.id.ad_note_delete);
		ad_note_delete.setOnClickListener(this);
		
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ad_note);
	}
Handler handler=new Handler(){

	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case 1:
				stopAnim();
			break;
	}
	}
};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ad_note_delete:
			final Dialog dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.show();
			Window window = dialog.getWindow();
			window.setContentView(R.layout.ad_note_dialog);	
			ad_note_dialog_ok=(Button)window.findViewById(R.id.ad_note_dialog_ok);
			ad_note_dialog_no=(Button)window.findViewById(R.id.ad_note_dialog_no);
			ad_note_dialog_ok.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					listView.setVisibility(View.GONE);
					ad_note_xuanzhuan.setVisibility(View.VISIBLE);
					startAnim();
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							handler.sendEmptyMessageDelayed(1, 500);
						}
					}).start();
				}
			});
			ad_note_dialog_no.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			break;

		case R.id.ad_note_fanhui:
			finish();
			break;
		}
	}
	private void startAnim() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.train_xuanzhuan);
		ad_note_xuanzhuan.startAnimation(anim);
	}

	private void stopAnim() {
		ad_note_xuanzhuan.clearAnimation();
		ad_note_xuanzhuan.setVisibility(View.GONE);
	}
}
