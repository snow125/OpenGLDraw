package com.example.think.opengl.view;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Think on 2015/5/23.
 */
public class PointGroup {

    private static final int SANDCOUNT = 20;
    private static final int SANDDENSITY = 80;
    private static final int SANDRADIUS = 50;
    private float x, y;
    private float radius = 1;
    private List<Point> points = new ArrayList<>();
    private int seed = 100;

    public PointGroup(float x, float y) {
        this.x = x;
        this.y = y;
        setUpPoints();
    }

    private void setUpPoints(){
        for (float i=(x-SANDRADIUS+radius); i<(x+SANDRADIUS-radius); i++){
            for (float j=(y-SANDRADIUS+radius); j<(y+SANDRADIUS-radius); j++){
                if(isInCircle(i, j) && getRandom()>SANDDENSITY){
                    points.add(new Point(i, j));
                }
            }
        }
    }

    private boolean isInCircle(float xa, float ya){
        if(Math.sqrt(Math.pow(xa - x, 2) + Math.pow(ya - y, 2)) < SANDRADIUS-radius){
            return true;
        }else{
            return false;
        }
    }

    private int getRandom(){
        Random r = new Random(seed);
        int out = r.nextInt();
        if(out < 0){
            out = -out;
        }
        seed = out;
        return out%100;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
