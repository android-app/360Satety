package com.anjoyo.anjoyosafety.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.anjoyo.anjoyosafety.activity.PersonalSpaceSettingPwdActivity;
import com.anjoyo.anjoyosafety.contants.FinalConstants;
import com.anjoyo.anjoyosafety.util.BitmapUtil;
import com.anjoyo.anjoyosafety.util.MathUtil;
import com.anjoyo.anjoyosafety.util.Point;
import com.anjoyo.anjoyosafety.util.RoundUtil;
import com.anjoyo.anjoyosafety.util.StringUtil;
import com.anjoyo.anjoyosatety.activity.R;

/**
 * 
 * �켣����
 * 
 * @author Dell
 * 
 */
public class LocusPassWordView extends View 
{
	private float w = 0;
	private float h = 0;

	//
	private boolean isCache = false;
	//
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	//
	public Point[][] mPoints = new Point[3][3];
	// Բ�İ뾶
	private float r = 0;
	// ѡ�еĵ�
	private List<Point> sPoints = new ArrayList<Point>();
	private boolean checking = false;
	private Bitmap locus_round_original;
	private Bitmap locus_round_click;
	private Bitmap locus_round_click_error;
	private Bitmap locus_line;
	private Bitmap locus_line_semicircle;
	private Bitmap locus_line_semicircle_error;
	private Bitmap locus_arrow;
	private Bitmap locus_line_error;
	private long CLEAR_TIME = 500;
	private int passwordMinLength = 4;
	private boolean isTouch = true; // �Ƿ�ɲ���
	private Matrix mMatrix = new Matrix();
	private int lineAlpha = 100;
	
	public boolean flag = false;

	public LocusPassWordView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public LocusPassWordView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LocusPassWordView(Context context) {
		super(context);
	}
	
	@Override
	public void onDraw(Canvas canvas) 
	{
		if (!isCache) 
		{
			initCache();
		}
		if(flag)
		{
			PersonalSpaceSettingPwdActivity.handler.sendEmptyMessage(1);
			flag = false;
		}
		drawToCanvas(canvas);
	}

	private void drawToCanvas(Canvas canvas) 
	{
		// mPaint.setColor(Color.RED);
		// Point p1 = mPoints[1][1];
		// Rect r1 = new Rect(p1.x - r,p1.y - r,p1.x +
		// locus_round_click.getWidth() - r,p1.y+locus_round_click.getHeight()-
		// r);
		// canvas.drawRect(r1, mPaint);
		// �����е�
		for (int i = 0; i < mPoints.length; i++) 
		{
			for (int j = 0; j < mPoints[i].length; j++) 
			{
				Point p = mPoints[i][j];
				if (p.state == Point.STATE_CHECK) 
				{
//					��ָ������ƴ�Դλͼ�л�ȡһ��
					canvas.drawBitmap(locus_round_click, p.x - r, p.y - r,mPaint);
				} else if (p.state == Point.STATE_CHECK_ERROR)
				{
					canvas.drawBitmap(locus_round_click_error, p.x - r,
							p.y - r, mPaint);
				} else
				{
					canvas.drawBitmap(locus_round_original, p.x - r, p.y - r,
							mPaint);
				}
			}
		}
		// mPaint.setColor(Color.BLUE);
		// canvas.drawLine(r1.left+r1.width()/2, r1.top, r1.left+r1.width()/2,
		// r1.bottom, mPaint);
		// canvas.drawLine(r1.left, r1.top+r1.height()/2, r1.right,
		// r1.bottom-r1.height()/2, mPaint);

		// ������
		if (sPoints.size() > 0) 
		{
			int tmpAlpha = mPaint.getAlpha();
			//����������͸����
			mPaint.setAlpha(lineAlpha);
			Point tp = sPoints.get(0);
			//����֮�������
			for (int i = 1; i < sPoints.size(); i++) 
			{
				Point p = sPoints.get(i);
				drawLine(canvas, tp, p);
				tp = p;
			}
			//�ƶ������л���
			if (this.movingNoPoint) 
			{
				drawLine(canvas, tp, new Point((int) moveingX, (int) moveingY));
			}
			mPaint.setAlpha(tmpAlpha);
			lineAlpha = mPaint.getAlpha();
		}

	}

