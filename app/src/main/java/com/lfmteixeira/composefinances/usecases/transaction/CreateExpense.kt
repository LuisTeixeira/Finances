package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.repository.AccountRepository
import com.lfmteixeira.composefinances.repository.CategoryRepository
import com.lfmteixeira.composefinances.repository.TransactionRepository
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import kotlin.math.exp

class CreateExpense(
    val accountRepository: AccountRepository,
    val categoryRepository: CategoryRepository,
    val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transactionModel: TransactionModel): Transaction {
        val account = accountRepository.getAccount(transactionModel.accountId)
        val category = categoryRepository.getCategory(transactionModel.categoryId)
        val expense = Transaction(
            account = account,
            category = category,
            description = transactionModel.description,
            value = transactionModel.value
        )
        account.addTransaction(expense)
        transactionRepository.save(expense)
        return expense
    }
}