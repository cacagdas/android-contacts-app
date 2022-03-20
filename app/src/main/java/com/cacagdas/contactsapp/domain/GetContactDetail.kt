package com.cacagdas.contactsapp.domain

import com.cacagdas.contactsapp.core.usecase.RequestUseCase
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.data.repo.ContactRepository
import javax.inject.Inject

class GetContactDetail @Inject constructor(
    private val repository: ContactRepository
) : RequestUseCase<GetContactDetail.Params, Contact>() {

    data class Params(val id: String)

    override suspend fun buildRequest(params: Params) =
        repository.getContactDetail(params.id)
}
