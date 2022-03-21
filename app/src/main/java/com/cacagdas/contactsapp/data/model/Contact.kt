package com.cacagdas.contactsapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contact(
    @Json(name = "id") val id: String?,
    @Json(name = "createdAt") val createdAt: String?,
    @Json(name = "name") var name: String?,
    @Json(name = "surname") var surname: String?,
    @Json(name = "number") var number: Long?,
    @Json(name = "department") var department: String?,
    @Json(name = "company_name") var companyName: String?,
    @Json(name = "email") var email: String?,
)
