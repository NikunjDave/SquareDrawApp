package com.nikiaitestapp.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SquareView extends View {

    private List<Square> squareList = new ArrayList<>();

    private Paint borderPaint ;


    public SquareView(Context context) {
        this(context, null);
    }

    public SquareView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(Color.BLUE);
        borderPaint.setStrokeWidth(10);
        borderPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < squareList.size(); i++){
            Square square = squareList.get(i);
            square.draw(canvas);
        }
    }



    @Override
    public boolean onTouchEvent (MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:

                PointF midPoint = new PointF();
                midPoint.x = event.getX();
                midPoint.y = event.getY();

                Square square = new Square(midPoint,borderPaint);
                squareList.add(square);
                break;

        }
        invalidate();
        return true;
    }

    public void clearAll(){
        squareList.clear();
        invalidate();
    }

    public void scaleSquare(float factor) {
        if (squareList != null && squareList.size() > 0) {
            for (int i = 0; i < squareList.size(); i++) {
                squareList.get(i).setScaleFactor(factor);
            }
            invalidate();
        }
    }

    public List<Square> getSquareList() {
        return squareList;
    }
}

