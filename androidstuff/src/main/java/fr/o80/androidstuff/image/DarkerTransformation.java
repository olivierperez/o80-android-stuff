package fr.o80.androidstuff.image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.squareup.picasso.Transformation;

public class DarkerTransformation implements Transformation {

    private int mColor;

    public DarkerTransformation(int alpha) {
        this.mColor = Color.argb(alpha, 0, 0, 0);
    }

    @Override
    public Bitmap transform(Bitmap source) {

        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColorFilter(new PorterDuffColorFilter(mColor, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(source, 0, 0, paint);
        source.recycle();

        return bitmap;
    }

    @Override
    public String key() {
        return "DarkerTransformation(color=" + mColor + ")";
    }
}
