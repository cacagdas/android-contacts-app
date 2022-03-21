package com.cacagdas.contactsapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.data.source.ContactRemoteDataSource
import com.cacagdas.contactsapp.data.source.paging.ContactsPagingSource
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val remoteDataSource: ContactRemoteDataSource
): ContactRepository {

    override fun getContacts() = Pager(
        PagingConfig(pageSize = 20)
    ) {
        ContactsPagingSource(remoteDataSource)
    }.flow

    override suspend fun getContactDetail(id: String) = remoteDataSource.getContactDetail(id)

    override suspend fun deleteContact(id: String) = remoteDataSource.deleteContact(id)

    override suspend fun updateContact(contact: Contact) = remoteDataSource.updateContact(contact)

    override suspend fun addContact(contact: Contact) = remoteDataSource.addContact(contact)
}
