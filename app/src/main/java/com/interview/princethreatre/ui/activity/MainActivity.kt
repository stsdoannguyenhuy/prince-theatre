package com.interview.princethreatre.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.interview.princethreatre.R
import com.interview.princethreatre.base.BaseActivity
import com.interview.princethreatre.data.FilmProvider
import com.interview.princethreatre.data.FilmResponse
import com.interview.princethreatre.ui.recyler.ListFilmAdapter
import com.interview.princethreatre.service.FilmService
import com.interview.princethreatre.util.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Inject
    lateinit var filmService: FilmService

    private val listFilmAdapter: ListFilmAdapter = ListFilmAdapter(ArrayList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
        setAdapter()
        initialize()
    }

    private fun setAdapter() {
        val listFilm = findViewById<RecyclerView>(R.id.lst_film_world_films)
        listFilm.adapter = listFilmAdapter
    }

    @SuppressLint("CheckResult")
    private fun initialize() {
        val provider = intent.extras!!.getString(PROVIDER)
        val filmSource: Observable<FilmResponse>
        if (FilmProvider.FILM_WORLD.apiName == provider) {
            findViewById<TextView>(R.id.txt_film_provider)?.text = FilmProvider.FILM_WORLD.showName
            filmSource = filmService.getFilmWorldFilms()
        } else {
            findViewById<TextView>(R.id.txt_film_provider)?.text =
                FilmProvider.CINEMA_WORLD.showName
            filmSource = filmService.getCinemaWorldFilms()
        }

        LogUtil.i("Start get data from API")
        showLoading()
        filmSource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showMessage("We also sort by price from cheapest to the most expensive for you!")
                val movies = it.movies
                filmService.sortByPrice(movies)
                listFilmAdapter.changeDataSet(movies)
            }, {
                LogUtil.e("ERROR OCCUR {${it.message}}")
                showError(it.message)
            }, {
                hideLoading()
            })
    }

    private fun setListener() {
    }
}