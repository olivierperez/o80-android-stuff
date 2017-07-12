package fr.o80.androidstuff.android;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

public final class AnimatorHelper {

    public static final int SHORT_ANIMATION = 300;

    private AnimatorHelper() {

    }

    public static void animateFadeIn(final View view) {
        animateFadeIn(view, SHORT_ANIMATION);
    }

    public static void animateFadeIn(final View view, int duration) {
        //Try to cancel any previous animation
        cancelPreviousAnimation(view);

        view.animate()
                .alpha(1f)
                .setDuration(duration)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    public static void animateFadeOut(View view) {
        animateFadeOut(view, SHORT_ANIMATION, true);
    }

    public static void animateFadeOut(final View view, int duration) {
        animateFadeOut(view, duration, true);
    }

    public static void animateFadeOut(final View view, int duration, final boolean completelyHide) {
        //Try to cancel any previous animation
        cancelPreviousAnimation(view);

        view.animate()
                .alpha(0f)
                .setDuration(duration)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (completelyHide && view.getAlpha() < 0.0001f) {
                            view.setVisibility(View.GONE);
                        }
                    }
                })
                .start();
    }

    private static void cancelPreviousAnimation(@NonNull View view) {
        Animation prevAnim = view.getAnimation();
        if (prevAnim != null) {
            prevAnim.cancel();
        }
        view.clearAnimation();
        view.animate().cancel();
    }
}
