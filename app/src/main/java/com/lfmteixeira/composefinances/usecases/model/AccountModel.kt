package com.lfmteixeira.composefinances.usecases.model

data class AccountModel(
    val name: String,
    val description: String,
    val initialValue: Double = 0.0
)