package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import com.lfmteixeira.composefinances.usecases.transaction.CreateExpense
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class TestTotalAfterAddingExpense : TestBase() {
    private val createExpense: CreateExpense = testConfig.createExpense

    @Test
    fun testTotalAfterAddingExpense() {
        runBlocking {
            val account = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()
            val expenseValue = 1225.0
            val transactionModel = TransactionModel(
                accountId = account.uuid,
                categoryId = category.uuid,
                "Rent Payment",
                expenseValue,
                LocalDate.now()
            )
            createExpense(transactionModel)
            Assert.assertEquals(expenseValue * -1, account.getTotal(), 0.0)
        }
    }

}