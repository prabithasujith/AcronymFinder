package com.prabitha.acronymfinder.ui.acronyms

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prabitha.acronymfinder.data.AcronymRepository
import com.prabitha.acronymfinder.models.Acronyms
import com.prabitha.acronymfinder.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class AcronymViewModel @Inject constructor(private val repo: AcronymRepository) : ViewModel() {

    val acronyms = MutableLiveData<List<Acronyms>>()
    val isLoading = ObservableBoolean(false)
    val showErrorMessage = ObservableBoolean(true)
    val errorMessage = ObservableField(Constants.NO_DATA)
    private var job: Job? = null

    fun getAcronyms(query: String) {
        viewModelScope.launch {
            showErrorMessage.set(false)
            isLoading.set(true)
            val response = repo.getAcronyms(query)
            if (response.isSuccessful) {
                val result = response.body() ?: emptyList()
                when {
                    result.isNotEmpty() -> acronyms.value = result
                    else -> handleError(Constants.NO_DATA_FOUND)
                }
            } else handleError(response.message())
            isLoading.set(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun handleError(message: String) {
        errorMessage.set(message)
        showErrorMessage.set(true)
    }
}