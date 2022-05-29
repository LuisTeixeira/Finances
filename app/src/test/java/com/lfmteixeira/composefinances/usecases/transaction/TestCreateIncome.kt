package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.framework.testdata.TestAccountFactory
import com.lfmteixeira.composefinances.framework.testdata.TestCategoryFactory
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class TestCreateIncome {

    private val createIncome: CreateIncome = TestConfig.createIncome
    private val getTransaction: GetTransaction = TestConfig.getTransaction

    @Test
    fun testCreateIncome() {
        runBlocking {
            val account = TestAccountFactory().createTestAccount()
            val category = TestCategoryFactory().createTestCategory()
            val transactionModel = TransactionModel(accountId = account.uuid, categoryId = category.uuid, "Salary", 2500.0)
            val createdIncome = createIncome(transactionModel)
            val retriedTransaction = getTransaction(createdIncome.uuid)
            Assert.assertEquals(createdIncome, retriedTransaction)
        }
    }
}