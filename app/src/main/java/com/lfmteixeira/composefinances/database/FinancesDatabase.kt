package com.lfmteixeira.composefinances.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lfmteixeira.composefinances.database.dao.*
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
@TypeConverters(DateTimeTypeConverters::class)
abstract class FinancesDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionRunnerDao(): TransactionRunnerDao
}