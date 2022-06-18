package com.lfmteixeira.composefinances.framework.testdata

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import kotlinx.coroutines.runBlocking

class TestAccountFactory(
    val testConfig: TestConfig
) {
    private var numOfObjectsCreated = 0

    fun createTestAccount(): Account {
        var account: Account
        runBlocking {
            val accountModel = AccountModel("Test Account #$numOfObjectsCreated", "Just a Test Account")
            account = testConfig.createAccount(accountModel).getOrThrow()
            numOfObjectsCreated += 1
        }
        return account
    }
}