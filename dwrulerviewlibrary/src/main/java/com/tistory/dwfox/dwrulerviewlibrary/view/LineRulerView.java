package com.tistory.dwfox.dwrulerviewlibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.tistory.dwfox.dwrulerviewlibrary.utils.DWUtils;

/**
 * Created by DW on 2016-08-31.
 */
public class LineRulerView extends View {

    private Paint paint;
    private Paint textPaint;
    private DashPathEffect dashPathEffect;
    private Path backGroundPath;

    private float bigUnitLineHeight = 0f;
    private float samllUnitLineHeight = 0f;

    private float MAX_DATA = 200;
    private float MIN_DATA = 50;

    private int viewHeight = 0;
    private int viewWidth = 0;


    public LineRulerView(Context context) {
        super(context);
        init(context);
    }

    public LineRulerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LineRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LineRulerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setStrokeWidth(5f);
        paint.isAntiAlias();
        paint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(0xFFFFFFFF);
        textPaint.isAntiAlias();
        textPaint.setTextSize(DWUtils.sp2px(context, 16));
        textPaint.setTextAlign(Paint.Align.CENTER);

        invalidate();
    }

    public LineRulerView setMaxValue(float maxValue) {
        this.MAX_DATA = maxValue;
        return this;
    }

    public LineRulerView setMinValue(float minValue) {
        this.MIN_DATA = minValue;
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();

        Log.d("##", "viewHeight : " + viewHeight);

        float viewInterval = (float) viewWidth / (MAX_DATA - MIN_DATA);


        canvas.drawLine(0, 0, 0, viewHeight / 5 * 2, paint);

        for (int i = 1; i < (MAX_DATA - MIN_DATA); i++) {
            if (i % 5 == 0) {
                canvas.drawLine(viewInterval * i, 0, viewInterval * i, viewHeight / 5 * 3, paint);
                canvas.drawText("" + (int) (i + MIN_DATA), viewInterval * i, viewHeight / 5 * 3 + DWUtils.sp2px(getContext(), 16), textPaint);
            } else {
                canvas.drawLine(viewInterval * i, 0, viewInterval * i, viewHeight / 10 * 3, paint);
            }
        }
        canvas.drawLine(viewWidth, 0, viewWidth, viewHeight / 5 * 2, paint);

        super.onDraw(canvas);
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        viewWidth = DWMeasure.getMeasure(widthMeasureSpec);
//        viewHeight = DWMeasure.getMeasure(heightMeasureSpec);
//    }
}
