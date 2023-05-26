package com.example.jucrchallenge.ui.component

import android.content.Context
import android.widget.RelativeLayout
import androidx.annotation.FloatRange
import com.airbnb.lottie.LottieAnimationView
import com.example.jucrchallenge.R

class AvailableSpaceView(context: Context) : RelativeLayout(context) {

    private val lottie : LottieAnimationView
    private var available: Float = 0.0f

    init {
        inflate(context, R.layout.widget_available_space_unifin, this)
        lottie = findViewById(R.id.ltAvailableSpace)
        lottie.loop(false)
    }

    private fun pause() {
        lottie.pauseAnimation()
        dispose()
       // lottie.progress = available
    }

    fun setAvailable(@FloatRange(from = 0.0, to = 1.0) available: Float) {
        this.available = available
//        lottie.reverseAnimationSpeed()
        var frame = 0
        lottie.addAnimatorUpdateListener {
            val percentage = 1f - it.animatedFraction
            if (frame>1 && percentage<=this.available) pause()
            frame++
        }

        lottie.playAnimation()
    }

    private fun dispose() = lottie.removeAllAnimatorListeners()

}