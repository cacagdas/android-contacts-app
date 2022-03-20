package com.cacagdas.contactsapp.presentation.detail

import com.cacagdas.contactsapp.core.base.ContactsAppViewModel
import com.cacagdas.contactsapp.domain.GetContactDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactDetailViewModel @Inject constructor(
    private val getContactDetail: GetContactDetail
) : ContactsAppViewModel() {

}
