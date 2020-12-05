package com.udacity.shoestore.core.extensions

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.udacity.shoestore.R

/**
 * Created by jesselima on 5/12/20.
 * This is a part of the project ShoeStore.
 */
fun Fragment.showDialogWithOptionalActions(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String = resources.getString(R.string.label_ok),
        positiveButtonAction: (() -> Unit?)? = null,
        negativeButtonText: String = resources.getString(R.string.label_cancel),
        negativeButtonAction: (() -> Unit?)? = null
) {
    MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { _, _ ->
            positiveButtonAction?.invoke()
        }
        .setNegativeButton(negativeButtonText) { _, _ ->
            negativeButtonAction?.invoke()
        }
        .show()
}
