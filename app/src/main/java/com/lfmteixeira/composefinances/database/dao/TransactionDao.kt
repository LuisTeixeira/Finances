package com.lfmteixeira.composefinances.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lfmteixeira.composefinances.database.entities.TransactionEntity

@Dao
abstract class TransactionDao {

    @Query(
        """
            SELECT * from transactions as t
            WHERE t.account_id = :accountId
        """
    )
    abstract suspend fun getTransactionsForAccount(accountId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(transactionEntity: TransactionEntity)
}