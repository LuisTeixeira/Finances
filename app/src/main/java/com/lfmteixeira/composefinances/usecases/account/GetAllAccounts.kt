package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.repository.AccountRepository

class GetAllAccounts(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): List<Account> {
        return accountRepository.getAllAccounts()
    }
}