package com.lfmteixeira.composefinances.repository

interface TransactionRepository {
    suspend fun saveTransaction()
}