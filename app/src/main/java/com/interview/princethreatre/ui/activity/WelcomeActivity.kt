package com.interview.princethreatre.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.interview.princethreatre.R
import com.interview.princethreatre.base.BaseActivity
import com.interview.princethreatre.data.FilmProvider

class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setListener()
    }

    private fun setListener() {
        findViewById<Button>(R.id.welcome_btn_film_world).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(PROVIDER, FilmProvider.FILM_WORLD.apiName)
            }
            startActivity(intent)
        }
        findViewById<Button>(R.id.welcome_btn_cinema_world).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(PROVIDER, FilmProvider.CINEMA_WORLD.apiName)
            }
            startActivity(intent)
        }
        findViewById<Button>(R.id.welcome_btn_comparison).setOnClickListener {
            val intent = Intent(this, ComparisonActivity::class.java)
            startActivity(intent)
        }
    }
}