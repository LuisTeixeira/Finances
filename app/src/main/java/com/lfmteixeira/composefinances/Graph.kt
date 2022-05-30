package com.lfmteixeira.composefinances

import com.lfmteixeira.composefinances.repository.TransactionRepository
import com.lfmteixeira.composefinances.repository.impl.AccountRepositoryImpl
import com.lfmteixeira.composefinances.repository.impl.TransactionRepositoryImpl
import com.lfmteixeira.composefinances.usecases.account.GetAllAccounts
import com.lfmteixeira.composefinances.usecases.transaction.GetTransactionsForAccount

object Graph {
    private val accountRepository = AccountRepositoryImpl()
    private val transactionRepository = TransactionRepositoryImpl()

    val getAllAccounts = GetAllAccounts(accountRepository)

    val getTransactionsForAccount = GetTransactionsForAccount(transactionRepository)
}