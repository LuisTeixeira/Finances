package com.lfmteixeira.composefinances.framework.testdata

import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import kotlinx.coroutines.runBlocking

class TestCategoryFactory(
    val testConfig: TestConfig
) {

    fun createTestCategory(): Category {
        var category: Category
        runBlocking {
            category = testConfig.createCategory("Test category")
        }
        return category
    }
}