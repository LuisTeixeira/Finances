package com.lfmteixeira.composefinances.domain

import com.lfmteixeira.composefinances.domain.validation.isNotNegative
import com.lfmteixeira.composefinances.domain.validation.isPresent
import com.lfmteixeira.composefinances.domain.validation.validate
import com.lfmteixeira.composefinances.util.randomUUID

class Account(
    val uuid: String = randomUUID(),
    val name: String,
    val description: String,
    private val initialValue: Double = 0.0,
    private val transactions: MutableList<Transaction> = ArrayList(),
) {

    init {
        validate { validator ->
            validator.isPresent("name", name)
            validator.isNotNegative("initialValue", initialValue)
        }
    }

    private var total: Double = initialValue

    fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
        if (transaction.isExpense()) total -= transaction.value else total += transaction.value
    }

    fun removeTransaction(transaction: Transaction) {
        transactions.remove(transaction)
        if (transaction.isExpense()) total += transaction.value else total -= transaction.value
    }

    fun getTotal(): Double {
        return total
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Account

        return other.uuid == uuid && other.name == name && other.transactions.containsAll(transactions)
    }
}