package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import com.lfmteixeira.composefinances.usecases.transaction.CreateIncome
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime

class TestTotalAfterAddingIncome : TestBase() {
    private val createIncome: CreateIncome = testConfig.createIncome

    @Test
    fun testTotalAfterAddingExpense() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val incomeValue = 2500.0
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "Salary",
                incomeValue,
                LocalDateTime.now()
            )
            createIncome(transactionModel)
            Assert.assertEquals(incomeValue, account.getTotal(), 0.0)
        }
    }
}