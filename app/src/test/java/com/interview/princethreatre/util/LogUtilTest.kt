package com.interview.princethreatre.util

import org.junit.Assert
import org.junit.Test

class LogUtilTest {
    @Test
    fun testLog() {
        LogUtil.i("Test")
        LogUtil.d("Test")
        LogUtil.w("Test")
        LogUtil.v("Test")
        LogUtil.e("Test")
        LogUtil.e("Test", java.lang.RuntimeException("Test"))
        Assert.assertTrue(true)
    }
}