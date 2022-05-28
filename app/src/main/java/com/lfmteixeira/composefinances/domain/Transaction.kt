package com.lfmteixeira.composefinances.domain

import com.lfmteixeira.composefinances.util.randomUUID

class Transaction(
    val uuid: String = randomUUID(),
    val account: Account,
    val category: Category,
    val description: String,
    val value: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Transaction

        return other.uuid == uuid &&
                other.account == account &&
                other.category == category &&
                other.description == description &&
                other.value == value
    }
}