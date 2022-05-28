package com.lfmteixeira.composefinances.domain

class Transaction(
    val uuid: String,
    val account: Account,
    val category: Category,
    val value: Double
) {
}