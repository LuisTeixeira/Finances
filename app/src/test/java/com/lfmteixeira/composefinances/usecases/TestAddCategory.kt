package com.lfmteixeira.composefinances.usecases

import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.repository.CategoryRepository
import com.lfmteixeira.composefinances.usecases.category.CreateCategory
import com.lfmteixeira.composefinances.usecases.category.GetCategory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestAddCategory {

    private lateinit var createCategory: CreateCategory
    private lateinit var getCategory: GetCategory

    @Before
    fun setup() {
        createCategory = TestConfig.createCategory
        getCategory = TestConfig.getCategory
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