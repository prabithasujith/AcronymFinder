package com.prabitha.acronymfinder.utils

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter(value = ["view_visibility"])
fun setRecyclerViewItems(view: View, isLoading: Boolean) {

    if (isLoading)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}
