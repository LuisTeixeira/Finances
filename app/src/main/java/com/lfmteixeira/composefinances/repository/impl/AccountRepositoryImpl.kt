package com.lfmteixeira.composefinances.repository.impl

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.repository.AccountRepository

class AccountRepositoryImpl(
    private val storage: MutableMap<String, Account> = mutableMapOf()
) : AccountRepository{

    override suspend fun getAccount(id: String): Account {
        return storage[id]!!
    }

    override suspend fun getAllAccounts(): List<Account> {
        return storage.values.toList()
    }

    override suspend fun save(account: Account): Account {
        storage[account.uuid] = account
        return account
    }

}