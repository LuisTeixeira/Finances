package com.lfmteixeira.composefinances.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lfmteixeira.composefinances.database.dao.AccountDao
import com.lfmteixeira.composefinances.database.dao.CategoriesDao
import com.lfmteixeira.composefinances.database.dao.TransactionDao
import com.lfmteixeira.composefinances.database.dao.TransactionRunnerDao
import com.lfmteixeira.composefinances.database.entities.AccountEntity
import com.lfmteixeira.composefinances.database.entities.CategoryEntity
import com.lfmteixeira.composefinances.database.entities.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        AccountEntity::class,
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FinancesDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionRunnerDao(): TransactionRunnerDao
}