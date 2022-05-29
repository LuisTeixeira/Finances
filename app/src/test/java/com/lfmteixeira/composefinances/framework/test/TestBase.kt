package com.lfmteixeira.composefinances.framework.test

import com.lfmteixeira.composefinances.framework.config.TestConfig
import com.lfmteixeira.composefinances.framework.testdata.TestAccountFactory
import com.lfmteixeira.composefinances.framework.testdata.TestCategoryFactory

open class TestBase {
    protected val testConfig = TestConfig()
    protected val testAccountFactory = TestAccountFactory(testConfig)
    protected val testCategoryFactory = TestCategoryFactory(testConfig)
}