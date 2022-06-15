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
            val createdExpense = createIncome(transactionModel)
            val retriedTransaction = getTransaction(createdExpense.uuid)
            Assert.assertEquals(createdExpense, retriedTransaction)
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
            val createdExpense = createIncome(transactionModel)
            val retrievedTransaction = getTransaction(createdExpense.uuid)
            Assert.assertEquals(datetime, retrievedTransaction.dateTime)
        }
    }
}