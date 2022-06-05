package com.lfmteixeira.composefinances.repository.impl

import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val storage: MutableMap<String, Category> = mutableMapOf()
) : CategoryRepository{

    override suspend fun getCategory(id: String): Category {
        return storage[id]!!
    }

    override suspend fun save(category: Category) {
        storage[category.uuid] = category
    }

    override suspend fun getAllCategories(): List<Category> {
        return storage.values.toList()
    }
}