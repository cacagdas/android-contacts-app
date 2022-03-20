package com.cacagdas.contactsapp.presentation.contacts

import com.cacagdas.contactsapp.data.model.Contact

interface ContactItemEventHandler {
    fun onContactClick(contact: Contact)
}
