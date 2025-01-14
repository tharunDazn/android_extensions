package com.example.demoapplication.utils

import android.animation.ValueAnimator
import androidx.core.view.isVisible
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerUtils {
    private val shimmerBuilder = Shimmer.AlphaHighlightBuilder()

    fun reverse(duration: Long = 2000L): Shimmer {
        return shimmerBuilder
            .setDuration(duration)
            .setRepeatMode(ValueAnimator.REVERSE)
            .build()
    }

    fun thinStraightTransparent(): Shimmer {
        return shimmerBuilder
            .setBaseAlpha(0.1f)
            .setDropoff(0.1f)
            .setTilt(0f)
            .build()
    }

    fun sweep(
        direction: Int = Shimmer.Direction.TOP_TO_BOTTOM,
        tilt: Float = 0f
    ): Shimmer {
        return shimmerBuilder
            .setDirection(direction)
            .setTilt(tilt)
            .build()
    }

    fun spotlight(
        alpha: Float = 0f,
        duration: Long = 2000L,
        dropOff: Float = 0.1f,
        intensity: Float = 0.35f,
        shape: Int = Shimmer.Shape.RADIAL
    ): Shimmer {
        return shimmerBuilder
            .setBaseAlpha(alpha)
            .setDuration(duration)
            .setDropoff(dropOff)
            .setIntensity(intensity)
            .setShape(shape)
            .build()
    }
}

fun ShimmerFrameLayout.show() {
    isVisible = true
    showShimmer(true)
}

fun ShimmerFrameLayout.hide() {
    stopShimmer()
    hideShimmer()
    isVisible = false
}