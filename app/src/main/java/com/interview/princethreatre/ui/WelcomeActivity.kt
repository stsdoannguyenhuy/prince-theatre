package com.interview.princethreatre.ui

import android.content.Intent
import android.os.Bundle
import com.interview.princethreatre.R
import com.interview.princethreatre.base.BaseActivity
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class WelcomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        initialize()
    }

    private fun initialize(){
        Observable.timer(2,TimeUnit.SECONDS)
            .doOnNext{
                val intent = Intent(this, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
                startActivity(intent)
            }
            .subscribe()

    }
}