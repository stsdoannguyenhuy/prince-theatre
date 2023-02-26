package com.interview.princethreatre.ui.recyler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.interview.princethreatre.R
import com.interview.princethreatre.ui.recyler.base.BaseViewHolder

class ListFilmViewHolder(view: View) : BaseViewHolder(view) {
    val txtId: TextView
    val txtTitle: TextView
    val txtActors: TextView
    val txtPricing: TextView
    val txtType: TextView
    val imgPoster: ImageView

    init {
        txtId = view.findViewById(R.id.item_film_row_id)
        txtActors = view.findViewById(R.id.item_film_row_actors)
        txtPricing = view.findViewById(R.id.item_film_row_pricing)
        txtTitle = view.findViewById(R.id.item_film_row_title)
        txtType = view.findViewById(R.id.item_film_row_type)
        imgPoster = view.findViewById(R.id.item_film_img_poster)
    }
}