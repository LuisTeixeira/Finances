package com.lfmteixeira.composefinances.domain.factories

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.domain.validation.isPresent

class AccountFactory {
    fun createAccount(
        name: String,
        description: String,
        initialValue: Double
    ) : Account {
        return Account(
            name = name,
            description = description,
            initialValue = initialValue
        )
    }
}