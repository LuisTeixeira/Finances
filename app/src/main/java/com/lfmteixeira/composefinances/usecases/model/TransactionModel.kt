package com.lfmteixeira.composefinances.usecases.model

import java.time.LocalDateTime

data class TransactionModel(
    val accountId: String,
    val categoryId: String,
    val description: String,
    val value: Double,
    val dateTime: LocalDateTime
)