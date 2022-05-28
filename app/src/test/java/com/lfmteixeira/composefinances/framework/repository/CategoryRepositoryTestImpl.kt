package com.lfmteixeira.composefinances.framework.repository

import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.repository.CategoryRepository

class CategoryRepositoryTestImpl(
    private val storage: MutableMap<String, Category> = mutableMapOf()
) : CategoryRepository {
    override suspend fun getCategory(id: String): Category {
        return storage[id]!!
    }

    override suspend fun save(category: Category) {
        storage[category.uuid] = category
    }
}


