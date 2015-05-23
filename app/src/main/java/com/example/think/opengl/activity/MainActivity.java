package com.example.think.opengl.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import com.example.think.opengl.R;
import com.example.think.opengl.view.GLView;
import com.example.think.opengl.view.OpenGLRenderer;
import com.example.think.opengl.view.TouchSurfaceView;

import java.util.Random;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

//        GLView view = new GLView(this);
//        view.setRenderer(new OpenGLRenderer(this));

        TouchSurfaceView view = new TouchSurfaceView(this);
        //view.setBackgroundResource(R.mipmap.sand);
        setContentView(view);
    }

}
