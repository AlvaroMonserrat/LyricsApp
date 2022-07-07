package com.rrat.lyricsapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
    CLASS OPEN: BaseViewModel Class is inheritable
    protected: It is only visible for their subclasses
*/
open class BaseViewModel<T> : ViewModel() {
    fun uiState(): LiveData<T> = uiState
    protected val uiState: MutableLiveData<T> = MutableLiveData()
}