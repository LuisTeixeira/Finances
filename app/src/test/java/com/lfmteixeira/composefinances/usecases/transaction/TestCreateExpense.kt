package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

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
                LocalDate.now()
            )
            val createdExpense = createExpense(transactionModel).getOrThrow()
            val retriedTransaction = getTransaction(createdExpense.uuid)
            Assert.assertEquals(createdExpense, retriedTransaction)
        }
    }

    @Test
    fun testExpenseShouldHaveADate() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val datetime = LocalDate.now()
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "Rent Payment",
                1225.0,
                datetime
            )
            val createdExpense = createExpense(transactionModel)
            val retrievedTransaction = getTransaction(createdExpense.getOrThrow().uuid)
            Assert.assertEquals(datetime, retrievedTransaction.dateTime)
        }
    }

    @Test
    fun testExpenseWithEmptyDescription() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val datetime = LocalDate.now()
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "",
                1225.0,
                datetime
            )
            val createdResult = createExpense(transactionModel)
            Assert.assertTrue(createdResult.isFailure)
            lateinit var message: String
            createdResult.onFailure { failure -> message = failure.message ?: "" }
            Assert.assertTrue(message, message.contains("must not be empty"))
        }
    }

    @Test
    fun testExpenseWithNegativeValue() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val datetime = LocalDate.now()
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "Test",
                -1225.0,
                datetime
            )
            val createdResult = createExpense(transactionModel)
            Assert.assertTrue(createdResult.isFailure)
            lateinit var message: String
            createdResult.onFailure { failure -> message = failure.message ?: "" }
            Assert.assertTrue(message, message.contains("must be larger than 0"))
        }
    }

    @Test
    fun testExpenseWithFutureDate() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val datetime = LocalDate.now().plusDays(1)
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "test",
                1225.0,
                datetime
            )
            val createdResult = createExpense(transactionModel)
            Assert.assertTrue(createdResult.isFailure)
            lateinit var message: String
            createdResult.onFailure { failure -> message = failure.message ?: "" }
            Assert.assertTrue(message, message.contains("must not be in the future"))
        }
    }

    @Test
    fun testExpenseWithMultipleValidationFailures() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val datetime = LocalDate.now().plusDays(1)
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "",
                -1225.0,
                datetime
            )
            val createdResult = createExpense(transactionModel)
            Assert.assertTrue(createdResult.isFailure)
            lateinit var message: String
            createdResult.onFailure { failure -> message = failure.message ?: "" }
            Assert.assertTrue(message, message.contains("must not be empty"))
            Assert.assertTrue(message, message.contains("must be larger than 0"))
            Assert.assertTrue(message, message.contains("must not be in the future"))
        }
    }
}