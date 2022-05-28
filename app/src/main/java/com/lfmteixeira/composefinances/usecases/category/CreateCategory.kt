package com.lfmteixeira.composefinances.usecases.category

import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.repository.CategoryRepository

class CreateCategory(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(name: String): Category {
        val category = Category(name = name)
        categoryRepository.save(category)
        return category
    }

}