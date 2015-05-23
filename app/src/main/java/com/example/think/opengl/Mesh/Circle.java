package com.example.think.opengl.mesh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.example.think.opengl.R;
import com.example.think.opengl.tool.BufferTools;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Think on 2015/5/23.
 */
public class Circle {

    private Bitmap pressed;
    private int texture;
    private float textures[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
    };
    private float vertices[] = {
            // x     y    z
            -1.0f,  1.0f, 0.0f,  // 0, Top Left
            -1.0f, -1.0f, 0.0f,  // 1, Bottom Left
            1.0f, -1.0f, 0.0f,  // 2, Bottom Right
            1.0f,  1.0f, 0.0f,  // 3, Top Right
    };
    private short[] indices = { 0, 1, 2, 0, 2, 3 };
    private FloatBuffer vertexBuffer, textureBuffer;
    private ShortBuffer indexBuffer;

    public Circle(Context context) {
        pressed = BitmapFactory.decodeResource(context.getResources(), R.mipmap.sand);
        vertexBuffer = BufferTools.getFloatBuffer(vertices);
        indexBuffer = BufferTools.getShortBuffer(indices);
        textureBuffer = BufferTools.getFloatBuffer(textures);
    }

    /**
     * This function draws our square on screen.
     * @param gl
     */
    public void draw(GL10 gl) {
        //显示正面
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);

//        float    theta, pai;
//        float    co, si;
//        float    r1, r2;
//        float    h1, h2;
//        float    step = 2.0f;
//        float[][] v = new float[32][3];
//        ByteBuffer vbb;
//        FloatBuffer vBuf;
//
//        vbb = ByteBuffer.allocateDirect(v.length * v[0].length * 4);
//        vbb.order(ByteOrder.nativeOrder());
//        vBuf = vbb.asFloatBuffer();
//
//        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        //gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
//        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
//
//        for (pai = -90.0f; pai < 90.0f; pai += step) {
//            int    n = 0;
//
//            r1 = (float)Math.cos(pai * Math.PI / 180.0);
//            r2 = (float)Math.cos((pai + step) * Math.PI / 180.0);
//            h1 = (float)Math.sin(pai * Math.PI / 180.0);
//            h2 = (float)Math.sin((pai + step) * Math.PI / 180.0);
//
//            for (theta = 0.0f; theta <= 360.0f; theta += step) {
//                co = (float)Math.cos(theta * Math.PI / 180.0);
//                si = -(float)Math.sin(theta * Math.PI / 180.0);
//
//                v[n][0] = (r2 * co);
//                v[n][1] = (h2);
//                v[n][2] = (r2 * si);
//                v[n + 1][0] = (r1 * co);
//                v[n + 1][1] = (h1);
//                v[n + 1][2] = (r1 * si);
//
//                vBuf.put(v[n]);
//                vBuf.put(v[n + 1]);
//
//                n += 2;
//
//                if(n>31){
//                    vBuf.position(0);
//
//                    gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, vBuf);
//                    gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
//
//                    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
//                    //gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);
//                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);
//
//                    n = 0;
//                    theta -= step;
//                }
//
//            }
//            vBuf.position(0);
//
//            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, vBuf);
//            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
//
//            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
//            //gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);
//            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);
//        }
//
//        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//        //gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
//        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    public void loadTexture(GL10 gl)
    {
        try
        {
            int[] textures = new int[1];
            gl.glGenTextures(1, textures, 0);
            texture = textures[0];
            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                    GL10.GL_CLAMP_TO_EDGE);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                    GL10.GL_CLAMP_TO_EDGE);
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, pressed, 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
