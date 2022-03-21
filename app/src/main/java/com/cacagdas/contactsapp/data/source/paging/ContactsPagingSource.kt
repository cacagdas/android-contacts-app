package com.cacagdas.contactsapp.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.data.source.ContactRemoteDataSource

class ContactsPagingSource(
    private val remoteDataSource: ContactRemoteDataSource,
    private val name: String?
): PagingSource<Long, Contact>() {
    override fun getRefreshKey(state: PagingState<Long, Contact>): Long? = null

    override suspend fun load(params: LoadParams<Long>) = runCatching {
        val page = params.key ?: 0
        getResponse(page + 1)?.let {
            LoadResult.Page(
                data = it,
                prevKey = null,
                nextKey = if (it.isNotEmpty()) page + 1 else null
            )
        } ?: run {
            LoadResult.Error(Throwable("Something bad happened"))
        }
    }.getOrElse {
        LoadResult.Error(it)
    }

    private suspend fun getResponse(page: Long?): List<Contact>? =
        remoteDataSource.getContacts(page, name)
}
