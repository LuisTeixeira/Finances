package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.framework.testdata.TestAccountFactory
import com.lfmteixeira.composefinances.framework.testdata.TestCategoryFactory
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import com.lfmteixeira.composefinances.usecases.transaction.CreateIncome
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class TestTotalAfterAddingIncome {
    private val createIncome: CreateIncome = TestConfig.createIncome

    @Test
    fun testTotalAfterAddingExpense() {
        runBlocking {
            val account = TestAccountFactory().createTestAccount()
            val category = TestCategoryFactory().createTestCategory()
            val incomeValue = 2500.0
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "Salary",
                incomeValue
            )
            createIncome(transactionModel)
            Assert.assertEquals(incomeValue, account.getTotal(), 0.0)
        }
    }
}