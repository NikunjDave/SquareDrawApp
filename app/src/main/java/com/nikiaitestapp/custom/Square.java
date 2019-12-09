package com.nikiaitestapp.custom;

import android.graphics.*;
import androidx.annotation.NonNull;

public class Square extends BaseShape {

    private final float[] squarePoints = new float[8];

    private final RectF rectSquare = new RectF();
    private final Paint mPaint;
    private final PointF centeredPoints;
    private final int DEFAULT_SIZE = 50;
    private Matrix matrix;

    public Square(PointF point, Paint paint) {
        this.centeredPoints = point;
        this.mPaint = paint;
        matrix = new Matrix();
        setupCoordinates();
    }


    /**
     * set initial coordinates  of  square
     */
    private void setupCoordinates() {
        rectSquare.left = centeredPoints.x - DEFAULT_SIZE;
        rectSquare.right = centeredPoints.x + DEFAULT_SIZE;
        rectSquare.top = centeredPoints.y - DEFAULT_SIZE;
        rectSquare.bottom = centeredPoints.y + DEFAULT_SIZE;


        // x1 and y1
        squarePoints[0] = rectSquare.left;
        squarePoints[1] = rectSquare.top;

        // x2 and y2
        squarePoints[2] = rectSquare.right;
        squarePoints[3] = rectSquare.top;

        // x3 and y3
        squarePoints[4] = rectSquare.left;
        squarePoints[5] = rectSquare.bottom;


        //x4 and y4
        squarePoints[6] = rectSquare.right;
        squarePoints[7] = rectSquare.bottom;


    }


    /**
     * draw rectangle on canvas
     * @param canvas canvas instance
     */
    @Override
    public void draw(@NonNull Canvas canvas) {
        Float scaleFactor = getScaleFactor();


        matrix.postScale(1f + scaleFactor, 1f + scaleFactor, rectSquare.centerX(), rectSquare.centerY());
        //Apply the matrix to a RectF
        RectF scaleRect = new RectF(rectSquare);
        matrix.mapRect(scaleRect);
        canvas.drawRect(scaleRect, mPaint);
        matrix.reset();


    }


    /**
     * return list of square points
     * @return
     */
    @Override
    public float[] getSquarePoints() {
        return squarePoints;
    }


}
