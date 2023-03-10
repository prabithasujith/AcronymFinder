package com.prabitha.acronymfinder.ui.acronyms

import androidx.lifecycle.LiveData
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

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _showErrorMessage = MutableLiveData(true)
    val showErrorMessage: LiveData<Boolean>
        get() = _showErrorMessage

    private val _errorMessage = MutableLiveData(Constants.NO_DATA)
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(
            throwable.message ?: Constants.EMPTY_STRING
        )
    }

    fun getAcronyms(query: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _showErrorMessage.postValue(false)
            _isLoading.postValue(true)
            val response = repo.getAcronyms(query)
            if (response.isSuccessful) {
                val result = response.body() ?: emptyList()
                when {
                    result.isNotEmpty() -> acronyms.postValue(result)
                    else -> handleError(Constants.NO_DATA_FOUND)
                }
            } else handleError(response.message())
            _isLoading.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun handleError(message: String) {
        acronyms.postValue(emptyList())
        _errorMessage.postValue(message)
        _showErrorMessage.postValue(true)
    }
}