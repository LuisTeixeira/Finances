package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.repository.TransactionRepository

class GetTransaction(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(id: String): Transaction = transactionRepository.getTransaction(id)
}