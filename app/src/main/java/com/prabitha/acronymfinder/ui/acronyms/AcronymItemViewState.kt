package com.prabitha.acronymfinder.ui.acronyms

import com.prabitha.acronymfinder.models.LongForms

class AcronymItemViewState(longForm: LongForms) {
    val lf = longForm.lf
    val freq = longForm.freq.toString()
    val since = longForm.since.toString()
}