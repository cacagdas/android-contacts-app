package com.cacagdas.contactsapp.presentation.contacts

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cacagdas.contactsapp.core.base.ContactsAppViewModel
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.domain.GetContacts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    getContacts: GetContacts
) : ContactsAppViewModel() {

    var contactsFlow: Flow<PagingData<Contact>>? =
        getContacts.invoke(GetContacts.Params(Unit))
            .cachedIn(viewModelScope)

}
