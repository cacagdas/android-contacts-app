package com.cacagdas.contactsapp.core.util.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.cacagdas.contactsapp.core.widget.WidgetRecyclerView

fun Fragment.releaseAdapterOnDestroy(recyclerView: WidgetRecyclerView) {
    viewLifecycleOwner.lifecycle.addObserver(recyclerView)
}

inline fun <T> Fragment.observeFlow(
    flow: Flow<T>?,
    crossinline onChanged: suspend (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow?.collectLatest {
                onChanged.invoke(it)
            }
        }
    }
}

inline fun <T> Fragment.observeLiveData(
    liveData: LiveData<T>,
    crossinline onChanged: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner, onChanged)
}
