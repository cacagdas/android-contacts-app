package com.cacagdas.contactsapp.domain

import com.cacagdas.contactsapp.core.usecase.FlowPagingUseCase
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.data.repo.ContactRepository
import javax.inject.Inject

class GetContacts @Inject constructor(
    private val repository: ContactRepository
): FlowPagingUseCase<Contact, GetContacts.Params>() {

    data class Params(val param: Any) // provide params if necessary

    override fun invoke(params: Params) = repository.getContacts()
}
