package com.cacagdas.contactsapp.core.base.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

abstract class FlowPagingUseCase<T : Any, P> {

    abstract fun invoke(params: P): Flow<PagingData<T>>
}
