package com.lfmteixeira.composefinances.usecases.category

import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.repository.CategoryRepository

class GetCategory(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(id: String): Category {
        return categoryRepository.getCategory(id)
    }
}