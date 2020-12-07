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

fun String.mapStringToImageResource() : Int {
    return when(this) {
        "model_shoe_01" -> R.drawable.model_shoe_01
        "model_shoe_02" -> R.drawable.model_shoe_02
        "model_shoe_03" -> R.drawable.model_shoe_03
        "model_shoe_04" -> R.drawable.model_shoe_04
        "model_shoe_05" -> R.drawable.model_shoe_05
        "model_shoe_06" -> R.drawable.model_shoe_06
        "model_shoe_07" -> R.drawable.model_shoe_07
        "model_shoe_08" -> R.drawable.model_shoe_08
        "model_shoe_09" -> R.drawable.model_shoe_09
        "model_shoe_10" -> R.drawable.model_shoe_10
        else -> R.drawable.model_shoe_default
    }
}

fun getRandomImageKey() : String {
    return listOf(
        "model_shoe_01",
        "model_shoe_02",
        "model_shoe_03",
        "model_shoe_04",
        "model_shoe_05",
        "model_shoe_06",
        "model_shoe_07",
        "model_shoe_08",
        "model_shoe_09",
        "model_shoe_10"
    ).shuffled().first()
}
