package com.interview.princethreatre.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.interview.princethreatre.R
import com.interview.princethreatre.base.BaseActivity
import com.interview.princethreatre.data.FilmDetail
import com.interview.princethreatre.ui.recyler.ListFilmAdapter
import com.interview.princethreatre.ui.service.FilmService
import com.interview.princethreatre.util.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class ComparisonActivity : BaseActivity() {
    @Inject
    lateinit var filmService: FilmService

    lateinit var filmWorldAdapter: ListFilmAdapter
    lateinit var cinemaWorldAdapter: ListFilmAdapter
    lateinit var searchViewListener: ComparisonSearchViewListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparison)
        setAdapter()
        setListener()
        initialize()
    }

    private fun setListener() {
        searchViewListener = ComparisonSearchViewListener(this)
        findViewById<SearchView>(R.id.comparison_search_film)
            .setOnQueryTextListener(searchViewListener)
    }

    private fun setAdapter() {
        filmWorldAdapter = ListFilmAdapter(ArrayList())
        findViewById<RecyclerView>(R.id.comparison_list_film_world).adapter = filmWorldAdapter

        cinemaWorldAdapter = ListFilmAdapter(ArrayList())
        findViewById<RecyclerView>(R.id.comparison_list_cinema_world).adapter = cinemaWorldAdapter
    }

    @SuppressLint("CheckResult")
    private fun initialize() {
        filmService.getFilmWorldFilms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                filmService.sortByPrice(it.movies)
                filmWorldAdapter.changeDataSet(it.movies)
                searchViewListener.originalFilmWorldList = it.movies
            }, {
                showError(it.message)
            }, {
                //todo what should I do ?
            })
        filmService.getCinemaWorldFilms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                filmService.sortByPrice(it.movies)
                cinemaWorldAdapter.changeDataSet(it.movies)
                searchViewListener.originalCinemaWorldList = it.movies
            }, {
                showError(it.message)
            }, {
                //todo what should I do ?
            })
    }
}

class ComparisonSearchViewListener(val activity: ComparisonActivity) :
    SearchView.OnQueryTextListener {
    lateinit var originalFilmWorldList: MutableList<FilmDetail>
    lateinit var originalCinemaWorldList: MutableList<FilmDetail>

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        LogUtil.i("TEXT change!: $newText")
        val searchText: String = newText ?: ""
        if (searchText.isBlank()) {
            activity.filmWorldAdapter.changeDataSet(originalFilmWorldList)
            activity.cinemaWorldAdapter.changeDataSet(originalCinemaWorldList)
            return false
        }

        activity.filmWorldAdapter.changeDataSet(originalFilmWorldList.filter {
            it.title.lowercase().contains(
                searchText.lowercase()
            )
        }.toMutableList())
        activity.cinemaWorldAdapter.changeDataSet(originalCinemaWorldList.filter {
            it.title.lowercase().contains(
                searchText.lowercase()
            )
        }.toMutableList())
        return false
    }
}