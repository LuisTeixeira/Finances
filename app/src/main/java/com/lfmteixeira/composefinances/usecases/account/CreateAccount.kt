package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.domain.factories.AccountFactory
import com.lfmteixeira.composefinances.repository.AccountRepository
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import java.lang.IllegalArgumentException

class CreateAccount(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(accountModel: AccountModel): Result<Account> {
        var result: Result<Account> = try{
            val account = AccountFactory().createAccount(
                name = accountModel.name,
                description = accountModel.description,
                initialValue = accountModel.initialValue
            )
            accountRepository.save(account);
            Result.success(account)
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
        return result
    }

}