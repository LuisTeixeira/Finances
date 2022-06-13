package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class TestCreateExpense : TestBase() {

    private lateinit var createExpense: CreateExpense
    private lateinit var getTransaction: GetTransaction

    @Before
    fun setup() {
        createExpense = testConfig.createExpense
        getTransaction = testConfig.getTransaction
    }

    @Test
    fun testCreateExpense() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "Rent Payment",
                1225.0,
                LocalDateTime.now()
            )
            val createdExpense = createExpense(transactionModel)
            val retriedTransaction = getTransaction(createdExpense.uuid)
            Assert.assertEquals(createdExpense, retriedTransaction)
        }
    }

    @Test
    fun testExpenseShouldHaveADate() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val datetime = LocalDateTime.now()
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "Rent Payment",
                1225.0,
                datetime
            )
            val createdExpense = createExpense(transactionModel)
            val retrievedTransaction = getTransaction(createdExpense.uuid)
            Assert.assertEquals(datetime, retrievedTransaction.dateTime)
        }
    }
}