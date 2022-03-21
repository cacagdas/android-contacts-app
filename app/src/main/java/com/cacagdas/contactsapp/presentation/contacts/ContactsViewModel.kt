package com.cacagdas.contactsapp.presentation.contacts

import androidx.paging.PagingData
import com.cacagdas.contactsapp.core.base.ContactsAppViewModel
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.domain.GetContacts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContacts: GetContacts
) : ContactsAppViewModel() {

    var contactsFlow: Flow<PagingData<Contact>>? = null

    init {
        getContacts()
    }

    fun getContacts() {
        contactsFlow = getContacts.invoke(GetContacts.Params(Unit))
    }

}
