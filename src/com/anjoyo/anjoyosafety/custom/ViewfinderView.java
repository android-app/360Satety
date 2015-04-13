/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anjoyo.anjoyosafety.custom;



import com.anjoyo.anjoyosafety.camera.CameraManager;
import com.anjoyo.anjoyosatety.activity.R;
import com.google.zxing.ResultPoint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Collection;
import java.util.HashSet;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder rectangle and partial
 * transparency outside it, as well as the laser scanner animation and result points.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ViewfinderView extends View {

  private static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
  private static final long ANIMATION_DELAY = 100L;
  private static final int OPAQUE = 0xFF;

  private final Paint paint;
  private Bitmap resultBitmap;
  private final int maskColor;
  private final int resultColor;
  private final int frameColor;
  private final int laserColor;
  private final int resultPointColor;
  private int scannerAlpha;
  private Collection<ResultPoint> possibleResultPoints;
  private Collection<ResultPoint> lastPossibleResultPoints;
  int i = 0;
  Paint textPaint;
  GradientDrawable mDrawable;
  Rect mRect;
  private boolean laserLinePortrait = true;
  // This constructor is used when the class is built from an XML resource.
  public ViewfinderView(Context context, AttributeSet attrs) {
    super(context, attrs);
    textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mRect = new Rect();
    int left = Color.parseColor("#CACACA");
	int center = Color.RED;
	int right = Color.parseColor("#CACACA");
	mDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[] { left, center, right });
    // Initialize these once for performance rather than calling them every time in onDraw().
    paint = new Paint();
    Resources resources = getResources();
    maskColor = resources.getColor(R.color.viewfinder_mask);
    resultColor = resources.getColor(R.color.result_view);
    frameColor = resources.getColor(R.color.viewfinder_frame);
    laserColor = resources.getColor(R.color.viewfinder_laser);
    resultPointColor = resources.getColor(R.color.possible_result_points);
    scannerAlpha = 0;
    possibleResultPoints = new HashSet<ResultPoint>(5);
  }

  @Override
  public void onDraw(Canvas canvas) {
	  
    Rect frame = CameraManager.get().getFramingRect();
    if (frame == null) {
      return;
    }
    int width = canvas.getWidth();
    int height = canvas.getHeight();

    // Draw the exterior (i.e. outside the framing rect) darkened
    paint.setColor(resultBitmap != null ? resultColor : maskColor);
    
    canvas.drawRect(0, 0, width, frame.top, paint);
    canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
    canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
    canvas.drawRect(0, frame.bottom + 1, width, height, paint);
    if (resultBitmap != null) {
        // Draw the opaque result bitmap over the scanning rectangle
        paint.setAlpha(OPAQUE);
        canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
      } else {
  		/*//上下走的线
  		if (laserLinePortrait) {

  			if ((i += 5) < frame.bottom - frame.top) {
  				
  				 * canvas.drawRect(frame.left + 2, frame.top - 2 + i, frame.right - 1, frame.top + 2 + i,
  				 * paint);
  				 
  				int r = 8;
  				mDrawable.setShape(GradientDrawable.RECTANGLE);
  				mDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
  				setCornerRadii(mDrawable, r, r, r, r);
  				mRect.set(frame.left + 2, frame.top - 3 + i, frame.right - 1, frame.top + 3 + i);
  				
  				mDrawable.setBounds(mRect);
  				mDrawable.draw(canvas);
  				invalidate();
  			} else {
  				i = 0;
  			}

  		} else {
  			float left = frame.left + (frame.right - frame.left) / 2 - 2;
  			canvas.drawRect(left, frame.top, left + 2, frame.bottom - 2, paint);
  		}*/
  		Collection<ResultPoint> currentPossible = possibleResultPoints;
  		Collection<ResultPoint> currentLast = lastPossibleResultPoints;
  		if (currentPossible.isEmpty()) {
  			lastPossibleResultPoints = null;
  		} else {
  			possibleResultPoints = new HashSet<ResultPoint>(5);			
  			lastPossibleResultPoints = currentPossible;
  			paint.setAlpha(OPAQUE);
  			paint.setColor(resultPointColor);
  			for (ResultPoint point : currentPossible) {
  				// canvas.drawCircle(frame.left + point.getX(), frame.top +
  				// point.getY(), 6.0f, paint);
  				/*
  				 * canvas.drawCircle(frame.left + point.getY(), frame.top + point.getX(), 6.0f, paint);
  				 */
  			}
  		}
  		if (currentLast != null) {
  			paint.setAlpha(OPAQUE / 2);
  			paint.setColor(resultPointColor);
  			for (ResultPoint point : currentLast) {
  				/*
  				 * canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 3.0f, paint);
  				 */
  			}
  		}

        // Request another update at the animation interval, but only repaint the laser line,
        // not the entire viewfinder mask.
        postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right, frame.bottom);
      }
    }

  public void drawViewfinder() {
    resultBitmap = null;
    invalidate();
  }

  /**
   * Draw a bitmap with the result points highlighted instead of the live scanning display.
   *
   * @param barcode An image of the decoded barcode.
   */
  public void drawResultBitmap(Bitmap barcode) {
    resultBitmap = barcode;
    invalidate();
  }

  public void addPossibleResultPoint(ResultPoint point) {
    possibleResultPoints.add(point);
  }
  public void setCornerRadii(GradientDrawable drawable, float r0, float r1, float r2, float r3) {
		drawable.setCornerRadii(new float[] { r0, r0, r1, r1, r2, r2, r3, r3 });
	}
}
