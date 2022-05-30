package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.repository.AccountRepository
import com.lfmteixeira.composefinances.usecases.model.AccountModel

class CreateAccount(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(accountModel: AccountModel): Account {
        val account = Account(name = accountModel.name, description = accountModel.description, initialValue = accountModel.initialValue)
        accountRepository.save(account);
        return account
    }

}