package com.example.think.opengl.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.think.opengl.Mesh.Background;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Think on 2015/5/21.
 */
public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private Background background;
    private Context context;

    public OpenGLRenderer(Context context) {
        this.context = context;
        background = new Background(context);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background color to black ( rgba ).
        gl.glClearColor(0.5f, 0.1f, 0.2f, 0.5f);  // OpenGL docs
        // Enable Smooth Shading, default not really needed.
        gl.glShadeModel(GL10.GL_SMOOTH);// OpenGL docs.
        // Depth buffer setup.
        gl.glClearDepthf(1.0f);// OpenGL docs.
        // Enables depth testing.
        gl.glEnable(GL10.GL_DEPTH_TEST);// OpenGL docs.
        // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL);// OpenGL docs.
        // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, // OpenGL docs.
                GL10.GL_NICEST);
        gl.glDisable(GL10.GL_DITHER);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        background.loadTexture(gl);
    }

    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //先写下面两行 否则无法出图像
        gl.glLoadIdentity();//保存栈信息
        gl.glTranslatef(0, 0, -4);//想Z轴负向平移

        background.draw(gl);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height);// OpenGL docs.
        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);// OpenGL docs.
        // Reset the projection matrix
        gl.glLoadIdentity();// OpenGL docs.
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 45.0f,
                (float) width / (float) height,
                0.1f, 100.0f);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);// OpenGL docs.
        // Reset the modelview matrix
        gl.glLoadIdentity();// OpenGL docs.
    }

}
