package br.com.douglasffilho.takecontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class FreeHandCanvas extends View {
    private static final Float LINE_WIDTH = 20f;

    private Map<Integer, BiFunction<Float, Float, Boolean>> EVENT_HANDLER = new ConcurrentHashMap<>();

    private Paint paint;
    private Path path;

    public FreeHandCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.paint = new Paint();
        this.path = new Path();

        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.BLUE);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStrokeWidth(LINE_WIDTH);

        EVENT_HANDLER.put(MotionEvent.ACTION_DOWN, (x, y) -> {
            this.path.moveTo(x, y);
            this.path.lineTo(x + 1, y + 1);
            return true;
        });

        EVENT_HANDLER.put(MotionEvent.ACTION_UP, (x, y) -> {
            invalidate();
            return true;
        });

        EVENT_HANDLER.put(MotionEvent.ACTION_MOVE, (x, y) -> {
            this.path.lineTo(x, y);
            invalidate();
            return true;
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(this.path, this.paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        return Objects.requireNonNull(EVENT_HANDLER.get(event.getAction())).apply(x, y);
    }

}
