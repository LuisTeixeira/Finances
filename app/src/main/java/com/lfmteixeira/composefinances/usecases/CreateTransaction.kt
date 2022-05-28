package com.lfmteixeira.composefinances.usecases

import com.lfmteixeira.composefinances.repository.AccountRepository
import com.lfmteixeira.composefinances.repository.CategoryRepository
import com.lfmteixeira.composefinances.repository.TransactionRepository
import com.lfmteixeira.composefinances.usecases.model.TransactionModel

class CreateTransaction(
    val accountRepository: AccountRepository,
    val categoryRepository: CategoryRepository,
    val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transactionModel: TransactionModel) {

    }
}