package br.com.douglasffilho.takecontrol.services;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.text.MessageFormat;

import br.com.douglasffilho.takecontrol.handlers.KeyboardEventHandler;
import br.com.douglasffilho.takecontrol.handlers.MouseEventHandler;

public class AccessibilityHandleService extends AccessibilityService {

    @Override
    public void onCreate() {
        super.onCreate();

        KeyboardEventHandler.registerAccessibilityService(this);
        MouseEventHandler.registerAccessibilityService(this);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {
        this.getApplication().onTerminate();
    }

    public void moveUp() {
        Log.i("AccessibilityHandleService", "Moving UP!");
    }

    public void moveDown() {
        Log.i("AccessibilityHandleService", "Moving DOWN!");
    }

    public void moveLeft() {
        Log.i("AccessibilityHandleService", "Moving LEFT!");
    }

    public void moveRight() {
        Log.i("AccessibilityHandleService", "Moving RIGHT!");
    }

    public void granade() {
        Log.i("AccessibilityHandleService", "Granade!");
    }

    public void jump() {
        Log.i("AccessibilityHandleService", "Jump!");
    }

    public void aim() {
        Log.i("AccessibilityHandleService", "Aim!");
    }

    public void squat() {
        Log.i("AccessibilityHandleService", "Squat!");
    }

    public void shot() {
        Log.i("AccessibilityHandleService", "Shot!");
    }

    public void moveTarget(float xPercent, float yPercent) {
        Log.i("AccessibilityHandleService", MessageFormat.format(
                "Move to x={0} y={1}",
                xPercent,
                yPercent
        ));

        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;

        final float y = height * (yPercent / 100);
        final float x = width * (xPercent / 100);

        final Path swipeUpPath = new Path();
        swipeUpPath.moveTo(x, y);
        swipeUpPath.lineTo(x, y);
        long swipeDuration = 10L;

        final GestureDescription.StrokeDescription swipeUpGestureStroke = new GestureDescription.StrokeDescription(
                swipeUpPath,
                0L,
                swipeDuration,
                false
        );

        final GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        gestureBuilder.addStroke(swipeUpGestureStroke);

        final GestureDescription swipeUp = gestureBuilder.build();

        dispatchGesture(swipeUp, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }

}
