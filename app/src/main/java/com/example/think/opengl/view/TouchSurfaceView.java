package com.example.think.opengl.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2015/5/23.
 */
public class TouchSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private float x=50, y=50;
    private Paint p;
    private SurfaceHolder sfh;
    private Canvas canvas;
    private float radius = 1;
    private List<PointGroup> pointGroups = new ArrayList<>();

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
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                pointGroups.add(new PointGroup(x, y));
                doDraw();
        }
        return true;
    }

    private void doDraw() {
        canvas = sfh.lockCanvas(new Rect(0, 0, 0, 0));
        sfh.unlockCanvasAndPost(canvas);

        canvas = sfh.lockCanvas();
        canvas.drawColor(Color.argb(255, 0xFE, 0xEA, 0xA2));
        p.setColor(Color.argb(204, 0xF6, 0xC0, 0x38));
        for (int i=0; i< pointGroups.size(); i++){
            List<Point> points = pointGroups.get(i).getPoints();
            for (int j=0; j<points.size(); j++){
                Point point = points.get(j);
                canvas.drawCircle(point.getX(), point.getY(), radius, p);
            }
        }
        sfh.unlockCanvasAndPost(canvas);

        canvas = sfh.lockCanvas(new Rect(0, 0, 0, 0));
        sfh.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        canvas = sfh.lockCanvas();
//        canvas.drawColor(Color.YELLOW);
//        sfh.unlockCanvasAndPost(canvas);
        doDraw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
