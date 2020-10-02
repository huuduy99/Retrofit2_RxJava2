package com.thanh.mvp_architecture.presenter.impl

import android.content.Context
import com.thanh.mvp_architecture.model.PhotoModel
import com.thanh.mvp_architecture.model.ResponseModel
import com.thanh.mvp_architecture.network.RestClient
import com.thanh.mvp_architecture.presenter.MainActivityPresenter
import com.thanh.mvp_architecture.service.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityPresenterImpl(private val context: Context, private val view: MainActivityPresenter.View) :
        MainActivityPresenter {
    override fun invokeData() {
        val service = RestClient.createService(ApiService::class.java)
        service.photos.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter { responseModel: ResponseModel<List<PhotoModel>> ->
            val list = responseModel.data
            for (t in list!!) {
                if (t.name == "Con mèo") t.name = "Thanh"
            }
            responseModel.setData(list)
            true
        }.subscribe({ response: ResponseModel<List<PhotoModel>> ->
                                // Log.e("respone",new Gson().toJson(response));
                                view.initAdapter(context, response.data!!)
                                view.initRecyclerView()
                            }) { throwable: Throwable -> throwable.printStackTrace() }
    }
}