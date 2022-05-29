package com.lfmteixeira.composefinances.repository.impl

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.repository.AccountRepository

class AccountRepositoryImpl(
    private val storage: MutableMap<String, Account> = mutableMapOf()
) : AccountRepository{

    init {
        var account = Account(
            name = "Bank account",
            description = "My bank Account"
        )
        storage[account.uuid] = account
        account = Account(
            name = "Cash",
            description = "My Cash Money"
        )
        storage[account.uuid] = account
    }

    override suspend fun getAccount(id: String): Account {
        return storage[id]!!
    }

    override suspend fun getAllAccounts(): List<Account> {
        return storage.values.toList()
    }

    override suspend fun save(account: Account) {
        storage[account.uuid] = account
    }

}