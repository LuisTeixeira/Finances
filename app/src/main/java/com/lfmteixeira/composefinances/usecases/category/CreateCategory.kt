package com.lfmteixeira.composefinances.usecases.category

import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.domain.exception.ValidationException
import com.lfmteixeira.composefinances.repository.CategoryRepository
import java.lang.IllegalArgumentException

class CreateCategory(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(name: String): Result<Category> {
        val result: Result<Category> = try {
            val category = Category(name = name)
            categoryRepository.save(category)
            Result.success(category)
        } catch (e: ValidationException) {
            Result.failure(e)
        }
        return result
    }

}