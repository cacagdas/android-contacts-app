package com.cacagdas.contactsapp.domain

import com.cacagdas.contactsapp.core.base.usecase.PagingUseCase
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.data.repo.ContactRepository
import javax.inject.Inject

class GetContacts @Inject constructor(
    private val repository: ContactRepository
): PagingUseCase<Contact, GetContacts.Params>() {

    data class Params(val name: String?)

    override fun invoke(params: Params) = repository.getContacts(params.name)
}
