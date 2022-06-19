package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.domain.TransactionType
import com.lfmteixeira.composefinances.repository.AccountRepository
import com.lfmteixeira.composefinances.repository.CategoryRepository
import com.lfmteixeira.composefinances.repository.TransactionRepository
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import java.lang.IllegalArgumentException

class CreateExpense(
    val accountRepository: AccountRepository,
    val categoryRepository: CategoryRepository,
    val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transactionModel: TransactionModel): Result<Transaction> {
        val result = try {
            val account = accountRepository.getAccount(transactionModel.accountId)
            val category = categoryRepository.getCategory(transactionModel.categoryId)
            val expense = Transaction(
                account = account,
                category = category,
                description = transactionModel.description,
                type = TransactionType.EXPENSE,
                value = transactionModel.value,
                dateTime = transactionModel.date
            )
            account.addTransaction(expense)
            transactionRepository.save(expense)
            Result.success(expense)
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
        return result

    }
}