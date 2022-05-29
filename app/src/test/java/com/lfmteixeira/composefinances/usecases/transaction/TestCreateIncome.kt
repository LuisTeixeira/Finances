package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.framework.testdata.TestAccountFactory
import com.lfmteixeira.composefinances.framework.testdata.TestCategoryFactory
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class TestCreateIncome {

    private val testConfig = TestConfig()
    private val createIncome: CreateIncome = testConfig.createIncome
    private val getTransaction: GetTransaction = testConfig.getTransaction

    @Test
    fun testCreateIncome() {
        runBlocking {
            val account = TestAccountFactory(testConfig).createTestAccount()
            val category = TestCategoryFactory(testConfig).createTestCategory()
            val transactionModel = TransactionModel(accountId = account.uuid, categoryId = category.uuid, "Salary", 2500.0)
            val createdIncome = createIncome(transactionModel)
            val retriedTransaction = getTransaction(createdIncome.uuid)
            Assert.assertEquals(createdIncome, retriedTransaction)
        }
    }
}