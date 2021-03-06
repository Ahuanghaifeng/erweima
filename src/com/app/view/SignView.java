package com.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SignView extends View {

	private Paint paint;
	private Canvas cacheCanvas;
	private Bitmap cachebBitmap;
	private Path path;
	static final int BACKGROUND_COLOR = Color.WHITE;
	static final int BRUSH_COLOR = Color.BLACK;

	public SignView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// initView(context);
		// TODO Auto-generated constructor stub
	}

	public SignView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// initView(context);
		// TODO Auto-generated constructor stub
	}

	public SignView(Context context) {
		super(context);
		// initView(context);
		// TODO Auto-generated constructor stub
	}

	public void initView(Context context) {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(20);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.BLACK);
		path = new Path();
		cachebBitmap = Bitmap.createBitmap(
				MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec), Config.ARGB_8888);
		cacheCanvas = new Canvas(cachebBitmap);
		cacheCanvas.drawColor(Color.WHITE);
	}

	public Bitmap getCachebBitmap() {
		BitmapFactory.Options options = new BitmapFactory.Options();  
	    options.inJustDecodeBounds = true;
	    int bitmapWidth = cachebBitmap.getWidth();  
        int bitmapHeight = cachebBitmap.getHeight();  
        // 缩放图片的尺寸  
        float scaleWidth = (float) 0.25;  
        float scaleHeight = (float) 0.25;  
        Matrix matrix = new Matrix();  
        matrix.postScale(scaleWidth, scaleHeight);  
        // 产生缩放后的Bitmap对象  
        Bitmap resizeBitmap = Bitmap.createBitmap(cachebBitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false); 
		return resizeBitmap;
	}

	public void clear() {
		if (cacheCanvas != null) {

//			paint.setColor(BACKGROUND_COLOR);
			paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));  
			cacheCanvas.drawPaint(paint);  
			paint = new Paint();
//		    paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
		    paint.setAntiAlias(true);
			paint.setStrokeWidth(20);
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(Color.BLACK);
//			cacheCanvas.drawPaint(paint);
//			paint.setColor(Color.BLACK);
			cacheCanvas.drawColor(Color.WHITE);
			invalidate();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// canvas.drawColor(BRUSH_COLOR);
		canvas.drawBitmap(cachebBitmap, 0, 0, null);
		canvas.drawPath(path, paint);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		int curW = cachebBitmap != null ? cachebBitmap.getWidth() : 0;
		int curH = cachebBitmap != null ? cachebBitmap.getHeight() : 0;
		if (curW >= w && curH >= h) {
			return;
		}

		if (curW < w)
			curW = w;
		if (curH < h)
			curH = h;

		Bitmap newBitmap = Bitmap.createBitmap(curW, curH,
				Bitmap.Config.ARGB_8888);
		Canvas newCanvas = new Canvas();
		newCanvas.setBitmap(newBitmap);
		if (cachebBitmap != null) {
			newCanvas.drawBitmap(cachebBitmap, 0, 0, null);
		}
		cachebBitmap = newBitmap;
		cacheCanvas = newCanvas;
	}

	private float cur_x, cur_y;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			if(isListener!=null){
				isListener.sign();
			}
			cur_x = x;
			cur_y = y;
			path.moveTo(cur_x, cur_y);
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			path.quadTo(cur_x, cur_y, x, y);
			cur_x = x;
			cur_y = y;
			break;
		}

		case MotionEvent.ACTION_UP: {
			cacheCanvas.drawPath(path, paint);
			path.reset();
			break;
		}
		}

		invalidate();

		return true;
	}
	
	public interface isSignListener{
		void sign();
	}
	
	isSignListener isListener;
	
	public void setIsListener(isSignListener isListener) {
		this.isListener = isListener;
	}

}
