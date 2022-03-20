package com.cacagdas.contactsapp.presentation.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacagdas.contactsapp.core.base.ContactsAppFragment
import com.cacagdas.contactsapp.core.util.extension.observeFlow
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.databinding.FragmentContactsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : ContactsAppFragment<FragmentContactsBinding, ContactsViewModel>(), ContactItemEventHandler {
    override val viewModel: ContactsViewModel by viewModels()
    private lateinit var contactsAdapter: ContactListAdapter

    override fun onBindView(binding: FragmentContactsBinding) {
        initRecyclerView()
        initAdapter()
    }

    override fun observeViewModel() {
        viewModel.run {
            observeFlow(contactsFlow) {
                contactsAdapter.submitData(it)
            }
        }
    }

    private fun initRecyclerView() {
        val contactsLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.contacts.layoutManager = contactsLayoutManager
    }

    private fun initAdapter() {
        contactsAdapter = ContactListAdapter(this)
        binding.contacts.setAdapter(contactsAdapter, this)
    }

    override fun onContactClick(contact: Contact) {
        contact.id?.let {
            findNavController().navigate(ContactsFragmentDirections.actionContactsFragmentToContactDetailFragment(it))
        }
    }

    override fun provideBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactsBinding {
        return FragmentContactsBinding::inflate
    }
}
