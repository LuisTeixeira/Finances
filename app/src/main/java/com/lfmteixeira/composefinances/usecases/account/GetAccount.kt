package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.repository.AccountRepository

class GetAccount(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(id: String): Account {
        return accountRepository.getAccount(id)
    }
}