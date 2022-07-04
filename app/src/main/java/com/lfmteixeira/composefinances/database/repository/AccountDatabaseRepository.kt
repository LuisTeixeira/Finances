package com.lfmteixeira.composefinances.database.repository

import com.lfmteixeira.composefinances.database.dao.AccountDao
import com.lfmteixeira.composefinances.database.dao.TransactionDao
import com.lfmteixeira.composefinances.database.dao.TransactionRunner
import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.domain.TransactionType
import com.lfmteixeira.composefinances.repository.AccountRepository
import com.lfmteixeira.composefinances.repository.CategoryRepository

class AccountDatabaseRepository(
    val transactionRunner: TransactionRunner,
    private val accountDao: AccountDao,
    private val transactionDao: TransactionDao,
    private val categoryRepository: CategoryRepository
) : AccountRepository {

    override suspend fun getAccount(id: String): Account {
        var account: Account? = null
        accountDao.getAccount(id).collect { accountEntity ->
            account = Account(
                uuid = accountEntity.id,
                name = accountEntity.name,
                description = accountEntity.description
            )
            transactionDao.getTransactionsForAccount(id).collect { transactionEntities ->
                transactionEntities.forEach { transactionEntity ->
                    account?.addTransaction(
                        Transaction(
                            uuid = transactionEntity.id,
                            description = transactionEntity.description,
                            category = categoryRepository.getCategory(transactionEntity.categoryId),
                            account = account!!,
                            dateTime = transactionEntity.date,
                            value = transactionEntity.value,
                            type = TransactionType.valueOf(transactionEntity.type)
                        )
                    )
                }
            }
        }
        return account!!
    }

    override suspend fun getAllAccounts(): List<Account> {
        var accountList = mutableListOf<Account>()
        accountDao.getAllAccounts().collect { accountEntityList ->
            accountEntityList.forEach { accountEntity ->
                val account = Account(
                    uuid = accountEntity.id,
                    name = accountEntity.name,
                    description = accountEntity.description
                )
                transactionDao.getTransactionsForAccount(accountEntity.id).collect { transactionEntities ->
                    transactionEntities.forEach { transactionEntity ->
                        account.addTransaction(
                            Transaction(
                                uuid = transactionEntity.id,
                                description = transactionEntity.description,
                                category = categoryRepository.getCategory(transactionEntity.categoryId),
                                account = account,
                                dateTime = transactionEntity.date,
                                value = transactionEntity.value,
                                type = TransactionType.valueOf(transactionEntity.type)
                            )
                        )
                    }
                }
                accountList.add(account)
            }
        }
        return accountList
    }

    override suspend fun save(account: Account): Account {
        TODO("Not yet implemented")
    }
}