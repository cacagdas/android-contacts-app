package com.cacagdas.contactsapp.presentation.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cacagdas.contactsapp.core.base.ContactsAppFragment
import com.cacagdas.contactsapp.databinding.FragmentContactsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : ContactsAppFragment<FragmentContactsBinding, ContactsViewModel>() {
    override val viewModel: ContactsViewModel by viewModels()

    override fun onBindView(binding: FragmentContactsBinding) {
        // TODO
    }

    override fun observeViewModel() {
        // TODO
    }

    override fun provideBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactsBinding {
        return FragmentContactsBinding::inflate
    }

}
