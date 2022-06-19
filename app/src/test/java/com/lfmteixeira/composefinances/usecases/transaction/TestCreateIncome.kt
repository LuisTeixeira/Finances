package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class TestCreateIncome : TestBase() {
    private val createIncome: CreateIncome = testConfig.createIncome
    private val getTransaction: GetTransaction = testConfig.getTransaction

    @Test
    fun testCreateIncome() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "Salary",
                2500.0,
                LocalDate.now()
            )
            val createdIncome = createIncome(transactionModel).getOrThrow()
            val retriedTransaction = getTransaction(createdIncome.uuid)
            Assert.assertEquals(createdIncome, retriedTransaction)
        }
    }

    @Test
    fun testIncomeShouldHaveADate() {
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
            val createdIncome = createIncome(transactionModel)
            val retrievedTransaction = getTransaction(createdIncome.getOrThrow().uuid)
            Assert.assertEquals(datetime, retrievedTransaction.dateTime)
        }
    }

    @Test
    fun testIncomeWithEmptyDescription() {
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
            val createdResult = createIncome(transactionModel)
            Assert.assertTrue(createdResult.isFailure)
            lateinit var message: String
            createdResult.onFailure { failure -> message = failure.message ?: "" }
            Assert.assertTrue(message, message.contains("must not be empty"))
        }
    }

    @Test
    fun testIncomeWithNegativeValue() {
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
            val createdResult = createIncome(transactionModel)
            Assert.assertTrue(createdResult.isFailure)
            lateinit var message: String
            createdResult.onFailure { failure -> message = failure.message ?: "" }
            Assert.assertTrue(message, message.contains("must be larger than 0"))
        }
    }

    @Test
    fun testIncomeWithFutureDate() {
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
            val createdResult = createIncome(transactionModel)
            Assert.assertTrue(createdResult.isFailure)
            lateinit var message: String
            createdResult.onFailure { failure -> message = failure.message ?: "" }
            Assert.assertTrue(message, message.contains("must not be in the future"))
        }
    }

    @Test
    fun testIncomeWithMultipleValidationFailures() {
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
            val createdResult = createIncome(transactionModel)
            Assert.assertTrue(createdResult.isFailure)
            lateinit var message: String
            createdResult.onFailure { failure -> message = failure.message ?: "" }
            Assert.assertTrue(message, message.contains("must not be empty"))
            Assert.assertTrue(message, message.contains("must be larger than 0"))
            Assert.assertTrue(message, message.contains("must not be in the future"))
        }
    }
}