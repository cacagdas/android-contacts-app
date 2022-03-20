package com.cacagdas.contactsapp.core.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.cacagdas.contactsapp.core.util.Result

abstract class RequestUseCase<Params, Response : Any?> {

    suspend fun invoke(params: Params): Result<Response> =
        withContext(Dispatchers.IO) {
            runCatching {
                Result.Success(buildRequest(params))
            }.getOrElse {
                Result.Error(it)
            }
        }

    abstract suspend fun buildRequest(params: Params): Response
}
