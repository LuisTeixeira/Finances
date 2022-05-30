package com.lfmteixeira.composefinances.framework.config

import com.lfmteixeira.composefinances.framework.repository.AccountRepositoryTestImpl
import com.lfmteixeira.composefinances.framework.repository.CategoryRepositoryTestImpl
import com.lfmteixeira.composefinances.framework.repository.TransactionRepositoryImpl
import com.lfmteixeira.composefinances.usecases.account.CreateAccount
import com.lfmteixeira.composefinances.usecases.account.GetAccount
import com.lfmteixeira.composefinances.usecases.account.GetAllAccounts
import com.lfmteixeira.composefinances.usecases.category.CreateCategory
import com.lfmteixeira.composefinances.usecases.category.GetCategory
import com.lfmteixeira.composefinances.usecases.transaction.CreateExpense
import com.lfmteixeira.composefinances.usecases.transaction.CreateIncome
import com.lfmteixeira.composefinances.usecases.transaction.GetTransaction
import com.lfmteixeira.composefinances.usecases.transaction.GetTransactionsForAccount

class TestConfig {

    private val categoryRepository = CategoryRepositoryTestImpl()
    private val accountRepository = AccountRepositoryTestImpl()
    private val transactionRepository = TransactionRepositoryImpl()

    val createCategory = CreateCategory(categoryRepository = categoryRepository)
    val getCategory = GetCategory(categoryRepository = categoryRepository)

    val createAccount = CreateAccount(accountRepository = accountRepository)
    val getAccount = GetAccount(accountRepository = accountRepository)
    val getAllAccounts = GetAllAccounts(accountRepository = accountRepository)

    val createExpense = CreateExpense(accountRepository = accountRepository, categoryRepository = categoryRepository, transactionRepository = transactionRepository)
    val createIncome = CreateIncome(accountRepository = accountRepository, categoryRepository = categoryRepository, transactionRepository = transactionRepository)
    val getTransaction = GetTransaction(transactionRepository = transactionRepository)
    val getTransactionsForAccount = GetTransactionsForAccount(transactionRepository = transactionRepository)
}