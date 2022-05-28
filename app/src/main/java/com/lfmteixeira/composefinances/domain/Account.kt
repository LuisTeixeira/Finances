package com.lfmteixeira.composefinances.domain

import com.lfmteixeira.composefinances.util.randomUUID

class Account(
    val uuid: String = randomUUID(),
    val name: String,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Account

        return other.uuid == uuid && other.name == name && other.transactions.containsAll(transactions)

    }
}