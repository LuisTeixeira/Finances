package com.lfmteixeira.composefinances.usecases.category

import com.lfmteixeira.composefinances.domain.Category
import com.lfmteixeira.composefinances.framework.test.TestBase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestGetAllCategories : TestBase() {
    private lateinit var createCategory: CreateCategory
    private lateinit var getAllCategories: GetAllCategories

    @Before
    fun setup() {
        createCategory = testConfig.createCategory
        getAllCategories = testConfig.getAllCategories
    }

    @Test
    fun testGetAllCategories() {
        runBlocking {
            val createdCategories = createCategories()
            val retrievedCategories = getAllCategories()
            Assert.assertEquals(retrievedCategories, createdCategories)
        }
    }

    private suspend fun createCategories(): List<Category> {
        val categories = mutableListOf<Category>()
        val category1 = createCategory("category1")
        val category2 = createCategory("category2")
        categories.add(category1)
        categories.add(category2)
        return categories
    }
}