package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestAddAccount : TestBase(){

    private lateinit var createAccount: CreateAccount
    private lateinit var getAccount: GetAccount

    @Before
    fun setup() {
        createAccount = testConfig.createAccount
        getAccount = testConfig.getAccount
    }

    @Test
    fun testCreateAccount() {
        runBlocking {
            val accountModel = AccountModel(
                name = "Bank Account",
                description = "My bank account"
            )
            val createdResult = createAccount(accountModel)
            Assert.assertTrue(createdResult.isSuccess)
            val createdAccount = createdResult.getOrThrow()
            val retrievedAccount = getAccount(createdAccount.uuid)
            Assert.assertEquals(createdAccount, retrievedAccount)
        }
    }

    @Test
    fun testCreateAccountWithInitialValue() {
        runBlocking {
            val value = 10000.0
            val accountModel = AccountModel(
                name = "Bank Account",
                description = "My bank account",
                initialValue = value
            )
            val createdResult = createAccount(accountModel)
            Assert.assertTrue(createdResult.isSuccess)
            val createdAccount = createdResult.getOrThrow()
            val retrievedAccount = getAccount(createdAccount.uuid)
            Assert.assertEquals(createdAccount, retrievedAccount)
            Assert.assertEquals(value, retrievedAccount.getTotal(), 0.0)
        }
    }

    @Test
    fun testAccountWithEmptyName() {
        runBlocking {
            val value = 10000.0
            val accountModel = AccountModel(
                name = "",
                description = "My bank account",
                initialValue = value
            )
            val createdResult = createAccount(accountModel)
            Assert.assertTrue(createdResult.isFailure)
            lateinit var failureMessage: String
            createdResult.onFailure { failure -> failureMessage = failure.message ?: "" }
            Assert.assertTrue(failureMessage.contains("must not be empty"))
        }
    }

}