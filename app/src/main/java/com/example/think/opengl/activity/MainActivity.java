package com.example.think.opengl.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.think.opengl.view.GLView;
import com.example.think.opengl.view.OpenGLRenderer;
import com.example.think.opengl.R;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

        GLView view = new GLView(this);
        view.setRenderer(new OpenGLRenderer(this));
        setContentView(view);
    }

}
