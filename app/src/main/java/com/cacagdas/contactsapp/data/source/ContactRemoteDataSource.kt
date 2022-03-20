package com.cacagdas.contactsapp.data.source

import com.cacagdas.contactsapp.data.model.Contact
import javax.inject.Inject

class ContactRemoteDataSource @Inject constructor(
    private val service: ContactService
) {
    suspend fun getContacts(page: Long?) = service.getContacts(page)

    suspend fun getContactDetail(id: String) = service.getContactDetail(id)

    suspend fun deleteContact(id: String) = service.deleteContact(id)

    suspend fun updateContact(contact: Contact) = service.updateContact(contact.id, contact)
}
