package com.lfmteixeira.composefinances.repository

import com.lfmteixeira.composefinances.domain.Account

interface AccountRepository {
    suspend fun getAccount(id: String): Account
    suspend fun getAllAccounts(): List<Account>
    suspend fun save(account: Account)
}