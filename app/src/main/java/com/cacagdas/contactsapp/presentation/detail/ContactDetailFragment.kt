package com.cacagdas.contactsapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cacagdas.contactsapp.core.base.ContactsAppFragment
import com.cacagdas.contactsapp.databinding.FragmentContactDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailFragment : ContactsAppFragment<FragmentContactDetailBinding, ContactDetailViewModel>() {
    override val viewModel: ContactDetailViewModel by viewModels()

    override fun onBindView(binding: FragmentContactDetailBinding) {
        TODO("Not yet implemented")
    }

    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

    override fun provideBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactDetailBinding {
        return FragmentContactDetailBinding::inflate
    }

}
