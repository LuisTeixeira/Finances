package com.lfmteixeira.composefinances.database.repository

import com.lfmteixeira.composefinances.database.dao.CategoriesDao
import com.lfmteixeira.composefinances.database.dao.TransactionRunner
import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.repository.CategoryRepository

class CategoryDatabaseRepository(
    val categoriesDao: CategoriesDao,
    val transactionRunner: TransactionRunner
) : CategoryRepository {

    override suspend fun getCategory(id: String): Category {
        var category: Category? = null
        categoriesDao.getCategory(id).collect { categoryEntity ->
            category = Category(
                uuid = categoryEntity.id,
                name = categoryEntity.name
            )
        }
        return category!!
    }

    override suspend fun save(category: Category) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCategories(): List<Category> {
        TODO("Not yet implemented")
    }
}