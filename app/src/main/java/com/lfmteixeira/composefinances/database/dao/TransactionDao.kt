package com.lfmteixeira.composefinances.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lfmteixeira.composefinances.database.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TransactionDao {

    @Query(
        """
            SELECT * from transactions as t
            WHERE t.account_id = :accountId
        """
    )
    abstract fun getTransactionsForAccount(accountId: String): Flow<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(transactionEntity: TransactionEntity)
}