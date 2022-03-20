package com.cacagdas.contactsapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
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
}