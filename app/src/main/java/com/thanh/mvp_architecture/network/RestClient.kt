package com.thanh.mvp_architecture.network

import android.util.Log
import okhttp3.Call
import okhttp3.EventListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 *
 * This is the main entry point for network communication. Use this class for instancing REST services which do the
 * actual communication.
 */
object RestClient {
    private const val BASE_URL = "http://www.mocky.io/"
    private val builder = Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).addConverterFactory(
            GsonConverterFactory.create()).client(httpClient.build())

    private val httpClient: OkHttpClient.Builder
        get() {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(5, TimeUnit.SECONDS).pingInterval(1, TimeUnit.SECONDS).eventListener(object :
                                                                                                                   EventListener() {
                override fun callStart(call: Call) {
                    super.callStart(call)
                    Log.e("Start call", "Start")
                }

                override fun callEnd(call: Call) {
                    super.callEnd(call)
                    Log.e("Stop call", "End")
                }
            })
            return httpClient
        }
    private val retrofit = builder.build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}