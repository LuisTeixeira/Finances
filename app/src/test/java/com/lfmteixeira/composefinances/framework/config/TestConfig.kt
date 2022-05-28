package com.lfmteixeira.composefinances.framework.config

import com.lfmteixeira.composefinances.framework.repository.AccountRepositoryTestImpl
import com.lfmteixeira.composefinances.framework.repository.CategoryRepositoryTestImpl
import com.lfmteixeira.composefinances.usecases.account.CreateAccount
import com.lfmteixeira.composefinances.usecases.account.GetAccount
import com.lfmteixeira.composefinances.usecases.category.CreateCategory
import com.lfmteixeira.composefinances.usecases.category.GetCategory

object TestConfig {

    private val categoryRepository = CategoryRepositoryTestImpl()
    private val accountRepository = AccountRepositoryTestImpl()

    val createCategory = CreateCategory(categoryRepository = categoryRepository)
    val getCategory = GetCategory(categoryRepository = categoryRepository)

    val createAccount = CreateAccount(accountRepository = accountRepository)
    val getAccount = GetAccount(accountRepository = accountRepository)

}