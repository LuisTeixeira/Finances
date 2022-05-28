package com.lfmteixeira.composefinances.repository

import com.lfmteixeira.composefinances.domain.Category

interface CategoryRepository {
    suspend fun getCategory(id: String): Category
    suspend fun save(category: Category)
}