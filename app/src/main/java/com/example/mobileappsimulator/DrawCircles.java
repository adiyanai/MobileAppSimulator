package com.example.mobileappsimulator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.MotionEventCompat;


public class DrawCircles extends View {
    static int x;
    static int y;
    final static int radiusBig = 500;
    final static int radiusSmall = 100;
    private Paint bigCircle;
    private Paint smallCircle;
    private boolean isJoystickMoving;

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

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int xBigCircle = getWidth() / 2;
        int yBigCircle = getHeight() / 2;

        canvas.drawRGB(51, 153, 255);

        // draw bigCircle
        canvas.drawCircle(xBigCircle, yBigCircle, radiusBig, bigCircle);

        // draw smallCircle
        canvas.drawCircle(xBigCircle, yBigCircle, radiusSmall, smallCircle);
    }

    // todo - understand how to start and finish with the circle in the middle
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                isJoystickMoving = true;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
              /*  if (!isJoystickMoving)
                    return true;
                int x = (int)event.getX();
                int y = (int)event.getY();
                if (x >= maze[0].length || pos.y >= maze.length) {
                    isJoystickMoving = false;
                } else if (maze[pos.y][pos.x] == 0) {
                    playerPos = pos;
                    invalidate();
                } else {
                    isJoystickMoving = false;
                }*/
                invalidate();
                break;

            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isJoystickMoving = false;
                break;
        }
        return true;
    }
}