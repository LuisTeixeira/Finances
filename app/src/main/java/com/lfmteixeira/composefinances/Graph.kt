package com.lfmteixeira.composefinances

import com.lfmteixeira.composefinances.repository.impl.AccountRepositoryImpl
import com.lfmteixeira.composefinances.repository.impl.CategoryRepositoryImpl
import com.lfmteixeira.composefinances.repository.impl.TransactionRepositoryImpl
import com.lfmteixeira.composefinances.usecases.account.CreateAccount
import com.lfmteixeira.composefinances.usecases.account.GetAccount
import com.lfmteixeira.composefinances.usecases.account.GetAllAccounts
import com.lfmteixeira.composefinances.usecases.category.CreateCategory
import com.lfmteixeira.composefinances.usecases.category.GetAllCategories
import com.lfmteixeira.composefinances.usecases.transaction.CreateExpense
import com.lfmteixeira.composefinances.usecases.transaction.CreateIncome
import com.lfmteixeira.composefinances.usecases.transaction.GetTransaction
import com.lfmteixeira.composefinances.usecases.transaction.GetTransactionsForAccount

object Graph {
    private val accountRepository = AccountRepositoryImpl()
    private val transactionRepository = TransactionRepositoryImpl()
    private val categoryRepository = CategoryRepositoryImpl()

    val createCategory = CreateCategory(categoryRepository)
    val getAllCategories = GetAllCategories(categoryRepository)

    var mockDataCreated = false

    val createAccount = CreateAccount(accountRepository)
    val getAccount = GetAccount(accountRepository)
    val getAllAccounts = GetAllAccounts(accountRepository)
    val getTransactionsForAccount = GetTransactionsForAccount(transactionRepository)

    val createExpense = CreateExpense(accountRepository = accountRepository, categoryRepository = categoryRepository, transactionRepository = transactionRepository)
    val createIncome = CreateIncome(accountRepository = accountRepository, categoryRepository = categoryRepository, transactionRepository = transactionRepository)
    val getTransaction = GetTransaction(transactionRepository = transactionRepository)
}