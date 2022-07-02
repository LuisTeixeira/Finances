package com.lfmteixeira.composefinances.database.repository

import com.lfmteixeira.composefinances.database.dao.AccountDao
import com.lfmteixeira.composefinances.database.dao.TransactionRunner
import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.repository.AccountRepository

class AccountDatabaseRepository(
    val transactionRunner: TransactionRunner,
    val accountDao: AccountDao
) : AccountRepository {

    override suspend fun getAccount(id: String): Account {
//        val account = accountDao.getAccount(id).collect({ entity ->
//            Account(
//                uuid = entity.id,
//                name = entity.name,
//                description = entity.description,
//            )
//        })
        TODO("Not yet implemented")
    }

    override suspend fun getAllAccounts(): List<Account> {
        TODO("Not yet implemented")
    }

    override suspend fun save(account: Account): Account {
        TODO("Not yet implemented")
    }
}