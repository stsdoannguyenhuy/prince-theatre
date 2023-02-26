package com.interview.princethreatre.ui.recyler

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.interview.princethreatre.R
import com.interview.princethreatre.data.FilmDetail
import com.interview.princethreatre.ui.recyler.base.BaseAdapter
import com.squareup.picasso.Picasso

class ListFilmAdapter(private val dataSet: MutableList<FilmDetail>) :
    BaseAdapter<FilmDetail, ListFilmViewHolder>(dataSet) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFilmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return ListFilmViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListFilmViewHolder, position: Int) {
        val filmDetail = dataSet[position]
        holder.txtId.text = "Film Id: ${filmDetail.id}"
        holder.txtId.visibility = View.GONE
        holder.txtTitle.text = filmDetail.title
        holder.txtPricing.text = "${filmDetail.price} $"
        holder.txtType.text = filmDetail.type
        holder.txtType.visibility = View.GONE
        //film actor
        val filmActorDisplay = "Actors: "+filmDetail.actors.split(",")
            .map { "<font color='yellow'>$it</font>" }
            .joinToString { it }
        holder.txtActors.text = Html.fromHtml(filmActorDisplay)
        Picasso.get().load(filmDetail.poster).into(holder.imgPoster)
    }
}