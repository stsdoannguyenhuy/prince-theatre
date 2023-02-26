package com.interview.princethreatre.ui.recyler.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, U : BaseViewHolder>(private val dataSet: MutableList<T>) :
    RecyclerView.Adapter<U>() {

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun clear() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    fun changeDataSet(dataSet: MutableList<T>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }
    fun getDataSet():MutableList<T>{
        return dataSet
    }

}