package com.cacagdas.contactsapp.presentation.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cacagdas.contactsapp.R
import com.cacagdas.contactsapp.core.base.ContactsAppFragment
import com.cacagdas.contactsapp.core.util.extension.observeFlow
import com.cacagdas.contactsapp.core.util.extension.observeLiveData
import com.cacagdas.contactsapp.core.widget.ToolbarMenu
import com.cacagdas.contactsapp.core.widget.WidgetProgressDialog
import com.cacagdas.contactsapp.core.widget.WidgetToolbar
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.databinding.FragmentContactsBinding
import com.cacagdas.contactsapp.presentation.detail.ContactDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : ContactsAppFragment<FragmentContactsBinding, ContactsViewModel>(), ContactItemEventHandler {
    override val viewModel: ContactsViewModel by viewModels()

    private val progressDialog by lazy {
        WidgetProgressDialog(requireContext()).also {
            lifecycle.addObserver(it)
        }
    }

    private lateinit var contactsAdapter: ContactListAdapter

    companion object {
        const val RESULT_KEY_CONTACT_EDIT_LISTENER = "contact_edit_listener"
        const val RESULT_ARG_CONTACT_UPDATED = "contact_updated"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(RESULT_KEY_CONTACT_EDIT_LISTENER) { _, bundle ->
            if (bundle.getBoolean(RESULT_ARG_CONTACT_UPDATED))
                viewModel.getContacts()
        }
    }

    override fun onBindView(binding: FragmentContactsBinding) {
        initRecyclerView()
        initAdapter()
    }

    override fun observeViewModel() {
        viewModel.run {
            observeLiveData(showLoadingLiveData) {
                progressDialog.showOrHide(it)
            }
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
        contactsAdapter.handleLoadState()
    }

    private fun ContactListAdapter.handleLoadState() {
        this.addLoadStateListener { loadState ->
            viewModel.showLoading.value = loadState.refresh is LoadState.Loading
        }
    }

    private fun onAddNewContactClicked() {
        findNavController().navigate(ContactsFragmentDirections.actionContactsFragmentToAddContactFragment())
    }

    override fun onContactClick(contact: Contact) {
        contact.id?.let {
            findNavController().navigate(ContactsFragmentDirections.actionContactsFragmentToContactDetailFragment(it))
        }
    }

    override fun provideToolbar() = WidgetToolbar(
        title = getString(R.string.contacts_title),
        menu = listOf(
            ToolbarMenu(
                title = getString(R.string.menu_add_new)
            ) {
                onAddNewContactClicked()
            }
        ),
    )

    override fun provideBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactsBinding {
        return FragmentContactsBinding::inflate
    }
}
