package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.repository.TransactionRepository

class GetTransactionsForAccount(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(accountId: String): List<Transaction> {
        return transactionRepository.getTransactionsForAccountOrderedByDateDescending(accountId)
    }
}