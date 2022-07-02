package com.lfmteixeira.composefinances.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lfmteixeira.composefinances.database.entities.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AccountDao {

    @Query("SELECT * FROM accounts a WHERE a.id = :accountId")
    abstract fun getAccount(accountId: String): Flow<AccountEntity>

    @Query("SELECT * FROM accounts")
    abstract fun getAllAccounts(): Flow<List<AccountEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: AccountEntity)
}