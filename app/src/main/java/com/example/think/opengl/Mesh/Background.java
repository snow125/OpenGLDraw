package com.example.think.opengl.Mesh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.example.think.opengl.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Think on 2015/5/22.
 */
public class Background {

    private Bitmap background;
    private int texture;
    private float textures[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
    };
    private float vertices[] = {
            // x     y    z
            -1.0f,  2.0f, 0.0f,  // 0, Top Left
            -1.0f, -2.0f, 0.0f,  // 1, Bottom Left
            1.0f, -2.0f, 0.0f,  // 2, Bottom Right
            1.0f,  2.0f, 0.0f,  // 3, Top Right
    };
    private short[] indices = { 0, 1, 2, 0, 2, 3 };
    private FloatBuffer vertexBuffer, textureBuffer;
    private ShortBuffer indexBuffer;

    public Background(Context context) {
        background = BitmapFactory.decodeResource(context.getResources(), R.mipmap.sand);
        vertexBuffer = getFloatBuffer(vertices);
        indexBuffer = getShortBuffer(indices);
        textureBuffer = getFloatBuffer(textures);
    }

    public void draw(GL10 gl){
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
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, background, 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private FloatBuffer getFloatBuffer(float[] arr){
        FloatBuffer mBuffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
        qbb.order(ByteOrder.nativeOrder());
        mBuffer = qbb.asFloatBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }

    private ShortBuffer getShortBuffer(short[] arr){
        ShortBuffer sb;
        ByteBuffer ibb
                = ByteBuffer.allocateDirect(arr.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        sb = ibb.asShortBuffer();
        sb.put(arr);
        sb.position(0);
        return sb;
    }

}
