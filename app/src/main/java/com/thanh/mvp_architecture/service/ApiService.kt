package com.thanh.mvp_architecture.service

import com.thanh.mvp_architecture.model.PhotoModel
import com.thanh.mvp_architecture.model.ProfileModel
import com.thanh.mvp_architecture.model.ResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("v2/5e15a0d03400006500406661")
    fun requestLogin(): Observable<ResponseModel<ProfileModel>>

    @get:GET("v2/5e1597353400005200406612")
    val photos: Observable<ResponseModel<List<PhotoModel>>>
}