package com.cacagdas.contactsapp.presentation.contacts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.databinding.ItemContactBinding

class ContactViewHolder(
    private val eventHandler: ContactItemEventHandler,
    private val binding: ItemContactBinding,
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private var contact: Contact? = null

    init {
        binding.root.apply {
            setOnClickListener(this@ContactViewHolder)
        }
    }

    fun bind(item: Contact) {
        contact = item
        binding.apply {
            contactName.text = item.name
            contactSurname.text = item.surname
        }
    }

    override fun onClick(v: View?) {
        val itemPosition = bindingAdapterPosition
        if (itemPosition != RecyclerView.NO_POSITION) {
            contact?.let { eventHandler.onContactClick(it) }
        }
    }
}
