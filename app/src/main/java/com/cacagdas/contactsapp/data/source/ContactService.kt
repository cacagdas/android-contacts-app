package com.cacagdas.contactsapp.data.source

import com.cacagdas.contactsapp.data.model.Contact
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactService {

    companion object {
        private const val PATH_CONTACT = "contacts"
    }

    @GET("$PATH_CONTACT/{page}/{limit}")
    suspend fun getContacts(
        @Path("page") page: Long?,
        @Path("limit") limit: Int? = 20,
    ): List<Contact>?
}
