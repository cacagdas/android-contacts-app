package com.cacagdas.contactsapp.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class ContactsAppViewModel : ViewModel() {

    val showLoading = MutableLiveData(false)
    val showLoadingLiveData: LiveData<Boolean> = showLoading

    fun viewModelLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Main, block = block)
    }
}
