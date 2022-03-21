package com.cacagdas.contactsapp.core.base.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

abstract class PagingUseCase<T : Any, P> {

    abstract fun invoke(params: P): LiveData<PagingData<T>>
}
