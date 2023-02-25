package com.interview.princethreatre.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.interview.princethreatre.R
import com.interview.princethreatre.base.BaseActivity
import com.interview.princethreatre.repository.DataSource
import com.interview.princethreatre.util.LogUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        setListener()
    }

    private fun initialize() {
        val dataSource = DataSource()
        LogUtil.i("Start get data from API")
        dataSource.filmRepository.getFilms("filmworld")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                LogUtil.d("AHHA next")
                LogUtil.e("dsada {${it.Provider}}")
                findViewById<TextView>(R.id.main_text_view).text = it.Provider
            }, {
                LogUtil.e("ERROR OCCUR {${it.message}}")
                LogUtil.e("ERROR",it)
            }, {
                LogUtil.e("COMPELTED")
            })
    }

    private fun setListener() {
        findViewById<Button>(R.id.btn_test)
            .setOnClickListener {
                initialize()
            }
    }
}