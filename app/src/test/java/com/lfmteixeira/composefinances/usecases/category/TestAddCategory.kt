package com.lfmteixeira.composefinances.usecases.category

import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.framework.test.TestBase
import com.lfmteixeira.composefinances.repository.CategoryRepository
import com.lfmteixeira.composefinances.usecases.category.CreateCategory
import com.lfmteixeira.composefinances.usecases.category.GetCategory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestAddCategory : TestBase() {

    private lateinit var createCategory: CreateCategory
    private lateinit var getCategory: GetCategory

    @Before
    fun setup() {
        createCategory = testConfig.createCategory
        getCategory = testConfig.getCategory
    }

    @Test
    fun testCreateCategory(){
        runBlocking {
            val categoryName = "Rent"
            val createdCategory = createCategory(categoryName)
            val retrievedCategory = getCategory(createdCategory.uuid)
            Assert.assertEquals(createdCategory, retrievedCategory)
        }
    }

}