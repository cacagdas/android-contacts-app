package com.cacagdas.contactsapp.presentation.addcontact

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cacagdas.contactsapp.core.base.ContactsAppViewModel
import com.cacagdas.contactsapp.core.util.extension.checkResult
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.domain.AddContact
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val addContact: AddContact
) : ContactsAppViewModel() {

    private val contactAdded = MutableLiveData<Unit>()
    val contactAddedLiveData: LiveData<Unit> = contactAdded

    private val routeBack = MutableLiveData<Unit>()
    val routeBackLiveData: LiveData<Unit> = routeBack

    private var anyChangesMade: Boolean = false
    
    private var contactName: String? = null
    private var contactSurname: String? = null
    private var contactNumber: String? = null
    private var contactCompany: String? = null
    private var contactDepartment: String? = null
    private var contactEmail: String? = null

    private fun addContact() = viewModelLaunch {
        showLoading.value = true
        checkResult(addContact.invoke(AddContact.Params(
            Contact(
                null,
                createdAt = getCurrentTime(),
                name = contactName,
                surname = contactSurname,
                number = contactNumber?.toLongOrNull(),
                companyName = contactCompany,
                department = contactDepartment,
                email = contactEmail
            )
        ))) {
            contactAdded.value = Unit
            showLoading.value = false
        }
    }

    private fun getCurrentTime(): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        return runCatching {
            simpleDateFormat.format(Calendar.getInstance().time)
        }.getOrNull()
    }

    fun onAddContactClicked() {
        if (!anyChangesMade) {
            routeBack.value = Unit
            return
        }
        addContact()
    }

    fun setName(text: Editable?) {
        anyChangesMade = true
        contactName = text.toString()
    }

    fun setSurname(text: Editable?) {
        anyChangesMade = true
        contactSurname = text.toString()
    }

    fun setNumber(text: Editable?) {
        anyChangesMade = true
        contactNumber = text.toString()
    }

    fun setCompany(text: Editable?) {
        anyChangesMade = true
        contactCompany = text.toString()
    }

    fun setDepartment(text: Editable?) {
        anyChangesMade = true
        contactDepartment = text.toString()
    }

    fun setEmail(text: Editable?) {
        anyChangesMade = true
        contactEmail = text.toString()
    }
}
