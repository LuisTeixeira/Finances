package com.lfmteixeira.composefinances.domain

import com.lfmteixeira.composefinances.domain.validation.isNotInTheFuture
import com.lfmteixeira.composefinances.domain.validation.isNotNegative
import com.lfmteixeira.composefinances.domain.validation.isPresent
import com.lfmteixeira.composefinances.domain.validation.validate
import com.lfmteixeira.composefinances.util.randomUUID
import java.time.LocalDate

enum class TransactionType {
    EXPENSE, INCOME
}

class Transaction(
    val uuid: String = randomUUID(),
    val account: Account,
    val category: Category,
    private val type: TransactionType,
    val description: String,
    val value: Double,
    val dateTime: LocalDate
) {

    init {
        validate { validator ->
            validator.isPresent("description", description)
            validator.isNotNegative("value", value)
            validator.isNotInTheFuture("dateTime", dateTime)
        }
    }

    fun isExpense(): Boolean {
        return type == TransactionType.EXPENSE
    }

    fun getValueString(): String {
        if (isExpense()) {
            return "-$value"
        }
        return value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Transaction

        return other.uuid == uuid &&
                other.account == account &&
                other.category == category &&
                other.description == description &&
                other.value == value &&
                other.dateTime == dateTime
    }
}