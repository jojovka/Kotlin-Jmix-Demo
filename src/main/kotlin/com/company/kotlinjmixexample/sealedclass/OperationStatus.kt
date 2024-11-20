package com.company.kotlinjmixexample.sealedclass

sealed class OperationStatus {
    object Loading : OperationStatus()
    data class Success(val message: String) : OperationStatus()
    data class Failure(val error: Throwable) : OperationStatus()
}