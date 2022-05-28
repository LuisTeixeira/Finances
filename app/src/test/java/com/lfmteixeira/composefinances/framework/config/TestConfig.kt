package com.lfmteixeira.composefinances.framework.config

import com.lfmteixeira.composefinances.framework.CategoryRepositoryTestImpl
import com.lfmteixeira.composefinances.usecases.category.CreateCategory
import com.lfmteixeira.composefinances.usecases.category.GetCategory

object TestConfig {

    private val categoryRepository = CategoryRepositoryTestImpl()

    val createCategory = CreateCategory(categoryRepository = categoryRepository)
    val getCategory = GetCategory(categoryRepository = categoryRepository)

}