package com.example.fcmilan.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _isHomeScreen = MutableLiveData<Boolean>()
    val isHomeCurrent: LiveData<Boolean> = _isHomeScreen
    fun setIsHomeScreen(isHome:Boolean) {
        _isHomeScreen.value = isHome
    }
}