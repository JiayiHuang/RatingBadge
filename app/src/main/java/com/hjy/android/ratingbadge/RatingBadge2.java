package com.hjy.android.ratingbadge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RatingBadge2 extends FrameLayout {
    private Paint mPaint;
    public static final int WIDTH_LINE = 6;
    public static final int GAP_TOP = 10 + WIDTH_LINE;
    private int ratingValue = 4;

    public RatingBadge2(Context context) {
        super(context);
        initPaint();
    }

    public RatingBadge2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public RatingBadge2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBadge(canvas);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#fbacaa"));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(WIDTH_LINE);
    }

    private void drawBadge(Canvas canvas) {
        int width = getMeasuredHeight() > getMeasuredWidth() ? getMeasuredWidth() : getMeasuredHeight();
        int center = width / 2;
        int radius = center - WIDTH_LINE / 2;
        // 外圆
        canvas.drawCircle(center, center, radius, mPaint);
        Bitmap rating = BitmapFactory.decodeResource(getResources(), R.drawable.star_fill);
        Bitmap ratingNone = BitmapFactory.decodeResource(getResources(), R.drawable.star_hollow);
        int ratingWidth = rating.getWidth();
        // 十颗星
        canvas.drawBitmap(ratingValue >= 3 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP * 3, null);// 第3颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 4 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP * 2, null);// 第4颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 5 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第5颗
        canvas.rotate(60, center, center);
        canvas.drawBitmap(ratingValue >= 10 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第10颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 9 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP * 2, null);// 第9颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 8 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP * 3, null);// 第8颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 7 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP * 2, null);// 第7颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 6 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第6颗
        canvas.rotate(60, center, center);
        canvas.drawBitmap(ratingValue >= 1 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第1颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 2 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP * 2, null);// 第2颗
        canvas.rotate(30, center, center);// 画布归正
        // 中间的两条弧线
        RectF rectF = new RectF(WIDTH_LINE + ratingWidth, GAP_TOP * 6 + ratingWidth,
                width - WIDTH_LINE - ratingWidth, width - GAP_TOP * 6 - ratingWidth);
        canvas.drawArc(rectF, 35, 110, false, mPaint);
        canvas.drawArc(rectF, 215, 110, false, mPaint);
    }

    public void setRatingValue(int value) {
        ratingValue = value;
        invalidate();
    }
}
