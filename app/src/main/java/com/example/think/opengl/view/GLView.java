package com.example.think.opengl.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.opengl.GLSurfaceView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.think.opengl.R;

/**
 * Created by Think on 2015/5/21.
 */
public class GLView extends GLSurfaceView implements SurfaceHolder.Callback{

    private Paint p;
    private float x, y;
    private SurfaceHolder sfh;

    public GLView(Context context) {
        this(context, null);
    }

    public GLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sfh = this.getHolder();
        sfh.addCallback(this);
        p = new Paint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
//        draw();
        return true;
    }

//    private void draw(){
//        Canvas canvas = sfh.lockCanvas();
//        if(canvas == null){
//            Log.e("123", "canvas");
//        }
//        p.setColor(Color.BLUE);
//        canvas.drawCircle(x, y, 10, p);
//        Log.e("123", "okokokoko");
//        sfh.unlockCanvasAndPost(canvas);
//    }

}
