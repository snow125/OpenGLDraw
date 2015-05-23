package com.example.think.opengl.tool;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Think on 2015/5/23.
 */
public class BufferTools {

    public static FloatBuffer getFloatBuffer(float[] arr){
        FloatBuffer mBuffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
        qbb.order(ByteOrder.nativeOrder());
        mBuffer = qbb.asFloatBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }

    public static ShortBuffer getShortBuffer(short[] arr){
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
