package com.lfmteixeira.composefinances.usecases.category

import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.repository.CategoryRepository

class GetAllCategories(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> {
        return categoryRepository.getAllCategories()
    }
}