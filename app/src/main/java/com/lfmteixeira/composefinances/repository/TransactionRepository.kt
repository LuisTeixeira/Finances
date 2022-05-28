package com.lfmteixeira.composefinances.repository

import com.lfmteixeira.composefinances.domain.Transaction

interface TransactionRepository {
    suspend fun getTransaction(id: String): Transaction
    suspend fun save(transaction: Transaction)
}