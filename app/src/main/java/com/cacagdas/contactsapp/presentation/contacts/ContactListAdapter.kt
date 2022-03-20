package com.cacagdas.contactsapp.presentation.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.databinding.ItemContactBinding

class ContactListAdapter(
    private val eventHandler: ContactItemEventHandler
) : PagingDataAdapter<Contact, ContactViewHolder>(object : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(eventHandler, binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
