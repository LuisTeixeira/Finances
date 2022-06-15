package com.lfmteixeira.composefinances.usecases.model

import java.time.LocalDate

data class TransactionModel(
    val accountId: String,
    val categoryId: String,
    val description: String,
    val value: Double,
    val date: LocalDate
)