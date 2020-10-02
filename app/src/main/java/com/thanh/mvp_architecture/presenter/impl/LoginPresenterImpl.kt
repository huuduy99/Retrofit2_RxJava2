package com.thanh.mvp_architecture.presenter.impl

import android.content.Context
import com.thanh.mvp_architecture.network.RestClient
import com.thanh.mvp_architecture.presenter.LoginPresenter
import com.thanh.mvp_architecture.service.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginPresenterImpl(private val context: Context, private val view: LoginPresenter.View) : LoginPresenter {
    override fun onLogin(username: String?, password: String?) {
        view.onLoginPending()
        if (username == "1" && password == "1") {
            val service = RestClient.createService(ApiService::class.java)
            service.requestLogin().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                                                                                                                        view.onLoginSuccess()
                                                                                                                        view.onStopProcessBar()
                                                                                                                    }) { throwable: Throwable ->
                throwable.printStackTrace()
                view.onLoginFail()
            }
        } else view.onLoginFail()
    }
}