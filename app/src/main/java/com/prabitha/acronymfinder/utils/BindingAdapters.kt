package com.prabitha.acronymfinder.utils

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter(value = ["view_visibility"])
fun setVisibility(view: View, isVisible: Boolean) {

    if (isVisible)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}
