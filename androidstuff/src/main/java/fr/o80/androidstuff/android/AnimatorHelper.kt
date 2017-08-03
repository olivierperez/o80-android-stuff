@file:JvmName("AnimatorHelper")

package fr.o80.androidstuff.android

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.animation.DecelerateInterpolator

val SHORT_ANIMATION = 300

@JvmOverloads fun View.fadeIn(duration: Int = SHORT_ANIMATION) {
    //Try to cancel any previous animation
    cancelPreviousAnimation()

    animate()
            .alpha(1f)
            .setDuration(duration.toLong())
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    visibility = View.VISIBLE
                }
            })
            .start()
}

@JvmOverloads fun View.fadeOut(duration: Int = SHORT_ANIMATION, completelyHide: Boolean = true) {
    //Try to cancel any previous animation
    cancelPreviousAnimation()

    animate()
            .alpha(0f)
            .setDuration(duration.toLong())
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (completelyHide && alpha < 0.0001f) {
                        visibility = View.GONE
                    }
                }
            })
            .start()
}

private fun View.cancelPreviousAnimation() {
    animation?.cancel()
    clearAnimation()
    animate().cancel()
}
