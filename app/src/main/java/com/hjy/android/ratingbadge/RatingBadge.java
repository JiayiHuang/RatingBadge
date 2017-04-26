package com.hjy.android.ratingbadge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RatingBadge extends View {
    private int WIDTH_LINE_OUTER = 2;
    private int WIDTH_LINE_MIDDLE = 1;
    private int WIDTH_LINE_INNER = 1;
    private int BORDER_INNER_CIRCLE = 17;
    private int BORDER_BETWEEN_OUT_MIDDLE = 3;
    private int SIZE_TEXT = 12;
    private int GAP_TOP = 7;
    private int ratingValue = 10;
    private Paint mPaintOuter, mPaintMiddle, mPaintInner, mTextPaint;

    public RatingBadge(Context context) {
        super(context);
        inits(context);
    }

    public RatingBadge(Context context, AttributeSet attrs) {
        super(context, attrs);
        inits(context);
    }

    public RatingBadge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inits(context);
    }

    public static int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    private void inits(Context context) {
        WIDTH_LINE_OUTER = dp2px(context, WIDTH_LINE_OUTER);
        WIDTH_LINE_MIDDLE = dp2px(context, WIDTH_LINE_MIDDLE);
        WIDTH_LINE_INNER = dp2px(context, WIDTH_LINE_INNER);
        BORDER_INNER_CIRCLE = dp2px(context, BORDER_INNER_CIRCLE);
        BORDER_BETWEEN_OUT_MIDDLE = dp2px(context, BORDER_BETWEEN_OUT_MIDDLE);
        SIZE_TEXT = dp2px(context, SIZE_TEXT);
        GAP_TOP = dp2px(context, GAP_TOP);

        mPaintOuter = new Paint();
        mPaintOuter.setColor(Color.parseColor("#fbaba9"));
        mPaintOuter.setAntiAlias(true);
        mPaintOuter.setStyle(Paint.Style.STROKE);
        mPaintOuter.setStrokeWidth(WIDTH_LINE_OUTER);

        mPaintMiddle = new Paint();
        mPaintMiddle.setColor(Color.parseColor("#fbaba9"));
        mPaintMiddle.setAntiAlias(true);
        mPaintMiddle.setStyle(Paint.Style.STROKE);
        mPaintMiddle.setStrokeWidth(WIDTH_LINE_MIDDLE);
        PathEffect effects = new DashPathEffect(new float[]{3, 3, 3, 3}, 1);
        mPaintMiddle.setPathEffect(effects);

        mPaintInner = new Paint();
        mPaintInner.setColor(Color.parseColor("#fbaba9"));
        mPaintInner.setAntiAlias(true);
        mPaintInner.setStyle(Paint.Style.STROKE);
        mPaintInner.setStrokeWidth(WIDTH_LINE_INNER);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(SIZE_TEXT);
        mTextPaint.setColor(Color.parseColor("#fbacaa"));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBadge(canvas);

    }

    private void drawBadge(Canvas canvas) {

        int width = getMeasuredHeight() > getMeasuredWidth() ? getMeasuredWidth() : getMeasuredHeight();
        int center = width / 2;
        int radius = center - WIDTH_LINE_OUTER / 2;

        canvas.drawCircle(center, center, radius, mPaintOuter);// 外圆
        canvas.drawCircle(center, center, radius - BORDER_BETWEEN_OUT_MIDDLE, mPaintMiddle);// 中虚线圆
        RectF rectF = new RectF(BORDER_INNER_CIRCLE, BORDER_INNER_CIRCLE, width - BORDER_INNER_CIRCLE, width - BORDER_INNER_CIRCLE);
        canvas.drawArc(rectF, 30, 120, false, mPaintInner);// 内圆弧
        canvas.drawArc(rectF, 210, 120, false, mPaintInner);// 内圆弧

        Bitmap rating = BitmapFactory.decodeResource(getResources(), R.drawable.star_fill);
        Bitmap ratingNone = BitmapFactory.decodeResource(getResources(), R.drawable.star_hollow);
        int ratingWidth = rating.getWidth();
        // 十颗星
        canvas.drawBitmap(ratingValue >= 3 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第3颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 4 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第4颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 5 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第5颗
        canvas.rotate(60, center, center);
        canvas.drawBitmap(ratingValue >= 10 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第10颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 9 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第9颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 8 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第8颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 7 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第7颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 6 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第6颗
        canvas.rotate(60, center, center);
        canvas.drawBitmap(ratingValue >= 1 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第1颗
        canvas.rotate(30, center, center);
        canvas.drawBitmap(ratingValue >= 2 ? rating : ratingNone, center - ratingWidth / 2, GAP_TOP, null);// 第2颗
        canvas.rotate(30, center, center);// 画布归正

        String testString = "已入账";
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int baseline = (int) ((rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(testString, rectF.centerX(), baseline, mTextPaint);
    }

    public void setRatingValue(int value) {
        ratingValue = value;
        invalidate();
    }
}
