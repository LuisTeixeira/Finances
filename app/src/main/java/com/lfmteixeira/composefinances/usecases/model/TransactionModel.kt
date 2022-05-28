package com.lfmteixeira.composefinances.usecases.model

data class TransactionModel(
    val accountId: String,
    val categoryId: String,
    val description: String,
    val value: Double
)