package com.cacagdas.contactsapp.core.util.extension

import com.cacagdas.contactsapp.core.base.ContactsAppViewModel
import com.cacagdas.contactsapp.core.util.Result

fun <T> ContactsAppViewModel.checkResult(result: Result<T>, onSuccess: (T) -> (Unit)) {
    when (result) {
        is Result.Success<T> -> onSuccess(result.data)
        is Result.Error -> {
            // no-op
        }
    }
}
