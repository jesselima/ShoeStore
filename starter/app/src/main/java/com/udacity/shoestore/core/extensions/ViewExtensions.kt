package com.udacity.shoestore.core.extensions

import android.view.View
import android.view.animation.AnimationUtils
import com.udacity.shoestore.R

/**
 * Created by jesselima on 17/11/20.
 * This is a part of the project ShoeStore.
 */

fun View.showWithFadeIn() {
    this.visibility = View.VISIBLE
    this.startAnimation(
            AnimationUtils.loadAnimation(
                    context,
                    R.anim.fade_in_animation
            )
    )
}

fun View.hideWithFadeOut() {
    this.startAnimation(AnimationUtils.loadAnimation(context,
            R.anim.fade_out_animation
    ))
    this.visibility = View.GONE
}