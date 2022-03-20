package com.cacagdas.contactsapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contact(
    @Json(name = "id") val id: String?,
    @Json(name = "createdAt") val createdAt: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "surname") val surname: String?,
    @Json(name = "number") val number: Long?,
    @Json(name = "department") val department: String?,
    @Json(name = "company_name") val companyName: String?,
    @Json(name = "email") val email: String?,
)
