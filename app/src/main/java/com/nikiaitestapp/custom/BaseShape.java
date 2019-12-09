package com.nikiaitestapp.custom;

import android.graphics.Canvas;
import androidx.annotation.NonNull;

public abstract class BaseShape {

    private Float scaleFactor = 1f;
    public abstract void draw(@NonNull Canvas canvas);


    public void setScaleFactor(Float _scaleFactor){
        scaleFactor  = _scaleFactor;
    }

    public Float getScaleFactor (){
        return scaleFactor;
    }

   public abstract float[] getSquarePoints();

}
