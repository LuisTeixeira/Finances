package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.domain.TransactionType
import com.lfmteixeira.composefinances.domain.exception.ValidationException
import com.lfmteixeira.composefinances.repository.AccountRepository
import com.lfmteixeira.composefinances.repository.CategoryRepository
import com.lfmteixeira.composefinances.repository.TransactionRepository
import com.lfmteixeira.composefinances.usecases.model.TransactionModel

class CreateIncome(
    private val accountRepository: AccountRepository,
    private val categoryRepository: CategoryRepository,
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transactionModel: TransactionModel): Result<Transaction> {
        val result = try {
            val account = accountRepository.getAccount(transactionModel.accountId)
            val category = categoryRepository.getCategory(transactionModel.categoryId)
            val income = Transaction(
                account = account,
                category = category,
                description = transactionModel.description,
                type = TransactionType.INCOME,
                value = transactionModel.value,
                dateTime = transactionModel.date
            )
            account.addTransaction(income)
            transactionRepository.save(income)
            Result.success(income)
        } catch (e: ValidationException ) {
            Result.failure(e)
        }
        return result
    }
}