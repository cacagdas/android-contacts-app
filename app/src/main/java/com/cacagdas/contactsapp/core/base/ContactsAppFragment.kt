package com.cacagdas.contactsapp.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class ContactsAppFragment<Binding : ViewBinding, VM : ContactsAppViewModel> : Fragment() {

    abstract val viewModel: VM

    protected open val binding get() = _binding!!
    private var _binding: Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindingInflater = provideBindingInflater()
        val viewBinding = bindingInflater(inflater, container, false)
        _binding = viewBinding
        onBindView(viewBinding)
        observeViewModel()
        return viewBinding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    abstract fun onBindView(binding: Binding)

    abstract fun observeViewModel()

    abstract fun provideBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> Binding
}
