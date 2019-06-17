package com.example.mobileappsimulator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.MotionEventCompat;


@SuppressWarnings("deprecation")
public class DrawCircles extends View {
    private float x;
    private float y;
    private int radiusBig;
    final private int radiusSmall = 100;
    private Paint bigCircle;
    private Paint smallCircle;
    private boolean isJoystickMoving;
    private boolean start;
    final String elevatorPath = "set controls/flight/elevator ";
    final String aileronPath = "set controls/flight/aileron ";

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

        radiusBig = 500;
        start = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        // Account for padding
        int xPad = getPaddingLeft() + getPaddingRight();
        int yPad = getPaddingTop() + getPaddingBottom();
        float width = (float)(w - xPad);
        float height = (float)(h - yPad);
        x = width / 2;
        y = height / 2;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            radiusBig = 400;
        } else {
            radiusBig = 500;
        }
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
            x =  (float)(getWidth() / 2);
            y = (float)(getHeight() / 2);
        }

        // draw smallCircle
        canvas.drawCircle(x, y, radiusSmall, smallCircle);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                float xEvent = event.getX();
                float yEvent  = event.getY();

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

                float w, h, diameter, minX, maxX, minY, maxY;
                w = getWidth();
                h = getHeight();

                x = event.getX();
                y = event.getY();

                diameter = radiusBig * 2;
                minX = (w - diameter) / 2;
                maxX = w - (w - diameter) / 2 ;
                minY = (h - diameter) / 2 ;
                maxY = h - (h - diameter) / 2;

                if ((x + radiusSmall > maxX) || (x - radiusSmall < minX) ||
                        (y + radiusSmall > maxY) || (y - radiusSmall < minY)) {
                    break;
                }

                float normalizedX, normalizedY;

                // normalized the x & y between -1 to 1
                if (x > (float)(getWidth() / 2)) {
                    normalizedX = (((x + radiusSmall - minX)*2) / (maxX - minX)) - 1;
                } else {
                    normalizedX = (((x - radiusSmall - minX)*2) / (maxX - minX)) - 1;
                }

                if (y > (float)(getHeight() / 2)) {
                    normalizedY = (((y + radiusSmall - minY)*2) / (maxY - minY)) - 1;
                } else {
                    normalizedY = (((y - radiusSmall - minY)*2) / (maxY - minY)) - 1;
                }

                // send to server
                String elevator = elevatorPath + normalizedX + "\r\n";
                String aileron = aileronPath + normalizedY + "\r\n";
                CommandModel.getInstance().sendMessage(elevator);
                CommandModel.getInstance().sendMessage(aileron);

                // move the joystick
                invalidate();

                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                x =  (float)(getWidth() / 2);
                y = (float)(getHeight() / 2);

                // send to server
                String elevator = elevatorPath + "0" + "\r\n";
                String aileron = aileronPath + "0" + "\r\n";
                CommandModel.getInstance().sendMessage(elevator);
                CommandModel.getInstance().sendMessage(aileron);

                // move the joystick
                invalidate();

                isJoystickMoving = false;
                break;
            }
        }
        return true;
    }
}