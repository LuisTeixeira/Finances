package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime

class TestGetTransactionsForAccount : TestBase() {


    @Test
    fun testGetTransactionsForAccount() {
        runBlocking {
            val firstAccount = testAccountFactory.createTestAccount()
            val secondAccount = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()

            val firstAccountTransactions = mutableListOf<Transaction>()
            val secondAccountTransactions = mutableListOf<Transaction>()

            for (i in 1..5) {
                var transactionModel = TransactionModel(
                    description = "Transaction first account #$i",
                    categoryId = category.uuid,
                    accountId = firstAccount.uuid,
                    value = i.toDouble(),
                    dateTime = LocalDateTime.now()
                )
                firstAccountTransactions.add(testConfig.createExpense(transactionModel))
                transactionModel = TransactionModel(
                    description = "Transaction second account #$i",
                    categoryId = category.uuid,
                    accountId = secondAccount.uuid,
                    value = i.toDouble(),
                    dateTime = LocalDateTime.now()
                )
                secondAccountTransactions.add(testConfig.createExpense(transactionModel))
            }

            val retrievedTransactions = testConfig.getTransactionsForAccount(firstAccount.uuid)
            Assert.assertEquals(firstAccountTransactions.size, retrievedTransactions.size)
            Assert.assertTrue(retrievedTransactions.containsAll(firstAccountTransactions))
        }
    }
}