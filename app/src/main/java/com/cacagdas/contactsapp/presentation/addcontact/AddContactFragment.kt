package com.cacagdas.contactsapp.presentation.addcontact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cacagdas.contactsapp.R
import com.cacagdas.contactsapp.core.base.ContactsAppFragment
import com.cacagdas.contactsapp.core.util.extension.observeLiveData
import com.cacagdas.contactsapp.core.widget.WidgetProgressDialog
import com.cacagdas.contactsapp.core.widget.WidgetToolbar
import com.cacagdas.contactsapp.databinding.FragmentAddContactBinding
import com.cacagdas.contactsapp.presentation.contacts.ContactsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContactFragment : ContactsAppFragment<FragmentAddContactBinding, AddContactViewModel>() {

    override val viewModel: AddContactViewModel by viewModels()

    private val progressDialog by lazy {
        WidgetProgressDialog(requireContext()).also {
            lifecycle.addObserver(it)
        }
    }

    override fun onBindView(binding: FragmentAddContactBinding) {
        initTextChangedListeners()
        binding.add.setOnClickListener { viewModel.onAddContactClicked() }
    }

    override fun observeViewModel() {
        viewModel.run {
            observeLiveData(showLoadingLiveData) {
                progressDialog.showOrHide(it)
            }
            observeLiveData(routeBackLiveData) {
                routeBack()
            }
            observeLiveData(contactAddedLiveData) {
                setFragmentResult(
                    ContactsFragment.RESULT_KEY_CONTACT_EDIT_LISTENER,
                    Bundle().apply {
                        putBoolean(ContactsFragment.RESULT_ARG_CONTACT_UPDATED, true)
                    }
                )
                routeBack()
            }
        }
    }

    private fun initTextChangedListeners() {
        binding.run {
            name.doAfterTextChanged { viewModel.setName(it) }
            surname.doAfterTextChanged { viewModel.setSurname(it) }
            number.doAfterTextChanged { viewModel.setNumber(it) }
            company.doAfterTextChanged { viewModel.setCompany(it) }
            department.doAfterTextChanged { viewModel.setDepartment(it) }
            email.doAfterTextChanged { viewModel.setEmail(it) }
        }
    }

    private fun routeBack() {
        findNavController().popBackStack()
    }

    override fun provideToolbar() = WidgetToolbar(
        title = getString(R.string.add_contact_title),
    )

    override fun provideBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddContactBinding {
        return FragmentAddContactBinding::inflate
    }
}
