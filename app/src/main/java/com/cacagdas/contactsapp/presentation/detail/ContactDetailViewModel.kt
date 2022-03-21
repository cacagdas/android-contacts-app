package com.cacagdas.contactsapp.presentation.detail

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.cacagdas.contactsapp.core.base.ContactsAppViewModel
import com.cacagdas.contactsapp.core.util.extension.checkResult
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.domain.GetContactDetail
import com.cacagdas.contactsapp.domain.UpdateContact
import com.cacagdas.contactsapp.presentation.detail.ContactDetailFragment.Companion.ARG_CONTACT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getContactDetail: GetContactDetail,
    private val updateContact: UpdateContact
) : ContactsAppViewModel() {

    private val contactDetail = MutableLiveData<Contact>()
    val contactDetailLiveData: LiveData<Contact> = contactDetail

    private val contactUpdated = MutableLiveData<Unit>()
    val contactUpdatedLiveData: LiveData<Unit> = contactUpdated

    private val contactId = savedStateHandle.get<String>(ARG_CONTACT_ID) as String

    init {
        showLoading.value = true
        viewModelLaunch {
            checkResult(getContactDetail.invoke(GetContactDetail.Params(contactId))) {
                contactDetail.value = it
                showLoading.value = false
            }
        }
    }

    fun setName(text: Editable?) {
        contactDetail.value?.name = text.toString()
    }

    fun setSurname(text: Editable?) {
        contactDetail.value?.surname = text.toString()
    }

    fun setNumber(text: Editable?) {
        contactDetail.value?.number = text.toString().toLongOrNull()
    }

    fun setCompany(text: Editable?) {
        contactDetail.value?.companyName = text.toString()
    }

    fun setDepartment(text: Editable?) {
        contactDetail.value?.department = text.toString()
    }

    fun setEmail(text: Editable?) {
        contactDetail.value?.email = text.toString()
    }

    fun onUpdateClicked() = viewModelLaunch {
        contactDetail.value?.let {
            showLoading.value = true
            checkResult(updateContact.invoke(UpdateContact.Params(it))) {
                contactUpdated.value = Unit
                showLoading.value = false
            }
        }
    }

}
