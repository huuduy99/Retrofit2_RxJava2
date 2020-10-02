package com.thanh.mvp_architecture.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thanh.mvp_architecture.R
import com.thanh.mvp_architecture.model.PhotoModel
import com.thanh.mvp_architecture.presenter.MainActivityPresenter
import com.thanh.mvp_architecture.presenter.impl.MainActivityPresenterImpl
import com.thanh.mvp_architecture.ui.adapter.PhotoCollectionAdapter

class MainActivity : AppCompatActivity(), MainActivityPresenter.View {
    var recyclerView: RecyclerView? = null
    var adapter: PhotoCollectionAdapter? = null
    var listData: List<PhotoModel>? = null
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rcv_photo)
        presenter = MainActivityPresenterImpl(this, this)
        presenter.invokeData()
    }


    override fun initAdapter(context: Context, listData: List<PhotoModel>) {
        adapter = PhotoCollectionAdapter(context, listData)
    }

    override fun initRecyclerView() {
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = adapter
    }
}