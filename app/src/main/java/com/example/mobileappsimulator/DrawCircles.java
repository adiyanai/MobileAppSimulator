package com.example.mobileappsimulator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.MotionEventCompat;


@SuppressWarnings("deprecation")
public class DrawCircles extends View {
    static int x;
    static int y;
    final static int radiusBig = 500;
    final static int radiusSmall = 100;
    private Paint bigCircle;
    private Paint smallCircle;
    private boolean isJoystickMoving;
    private boolean start;
    private String elevatorPath = "set controls/flight/elevator ";
    private String aileronPath = "set controls/flight/aileron ";

    @SuppressLint("ClickableViewAccessibility")
    public DrawCircles(Context context) {
        super(context);
        isJoystickMoving = false;

        bigCircle = new Paint();
        bigCircle.setStyle(Paint.Style.FILL);
        bigCircle.setAntiAlias(true);
        bigCircle.setColor(Color.GRAY);

        smallCircle = new Paint();
        smallCircle.setStyle(Paint.Style.FILL);
        smallCircle.setAntiAlias(true);
        smallCircle.setColor(Color.BLACK);

        start = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int xBigCircle = getWidth() / 2;
        int yBigCircle = getHeight() / 2;

        canvas.drawRGB(51, 153, 255);

        // draw bigCircle
        canvas.drawCircle(xBigCircle, yBigCircle, radiusBig, bigCircle);

        if (start) {
            x =  getWidth() / 2;
            y = getHeight() / 2;
        }

        // draw smallCircle
        canvas.drawCircle(x, y, radiusSmall, smallCircle);
    }

    // todo - handle the send message problem
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                int xEvent =  (int)event.getX();
                int yEvent  = (int)event.getY();

                if ((xEvent >= x - radiusSmall && xEvent <= x + radiusSmall)
                        && (yEvent >= y - radiusSmall && yEvent <= y + radiusSmall)) {
                    isJoystickMoving = true;
                    start = false;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!isJoystickMoving) {
                    return true;
                }
                x =  (int)event.getX();
                y = (int)event.getY();
                invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                /*String xEvent =  String.valueOf((int)event.getX());
                String yEvent  = String.valueOf((int)event.getY());
                String elevator = elevatorPath + yEvent + "\r\n";
                String aileron = aileronPath + xEvent + "\r\n";
                CommandModel.getInstance().sendMessage(elevator);
                CommandModel.getInstance().sendMessage(aileron);*/
                x =  getWidth() / 2;
                y = getHeight() / 2;
                invalidate();
                isJoystickMoving = false;
                break;
            }
        }
        return true;
    }
}