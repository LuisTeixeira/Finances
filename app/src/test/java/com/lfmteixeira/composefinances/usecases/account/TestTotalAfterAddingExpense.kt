package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.framework.testdata.TestAccountFactory
import com.lfmteixeira.composefinances.framework.testdata.TestCategoryFactory
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import com.lfmteixeira.composefinances.usecases.transaction.CreateExpense
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class TestTotalAfterAddingExpense {

    private val testConfig = TestConfig()
    private val createExpense: CreateExpense = testConfig.createExpense

    @Test
    fun testTotalAfterAddingExpense() {
        runBlocking {
            val account = TestAccountFactory(testConfig).createTestAccount()
            val category = TestCategoryFactory(testConfig).createTestCategory()
            val expenseValue = 1225.0
            val transactionModel = TransactionModel(accountId = account.uuid, categoryId = category.uuid, "Rent Payment", expenseValue)
            createExpense(transactionModel)
            Assert.assertEquals(expenseValue * -1, account.getTotal(), 0.0)
        }
    }

}