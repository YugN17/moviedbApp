package com.aleixballetbo.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;


public class SimpleCustomView extends android.support.v7.widget.AppCompatImageView {

    private static final float STROKE_WIDTH = 15f;
    private static final float DEFAULT_RADIUS = 20f;
    private Paint paint;
    private Path path;
    private int height;
    private int width;
    private Bitmap bitmap;
    private float radius;
    private int borderColor;

    public void setBorderColor (int borderColor) {
        this.borderColor = borderColor;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public SimpleCustomView(Context context) {
        super(context);
        init();
    }

    public SimpleCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init () {
        radius = dpToPx(DEFAULT_RADIUS);
        borderColor = Color.BLACK;
        paint = new Paint();
        //paint.setStyle(Paint.Style.STROKE);
        //paint.setStrokeWidth(STROKE_WIDTH);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        path = new Path();

        //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            Bitmap roundBitmap = getRoundedCroppedBitmap(bitmap, width);
            canvas.drawBitmap(roundBitmap, 0, 0, null);
        }
    }

    private Bitmap getBitmap() {
        Bitmap bm = null;
        Drawable d = getDrawable();
        if (d != null && d instanceof BitmapDrawable) bm = ((BitmapDrawable) d).getBitmap();
        return bm;
    }

    public Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, Math.round(radius*1.5f), false);

        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(), finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(Color.BLACK);
        canvas.drawCircle(finalBitmap.getWidth() / 2,
                finalBitmap.getHeight() / 3,
                finalBitmap.getWidth() / 2 - STROKE_WIDTH, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, 0f, 0f, paint);

        paint.setColor(borderColor);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        path.addCircle(finalBitmap.getWidth()/2f,finalBitmap.getHeight()/3f, finalBitmap.getWidth()/2f, Path.Direction.CCW);
        canvas.drawPath(path, paint);

        return output;
    }

    private float dpToPx(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.height = h;
        this.width = w;
    }

}
