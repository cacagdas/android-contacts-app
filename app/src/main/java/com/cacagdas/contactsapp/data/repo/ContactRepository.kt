package com.cacagdas.contactsapp.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.cacagdas.contactsapp.data.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getContacts(name: String?): LiveData<PagingData<Contact>>

    suspend fun getContactDetail(id: String): Contact

    suspend fun deleteContact(id: String): Contact

    suspend fun updateContact(contact: Contact): Contact

    suspend fun addContact(contact: Contact): Contact
}
