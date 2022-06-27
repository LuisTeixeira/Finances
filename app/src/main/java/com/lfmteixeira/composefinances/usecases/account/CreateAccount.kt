package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.domain.exception.ValidationException
import com.lfmteixeira.composefinances.domain.factories.AccountFactory
import com.lfmteixeira.composefinances.repository.AccountRepository
import com.lfmteixeira.composefinances.usecases.model.AccountModel

class CreateAccount(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(accountModel: AccountModel): Result<Account> {
        var result: Result<Account> = try {
            val account = AccountFactory().createAccount(
                name = accountModel.name,
                description = accountModel.description,
                initialValue = accountModel.initialValue
            )
            accountRepository.save(account);
            Result.success(account)
        } catch (e: ValidationException) {
            Result.failure(e)
        }
        return result
    }

}