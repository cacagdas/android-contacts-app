package com.cacagdas.contactsapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cacagdas.contactsapp.core.base.ContactsAppFragment
import com.cacagdas.contactsapp.core.util.extension.observeLiveData
import com.cacagdas.contactsapp.core.widget.ToolbarMenu
import com.cacagdas.contactsapp.core.widget.WidgetProgressDialog
import com.cacagdas.contactsapp.core.widget.WidgetToolbar
import com.cacagdas.contactsapp.data.model.Contact
import com.cacagdas.contactsapp.databinding.FragmentContactDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailFragment : ContactsAppFragment<FragmentContactDetailBinding, ContactDetailViewModel>() {

    override val viewModel: ContactDetailViewModel by viewModels()

    private val progressDialog by lazy {
        WidgetProgressDialog(requireContext()).also {
            lifecycle.addObserver(it)
        }
    }

    companion object {
        const val ARG_CONTACT_ID = "contactId"
        const val RESULT_KEY_CONTACT_EDIT_LISTENER = "contact_edit_listener"
        const val RESULT_ARG_CONTACT_UPDATED = "contact_updated"
    }

    override fun onBindView(binding: FragmentContactDetailBinding) {
        binding.update.setOnClickListener { viewModel.onUpdateContactClicked() }
    }

    override fun observeViewModel() {
        viewModel.run {
            observeLiveData(showLoadingLiveData) {
                progressDialog.showOrHide(it)
            }
            observeLiveData(contactDetailLiveData) {
                bindContact(it)
            }
            observeLiveData(routeBackLiveData) {
                routeBack()
            }
            observeLiveData(contactUpdatedLiveData) {
                setFragmentResult(
                    RESULT_KEY_CONTACT_EDIT_LISTENER,
                    Bundle().apply {
                        putBoolean(RESULT_ARG_CONTACT_UPDATED, true)
                    }
                )
                routeBack()
            }
        }
    }

    private fun bindContact(contact: Contact) {
        binding.run {
            name.setText(contact.name)
            surname.setText(contact.surname)
            number.setText(contact.number.toString())
            company.setText(contact.companyName)
            department.setText(contact.department)
            email.setText(contact.email)
        }
        initTextChangedListeners()
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
        title = "Contact Detail",
        menu = listOf(
            ToolbarMenu(
                title = "Delete"
            ) {
                viewModel.deleteContact()
            }
        ),
    )

    override fun provideBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactDetailBinding {
        return FragmentContactDetailBinding::inflate
    }
}
