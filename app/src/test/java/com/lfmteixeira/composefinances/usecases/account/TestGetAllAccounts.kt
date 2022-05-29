package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class TestGetAllAccounts : TestBase() {
    val createAccount: CreateAccount = testConfig.createAccount
    val getAllAccounts: GetAllAccounts = testConfig.getAllAccounts

    @Test
    fun testGetAllAccounts() {
        runBlocking {
            val createdAccounts = createAccounts()
            val retrievedAccounts = getAllAccounts()
            Assert.assertEquals(retrievedAccounts, createdAccounts)
        }
    }

    private suspend fun createAccounts(): List<Account> {
        val accounts = mutableListOf<Account>()
        val accountModel1 = AccountModel("Test Account 1", "First Test Account")
        val accountModel2 = AccountModel("Test Account 2", "Seconde Test Account")
        accounts.add(createAccount(accountModel1))
        accounts.add(createAccount(accountModel2))
        return accounts
    }
}