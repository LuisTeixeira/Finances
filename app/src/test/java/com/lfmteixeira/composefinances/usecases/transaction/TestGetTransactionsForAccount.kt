package com.lfmteixeira.composefinances.usecases.transaction

import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

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
                    date = LocalDate.now()
                )
                firstAccountTransactions.add(testConfig.createExpense(transactionModel))
                transactionModel = TransactionModel(
                    description = "Transaction second account #$i",
                    categoryId = category.uuid,
                    accountId = secondAccount.uuid,
                    value = i.toDouble(),
                    date = LocalDate.now()
                )
                secondAccountTransactions.add(testConfig.createExpense(transactionModel))
            }

            val retrievedTransactions = testConfig.getTransactionsForAccount(firstAccount.uuid)
            Assert.assertEquals(firstAccountTransactions.size, retrievedTransactions.size)
            Assert.assertTrue(retrievedTransactions.containsAll(firstAccountTransactions))
        }
    }

    @Test
    fun testGetTransactionsForAccountShouldBeOrderByData() {
        runBlocking {
            val firstAccount = testAccountFactory.createTestAccount()
            val category = testCategoryFactory.createTestCategory()

            val firstAccountTransactions = mutableListOf<Transaction>()

            val unorderedDates = listOf<LocalDate>(
                LocalDate.of(2018, 4, 20),
                LocalDate.of(2022, 4, 20),
                LocalDate.of(2021, 4, 20),
                LocalDate.of(2019, 4, 20),
                LocalDate.of(2020, 4, 20)
            )

            val orderedDates = listOf<LocalDate>(
                LocalDate.of(2022, 4, 20),
                LocalDate.of(2021, 4, 20),
                LocalDate.of(2020, 4, 20),
                LocalDate.of(2019, 4, 20),
                LocalDate.of(2018, 4, 20)
            )

            for (i in 1..5) {
                var transactionModel = TransactionModel(
                    description = "Transaction first account #$i",
                    categoryId = category.uuid,
                    accountId = firstAccount.uuid,
                    value = i.toDouble(),
                    date = unorderedDates[i - 1]
                )
                firstAccountTransactions.add(testConfig.createExpense(transactionModel))
            }

            val retrievedTransactions = testConfig.getTransactionsForAccount(firstAccount.uuid)
            val retrievedTransactionsDates = retrievedTransactions.map { transaction ->
                transaction.dateTime
            }

            Assert.assertEquals(orderedDates, retrievedTransactionsDates)
        }
    }
}