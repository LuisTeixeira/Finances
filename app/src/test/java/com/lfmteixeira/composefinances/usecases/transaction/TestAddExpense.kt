package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.framework.testdata.TestAccountFactory
import com.lfmteixeira.composefinances.framework.testdata.TestCategoryFactory
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestAddExpense {

    private lateinit var createExpense: CreateExpense
    private lateinit var getTransaction: GetTransaction

    @Before
    fun setup() {
        runBlocking {
            createExpense = TestConfig.createExpense
            getTransaction = TestConfig.getTransaction
        }
    }

    @Test
    fun testCreateExpense() {
        runBlocking {
            val account = TestAccountFactory().createTestAccount()
            val category = TestCategoryFactory().createTestCategory()
            val transactionModel = TransactionModel(accountId = account.uuid, categoryId = category.uuid, "Rent Payment", 1225.0)
            val createdExpense = createExpense(transactionModel)
            val retriedTransaction = getTransaction(createdExpense.uuid)
            Assert.assertEquals(createdExpense, retriedTransaction)
        }
    }
}