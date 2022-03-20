package com.cacagdas.contactsapp.domain

import com.cacagdas.contactsapp.core.base.usecase.RequestUseCase
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.data.repo.ContactRepository
import javax.inject.Inject

class DeleteContact @Inject constructor(
    private val repository: ContactRepository
) : RequestUseCase<DeleteContact.Params, Contact>() {

    data class Params(val id: String)

    override suspend fun buildRequest(params: Params) =
        repository.deleteContact(params.id)
}
