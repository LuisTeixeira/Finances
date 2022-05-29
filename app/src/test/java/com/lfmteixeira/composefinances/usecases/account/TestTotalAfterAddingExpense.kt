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

    private val createExpense: CreateExpense = TestConfig.createExpense

    @Test
    fun testTotalAfterAddingExpense() {
        runBlocking {
            val account = TestAccountFactory().createTestAccount()
            val category = TestCategoryFactory().createTestCategory()
            val expenseValue = 1225.0
            val transactionModel = TransactionModel(accountId = account.uuid, categoryId = category.uuid, "Rent Payment", expenseValue)
            createExpense(transactionModel)
            Assert.assertEquals(account.getTotal(), expenseValue * -1, 0.0)
        }
    }

}