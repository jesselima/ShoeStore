package com.udacity.shoestore.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
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

fun View.showListItemWithFadeIn() {
    this.startAnimation(AnimationUtils.loadAnimation(context,
        R.anim.fade_in
    ))
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) : View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
