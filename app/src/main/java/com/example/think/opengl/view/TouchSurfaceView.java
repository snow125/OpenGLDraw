package com.example.think.opengl.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Think on 2015/5/23.
 */
public class TouchSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private float x=50, y=50;
    private Paint p;
    private SurfaceHolder sfh;
    private Canvas canvas;
    private float radius = 100;

    public TouchSurfaceView(Context context) {
        this(context, null, 0);
    }

    public TouchSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        p = new Paint();
        sfh = this.getHolder();
        sfh.addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        doDraw();
        return true;
    }

    private void doDraw() {
        canvas = sfh.lockCanvas(new Rect(0, 0, 0, 0));
        sfh.unlockCanvasAndPost(canvas);

        canvas = sfh.lockCanvas();
        //p.setColor(Color.BLUE);
        p.setARGB(100, 100, 100, 100);
        canvas.drawCircle(x, y, radius, p);
        sfh.unlockCanvasAndPost(canvas);

        canvas = sfh.lockCanvas(new Rect(0, 0, 0, 0));
        sfh.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        doDraw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
