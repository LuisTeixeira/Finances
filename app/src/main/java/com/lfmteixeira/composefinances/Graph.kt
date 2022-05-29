package com.lfmteixeira.composefinances

import com.lfmteixeira.composefinances.repository.impl.AccountRepositoryImpl
import com.lfmteixeira.composefinances.usecases.account.GetAllAccounts

object Graph {
    val accountRepository = AccountRepositoryImpl()

    val getAllAccounts = GetAllAccounts(accountRepository)
}