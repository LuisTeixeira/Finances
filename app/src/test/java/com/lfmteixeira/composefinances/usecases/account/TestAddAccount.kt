package com.lfmteixeira.composefinances.usecases.account

import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.usecases.account.CreateAccount
import com.lfmteixeira.composefinances.usecases.account.GetAccount
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestAddAccount {

    private lateinit var createAccount: CreateAccount
    private lateinit var getAccount: GetAccount

    @Before
    fun setup() {
        createAccount = TestConfig.createAccount
        getAccount = TestConfig.getAccount
    }

    @Test
    fun testCreateAccount() {
        runBlocking {
            val accountModel = AccountModel(
                name = "Bank Account",
                description = "My bank account"
            )
            val createdAccount = createAccount(accountModel)
            val retrievedAccount = getAccount(createdAccount.uuid)
            Assert.assertEquals(createdAccount, retrievedAccount)
        }
    }

}