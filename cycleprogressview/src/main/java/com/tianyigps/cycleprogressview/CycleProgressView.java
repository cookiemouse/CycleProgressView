package com.tianyigps.cycleprogressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cookiemouse on 2017/7/21.
 */

public class CycleProgressView extends View {

    private static final String TAG = "CycleProgressView";

    private int color_default = 0xff656b81;
    private int color_progressed = 0xff3cabfa;

    private Paint mPaint;

    private RectF mRectF = new RectF();

    private int progress = 0;
    private int max = 100;

    public CycleProgressView(Context context) {
        this(context, null);
    }

    public CycleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CycleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color_default);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width;
        int height;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = getPaddingLeft() + 200 + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = getPaddingTop() + 200 + getPaddingBottom();
        }

        setMeasuredDimension(width, height);

        mRectF.set(getPaddingLeft(), getPaddingTop(), width - getPaddingRight(), height - getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int degree = 360 * progress / max;

        mPaint.setColor(color_progressed);
        canvas.drawArc(mRectF, -90, degree, false, mPaint);
        mPaint.setColor(color_default);
        canvas.drawArc(mRectF, (degree - 90), (360 - degree), false, mPaint);
    }

    public void setStrokWidth(int width) {
        mPaint.setStrokeWidth(width);
        postInvalidate();
    }

    public void setProgressedColor(int color) {
        color_progressed = color;
        postInvalidate();
    }

    public void setDefaultColor(int color) {
        color_default = color;
        postInvalidate();
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

    public void setMax(int max) {
        this.max = max;
        postInvalidate();
    }
}
