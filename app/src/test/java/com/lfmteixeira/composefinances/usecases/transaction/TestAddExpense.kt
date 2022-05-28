package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestAddExpense {

    private lateinit var createExpense: CreateExpense
    private lateinit var getTransaction: GetTransaction

    private lateinit var account: Account
    private lateinit var category: Category


    @Before
    fun setup() {
        runBlocking {
            createExpense = TestConfig.createExpense
            getTransaction = TestConfig.getTransaction
            createAccount()
            createCategory()
        }
    }

    private suspend fun createAccount() {
        val accountName = "Bank"
        val accountDescription = "Just a bank account"
        account = TestConfig.createAccount(
            AccountModel(
                name = accountName,
                description = accountDescription
            )
        )
    }

    private suspend fun createCategory() {
        val categoryName = "Rent"
        category = TestConfig.createCategory(name = categoryName)
    }

    @Test
    fun testCreateExpense() {
        runBlocking {
            val transactionModel = TransactionModel(accountId = account.uuid, categoryId = category.uuid, "Rent Payment", 1225.0)
            val createdExpense = createExpense(transactionModel)
            val retriedTransaction = getTransaction(createdExpense.uuid)
            Assert.assertEquals(createdExpense, retriedTransaction)
        }
    }
}