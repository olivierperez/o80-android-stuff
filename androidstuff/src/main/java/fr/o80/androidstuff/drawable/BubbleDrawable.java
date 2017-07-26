package fr.o80.androidstuff.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

public class BubbleDrawable extends Drawable {

    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;

    private float cornerRad = 0;
    private Rect padding = new Rect();
    private int pointerWidth = 40;
    private int pointerHeight = 40;
    private int pointerAlignment = CENTER;

    private Paint paint;
    private Path pointer;
    private int boxWidth;
    private int boxHeight;

    public BubbleDrawable() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        updatePointerPath();
    }

    /**
     * Set the place to display the pointer. Default value {@code BubbleDrawable.CENTER}
     * @param pointerAlignment one of the three constants: {@code BubbleDrawable.LEFT}, {@code BubbleDrawable.CENTER}, {@code BubbleDrawable.RIGHT}
     * @return this
     */
    public BubbleDrawable pointerAlignment(int pointerAlignment) {
        if (pointerAlignment != BubbleDrawable.LEFT
                && pointerAlignment != BubbleDrawable.CENTER
                && pointerAlignment != BubbleDrawable.RIGHT) {
            throw new IllegalArgumentException("Invalid pointerAlignment argument");
        } else {
            this.pointerAlignment = pointerAlignment;
        }
        return this;
    }

    /**
     * Set the color of the bubble. Default value Color.RED
     * @param color the desired color
     * @return this
     */
    public BubbleDrawable color(@ColorInt int color) {
        paint.setColor(color);
        return this;
    }

    /**
     * Set the corner radius. Default value 0
     * @param cornerRadius the corner radius
     * @return this
     */
    public BubbleDrawable cornerRadius(float cornerRadius) {
        this.cornerRad = cornerRadius;
        return this;
    }

    /**
     * Set the padding. Default values 0
     * @return this
     */
    public BubbleDrawable padding(int left, int top, int right, int bottom) {
        padding.left = left;
        padding.top = top;
        padding.right = right;
        padding.bottom = bottom;
        return this;
    }

    /**
     * Set the pointer's width and height. Default values 40
     * @return this
     */
    public BubbleDrawable setPointerSize(int width, int height) {
        this.pointerWidth = width;
        this.pointerHeight = height;
        updatePointerPath();
        return this;
    }

    private void updatePointerPath() {
        pointer = new Path();
        pointer.setFillType(Path.FillType.EVEN_ODD);

        // Set the starting point
        pointer.moveTo(pointerHorizontalStart(), boxHeight);

        // Define the lines
        pointer.rLineTo(pointerWidth, 0);
        pointer.rLineTo(-(pointerWidth / 2), pointerHeight);
        pointer.rLineTo(-(pointerWidth / 2), -pointerHeight);
        pointer.close();
    }

    private float pointerHorizontalStart() {
        float x = 0;
        switch (pointerAlignment) {
            case LEFT:
                x = cornerRad;
                break;
            case CENTER:
                x = (boxWidth / 2) - (pointerWidth / 2);
                break;
            case RIGHT:
                x = boxWidth - cornerRad - pointerWidth;
                break;
            default:
        }
        return x;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        RectF boxRect = new RectF(0.0f, 0.0f, boxWidth, boxHeight);
        canvas.drawRoundRect(boxRect, cornerRad, cornerRad, paint);
        canvas.drawPath(pointer, paint);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public void setAlpha(int alpha) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean getPadding(@NonNull Rect padding) {
        padding.set(this.padding);

        // Adjust the padding to include the height of the pointer
        padding.bottom += pointerHeight;
        return true;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        boxWidth = bounds.width();
        boxHeight = getBounds().height() - pointerHeight;
        super.onBoundsChange(bounds);
        updatePointerPath();
    }
}
