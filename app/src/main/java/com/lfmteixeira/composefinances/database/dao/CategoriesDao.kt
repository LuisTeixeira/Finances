package com.lfmteixeira.composefinances.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lfmteixeira.composefinances.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CategoriesDao {

    @Query("SELECT * FROM categories")
    abstract fun getCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(categoryEntity: CategoryEntity)
}