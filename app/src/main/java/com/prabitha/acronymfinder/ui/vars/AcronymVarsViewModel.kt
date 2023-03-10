package com.prabitha.acronymfinder.ui.vars

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prabitha.acronymfinder.models.Vars

class AcronymVarsViewModel : ViewModel() {
    val varsList = MutableLiveData<List<Vars>>()
    fun setResultFromBundle(vars: List<Vars>){
        varsList.postValue(vars)
    }
}