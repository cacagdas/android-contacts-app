package com.cacagdas.contactsapp.presentation.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cacagdas.contactsapp.core.base.ContactsAppViewModel
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.domain.GetContacts
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContacts: GetContacts
) : ContactsAppViewModel() {

    private val query = MutableLiveData<String?>()
    val contacts: LiveData<PagingData<Contact>> = query.switchMap {
        getContacts.invoke(GetContacts.Params(it))
    }.cachedIn(viewModelScope)

    init {
        getContacts()
    }

    fun getContacts() {
        query.value = null
    }

    fun searchByName(search: String?) {
        if (search != query.value)
            query.value = search
    }

}
