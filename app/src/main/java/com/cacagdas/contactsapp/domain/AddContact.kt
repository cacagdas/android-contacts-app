package com.cacagdas.contactsapp.domain

import com.cacagdas.contactsapp.core.base.usecase.RequestUseCase
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.data.repo.ContactRepository
import javax.inject.Inject

class AddContact @Inject constructor(
    private val repository: ContactRepository
) : RequestUseCase<AddContact.Params, Contact>() {

    data class Params(val contact: Contact)

    override suspend fun buildRequest(params: Params) =
        repository.addContact(params.contact)
}
