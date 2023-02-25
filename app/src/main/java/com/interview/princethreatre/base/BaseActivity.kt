package com.interview.princethreatre.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hidingNavigationBar()
    }

    private fun hidingNavigationBar() {
        this.supportActionBar?.hide()
    }
}