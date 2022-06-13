package com.lfmteixeira.composefinances.framework.repository

import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.repository.TransactionRepository

class TransactionRepositoryImpl(
    private val storage: MutableMap<String, Transaction> = mutableMapOf()
) : TransactionRepository {
    override suspend fun getTransaction(id: String): Transaction {
        return storage[id]!!
    }

    override suspend fun getTransactionsForAccountOrderedByDateDescending(accountId: String): List<Transaction> {
        return storage.values.filter { transaction ->
            transaction.account.uuid == accountId
        }.sortedByDescending { it.dateTime }.toList()
    }

    override suspend fun save(transaction: Transaction) {
        storage[transaction.uuid] = transaction
    }

}