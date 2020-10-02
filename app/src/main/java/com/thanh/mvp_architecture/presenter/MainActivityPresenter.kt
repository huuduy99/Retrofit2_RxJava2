package com.thanh.mvp_architecture.presenter

import android.content.Context
import com.thanh.mvp_architecture.model.PhotoModel

interface MainActivityPresenter {
    fun invokeData()
    interface View {
        fun initAdapter(context: Context, listData: List<PhotoModel>)
        fun initRecyclerView()
    }
}