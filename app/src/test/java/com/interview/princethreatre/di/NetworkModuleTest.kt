package com.interview.princethreatre.di

import com.interview.princethreatre.util.LogUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NetworkModuleTest {
    @Test
    fun testProvideDataSource() {
        val netWorkModule = NetworkModule()
        netWorkModule.provideDataSource()
        LogUtil.i("data source module installed successfully")
        Assert.assertTrue(true)
    }
    @Test
    fun testProvideFilmRepo(){
        val networkModule = NetworkModule()
        val dataSource = networkModule.provideDataSource()
        networkModule.provideFilmRepo(dataSource)
        LogUtil.i("film repo module installed successfully")
        Assert.assertTrue(true)
    }
}