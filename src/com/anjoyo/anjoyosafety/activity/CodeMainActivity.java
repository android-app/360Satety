package com.anjoyo.anjoyosafety.activity;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.camera.CameraManager;
import com.anjoyo.anjoyosafety.custom.ViewfinderView;
import com.anjoyo.anjoyosafety.decoding.CaptureActivityHandler;
import com.anjoyo.anjoyosafety.decoding.InactivityTimer;
import com.anjoyo.anjoyosafety.decoding.RGBLuminanceSource;
import com.anjoyo.anjoyosatety.activity.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CodeMainActivity extends MyBaseActivity implements Callback ,OnClickListener{


	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	public static final int REQUEST_CODE=1;
	public static final int PARSE_BARCODE_SUC=2;
	public static final int PARSE_BARCODE_FAIL=3;
	private String photo_path;
	private TextView txtResult;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private LinearLayout codemain_photo;
	private Button codemain_set,codemain_fanhui;
	private Bitmap scanBitmap;
	ProgressDialog mProgress;
	private ProgressBar pg;
	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	public void handleDecode(Result obj, Bitmap barcode) {
		inactivityTimer.onActivity();
		viewfinderView.drawResultBitmap(barcode);
		playBeepSoundAndVibrate();
		txtResult.setText(obj.getBarcodeFormat().toString() + ":"
				+ obj.getText());
		
		/*if (obj.getBarcodeFormat().toString().equals("QR_CODE")) {
			Uri uri = Uri.parse(obj.getText());
			Intent intent=new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			txtResult.setText("");
		}*/
	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{ if(resultCode == RESULT_OK){  
		switch(requestCode){  
		case REQUEST_CODE:  
			//获取选中图片的路径  
			Cursor cursor = getContentResolver().query(data.getData(), null, null, null, null);  
			if (cursor.moveToFirst()) {  
				photo_path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));  

			}  
			cursor.close(); 

			mProgress = new ProgressDialog(CodeMainActivity.this);  
			mProgress.setMessage("正在扫描...");  
			mProgress.setCancelable(false);  
			mProgress.show();  

			new Thread(new Runnable() {  
				@Override  
				public void run() {  
					Result result = scanningImage(photo_path);  
					if (result != null) {  
						Message m = mHandler.obtainMessage();  
						m.what = PARSE_BARCODE_SUC;  
						m.obj = result.getText();  
						mHandler.sendMessage(m); 
						mProgress.dismiss();
					} else {  
						Message m = mHandler.obtainMessage();  
						m.what = PARSE_BARCODE_FAIL;  
						m.obj = "Scan failed!";  
						mHandler.sendMessage(m);  
						mProgress.dismiss();
					}  

				}  
			}).start();  

			break;  
		}
	}
	}
	Handler mHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case PARSE_BARCODE_SUC:
				String str1=msg.obj.toString();
				txtResult.setText(str1);
				Uri uri = Uri.parse(str1);
				Intent intent=new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				break;
			case PARSE_BARCODE_FAIL:
				String str2=msg.obj.toString();
				txtResult.setText(str2);
				break;



			}
		}
	};
	//解析手机相册图片的二维码
	public Result scanningImage(String path) {  
		if(TextUtils.isEmpty(path)){  
			return null;  
		}  
		Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();  
		hints.put(DecodeHintType.CHARACTER_SET, "UTF8"); //设置二维码内容的编码  

		BitmapFactory.Options options = new BitmapFactory.Options();  
		options.inJustDecodeBounds = true; // 先获取原大小  
		scanBitmap = BitmapFactory.decodeFile(path, options);  
		options.inJustDecodeBounds = false; // 获取新的大小  
		int sampleSize = (int) (options.outHeight / (float) 200);  
		if (sampleSize <= 0)  
			sampleSize = 1;  
		options.inSampleSize = sampleSize;  
		scanBitmap = BitmapFactory.decodeFile(path, options);  
		RGBLuminanceSource source = new RGBLuminanceSource(scanBitmap);  
		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));  
		QRCodeReader reader = new QRCodeReader();  
		try {  
			return reader.decode(bitmap1, hints);  

		} catch (Exception e) {  
			e.printStackTrace();  
		} 
		return null;  
	}  
	@Override
	protected void controll() {
		// TODO Auto-generated method stub
		CameraManager.init(getApplication());
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}



	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		pg = (ProgressBar) findViewById(R.id.codemain_pb);
		codemain_photo = (LinearLayout) findViewById(R.id.codemain_photo);
		codemain_set = (Button) findViewById(R.id.codemain_set);
		codemain_fanhui = (Button) findViewById(R.id.codemain_fanhui);
		codemain_set.setOnClickListener(this);
		codemain_fanhui.setOnClickListener(this);
		codemain_photo.setOnClickListener(this);
		txtResult = (TextView) findViewById(R.id.txtResult);	
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.codemain);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.codemain_set:
			Intent intent =new Intent(this, CodeSetActivity.class);
			startActivity(intent);
			break;
		case R.id.codemain_fanhui:
			finish();
			break;
		case R.id.codemain_photo:
			Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT); //"android.intent.action.GET_CONTENT"  
			innerIntent.setType("image/*");  
			Intent wrapperIntent = Intent.createChooser(innerIntent, "选择二维码图片");  
			this.startActivityForResult(wrapperIntent, REQUEST_CODE);
			break;

		}
	}

}