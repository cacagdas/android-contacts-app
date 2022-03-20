package com.cacagdas.contactsapp.data.source

import javax.inject.Inject

class ContactRemoteDataSource @Inject constructor(
    private val service: ContactService
) {
    suspend fun getContacts(page: Long?) = service.getContacts(page)
}
