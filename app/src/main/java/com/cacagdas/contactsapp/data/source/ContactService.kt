package com.cacagdas.contactsapp.data.source

import com.cacagdas.contactsapp.data.model.Contact
import retrofit2.http.*

interface ContactService {

    companion object {
        private const val PATH_CONTACT = "contacts"
    }

    @GET(PATH_CONTACT)
    suspend fun getContacts(
        @Query("page") page: Long?,
        @Query("name") name: String?,
        @Query("limit") limit: Int? = 20,
    ): List<Contact>?

    @GET("$PATH_CONTACT/{id}")
    suspend fun getContactDetail(
        @Path("id") id: String,
    ): Contact

    @DELETE("$PATH_CONTACT/{id}")
    suspend fun deleteContact(
        @Path("id") id: String,
    ): Contact

    @PUT("$PATH_CONTACT/{id}")
    suspend fun updateContact(
        @Path("id") id: String?,
        @Body contact: Contact,
    ): Contact

    @POST(PATH_CONTACT)
    suspend fun addContact(
        @Body contact: Contact,
    ): Contact
}