	/**
	 * ��ʼ��Cache��Ϣ
	 * �ս�ȥ�ĳ�ʼ��ÿ�������Ϣ�����꣬��С
	 * @param canvas
	 */
	private void initCache() 
	{
		//��ȡ��ǰView�Ŀ��
		w = this.getWidth();
		h = this.getHeight();
		
		float x = 0;
		float y = 0;
		
		// ����С��Ϊ׼
		// ����
		if (w > h)
		{
			x = (w - h) / 2;
			w = h;
		}
		// ����
		else 
		{
			y = (h - w) / 2;
			h = w;
		}
		
		//��ʼ����Чλͼ
		locus_round_original = convertDrawable2BitmapByCanvas(this.getResources().getDrawable(R.drawable.drawpoint_nomal));
		locus_round_click = convertDrawable2BitmapByCanvas(this.getResources().getDrawable(R.drawable.drawpoint_press));
		locus_round_click_error = convertDrawable2BitmapByCanvas(this.getResources().getDrawable(R.drawable.drawpoint_error));

		locus_line = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.locus_line);
		locus_line_semicircle = BitmapFactory.decodeResource(
				this.getResources(), R.drawable.locus_line_semicircle);

		locus_line_error = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.locus_line_error);
		locus_line_semicircle_error = BitmapFactory.decodeResource(
				this.getResources(), R.drawable.locus_line_semicircle_error);

		locus_arrow = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.privacy_indicator_code_lock_drag_direction_green_up);

		// ����ԲȦͼƬ�Ĵ�С
		float canvasMinW = w;
		if (w > h)
		{
			canvasMinW = h;
		}
		//���ķ�
		float roundMinW = canvasMinW / 8.0f * 2;
		//��8��
		float roundW = roundMinW / 2.f;
		//????
		float deviation = canvasMinW % (8 * 2) / 2;
		x += deviation;
		x += deviation;
		
		//����ͼƬ����View�ı���
		if (locus_round_original.getWidth() > roundMinW) 
		{
			float sf = roundMinW * 1.0f / locus_round_original.getWidth(); // ȡ�����ű����������е�ͼƬ��������
			locus_round_original = BitmapUtil.zoom(locus_round_original, sf);
			locus_round_click = BitmapUtil.zoom(locus_round_click, sf);
			locus_round_click_error = BitmapUtil.zoom(locus_round_click_error,
					sf);

			locus_line = BitmapUtil.zoom(locus_line, sf);
			locus_line_semicircle = BitmapUtil.zoom(locus_line_semicircle, sf);

			locus_line_error = BitmapUtil.zoom(locus_line_error, sf);
			locus_line_semicircle_error = BitmapUtil.zoom(
					locus_line_semicircle_error, sf);
			locus_arrow = BitmapUtil.zoom(locus_arrow, sf);
			
			roundW = locus_round_original.getWidth() / 2;
		}
		
		//��¼ÿ���������
		mPoints[0][0] = new Point(x + 0 + roundW, y + 0 + roundW);
		mPoints[0][1] = new Point(x + w / 2, y + 0 + roundW);
		mPoints[0][2] = new Point(x + w - roundW, y + 0 + roundW);
		mPoints[1][0] = new Point(x + 0 + roundW, y + h / 2);
		mPoints[1][1] = new Point(x + w / 2, y + h / 2);
		mPoints[1][2] = new Point(x + w - roundW, y + h / 2);
		mPoints[2][0] = new Point(x + 0 + roundW, y + h - roundW);
		mPoints[2][1] = new Point(x + w / 2, y + h - roundW);
		mPoints[2][2] = new Point(x + w - roundW, y + h - roundW);
		int k = 0;
		for (Point[] ps : mPoints) 
		{
			for (Point p : ps) 
			{
				p.index = k;
				k++;
			}
		}
		
		r = locus_round_original.getHeight() / 2;// roundW;
		isCache = true;
	}

	/**
	 * �����������
	 * 
	 * @param canvas
	 * @param a
	 * @param b
	 */
	private void drawLine(Canvas canvas, Point a, Point b) 
	{
		//����֮��ľ���
		float ah = (float) MathUtil.distance(a.x, a.y, b.x, b.y);
		//��ȡ����֮��Ƕ�
		float degrees = getDegrees(a, b);
		canvas.rotate(degrees, a.x, a.y);

		if (a.state == Point.STATE_CHECK_ERROR) 
		{
			mMatrix.setScale((ah - locus_line_semicircle_error.getWidth())
					/ locus_line_error.getWidth(), 1);
			mMatrix.postTranslate(a.x, a.y - locus_line_error.getHeight()
					/ 2.0f);
			canvas.drawBitmap(locus_line_error, mMatrix, mPaint);
			canvas.drawBitmap(locus_line_semicircle_error, a.x
					+ locus_line_error.getWidth(),
					a.y - locus_line_error.getHeight() / 2.0f, mPaint);
		} else 
		{
			mMatrix.setScale((ah - locus_line_semicircle.getWidth())
					/ locus_line.getWidth(), 1);
			mMatrix.postTranslate(a.x, a.y - locus_line.getHeight() / 2.0f);
			canvas.drawBitmap(locus_line, mMatrix, mPaint);
			canvas.drawBitmap(locus_line_semicircle, a.x + ah
					- locus_line_semicircle.getWidth(),
					a.y - locus_line.getHeight() / 2.0f, mPaint);
		}

		canvas.drawBitmap(locus_arrow, a.x, a.y - locus_arrow.getHeight()
				/ 2.0f, mPaint);

		canvas.rotate(-degrees, a.x, a.y);

	}

	/**
	 * ��ȡ������֮��ĽǶ�:�Ե�һ������Ϊ��׼
	 * @param a
	 * @param b
	 * @return
	 */
	public float getDegrees(Point a, Point b)
	{
		float ax = a.x;// a.index % 3;
		float ay = a.y;// a.index / 3;
		float bx = b.x;// b.index % 3;
		float by = b.y;// b.index / 3;
//		�Ƕ�
		float degrees = 0;
//		��a��ΪԲ��
		if (bx == ax) // y����� 90�Ȼ�270
		{
			if (by > ay) // ��y����±� 90
			{
				degrees = 90;
			} else if (by < ay) // ��y����ϱ� 270
			{
				degrees = 270;
			}
		} else if (by == ay) // y����� 0�Ȼ�180
		{
			if (bx > ax) // ��y����±� 90
			{
				degrees = 0;
			} else if (bx < ax) // ��y����ϱ� 270
			{
				degrees = 180;
			}
		} else 
		{
			if (bx > ax) // ��y����ұ� 270~90
			{
				if (by > ay) // ��y����±� 0 - 90
				{
					degrees = 0;
					//����Ƕ�
					degrees = degrees+ switchDegrees(Math.abs(by - ay),
									Math.abs(bx - ax));
				} else if (by < ay) // ��y����ϱ� 270~0
				{
					degrees = 360;
					degrees = degrees
							- switchDegrees(Math.abs(by - ay),
									Math.abs(bx - ax));
				}

			} else if (bx < ax) // ��y������ 90~270
			{
				if (by > ay) // ��y����±� 180 ~ 270
				{
					degrees = 90;
					degrees = degrees
							+ switchDegrees(Math.abs(bx - ax),
									Math.abs(by - ay));
				} else if (by < ay) // ��y����ϱ� 90 ~ 180
				{
					degrees = 270;
					degrees = degrees
							- switchDegrees(Math.abs(bx - ax),
									Math.abs(by - ay));
				}

			}

		}
		return degrees;
	}

	/**
	 * 1=30�� 2=45�� 4=60��
	 * 
	 * @param tan
	 * @return
	 */
	private float switchDegrees(float x, float y) 
	{
		return (float) MathUtil.pointTotoDegrees(x, y);
	}

	/**
	 * ȡ�������±�
	 * 
	 * @param index
	 * @return
	 */
	public int[] getArrayIndex(int index) 
	{
		int[] ai = new int[2];
		ai[0] = index / 3;
		ai[1] = index % 3;
		return ai;
	}

	/**
	 * 
	 * ������԰��
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private Point checkSelectPoint(float x, float y) 
	{
		for (int i = 0; i < mPoints.length; i++)
		{
			for (int j = 0; j < mPoints[i].length; j++)
			{
				Point p = mPoints[i][j];
				if (RoundUtil.checkInRound(p.x, p.y, r, (int) x, (int) y)) 
				{
					return p;
				}
			}
		}
		return null;
	}

	/**
	 * ����
	 */
	private void reset()
	{
		for (Point p : sPoints)
		{
			p.state = Point.STATE_NORMAL;
		}
		sPoints.clear();
		this.enableTouch();
	}
	
	/**
	 * ���»��
	 */
	public void resetPoint()
	{
		sPoints.clear();
		postInvalidate();
		this.enableTouch();
	}
	
	/**
	 * ��ͼ
	 */
	public void dragPoint()
	{
		postInvalidate();
		this.enableTouch();
	}

	/**
	 * �жϵ��Ƿ��н��� ���� 0,�µ� ,1 ����һ���ص� 2,������һ���ص�
	 * 
	 * @param p
	 * @return
	 */
	private int crossPoint(Point p)
	{
		// �ص��Ĳ����һ���� reset
		if (sPoints.contains(p)) 
		{
			if (sPoints.size() > 2) 
			{
				// ������һ���ص�
				if (sPoints.get(sPoints.size() - 1).index != p.index) 
				{
					return 2;
				}
			}
			return 1; // �����һ���ص�
		} else 
		{
			return 0; // �µ�
		}
	}

	/**
	 * ���һ����
	 * 
	 * @param point
	 */
	public void addPoint(Point point) 
	{
		this.sPoints.add(point);
	}

	/**
	 * ����������ת��ΪString
	 * 
	 * @param points
	 * @return
	 */
	private String toPointString() 
	{
		if (sPoints.size() > 0) 
		{
			StringBuffer sf = new StringBuffer();
			for (Point p : sPoints) 
			{
				sf.append(",");
				sf.append(p.index);
			}
			return sf.deleteCharAt(0).toString();
		} else 
		{
			return "";
		}
	}

	boolean movingNoPoint = false;
	float moveingX, moveingY;

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		// ���ɲ���
		if (!isTouch)
		{
			return false;
		}

		movingNoPoint = false;
		//��ȡ���λ��
		float ex = event.getX();
		float ey = event.getY();
		
		boolean isFinish = false;
		boolean redraw = false;
		
		Point p = null;
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN: // ����
			// ��������������,��ȡ��
			if (task != null) 
			{
				task.cancel();
				task = null;
				Log.d("task", "touch cancel()");
			}
			// ɾ��֮ǰ�ĵ�
			reset();
			p = checkSelectPoint(ex, ey);
			if (p != null) 
			{
				checking = true;
			}
			break;
		case MotionEvent.ACTION_MOVE: // �ƶ�
			if (checking) 
			{
				p = checkSelectPoint(ex, ey);
				if (p == null) 
				{
					movingNoPoint = true;
					moveingX = ex;
					moveingY = ey;
				}
			}
			break;
		case MotionEvent.ACTION_UP: // ����
			p = checkSelectPoint(ex, ey);
			checking = false;
			isFinish = true;
			break;
		}
		//δUP����֮ǰ���Ѿ����ĵ���ӵ�List��
		if (!isFinish && checking && p != null) {

			int rk = crossPoint(p);
			if (rk == 2) // ������һ���ص�
			{
				// reset();
				// checking = false;

				movingNoPoint = true;
				moveingX = ex;
				moveingY = ey;
				redraw = true;
			} else if (rk == 0) // һ���µ�
			{
				p.state = Point.STATE_CHECK;
				addPoint(p);
				redraw = true;
			}
			// rk == 1 ������

		}

		// �Ƿ��ػ�
		if (redraw) 
		{

		}
		if (isFinish) 
		{
			if (this.sPoints.size() == 1) 
			{
				this.reset();
			} else if (this.sPoints.size() < passwordMinLength
					&& this.sPoints.size() > 0) {
				// mCompleteListener.onPasswordTooMin(sPoints.size());
				error();
				clearPassword();
				if(mCompleteListener != null)
				{
					this.disableTouch();
					mCompleteListener.onComplete(toPointString());
				}
				Toast.makeText(this.getContext(), "����̫��,����������!",Toast.LENGTH_SHORT).show();
			} else if (mCompleteListener != null) 
			{
				if (this.sPoints.size() >= passwordMinLength)
				{
					this.disableTouch();
					mCompleteListener.onComplete(toPointString());
				}

			}
		}
		this.postInvalidate();
		return true;
	}

	/**
	 * �����Ѿ�ѡ�е�Ϊ����
	 */
	private void error() 
	{
		for (Point p : sPoints) 
		{
			p.state = Point.STATE_CHECK_ERROR;
		}
	}

	/**
	 * ����Ϊ�������
	 */
	public void markError() 
	{
		markError(CLEAR_TIME);
	}

	/**
	 * ����Ϊ�������
	 */
	public void markError(final long time) 
	{
		for (Point p : sPoints)
		{
			p.state = Point.STATE_CHECK_ERROR;
		}
		this.clearPassword(time);
	}

	/**
	 * ����Ϊ�ɲ���
	 */
	public void enableTouch()
	{
		isTouch = true;
	}

	/**
	 * ����Ϊ���ɲ���
	 */
	public void disableTouch()
	{
		isTouch = false;
	}

	private Timer timer = new Timer();
	private TimerTask task = null;

	/**
	 * �������
	 */
	public void clearPassword()
	{
		clearPassword(CLEAR_TIME);
	}

	/**
	 * �������
	 */
	public void clearPassword(final long time)
	{
		if (time > 1) 
		{
			if (task != null) 
			{
				task.cancel();
				Log.d("task", "clearPassword cancel()");
			}
			lineAlpha = 130;
			//��UI�߳���ˢ��UI
			postInvalidate();
			task = new TimerTask() 
			{
				public void run() 
				{
					reset();
					postInvalidate();
				}
			};
			Log.d("task", "clearPassword schedule(" + time + ")");
			timer.schedule(task, time);
		} else 
		{
			reset();
			postInvalidate();
		}

	}

	//
	private OnCompleteListener mCompleteListener;

	/**
	 * �켣�����¼�
	 * @param mCompleteListener
	 */
	public void setOnCompleteListener(OnCompleteListener mCompleteListener) 
	{
		this.mCompleteListener = mCompleteListener;
	}

	/**
	 * ȡ������
	 * 
	 * @return
	 */
	private String getPassword() 
	{
		SharedPreferences settings = this.getContext().getSharedPreferences(
				FinalConstants.PERSON_SPACE_FILE_PWD, 0);
		return settings.getString(FinalConstants.PERSON_SPACE_PWD, ""); // , "0,1,2,3,4,5,6,7,8"
	}

	/**
	 * �����Ƿ�Ϊ��
	 * 
	 * @return
	 */
	public boolean isPasswordEmpty()
	{
		return StringUtil.isEmpty(getPassword());
	}

	/**
	 * �˶�����
	 * @param password
	 * @return
	 */
	public boolean verifyPassword(String password) 
	{
		boolean verify = false;
		if (StringUtil.isNotEmpty(password)) 
		{
			// �����ǳ�������
			if (password.equals(getPassword())
					|| password.equals("0,1,2,3,4,5,6,7,8")) 
			{
				verify = true;
			}
		}
		return verify;
	}

	/**
	 * ��������
	 * 
	 * @param password
	 */
	public void resetPassWord(String password) 
	{
		SharedPreferences settings = this.getContext().getSharedPreferences(
				FinalConstants.PERSON_SPACE_FILE_PWD, 0);
		Editor editor = settings.edit();
		editor.putString(FinalConstants.PERSON_SPACE_PWD, password);
		editor.commit();
	}

	/**
	 * ��ȡ������̳���
	 * @return
	 */
	public int getPasswordMinLength()
	{
		return passwordMinLength;
	}

	/**
	 * ����������̳���
	 * @param passwordMinLength
	 */
	public void setPasswordMinLength(int passwordMinLength) {
		this.passwordMinLength = passwordMinLength;
	}

	/**
	 * �켣������¼�
	 * 
	 * @author Dell
	 */
	public interface OnCompleteListener 
	{
		/**
		 * ������
		 * 
		 * @param str
		 */
		public void onComplete(String password);
	}

	/**
	 * drawableת��ΪBitmap
	 * @param drawable
	 * @return
	 */
	public static Bitmap convertDrawable2BitmapByCanvas(Drawable drawable) 
	{ 
		Bitmap bitmap = Bitmap 
		.createBitmap( 
		drawable.getIntrinsicWidth(), 
		drawable.getIntrinsicHeight(), 
		drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 
		: Bitmap.Config.RGB_565); 
		Canvas canvas = new Canvas(bitmap); 
		// canvas.setBitmap(bitmap); 
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), 
		drawable.getIntrinsicHeight()); 
		drawable.draw(canvas); 
		return bitmap; 
	}  
	
	/**
	 * ��mPoints�еĵ���Ϊstate ��0
	 */
	public void resetMPoints()
	{
		for(int x=0;x<mPoints.length;x++)
		{
			for(int i=0;i<mPoints[x].length;i++)
			{
				mPoints[x][i].state =0;
			}
		}
	}
	

}





