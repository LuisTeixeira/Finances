package com.lfmteixeira.composefinances.domain

class Account(
    val uuid: String,
    val description: String,
    private val transactions: MutableList<Transaction> = ArrayList(),
) {

    private var total: Double = 0.0
        get() = field

    fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
        total += transaction.value
    }

    fun removeTransaction(transaction: Transaction) {
        transactions.remove(transaction)
        total -= transaction.value
    }
}