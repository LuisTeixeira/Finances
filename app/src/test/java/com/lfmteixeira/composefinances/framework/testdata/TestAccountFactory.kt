package com.lfmteixeira.composefinances.framework.testdata

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import kotlinx.coroutines.runBlocking

class TestAccountFactory {

    fun createTestAccount(): Account {
        var account: Account
        runBlocking {
            val accountModel = AccountModel("Test Account", "Just a Test Account")
            account = TestConfig.createAccount(accountModel)
        }
        return account
    }

}