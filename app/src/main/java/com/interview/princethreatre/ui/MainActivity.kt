package com.interview.princethreatre.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.interview.princethreatre.R
import com.interview.princethreatre.base.BaseActivity
import com.interview.princethreatre.repository.FilmRepository
import com.interview.princethreatre.util.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Inject
    lateinit var filmRepository: FilmRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        setListener()
    }

    private fun initialize() {
        LogUtil.i("Start get data from API")
        showLoading()
        filmRepository.getFilms("filmworld")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                LogUtil.d("AHHA next")
                LogUtil.e("dsada {${it.Provider}}")
                findViewById<TextView>(R.id.main_text_view).text = it.Provider
            }, {
                LogUtil.e("ERROR OCCUR {${it.message}}")
                LogUtil.e("ERROR", it)
            }, {
                LogUtil.e("COMPELTED")
                hideLoading()
            })
    }

    private fun setListener() {
        findViewById<Button>(R.id.btn_test)
            .setOnClickListener {
                initialize()
            }
    }
}