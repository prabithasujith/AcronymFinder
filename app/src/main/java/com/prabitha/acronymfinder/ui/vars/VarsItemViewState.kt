package com.prabitha.acronymfinder.ui.vars

import com.prabitha.acronymfinder.models.Vars

class VarsItemViewState (vars: Vars) {
    val lf = vars.lf
    val since = vars.since.toString()
    val freq = vars.freq.toString()
}